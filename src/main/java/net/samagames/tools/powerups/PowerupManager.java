package net.samagames.tools.powerups;

import net.samagames.api.SamaGamesAPI;
import net.samagames.tools.Titles;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Power up manager class
 *
 * Copyright (c) for SamaGames
 * All right reserved
 *
 * @author Amaury Carrade, IamBlueSlime
 */
public class PowerupManager
{
    private final JavaPlugin plugin;
    private final Random random;
    private final List<Powerup> powerups;
    private final List<Location> locations;

    private BukkitTask spawnTask;
    private int inverseFrequency;
    private double totalWeight;

    public PowerupManager(JavaPlugin plugin)
    {
        this.plugin = plugin;
        this.random = new Random();

        this.powerups = new ArrayList<>();
        this.locations = new ArrayList<>();
    }

    public void start()
    {
        this.spawnTask = this.plugin.getServer().getScheduler().runTaskTimerAsynchronously(this.plugin, () ->
        {
            if (this.random.nextInt(this.inverseFrequency) == 0)
                this.spawnRandomPowerup();
        }, 1L, 1L);
    }

    public void stop()
    {
        this.spawnTask.cancel();
    }

    public void registerPowerup(Powerup powerup)
    {
        this.powerups.add(powerup);
        this.totalWeight += powerup.getWeight();
    }

    public void registerLocation(Location location)
    {
        this.locations.add(location);
    }

    public void setInverseFrequency(int inverseFrequency)
    {
        this.inverseFrequency = inverseFrequency;
    }

    private void spawnRandomPowerup()
    {
        if(this.locations.isEmpty())
            return;

        Location location = this.locations.get(this.random.nextInt(this.locations.size()));

        Powerup powerup = null;
        double randomIndex = this.random.nextDouble() * this.totalWeight;

        for(Powerup testedPowerup : this.powerups)
        {
            randomIndex -= testedPowerup.getWeight();

            if(randomIndex <= 0.0D)
            {
                powerup = testedPowerup;
                break;
            }
        }

        if(powerup == null)
            throw new RuntimeException("Cannot find a powerup to spawn");

        this.locations.remove(location);

        ActivePowerup activePowerup = powerup.spawn(location);

        for (Player player : this.plugin.getServer().getOnlinePlayers())
        {
            player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1.0F, 1.0F);
            Titles.sendTitle(player, 5, 30, 5, ChatColor.GOLD + "✯", "");
        }

        SamaGamesAPI.get().getGameManager().getCoherenceMachine().getMessageManager().writeCustomMessage(ChatColor.YELLOW + "Un bonus vient de faire son apparition !", true);

        this.plugin.getServer().getScheduler().runTaskLater(this.plugin, () ->
        {
            if (activePowerup.isAlive())
            {
                activePowerup.remove(false);
                this.locations.add(location);
            }
        }, 20 * 20L);
    }
}

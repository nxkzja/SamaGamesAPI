package net.samagames.api.coins;

import org.bukkit.Bukkit;

import java.util.UUID;

/**
 * This file is a part of the SamaGames project
 * This code is absolutely confidential.
 * Created by zyuiop
 * (C) Copyright Elydra Network 2015
 * All rights reserved.
 */
public interface CoinsManager {

	public void creditPlayer(UUID player, int amount, String reason);

	public void creditPlayer(UUID player, int amount, String reason, boolean applyMultiplier);

	public void creditPlayer(UUID player, int amount);

	public int creditPlayerSynchronized(UUID player, int amount, String reason, boolean applyMultiplier);

	public void withdrawPlayer(UUID player, int amount);

	public int withdrawPlayerSynchronized(UUID player, int amount);

	public boolean canPay(UUID player, int amount);

	public int getAmount(UUID player);
}

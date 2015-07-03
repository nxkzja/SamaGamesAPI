package net.samagames.api;

import net.samagames.api.achievements.AchievementManager;
import net.samagames.api.friends.FriendsManager;
import net.samagames.api.games.IGameManager;
import net.samagames.api.names.UUIDTranslator;
import net.samagames.api.network.ProxyDataManager;
import net.samagames.api.parties.PartiesManager;
import net.samagames.api.permissions.PermissionsManager;
import net.samagames.api.player.PlayerDataManager;
import net.samagames.api.resourcepacks.ResourcePacksManager;
import net.samagames.api.settings.SettingsManager;
import net.samagames.api.shops.ShopsManager;
import net.samagames.api.stats.StatsManager;
import net.samagames.tools.BarAPI.BarAPI;

/**
 * This file is a part of the SamaGames project
 * This code is absolutely confidential.
 * (C) Copyright Elydra Network 2015
 * All rights reserved.
 */
public abstract class SamaGamesAPI {

	private static SamaGamesAPI instance;

	protected SamaGamesAPI() {
		instance = this;
	}

	public static SamaGamesAPI get() {
		return instance;
	}

	public abstract String getServerName();

	public abstract StatsManager getStatsManager(String game);
	public abstract ShopsManager getShopsManager(String game);
	public abstract SettingsManager getSettingsManager();
	public abstract PlayerDataManager getPlayerManager();
    public abstract AchievementManager getAchievementManager();
	public abstract UUIDTranslator getUUIDTranslator();
	public abstract IGameManager getGameManager();
	public abstract ResourcePacksManager getResourcePacksManager();
    public abstract FriendsManager getFriendsManager();

	public abstract ProxyDataManager getProxyDataManager();
	public abstract PartiesManager getPartiesManager();

	public abstract BarAPI getBarAPI();

	public abstract PermissionsManager getPermissionsManager();

}

package me.fanhua.piggies.tool.player;

import org.bukkit.entity.Player;

public final class Players {

	private Players() {}

	public static boolean isInGame(Player player) {
		return switch (player.getGameMode()) {
			case SURVIVAL -> true;
			case ADVENTURE -> true;
			default -> false;
		};
	}

}

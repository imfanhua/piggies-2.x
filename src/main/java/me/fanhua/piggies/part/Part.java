package me.fanhua.piggies.part;

import org.bukkit.entity.Player;

public record Part<P extends IPart>(int index) {

	public P from(Player player) {
		return Parts.get(player, this);
	}

}
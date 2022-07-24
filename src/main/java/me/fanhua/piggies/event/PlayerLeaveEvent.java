package me.fanhua.piggies.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class PlayerLeaveEvent extends PlayerEvent {

	private static final HandlerList HANDLERS = new HandlerList();
	public static HandlerList getHandlerList() {
		return HANDLERS;
	}

	public PlayerLeaveEvent(Player who) {
		super(who);
	}

	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}

}

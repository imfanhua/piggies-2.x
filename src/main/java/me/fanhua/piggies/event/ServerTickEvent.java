package me.fanhua.piggies.event;

import org.bukkit.event.HandlerList;
import org.bukkit.event.server.ServerEvent;

public class ServerTickEvent extends ServerEvent {

	private static final HandlerList HANDLERS = new HandlerList();
	public static HandlerList getHandlerList() {
		return HANDLERS;
	}

	private final int tick;

	public ServerTickEvent(int tick) {
		this.tick = tick;
	}

	public int getTick() {
		return this.tick;
	}

	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}

}

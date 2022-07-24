package me.fanhua.piggies.tool.misc;

import me.fanhua.piggies.handler.TickHandlers;

public final class ServerTools {

	private ServerTools() {}

	public static int tick() {
		return TickHandlers.getTick();
	}

}

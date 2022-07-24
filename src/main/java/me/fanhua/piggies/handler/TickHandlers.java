package me.fanhua.piggies.handler;

import me.fanhua.piggies.Piggies;
import me.fanhua.piggies.event.ServerTickEvent;
import org.bukkit.Bukkit;

@Deprecated
public final class TickHandlers {

	private static int tick;

	@Deprecated
	private TickHandlers() {}

	@Deprecated
	public static void register() {
		Piggies.instance().task(TickHandlers::onTick);
	}

	private static void onTick() {
		Bukkit.getPluginManager().callEvent(new ServerTickEvent(tick++));
	}

	@Deprecated
	public static int getTick() {
		return tick;
	}

}

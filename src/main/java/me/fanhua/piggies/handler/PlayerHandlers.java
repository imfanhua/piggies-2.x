package me.fanhua.piggies.handler;

import me.fanhua.piggies.event.PlayerLeaveEvent;
import me.fanhua.piggies.part.Parts;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@Deprecated
public final class PlayerHandlers implements Listener {

	@Deprecated
	public static final PlayerHandlers HANDLERS = new PlayerHandlers();

	@Deprecated
	private PlayerHandlers() {}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerQuitEvent(PlayerQuitEvent event) {
		Bukkit.getPluginManager().callEvent(new PlayerLeaveEvent(event.getPlayer()));
		Parts.remove(event.getPlayer());
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerKickEvent(PlayerKickEvent event) {
		Bukkit.getPluginManager().callEvent(new PlayerLeaveEvent(event.getPlayer()));
		Parts.remove(event.getPlayer());
	}

}

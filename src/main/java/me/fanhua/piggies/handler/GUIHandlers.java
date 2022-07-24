package me.fanhua.piggies.handler;

import me.fanhua.piggies.event.ServerTickEvent;
import me.fanhua.piggies.gui.IBaseGUI;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

@Deprecated
public final class GUIHandlers implements Listener {

	@Deprecated
	public static final GUIHandlers HANDLERS = new GUIHandlers();

	@Deprecated
	private GUIHandlers() {}

	@EventHandler(priority = EventPriority.LOW)
	public void onInventoryClickEvent(InventoryClickEvent event) {
		var top = event.getView().getTopInventory();
		if (!(top.getHolder() instanceof IBaseGUI gui)) return;
		var inv = event.getClickedInventory();
		if (inv != top) {
			event.setCancelled(event.getClick() != ClickType.LEFT);
			return;
		}
		if (inv.getHolder() != gui) return;
		event.setCancelled(true);
		gui.whenUse(event);
	}

	@EventHandler
	public void onInventoryDragEvent(InventoryDragEvent event) {
		if (!(event.getView().getTopInventory().getHolder() instanceof IBaseGUI)) return;
		event.setCancelled(true);
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onInventoryCloseEvent(InventoryCloseEvent event) {
		if (!(event.getInventory().getHolder() instanceof IBaseGUI gui)) return;
		gui.whenClose(event);
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onServerTickEvent(ServerTickEvent event) {
		for (var player : Bukkit.getOnlinePlayers()) {
			if (!(player.getOpenInventory().getTopInventory().getHolder() instanceof IBaseGUI gui)) continue;
			gui.whenUpdate();
		}
	}

}

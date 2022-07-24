package me.fanhua.piggies.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryHolder;

public interface IBaseGUI extends InventoryHolder {

	GUISize getSize();

	void whenUpdate();
	void whenUse(InventoryClickEvent event);
	void whenClose(InventoryCloseEvent event);

	default void open(Player player) {
		player.openInventory(getInventory());
	}

}

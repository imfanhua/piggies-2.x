package me.fanhua.piggies.gui;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public interface InventoryFactory {

	GUISize size();
	Inventory create(InventoryHolder holder, String title);

}

package me.fanhua.piggies.gui.impl;

import me.fanhua.piggies.gui.GUISize;
import me.fanhua.piggies.gui.InventoryFactory;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

@Deprecated
public class ChestInventoryFactory implements InventoryFactory {

	public final GUISize size;

	@Deprecated
	public ChestInventoryFactory(int lines) {
		this.size = new GUISize(9, lines);
	}

	@Override
	public GUISize size() {
		return size;
	}

	@Override
	public Inventory create(InventoryHolder holder, String title) {
		return Bukkit.createInventory(holder, size.size, title);
	}

}

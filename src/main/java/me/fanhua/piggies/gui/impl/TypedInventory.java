package me.fanhua.piggies.gui.impl;

import me.fanhua.piggies.gui.GUISize;
import me.fanhua.piggies.gui.InventoryFactory;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public enum TypedInventory implements InventoryFactory {

	BOX(InventoryType.DISPENSER, new GUISize(3, 3)),
	HOPPER(InventoryType.HOPPER, new GUISize(5, 1)),
	;

	public final InventoryType type;
	public final GUISize size;

	TypedInventory(InventoryType type, GUISize size) {
		this.type = type;
		this.size = size;
	}

	@Override
	public GUISize size() {
		return size;
	}

	@Override
	public Inventory create(InventoryHolder holder, String title) {
		return Bukkit.createInventory(holder, type, title);
	}

}

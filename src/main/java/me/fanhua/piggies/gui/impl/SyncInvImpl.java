package me.fanhua.piggies.gui.impl;

import me.fanhua.piggies.Piggies;
import me.fanhua.piggies.gui.GUI;
import me.fanhua.piggies.gui.GUISize;
import me.fanhua.piggies.gui.IBaseGUI;
import me.fanhua.piggies.gui.InventoryFactory;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

@Deprecated
public class SyncInvImpl implements IBaseGUI {

	private static final InventoryFactory FACTORY = GUI.linesOf(5);
	private static final int SIZE = 41;

	private final UUID id;
	private final Inventory inventory;

	@Deprecated
	public SyncInvImpl(Player player) {
		id = player.getUniqueId();
		inventory = FACTORY.create(this, "§7> §e" + player.getDisplayName());
	}

	@Override
	public GUISize getSize() {
		return FACTORY.size();
	}

	@Override
	public void whenUpdate() {
		var target = Bukkit.getPlayer(id);
		if (target == null) {
			for (var x : inventory.getViewers()) x.closeInventory();
			return;
		}
		var inv = target.getInventory();
		for (var i = 0; i < SIZE; i++) {
			var item = inv.getItem(i);
			var now = inventory.getItem(i);
			if ((item == null && now == null) || (item != null && item.equals(now))) continue;
			inventory.setItem(i, item == null ? null : item.clone());
		}
	}

	@Override
	public void whenUse(InventoryClickEvent event) {
		if (event.getClickedInventory() != inventory) {
			event.setCancelled(event.getClick() != ClickType.LEFT);
			return;
		}
		var slot = event.getSlot();
		if (slot >= SIZE) return;
		sync(slot, event.getWhoClicked());
	}

	private void sync(int slot, HumanEntity clicker) {
		Piggies.instance().tick(() -> {
			var target = Bukkit.getPlayer(id);
			if (target == null) return;
			var inv = target.getInventory();
			var item = inv.getItem(slot);
			if (item != null) item = item.clone();
			inv.setItem(slot, clicker.getItemOnCursor());
			clicker.setItemOnCursor(item);
		});
	}

	@Override
	public void whenClose(InventoryCloseEvent event) {}

	@Override
	public Inventory getInventory() {
		return inventory;
	}

}

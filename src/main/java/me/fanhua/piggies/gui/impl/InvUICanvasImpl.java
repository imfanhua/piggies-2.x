package me.fanhua.piggies.gui.impl;

import me.fanhua.piggies.gui.GUISize;
import me.fanhua.piggies.gui.ui.IUICanvas;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@Deprecated
public class InvUICanvasImpl implements IUICanvas {

	private final GUISize size;
	private final Inventory inventory;

	@Deprecated
	public InvUICanvasImpl(GUISize size, Inventory inventory) {
		this.size = size;
		this.inventory = inventory;
	}

	@Override
	public GUISize size() {
		return size;
	}

	@Override
	public void draw(int x, int y, ItemStack item) {
		inventory.setItem(size.slot(x, y), item);
	}

}

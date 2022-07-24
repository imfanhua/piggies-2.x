package me.fanhua.piggies.gui;

import me.fanhua.piggies.gui.ui.IUICanvas;
import org.bukkit.inventory.ItemStack;

public final class GUIImage {

	private final ItemStack[][] icons;

	public GUIImage(ItemStack[][] icons) {
		this.icons = icons;
	}

	public void draw(IUICanvas canvas, int x, int y) {
		var length = icons.length;
		for (var l = 0; l < length; l++) {
			var items = icons[l];
			for (var i = 0; i < items.length; i++)
				canvas.draw(x + i, y + l, items[i]);
		}
	}

}

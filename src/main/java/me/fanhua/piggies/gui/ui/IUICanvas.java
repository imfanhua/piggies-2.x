package me.fanhua.piggies.gui.ui;

import me.fanhua.piggies.gui.GUISize;
import org.bukkit.inventory.ItemStack;

public interface IUICanvas {

	GUISize size();
	void draw(int x, int y, ItemStack item);

	default IUICanvas diffOf(int x, int y, int width, int height) {
		if (x == 0 && y == 0 && width == -1 && height == -1) return this;
		var size = size();
		return diff(x, y, width == -1 ? size.width - x : width, height == -1 ? size.lines - y : height);
	}

	default IUICanvas diff(int x, int y) {
		var size = size();
		return diff(x, y, size.width - x, size.lines - y);
	}

	default IUICanvas diff(int x, int y, int width, int height) {
		return new UIDiffCanvas(this, x, y, width, height);
	}

	default void drawHLine(int x, int y, int endX, ItemStack item) {
		var length = endX - x;
		if (length == 0) return;
		else if (length < 0) {
			x = endX;
			length = -length;
		}
		for (var i = 0; i < length; i++)
			draw(x + i, y, item);
	}

	default void drawHLine(int x, int y, ItemStack item) {
		drawHLine(x, y, size().width, item);
	}

	default void drawHLine(int x, ItemStack item) {
		drawHLine(x, 0, size().width, item);
	}

	default void drawHLine(ItemStack item) {
		drawHLine(0, size().lines - 1, size().width, item);
	}

	default void drawVLine(int x, int y, int endY, ItemStack item) {
		var length = endY - y;
		if (length == 0) return;
		else if (length < 0) {
			y = endY;
			length = -length;
		}
		for (var i = 0; i < length; i++)
			draw(x, y + i, item);
	}

	default void drawVLine(int x, int y, ItemStack item) {
		drawVLine(x, y, size().lines, item);
	}

	default void drawVLine(int y, ItemStack item) {
		drawVLine(0, y, size().lines, item);
	}

	default void drawVLine(ItemStack item) {
		drawVLine(size().width - 1, 0, size().lines, item);
	}

}

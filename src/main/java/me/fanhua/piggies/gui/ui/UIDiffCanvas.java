package me.fanhua.piggies.gui.ui;

import me.fanhua.piggies.gui.GUISize;
import org.bukkit.inventory.ItemStack;

public class UIDiffCanvas implements IUICanvas {

	private final IUICanvas canvas;

	private final int x;
	private final int y;

	private final int width;
	private final int height;

	private final GUISize size;

	public UIDiffCanvas(IUICanvas canvas, int x, int y, int width, int height) {
		this.canvas = canvas;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.size = new GUISize(width, height);
	}

	@Override
	public GUISize size() {
		return size;
	}

	@Override
	public void draw(int x, int y, ItemStack item) {
		canvas.draw(this.x + x, this.y + y, item);
	}

	@Override
	public IUICanvas diff(int x, int y, int width, int height) {
		return new UIDiffCanvas(canvas, this.x + x, this.y + y, width, height);
	}

}

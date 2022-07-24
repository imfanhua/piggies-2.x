package me.fanhua.piggies.gui.ui;

import me.fanhua.piggies.gui.GUIImage;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class UIImage implements IUI {

	private int x;
	private int y;

	private GUIImage image;

	private boolean redraw = true;

	public UIImage(GUIImage image) {
		this(0, 0, image);
	}

	public UIImage(int x, int y, GUIImage image) {
		this.x = x;
		this.y = y;
		this.image = image;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		if (this.x == x) return;
		this.x = x;
		this.redraw = true;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		if (this.y == y) return;
		this.y = y;
		this.redraw = true;
	}

	public GUIImage getImage() {
		return image;
	}

	public void setImage(GUIImage image) {
		if (this.image == image) return;
		this.image = image;
		redraw = true;
	}

	@Override
	public boolean redraw() {
		return redraw;
	}

	@Override
	public void update() {}

	@Override
	public void draw(IUICanvas canvas) {
		redraw = false;
		if (image != null) image.draw(canvas, x, y);
	}

	@Override
	public boolean use(Player clicker, ClickType type, int x, int y) {
		return false;
	}

}

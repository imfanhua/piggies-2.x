package me.fanhua.piggies.gui.ui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.function.BiConsumer;

public class UIButton implements IUI {

	private int x;
	private int y;

	private ItemStack icon;
	private BiConsumer<Player, ClickType> handler;

	private boolean redraw = true;

	public UIButton(int x, int y, ItemStack icon, BiConsumer<Player, ClickType> handler) {
		this.x = x;
		this.y = y;
		this.icon = icon;
		this.handler = handler;
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

	public ItemStack getIcon() {
		return icon;
	}

	public void setIcon(ItemStack icon) {
		if (Objects.equals(this.icon, icon)) return;
		this.icon = icon;
		redraw = true;
	}

	public BiConsumer<Player, ClickType> getHandler() {
		return handler;
	}

	public void setHandler(BiConsumer<Player, ClickType> handler) {
		this.handler = handler;
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
		if (icon != null) canvas.draw(x, y, icon);
	}

	@Override
	public boolean use(Player clicker, ClickType type, int x, int y) {
		if (this.x != x || this.y != y) return false;
		if (handler != null) handler.accept(clicker, type);
		return true;
	}

}

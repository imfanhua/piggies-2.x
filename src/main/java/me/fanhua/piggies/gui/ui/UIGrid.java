package me.fanhua.piggies.gui.ui;

import me.fanhua.piggies.gui.GUISize;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UIGrid implements IContainerUI {

	private int x;
	private int y;

	private int width;
	private int height;

	private GUISize lastSize;

	private boolean redraw = true;
	private final List<IUI> ui = new ArrayList<>();

	public UIGrid() {
		this(0, 0, -1, -1);
	}

	public UIGrid(int x, int y) {
		this(x, y, -1, -1);
	}

	public UIGrid(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
		this.redraw = true;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
		this.redraw = true;
	}

	@Override
	public boolean redraw() {
		if (redraw) return true;
		for (var ui : ui) if (ui.redraw()) return true;
		return false;
	}

	@Override
	public void update() {
		for (var ui : ui) ui.update();
	}

	@Override
	public void draw(IUICanvas canvas) {
		redraw = false;
		var diff = canvas.diffOf(x, y, width, height);
		lastSize = diff.size();
		for (var ui : ui) ui.draw(diff);
	}

	@Override
	public boolean use(Player clicker, ClickType type, int x, int y) {
		x -= this.x;
		y -= this.y;
		if (x < 0 || y < 0 || x > lastSize.width || y > lastSize.lines) return false;
		for (var ui : ui) if (ui.use(clicker, type, x, y)) return true;
		return false;
	}

	@Override
	public <T extends IUI> T add(T ui) {
		if (!this.ui.contains(ui)) this.ui.add(ui);
		redraw = true;
		return ui;
	}

	@Override
	public <T extends IUI> T remove(T ui) {
		if (this.ui.remove(ui)) redraw = true;
		return ui;
	}

	@Override
	public List<IUI> getAllUI() {
		return Collections.unmodifiableList(this.ui);
	}

	@Override
	public void clear() {
		if (this.ui.isEmpty()) return;
		this.ui.clear();
		redraw = true;
	}

}

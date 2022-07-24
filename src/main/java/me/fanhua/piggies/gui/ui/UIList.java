package me.fanhua.piggies.gui.ui;

import me.fanhua.piggies.gui.GUISize;
import me.fanhua.piggies.tool.item.Items;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import java.util.List;

public abstract class UIList<T> implements IUI {

	private int x;
	private int y;

	private int width;
	private int height;

	private int page;
	private GUISize lastSize;
	private int lastStart;

	private boolean redraw = true;
	private List<T> list;

	private int extra;

	public UIList(List<T> list, int x, int y) {
		this(list, 0, x, y, -1, -1);
	}

	public UIList(List<T> list, int x, int y, int width, int height) {
		this(list, 0, x, y, width, height);
	}

	public UIList(List<T> list) {
		this(list, 0, 0, 0, -1, -1);
	}

	public UIList(List<T> list, int extra) {
		this(list, extra, 0, 0, -1, -1);
	}

	public UIList(List<T> list, int extra, int x, int y) {
		this(list, extra, x, y, -1, -1);
	}

	public UIList(List<T> list, int extra, int x, int y, int width, int height) {
		this.list = list;
		this.extra = extra;
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

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		if (this.list == list) return;
		this.list = list;
		redraw = true;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if (this.page == page) return;
		this.page = page;
		redraw = true;
	}

	public void prevPage() {
		if (this.page < 1) return;
		this.page--;
		redraw = true;
	}

	public void nextPage() {
		this.page++;
		redraw = true;
	}

	public void lastPage() {
		this.page = Integer.MAX_VALUE;
		redraw = true;
	}

	public int getExtra() {
		return extra;
	}

	public void setExtra(int extra) {
		if (this.extra == extra) return;
		this.extra = extra;
		redraw = true;
	}

	public void updated() {
		redraw = true;
	}

	@Override
	public boolean redraw() {
		return redraw;
	}

	@Override
	public void update() {}

	public int getTotalPage() {
		if (lastSize == null || list == null) return 0;
		var total = lastSize.size - this.extra - 1;
		if (total < 1) return 0;
		var size = list.size();
		var page = size / total;
		return page * total < size ? page + 1 : page;
	}

	@Override
	public void draw(IUICanvas canvas) {
		redraw = false;
		var diff = canvas.diffOf(x, y, width, height);
		lastSize = diff.size();
		if (list == null || list.isEmpty()) {
			draw(canvas, lastSize.width - 1, lastSize.lines - 1, 0, 1);
			return;
		}
		var pageSize = lastSize.size - this.extra - 1;
		var total = getTotalPage();
		if (page < 0) page = 0;
		else if (page >= total) page = total - 1;
		if (page >= total) return;
		var start = page * pageSize;
		var size = list.size();
		var width = lastSize.width;
		lastStart = start;
		for (var i = 0; i < pageSize; i++) {
			var index = start + i;
			if (index >= size) break;
			var y = i / width;
			var x = i - y * width;
			draw(canvas, x, y, list.get(index), index);
		}
		draw(canvas, lastSize.width - 1, lastSize.lines - 1, page, total);
	}

	@Override
	public boolean use(Player clicker, ClickType type, int x, int y) {
		if (lastSize == null || list == null || list.isEmpty()) return false;
		x -= this.x;
		y -= this.y;
		if (x < 0 || y < 0 || x > lastSize.width || y > lastSize.lines) return false;
		var pos = y * lastSize.width + x;
		var i = lastStart + pos;
		if (pos >= lastSize.size - extra - 1) {
			if (pos == lastSize.size - 1) return use(clicker.getPlayer(), type);
		} else if (i < list.size()) return use(clicker, type, x, y, list.get(i), i);
		return false;
	}

	protected void draw(IUICanvas canvas, int x, int y, int page, int maxPage) {
		var item = Items.item(
				Material.BOOK,
				"§e" + (page + 1) + "§7 / §6" + maxPage,
				" §7 > §a§l左键§6 下一页",
				" §7 > §e§l右键§6 上一页"
		);
		if (page < 63) item.setAmount(page + 1);
		canvas.draw(x, y, item);
	}

	protected abstract void draw(IUICanvas canvas, int x, int y, T value, int index);

	protected abstract boolean use(Player clicker, ClickType type, int x, int y, T value, int index);

	protected boolean use(Player clicker, ClickType type) {
		if (type.isLeftClick()) nextPage();
		else if (type.isRightClick()) prevPage();
		return true;
	}

}

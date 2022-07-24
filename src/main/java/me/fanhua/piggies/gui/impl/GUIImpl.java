package me.fanhua.piggies.gui.impl;

import me.fanhua.piggies.gui.GUISize;
import me.fanhua.piggies.gui.IGUI;
import me.fanhua.piggies.gui.InventoryFactory;
import me.fanhua.piggies.gui.ui.IUI;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;

@Deprecated
public class GUIImpl implements IGUI {

	private final GUISize size;
	private final Inventory inventory;
	private final InvUICanvasImpl canvas;

	private String title;

	private boolean redraw = true;
	private final List<IUI> ui = new ArrayList<>();

	private final List<BiConsumer<IGUI, Player>> closeHandlers = new ArrayList<>();

	@Deprecated
	public GUIImpl(InventoryFactory factory, String title) {
		this.title = title;

		size = factory.size();
		inventory = factory.create(this, title);
		canvas = new InvUICanvasImpl(size, inventory);
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public Inventory getInventory() {
		return inventory;
	}

	@Override
	public GUISize getSize() {
		return size;
	}

	@Override
	public IGUI onClose(BiConsumer<IGUI, Player> closeHandler) {
		closeHandlers.remove(closeHandler);
		closeHandlers.add(closeHandler);
		return this;
	}

	@Override
	public void removeCloseHandler(BiConsumer<IGUI, Player> closeHandler) {
		closeHandlers.remove(closeHandler);
	}

	@Override
	public void clearCloseHandlers() {
		closeHandlers.clear();
	}

	@Override
	public void clearAllHandlers() {
		closeHandlers.clear();
	}

	@Override
	public IGUI redraw() {
		redraw = true;
		return this;
	}

	@Override
	public void draw() {
		redraw = false;
		inventory.clear();
		for (var ui : ui) ui.draw(canvas);
	}

	@Override
	public boolean isRedrawNeeded() {
		if (redraw) return true;
		for (var ui : ui) if (ui.redraw()) return true;
		return false;
	}

	@Override
	public void whenUpdate() {
		for (var ui : ui) ui.update();
		if (isRedrawNeeded()) draw();
	}

	@Override
	public void whenUse(InventoryClickEvent event) {
		if (!(event.getWhoClicked() instanceof Player player)) return;
		var slot = event.getSlot();
		var y = slot / size.width;
		var x = slot - y * size.width;
		var type = event.getClick();
		for (var ui : ui) if (ui.use(player, type, x, y)) return;
	}

	@Override
	public void whenClose(InventoryCloseEvent event) {
		if (!(event.getPlayer() instanceof Player player)) return;
		for (var handler : closeHandlers) handler.accept(this, player);
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

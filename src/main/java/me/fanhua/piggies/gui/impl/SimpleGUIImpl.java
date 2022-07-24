package me.fanhua.piggies.gui.impl;

import me.fanhua.piggies.gui.GUISize;
import me.fanhua.piggies.gui.ISimpleGUI;
import me.fanhua.piggies.gui.InventoryFactory;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Deprecated
public class SimpleGUIImpl implements ISimpleGUI {

	private final GUISize size;
	private final Inventory inventory;

	private String title;

	private final List<Consumer<ISimpleGUI>> updateHandlers = new ArrayList<>();
	private final List<BiConsumer<ISimpleGUI, InventoryClickEvent>> useHandlers = new ArrayList<>();
	private final List<BiConsumer<ISimpleGUI, InventoryCloseEvent>> closeHandlers = new ArrayList<>();

	@Deprecated
	public SimpleGUIImpl(InventoryFactory factory, String title) {
		this.title = title;

		size = factory.size();
		inventory = factory.create(this, title);
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void whenUpdate() {
		for (var handler : updateHandlers) handler.accept(this);
	}

	@Override
	public void whenUse(InventoryClickEvent event) {
		event.setCancelled(true);
		for (var handler : useHandlers) handler.accept(this, event);
	}

	@Override
	public void whenClose(InventoryCloseEvent event) {
		for (var handler : closeHandlers) handler.accept(this, event);
	}

	@Override
	public ISimpleGUI onUpdate(Consumer<ISimpleGUI> updateHandler) {
		updateHandlers.remove(updateHandler);
		updateHandlers.add(updateHandler);
		return this;
	}

	@Override
	public ISimpleGUI onUse(BiConsumer<ISimpleGUI, InventoryClickEvent> useHandler) {
		useHandlers.remove(useHandler);
		useHandlers.add(useHandler);
		return this;
	}

	@Override
	public ISimpleGUI onClose(BiConsumer<ISimpleGUI, InventoryCloseEvent> closeHandler) {
		closeHandlers.remove(closeHandler);
		closeHandlers.add(closeHandler);
		return this;
	}

	@Override
	public void removeUpdateHandler(Consumer<ISimpleGUI> updateHandler) {
		updateHandlers.remove(updateHandler);
	}

	@Override
	public void removeUseHandler(BiConsumer<ISimpleGUI, InventoryClickEvent> useHandler) {
		useHandlers.remove(useHandler);
	}

	@Override
	public void removeCloseHandler(BiConsumer<ISimpleGUI, InventoryCloseEvent> closeHandler) {
		closeHandlers.remove(closeHandler);
	}

	@Override
	public void clearUpdateHandlers() {
		updateHandlers.clear();
	}

	@Override
	public void clearUseHandlers() {
		useHandlers.clear();
	}

	@Override
	public void clearCloseHandlers() {
		closeHandlers.clear();
	}

	@Override
	public void clearAllHandlers() {
		updateHandlers.clear();
		useHandlers.clear();
		closeHandlers.clear();
	}

	@Override
	public GUISize getSize() {
		return size;
	}

	@Override
	public Inventory getInventory() {
		return inventory;
	}

}

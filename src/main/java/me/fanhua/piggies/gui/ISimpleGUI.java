package me.fanhua.piggies.gui;

import me.fanhua.piggies.gui.impl.SimpleGUIImpl;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface ISimpleGUI extends IBaseGUI {

	String getTitle();

	ISimpleGUI onUpdate(Consumer<ISimpleGUI> updateHandler);
	ISimpleGUI onUse(BiConsumer<ISimpleGUI, InventoryClickEvent> useHandler);
	ISimpleGUI onClose(BiConsumer<ISimpleGUI, InventoryCloseEvent> closeHandler);

	void removeUpdateHandler(Consumer<ISimpleGUI> updateHandler);
	void removeUseHandler(BiConsumer<ISimpleGUI, InventoryClickEvent> useHandler);
	void removeCloseHandler(BiConsumer<ISimpleGUI, InventoryCloseEvent> closeHandler);

	void clearUpdateHandlers();
	void clearUseHandlers();
	void clearCloseHandlers();
	void clearAllHandlers();

}

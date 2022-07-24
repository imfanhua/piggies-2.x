package me.fanhua.piggies.gui;

import me.fanhua.piggies.gui.ui.IUIContainer;
import org.bukkit.entity.Player;

import java.util.function.BiConsumer;

public interface IGUI extends IBaseGUI, IUIContainer {

	String getTitle();

	IGUI redraw();
	void draw();
	boolean isRedrawNeeded();

	IGUI onClose(BiConsumer<IGUI, Player> closeHandler);
	void removeCloseHandler(BiConsumer<IGUI, Player> closeHandler);
	void clearCloseHandlers();

	void clearAllHandlers();

}

package me.fanhua.piggies.gui.ui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public interface IUI {

	boolean redraw();
	void update();
	void draw(IUICanvas canvas);
	boolean use(Player clicker, ClickType type, int x, int y);

}

package me.fanhua.piggies.gui.ui;

import java.util.List;

public interface IUIContainer {

	<T extends IUI> T add(T ui);
	<T extends IUI> T remove(T ui);
	List<IUI> getAllUI();
	void clear();

}

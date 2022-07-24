package me.fanhua.piggies.gui;

public final class GUISize {

	public final int width;
	public final int lines;
	public final int size;

	public GUISize(int width, int lines) {
		this.width = width;
		this.lines = lines;
		this.size = width * lines;
	}

	public int slot(int x, int y) {
		return y * width + x;
	}

}

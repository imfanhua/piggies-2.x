package me.fanhua.piggies.gui;

import me.fanhua.piggies.gui.impl.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public final class GUI {

	private GUI() {}

	public static final class PatternBuilder {

		private final String[] lines;
		private final Map<Character, ItemStack> items = new HashMap<>();

		private PatternBuilder(String[] lines) {
			this.lines = lines;
		}

		public PatternBuilder icon(char input, ItemStack output) {
			items.put(input, output);
			return this;
		}

		public GUIImage done() {
			var icons = new ItemStack[lines.length][];
			for (var i = 0; i < lines.length; i++) {
				var line = lines[i].toCharArray();
				var list = new ItemStack[line.length];
				icons[i] = list;
				for (var c = 0; c < line.length; c++)
					list[c] = items.get(line[c]);
			}
			return new GUIImage(icons);
		}

	}

	public static PatternBuilder pattern(String... lines) {
		return new PatternBuilder(lines);
	}

	public static IBaseGUI syncOf(Player target) {
		return new SyncInvImpl(target);
	}

	public static IGUI of(InventoryFactory factory, String title) {
		return new GUIImpl(factory, title);
	}

	public static ISimpleGUI simpleOf(InventoryFactory factory, String title) {
		return new SimpleGUIImpl(factory, title);
	}

	public static final InventoryFactory BOX = TypedInventory.BOX;
	public static final InventoryFactory HOPPER = TypedInventory.HOPPER;

	public static final InventoryFactory MIN = new ChestInventoryFactory(1);
	public static final InventoryFactory NORMAL = new ChestInventoryFactory(3);
	public static final InventoryFactory MAX = new ChestInventoryFactory(6);

	public static InventoryFactory sizeOf(int size) {
		return new ChestInventoryFactory(size / 9 * 9);
	}

	public static InventoryFactory linesOf(int lines) {
		return new ChestInventoryFactory(lines * 9);
	}

}

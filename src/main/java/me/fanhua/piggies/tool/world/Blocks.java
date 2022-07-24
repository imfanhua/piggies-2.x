package me.fanhua.piggies.tool.world;

import me.fanhua.piggies.tool.math.Pos3I;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.stream.Stream;

public final class Blocks {

	private Blocks() {}

	public static void breakNaturally(Block block) {
		playBreakEffect(block);
		block.breakNaturally(new ItemStack(Material.NETHERITE_PICKAXE));
	}

	public static Stream<Block> blocks(Location pos, float distance) {
		return blocks(pos.getWorld(), Pos.area(new Pos3I(pos), distance));
	}

	public static Stream<Block> blocks(World world, Stream<Pos3I> pos) {
		return pos
				.map(v -> v.blockAt(world))
				.filter(v -> !v.isEmpty());
	}

	public static boolean breakIfNotEmpty(Block block) {
		if (block.isEmpty()) return false;
		breakNaturally(block);
		return true;
	}

	public static void playBreakEffect(Block block) {
		block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, block.getType());
	}

	public static void playBreakEffect(Location pos, Material type) {
		pos.getWorld().playEffect(pos, Effect.STEP_SOUND, type);
	}

}

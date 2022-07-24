package me.fanhua.piggies.tool.item;

import me.fanhua.piggies.tool.math.RandomTools;
import me.fanhua.piggies.tool.misc.Lists;
import org.bukkit.Material;

import java.util.*;

public enum TierItems {

	NETHERITE(
			null,

			Material.NETHERITE_HELMET,
			Material.NETHERITE_CHESTPLATE,
			Material.NETHERITE_LEGGINGS,
			Material.NETHERITE_BOOTS,

			Material.NETHERITE_SWORD,
			Material.NETHERITE_AXE,
			Material.NETHERITE_PICKAXE,
			Material.NETHERITE_HOE,
			Material.NETHERITE_SHOVEL
	),

	DIAMOND(
			NETHERITE,

			Material.DIAMOND_HELMET,
			Material.DIAMOND_CHESTPLATE,
			Material.DIAMOND_LEGGINGS,
			Material.DIAMOND_BOOTS,

			Material.DIAMOND_SWORD,
			Material.DIAMOND_AXE,
			Material.DIAMOND_PICKAXE,
			Material.DIAMOND_HOE,
			Material.DIAMOND_SHOVEL
	),

	IRON(
			DIAMOND,

			Material.IRON_HELMET,
			Material.IRON_CHESTPLATE,
			Material.IRON_LEGGINGS,
			Material.IRON_BOOTS,

			Material.IRON_SWORD,
			Material.IRON_AXE,
			Material.IRON_PICKAXE,
			Material.IRON_HOE,
			Material.IRON_SHOVEL
	),

	GOLD(
			IRON,

			Material.GOLDEN_HELMET,
			Material.GOLDEN_CHESTPLATE,
			Material.GOLDEN_LEGGINGS,
			Material.GOLDEN_BOOTS,

			Material.GOLDEN_SWORD,
			Material.GOLDEN_AXE,
			Material.GOLDEN_PICKAXE,
			Material.GOLDEN_HOE,
			Material.GOLDEN_SHOVEL
	),

	STONE(
			GOLD,

			Material.CHAINMAIL_HELMET,
			Material.CHAINMAIL_CHESTPLATE,
			Material.CHAINMAIL_LEGGINGS,
			Material.CHAINMAIL_BOOTS,

			Material.STONE_SWORD,
			Material.STONE_AXE,
			Material.STONE_PICKAXE,
			Material.STONE_HOE,
			Material.STONE_SHOVEL
	),

	CHAINMAIL(STONE),

	WOOD(
			STONE,

			Material.LEATHER_HELMET,
			Material.LEATHER_CHESTPLATE,
			Material.LEATHER_LEGGINGS,
			Material.LEATHER_BOOTS,

			Material.WOODEN_SWORD,
			Material.WOODEN_AXE,
			Material.WOODEN_PICKAXE,
			Material.WOODEN_HOE,
			Material.WOODEN_SHOVEL
	),

	LEATHER(WOOD),
	;

	private static class STATIC {
		private static Map<Material, TierItems> MAPPER = new HashMap<>();
	}

	private TierItems same;
	public final TierItems next;

	public final Material helmet;
	public final Material chestplate;
	public final Material leggings;
	public final Material boots;

	public final Material sword;
	public final Material axe;
	public final Material pickaxe;
	public final Material hoe;
	public final Material shovel;

	public final List<Material> all;
	public final List<Material> armors;
	public final List<Material> weapons;
	public final List<Material> tools;
	public final List<Material> useables;

	TierItems(TierItems next, Material helmet, Material chestplate, Material leggings, Material boots, Material sword, Material axe, Material pickaxe, Material hoe, Material shovel) {
		this.next = next;

		this.helmet = helmet;
		this.chestplate = chestplate;
		this.leggings = leggings;
		this.boots = boots;

		this.sword = sword;
		this.axe = axe;
		this.pickaxe = pickaxe;
		this.hoe = hoe;
		this.shovel = shovel;

		armors = Arrays.stream(new Material[] {
				helmet,
				chestplate,
				leggings,
				boots,
		}).filter(Objects::nonNull).toList();

		weapons = Arrays.stream(new Material[] {
				sword,
				axe,
		}).filter(Objects::nonNull).toList();

		tools = Arrays.stream(new Material[] {
				axe,
				pickaxe,
				hoe,
				shovel,
		}).filter(Objects::nonNull).toList();

		useables = Arrays.stream(new Material[] {
				sword,
				axe,
				pickaxe,
				hoe,
				shovel,
		}).filter(Objects::nonNull).toList();

		all = Lists.flat(armors, useables);

		for (var type : all) STATIC.MAPPER.put(type, this);
	}

	TierItems(TierItems same) {
		this.next = same.next;
		this.same = same;
		same.same = this;

		helmet = same.helmet;
		chestplate = same.chestplate;
		leggings = same.leggings;
		boots = same.boots;

		sword = same.sword;
		axe = same.axe;
		pickaxe = same.pickaxe;
		hoe = same.hoe;
		shovel = same.shovel;

		armors = same.armors;
		weapons = same.weapons;
		tools = same.tools;
		useables = same.useables;

		all = same.all;

		for (var type : armors) STATIC.MAPPER.put(type, this);
	}

	public boolean contains(Material type) {
		return all.contains(type);
	}

	public TierItems same() {
		return same;
	}

	public boolean isSame(TierItems tier) {
		return tier == this;
	}

	public boolean isNext(TierItems tier) {
		return next == this;
	}

	public Material random() {
		return RandomTools.next(all);
	}

	public Material randomArmor() {
		return RandomTools.next(armors);
	}

	public Material randomWeapon() {
		return RandomTools.next(weapons);
	}

	public Material randomTool() {
		return RandomTools.next(tools);
	}

	public Material randomUseable() {
		return RandomTools.next(useables);
	}

}

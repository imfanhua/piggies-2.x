package me.fanhua.piggies.tool.item;

import me.fanhua.piggies.tool.math.RandomTools;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public final class Items {

	private Items() {}

	public static ItemStack item(Material material, String name, String... lore) {
		var item = new ItemStack(material);
		var meta = item.getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(Arrays.stream(lore).toList());
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack item(Material material, int model, String name, String... lore) {
		var item = new ItemStack(material);
		var meta = item.getItemMeta();
		meta.setCustomModelData(model);
		meta.setDisplayName(name);
		meta.setLore(Arrays.stream(lore).toList());
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack allHide(ItemStack item) {
		var meta = item.getItemMeta();
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.values());
		meta.addEnchant(Enchantment.QUICK_CHARGE, 1, true);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack unbreakableHide(ItemStack item) {
		var meta = item.getItemMeta();
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.values());
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack enchant(ItemStack item) {
		var meta = item.getItemMeta();
		meta.addEnchant(Enchantment.QUICK_CHARGE, 1, true);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack unbreakable(ItemStack item) {
		var meta = item.getItemMeta();
		meta.setUnbreakable(true);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack hide(ItemStack item) {
		var meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.values());
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack damage(ItemStack item, int damage) {
		var amount = item.getAmount();
		if (item == null || amount < 1 || !(item.getItemMeta() instanceof Damageable meta)) return item;
		var max = item.getType().getMaxDurability();
		damage += meta.getDamage();
		while (amount > 0 || damage > 0)
			if (damage >= max) {
				amount--;
				damage -= max;
			} else break;
		item.setAmount(amount);
		if (amount > 0) {
			meta.setDamage(damage);
			item.setItemMeta((ItemMeta) meta);
		}
		return item;
	}

	public static ItemStack addEnchant(ItemStack item, Enchantment enchant, int level) {
		var meta = item.getItemMeta();
		meta.addEnchant(enchant, meta.getEnchantLevel(enchant) + level, true);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack setEnchant(ItemStack item, Enchantment enchant, int level) {
		var meta = item.getItemMeta();
		meta.addEnchant(enchant, level, true);
		item.setItemMeta(meta);
		return item;
	}

	public static List<Enchantment> enchants(ItemStack item, boolean max) {
		var stream = Arrays.stream(Enchantment.values()).filter(x -> x.canEnchantItem(item));
		if (max) stream = stream.filter(x -> item.getEnchantmentLevel(x) < x.getMaxLevel());
		return stream.toList();
	}

	public static ItemStack randomEnchant(ItemStack item, boolean max) {
		var list = enchants(item, max);
		if (list.isEmpty()) return item;
		return addEnchant(item, RandomTools.next(list), 1);
	}

	public static ItemStack addRandomEnchant(ItemStack item, int times, boolean max) {
		var count = RandomTools.RANDOM.nextInt(times) + 1;
		for (var i = 0; i < count; i++)
			item = Items.randomEnchant(item, max);
		return item;
	}

	public static ItemStack addRandomEnchant(ItemStack item, double rate, int times, boolean max) {
		return RandomTools.rate(rate) ? addRandomEnchant(item, times, max) : item;
	}

}

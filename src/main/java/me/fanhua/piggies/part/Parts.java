package me.fanhua.piggies.part;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public final class Parts {

	private static final class PartsBank {
		public final IPart[] parts = new IPart[SIZE];
	}

	private Parts() {}

	private static final List<IPartFactory<?>> PARTS = new ArrayList<>();
	private static final List<NamespacedKey> KEYS = new ArrayList<>();
	private static final Map<UUID, PartsBank> BANKS = new HashMap<>();

	private static int SIZE;

	public static <P extends IPart> Part<P> register(NamespacedKey key, IPartFactory<P> factory) {
		var index = PARTS.size();
		PARTS.add(factory);
		KEYS.add(key);
		SIZE = index + 1;
		return new Part<>(index);
	}

	public static <P extends IPart> P get(Player player, Part<P> part) {
		var bank = BANKS.get(player.getUniqueId());
		if (bank == null) {
			bank = new PartsBank();
			BANKS.put(player.getUniqueId(), bank);
		}
		var value = bank.parts[part.index()];
		if (value == null) {
			var i = part.index();
			value = PARTS.get(i).create();
			value.loadFrom(player, player.getPersistentDataContainer().get(KEYS.get(i), PersistentDataType.TAG_CONTAINER));
			bank.parts[i] = value;
		}
		//noinspection unchecked
		return (P) value;
	}

	@Deprecated
	public static void remove(Player player) {
		var bank = BANKS.get(player.getUniqueId());
		if (bank == null) return;
		var parts = bank.parts;
		var container = player.getPersistentDataContainer();
		for (var i = 0; i < SIZE; i++) {
			var part = parts[i];
			if (part == null) continue;
			var data = container.get(KEYS.get(i), PersistentDataType.TAG_CONTAINER);
			if (data == null) data = container.getAdapterContext().newPersistentDataContainer();
			part.saveTo(player, data);
			container.set(KEYS.get(i), PersistentDataType.TAG_CONTAINER, data);
		}
		BANKS.remove(player.getUniqueId());
	}

}

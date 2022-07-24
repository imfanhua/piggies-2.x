package me.fanhua.piggies.part;

import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;

public interface ITempPart extends IPart {

	default void loadFrom(Player player, PersistentDataContainer io) {}
	default void saveTo(Player player, PersistentDataContainer io) {}

}

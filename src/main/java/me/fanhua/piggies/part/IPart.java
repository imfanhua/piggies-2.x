package me.fanhua.piggies.part;

import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;

public interface IPart {

	void loadFrom(Player player, PersistentDataContainer io);
	void saveTo(Player player, PersistentDataContainer io);

}

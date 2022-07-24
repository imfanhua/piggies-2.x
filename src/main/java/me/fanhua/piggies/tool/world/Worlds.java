package me.fanhua.piggies.tool.world;

import me.fanhua.piggies.tool.io.FileTools;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

import java.io.File;
import java.nio.file.StandardCopyOption;

public final class Worlds {

	public record WorldInfo(WorldCreator creator, boolean exists) {
		public WorldInfo(WorldCreator creator) {
			this(creator, Worlds.exists(creator));
		}
	}

	private static final File PATH = new File("plugins", "templates");
	public static final String VOID = "{\"structures\": {\"structures\": {}}, \"layers\": [{\"block\": \"air\", \"height\": 1}], \"biome\":\"the_void\"}";

	static {
		//noinspection ResultOfMethodCallIgnored
		PATH.mkdirs();
	}

	private Worlds() {}

	public static long tick() {
		return Bukkit.getWorlds().get(0).getTime();
	}

	public static WorldCreator newVoidCreator(String name) {
		return new WorldCreator(name)
				.type(WorldType.FLAT)
				.generatorSettings(VOID)
				.generateStructures(false);
	}

	public static WorldInfo newVoid(String name) {
		return new WorldInfo(newVoidCreator(name));
	}

	public static WorldInfo newVoidFrom(String name, String template) {
		var file = new File(name);
		var exists = file.exists();
		if (!exists) {
			if (!FileTools.copyDirectory(new File(PATH, template), file, StandardCopyOption.REPLACE_EXISTING))
				return null;
		}
		return new WorldInfo(newVoidCreator(name), exists);
	}

	public static boolean exists(WorldCreator creator) {
		return new File(creator.name()).exists();
	}

}

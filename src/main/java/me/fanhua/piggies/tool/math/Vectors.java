package me.fanhua.piggies.tool.math;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public final class Vectors {

	private Vectors() {}

	public static Location forward(Location location, float distance) {
		return location.add(location.getDirection().multiply(distance));
	}

	public static Location forward(Location location, VectorFace face, float distance) {
		return forward(location, face, 90.0F, distance);
	}

	public static Location forward(Location location, VectorFace face, float amount, float distance) {
		return location.add(forward(new Direction(location), face, amount, distance));
	}

	public static Vector forward(Direction direction, VectorFace face, float distance) {
		return forward(direction, face, 90.0F, distance);
	}

	public static Vector forward(Direction direction, VectorFace face, float amount, float distance) {
		return forward(face.rotate(direction.clone(), amount), distance);
	}

	public static Vector forward(Direction direction, float distance) {
		return direction.toVector().multiply(distance);
	}

}

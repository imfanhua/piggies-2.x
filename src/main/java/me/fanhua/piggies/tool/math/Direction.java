package me.fanhua.piggies.tool.math;

import org.bukkit.Location;
import org.bukkit.util.NumberConversions;
import org.bukkit.util.Vector;

import java.util.Objects;

public class Direction {

	public float pitch;
	public float yaw;

	public Direction(float pitch, float yaw) {
		this.pitch = pitch;
		this.yaw = yaw;
	}

	public Direction(double x, double y, double z) {
		this.from(x, y, z);
	}

	public Direction(Vector direction) {
		this.from(direction);
	}

	public Direction(Location direction) {
		this.from(direction);
	}

	public Direction pitch(float pitch) {
		this.pitch = pitch;
		return this;
	}

	public Direction yaw(float yaw) {
		this.yaw = yaw;
		return this;
	}

	public Direction add(float pitch, float yaw) {
		this.pitch += pitch;
		this.yaw += yaw;
		return this;
	}

	public Direction mul(float pitch, float yaw) {
		this.pitch *= pitch;
		this.yaw *= yaw;
		return this;
	}

	public Direction from(float pitch, float yaw) {
		this.pitch = pitch;
		this.yaw = yaw;
		return this;
	}

	public Direction from(double x, double y, double z) {
		final var DOUBLE_PI = 2 * Math.PI;

		if (x == 0 && z == 0) {
			this.pitch = y > 0 ? -90 : 90;
			return this;
		}

		var theta = Math.atan2(-x, z);
		this.yaw = (float) Math.toDegrees((theta + DOUBLE_PI) % DOUBLE_PI);

		var x2 = NumberConversions.square(x);
		var z2 = NumberConversions.square(z);
		var xz = Math.sqrt(x2 + z2);
		this.pitch = (float) Math.toDegrees(Math.atan(-y / xz));
		return this;
	}

	public Direction from(Vector direction) {
		return this.from(direction.getX(), direction.getY(), direction.getZ());
	}

	public Direction from(Location direction) {
		return this.from(direction.getPitch(), direction.getYaw());
	}

	public Vector toVector() {
		double xz = Math.cos(Math.toRadians(pitch));
		return new Vector(
				-xz * Math.sin(Math.toRadians(yaw)),
				-Math.sin(Math.toRadians(pitch)),
				xz * Math.cos(Math.toRadians(yaw))
		);
	}

	public Direction clone() {
		return new Direction(pitch, yaw);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Direction direction = (Direction) o;
		return Float.compare(direction.pitch, pitch) == 0 && Float.compare(direction.yaw, yaw) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(pitch, yaw);
	}

}

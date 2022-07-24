package me.fanhua.piggies.tool.math;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.util.NumberConversions;
import org.bukkit.util.Vector;

import java.util.Objects;

public class Pos3I {

	public int x;
	public int y;
	public int z;

	public Pos3I(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Pos3I(Vector pos) {
		this(pos.getBlockX(), pos.getBlockY(), pos.getBlockZ());
	}

	public Pos3I(Location pos) {
		this(pos.getBlockX(), pos.getBlockY(), pos.getBlockZ());
	}

	public Pos3I add(int x, int y, int z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}

	public Pos3I sub(int x, int y, int z) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
		return this;
	}

	public double lengthBy(Pos3I pos) {
		return Math.sqrt(NumberConversions.square(x - pos.x) + NumberConversions.square(y - pos.y) + NumberConversions.square(z - pos.z));
	}

	public Block blockAt(World world) {
		return world.getBlockAt(x, y, z);
	}

	public Pos3I clone() {
		return new Pos3I(x, y, z);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Pos3I pos3I = (Pos3I) o;
		return x == pos3I.x && y == pos3I.y && z == pos3I.z;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}

}

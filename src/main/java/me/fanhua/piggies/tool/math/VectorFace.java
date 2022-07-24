package me.fanhua.piggies.tool.math;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public enum VectorFace {

	UP {

		@Override
		protected float rotatePitch(float pitch, float amount) {
			return pitch - amount;
		}

		@Override
		protected float rotateYaw(float yaw, float amount) {
			return 0;
		}

	},
	DOWN {

		@Override
		protected float rotatePitch(float pitch, float amount) {
			return pitch + amount;
		}

		@Override
		protected float rotateYaw(float yaw, float amount) {
			return 0;
		}

	},
	LEFT {

		@Override
		protected float rotatePitch(float pitch, float amount) {
			return 0;
		}

		@Override
		protected float rotateYaw(float yaw, float amount) {
			return yaw - amount;
		}

	},
	RIGHT {

		@Override
		protected float rotatePitch(float pitch, float amount) {
			return 0;
		}

		@Override
		protected float rotateYaw(float yaw, float amount) {
			return yaw + amount;
		}

	};

	VectorFace() {}

	public Location rotate(Location location, float amount) {
		location.setPitch(rotatePitch(location.getPitch(), amount));
		location.setYaw(rotateYaw(location.getYaw(), amount));
		return location;
	}

	public Vector rotate(Vector direction, float amount) {
		return rotate(new Direction(direction), amount).toVector();
	}

	public Direction rotate(Direction direction, float amount) {
		return direction.pitch(rotatePitch(direction.pitch, amount)).yaw(rotateYaw(direction.yaw, amount));
	}

	protected abstract float rotateYaw(float yaw, float amount);
	protected abstract float rotatePitch(float pitch, float amount);

}

package me.fanhua.piggies.tool.world;

import me.fanhua.piggies.tool.math.Pos3I;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class Pos {

	private Pos() {}

	public static Stream<Pos3I> area(Pos3I pos, float distance) {
		int width = (int) Math.ceil(distance);
		int width2 = width * 2;
		int depth2 = width2 * width2;
		@SuppressWarnings("SuspiciousNameCombination")
		Pos3I point = pos.clone().sub(width, width, width);

		return IntStream.range(0, width2 * depth2)
				.mapToObj(v -> {
					int z = v / depth2;
					int part = v % depth2;
					int y = part / width2;
					return point.clone().add(part % width2, y, z);
				})
				.filter(v -> v.lengthBy(pos) <= distance);
	}

}

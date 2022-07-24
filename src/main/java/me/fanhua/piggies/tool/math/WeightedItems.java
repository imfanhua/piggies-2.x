package me.fanhua.piggies.tool.math;

import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

public class WeightedItems<T> {

	private final NavigableMap<Double, T> map = new TreeMap<Double, T>();
	private double total = 0;

	public WeightedItems<T> add(double weight, T item) {
		if (weight <= 0) return this;
		total += weight;
		map.put(total, item);
		return this;
	}

	public T get(double value) {
		return map.higherEntry(value * total).getValue();
	}

	public T next(Random random) {
		return get(random.nextDouble());
	}

	public T next() {
		return get(RandomTools.RANDOM.nextDouble());
	}

}

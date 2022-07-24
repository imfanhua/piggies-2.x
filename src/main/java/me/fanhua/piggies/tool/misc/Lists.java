package me.fanhua.piggies.tool.misc;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public final class Lists {

	private Lists() {}

	public static <T> List<T> as(T[] elements) {
		return Arrays.stream(elements).toList();
	}

	@SafeVarargs
	public static <T> List<T> of(T... elements) {
		return Arrays.stream(elements).toList();
	}

	@SafeVarargs
	public static <T> List<T> flat(List<T>... lists) {
		return Arrays.stream(lists).flatMap(Collection::stream).toList();
	}

}

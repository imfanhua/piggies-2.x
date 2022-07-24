package me.fanhua.piggies.tool.data;

import java.util.UUID;
import java.util.regex.Pattern;

public final class UUIDTools {

	private static final Pattern FROM = Pattern.compile("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})");
	private static final Pattern TO = Pattern.compile("-");

	private UUIDTools() {}

	public static UUID from(String id) {
		return UUID.fromString(FROM.matcher(id).replaceAll("$1-$2-$3-$4-$5"));
	}

	public static String to(UUID id) {
		return TO.matcher(id.toString()).replaceAll("");
	}

	public static boolean isPlayerId(String name) {
		return name.length() == 32;
	}

	public static UUID getPlayerId(String name) {
		return name.length() != 32 ? null : from(name);
	}

}

package me.fanhua.piggies.tool.data;

import com.google.common.base.Charsets;
import com.google.gson.*;

import java.io.InputStream;
import java.io.InputStreamReader;

public final class Json {

	public static final JsonParser PARSER = new JsonParser();
	public static final Gson GSON = new GsonBuilder()
			.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
			.setPrettyPrinting()
			.create();

	private Json() {}

	public static JsonElement as(Object obj) {
		return GSON.toJsonTree(obj);
	}
	public static JsonElement as(String value) {
		return PARSER.parse(value);
	}
	public static JsonElement as(InputStream input) {
		return PARSER.parse(new InputStreamReader(input, Charsets.UTF_8));
	}

	public static String stringify(Object obj) {
		return GSON.toJson(obj);
	}

	public static <T> T parse(Class<T> clazz, String json) {
		return GSON.fromJson(json, clazz);
	}

	public static <T> T parse(Class<T> clazz, InputStream input) {
		return GSON.fromJson(new InputStreamReader(input, Charsets.UTF_8), clazz);
	}

	public static <T> T parse(Class<T> clazz, JsonElement json) {
		return GSON.fromJson(json, clazz);
	}

}

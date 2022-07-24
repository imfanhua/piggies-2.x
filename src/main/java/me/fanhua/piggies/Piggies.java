package me.fanhua.piggies;

import me.fanhua.piggies.handler.GUIHandlers;
import me.fanhua.piggies.handler.PlayerHandlers;
import me.fanhua.piggies.handler.TickHandlers;

public final class Piggies extends PiggyPlugin<Piggies> {

	private static Piggies INSTANCE;
	public static Piggies instance() {
		return INSTANCE;
	}

	@Override
	protected void instance(Piggies instance, boolean set) {
		if (set || INSTANCE == instance) INSTANCE = instance;
	}

	@Override
	protected void init() {
		TickHandlers.register();
		events(PlayerHandlers.HANDLERS);
		events(GUIHandlers.HANDLERS);
	}

}

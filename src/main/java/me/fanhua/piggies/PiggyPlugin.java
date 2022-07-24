package me.fanhua.piggies;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.function.Consumer;
import java.util.logging.Logger;

public abstract class PiggyPlugin<T extends PiggyPlugin<T>> extends JavaPlugin implements Listener {

	public NamespacedKey key(String key) {
		return new NamespacedKey(this, key);
	}

	public Logger logger() {
		return logger;
	}

	protected Logger logger;

	@Override
	public void onEnable() {
		//noinspection unchecked
		instance((T) this, true);
		logger = getLogger();
		events(this);
		this.init();
	}

	@Override
	public void onDisable() {
		//noinspection unchecked
		instance((T) this, false);
	}

	protected void init() {}

	protected abstract void instance(T instance, boolean set);

	public T command(String command, CommandExecutor executor) {
		getCommand(command).setExecutor(executor);
		//noinspection unchecked
		return (T) this;
	}

	public T events(Listener listener) {
		Bukkit.getPluginManager().registerEvents(listener, this);
		//noinspection unchecked
		return (T) this;
	}

	public T events(Object listener) {
		if (listener instanceof Listener) Bukkit.getPluginManager().registerEvents((Listener) listener, this);
		//noinspection unchecked
		return (T) this;
	}

	public BukkitTask tick(Runnable task, long delay) {
		return Bukkit.getScheduler().runTaskLater(this, task, delay);
	}

	public T doTick(Runnable task, long delay) {
		tick(task, delay);
		//noinspection unchecked
		return (T) this;
	}

	public BukkitTask tick(Runnable task) {
		return tick(task, 0);
	}

	public T doTick(Runnable task) {
		return doTick(task, 0);
	}

	public BukkitTask task(Runnable task, long delay, long period) {
		return Bukkit.getScheduler().runTaskTimer(this, task, delay, period);
	}

	public T doTask(Runnable task, long delay, long period) {
		task(task, delay, period);
		//noinspection unchecked
		return (T) this;
	}

	public T task(Consumer<BukkitTask> task, long delay, long period) {
		Bukkit.getScheduler().runTaskTimer(this, task, delay, period);
		//noinspection unchecked
		return (T) this;
	}

	public BukkitTask task(Runnable task, long period) {
		return task(task, period, period);
	}

	public T doTask(Runnable task, long period) {
		return doTask(task, period, period);
	}

	public T task(Consumer<BukkitTask> task, long period) {
		return task(task, period, period);
	}

	public BukkitTask task(Runnable task) {
		return task(task, 0, 0);
	}

	public T doTask(Runnable task) {
		return doTask(task, 0, 0);
	}

	public T task(Consumer<BukkitTask> task) {
		return task(task, 0, 0);
	}

}

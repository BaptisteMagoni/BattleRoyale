package net.zastax.Arena.listener;

import org.bukkit.plugin.PluginManager;

import net.zastax.Main;

public class EventManager {

	private Main main;
	
	public EventManager(Main main) {
		this.main = main;
	}

	public void registerEvent() {
		PluginManager pm = main.getServer().getPluginManager();
		pm.registerEvents(new InteractEvent(main), main);
		pm.registerEvents(new DamageEvent(main), main);
		pm.registerEvents(new JoinEvent(main), main);
		pm.registerEvents(new QuitEvent(main), main);
		pm.registerEvents(new BreakEvent(main), main);
		pm.registerEvents(new ChatEvent(main), main);
		pm.registerEvents(new PlayerDeath(main), main);
		pm.registerEvents(new InventoryClick(main), main);
		pm.registerEvents(new HitEvent(main), main);
	}

}

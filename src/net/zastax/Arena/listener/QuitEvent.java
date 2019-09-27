package net.zastax.Arena.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import net.zastax.Main;

public class QuitEvent implements Listener {

	private Main main;
	
	public QuitEvent(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		Player player = e.getPlayer();
		player.getInventory().clear();
		main.getListPlayer().remove(player);
	}

}

package net.zastax.Arena.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.zastax.Main;
import net.zastax.Arena.player.CPlayer;
import net.zastax.Arena.player.PlayerState;

public class JoinEvent implements Listener {

	private Main main;
	
	public JoinEvent(Main main) {
		this.main = main;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player player = e.getPlayer();
		CPlayer cp = new CPlayer(main, player);
		main.getListPlayer().add(cp);
		if(!main.getBankManager().playerExist(player)){
			main.getBankConfig().set("Player." + cp.getName() + ".money", 200.0);
			main.savePlayerConfig();
			cp.getBank().addMoney(200.0);
		}else
			cp.getBank().addMoney(main.getBankConfig().getDouble("Player." + cp.getName() + ".money"));
		cp.setPlayerState(PlayerState.NOTINGAME);
		try {
			player.teleport(main.getSpawnServer());
		}catch(Exception err) {
			System.out.println("Le plugin n'a pas trouvé de spawn !");
		}
		player.getInventory().addItem(main.getItem(Material.COMPASS, "§cMenu des arènes"));
		player.updateInventory();
	}
	
}

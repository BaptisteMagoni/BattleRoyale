package net.zastax.Arena.listener;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import net.zastax.Damager;
import net.zastax.Main;
import net.zastax.Arena.Arena;
import net.zastax.Arena.player.CPlayer;
import net.zastax.Arena.player.PlayerArena;
import net.zastax.Arena.player.PlayerState;

public class PlayerDeath implements Listener {

	private Main main;
	public PlayerDeath(Main main) {
		this.main = main;
	}
	@EventHandler
	public void onKill(PlayerDeathEvent e){
		Player killer = e.getEntity().getKiller();
		Player killed = (Player) e.getEntity();
		if(killer instanceof Player){
			killed.teleport(killer.getLocation());
			for(PlayerArena parena : main.getArenaManager().getArenaByPlayer(killer).getPlayerArena()){
				if(parena != null){
					if(killer.equals(parena.getPlayer())){
						parena.newKill();
						main.getArenaManager().getArenaByPlayer(killed).getListPlayer().remove(killed);
						try {
						for(int i=0;i<this.getHeadInGame(killer).size();i++)
							killed.getInventory().addItem(this.getHeadInGame(killer).get(i));
						}catch(Exception err) {
							System.out.println("Plus de jour dans l'arène !");
						}
						for(Player pls : main.getArenaManager().getArenaByPlayer(killer).getListPlayer())
							pls.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "BattleRoyale" + ChatColor.GRAY + "]" + ChatColor.GOLD + " Le joueur " + killer.getName() + " viens de tué " + killed.getName());
					}
				}
			}
		}
		if(main.getArenaManager().playerIsInArena(e.getEntity().getPlayer())){
			for(Damager d : main.getListDamager()){
				if(e.getEntity().getLastDamageCause().getCause().equals(d.getDamageCause())){
					Arena arena = main.getArenaManager().getArenaByPlayer(e.getEntity().getPlayer());
					for(Player pls : arena.getListPlayer()){
						pls.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "BattleRoyale" + ChatColor.GRAY + "]" + ChatColor.GOLD + String.format(d.getDeathMessage(), e.getEntity().getName()));
						e.setDeathMessage(null);
						e.getEntity().getPlayer().teleport(arena.getListPlayer().get(0).getLocation());
					}
				}
			}
		}
		killed.setGameMode(GameMode.SPECTATOR);
		for(CPlayer cp : main.getListPlayer())
			cp.setPlayerState(PlayerState.SPECTATOR);
		killed.getInventory().setItem(3, main.getItem(Material.ACACIA_DOOR, "&4Close Menu"));
		killed.updateInventory();
	}
	
	private ArrayList<ItemStack> getHeadInGame(Player killer){
		ArrayList<ItemStack> items = new ArrayList<>();
		for(Player pls : main.getArenaManager().getArenaByPlayer(killer).getListPlayer())
			items.add(main.getItem(Material.PLAYER_HEAD, pls.getName()));
		return items;
	}
	
}

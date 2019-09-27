package net.zastax.Arena.modeGameArena;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.zastax.Main;
import net.zastax.Arena.GameState;

public class Wainting {
	
	private int timer = 15;
	@SuppressWarnings("unused")
	private Main main;
	@SuppressWarnings("unused")
	private String m_arenaName;
	
	public Wainting( Main main, String arenaName){
		this.main = main;
		this.m_arenaName = arenaName;
	}
	
	public GameState start(ArrayList<Player> listPlayers, int minPlayer, int maxPlayer, int sch, ArrayList<Location> playerSpawn){
		if(listPlayers.size() >= minPlayer){
			if(timer == 0){
				for(Player pls : listPlayers){
					pls.getInventory().clear();
					pls.getInventory().addItem(new ItemStack(Material.ELYTRA, 1));
					pls.updateInventory();
				}
				return GameState.PREGAME;
			}
			else if(timer == 15 || timer == 10 || timer <= 5)
				for(Player pls : listPlayers)
					pls.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "BattleRoyale" + ChatColor.GRAY + "]" + ChatColor.GOLD + " Le jeux commance dans " + ChatColor.GRAY + timer + " seconde(s)" + ChatColor.GOLD + ". Préparez-vous !");
			timer--;
		}
		return GameState.WAITING;
	}
}

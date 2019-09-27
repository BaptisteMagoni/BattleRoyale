package net.zastax.Arena.modeGameArena;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import net.zastax.Arena.GameState;

public class Pregame {

	private int timer = 20;

	public GameState start(ArrayList<Player> listPlayers, ArrayList<Location> playerSpawn){
		if(timer == 0){
			for(int i=0;i<listPlayers.size();i++)
				listPlayers.get(i).teleport(playerSpawn.get(i));
			return GameState.GAME;
		}
		if(timer == 20 || timer == 15 || timer == 10 || timer <= 5){
			for(Player pls : listPlayers)
				pls.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "BattleRoyale" + ChatColor.GRAY + "]" + ChatColor.GOLD + " Dans " + ChatColor.GRAY + timer + " seconde(s) " + ChatColor.GOLD + "vous allez être parachuté!");
		}
		timer--;
		return GameState.PREGAME;
	}

}

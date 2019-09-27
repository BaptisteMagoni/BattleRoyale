package net.zastax.Arena.modeGameArena;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.zastax.Main;
import net.zastax.Arena.GameState;
import net.zastax.Arena.player.CPlayer;

public class Finish {

	private Main main;
	
	public Finish(Main main) {
		this.main = main;
	}

	public GameState start(ArrayList<Player> listPlayers){
		
		for(Player winner : listPlayers){
			for(CPlayer cp : main.getListPlayer()){
				if(cp.getName().equals(winner.getName())){
					cp.getBank().addMoney(200.0);
					winner.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "BattleRoyale" + ChatColor.GRAY + "]" + ChatColor.GOLD + " Bravo vous avez gagné. Récompense : 200 coins");
					winner.teleport(main.getSpawnServer());
					main.getArenaManager().quit(winner);
					
				}
			}
		}	
		
		return GameState.FINISH;
	}
	
}

package net.zastax;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import net.zastax.Arena.Arena;
import net.zastax.Arena.ArenaType;
import net.zastax.Arena.Team.GroupTeam;
import net.zastax.Arena.area.BorderState;
import net.zastax.Arena.modeGameArena.Game;
import net.zastax.Arena.player.CPlayer;
import net.zastax.Arena.player.PlayerState;
import net.zastax.Arena.player.bank.Bank;

public class ScoreboardSign {

	private Main main;
	private Player m_player;
	private Arena arena;
	private int slot;
	
	@SuppressWarnings("deprecation")
	public ScoreboardSign(Main main, Player player, Bank bank){
		this.main = main;
		this.m_player = player;
		slot = 0;
				try{
					arena = main.getArenaManager().getArenaByPlayer(player);
				}catch(Exception e){
					
				}
				
				ScoreboardManager m = Bukkit.getScoreboardManager();
				Scoreboard s = m.getNewScoreboard();
				Objective o = s.registerNewObjective("Gold", "");
				o.setDisplaySlot(DisplaySlot.SIDEBAR);
				o.setDisplayName(ChatColor.DARK_AQUA + "[Battle Royale]");
				
				for(CPlayer cp : main.getListPlayer())
					if(cp.getName().equals(player.getName()))
						if(cp.getPlayerState().equals(PlayerState.NOTINGAME))
							inNotGame(o);
						else if(cp.getPlayerState().equals(PlayerState.INGAME))
							inGame(o, cp);
							
				player.setScoreboard(s);	
	}
		
	
	private String getTime(){
		Arena arena = main.getArenaManager().getArenaByPlayer(m_player);
		Game game = arena.getGame();
		if(game != null){
			double timer = arena.getGame().getTimer();
			double time = timer/60;
			String[] splitData = String.valueOf(time).split("\\.");
			String minute = splitData[0];
			String seconde = splitData[1];
			seconde = "0." + seconde;
			int sd = (int) Math.round(Double.valueOf(seconde) * 60);
			String message = "";
			if(arena.getGame().getBorderState().equals(BorderState.WaveMove))
				message = "Border " + ChatColor.GREEN + ChatColor.ITALIC + "V " + ChatColor.WHITE + ": ";
			else
				message = "Border " + ChatColor.DARK_RED + "X " + ChatColor.WHITE + ": ";
			if(minute == "0"){
				if(sd < 10)
					return message + sd;
				else
					return message + "0" + sd;
			}else{
				if(sd < 10)
					return message + minute + ":0" + sd;
				else
					return message + minute + ":" + sd;
			}
		}
		return "----------";
	}
	
	private void inGame(Objective o, CPlayer cp){
		try{
			slot = 0;
			getScore(o, " ");
			getScore(o, "Arène " + arena.getName());
			getScore(o, ChatColor.GOLD + "" + ChatColor.BOLD + "----" + ChatColor.GRAY + "[Info Arène]" + ChatColor.GOLD + "----");
			getScore(o, "Joueur : " + arena.getListPlayer().size());
			getScore(o, getTime());
			
			getScore(o, ChatColor.GOLD + "" + ChatColor.BOLD + "----" + ChatColor.GRAY + "[Info Joueur]" + ChatColor.GOLD + "----");
			getScore(o, "Pseudo : " + m_player.getName());
			getScore(o, "Kill : " + main.getArenaManager().getArenaByPlayer(m_player).getPlayerArenaByPlayer(m_player).getKill());
			if(arena.getArenaType().equals(ArenaType.TEAM))
				for(GroupTeam gt : GroupTeam.values())
					if(gt.getTeam().getPlayers().contains(cp.getPlayer()))
						getScore(o, "Equipe : " + gt.getDisplayName());
		}catch(Exception err){
			
		}
	}
	
	private void inNotGame(Objective o){
		slot = 0;
		getScore(o, "");
		getScore(o, "§4Money : " + 200.0);
	}
	
	private Score getScore(Objective o, String message){
		Score score = o.getScore(message);
		score.setScore(slot);
		slot--;
		return score;
	}
	
}

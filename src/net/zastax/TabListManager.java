package net.zastax;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import net.zastax.Arena.Arena;
import net.zastax.Arena.ArenaType;
import net.zastax.Arena.Team.GroupTeam;

public class TabListManager {

	private Main main;
	private Scoreboard m_scoreboardServer;
	private Team m_teamServer;
	
	public TabListManager(Main main){
		this.main = main;
		m_scoreboardServer = Bukkit.getScoreboardManager().getMainScoreboard();
	}
	
	@SuppressWarnings("deprecation")
	public void loadTabList(){
		for(Player pls : Bukkit.getOnlinePlayers()){
			if(main.getArenaManager().playerIsInArena(pls)){
				Arena arena = main.getArenaManager().getArenaByPlayer(pls);
				for(Player targetPlayer : arena.getListPlayer()){
					Scoreboard board = arena.getTabList();
					if(arena.getArenaType().equals(ArenaType.SOLO)){
						if(board.getTeam(arena.getName()) == null){
							arena.setTeamTabList(board.registerNewTeam(arena.getName()));
						}else{
							arena.setTeamTabList(board.getTeam(arena.getName()));
							arena.getTeamTabList().addPlayer(targetPlayer);
						}
						targetPlayer.setPlayerListName(ChatColor.GOLD + "[Joueur] " + targetPlayer.getName());
					}else if(arena.getArenaType().equals(ArenaType.TEAM)){
						for(GroupTeam gt : GroupTeam.values()){
							if(board.getTeam(gt.getName()) == null){
								arena.setTeamTabList(board.registerNewTeam(gt.getName()));
							}else{
								arena.setTeamTabList(board.getTeam(gt.getName()));
								arena.getTeamTabList().addPlayer(targetPlayer);
							}
							targetPlayer.setPlayerListName(ChatColor.GRAY + "" + ChatColor.ITALIC + "[" + arena.getName() + "] " + targetPlayer.getName());
						}	
					}
				}
			}else{
				if(this.m_scoreboardServer.getTeam("Lobby") == null)
					m_teamServer = this.m_scoreboardServer.registerNewTeam("Lobby");
				else{
					m_teamServer = this.m_scoreboardServer.getTeam("Lobby");
					m_teamServer.addPlayer(pls);
				}
				pls.setPlayerListName(ChatColor.GRAY + "[Lobby] " + pls.getName());
			}
		}
	}
}

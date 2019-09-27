package net.zastax.Arena.Team;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.zastax.Arena.Arena;

public class ArenaTeam {

	private String m_name;
	private ArrayList<Player> m_players;
	private Arena m_arena;
	
	public ArenaTeam(String name){
		this.m_name = name;
		this.m_arena = null;
		this.m_players = new ArrayList<Player>();
	}
	
	public String getName(){
		return m_name;
	}
	
	public ArrayList<Player> getPlayers(){
		return m_players;
	}
	
	public void setArena(Arena arena){
		this.m_arena = arena;
	}
	
	public void addPlayer(Player player){
		if(!m_players.contains(player)){
			if(m_players.size() < m_arena.getMaxPlayerTeam()){
				m_players.add(player);
				player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "BattleRoyale" + ChatColor.GRAY + "]" + ChatColor.GREEN + " Tu à rejoins la team " + m_name + " !");
			}else
				player.sendMessage(ChatColor.RED + "[BattleRoyale] L'équipe " + m_name + " est déjà au complet !");
		}else
			player.sendMessage(ChatColor.RED + "[BattleRoyale] Vous êtes déjà dans cette équipe !");
	}
	
	public void remove(Player player){
		if(m_players.contains(player))
			m_players.remove(player);
	}
	
}

package net.zastax.Arena.player;

import org.bukkit.entity.Player;

public class PlayerArena {

	private Player m_player;
	private int m_kill;
	
	public PlayerArena(Player player){
		this.m_player = player;
	}
	
	public Player getPlayer(){
		return m_player;
	}
	
	public void newKill(){
		m_kill++;
	}
	
	public int getKill(){
		return m_kill;
	}
	
}

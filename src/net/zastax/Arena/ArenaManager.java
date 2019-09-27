package net.zastax.Arena;

import java.util.ArrayList;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.zastax.Main;
import net.zastax.Arena.player.CPlayer;
import net.zastax.Arena.player.PlayerState;

public class ArenaManager {

	private ArrayList<Arena> m_listArena = new ArrayList<Arena>();
	private Main main;
	
	public ArenaManager(Main main){
		this.main = main;
	}
	
	public void join(String arena_name, Player player){
		for(Arena arena : m_listArena){
			if(arena.isReady()){
				if(arena.getName().equals(arena_name)){
					if(!arena.getListPlayer().contains(player)){
						if(arena.getListPlayer().size() <= arena.getMaxPlayer()){
							arena.add(player);
						}else
							player.sendMessage(ChatColor.RED + "[BattleRoyale] Cette ar�ne est d�j� compl�te d�sol� !");
					}else
						player.sendMessage(ChatColor.RED + "[BattleRoyale] Vous �tes d�j� dans cette ar�ne !");
				}else
					player.sendMessage(ChatColor.RED + "[BattleRoyale] D�sol� cette ar�ne n'�xiste pas !");
			}else
				player.sendMessage(ChatColor.RED + "[BattleRoyale] D�sol� cette ar�ne n'est pas pr�te !");
		}
	}
	
	public boolean quit(Player player){
		boolean isArena = false;
		for(Arena arena : m_listArena){
			if(arena.getListPlayer().contains(player)){
				for(CPlayer pl : main.getListPlayer())
					pl.setPlayerState(PlayerState.NOTINGAME);
				arena.getListPlayer().remove(player);
				player.teleport(main.getSpawnServer());
				player.setGameMode(GameMode.SURVIVAL);
				isArena = true;
				player.sendMessage(ChatColor.RED + "[Battle Royale] Vous avez quitter l'ar�ne " + arena.getName());
				this.reloadArena(arena);
			}
		}
		return isArena;
	}
	
	public boolean ArenaExist(String name){
		for(Arena arena : m_listArena)
			if(arena.getName().equals(name))
				return true;
		return false;
	}
	
	public void show(Player player){
		for(Arena arena : m_listArena)
			arena.show(player);
	}
	
	public void createArena(String name, int maxPlayer, int minPlayer, boolean chat, ArenaType type){
		if(!ArenaExist(name))
			m_listArena.add(new Arena(main, name, maxPlayer, minPlayer, chat, type));
		else
			System.out.println("Ar�ne " + name + " �xiste d�j� !");
	}
	
	public void createArena(Player player, String worldName, String name, int maxPlayer, int minPlayer, boolean chat, ArenaType type){
		if(!ArenaExist(name))
			m_listArena.add(new Arena(main, worldName, name, maxPlayer, minPlayer, chat, type));
		else
			player.sendMessage(ChatColor.RED + "[BattleRoyale] Ar�ne " + name + " �xiste d�j� !");
	}
	
	public void createArena(String worldName, String name, int maxPlayer, int minPlayer, boolean chat, ArenaType type){
		if(!ArenaExist(name))
			m_listArena.add(new Arena(main, worldName, name, maxPlayer, minPlayer, chat, type));
		else
			System.out.println("Ar�ne " + name + " �xiste d�j� !");
	}
	
	public Arena getArena(String arenaName){
		for(Arena arena : m_listArena)
			if(arena.getName().equals(arenaName))
				return arena;
		return null;
	}
	
	public Arena getArenaByPlayer(Player player){
		for(Arena arena : m_listArena)
			if(arena.getListPlayer().contains(player))
				return arena;
		return null;
	}
	
	public void deleteArena(String name){
		for(Arena arena : m_listArena)
			if(arena.getName().equals(name))
				m_listArena.remove(arena);
	}
	
	public void showArena(){
		for(Arena arena : m_listArena)
			arena.showInformationArena();
	}
	
	public void informationArena(){
		for(Arena arena : m_listArena)
			System.out.println(arena.getName() + " : " + arena.getArenaType() + " -- " + arena.getMaxPlayerTeam() + " -- " + arena.getGameState());
	}
	
	public ArrayList<Arena> getArenaList(){
		return m_listArena;
	}
	
	public boolean playerIsInArena(Player player){
		for(Arena arena : m_listArena)
			if(arena.getListPlayer().contains(player))
				return true;
		return false;
	}
	
	public void reloadArena(Arena arena){
		String arenaName = arena.getName();
		if(this.ArenaExist(arenaName)){
			m_listArena.remove(arena);
			main.loadConfigByArena(arenaName);
		}
	}
	
}

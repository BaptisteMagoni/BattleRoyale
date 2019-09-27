package net.zastax.Arena.player;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.connorlinfoot.actionbarapi.ActionBarAPI;

import net.zastax.Main;
import net.zastax.ScoreboardSign;
import net.zastax.Arena.gun.KitGun;
import net.zastax.Arena.player.bank.Bank;

public class CPlayer {

	private Player m_player;
	private Main main;
	private ScoreboardSign scoreboard;
	private Bank m_bank;
	private PlayerState m_playerstate;
	
	public CPlayer(Main main, Player player){
		this.m_player = player;
		this.main = main;
		m_playerstate = PlayerState.NOTINGAME;
		m_bank = new Bank(main, player);
		this.updateScoreboard();
	}
	
	public Player getPlayer(){
		return m_player;
	}
	
	public String getName(){
		return m_player.getName();
	}
	
	public Bank getBank(){
		return m_bank;
	}

	public ScoreboardSign getScoreboardSign() {
		return scoreboard;
	}
	
	public void updateScoreboard(){
		scoreboard = new ScoreboardSign(main, m_player, m_bank);
	}
	
	public PlayerState getPlayerState(){
		return m_playerstate;
	}
	
	public void setPlayerState(PlayerState ps){
		m_playerstate = ps;
	}
	
	public void loadItemInHand(){
		if(main.getArenaManager().playerIsInArena(m_player)){
			for(KitGun gun : main.getKitGunManager().getKitGun())
				if(m_player.getInventory().getItemInMainHand().getType().equals(gun.getMaterial()))
					if(main.getArenaManager().playerIsInArena(m_player))
						ActionBarAPI.sendActionBar(m_player, ChatColor.GOLD + gun.getExplanationMessage());
			
			if(m_player.getInventory().getItemInMainHand().getType().equals(Material.ELYTRA))
				ActionBarAPI.sendActionBar(m_player, ChatColor.GOLD + "Cliquer droit pour vous le mettre - Appuyer sur espace pour l'activer en l'air");
		}
		if(m_player.getInventory().getItemInMainHand().equals(getItem(Material.COMPASS, "§2Choissisez une arène")))
			ActionBarAPI.sendActionBar(m_player, ChatColor.GOLD + "Cliquer droit pour ouvrir le menu");
	}
	
	private ItemStack getItem(Material m, String name){
		ItemStack it = new ItemStack(m, 1);
		ItemMeta itM = it.getItemMeta();
		itM.setDisplayName(name);
		it.setItemMeta(itM);
		return it;
	}
}

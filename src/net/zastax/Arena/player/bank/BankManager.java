package net.zastax.Arena.player.bank;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import net.zastax.Main;
import net.zastax.Arena.player.CPlayer;

public class BankManager {

	private Main main;
	
	public BankManager(Main main){
		this.main = main;
	}
	
	public boolean playerExist(Player player){
		ConfigurationSection sectionBank = main.getBankConfig().getConfigurationSection("Player");
		if(sectionBank != null)
			for(String playerName: sectionBank.getKeys(false))
				if(playerName.equals(player.getName()))
					return true;
		return false;
	}
	
	public Bank getBankByPlayer(Player player){
		for(CPlayer cp : main.getListPlayer())
			if(cp.getName().equals(player.getName()))
				return cp.getBank();
		return null;
	}
	
}

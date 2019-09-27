package net.zastax.Arena.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import net.zastax.Main;

public class cmdKillEntities implements CommandBatlle {
	
	public cmdKillEntities(Main main) {
		
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
		Player player = (Player) sender;
		int nb = 0;
		for(Entity entity : player.getLocation().getWorld().getEntities()){
			entity.remove();
			nb++;
		}
		player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "BattleRoyale" + ChatColor.GRAY + "]" + ChatColor.GOLD + " Vous avez tué " + ChatColor.GRAY + nb + ChatColor.GOLD + " entité(s) dans le monde " + ChatColor.GRAY + player.getLocation().getWorld().getName() + ChatColor.GOLD + " !");
		
		return false;
	}

	@Override
	public String help(Player paramPlayer) {
		return "/br killall";
	}

	@Override
	public String getPermission() {
		// TODO Auto-generated method stub
		return null;
	}

}

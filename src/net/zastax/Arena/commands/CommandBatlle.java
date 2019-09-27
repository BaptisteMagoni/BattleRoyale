package net.zastax.Arena.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract interface CommandBatlle {
	
	public abstract boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args);
	  
	public abstract String help(Player paramPlayer);
	  
	public abstract String getPermission();
	
}

package net.zastax.Arena.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.zastax.Main;

public class cmdShow implements CommandBatlle {

	private Main main;
	
	public cmdShow(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		Player player = (Player) sender;
		player.sendMessage(ChatColor.GOLD + "------------------" + ChatColor.GRAY + " Arene " + ChatColor.GOLD + "------------------");
		main.getArenaManager().show(player);
		player.sendMessage(ChatColor.GOLD + "-------------------------------------------");
		return false;
	}

	@Override
	public String help(Player paramPlayer) {
		return "/br show";
	}

	@Override
	public String getPermission() {
		// TODO Auto-generated method stub
		return null;
	}

}

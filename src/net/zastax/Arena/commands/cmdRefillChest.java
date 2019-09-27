package net.zastax.Arena.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.zastax.Main;
import net.zastax.Arena.chest.ChestRefill;
import net.zastax.Arena.chest.ItemsRandom;

public class cmdRefillChest implements CommandBatlle {

	private Main main;
	
	public cmdRefillChest(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		Player player = (Player) sender;
		String arenaName = args[0];
		if(main.getArenaManager().ArenaExist(arenaName)){
			new ItemsRandom(main);
			new ChestRefill(main).refillAllChests(arenaName);
			player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "BattleRoyale" + ChatColor.GRAY + "] " + ChatColor.GREEN + "Vous avez mit à jour les coffre de l'arène " + ChatColor.GRAY + arenaName + ChatColor.GRAY + ".");
		}
		
		return false;
	}

	@Override
	public String help(Player paramPlayer) {
		return "/br refillchest <NomDeL'arène>";
	}

	@Override
	public String getPermission() {
		// TODO Auto-generated method stub
		return null;
	}

}

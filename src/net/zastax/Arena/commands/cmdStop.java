package net.zastax.Arena.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.zastax.Main;

public class cmdStop implements CommandBatlle {

	private Main main;
	
	public cmdStop(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		Player player = (Player) sender;
		if(args.length == 0) player.sendMessage(help(player));
		else{
			String arenaName = args[0];
			if(main.getArenaManager().ArenaExist(arenaName))
				if(main.getArenaManager().getArena(arenaName).getState())
					main.getArenaManager().reloadArena(main.getArenaManager().getArenaByPlayer(player));
				else
					player.sendMessage(ChatColor.RED + "[BattleRoyale] Vous ne pouvez pas stoper l'arène car elle n'est pas lancée !");
			else
				player.sendMessage(ChatColor.RED + "[BattleRoyale] Cette arène n'éxiste pas !");
		}
		
		return false;
	}

	@Override
	public String help(Player paramPlayer) {
		return "/br stop <NomDeL'arène>";
	}

	@Override
	public String getPermission() {
		// TODO Auto-generated method stub
		return null;
	}

}

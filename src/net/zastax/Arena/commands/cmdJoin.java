package net.zastax.Arena.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.zastax.Main;

public class cmdJoin implements CommandBatlle {

	private Main main;
	
	public cmdJoin(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		Player player = (Player) sender;
		String arenaName = args[0];
		if(!arenaName.equals(null))
			if(main.getArenaManager().ArenaExist(arenaName))
				if(main.getArenaManager().getArena(arenaName).isReady())
					main.getArenaManager().join(arenaName, player);
				else
					player.sendMessage(ChatColor.RED + "[BattleRoyale] Cette ar�ne n'est pas fini d'�tre configur�e !");
			else
				player.sendMessage(ChatColor.RED + "[BattleRoyale] L'ar�ne existe pas !");
		return false;
	}

	@Override
	public String help(Player paramPlayer) {
		return "/br join <NomDeL'Ar�ne>";
	}

	@Override
	public String getPermission() {
		// TODO Auto-generated method stub
		return null;
	}

}

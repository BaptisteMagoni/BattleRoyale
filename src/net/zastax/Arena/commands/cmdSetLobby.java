package net.zastax.Arena.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.zastax.Main;

public class cmdSetLobby implements CommandBatlle {

	private Main main;
	
	public cmdSetLobby(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		Player player = (Player) sender;
		try{
			String arenaName = args[0];
			if(main.getArenaManager().ArenaExist(arenaName)){
				double x = player.getLocation().getX();
				double y = player.getLocation().getY();
				double z = player.getLocation().getZ();
				String coordonnee = x + "," + y + "," + z;
				main.getArenaConfig().set("Arena." + arenaName + ".lobby", coordonnee);
				main.saveAreneConfig();
				player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "BattleRoyale" + ChatColor.GRAY + "] " + ChatColor.GOLD + "Vous avez mit à jour le lobby de l'arène " + arenaName + "!");
			}else
				player.sendMessage(ChatColor.RED + "[BattleRoyale] Cette arène n'éxiste pas !");
		}catch(Exception e){
			player.sendMessage(ChatColor.RED + "[BattleRoyale] " + help(player));
		}
		return false;
	}

	@Override
	public String help(Player paramPlayer) {
		return "/br setlobby <NomDeL'Arène>";
	}

	@Override
	public String getPermission() {
		// TODO Auto-generated method stub
		return null;
	}

}

package net.zastax.Arena.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.zastax.Main;

public class cmdSetSpawnServer implements CommandExecutor {

	private Main main;
	
	public cmdSetSpawnServer(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		Player player = (Player) sender;
		
		int x = (int) player.getLocation().getX();
		int y = (int) player.getLocation().getY();
		int z = (int) player.getLocation().getZ();
		main.getServerConfig().set("spawn", x + "," + y + "," + z);
		main.getServerConfig().set("worldspawn", player.getLocation().getWorld().getName());
		main.saveServerConfig();
		player.sendMessage("[Serveur] Vous avez mit à jour le spawn du serveur !");
		
		return false;
	}

}

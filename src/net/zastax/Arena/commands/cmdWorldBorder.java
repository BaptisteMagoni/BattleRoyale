package net.zastax.Arena.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.zastax.Main;

public class cmdWorldBorder implements CommandBatlle {

	private Main main;
	
	public cmdWorldBorder(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		Player player = (Player) sender;
		String arenaName = args[0];
		if(args.length == 0) return false;
		else{
			int x = (int) player.getLocation().getX();
			int y = (int) player.getLocation().getY();
			int z = (int) player.getLocation().getZ();
			main.getArenaConfig().set("Arena." + arenaName + ".WorldBorder", x + "," + y + "," + z);
			main.saveAreneConfig();
			main.getArenaManager().getArena(arenaName).getWorldBorder().setCenter(player.getLocation());
			player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "BattleRoyale" + ChatColor.GRAY + "] " + ChatColor.GOLD + "Vous avez mit à jour le centre de la bordure de map !");
		}
		return false;
	}

	@Override
	public String help(Player paramPlayer) {
		return "/br setworldborder <NomDeL'Arène>";
	}

	@Override
	public String getPermission() {
		// TODO Auto-generated method stub
		return null;
	}

}

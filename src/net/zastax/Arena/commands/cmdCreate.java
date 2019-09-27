package net.zastax.Arena.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.zastax.Main;
import net.zastax.Arena.ArenaType;

public class cmdCreate implements CommandBatlle {

	private Main main;
	
	public cmdCreate(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		Player player = (Player) sender;
		if(args.length == 0) player.sendMessage(help(player));
		else{
			String arenaName = String.join(" ", args[0]);
			if(!main.getArenaManager().ArenaExist(arenaName)){
				String maxPlayer = String.join(" ", args[1]);
				String minPlayer = String.join(" ", args[2]);
				String worldName = String.join(" ", args[3]);
				Boolean chat = Boolean.valueOf(String.join(" ", args[4]));
				String arenaType = String.join(" ", args[5]);
				if(arenaType.equals("SOLO"))
					main.getArenaManager().createArena(player, worldName, arenaName, Integer.parseInt(maxPlayer), Integer.parseInt(minPlayer), chat, ArenaType.SOLO);
				else if(arenaType.equals("TEAM"))
					main.getArenaManager().createArena(player, worldName, arenaName, Integer.parseInt(maxPlayer), Integer.parseInt(minPlayer), chat, ArenaType.TEAM);
				else
					player.sendMessage(ChatColor.RED + "[BattleRoyale] Ce type de jeux n'éxiste pas !");
			}else{
				player.sendMessage(ChatColor.RED + "[BattleRoyale] Cette arène éxiste déjà !");
			}
		}
		return false;
	}

	@Override
	public String help(Player paramPlayer) {
		return "/br create <NomDeL'Arène> <MaxPlayer> <MinPlayer> <WorldName> <ChatState(true|false)> <SOLO|TEAM>";
	}

	@Override
	public String getPermission() {
		// TODO Auto-generated method stub
		return null;
	}

}

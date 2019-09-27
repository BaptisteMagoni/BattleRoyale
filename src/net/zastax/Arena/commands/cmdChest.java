package net.zastax.Arena.commands;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.zastax.Main;
import net.zastax.Arena.Arena;

public class cmdChest implements CommandBatlle {

	private Main main;
	
	public cmdChest(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		Player player = (Player) sender;
		if(args.length == 0) player.sendMessage(help(player));
		else{
			String arenaName = args[0];
			if(main.getArenaManager().ArenaExist(arenaName)){
				Arena arena = main.getArenaManager().getArena(arenaName);
				if(main.getArenaConfig().getString("Arena." + arenaName + ".World").equals(player.getLocation().getWorld().getName())){
					int index = arena.getListChest().size();
					int _index = index+1;
					double x = player.getLocation().getX();
					double y = player.getLocation().getY();
					double z = player.getLocation().getZ();
					String coordonnee = x + "," + y + "," + z;
					Location loc = new Location(Bukkit.getServer().getWorld(main.getArenaConfig().getString("Arena." + arenaName + ".World")), x, y, z);
					if(!arena.locationExist(loc)){
						arena.getListChest().add(loc);
						main.getArenaConfig().set("Arena." + arenaName + ".chest." + index, coordonnee);
						main.saveAreneConfig();
						if(_index == 1)
							player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "BattleRoyale" + ChatColor.GRAY + "]" + ChatColor.GOLD + " Vous avez mit " + ChatColor.GRAY + _index + "er " + ChatColor.GOLD + "coffre");
						else
							player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "BattleRoyale" + ChatColor.GRAY + "]" + ChatColor.GOLD + " Vous avez mit " + ChatColor.GRAY + _index + "ème " + ChatColor.GOLD + "coffre");
					}else
						player.sendMessage(ChatColor.RED + "[BattleRoyale] Il y a déjà un coffre de positionné ici !");
				}else{
					player.sendMessage(ChatColor.RED + "[BattleRoyale] Vous ne pouvez pas mettre de coffre dans ce monde !");
				}
			}
				
		}
		return false;
	}
	
	@Override
	public String help(Player paramPlayer) {
		return "/br setchest";
	}

	@Override
	public String getPermission() {
		// TODO Auto-generated method stub
		return null;
	}

}

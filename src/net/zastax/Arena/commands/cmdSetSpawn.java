package net.zastax.Arena.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.zastax.Main;
import net.zastax.Arena.Arena;

public class cmdSetSpawn implements CommandBatlle {

	private Main main;
	
	public cmdSetSpawn(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		Player player = (Player) sender;
		try{
			String arenaName = args[0];
			if(main.getArenaManager().ArenaExist(arenaName)){
				Arena arena = main.getArenaManager().getArena(arenaName);
				int index = arena.getPSpawn().size();
				if(index <= main.getArenaManager().getArena(arenaName).getMaxPlayer()){
					double x = player.getLocation().getX();
					double y = player.getLocation().getY();
					double z = player.getLocation().getZ();
					String coordonnee = x + "," + y + "," + z;
					main.getArenaConfig().set("Arena." + arenaName + ".spawn." + index, coordonnee);
					if(index == 0)
						main.getArenaConfig().set("Arena." + arena.getName() + ".World", player.getLocation().getWorld().getName());
					
					if(player.getLocation().getWorld().getName().equals(main.getArenaConfig().getString("Arena." + arena.getName() + ".World"))){
						int _index = index+1;
						if(_index <= arena.getMaxPlayer()){
							setCage(player.getLocation());
							arena.getPSpawn().add(new Location(Bukkit.getServer().getWorld(main.getArenaConfig().getString("Arena." + arena.getName() + ".World")), x, y, z));
							player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "BattleRoyale" + ChatColor.GRAY + "] " + ChatColor.GOLD + "Vous avez mit en place un nouveau spawn. " + ChatColor.GRAY + _index + ChatColor.GOLD + "/" + ChatColor.GRAY + arena.getMaxPlayer());
							main.saveAreneConfig();
						}else
							player.sendMessage(ChatColor.RED + "[BattleRoyale] Vous avez atteint le maximum de spawn pour cette arène !");
					}else
						player.sendMessage(ChatColor.RED + "[BattleRoyale] Vous ne pouvez pas définir un spawn dans ce monde !");
				}
			}else
				player.sendMessage(ChatColor.RED + "[BattleRoyale] Cette arène n'éxiste pas !");
		}catch(Exception e){
			player.sendMessage(help(player));
		}
		return false;
	}

	private void setCage(Location loc){
		
	}
	
	@Override
	public String help(Player paramPlayer) {
		return "/br setspawn <NomDeL'arène>";
	}

	@Override
	public String getPermission() {
		// TODO Auto-generated method stub
		return null;
	}

}

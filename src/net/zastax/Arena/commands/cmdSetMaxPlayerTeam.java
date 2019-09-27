package net.zastax.Arena.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.zastax.Main;

public class cmdSetMaxPlayerTeam implements CommandBatlle {

	private Main main;
	
	public cmdSetMaxPlayerTeam(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		Player player = (Player) sender;
		
		try{
			String arenaName = String.join(" ", args[0]);
			if(main.getArenaManager().ArenaExist(arenaName)){
				int nb = Integer.parseInt(String.join(" ", args[1]));
				main.getArenaManager().getArena(arenaName).setMaxPlayerTeam(nb);
				main.getArenaConfig().set("Arena." + arenaName + ".setteammaxplayer", nb);
				main.saveAreneConfig();
			}
			
		}catch(Exception e){
			player.sendMessage(ChatColor.RED + "[BattleRoyale] /br setmaxplayerteam <NomDeL'Arène> <NombreMax>");
		}
		
		return false;
	}

	@Override
	public String help(Player paramPlayer) {
		// TODO Auto-generated method stub
		return "/br setmaxplayerteam <NomDeL'Arène> <NombreMax>";
	}

	@Override
	public String getPermission() {
		// TODO Auto-generated method stub
		return null;
	}

}

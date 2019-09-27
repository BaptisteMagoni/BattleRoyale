package net.zastax.Arena.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.zastax.Main;
import net.zastax.Arena.player.CPlayer;
import net.zastax.Arena.player.PlayerState;

public class cmdQuit implements CommandBatlle {

	private Main main;
	
	public cmdQuit(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		Player player = (Player) sender;
		for(CPlayer cp : main.getListPlayer())
			if(cp.getPlayerState().equals(PlayerState.INGAME))
				main.getArenaManager().quit(player);
			else
				player.sendMessage(ChatColor.RED + "[BattleRoyale] Vous n'êtes pas dans une arène !");
		return false;
	}

	@Override
	public String help(Player paramPlayer) {
		return "/br quit";
	}

	@Override
	public String getPermission() {
		return null;
	}

}

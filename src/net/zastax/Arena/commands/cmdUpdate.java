package net.zastax.Arena.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.zastax.Main;
import net.zastax.Arena.player.CPlayer;

public class cmdUpdate implements CommandBatlle {

	private Main main;
	
	public cmdUpdate(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		for(CPlayer cp : main.getListPlayer()){
			cp.updateScoreboard();
			System.out.println(cp.getBank().getMoney());
		}
		return false;
	}

	@Override
	public String help(Player paramPlayer) {
		return "/br update";
	}

	@Override
	public String getPermission() {
		// TODO Auto-generated method stub
		return null;
	}

}

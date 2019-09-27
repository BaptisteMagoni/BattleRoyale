package net.zastax.Arena.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.zastax.Main;

public class cmdMenu implements CommandBatlle {

	public cmdMenu(Main main) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		Player player = (Player) sender;
		ItemStack it = new ItemStack(Material.COMPASS, 1);
		ItemMeta itM = it.getItemMeta();
		itM.setDisplayName("Choissisez une arène");
		it.setItemMeta(itM);
		player.getInventory().addItem(it);
		player.updateInventory();
		return false;
	}

	@Override
	public String help(Player paramPlayer) {
		return "/br menu";
	}

	@Override
	public String getPermission() {
		// TODO Auto-generated method stub
		return null;
	}

}

package net.zastax.Arena.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.zastax.Main;

public class cmdGiveAxe implements CommandBatlle {

	@SuppressWarnings("unused")
	private Main main;
	
	public cmdGiveAxe(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		Player player = (Player) sender;
		player.getInventory().addItem(getItem("§eBattleRoyale Area", Material.DIAMOND_AXE, 1, 0, null, 0));
		player.updateInventory();
		player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "BattleRoyale" + ChatColor.GRAY + "]" + ChatColor.GOLD + " Vous avez reçu la hache pour séléctionner la zone de combat !");
		return false;
	}

	@Override
	public String help(Player paramPlayer) {
		return "/br giveAxe";
	}

	@Override
	public String getPermission() {
		return null;
	}
	
	@SuppressWarnings({ "deprecation" })
	private ItemStack getItem(String displayName, Material material, int amount, int data, Enchantment ench, int enchLevel) {
		ItemStack i = new ItemStack(material,amount,(byte)data);
		ItemMeta iM = i.getItemMeta();
		iM.setDisplayName(displayName);
		if(ench != null && enchLevel != 0)
			iM.addEnchant(ench, enchLevel, true);
		
		i.setItemMeta(iM);
		return i;
	}

}

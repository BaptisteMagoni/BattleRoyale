package net.zastax.Arena.chest;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.zastax.Main;
import net.zastax.Arena.gun.KitGun;

public class ItemsRandom {

	private static ArrayList<ItemStack> itemsList = new ArrayList<ItemStack>();
	private Main main;
	
	public ItemsRandom(Main main){
		this.main = main;
		
		itemsList.add(getItem(null, Material.DIAMOND_SWORD, 1, 0));
		itemsList.add(getItem(null, Material.DIAMOND_CHESTPLATE, 1, 0));
		itemsList.add(getItem(null, Material.IRON_CHESTPLATE, 1, 0));
		itemsList.add(getItem(null, Material.ACACIA_WOOD, 10, 0));
		itemsList.add(getItem(null, Material.IRON_BLOCK, 5, 0));
		itemsList.add(getItem(null, Material.BRICKS, 7, 0));
		
		for(KitGun gun : this.main.getKitGunManager().getKitGun())
			itemsList.add(getItem(gun.getName(), gun.getMaterial(), gun.getAmount(), gun.getData()));

		System.out.println("[ChestRefill] Chest all refill");
				
	}

	public static ItemStack getRandomItem(){
		int listLimit = itemsList.size() - 1;
		Random rand = new Random();
		int alea = rand.nextInt(listLimit);
		return itemsList.get(alea);
	}
	
	@SuppressWarnings("deprecation")
	private ItemStack getItem(String displayName, Material material, int amount, int data) {
		ItemStack i = new ItemStack(material,amount,(byte)data);
		ItemMeta iM = i.getItemMeta();
		iM.setDisplayName(displayName);
		i.setItemMeta(iM);
		return i;
	}
	
	@SuppressWarnings({ "deprecation", "unused" })
	private ItemStack getItem(String displayName, Material material, int amount, int data, Enchantment ench, int enchLevel) {
		ItemStack i = new ItemStack(material,amount,(byte)data);
		ItemMeta iM = i.getItemMeta();
		iM.setDisplayName(displayName);
		if(ench != null && enchLevel != 0)
			iM.addEnchant(ench, enchLevel, true);
		
		i.setItemMeta(iM);
		return i;
	}
	
	public ArrayList<ItemStack> getItemsList(){
		return itemsList;
	}
	
}

package net.zastax.Arena.chest;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.zastax.Main;

public class ChestRefill {

	private Main main;
	
	public ChestRefill(Main main){
		this.main = main;
	}
	
	public void refillAllChests(String arenaName){
		for(Chunk chunk : Bukkit.getServer().getWorld(main.getArenaConfig().getString("Arena." + arenaName + ".World")).getLoadedChunks()){
			for(BlockState bs : chunk.getTileEntities()){
				if(bs instanceof Chest){
					Chest chest = (Chest) bs.getBlock().getState();
					Inventory inv = chest.getInventory();
					inv.clear();
					
					for(int i=0;i<5;i++){
						ItemStack randomItem = ItemsRandom.getRandomItem();
						Random r = new Random();
						int limit = inv.getSize()-1;
						int randomSlot = r.nextInt(limit);
						inv.setItem(randomSlot, randomItem);
					}
					
				}
			}
		}
	}
	
}

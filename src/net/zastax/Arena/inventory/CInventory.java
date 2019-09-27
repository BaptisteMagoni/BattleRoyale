package net.zastax.Arena.inventory;

import java.util.ArrayList;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public abstract class CInventory {
	
	public abstract void create();
	
	public abstract int getSize();
	
	public abstract Inventory getInventory();
	
	public abstract void setSize(int size);
	
	public abstract ArrayList<ItemStack> getItemsList();
	
}

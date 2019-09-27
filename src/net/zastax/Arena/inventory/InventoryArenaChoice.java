package net.zastax.Arena.inventory;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.zastax.Main;

public class InventoryArenaChoice extends CInventory{

	private ArrayList<ItemStack> m_items;
	private Inventory m_inv;
	private int m_size;
	private Main main;
	
	public InventoryArenaChoice(Main main){
		this.main = main;
		m_size = 9;
		System.out.println("Size team " + m_size);
		m_items = new ArrayList<ItemStack>();
	}
	
	@Override
	public void create() {
		m_inv = Bukkit.createInventory(null, m_size, "§cMenu des arènes");
		m_inv.setItem(2, main.getItem(Material.BOW, "§4Arène SOLO"));
		m_inv.setItem(6, main.getItem(Material.ARROW, "§4Arène TEAM"));
		m_inv.setItem(m_inv.getSize()-1, main.getItem(Material.BARRIER, ChatColor.DARK_RED + "Fermer le menu"));
		main.filleEmptyBoxes(m_inv);
	}

	@Override
	public int getSize() {
		return m_size;
	}

	@Override
	public Inventory getInventory() {
		return m_inv;
	}

	@Override
	public void setSize(int size) {
		m_size = size;
	}

	@Override
	public ArrayList<ItemStack> getItemsList() {
		return m_items;
	}
	
}

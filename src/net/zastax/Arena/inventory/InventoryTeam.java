package net.zastax.Arena.inventory;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.zastax.Main;
import net.zastax.Arena.Team.GroupTeam;

public class InventoryTeam extends CInventory{

	private ArrayList<ItemStack> m_items;
	private Inventory m_inv;
	private int m_size;
	private Main main;
	
	public InventoryTeam(Main main){
		this.main = main;
		m_size = getSize();
		m_items = new ArrayList<ItemStack>();
	}
	
	@Override
	public void create() {
		m_inv = Bukkit.createInventory(null, m_size, "§cChoisissez une équipe");
		for(GroupTeam gt : GroupTeam.values())
			m_inv.addItem(main.getItem(gt.getMaterial(), gt.getColor() + gt.getName()));
		m_inv.setItem(m_size-1, main.getItem(Material.BARRIER, ChatColor.DARK_RED + "Fermer le menu"));
	}

	@SuppressWarnings("unused")
	@Override
	public int getSize() {
		int step = 1;
		int nb = 0;
		for(GroupTeam gt : GroupTeam.values())
			if(nb == 9) {
				step++;
				nb = 0;
			}else nb++;
		return 9*step;
	}

	@Override
	public Inventory getInventory() {
		return m_inv;
	}

	@Override
	public void setSize(int size) {
		this.m_size = size;
		
	}

	@Override
	public ArrayList<ItemStack> getItemsList() {
		return m_items;
	}
	
}

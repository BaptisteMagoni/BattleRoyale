package net.zastax.Arena.inventory;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.zastax.Main;
import net.zastax.Arena.Arena;
import net.zastax.Arena.ArenaType;
import net.zastax.Arena.GameState;

public class InventoryMain extends CInventory{

	private ArrayList<ItemStack> m_items;
	private Inventory m_inv;
	private int m_size;
	private Main main;
	private ArenaType m_typeArena;
	private int index;
	
	public InventoryMain(Main main){
		this.main = main;
		m_size = getSize();
		m_items = new ArrayList<ItemStack>();
	}
	
	public void create(){
		index = 0;
		m_inv = Bukkit.createInventory(null, m_size, "§2Choisissez une arène");
		for(Arena arena : main.getArenaManager().getArenaList())
			if(arena.getGameState().equals(GameState.WAITING))
				if(arena.getArenaType().equals(m_typeArena))
						setItem(arena);
		m_inv.setItem(m_size-1, main.getItem(Material.BARRIER, ChatColor.DARK_RED + "Fermer le menu"));
		m_inv.setItem(m_size-2, main.getItem(Material.ACACIA_DOOR, ChatColor.GREEN + "Revenir en arrière"));
		main.filleEmptyBoxes(m_inv);
	}
	
	public Inventory getInventory(){
		return m_inv;
	}
	
	public void setSize(int size){
		m_size = size;
	}
	
	public ArrayList<ItemStack> getItemsList(){
		return m_items;
	}
	
	private void setItem(Arena arena){
		m_inv.setItem(index, main.getItem(Material.DIAMOND_HOE, ChatColor.BLUE + arena.getName()));
		index++;
	}
	
	@SuppressWarnings("unused")
	public int getSize() {
		int step = 1;
		int nb = 0;
		for(Arena arena : main.getArenaManager().getArenaList())
			if(nb == 9) {
				step++;
				nb = 0;
			}else nb++;
		return 9*step;
	}
	
	public void setArenaType(ArenaType arenaType){
			m_typeArena = arenaType;
	}
	
	public ArenaType getArenaType(){
		return m_typeArena;
	}
}

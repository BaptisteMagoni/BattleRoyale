package net.zastax.Arena.Team;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public enum GroupTeam {

	RED("RED", ChatColor.RED, Material.RED_WOOL, new ArenaTeam("RED")),
	GREEN("GREEN", ChatColor.GREEN, Material.GREEN_WOOL, new ArenaTeam("GREEN")),
	BLUE("BLUE", ChatColor.BLUE, Material.BLUE_WOOL, new ArenaTeam("BLUE")),
	YELLOW("YELLOW", ChatColor.YELLOW, Material.YELLOW_WOOL, new ArenaTeam("YELLOW")),
	ORANGE("ORANGE", ChatColor.GOLD, Material.ORANGE_WOOL, new ArenaTeam("ORANGE")),
	GRAY("GRAY", ChatColor.DARK_GRAY, Material.GRAY_WOOL, new ArenaTeam("GRAY")),
	AQUA("AQUA", ChatColor.AQUA, Material.LIGHT_BLUE_WOOL, new ArenaTeam("AQUA")),
	DARK_RED("DARK_RED", ChatColor.DARK_RED, Material.NETHER_WART_BLOCK, new ArenaTeam("DARK_RED")),
	DARK_GREEN("DARK_GREEN", ChatColor.DARK_GREEN, Material.GREEN_CONCRETE, new ArenaTeam("DARK_GREEN")),
	DARK_BLUE("DARK_BLUE", ChatColor.DARK_BLUE, Material.LAPIS_BLOCK, new ArenaTeam("DARK_BLUE")),
	DARK_PURPLE("DARK_PURPLE", ChatColor.DARK_PURPLE, Material.PURPLE_WOOL, new ArenaTeam("DARK_PURPLE")),
	DARK_AQUA("DARK_AQUA", ChatColor.DARK_AQUA, Material.BLUE_CONCRETE, new ArenaTeam("DARK_AQUA"));
	
	private String m_name;
	private ChatColor m_color;
	private Material m_material;
	private ArenaTeam m_team;
	
	GroupTeam(String name, ChatColor color, Material material, ArenaTeam team){
		this.m_name = name;
		this.m_color = color;
		this.m_material = material;
		this.m_team = team;
	}
	
	public String getName(){
		return "[" + m_name + "]";
	}
	
	public ChatColor getColor(){
		return m_color;
	}
	
	public Material getMaterial(){
		return m_material;
	}
	
	public void addPlayer(Player player){
		m_team.addPlayer(player);
		player.setDisplayName(m_color + m_name + " " + player.getName());
	}
	
	public ArenaTeam getTeam(){
		return m_team;
	}
	
	public String getDisplayName(){
		return m_color + "[" + m_name + "]";
	}
}

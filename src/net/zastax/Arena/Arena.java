package net.zastax.Arena;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import net.zastax.Main;
import net.zastax.Arena.chest.ChestRefill;
import net.zastax.Arena.chest.ItemsRandom;
import net.zastax.Arena.modeGameArena.Finish;
import net.zastax.Arena.modeGameArena.Game;
import net.zastax.Arena.modeGameArena.Pregame;
import net.zastax.Arena.modeGameArena.Wainting;
import net.zastax.Arena.player.CPlayer;
import net.zastax.Arena.player.PlayerArena;
import net.zastax.Arena.player.PlayerState;

public class Arena {
	
	private Main main;
	private String m_name;
	private Wainting m_wait;
	private Pregame m_pregame;
	private Game m_game;
	private Finish m_finish;
	
	private Scoreboard board;
	private WorldBorder m_wb;
	private Team team;
	
	private int m_maxPlayer;
	private int m_minPlayer;
	private int m_sch_state;
	private int m_nb_max_player_team;
	
	private boolean m_state;
	private boolean m_chatState;
	private GameState m_arenaState;
	private ArenaType m_type;
	
	private ArrayList<PlayerArena> m_arenaPlayer;
	private ArrayList<Player> m_listPlayers;
	private ArrayList<Location> m_playerSpawn;
	private ArrayList<Location> m_listChest;
	
	public Arena(Main main, String worldName, String name, int maxPlayer, int minPlayer, boolean chat, ArenaType type){
		this.main = main;
		this.m_chatState = chat;
		m_playerSpawn = new ArrayList<Location>();
		m_listPlayers = new ArrayList<Player>();
		m_listChest = new ArrayList<Location>();
		m_arenaPlayer = new ArrayList<PlayerArena>();
		board = Bukkit.getScoreboardManager().getMainScoreboard();
		this.m_type = type;
		this.m_name = name;
		this.setMaxPlayer(maxPlayer);
		this.setMinPlayer(minPlayer);
		this.registerConfig(worldName);
		if(type.equals(ArenaType.TEAM)){
			this.m_nb_max_player_team = main.getArenaConfig().getInt("Arena." + m_name + ".setteammaxplayer");
		}
		m_arenaState = GameState.WAITING;
		World world = Bukkit.getWorld(main.getArenaConfig().getString("Arena." + m_name + ".World"));
		m_wb = world.getWorldBorder();
		m_wb.setSize(200);
		m_state = false;
		this.loadPlayerSpawn();
		this.loadChestLocation();
	}
	
	public Arena(Main main, String name, int maxPlayer, int minPlayer, boolean chat, ArenaType type){
		this.main = main;
		this.m_chatState = chat;
		m_type = type;
		m_playerSpawn = new ArrayList<Location>();
		m_listPlayers = new ArrayList<Player>();
		m_listChest = new ArrayList<Location>();
		m_arenaPlayer = new ArrayList<PlayerArena>();
		board = Bukkit.getScoreboardManager().getMainScoreboard();
		this.setName(name);
		this.setMaxPlayer(maxPlayer);
		this.setMinPlayer(minPlayer);
		m_arenaState = GameState.WAITING;
		World world = Bukkit.getWorld(main.getArenaConfig().getString("Arena." + m_name + ".World"));
		m_wb = world.getWorldBorder();
		m_wb.setSize(350);
		m_state = false;
		this.loadPlayerSpawn();
		this.loadChestLocation();
	}
	
	public ArrayList<Player> getListPlayer(){
		return m_listPlayers;
	}
	
	public ArrayList<Location> getPSpawn(){
		return m_playerSpawn;
	}
	
	public ArrayList<Location> getListChest(){
		return m_listChest;
	}
	
	public ArrayList<PlayerArena> getPlayerArena(){
		return m_arenaPlayer;
	}
	
	public String getName() {
		return m_name;
	}

	public void setName(String m_name) {
		this.m_name = m_name;
	}

	public int getMaxPlayer() {
		return m_maxPlayer;
	}

	public void setMaxPlayer(int maxPlayer) {
		this.m_maxPlayer = maxPlayer;
	}

	public int getMinPlayer() {
		return m_minPlayer;
	}
	
	public int getMaxPlayerTeam(){
		return m_nb_max_player_team;
	}
	
	public void setMaxPlayerTeam(int nb){
		m_nb_max_player_team = nb;
	}
	
	public void setMinPlayer(int minPlayer) {
		this.m_minPlayer = minPlayer;
	}
	
	public void setState(boolean state){
		this.m_state = state;
	}
	
	public boolean getState(){
		return m_state;
	}
	
	public boolean getChatState(){
		return m_chatState;
	}
	
	public GameState getGameState(){
		return m_arenaState;
	}
	
	public ArenaType getArenaType(){
		return m_type;
	}
	
	public WorldBorder getWorldBorder(){
		return m_wb;
	}
	
	public Scoreboard getTabList(){
		return board;
	}
	
	public Team getTeamTabList(){
		return team;
	}
	
	public void setTeamTabList(Team team){
		this.team = team;
	}
	
	public Game getGame(){
		return m_game;
	}
	
	public PlayerArena getPlayerArenaByPlayer(Player player){
		for(PlayerArena pa : m_arenaPlayer)
			if(pa.getPlayer().equals(player))
				return pa;
		return null;
	}
	
	public void add(Player player){
		m_listPlayers.add(player);
		player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "Battle Royale" + ChatColor.GRAY + "]" + ChatColor.GOLD + 
		" Vous êtes le " + ChatColor.GRAY + m_listPlayers.size() + ChatColor.GOLD + "/" + ChatColor.GRAY + this.m_maxPlayer + 
		ChatColor.GOLD + " à rejoindre le serveur !");
		m_arenaPlayer.add(new PlayerArena(player));
		player.teleport(this.getLocationLobby());
		for(CPlayer cp : main.getListPlayer())
			if(cp.getName().equals(player.getName()))
				cp.setPlayerState(PlayerState.INGAME);
		if(m_type.equals(ArenaType.TEAM)){
			player.getInventory().clear();
			player.getInventory().addItem(main.getItem(Material.NAME_TAG, "§cChoisissez une équipe"));
			player.updateInventory();
		}
	}
	
	public void showInformationArena(){
		System.out.println("Arena Name : " + getName());
		System.out.println("Max Player : " + getMaxPlayer());
		System.out.println("Min Player : " + getMinPlayer());
	}
	
	public void show(Player player){
		if(isReady())
			player.sendMessage(ChatColor.GRAY + getName() + " : " + ChatColor.GREEN + ChatColor.ITALIC + ChatColor.BOLD + "V");
		else
			player.sendMessage(ChatColor.GRAY + getName() + " : " + ChatColor.DARK_RED + ChatColor.BOLD + "X");
		player.sendMessage(ChatColor.GRAY + "   - Max Player : " + getMaxPlayer());
		player.sendMessage(ChatColor.GRAY + "   - Min Player : " + getMinPlayer());
		if(getState())
			player.sendMessage(ChatColor.GRAY + "   - Etat : En cours");
		else
			player.sendMessage(ChatColor.GRAY + "   - Etat : En attente");
	}
	
	public boolean isReady(){
		if(m_playerSpawn.size() < m_maxPlayer) return false;
		//if(m_listChest.size() <= 3) return false;
		if(main.getArenaConfig().getString("Arena." + m_name + ".lobby") == null) return false;
		if(main.getArenaConfig().getString("Arena." + m_name + ".BreakLimite") == null) return false;
		if(m_type.equals(ArenaType.TEAM))
			if(this.m_nb_max_player_team == 0) return false;
		return true;
	}
	
	public boolean locationExist(Location location){
		for(Location loc : m_listChest)
			if(loc.equals(location))
				return true;
		return false;
	}
	
	@SuppressWarnings("unused")
	private Location getLocationWorldBorder(){
		World w = Bukkit.getServer().getWorld(main.getArenaConfig().getString("Arena." + m_name + ".World"));
		try{
			double tab[] = main.StringParseDouble(main.getArenaConfig().getString("Arena." + m_name + ".lobby"));
			return new Location(w, tab[0], tab[1], tab[2]);
		}catch(Exception e){
			return w.getSpawnLocation();
		}
	}
	
	private Location getLocationLobby(){
		World w = Bukkit.getServer().getWorld(main.getArenaConfig().getString("Arena." + m_name + ".World"));
		try{
			double tab[] = main.StringParseDouble(main.getArenaConfig().getString("Arena." + m_name + ".lobby"));
			return new Location(w, tab[0], tab[1], tab[2]);
		}catch(Exception e){
			return w.getSpawnLocation();
		}
	}
	
	private void setChestLocation(){
		for(Location loc : m_listChest)
			loc.getBlock().setType(Material.CHEST);
	}
	
	private void loadChestLocation(){
		ConfigurationSection chestArena = main.getArenaConfig().getConfigurationSection("Arena." + m_name + ".chest");
		if(chestArena != null){
			World w = Bukkit.getServer().getWorld(main.getArenaConfig().getString("Arena." + m_name + ".World"));
			if(chestArena != null){
				for(String id : chestArena.getKeys(false)){
					String coord = chestArena.getString(id);
					double tab[] = main.StringParseDouble(coord);
					this.m_listChest.add(new Location(w, tab[0], tab[1], tab[2]));
				}
			}
		}
	}
	
	private void loadPlayerSpawn(){
		ConfigurationSection sectionArena = main.getArenaConfig().getConfigurationSection("Arena." + m_name + ".spawn");
		if(sectionArena != null){
			World w = Bukkit.getServer().getWorld(main.getArenaConfig().getString("Arena." + m_name + ".World"));
			if(sectionArena != null){
				for(String id : sectionArena.getKeys(false)){
					String coord = sectionArena.getString(id);
					double tab[] = main.StringParseDouble(coord);
					m_playerSpawn.add(new Location(w, tab[0], tab[1], tab[2]));
				}
			}
		}
	}
	
	public void loadArena(){
		if(m_listPlayers.size() != 0){
			if(m_arenaState.equals(GameState.WAITING))
				waitingState();
			else if(m_arenaState.equals(GameState.PREGAME))
				pregameState();
			else if(m_arenaState.equals(GameState.GAME))
				gameState();
			else if(m_arenaState.equals(GameState.FINISH))
				finishState();
		}
	}
	
	private void waitingState(){
		if(m_wait == null){
			m_wait = new Wainting(main, m_name);
		}
		m_arenaState = m_wait.start(m_listPlayers, m_minPlayer, m_maxPlayer, m_sch_state, m_playerSpawn);
	}
	
	private void pregameState(){
		if(m_pregame == null){
			setState(true);
			loadChestLocation();
			setChestLocation();
			new ItemsRandom(main);
			new ChestRefill(main).refillAllChests(m_name);
			m_pregame = new Pregame();
		}
		m_arenaState = m_pregame.start(m_listPlayers, m_playerSpawn);
	}
	
	private void gameState(){
		if(m_game == null)
			m_game = new Game(main, m_wb);
		m_arenaState = m_game.start(m_listPlayers);
	}
	
	private void finishState(){
		if(m_finish == null)
			m_finish = new Finish(main);
		m_arenaState = m_finish.start(m_listPlayers);
	}

	private void registerConfig(String worldName){
		main.getArenaConfig().set("Arena." + m_name + ".maxPlayer", m_maxPlayer);
		main.getArenaConfig().set("Arena." + m_name + ".minPlayer", m_minPlayer);
		main.getArenaConfig().set("Arena." + m_name + ".World", worldName);
		main.getArenaConfig().set("Arena." + m_name + ".chat", m_chatState);
		main.saveAreneConfig();
	}
}

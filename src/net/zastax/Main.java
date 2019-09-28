package net.zastax;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import net.zastax.Arena.Arena;
import net.zastax.Arena.ArenaManager;
import net.zastax.Arena.ArenaType;
import net.zastax.Arena.commands.cmdSetSpawnServer;
import net.zastax.Arena.commands.registerCommand;
import net.zastax.Arena.gun.KitGunManager;
import net.zastax.Arena.inventory.CInventory;
import net.zastax.Arena.inventory.InventoryMain;
import net.zastax.Arena.inventory.InventoryTeam;
import net.zastax.Arena.inventory.InventoryArenaChoice;
import net.zastax.Arena.listener.EventManager;
import net.zastax.Arena.player.CPlayer;
import net.zastax.Arena.player.bank.BankManager;

public class Main extends JavaPlugin{

	//ChatColor.GRAY + "[" + ChatColor.GOLD + "BattleRoyale" + ChatColor.GRAY + "] " + ChatColor.GOLD +
	
	private ArenaManager arenaManager;
	private KitGunManager kitGunManager;
	private BankManager bankmanager;
	private TabListManager tablistmanager;
	
	private Location spawn;
	
	private ArrayList<CPlayer> listPlayer = new ArrayList<CPlayer>();
	private ArrayList<Damager> listDamage = new ArrayList<Damager>();

	private HashMap<String, CInventory> mapInventory = new HashMap<>();
	
	private final File arenaFile = new File(getDataFolder(), "arene.yml");
	private final YamlConfiguration arenaConfig = YamlConfiguration.loadConfiguration(this.arenaFile);
	
	private final File kitFile = new File(getDataFolder(), "kit.yml");
	private final YamlConfiguration kitConfig = YamlConfiguration.loadConfiguration(this.kitFile);
	
	private final File playerFile = new File(getDataFolder(), "player.yml");
	private final YamlConfiguration playerConfig = YamlConfiguration.loadConfiguration(this.playerFile);
	
	private final File serverFile = new File(getDataFolder(), "server.yml");
	private final YamlConfiguration serverConfig = YamlConfiguration.loadConfiguration(this.serverFile);
	
	public void onEnable(){
		if(!getDataFolder().exists())
			getDataFolder().mkdir();
		this.createFile(arenaFile);
		this.createFile(kitFile);
		this.createFile(playerFile);
		this.createFile(serverFile);
		new EventManager(this).registerEvent();
		bankmanager = new BankManager(this);
		arenaManager = new ArenaManager(this);
		for(Player pls : Bukkit.getOnlinePlayers())
			listPlayer.add(new CPlayer(this, pls));
		kitGunManager = new KitGunManager();
		tablistmanager = new TabListManager(this);
		
		loadConfig();
		
		mapInventory.put("main", new InventoryMain(this));
		mapInventory.put("arenachoice", new InventoryArenaChoice(this));
		mapInventory.put("team", new InventoryTeam(this));
		
		getCommand("br").setExecutor(new registerCommand(this));
		getCommand("setspawn").setExecutor(new cmdSetSpawnServer(this));
		try{
			this.spawn = getLocationSpawnServer();
		}catch(Exception err){
			System.out.println("Error : " + err);
		}
		listDamage.add(new Damager(DamageCause.BLOCK_EXPLOSION, "Le joueur %s viens de ce faire exploser la tête !"));
		listDamage.add(new Damager(DamageCause.BLOCK_EXPLOSION, listDamage.get(0).getDeathMessage()));
		listDamage.add(new Damager(DamageCause.FALL, "Le joueur %s à été optimiste de sauter aussi haut !"));
		listDamage.add(new Damager(DamageCause.FIRE, "Le joueur %s à été crucifié !"));
		listDamage.add(new Damager(DamageCause.DROWNING, "Le joueur %s c'est prit pour un poisson !"));
		this.arenaManager.informationArena();
		System.out.println("Test de merde : " + arenaConfig.getInt("Arena.Lol.setteammaxplayer"));
		run();
	}
	
	public void onDisable(){
		
	}
	
	public void saveAreneConfig(){
	    try{ 
	    	this.arenaConfig.save(this.arenaFile);
	    }catch (IOException e){
	    	e.printStackTrace();
	    }
	}
	
	public void saveKitConfig(){
	    try{ 
	    	this.kitConfig.save(this.kitFile);
	    }catch (IOException e){
	    	e.printStackTrace();
	    }
	}
	
	public void savePlayerConfig(){
	    try{ 
	    	this.playerConfig.save(this.playerFile);
	    }catch (IOException e){
	    	e.printStackTrace();
	    }
	}
	
	public void saveServerConfig(){
	    try{ 
	    	this.serverConfig.save(this.serverFile);
	    }catch (IOException e){
	    	e.printStackTrace();
	    }
	}
	
	public YamlConfiguration getArenaConfig(){
		return arenaConfig;
	}
	
	public YamlConfiguration getKitConfig(){
		return kitConfig;
	}
	
	public YamlConfiguration getBankConfig(){
		return playerConfig;
	}
	
	public YamlConfiguration getServerConfig(){
		return serverConfig;
	}
	
	public ArrayList<CPlayer> getListPlayer(){
		return listPlayer;
	}
	
	public ArrayList<Damager> getListDamager(){
		return listDamage;
	}
	
	public BankManager getBankManager(){
		return bankmanager;
	}
	
	public HashMap<String, CInventory> getMapInventory(){
		return mapInventory;
	}
	
	public ArenaManager getArenaManager(){
		return arenaManager;
	}
	
	public KitGunManager getKitGunManager(){
		return kitGunManager;
	}
	
	public Location getSpawnServer(){
		return spawn;
	}
	
	public TabListManager getListManager(){
		return tablistmanager;
	}
	
	public double[] StringParseDouble(String coord){
		String coordSplit[] = coord.split(",");
		double coordonnee[] = new double[coordSplit.length];
		for(int i=0;i<coordSplit.length;i++)
			coordonnee[i] = Double.valueOf(coordSplit[i]);
		return coordonnee;
	}
	
	public void loadConfigByArena(String arena){
		String arena_name = arena;
		int maxPlayer = arenaConfig.getInt(arena_name + ".maxPlayer");
		int minPlayer = arenaConfig.getInt(arena_name + ".minPlayer");
		boolean chat = arenaConfig.getBoolean(arena_name + ".chat");
		String arenaType = arenaConfig.getString(arena_name + ".type");
		String worldName = arenaConfig.getString(arena_name + ".World");
		if(arenaType.equals("SOLO"))
			this.arenaManager.createArena(worldName, arena_name, maxPlayer, minPlayer, chat, ArenaType.SOLO);
		else
			this.arenaManager.createArena(worldName, arena_name, maxPlayer, minPlayer, chat, ArenaType.TEAM);
	}
	
	public void filleEmptyBoxes(Inventory inv){
		for(int i=0;i<inv.getSize();i++)
			if(inv.getItem(i) == null)
				inv.setItem(i, getItem(Material.LIME_STAINED_GLASS_PANE, ChatColor.GREEN + "BattleRoyale"));
			
	}
	
	public ItemStack getItem(Material m, String name){
		ItemStack it = new ItemStack(m, 1);
		ItemMeta itM = it.getItemMeta();
		itM.setDisplayName(name);
		it.setItemMeta(itM);
		return it;
	}
	
	private void createFile(File file){
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private Location getLocationSpawnServer(){
		World w = Bukkit.getServer().getWorld(this.getServerConfig().getString("worldspawn"));
		double tab[] = this.StringParseDouble(this.getServerConfig().getString("spawn"));
		if(w != null || tab.length == 0) 
			return new Location(Bukkit.getWorld("world"), 0, 0, 0);
		return new Location(w, tab[0], tab[1], tab[2]);
	}
	
	private void loadConfig(){
		ConfigurationSection sectionArena = arenaConfig.getConfigurationSection("Arena");
		if(sectionArena != null) {
			for(String arena_name: sectionArena.getKeys(false)){
				int maxPlayer = sectionArena.getInt(arena_name + ".maxPlayer");
				int minPlayer = sectionArena.getInt(arena_name + ".minPlayer");
				boolean chat = sectionArena.getBoolean(arena_name + ".chat");
				String arenaType = sectionArena.getString(arena_name + ".type");
				String worldName = sectionArena.getString(arena_name + ".World");
				if(arenaType.equals("SOLO"))
					this.arenaManager.createArena(worldName, arena_name, maxPlayer, minPlayer, chat, ArenaType.SOLO);
				else
					this.arenaManager.createArena(worldName, arena_name, maxPlayer, minPlayer, chat, ArenaType.TEAM);
			}
		}else {
			System.out.println("Aucune arène de détecté !");
		}
	}
	
	private void createInventory() {
		mapInventory.put("main", new InventoryMain(this));
		mapInventory.put("arenachoice", new InventoryArenaChoice(this));
		mapInventory.put("team", new InventoryTeam(this));
	}
	
	private void run(){
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){

			@Override
			public void run() {
				for(Arena arena : arenaManager.getArenaList()){
					arena.loadArena();
				}
				for(CPlayer cp : listPlayer){
					cp.updateScoreboard();
					cp.loadItemInHand();
				}
				tablistmanager.loadTabList();
			}
			
		}, 20, 20);
	}
	
}

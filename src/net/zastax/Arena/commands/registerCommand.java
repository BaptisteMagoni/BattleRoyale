package net.zastax.Arena.commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.zastax.Main;

public class registerCommand implements CommandExecutor {

	private Main main;
	private HashMap<String, CommandBatlle> commands;
	 
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public registerCommand(Main main) {
		this.main = main;
		commands = new HashMap();
		loadCommands();
	}

	public void loadCommands() {
		commands.put("join", new cmdJoin(main));
		commands.put("setspawn", new cmdSetSpawn(main));
		commands.put("show", new cmdShow(main));
		commands.put("stop", new cmdStop(main));
		commands.put("setchest", new cmdChest(main));
		commands.put("refillchest", new cmdRefillChest(main));
		commands.put("setlobby", new cmdSetLobby(main));
		commands.put("giveAxe", new cmdGiveAxe(main));
		commands.put("update", new cmdUpdate(main));
		commands.put("quit", new cmdQuit(main));
		commands.put("menu", new cmdMenu(main));
		commands.put("setworldborder", new cmdWorldBorder(main));
		commands.put("create", new cmdCreate(main));
		commands.put("setbreaklimite", new cmdBreakLimite(main));
		commands.put("killall", new cmdKillEntities(main));
		commands.put("setmaxplayerteam", new cmdSetMaxPlayerTeam(main));
	}
	
	@SuppressWarnings("unchecked")
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
	    Player player = null;
	    if ((sender instanceof Player))
	    {
	      player = (Player)sender;
	    }
	    else
	    {
	      sender.sendMessage("You need to be a player !");
	      return true;
	    }
	    if (cmd.getName().equalsIgnoreCase("br"))
	    {
	      if ((args == null) || (args.length < 1))
	      {
	        player.sendMessage(ChatColor.YELLOW + "Plugin By ZasTax !" + " Version: Beta 1");
	        return true;
	      }
	      if (args[0].equalsIgnoreCase("help"))
	      {
	        help(player);
	        return true;
	      }
	      
	      String sub = args[0];
	      
	      @SuppressWarnings("rawtypes")
	      Vector<String> l = new Vector();
	      l.addAll(Arrays.asList(args));
	      l.remove(0);
	      args = (String[])l.toArray(new String[0]);
	      if (!this.commands.containsKey(sub))
	      {
	        player.sendMessage(ChatColor.RED + "Cette commande n'éxiste pas !");
	        player.sendMessage(ChatColor.GOLD + "/br help pour une aide");
	        return true;
	      }
	      try
	      {
	        ((CommandBatlle)this.commands.get(sub)).onCommand(player, cmd, commandLabel, args);
	      }
	      catch (Exception e)
	      {
	        e.printStackTrace();
	        
	        player.sendMessage(ChatColor.RED + "An error occured while executing the command. Check the console");
	        player.sendMessage(ChatColor.BLUE + "/br help pour une aide");
	      }
	      return true;
	    }
	    return true;
	}
	  
	public void help(Player p)
	{
		p.sendMessage("/br <command> <args>");
	    for (CommandBatlle v : this.commands.values()) {
	    	p.sendMessage(ChatColor.GRAY + "- " + v.help(p));
	    }
	}
}

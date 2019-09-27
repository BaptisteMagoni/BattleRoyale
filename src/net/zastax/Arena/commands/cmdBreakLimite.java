package net.zastax.Arena.commands;

import net.zastax.Arena.Arena;
import net.zastax.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdBreakLimite
  implements CommandBatlle
{
  private Main main;
  
  public cmdBreakLimite(Main main)
  {
    this.main = main;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args)
  {
    Player player = (Player)sender;
    String arenaName = args[0];
    if (this.main.getArenaManager().ArenaExist(arenaName))
    {
      Arena arena = this.main.getArenaManager().getArena(arenaName);
      int y = (int)player.getLocation().getY();
      this.main.getArenaConfig().set("Arena." + arena.getName() + ".BreakLimite", Double.valueOf(player.getLocation().getY()));
      this.main.saveAreneConfig();
      player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "Battle Royale" + ChatColor.GRAY + "] " + ChatColor.GOLD + "Vous avez mit à jour le niveau de construction à " + ChatColor.GRAY + y + ChatColor.GOLD + " de l'arène " + ChatColor.GRAY + arena.getName() + ChatColor.GOLD + " !");
    }
    return false;
  }
  
  public String help(Player paramPlayer)
  {
    return "/br setbreaklimite <NomDeL'Arène>";
  }
  
  public String getPermission()
  {
    return null;
  }
}

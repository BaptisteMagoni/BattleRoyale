package net.zastax.Title;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_13_R2.IChatBaseComponent;
import net.minecraft.server.v1_13_R2.PacketPlayOutChat;
import net.minecraft.server.v1_13_R2.PacketPlayOutTitle;

public class Title
{
  public static void sendTitle(Player player, String title, String subTitle, int ticks)
  {
    IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
    IChatBaseComponent chatsubTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subTitle + "\"}");
    
    PacketPlayOutTitle titre = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, chatTitle);
    PacketPlayOutTitle soustitre = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, chatsubTitle);
    
    ((CraftPlayer)player).getHandle().playerConnection.sendPacket(titre);
    ((CraftPlayer)player).getHandle().playerConnection.sendPacket(soustitre);
    
    sendTime(player, ticks);
  }
  
  private static void sendTime(Player player, int ticks)
  {
    PacketPlayOutTitle p = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, 20, ticks, 20);
    ((CraftPlayer)player).getHandle().playerConnection.sendPacket(p);
  }
  
  public void sendActionbar(Player p, String message) {
      IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + ChatColor.translateAlternateColorCodes('&', message) + "\"}");
      PacketPlayOutChat bar = new PacketPlayOutChat(icbc);
      ((CraftPlayer)p).getHandle().playerConnection.sendPacket(bar);
  }
}

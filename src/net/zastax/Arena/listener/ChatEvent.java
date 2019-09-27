package net.zastax.Arena.listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.zastax.Main;
import net.zastax.Arena.Arena;
import net.zastax.Arena.player.CPlayer;
import net.zastax.Arena.player.PlayerState;

public class ChatEvent implements Listener {

	private Main main;
	
	public ChatEvent(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e){
		Player player = e.getPlayer();
		if(main.getArenaManager().playerIsInArena(player)){
			for(Arena arena : main.getArenaManager().getArenaList()){
				for(Player pls : arena.getListPlayer()){
					if(player.getName().equals(pls.getName())){
						if(e.getMessage() != null){
							e.setCancelled(true);
							player.sendMessage(ChatColor.RED + "[Battle Royale] Vous ne pouvez pas parler durant la partie !");
						}
					}
				}
			}
		}else{
			for(CPlayer cp : main.getListPlayer()){
				if(cp.getPlayerState().equals(PlayerState.NOTINGAME)){
					e.setCancelled(true);
					cp.getPlayer().sendMessage(ChatColor.GRAY + "[Lobby] " + player.getName() + " : " + e.getMessage());
				}
			}
		}
	}

}

package net.zastax.Arena.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import net.md_5.bungee.api.ChatColor;
import net.zastax.Main;
import net.zastax.Arena.Arena;
import net.zastax.Arena.player.CPlayer;
import net.zastax.Arena.player.PlayerState;

public class BreakEvent implements Listener {

	private Main main;
	
	public BreakEvent(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e){
		Player player = e.getPlayer();
		if(main.getArenaManager().playerIsInArena(player)){
			for(Arena arena : main.getArenaManager().getArenaList()){
				if(arena.getListPlayer().contains(player)){
					for(Player pls : arena.getListPlayer()){
						int y = main.getArenaConfig().getInt("Arena." + arena.getName() + ".BreakLimite");
						if(pls.getLocation().getY() <= y || pls.getLocation().getY() >= 93.0){
							e.setCancelled(true);
							pls.sendMessage(ChatColor.RED + "[Battle Royale] Vous ne pouvez pas casser de block !");
							return;
						}else{
							e.setCancelled(false);
							return;
						}
					}
				}
			}
			for(CPlayer cp : main.getListPlayer()){
				if(cp.getName().equals(player.getName())){
					if(cp.getPlayerState().equals(PlayerState.NOTINGAME) || !player.isOp()){
						e.setCancelled(true);
						player.sendMessage(ChatColor.RED + "[Battle Royale] Vous ne pouvez pas casser de block !");
					}else{
						e.setCancelled(false);
					}
				}
			}
		}else{
			if(player.isOp())
				e.setCancelled(false);
			else
				e.setCancelled(true);
		}
	}

}

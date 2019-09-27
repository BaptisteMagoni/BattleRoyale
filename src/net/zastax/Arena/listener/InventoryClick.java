package net.zastax.Arena.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import net.zastax.Main;
import net.zastax.Arena.Team.GroupTeam;
import net.zastax.Arena.inventory.InventoryMain;
import net.zastax.Arena.ArenaType;
import net.zastax.Arena.Team.ArenaTeam;

public class InventoryClick implements Listener {

	private Main main;
	public InventoryClick(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e){
		InventoryMain inv = (InventoryMain) main.getMapInventory().get("main");
		if(e.getInventory().getTitle().equals("§2Choisissez une arène") || e.getInventory().getTitle().equals("§cChoisissez une équipe") 
		|| e.getInventory().getTitle().equals("§cMenu des arènes")){
			Player player = (Player) e.getWhoClicked();
			ItemStack itemClick = e.getCurrentItem();
			if(itemClick != null){
				switch(itemClick.getType()){
				case DIAMOND_HOE: main.getArenaManager().join(itemClick.getItemMeta().getDisplayName().replace(ChatColor.BLUE + "", ""), player);
					break;
				case ACACIA_DOOR: player.openInventory(main.getMapInventory().get("arenachoice").getInventory());
					break;
				case BARRIER:
					if(player.getGameMode().equals(GameMode.SPECTATOR)){
						try{
							Player targetPlayer = Bukkit.getPlayer(itemClick.getItemMeta().getDisplayName());
							player.teleport(targetPlayer.getLocation());
							player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "BattleRoyale" + ChatColor.GRAY + "]" + ChatColor.GOLD + " Vous avez été téléporté sur " + targetPlayer.getName());
						}catch(Exception err){
							player.sendMessage(ChatColor.RED + "[BattleRoyale] Ce joueur n'éxiste pas ou à été éliminé de la partie !");
						}
					}
					player.closeInventory();
					break;
				case BOW:
					inv.setArenaType(ArenaType.SOLO);
					inv.create();
					player.openInventory(inv.getInventory());
					break;
				case ARROW:
					inv.setArenaType(ArenaType.TEAM);
					inv.create();
					player.openInventory(inv.getInventory());
					break;
				default:
					for(GroupTeam gt : GroupTeam.values()){
						if(itemClick.getType().equals(gt.getMaterial())){
							gt.getTeam().setArena(main.getArenaManager().getArenaByPlayer(player));
							ArenaTeam team = playerGetTeam(player);
							if(team == null)
								gt.addPlayer(player);
							else{
								team.remove(player);
								gt.addPlayer(player);
							}
						}
					}
					break;
				}
			}
			e.setCancelled(true);
		}
	}
	
	private ArenaTeam playerGetTeam(Player player){
		for(GroupTeam gt : GroupTeam.values())
			if(gt.getTeam().getPlayers().contains(player))
				return gt.getTeam();
		return null;
	}
	
}

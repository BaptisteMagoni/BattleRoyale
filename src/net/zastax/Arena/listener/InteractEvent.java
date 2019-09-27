package net.zastax.Arena.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.zastax.Main;
import net.zastax.Arena.Arena;
import net.zastax.Arena.GameState;
import net.zastax.Arena.gun.GunType;
import net.zastax.Arena.gun.KitGun;
import net.zastax.Arena.player.CPlayer;
import net.zastax.Arena.player.PlayerState;

public class InteractEvent implements Listener {

	private Main main;
	
	public InteractEvent(Main main) {
		this.main = main;
	}
	
	@SuppressWarnings("unchecked")
	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		Player player = e.getPlayer();
		ItemStack item = e.getItem();
		Arena arena = main.getArenaManager().getArenaByPlayer(player);
		for(KitGun gun : main.getKitGunManager().getKitGun()){
			try{
				if(main.getArenaManager().ArenaExist(arena.getName()) &&  arena.getGameState().equals(GameState.GAME)){
					if(item.getItemMeta().getDisplayName().equals(gun.getName()) ){
						if(gun.getGunType().equals(GunType.GUN)){
							if(item.getType().equals(gun.getMaterial())){
								if(e.getAction() == Action.LEFT_CLICK_AIR){
									if(player.getInventory().contains(gun.getMaterialProjectile())){
										player.launchProjectile(gun.getProjectile());
										player.getInventory().removeItem(main.getItem(gun.getMaterialProjectile(), gun.getNameMunition()));
										player.updateInventory();
									}else
										player.sendMessage(ChatColor.RED + "[BattleRoyale] Vous avez 0 projectile pour le " + ChatColor.GRAY + gun.getName() + ChatColor.RED + " !");
								}
								if(e.getAction() == Action.RIGHT_CLICK_AIR){
									if(gun.getCanZoom()){
										if(player.hasPotionEffect(PotionEffectType.SLOW)){
											player.removePotionEffect(PotionEffectType.SLOW);
										}else{
											player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 99999, 20));
										}
									}else
										player.sendMessage(ChatColor.RED + "[BattleRoyale] Vous ne pouvez pas visez avec un " + ChatColor.GRAY + gun.getName() + ChatColor.RED + " !");
								}
							}
						}else if(gun.getGunType().equals(GunType.GRENADE)){
							if(item != null && item.getType().equals(gun.getMaterial())){
								if(e.getAction().equals(Action.LEFT_CLICK_AIR)){
									player.getInventory().removeItem(new ItemStack(main.getItem(gun.getMaterial(), gun.getName())));
									player.updateInventory();
									final Item ball = player.getWorld().dropItem(player.getLocation(), new ItemStack(gun.getMaterial(), 1));
									ball.setVelocity(player.getEyeLocation().getDirection());
									Bukkit.getScheduler().runTaskLater(main, new Runnable(){
			
										@Override
										public void run() {
											ball.getWorld().createExplosion(ball.getLocation(), (float) gun.getDamage());
											ball.getWorld().playEffect(ball.getLocation(), Effect.SMOKE, 30);
											ball.remove();
										}
										
									}, 80);
								}else
									player.sendMessage(ChatColor.RED + "[Battle Royale] Pour lancer la grenade Cliquer Gauche !");
							}
						}
					}
				}
			}catch(Exception err){
				
			}
		}
		if(e.getAction().equals(Action.LEFT_CLICK_BLOCK)){
			if(item != null){
				if(item.getType().equals(Material.DIAMOND_AXE) && item.getItemMeta().getDisplayName().equals("§eBattleRoyale Area")){
					Block block = e.getClickedBlock();
					player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "BattleRoyale" + ChatColor.GRAY + "]" + ChatColor.GOLD + " Sélection : x=" + block.getX() + ", y=" + block.getY() + ", z=" + block.getZ());
				}
			}
		}
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
			for(CPlayer cp : main.getListPlayer()){
				if(cp.getName().equals(player.getName())){
					if(cp.getPlayerState().equals(PlayerState.NOTINGAME)){
						if(item != null){ 
							if(item.getType().equals(Material.COMPASS) && item.getItemMeta().getDisplayName().equals("§cMenu des arènes")){
								main.getMapInventory().get("arenachoice").create();
								player.openInventory(main.getMapInventory().get("arenachoice").getInventory());
							}
						}
					}else if(cp.getPlayerState().equals(PlayerState.INGAME)){
						if(item != null){
							if(item.getType().equals(Material.NAME_TAG) && item.getItemMeta().getDisplayName().equals("§cChoisissez une équipe")){
								main.getMapInventory().get("team").create();
								player.openInventory(main.getMapInventory().get("team").getInventory());
							}
						}
					}
				}
			}
		}
	}
	
}

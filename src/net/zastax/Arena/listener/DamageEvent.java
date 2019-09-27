package net.zastax.Arena.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import net.zastax.Main;
import net.zastax.Arena.Arena;
import net.zastax.Arena.ArenaType;
import net.zastax.Arena.Team.GroupTeam;
import net.zastax.Arena.gun.KitGun;

public class DamageEvent implements Listener {

	private Main main;
	
	public DamageEvent(Main main) {
		this.main = main;
	}
	
	@SuppressWarnings({ "deprecation" })
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e){
		Player victim = Bukkit.getPlayer(e.getEntity().getName());
		if(!(e.getDamager() instanceof  Player)){
			for(KitGun gun : main.getKitGunManager().getKitGun()){
				if(e.getDamager() instanceof Snowball){
					Snowball s = (Snowball) e.getDamager();
					Player damager = (Player) s.getShooter();
					if(!damagerIsFriendWithVictime(damager, victim)){
						if(damager instanceof Player){
							Player shooter = (Player) s.getShooter();
							if(shooter.getItemInHand().getType().equals(gun.getMaterial())){
								e.setCancelled(false);
								e.setDamage(gun.getDamage());
							}
						}
					}else{
						e.getDamager().sendMessage(ChatColor.RED + "[BattleRoyale] Vous ne pouvez pas attaqué un coéquipié !");
						e.setCancelled(true);
					}
					}else if(e.getDamager() instanceof Egg){
						Egg s = (Egg) e.getDamager();
						Player damager = (Player) s.getShooter();
						if(!damagerIsFriendWithVictime(damager, victim)){
							if(damager instanceof Player){
								Player shooter = (Player) s.getShooter();
								if(shooter.getItemInHand().getType().equals(gun.getMaterial())){
									e.setCancelled(false);
									e.setDamage(gun.getDamage());
									s.getWorld().playEffect(damager.getLocation(), Effect.SMOKE, 10);
								}
							}
						}else{
							e.getDamager().sendMessage(ChatColor.RED + "[BattleRoyale] Vous ne pouvez pas attaqué un coéquipié !");
							e.setCancelled(true);
						}
					}else if(e.getDamager() instanceof Fireball){
						Fireball s = (Fireball) e.getDamager();
						Player damager = (Player) s.getShooter();
						if(!damagerIsFriendWithVictime(damager, victim)){
							if(damager instanceof Player){
								Player shooter = (Player) s.getShooter();
								if(shooter.getItemInHand().getType().equals(gun.getMaterial())){
									e.setDamage(gun.getDamage());
									e.setCancelled(false);
									s.getWorld().playEffect(damager.getLocation(), Effect.SMOKE, 30);
								}
							}
						}else{
							e.getDamager().sendMessage(ChatColor.RED + "[BattleRoyale] Vous ne pouvez pas attaqué un coéquipié !");
							e.setCancelled(true);
						}
					}else if(e.getDamager() instanceof Arrow){
						Arrow s = (Arrow) e.getDamager();
						Player damager = (Player) s.getShooter();
						System.out.println("Arrow");
						if(!damagerIsFriendWithVictime(damager, victim)){
							if(damager instanceof Player){
								Player shooter = (Player) s.getShooter();
								if(shooter.getItemInHand().getType().equals(gun.getMaterial())){
									e.setCancelled(false);
									e.setDamage(gun.getDamage());
									s.setVelocity(damager.getLocation().getDirection().multiply(1.8));
									s.getWorld().playEffect(damager.getLocation(), Effect.SMOKE, 5);
								}
							}
						}else{
							e.getDamager().sendMessage(ChatColor.RED + "[BattleRoyale] Vous ne pouvez pas attaqué un coéquipié !");
							e.setCancelled(true);
						}
					}
				}
		}else{
			Player damager = (Player) e.getDamager();
			if(!damagerIsFriendWithVictime(damager, victim)){
				if(main.getArenaManager().playerIsInArena(damager)){
					e.setCancelled(false);
				}else{
					e.setCancelled(true);
					damager.sendMessage(ChatColor.RED + "[BattleRoyale] Vous avez pas la permission de faire cette action !");
				}	
			}else{
				e.getDamager().sendMessage(ChatColor.RED + "[BattleRoyale] Vous ne pouvez pas attaqué un coéquipié !");
				e.setCancelled(true);
			}
		}
	}
	
	private boolean damagerIsFriendWithVictime(Player damager, Player victim){
		Arena arena = main.getArenaManager().getArenaByPlayer(damager);
		if(arena != null){
			if(arena.getArenaType().equals(ArenaType.SOLO))
				return false;
			else
				for(GroupTeam gt : GroupTeam.values())
					if(gt.getTeam().getPlayers().contains(damager) && gt.getTeam().getPlayers().contains(victim))
						return true;
					else if(gt.getTeam().getPlayers().contains(damager) && !gt.getTeam().getPlayers().contains(victim))
						return false;
		}
		return false;
	}
	
}

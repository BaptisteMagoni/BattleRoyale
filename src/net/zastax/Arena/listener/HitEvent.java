package net.zastax.Arena.listener;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.meta.FireworkMeta;

import net.zastax.Main;

public class HitEvent implements Listener {

	@SuppressWarnings("unused")
	private Main main;
	
	public HitEvent(Main main) {
		this.main = main;
	}

	@EventHandler
	public void onHit(ProjectileHitEvent e){
		if (e.getEntity() instanceof Arrow) {
            Arrow arrow = (Arrow) e.getEntity();
            Location loc = arrow.getLocation();
            World world = arrow.getWorld();
            
            @SuppressWarnings("unused")
			EntityType entity = e.getEntityType();
            entity = EntityType.FIREWORK;
            world.spawn(loc, Firework.class);
            Firework fw = (Firework) arrow.getWorld().spawn(arrow.getLocation(), Firework.class);
            FireworkMeta fm = fw.getFireworkMeta();
            fm.addEffect(FireworkEffect.builder().flicker(false).trail(true).with(Type.BALL_LARGE).withColor(Color.TEAL).build());
            fm.setPower(1);
            fw.setFireworkMeta(fm);
		}
	}
	
}

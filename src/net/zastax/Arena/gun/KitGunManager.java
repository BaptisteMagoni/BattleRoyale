package net.zastax.Arena.gun;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Blaze;

public class KitGunManager {

	private ArrayList<KitGun> listGuns = new ArrayList<KitGun>();
	
	public KitGunManager(){
		listGuns.add(new KitGun("AK-47", Material.IRON_HOE, Material.SNOWBALL, Snowball.class, 40, true, 1, 0, "Munition AK-47", "Cliquer Droit pour viser - Cliquer Gauche pour tirer", GunType.GUN));
		listGuns.add(new KitGun("Pistolet", Material.STONE_HOE, Material.EGG, Egg.class, 20, true, 1, 0, "Munition Pistolet", "Cliquer Droit pour viser - Cliquer Gauche pour tirer", GunType.GUN));
		listGuns.add(new KitGun("Sniper", Material.GOLDEN_HOE, Material.GUNPOWDER, Arrow.class, 20, true, 1, 0, "Munition Sniper", "Cliquer Droit pour viser - Cliquer Gauche pour tirer", GunType.GUN));
		listGuns.add(new KitGun("Bazooka", Material.DIAMOND_HOE, Material.MAGMA_CREAM, Fireball.class, 200, false, 1, 0, "Munition Bazooka", "Cliquer Gauche pour tirer", GunType.GUN));
		listGuns.add(new KitGun("Lance Grenade", Material.FISHING_ROD, Material.BLAZE_POWDER, Blaze.class, 70, false, 1, 0, "Munition Lance Grenade", "Cliquer Droit pour viser - Cliquer Gauche pour tirer", GunType.GUN));
		
		listGuns.add(new KitGun("Grenade", Material.SLIME_BALL, 5, false, 1, 0, "Cliquer gauche pour lancer la Grenade", GunType.GRENADE));
		listGuns.add(new KitGun("Dynamite", Material.TNT, 10, false, 1, 0, "Cliquer gauche pour lancer la Dynamite", GunType.GRENADE));
		
		listGuns.add(new KitGun("Munition AK-47", Material.SNOWBALL, 8, 0, GunType.MUNITION));
		listGuns.add(new KitGun("Munition Pistolet", Material.EGG, 10, 0, GunType.MUNITION));
		listGuns.add(new KitGun("Munition Bazooka", Material.MAGMA_CREAM, 6, 0, GunType.MUNITION));
		listGuns.add(new KitGun("Munition Sniper", Material.GUNPOWDER, 5, 0, GunType.MUNITION));
		listGuns.add(new KitGun("Munition Lance Grenade", Material.BLAZE_POWDER, 5, 0, GunType.MUNITION));
	}
	
	public ArrayList<KitGun> getKitGun(){
		return listGuns;
	}
	
}

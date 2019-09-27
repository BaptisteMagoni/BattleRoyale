package net.zastax;

import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class Damager {

	private DamageCause m_dc;
	private String m_deathMessage;
	
	public Damager(DamageCause dc, String deathMessage){
		this.m_dc = dc;
		this.m_deathMessage = deathMessage;
	}
	
	public DamageCause getDamageCause(){
		return m_dc;
	}
	
	public String getDeathMessage(){
		return m_deathMessage;
	}
	
}

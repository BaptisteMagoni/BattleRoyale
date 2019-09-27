package net.zastax.Arena.gun;

import org.bukkit.Material;

public class KitGun {
	
	private String m_name;
	private String m_nameMunition;
	private Material m_material;
	private Material m_materialP;
	@SuppressWarnings("rawtypes")
	private Class m_projectile;
	private int m_damage;
	private boolean m_zoom;
	private GunType m_type;
	private int m_amount;
	private int m_data;
	private String m_explanationMessage;
	
	@SuppressWarnings("rawtypes")
	public KitGun(String name, Material material, Material materialP, Class projectile, int damage, boolean zoom, int amount, int data, String nameMunition, GunType type){
		this.m_name = name;
		this.m_material = material;
		this.m_materialP = materialP;
		this.m_projectile = projectile;
		this.m_damage = damage;
		this.m_zoom = zoom;
		this.m_type = type;
		this.m_amount = amount;
		this.m_data = data;
		this.m_nameMunition = nameMunition;
		this.m_explanationMessage = "";
	}
	
	@SuppressWarnings("rawtypes")
	public KitGun(String name, Material material, Material materialP, Class projectile, int damage, boolean zoom, int amount, int data, String nameMunition, String explanationMessage, GunType type){
		this.m_name = name;
		this.m_material = material;
		this.m_materialP = materialP;
		this.m_projectile = projectile;
		this.m_damage = damage;
		this.m_zoom = zoom;
		this.m_type = type;
		this.m_amount = amount;
		this.m_data = data;
		this.m_nameMunition = nameMunition;
		this.m_explanationMessage = explanationMessage;
	}
	
	public KitGun(String name, Material material, int damage, boolean zoom, int amount, int data, String explanationMessage, GunType type){
		this.m_name = name;
		this.m_material = material;
		this.m_damage = damage;
		this.m_zoom = zoom;
		this.m_type = type;
		this.m_amount = amount;
		this.m_data = data;
		this.m_explanationMessage = explanationMessage;
	}
	
	public KitGun(String name, Material material, int damage, boolean zoom, int amount, int data, GunType type){
		this.m_name = name;
		this.m_material = material;
		this.m_damage = damage;
		this.m_zoom = zoom;
		this.m_type = type;
		this.m_amount = amount;
		this.m_data = data;
		this.m_explanationMessage = "";
	}
	
	public KitGun(String name, Material material, int amount, int data, GunType type){
		this.m_name = name;
		this.m_material = material;
		this.m_amount = amount;
		this.m_data = data;
	}
	
	public GunType getGunType(){
		return m_type;
	}
	
	public int getAmount(){
		return m_amount;
	}
	
	public int getData(){
		return m_data;
	}
	
	public String getName(){
		return m_name;
	}
	
	public String getNameMunition(){
		return m_nameMunition;
	}
	
	public String getExplanationMessage(){
		return m_explanationMessage;
	}
	
	public boolean getCanZoom(){
		return m_zoom;
	}
	
	public int getDamage(){
		return m_damage;
	}
	
	public Material getMaterial(){
		return m_material;
	}
	
	public Material getMaterialProjectile(){
		return m_materialP;
	}
	
	@SuppressWarnings("rawtypes")
	public Class getProjectile(){
		return m_projectile;
	}
}

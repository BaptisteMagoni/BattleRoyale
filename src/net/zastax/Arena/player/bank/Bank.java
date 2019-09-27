package net.zastax.Arena.player.bank;

import org.bukkit.entity.Player;

import net.zastax.Main;

public class Bank {

	private double m_money;
	private Main main;
	private Player m_player;
	
	public Bank(Main main, Player player) {
		this.main = main;
		this.m_player = player;
		m_money = this.getConfigMoney();
	}

	public void addMoney(double money){
		m_money += money;
		this.updateMoneyFile();
	}
	
	public void removeMoney(double money){
		m_money -= money;
		this.updateMoneyFile();
	}
	
	public double getMoney(){
		return m_money;
	}
	
	public void setMoney(double money){
		System.out.println("Change money of " + m_player.getName());
		m_money = money;
		System.out.println("Money of " + m_player.getName() + ": " + m_money);
	}
	
	private void updateMoneyFile(){
		main.getBankConfig().set("Player." + m_player.getName() + ".money", m_money);
		main.savePlayerConfig();
	}
	
	private double getConfigMoney(){
		return main.getBankConfig().getDouble("Player." + m_player.getName() + ".money");
	}
	
	public Player getPlayer(){
		return m_player;
	}
	
}

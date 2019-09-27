package net.zastax.Arena.modeGameArena;

import java.util.ArrayList;

import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;

import net.zastax.Main;
import net.zastax.Arena.GameState;
import net.zastax.Arena.area.BorderState;
import net.zastax.Arena.area.BorderWaveState;

public class Game {
	
	private int timer_move_border = 80;
	private int timer_stay_border = 180;
	private WorldBorder m_wb;
	private BorderWaveState m_state_now;
	private BorderState m_borderState;
	
	@SuppressWarnings("unused")
	private Main main;
	
	public Game(Main main, WorldBorder wb){
		this.main = main;
		this.m_wb = wb;
		m_borderState = BorderState.WaveStay;
		m_state_now = BorderWaveState.Wave1;
	}
	
	public GameState start(ArrayList<Player> listPlayers){
		/*if(listPlayers.size() == 1){
			return GameState.FINISH;
		}else{*/
			if(m_state_now.equals(BorderWaveState.Wave1))
				m_state_now = Wave(BorderWaveState.Wave1, BorderWaveState.Wave2);
			else if(m_state_now.equals(BorderWaveState.Wave2))
				m_state_now = Wave(BorderWaveState.Wave2, BorderWaveState.Wave3);
			else if(m_state_now.equals(BorderWaveState.Wave3))
				m_state_now = Wave(BorderWaveState.Wave3, BorderWaveState.Wave3);
			if(m_borderState.equals(BorderState.WaveMove))
				m_wb.setSize(m_wb.getSize() - 1);
			return GameState.GAME;
		//}
	}
	
	public int getTimer(){
		if(m_borderState.equals(BorderState.WaveMove))
			return timer_move_border;
		else
			return timer_stay_border;
	}
	
	public BorderState getBorderState(){
		return m_borderState;
	}
	
	private BorderWaveState Wave(BorderWaveState now, BorderWaveState after){
		if(timer_stay_border != 0){
			timer_stay_border--;
			m_borderState = BorderState.WaveStay;
		}else if(timer_stay_border == 0){
			timer_move_border--;
			m_borderState = BorderState.WaveMove;
			if(timer_move_border == 0){
				timer_move_border = 60;
				timer_stay_border = 180;
				return after;
			}
			
		}
		return now;
	}
	
}

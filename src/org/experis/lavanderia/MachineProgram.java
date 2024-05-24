package org.experis.lavanderia;

public abstract class MachineProgram {
	private int number;
	
	private String name;
	
	private int duration;
	
	private int coins;

	public MachineProgram(int number, String name, int duration, int coins) {
		this.number = number;
		this.name = name;
		this.duration = duration;
		this.coins = coins;
	}
	
	public int getCoins() {
		return coins;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public String getName() {
		return name;
	}

	public int getNumber() {
		return number;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [number=" + number + ", name=" + name + ", duration=" + duration + ", coins=" + coins
				+ "]";
	}
	
}

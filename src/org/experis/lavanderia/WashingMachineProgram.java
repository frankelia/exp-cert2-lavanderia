package org.experis.lavanderia;

public class WashingMachineProgram extends MachineProgram {

	private int conditioner;

	private int softener;
	
	public WashingMachineProgram(int number, String name, int duration, int coins, int conditioner, int softnener) {
		super(number, name, duration, coins);
		this.conditioner = conditioner;
		this.softener = softnener;
	}
	
	public int getConditioner() {
		return conditioner;
	}
	
	public int getSoftener() {
		return softener;
	}

	@Override
	public String toString() {
		return super.toString() + ", conditioner=" + conditioner + ", softener=" + softener + "]";
	}
	
	
}

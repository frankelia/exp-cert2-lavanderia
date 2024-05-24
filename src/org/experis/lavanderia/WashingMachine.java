package org.experis.lavanderia;

import java.util.ArrayList;
import java.util.Random;

import org.experis.lavanderia.exceptions.LavanderiaException;

public class WashingMachine extends Machine {
	private static final Random random = new Random();

	private Tank conditionerTank = new Tank(1000);
	
	private Tank softenerTank = new Tank(500);
	
	public WashingMachine(int id) {
		this(id, false);
	}
	
	public WashingMachine(int id, boolean generateRandom) {
		super(id, generateRandom);
		
		if (generateRandom) {
			try {
				conditionerTank.add(random.nextInt(300));
			} catch (LavanderiaException e) {
				throw new RuntimeException(e);
			}
			
			try {
				softenerTank.add(random.nextInt(300));
			} catch (LavanderiaException e) {
				throw new RuntimeException(e);
			}
		}
		
		programs = new ArrayList<MachineProgram>();
		programs.add(new WashingMachineProgram(1, "Rinfrescante", 20, 5, 20, 25));
		programs.add(new WashingMachineProgram(2, "Rinnovante", 40, 10, 40, 50));
		programs.add(new WashingMachineProgram(3, "Sgrassante", 60, 15, 60, 100));
	}

	public Tank getConditionerTank() {
		return conditionerTank;
	}
	
	public Tank getSoftenerTank() {
		return softenerTank;
	}

	@Override
	public String toString() {
		return super.toString() + " " + conditionerTank + " | " + softenerTank;
	}
	
	@Override
	public void start() throws LavanderiaException {
		super.start();
		
		WashingMachineProgram machineProgram = (WashingMachineProgram)programs.get(selectedProgram - 1);

		if (machineProgram.getConditioner() > conditionerTank.getCurrentLevel()) {
			this.active = false;
			this.addCoins(machineProgram.getCoins());
			this.startTime = null;
			throw new LavanderiaException("Detersivo insufficiente");
		}
		
		if (machineProgram.getSoftener() > softenerTank.getCurrentLevel()) {
			this.active = false;
			this.addCoins(machineProgram.getCoins());
			this.startTime = null;
			throw new LavanderiaException("Detersivo insufficiente");
		}
		
		this.conditionerTank.consume(machineProgram.getConditioner());
		this.softenerTank.consume(machineProgram.getSoftener());
	}
}

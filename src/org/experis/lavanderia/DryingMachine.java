package org.experis.lavanderia;

import java.util.ArrayList;

public class DryingMachine extends Machine {
	public DryingMachine(int id, boolean random) {
		super(id, random);
		programs = new ArrayList<MachineProgram>();
		programs.add(new DryingMachineProgram(1, "Rapido", 20, 2));
		programs.add(new DryingMachineProgram(2, "Intenso", 60, 4));
	}

}

package org.experis.lavanderia;

import java.util.Scanner;

import org.experis.lavanderia.exceptions.LavanderiaException;

public class LavanderiaMain {
	public static void main(String[] args) {
		Lavanderia lavanderia = new Lavanderia(3, 2);
		
		Scanner scanner = new Scanner(System.in);
		
		String command = null;
		do {
			lavanderia.printStatus();
			
			System.out.print("> ");
			command = scanner.nextLine(); 
			
			try {
				Command parsedCommand = Command.parse(command);
				
				Integer machineId = parsedCommand.getMachineId();
				if (machineId == null) {
					if (parsedCommand.getCommand().equalsIgnoreCase("exit")) {
						break;
					} else {
						throw new LavanderiaException("Comando non valido.");
					}
				}
				
				Machine machine = lavanderia.getMachineById(machineId);
				
				if (machine == null) {
					throw new LavanderiaException("Macchina non trovata");
				}
				
				if (parsedCommand.getCommand().equalsIgnoreCase("apri")) {
					machine.open();
				} else if (parsedCommand.getCommand().equalsIgnoreCase("chiudi")) {
					machine.close();
				} else if (parsedCommand.getCommand().equalsIgnoreCase("gettoni")) {
					machine.addCoins(parsedCommand.getAdditionalParam());
				} else if (parsedCommand.getCommand().equalsIgnoreCase("lista")) {
					for (MachineProgram p : machine.getPrograms()) {
						System.out.println(p);
					}
				} else if (parsedCommand.getCommand().equalsIgnoreCase("programma")) {
					machine.setSelectedProgram(parsedCommand.getAdditionalParam());
				} else if (parsedCommand.getCommand().equalsIgnoreCase("avvia")) {
					machine.start();
				} else if (parsedCommand.getCommand().equalsIgnoreCase("ferma")) {
					machine.stop();
				} else if (parsedCommand.getCommand().equalsIgnoreCase("detersivo")) {
//					if (machine instanceof WashingMachine) {
					try {
						WashingMachine w = (WashingMachine)machine;
						w.getConditionerTank().add(parsedCommand.getAdditionalParam());
					} catch (ClassCastException e) {
						throw new LavanderiaException("Il comando detersivo è disponibile solo per le lavatrici.");
					}
//					} else {
//						throw new LavanderiaException("Il comando detersivo è disponibile solo per le lavatrici.");
//					}
				} else if (parsedCommand.getCommand().equalsIgnoreCase("ammorbidente")) {
					try {
						WashingMachine w = (WashingMachine)machine;
						w.getSoftenerTank().add(parsedCommand.getAdditionalParam());
					} catch (ClassCastException e) {
						throw new LavanderiaException("Il comando detersivo è disponibile solo per le lavatrici.");
					}
				} else {
					throw new LavanderiaException("Comando non valido: " + command);
				}
				
				System.out.println("ULTIMA OPERAZIONE: Comando eseguito correttamente.");
			} catch (LavanderiaException e) {
				System.out.println("ERRORE ULTIMA OPERAZIONE: " + e.getMessage());
			}
			
			System.out.println("----------------------------------------------------------------");
			lavanderia.simulazione();
		} while (!command.equals("exit"));
		
		scanner.close();
		
	}
}

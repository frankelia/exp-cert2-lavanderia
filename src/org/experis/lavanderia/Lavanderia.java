package org.experis.lavanderia;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.experis.lavanderia.exceptions.LavanderiaException;

public class Lavanderia {
	private List<Machine> machines = new ArrayList<>();
	
	public Lavanderia(int numWashingMachine, int numDryingMachine) {
		for (int i = 0; i < numWashingMachine; i++) {
			WashingMachine washingMachine = new WashingMachine(i + 1, true);
			machines.add(washingMachine);
		}
		
		for (int i = 0; i < numDryingMachine; i++) {
			machines.add(new DryingMachine(i + 1 + numWashingMachine, true));
		}
	}
	
	
	public void simulazione() {
		for (Machine m : machines) {
			if (!m.isActive()) continue;
			
			long remainingSeconds = m.getRemainingSeconds();
			
			if (remainingSeconds <= 0) {
				try {
					m.setSelectedProgram(0);
				} catch (LavanderiaException e) {
					throw new RuntimeException(e);
				}
				m.stop();
			}
		}
	}
	
	public Machine getMachineById(int id) {
		for (Machine m : machines) {
			if (m.getId() == id)
				return m;
		}
		
		return null;
	}
	
	public List<Machine> getMachines() {
		return machines;
	}
	
	public void printStatus() {
		for (Machine m : machines) {
			System.out.println(m);
		}
	}
	
	public void printCommands() {
		System.out.println("   Comando             Descrizione                                                       Parametro aggiuntivo     \n"
				+ "   apri                apre lo sportello                                                 -   \n"
				+ "   chiudi              chiude lo sportello                                               -   \n"
				+ "   gettoni             inserisce il numero di gettoni nella macchina specificata         numero di gettoni positivo e maggiore di 0   \n"
				+ "   lista               fornisce la lista dei programmi                                   -   \n"
				+ "   programma           seleziona il programma specificato sulla macchina indicata        numero del programma\n"
				+ "   avvia               avvia il lavaggio o l’asciugatura sulla macchina specificata      -\n"
				+ "   ferma               ferma il lavaggio o l’asciugatura sulla macchina specificata      -\n"
				+ "   detersivo           ricarica il detersivo sulla macchina specificata                  quantità di detersivo   \n"
				+ "   ammorbidente        ricarica l'ammorbidente sulla macchina specificata                quantità di ammorbidente   ");
	}
}

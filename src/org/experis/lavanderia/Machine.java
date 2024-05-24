package org.experis.lavanderia;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.experis.lavanderia.exceptions.LavanderiaException;

public abstract class Machine {
	private static final Random random = new Random();
	
	private int id;
	
	private int coins;
	
	private boolean open;
	
	protected List<MachineProgram> programs;
	
	protected int selectedProgram;
	
	protected boolean active;
	
	protected LocalDateTime startTime;
	
	public Machine(int id, boolean generateRandom) {
		this.id = id;
		
		if (generateRandom) {
			coins = 20 + random.nextInt(100);
			open = false;
		}
	}
	
	public int getId() {
		return id;
	}
	
	public int getCoins() {
		return coins;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + " [id=" + id + ", coins=" + coins + ", open=" + open + ", "
				+ ", selectedProgram=" + selectedProgram + ", active=" + active + ", startTime=" + startTime + ", s=" + getRemainingSeconds()
				+ "]";
	}

	public void close() {
		this.open = false;
	}
	
	public void open() {
		this.open = true;
	}
	
	public boolean isOpen() {
		return open;
	}
	
	public LocalDateTime getStartTime() {
		return startTime;
	}
	
	public void addCoins(int coins) throws LavanderiaException {
		if (coins < 0)
			throw new LavanderiaException("Numero di gettoni negativo");
		
		this.coins += coins;
	}
	
	public List<MachineProgram> getPrograms() {
		return programs;
	}
	
	public int getSelectedProgram() {
		return selectedProgram;
	}
	
	public void setSelectedProgram(int selectedProgram) throws LavanderiaException {
		if (selectedProgram > programs.size())
			throw new LavanderiaException("Programma selezionato non valido: " + selectedProgram);
		this.selectedProgram = selectedProgram;
	}
	
	public long getRemainingSeconds() {
		if (active) {
			Duration between = Duration.between(getStartTime(), LocalDateTime.now());
			long expiredSeconds = between.getSeconds() * 60;
			return programs.get(selectedProgram - 1).getDuration() * 60 - expiredSeconds;
		} else {
			return 0;
		}
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void start() throws LavanderiaException {
		if (selectedProgram == 0) {
			throw new LavanderiaException("Nessun programma selezionato.");
		}
		
		if (isOpen())
			throw new LavanderiaException("Impossibile avviare con sportello aperto.");
		
		MachineProgram machineProgram = programs.get(selectedProgram - 1);
		if (machineProgram.getCoins() > getCoins()) {
			throw new LavanderiaException("Gettoni insufficienti");
		}
		
		this.coins -= machineProgram.getCoins();
		this.active = true;
		this.startTime = LocalDateTime.now();
	}
	
	public void stop() {
		this.active = false;
		this.startTime = null;
	}
}

package org.experis.lavanderia;

import java.util.HashSet;
import java.util.Set;

import org.experis.lavanderia.exceptions.LavanderiaException;

public class Command {
	private Integer machineId;
	
	private String command;
	
	private Integer additionalParam;
	
	public Command(Integer machineId, String command, Integer additionalParam) {
		this.machineId = machineId;
		this.command = command;
		this.additionalParam = additionalParam;
	}
	
	public Integer getAdditionalParam() {
		return additionalParam;
	}
	
	public Integer getMachineId() {
		return machineId;
	}
	
	public String getCommand() {
		return command;
	}
	
	public static Command parse(String commandString) throws LavanderiaException {
		Set<String> command3Args = new HashSet<>();
		command3Args.add("gettoni");
		command3Args.add("detersivo");
		command3Args.add("ammorbidente");
		command3Args.add("programma");

		String[] tokens = commandString.split(" ");
		
		if (tokens.length == 0 || tokens.length > 3) {
			throw new LavanderiaException("Comando non valido");
		}
		
		String commandName = tokens[0];
		
		Integer machineId = null;
		if (tokens.length >= 2) {
			machineId = Integer.parseInt(tokens[1]);
		}
		
		Integer additionalParam = null;
		if (tokens.length >= 3) {
			additionalParam = Integer.parseInt(tokens[2]);
		}
		
		if (command3Args.contains(commandName) && tokens.length != 3) 
			throw new LavanderiaException("Comando non valido: necessario argomento aggiuntivo.");
		
		return new Command(machineId, commandName, additionalParam);
	}
}

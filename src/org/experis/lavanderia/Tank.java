package org.experis.lavanderia;

import org.experis.lavanderia.exceptions.LavanderiaException;

public class Tank {
	private int maxCapacity;
	
	private int currentLevel;
	
	public Tank(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
	
	public int getMaxCapacity() {
		return maxCapacity;
	}
	
	public int getCurrentLevel() {
		return currentLevel;
	}
	
	public void add(int q) throws LavanderiaException {
		if (q < 0)
			throw new LavanderiaException("La quantità deve essere positiva.");
		
		if (currentLevel + q > maxCapacity)
			throw new LavanderiaException("La quantità è eccessiva rispetto alla capacità.");
		
		currentLevel += q;
	}

	public void consume(int q) throws LavanderiaException {
		if (q < 0)
			throw new LavanderiaException("La quantità deve essere positiva.");
		
		currentLevel -= q;
	}
	
	@Override
	public String toString() {
		return "Tank [maxCapacity=" + maxCapacity + ", currentLevel=" + currentLevel + "]";
	}
	
}

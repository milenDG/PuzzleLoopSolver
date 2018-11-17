package org.board_classes.enums;


public enum CellState {
	Empty(4), Zero(0), One(1), Two(2), Three(3);
	
	private int numberValue = -1;
	private CellState(int cellNumber){
		this.numberValue = cellNumber;
	}
	
	public int getNumberValue() {
		return this.numberValue;
	}
}

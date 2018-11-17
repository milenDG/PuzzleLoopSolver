package org.board_classes.enums;

public enum LineState {
	Connected(1), Disconnected(0), Forbiden(-1);
	
	private int numberValue = 0;
	
	private LineState(int numberValue) {
		this.numberValue = numberValue;
	}
	
	public int getNumberValue() {
		return this.numberValue;
	}
}

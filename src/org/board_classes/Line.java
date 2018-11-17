package org.board_classes;

import org.board_classes.enums.LineState;

public class Line {
	private LineState lineState = LineState.Disconnected;
	private boolean isVertical = true;
	private int weight = 0;
	public Line(boolean isVertical) {
		this.lineState = LineState.Disconnected;
		this.isVertical = isVertical;
		this.weight = 0;
	}
	public void connect() {
		this.lineState = LineState.Connected;
	}
	public void disconnect() {
		this.lineState = LineState.Disconnected;
	}
	public void forbid() {
		this.lineState = LineState.Forbiden;
	}
	public LineState getLineState() {
		return this.lineState;
	}
	public int getWeight() {
		return this.weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public void printOnConsole() {
		if(this.getLineState() == LineState.Connected) {
			
			if(this.isVertical) {
				System.out.print("│");
			}
			else {
				System.out.print("———");
			}
			
		}
		else if(this.getLineState() == LineState.Forbiden) {

			if(this.isVertical) {
				System.out.print("x");
			}
			else {
				System.out.print(" x ");
			}
		}
		else if(this.getLineState() == LineState.Disconnected) {
			if(this.isVertical) {
				System.out.print(" ");
			}
			else {
				System.out.print("   ");
			}
		}
	}
}

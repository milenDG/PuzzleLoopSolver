package org.board_classes;

import org.board_classes.enums.CellState;

public class Cell {
	private Line topLine = null;
	private Line bottomLine = null;
	private Line leftLine = null;
	private Line rightLine = null;
	private Dot topLeft = null;
	private Dot topRight = null;
	private Dot bottomLeft = null;
	private Dot bottomRight = null;
	private CellState cellState = CellState.Empty;
	private boolean isReady = false;
	
	public Cell(int cellNumber,Line topLine, Line bottomLine, Line leftLine, Line rightLine) throws Exception {
		switch(cellNumber) {
			case 4: this.cellState = CellState.Empty; break;
			case 0: this.cellState = CellState.Zero; break;
			case 1: this.cellState = CellState.One; break;
			case 2: this.cellState = CellState.Two; break;
			case 3: this.cellState = CellState.Three; break;
			default: throw new Exception("Wrong cell number!!!");
		}
		this.topLine = topLine;
		this.bottomLine = bottomLine;
		this.leftLine = leftLine;
		this.rightLine = rightLine;
		this.isReady = false;
	}
	
	public void initialiseDots(	Dot topLeft, Dot topRight, Dot bottomLeft, Dot bottomRight) {
		this.topLeft = topLeft;
		this.topRight = topRight;
		this.bottomLeft = bottomLeft;
		this.bottomRight = bottomRight;
	}
	public void connectTopLine() {
		this.topLine.connect();
	}
	public void connectBottomLine() {
		this.bottomLine.connect();
	}
	public void connectLeftLine() {
		this.leftLine.connect();
	}
	public void connectRightLine() {
		this.rightLine.connect();
	}
	
	
	public void disconnectTopLine() {
		this.topLine.disconnect();
	}
	public void disconnectBottomLine() {
		this.bottomLine.disconnect();
	}
	public void disconnectLeftLine() {
		this.leftLine.disconnect();
	}
	public void disconnectRightLine() {
		this.rightLine.disconnect();
	}
	
	
	public void forbidTopLine() {
		this.topLine.forbid();
	}
	public void forbidBottomLine() {
		this.bottomLine.forbid();
	}
	public void forbidLeftLine() {
		this.leftLine.forbid();
	}
	public void forbidRightLine() {
		this.rightLine.forbid();
	}
	
	
	public Line getTopLine() {
		return this.topLine;
	}
	public Line getBottomLine() {
		return this.bottomLine;
	}
	public Line getLeftLine() {
		return this.leftLine;
	}
	public Line getRightLine() {
		return this.rightLine;
	}
	
	
	public Dot getTopLeft() {
		return this.topLeft;
	}
	public Dot getTopRight() {
		return this.topRight;
	}
	public Dot getBottomLeft() {
		return this.bottomLeft;
	}
	public Dot getBottomRight() {
		return this.bottomRight;
	}


	public boolean isReady() {
		return this.isReady;
	}
	public void setReady() {
		this.isReady = true;
	}
	
	public int getCellState() {
		return cellState.getNumberValue();
	}
	
	public static Cell turn90(Cell cell) throws Exception {
		Line top = cell.leftLine, bottom = cell.rightLine, left = cell.bottomLine, right = cell.topLine;
		Cell out = new Cell(cell.getCellState(), top, bottom, left, right);
		out.topLeft = Dot.turn90(cell.bottomLeft);
		out.topRight = Dot.turn90(cell.topLeft);
		out.bottomLeft = Dot.turn90(cell.bottomRight);
		out.bottomRight = Dot.turn90(cell.topRight);
		if(cell.isReady) {
			out.setReady();
		}
		return out;
	}
}

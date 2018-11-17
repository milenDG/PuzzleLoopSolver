package org.board_classes;

import java.io.IOException;

import org.board_classes.rules.InitialSolvingRules;
import org.board_classes.rules.MultiSolvingRules;

public class Board {
	private int height = 0;
	private int width = 0;
	private Cell[][] cells = null;
	private Dot[][] dots = null;
	private boolean hasInitialChecksHappened = false;
	
	/*
	 * Initialising board.
	 */
	public Board(int height, int width, int[][] cellNumbers) throws Exception {
		this.height = height;
		this.width = width;
		this.hasInitialChecksHappened = false;
		this.cells = new Cell[height][width];
		this.dots = new Dot[height + 1][width + 1];
		
		this.initialiseCells(cellNumbers);
		
		this.initialiseDots();
		
		this.initialiseDotsWithinCells();
		
	}
	private void initialiseCells(int[][] cellNumbers) throws Exception {
		for (int i = 0; i < this.cells.length; i++) {
			for (int j = 0; j < this.cells[i].length; j++) {
				Line topLine, bottomLine = new Line(false), leftLine , rightLine = new Line(true);
				if(i == 0) {
					topLine = new Line(false);
				} else {
					topLine = this.cells[i - 1][j].getBottomLine();
				}
				if(j == 0) {
					leftLine = new Line(true);
				} else {
					leftLine = this.cells[i][j - 1].getRightLine();
				}
		
				this.cells[i][j] = new Cell(cellNumbers[i][j], topLine, bottomLine, leftLine, rightLine);
			}
		}
	}
	private void initialiseDots() {
		for (int i = 0; i < this.dots.length; i++) {
			for (int j = 0; j < this.dots[i].length; j++) {
				Line topLine = null, bottomLine = null, leftLine = null, rightLine = null;
				if(i == 0) {
					topLine = new Line(true);
					topLine.forbid();
					if(j == 0) {
						bottomLine = this.cells[i][j].getLeftLine();
						rightLine = this.cells[i][j].getTopLine();
					}
					else {
						bottomLine = this.cells[i][j - 1].getRightLine();
						leftLine = this.cells[i][j - 1].getTopLine();
						if(j != this.dots[i].length - 1) {
							rightLine = cells[i][j].getTopLine();
						}
					}
				}
				else if(i == this.dots.length - 1) {
					bottomLine = new Line(true);
					bottomLine.forbid();
					if(j != 0) {
						leftLine = cells[i - 1][j - 1].getBottomLine();
						topLine = cells[i - 1][j - 1].getRightLine();
					}
					if(j != this.dots[i].length - 1) {
						rightLine = this.cells[i - 1][j].getBottomLine();
					}
					
				}
				if(j == 0) {
					leftLine = new Line(false);
					leftLine.forbid();
					if(i != dots.length - 1) {
						bottomLine = cells[i][j].getLeftLine();
					}
					if(i != 0) {
						topLine = cells[i - 1][j].getLeftLine();
						rightLine = cells[i - 1][j].getBottomLine();
					}
				}
				else if(j == this.dots[i].length - 1) {
					rightLine = new Line(false);
					rightLine.forbid();
					if(i != 0 && i != dots.length - 1) {
						leftLine = cells[i - 1][j - 1].getBottomLine();
						topLine = cells[i - 1][j - 1].getRightLine();
						bottomLine = cells[i][j - 1].getRightLine();
					}
				}
				if(i != 0 && i != this.dots.length - 1 && j != 0 && j != this.dots[i].length - 1) {
					topLine = cells[i - 1][j - 1].getRightLine();
					bottomLine = cells[i][j - 1].getRightLine();
					leftLine = cells[i - 1][j - 1].getBottomLine();
					rightLine = cells[i - 1][j].getBottomLine();
				}
				this.dots[i][j] = new Dot(topLine, bottomLine, leftLine, rightLine);
			}
		}
	}
	
	private void initialiseDotsWithinCells() {
		for (int i = 0; i < this.cells.length; i++) {
			for (int j = 0; j < this.cells[i].length; j++) {
				this.cells[i][j].initialiseDots(this.dots[i][j], this.dots[i][j + 1], this.dots[i + 1][j], this.dots[i + 1][j + 1]);
			}
		}
	}
	
	/*
	 * Getters.
	 */
	public Cell[][] getCells() {
		return this.cells;
	}
	public int getHeight() {
		return this.height;
	}
	public int getWidth() {
		return this.width;
	}
	
	public void printBoardOnConsole() throws IOException {
		for (int i = 0; i < cells.length; i++) {
			if(i == 0) {
				for (int j = 0; j < cells[i].length; j++) {
					if(j == 0) {
						System.out.print("●");	
					}
					this.cells[i][j].getTopLine().printOnConsole();
					System.out.print("●");
				}
							System.out.println();
			}
			for (int j = 0; j < cells[i].length; j++) {
				if(j == 0) {
					this.cells[i][j].getLeftLine().printOnConsole();
				}
				if(this.cells[i][j].getCellState() < 4) {
					System.out.print(" " + this.cells[i][j].getCellState() + " ");
				}
				else {
					System.out.print("   ");
				}
				this.cells[i][j].getRightLine().printOnConsole();
			}
			System.out.println();
			for (int j = 0; j < cells[i].length; j++) {
				if(j == 0) {
					System.out.print("●");
				}
				this.cells[i][j].getBottomLine().printOnConsole();
				System.out.print("●");
			}
			System.out.println();
		}
	}
	

	/*
	 * Solving.
	 */
	public void solve() throws Exception {
		if(!hasInitialChecksHappened) {
			initialRules();
		}
		multiRules();
		
	}
	
	private void initialRules() {
		InitialSolvingRules.zeroCellInitial(cells);
		InitialSolvingRules.threeNeighbourCells(cells);
		InitialSolvingRules.threeWithTwosBetween(cells);
		this.hasInitialChecksHappened = true;
	}
	
	private void multiRules() throws Exception {
		
		boolean hasChanged = false;
		do {
			do {
				hasChanged = false;
				if(MultiSolvingRules.readyCells(cells)
						| MultiSolvingRules.readyDots(dots)
						| MultiSolvingRules.oneCellWithConnectedNeighbourLine(cells)
						| MultiSolvingRules.threeCellWithConnectedNeighbourLine(cells)
						| MultiSolvingRules.threeCellWithForbidenDot(cells)
						| MultiSolvingRules.oneCellWithForbiddenDot(cells)
						| MultiSolvingRules.ForbidWholeCells(cells)
						| MultiSolvingRules.twoCellWithForbiddenSide(cells)
						| MultiSolvingRules.twoCellWithConnectedDot(cells)
						| MultiSolvingRules.twoCellWithForbiddenDot(cells)
						| MultiSolvingRules.twoCellWithTwoConnectedDots(cells)) {
					hasChanged = true;
				}
			} while(hasChanged);
			MultiSolvingRules.setDotWeightAsTheLowestLine(dots);
		} while(MultiSolvingRules.forbidDotsConnectionBetweenEqualWeightDots(dots));
		/*System.out.println(dots[16][14].getWeight());
		System.out.println(dots[17][14].getWeight());*/
	}
}

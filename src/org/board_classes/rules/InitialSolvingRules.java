package org.board_classes.rules;

import org.board_classes.Cell;
import org.board_classes.enums.LineState;

public class InitialSolvingRules {
	public static void zeroCellInitial(Cell[][] cells) {
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				if(cells[i][j].getCellState() == 0
						&& ((cells[i][j].getTopLine().getLineState() == LineState.Disconnected ||
								cells[i][j].getBottomLine().getLineState() == LineState.Disconnected ||
										cells[i][j].getLeftLine().getLineState() == LineState.Disconnected ||
												cells[i][j].getRightLine().getLineState() == LineState.Disconnected))) {
					cells[i][j].getTopLine().forbid();
					cells[i][j].getBottomLine().forbid();
					cells[i][j].getLeftLine().forbid();
					cells[i][j].getRightLine().forbid();
					cells[i][j].setReady();
				}
			}
		}
	}
	
	public static void threeNeighbourCells(Cell[][] cells) {
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				if(cells[i][j].getCellState() == 3) {
					if(i < cells.length - 1) {
						if(cells[i + 1][j].getCellState() == 3) {
							cells[i][j].connectTopLine();
							cells[i][j].connectBottomLine();
							cells[i + 1][j].connectBottomLine();
							if(j != 0) {
								cells[i][j - 1].forbidBottomLine();
							}
							if(j != cells[i].length - 1) {
								cells[i][j + 1].forbidBottomLine();
							}
						}
					}
					if(j < cells[i].length - 1) {
						if(cells[i][j + 1].getCellState() == 3) {
							cells[i][j].connectLeftLine();
							cells[i][j].connectRightLine();
							cells[i][j + 1].connectRightLine();
							if(i != 0) {
								cells[i - 1][j].forbidRightLine();
							}
							if(i != cells.length - 1) {
								cells[i + 1][j].forbidRightLine();
							}
						}
					}
				}
			}
		}
	}
	
	public static void threeWithTwosBetween(Cell[][] cells) {
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				if(cells[i][j].getCellState() == 3) {
					for (int k = 1; i + k < cells.length && j + k < cells[i].length; k++) {
						if(cells[i + k][j + k].getCellState() == 2) {
							continue;
						}
						else if(cells[i + k][j + k].getCellState() != 3){
							break;
						}
						else if(cells[i + k][j + k].getCellState() == 3) {
							if(cells[i][j].getLeftLine().getLineState() == LineState.Disconnected
									|| cells[i][j].getTopLine().getLineState() == LineState.Disconnected
									|| cells[i + k][j + k].getBottomLine().getLineState() == LineState.Disconnected
									|| cells[i + k][j + k].getRightLine().getLineState() == LineState.Disconnected) {
								cells[i][j].connectTopLine();
								cells[i][j].connectLeftLine();
								cells[i + k][j + k].connectBottomLine();
								cells[i + k][j + k].connectRightLine();
							}
						}
					}
					for (int k = 1; i + k < cells.length && j - k >= 0; k++) {
						if(cells[i + k][j - k].getCellState() == 2) {
							continue;
						}
						else if(cells[i + k][j - k].getCellState() != 3){
							break;
						}
						else if(cells[i + k][j - k].getCellState() == 3) {
							if(cells[i][j].getRightLine().getLineState() == LineState.Disconnected
									|| cells[i][j].getTopLine().getLineState() == LineState.Disconnected
									|| cells[i + k][j - k].getBottomLine().getLineState() == LineState.Disconnected
									|| cells[i + k][j - k].getLeftLine().getLineState() == LineState.Disconnected) {
								cells[i][j].connectTopLine();
								cells[i][j].connectRightLine();
								cells[i + k][j - k].connectBottomLine();
								cells[i + k][j - k].connectLeftLine();
							}
						}
					}
				}
			}
		}
	}
	
	
}

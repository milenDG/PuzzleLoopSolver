package org.board_classes.rules;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.board_classes.Cell;
import org.board_classes.Dot;
import org.board_classes.enums.LineState;

public class MultiSolvingRules {

	public static boolean readyDots(Dot[][] dots) {
		boolean hasChanged = false;
		for (int i = 0; i < dots.length; i++) {
			for (int j = 0; j < dots[i].length; j++) {
				if(!dots[i][j].isReady()) {
					Dot dot90 = Dot.turn90(dots[i][j]), dot180 = Dot.turn90(dot90), dot270 = Dot.turn90(dot180);
					if(SubMultiSolvingRules.SubSetForbidsOfConnectedDot(dots[i][j])
							|| SubMultiSolvingRules.SubSetForbidsOfConnectedDot(dot90)
							|| SubMultiSolvingRules.SubSetForbidsOfConnectedDot(dot180)
							|| SubMultiSolvingRules.SubSetForbidsOfConnectedDot(dot270)
							|| SubMultiSolvingRules.SubSetLineOfForbiddenDot(dots[i][j])
							|| SubMultiSolvingRules.SubSetLineOfForbiddenDot(dot90)
							|| SubMultiSolvingRules.SubSetLineOfForbiddenDot(dot180)
							|| SubMultiSolvingRules.SubSetLineOfForbiddenDot(dot270)
							|| SubMultiSolvingRules.SubDotWithThreeForbiden(dots[i][j])
							|| SubMultiSolvingRules.SubDotWithThreeForbiden(dot90)
							|| SubMultiSolvingRules.SubDotWithThreeForbiden(dot180)
							|| SubMultiSolvingRules.SubDotWithThreeForbiden(dot270)) { 
						dots[i][j].setReady(); hasChanged = true;
					}
				}
			}
		}
		
		return hasChanged;
	}
	
	public static boolean readyCells(Cell[][] cells) throws Exception {
		boolean hasChanged = false;
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				if(!cells[i][j].isReady() && cells[i][j].getCellState() != 4) {
					Cell cell90 = Cell.turn90(cells[i][j]), cell180 = Cell.turn90(cell90), cell270 = Cell.turn90(cell180);
					if(cells[i][j].getCellState() == 3
							&& (SubMultiSolvingRules.SubReadyThreeCell(cells[i][j])
							|| SubMultiSolvingRules.SubReadyThreeCell(cell90)
							|| SubMultiSolvingRules.SubReadyThreeCell(cell180)
							|| SubMultiSolvingRules.SubReadyThreeCell(cell270))) {
						hasChanged = true;
					}
					else if(cells[i][j].getCellState() == 2
								&& (SubMultiSolvingRules.SubReadyTwoCell(cells[i][j])
								|| SubMultiSolvingRules.SubReadyTwoCell(cell90)
								|| SubMultiSolvingRules.SubReadyTwoCell(cell180)
								|| SubMultiSolvingRules.SubReadyTwoCell(cell270))) {
						hasChanged = true;
					}
					else if(cells[i][j].getCellState() == 1
							&& (SubMultiSolvingRules.SubReadyOneCell(cells[i][j])
							|| SubMultiSolvingRules.SubReadyOneCell(cell90)
							|| SubMultiSolvingRules.SubReadyOneCell(cell180)
							|| SubMultiSolvingRules.SubReadyOneCell(cell270))) {
					hasChanged = true;
					}
				}
			}
		}
		return hasChanged;

	}
	
	public static boolean threeCellWithConnectedNeighbourLine(Cell[][] cells) throws Exception {
		boolean hasChanged = false;
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				if(cells[i][j].getCellState() == 3
						&& !cells[i][j].isReady()){
					Cell cell90 = Cell.turn90(cells[i][j]), cell180 = Cell.turn90(cell90), cell270 = Cell.turn90(cell180);
					if(SubMultiSolvingRules.subThreeCellWithConnectedNeighbourLine(cells[i][j])
							|| SubMultiSolvingRules.subThreeCellWithConnectedNeighbourLine(cell90)
							|| SubMultiSolvingRules.subThreeCellWithConnectedNeighbourLine(cell180)
							|| SubMultiSolvingRules.subThreeCellWithConnectedNeighbourLine(cell270)) {
						hasChanged = true;
					}
				}
			}
		}
		
		return hasChanged;
	}
	
	public static boolean oneCellWithConnectedNeighbourLine(Cell[][] cells) throws Exception {
		boolean hasChanged = false;
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				if(cells[i][j].getCellState() == 1
						&& !cells[i][j].isReady()) {
					Cell cell90 = Cell.turn90(cells[i][j]), cell180 = Cell.turn90(cell90), cell270 = Cell.turn90(cell180);
					if(SubMultiSolvingRules.subOneCellWithConnectedNeighbourLine(cells[i][j])
							|| SubMultiSolvingRules.subOneCellWithConnectedNeighbourLine(cell90)
							|| SubMultiSolvingRules.subOneCellWithConnectedNeighbourLine(cell180)
							|| SubMultiSolvingRules.subOneCellWithConnectedNeighbourLine(cell270)) {
						hasChanged = true;
					}
				}
			}
		}
		
		return hasChanged;
	}
	
	public static boolean threeCellWithForbidenDot(Cell[][] cells) throws Exception {
		boolean hasChanged = false;
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				if(cells[i][j].getCellState() == 3
						&& !cells[i][j].isReady()) {
					Cell cell90 = Cell.turn90(cells[i][j]), cell180 = Cell.turn90(cell90), cell270 = Cell.turn90(cell180);
					if(SubMultiSolvingRules.subThreeCellWithForbidenDot(cells[i][j])
							|| SubMultiSolvingRules.subThreeCellWithForbidenDot(cell90)
							|| SubMultiSolvingRules.subThreeCellWithForbidenDot(cell180)
							|| SubMultiSolvingRules.subThreeCellWithForbidenDot(cell270)) {
						hasChanged = true;
					}
				}
			}
		}
		return hasChanged;
	}

	public static boolean oneCellWithForbiddenDot(Cell[][] cells) throws Exception {
		boolean hasChanged = false;
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				if(cells[i][j].getCellState() == 1
						&& !cells[i][j].isReady()) {
					Cell cell90 = Cell.turn90(cells[i][j]), cell180 = Cell.turn90(cell90), cell270 = Cell.turn90(cell180);
					if(SubMultiSolvingRules.subOneCellWithForbidenDot(cells[i][j])
							|| SubMultiSolvingRules.subOneCellWithForbidenDot(cell90)
							|| SubMultiSolvingRules.subOneCellWithForbidenDot(cell180)
							|| SubMultiSolvingRules.subOneCellWithForbidenDot(cell270)) {
						hasChanged = true;
					}
				}
			}
		}
		return hasChanged;
	}

	public static boolean ForbidWholeCells(Cell[][] cells) throws Exception {
		boolean hasChanged = false;
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				if(!cells[i][j].isReady()) {
					Cell cell90 = Cell.turn90(cells[i][j]), cell180 = Cell.turn90(cell90), cell270 = Cell.turn90(cell180);
					if(SubMultiSolvingRules.subForbidWholeCells(cells[i][j])
							|| SubMultiSolvingRules.subForbidWholeCells(cell90)
							|| SubMultiSolvingRules.subForbidWholeCells(cell180)
							|| SubMultiSolvingRules.subForbidWholeCells(cell270)) {
						hasChanged = true;
					}
				}
			}
		}
		return hasChanged;
	}

	public static boolean twoCellWithForbiddenSide(Cell[][] cells) throws Exception {
		boolean hasChanged = false;
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				if(cells[i][j].getCellState() == 2
						&& !cells[i][j].isReady()) {
					Cell cell90 = Cell.turn90(cells[i][j]), cell180 = Cell.turn90(cell90), cell270 = Cell.turn90(cell180);
					if(SubMultiSolvingRules.subTwoCellWtihForbiddenSide(cells[i][j])
							|| SubMultiSolvingRules.subTwoCellWtihForbiddenSide(cell90)
							|| SubMultiSolvingRules.subTwoCellWtihForbiddenSide(cell180)
							|| SubMultiSolvingRules.subTwoCellWtihForbiddenSide(cell270)) {
						hasChanged = true;
					}
				}
			}
		}
		
		return hasChanged;
	}

	public static boolean twoCellWithConnectedDot(Cell[][] cells) throws Exception {
		boolean hasChanged = false;
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				if(cells[i][j].getCellState() == 2
						&& !cells[i][j].isReady()) {
					Cell cell90 = Cell.turn90(cells[i][j]), cell180 = Cell.turn90(cell90), cell270 = Cell.turn90(cell180);
					if(SubMultiSolvingRules.subTwoCellWithConnectedDot(cells[i][j])
							|| SubMultiSolvingRules.subTwoCellWithConnectedDot(cell90)
							|| SubMultiSolvingRules.subTwoCellWithConnectedDot(cell180)
							|| SubMultiSolvingRules.subTwoCellWithConnectedDot(cell270)) {
						hasChanged = true;
					}
				}
			}
		}
		
		return hasChanged;
	}

	public static boolean twoCellWithForbiddenDot(Cell[][] cells) throws Exception {
		boolean hasChanged = false;
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				if(cells[i][j].getCellState() == 2
						&& !cells[i][j].isReady()) {
					Cell cell90 = Cell.turn90(cells[i][j]), cell180 = Cell.turn90(cell90), cell270 = Cell.turn90(cell180);
					if(SubMultiSolvingRules.subTwoCellWithForbiddenDot(cells[i][j])
							|| SubMultiSolvingRules.subTwoCellWithForbiddenDot(cell90)
							|| SubMultiSolvingRules.subTwoCellWithForbiddenDot(cell180)
							|| SubMultiSolvingRules.subTwoCellWithForbiddenDot(cell270)) {
						hasChanged = true;
					}
				}
			}
		}
		
		return hasChanged;
	}
	
	public static boolean twoCellWithTwoConnectedDots(Cell[][] cells) throws Exception {
		boolean hasChanged = false;
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				if(cells[i][j].getCellState() == 2
						&& !cells[i][j].isReady()) {
					Cell cell90 = Cell.turn90(cells[i][j]), cell180 = Cell.turn90(cell90), cell270 = Cell.turn90(cell180);
					if(SubMultiSolvingRules.subTwoCellWithTwoConnectedDots(cells[i][j])
							|| SubMultiSolvingRules.subTwoCellWithTwoConnectedDots(cell90)
							|| SubMultiSolvingRules.subTwoCellWithTwoConnectedDots(cell180)
							|| SubMultiSolvingRules.subTwoCellWithTwoConnectedDots(cell270)) {
						hasChanged = true;
					}
				}
			}
		}
		
		return hasChanged;
	}
	
	public static boolean twoCellWithForbiddenDot2(Cell[][] cells) throws Exception {
		boolean hasChanged = false;
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				if(cells[i][j].getCellState() == 2
						&& !cells[i][j].isReady()) {
					Cell cell90 = Cell.turn90(cells[i][j]), cell180 = Cell.turn90(cell90), cell270 = Cell.turn90(cell180);
					if(SubMultiSolvingRules.subTwoCellWithTwoConnectedDots(cells[i][j])
							|| SubMultiSolvingRules.subTwoCellWithTwoConnectedDots(cell90)
							|| SubMultiSolvingRules.subTwoCellWithTwoConnectedDots(cell180)
							|| SubMultiSolvingRules.subTwoCellWithTwoConnectedDots(cell270)) {
						hasChanged = true;
					}
				}
			}
		}
		
		return hasChanged;
	}

	public static void setDotWeightAsTheLowestLine(Dot[][] dots) {
		SubMultiSolvingRules.resetWeights(dots);
		boolean hasChanged = false;
		Set<Integer> currentWeights = new HashSet<Integer>();
		int newWeight = 1;
		do {
			hasChanged = false;
			for (int i = 0; i < dots.length; i++) {
				for (int j = 0; j < dots[i].length; j++) {
					if(dots[i][j].getTopLine().getLineState() == LineState.Connected
							|| dots[i][j].getBottomLine().getLineState() == LineState.Connected
							|| dots[i][j].getLeftLine().getLineState() == LineState.Connected
							|| dots[i][j].getRightLine().getLineState() == LineState.Connected) {
						if(dots[i][j].getWeight() == 0
								&& dots[i][j].getTopLine().getWeight() == 0
								&& dots[i][j].getBottomLine().getWeight() == 0
								&& dots[i][j].getLeftLine().getWeight() == 0
								&& dots[i][j].getRightLine().getWeight() == 0) {
							currentWeights.add(newWeight);
							SubMultiSolvingRules.setDotWeight(dots[i][j], newWeight);
							newWeight++;
							hasChanged = true;
						}
						else if(dots[i][j].getWeight() == 0) {
							SubMultiSolvingRules.setDotWeight(dots[i][j], Dot.leastWeightinDot(dots[i][j]));
							hasChanged = true;
						}
						else if(dots[i][j].getWeight() > 0
								&& Dot.leastWeightinDot(dots[i][j]) != dots[i][j].getWeight()) {
							currentWeights.remove(dots[i][j].getWeight());
							if(newWeight > dots[i][j].getWeight()) {
								newWeight = dots[i][j].getWeight();
							}
							SubMultiSolvingRules.setDotWeight(dots[i][j], Math.min(Dot.leastWeightinDot(dots[i][j]),dots[i][j].getWeight()));
							hasChanged = true;
						}
					}
				}
			}
		} while(hasChanged);
		for (newWeight = 1; currentWeights.contains(newWeight); newWeight++) {}
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < dots.length; i++) {
			for (int j = 0; j < dots[i].length; j++) {
				if(!map.containsKey(dots[i][j].getWeight()))
				{
					if(dots[i][j].getWeight() > 0 && newWeight < dots[i][j].getWeight()) {
						map.put(dots[i][j].getWeight(), newWeight);
						currentWeights.remove(dots[i][j].getWeight());
						SubMultiSolvingRules.setDotWeight(dots[i][j], newWeight);
						currentWeights.add(newWeight);
						while(currentWeights.contains(newWeight)) {
							newWeight++;
						}
					}
				}
				else if(dots[i][j].getWeight() > map.get(dots[i][j].getWeight())) {
					SubMultiSolvingRules.setDotWeight(dots[i][j], map.get(dots[i][j].getWeight()));
				}
			}
		}
	}

	public static boolean forbidDotsConnectionBetweenEqualWeightDots(Dot[][] dots) {
		boolean hasChanged = false;
		for (int i = 0; i < dots.length - 1; i++) {
			for (int j = 0; j < dots[i].length - 1; j++) {
				if(!dots[i][j].isReady()) {
					if(dots[i][j].getWeight() == dots[i][j + 1].getWeight()
							&& dots[i][j].getWeight() > 0
							&& dots[i][j].getRightLine().getLineState() == LineState.Disconnected) {
						dots[i][j].forbidRightLine();
						hasChanged = true;
					}
					if(dots[i][j].getWeight() == dots[i + 1][j].getWeight()
							&& dots[i][j].getWeight() > 0
							&& dots[i][j].getBottomLine().getLineState() == LineState.Disconnected) {
						dots[i][j].forbidBottomLine();
						hasChanged = true;
					}
				}
			}
		}
		return hasChanged;
	}
}
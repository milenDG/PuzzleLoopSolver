package org.board_classes.rules;

import org.board_classes.Cell;
import org.board_classes.Dot;
import org.board_classes.enums.LineState;

public class SubMultiSolvingRules {

	/*
	 * Sub-rules
	 */
	
	public static boolean subThreeCellWithConnectedNeighbourLine(Cell cell) {
		boolean hasChanged = false;
		if(cell.getTopLeft().getTopLine().getLineState() == LineState.Connected
				|| cell.getTopLeft().getLeftLine().getLineState() == LineState.Connected) {
			if(cell.getTopLeft().getLeftLine().getLineState() == LineState.Disconnected) {
				cell.getTopLeft().forbidLeftLine();
				hasChanged = true;
			} else if(cell.getTopLeft().getTopLine().getLineState() == LineState.Disconnected) {
				cell.getTopLeft().forbidTopLine();
				hasChanged = true;
			}
			if(cell.getRightLine().getLineState() == LineState.Disconnected
					|| cell.getBottomLine().getLineState() == LineState.Disconnected) {
				cell.connectRightLine();
				cell.connectBottomLine();
				hasChanged = true;
			}
		}
		return hasChanged;
	}
	public static boolean subOneCellWithConnectedNeighbourLine(Cell cell) {
		boolean hasChanged = false;
		if((cell.getTopLeft().getTopLine().getLineState() == LineState.Connected
				&& cell.getTopLeft().getLeftLine().getLineState() == LineState.Forbiden)
				|| (cell.getTopLeft().getLeftLine().getLineState() == LineState.Connected
				&& cell.getTopLeft().getTopLine().getLineState() == LineState.Forbiden)) {
			if(cell.getRightLine().getLineState() == LineState.Disconnected
					|| cell.getBottomLine().getLineState() == LineState.Disconnected) {
				cell.forbidRightLine();
				cell.forbidBottomLine();
				hasChanged = true;
			}
		}
		return hasChanged;
	}

	/*
	 *  ReadyDotSubRules
	 */
	public static boolean SubSetForbidsOfConnectedDot(Dot dot) {
		boolean hasChanged = false;
		if(dot.getTopLine().getLineState() == LineState.Connected) {
			if(dot.getLeftLine().getLineState() == LineState.Connected)
			{
				if(dot.getBottomLine().getLineState() == LineState.Disconnected ||
						dot.getRightLine().getLineState() == LineState.Disconnected) {
					dot.forbidBottomLine();
					dot.forbidRightLine();
					dot.setReady();
					hasChanged = true;
				}
			}
			else if(dot.getBottomLine().getLineState() == LineState.Connected)
			{
				if(dot.getLeftLine().getLineState() == LineState.Disconnected ||
						dot.getRightLine().getLineState() == LineState.Disconnected) {
					dot.forbidLeftLine();
					dot.forbidRightLine();
					dot.setReady();
					hasChanged = true;
				}
			}
			else if(dot.getRightLine().getLineState() == LineState.Connected)
			{
				if(dot.getLeftLine().getLineState() == LineState.Disconnected ||
						dot.getBottomLine().getLineState() == LineState.Disconnected) {
					dot.forbidLeftLine();
					dot.forbidBottomLine();
					dot.setReady();
					hasChanged = true;
				}
			}
		}
		
		return hasChanged;
	}	
	public static boolean SubSetLineOfForbiddenDot(Dot dot) {
		boolean hasChanged = false;
		if(dot.getTopLine().getLineState() == LineState.Connected) {
			if(dot.getLeftLine().getLineState() == LineState.Forbiden)
			{
				if(dot.getBottomLine().getLineState() == LineState.Forbiden) {
					dot.connectRightLine();
					dot.setReady();
					hasChanged = true;
				}
				else if(dot.getRightLine().getLineState() == LineState.Forbiden) {
					dot.connectBottomLine();
					dot.setReady();
					hasChanged = true;
				}
			}
			else if(dot.getBottomLine().getLineState() == LineState.Forbiden
					&& dot.getRightLine().getLineState() == LineState.Forbiden) {
				dot.connectLeftLine();
				dot.setReady();
				hasChanged = true;
			}
		}
		
		return hasChanged;
	}	
	public static boolean SubDotWithThreeForbiden(Dot dot) {
		boolean hasChanged = false;
		if(dot.getTopLine().getLineState() == LineState.Forbiden
				&& dot.getBottomLine().getLineState() == LineState.Forbiden
					&& dot.getLeftLine().getLineState() == LineState.Forbiden) {
			dot.forbidRightLine();
			dot.setReady();
			hasChanged = true;
		}
		return hasChanged;
	}	

	
	/*
	 * ReadyCellSubRules
	 */
	public static boolean SubReadyThreeCell(Cell cell) {
		boolean hasChanged = false;
		if(cell.getTopLine().getLineState() == LineState.Forbiden
				&& (cell.getBottomLine().getLineState() == LineState.Disconnected
				|| cell.getLeftLine().getLineState() == LineState.Disconnected
				|| cell.getRightLine().getLineState() == LineState.Disconnected)) {
			cell.connectBottomLine();
			cell.connectLeftLine();
			cell.connectRightLine();
			cell.setReady();
			hasChanged = true;
		} else {
			if(cell.getTopLine().getLineState() == LineState.Disconnected
					&& cell.getBottomLine().getLineState() == LineState.Connected
					&& cell.getLeftLine().getLineState() == LineState.Connected
					&& cell.getRightLine().getLineState() == LineState.Connected) {
				cell.forbidTopLine();
				cell.setReady();
				hasChanged = true;
			}
		}
		return hasChanged;
	}
	public static boolean SubReadyOneCell(Cell cell) {
		boolean hasChanged = false;
		if(cell.getTopLine().getLineState() == LineState.Connected
				&& (cell.getBottomLine().getLineState() == LineState.Disconnected
				|| cell.getLeftLine().getLineState() == LineState.Disconnected
				|| cell.getRightLine().getLineState() == LineState.Disconnected)) {
			cell.forbidBottomLine();
			cell.forbidLeftLine();
			cell.forbidRightLine();
			cell.setReady();
			hasChanged = true;
		} else {
			if(cell.getBottomLine().getLineState() == LineState.Forbiden
					&& cell.getLeftLine().getLineState() == LineState.Forbiden
					&& cell.getRightLine().getLineState() == LineState.Forbiden
					&& cell.getTopLine().getLineState() == LineState.Disconnected) {
				cell.connectTopLine();
				cell.setReady();
				hasChanged = true;
			}
		}
		return hasChanged;
	}
	public static boolean SubReadyTwoCell(Cell cell) {
		boolean hasChanged = false;
		if(cell.getTopLine().getLineState() == LineState.Connected
				&& cell.getBottomLine().getLineState() == LineState.Connected
				&& (cell.getLeftLine().getLineState() == LineState.Disconnected
				|| cell.getRightLine().getLineState() == LineState.Disconnected)) {
			cell.forbidLeftLine();
			cell.forbidRightLine();
			cell.setReady();
			hasChanged = true;
		} else if(cell.getTopLine().getLineState() == LineState.Forbiden
					&& cell.getBottomLine().getLineState() == LineState.Forbiden
					&& (cell.getLeftLine().getLineState() == LineState.Disconnected
					|| cell.getRightLine().getLineState() == LineState.Disconnected)) {
				cell.connectLeftLine();
				cell.connectRightLine();
				cell.setReady();
				hasChanged = true;
		} else if(cell.getTopLine().getLineState() == LineState.Connected
					&& cell.getLeftLine().getLineState() == LineState.Connected
					&& (cell.getBottomLine().getLineState() == LineState.Disconnected
					|| cell.getRightLine().getLineState() == LineState.Disconnected)) {
			cell.forbidBottomLine();
			cell.forbidRightLine();
			cell.setReady();
			hasChanged = true;
		} else if(cell.getTopLine().getLineState() == LineState.Forbiden
				&& cell.getLeftLine().getLineState() == LineState.Forbiden
				&& (cell.getBottomLine().getLineState() == LineState.Disconnected
				|| cell.getRightLine().getLineState() == LineState.Disconnected)) {
			cell.connectBottomLine();
			cell.connectRightLine();
			cell.setReady();
			hasChanged = true;
		}
		return hasChanged;
	}
	
	/*
	 * Others
	 */
	public static boolean subThreeCellWithForbidenDot(Cell cell) {
		boolean hasChanged = false;
		if(cell.getTopLeft().getTopLine().getLineState() == LineState.Forbiden
				&& cell.getTopLeft().getLeftLine().getLineState() == LineState.Forbiden) {
			if(cell.getTopLine().getLineState() == LineState.Disconnected
					|| cell.getLeftLine().getLineState() == LineState.Disconnected) {
				cell.connectTopLine();
				cell.connectLeftLine();
				cell.getTopLeft().setReady();
				hasChanged = true;
			}
		}
		return hasChanged;
	}
	
	public static boolean subOneCellWithForbidenDot(Cell cell) {
		boolean hasChanged = false;
		if(cell.getTopLeft().getTopLine().getLineState() == LineState.Forbiden
				&& cell.getTopLeft().getLeftLine().getLineState() == LineState.Forbiden) {
			if(cell.getTopLine().getLineState() == LineState.Disconnected
					|| cell.getLeftLine().getLineState() == LineState.Disconnected) {
				cell.forbidTopLine();
				cell.forbidLeftLine();
				cell.getTopLeft().setReady();
				hasChanged = true;
			}
		}
		return hasChanged;
	}
	
	public static boolean subForbidWholeCells(Cell cell) {
		boolean hasChanged = false;
		if(cell.getTopLine().getLineState() == LineState.Connected
				&& cell.getBottomLine().getLineState() == LineState.Connected
				&& cell.getLeftLine().getLineState() == LineState.Connected
				&& cell.getRightLine().getLineState() == LineState.Disconnected) {
			cell.forbidRightLine();
			cell.setReady();
			hasChanged = true;
		}
		
		return hasChanged;
	}
	
	public static boolean subTwoCellWtihForbiddenSide(Cell cell) {
		boolean hasChanged = false;
		if((cell.getTopLeft().getTopLine().getLineState() == LineState.Connected
				&& cell.getBottomLine().getLineState() == LineState.Forbiden)
				&& (cell.getRightLine().getLineState() == LineState.Disconnected
				|| cell.getTopLeft().getLeftLine().getLineState() == LineState.Disconnected)) {
			cell.connectRightLine();
			cell.getTopLeft().forbidLeftLine();
			hasChanged = true;
		} else if((cell.getTopLeft().getTopLine().getLineState() == LineState.Connected
				&& cell.getRightLine().getLineState() == LineState.Forbiden)
				&& (cell.getBottomLine().getLineState() == LineState.Disconnected
				|| cell.getTopLeft().getLeftLine().getLineState() == LineState.Disconnected)) {
			cell.connectBottomLine();
			cell.getTopLeft().forbidLeftLine();
			hasChanged = true;
		} else if((cell.getTopLeft().getLeftLine().getLineState() == LineState.Connected
				&& cell.getBottomLine().getLineState() == LineState.Forbiden)
				&& (cell.getRightLine().getLineState() == LineState.Disconnected
				|| cell.getTopLeft().getTopLine().getLineState() == LineState.Disconnected)) {
			cell.connectRightLine();
			cell.getTopLeft().forbidTopLine();
			hasChanged = true;
		} else if((cell.getTopLeft().getLeftLine().getLineState() == LineState.Connected
				&& cell.getRightLine().getLineState() == LineState.Forbiden)
				&& (cell.getBottomLine().getLineState() == LineState.Disconnected
				|| cell.getTopLeft().getTopLine().getLineState() == LineState.Disconnected)) {
			cell.connectBottomLine();
			cell.getTopLeft().forbidTopLine();
			hasChanged = true;
		}
		return hasChanged;
	}
	
	public static boolean subTwoCellWithConnectedDot(Cell cell) {
		boolean hasChanged = false;
		if((cell.getTopLeft().getTopLine().getLineState() == LineState.Connected
				&& cell.getTopLeft().getLeftLine().getLineState() == LineState.Forbiden)
				|| (cell.getTopLeft().getTopLine().getLineState() == LineState.Forbiden
				&& cell.getTopLeft().getLeftLine().getLineState() == LineState.Connected)){
			if(cell.getBottomRight().getBottomLine().getLineState() == LineState.Forbiden
					&& cell.getBottomRight().getRightLine().getLineState() == LineState.Disconnected) {
				cell.getBottomRight().connectRightLine();
				hasChanged = true;
			} else if(cell.getBottomRight().getBottomLine().getLineState() == LineState.Disconnected
					&& cell.getBottomRight().getRightLine().getLineState() == LineState.Forbiden) {
				cell.getBottomRight().connectBottomLine();
				hasChanged = true;
			}
		}
		return hasChanged;
	}
	
	public static boolean subTwoCellWithForbiddenDot(Cell cell) {
		boolean hasChanged = false;
		if(cell.getTopLeft().getTopLine().getLineState() == LineState.Forbiden
				&& cell.getTopLeft().getLeftLine().getLineState() == LineState.Forbiden) {
			if((cell.getBottomLine().getLineState() == LineState.Forbiden || 
					cell.getRightLine().getLineState() == LineState.Forbiden)
					&& (cell.getTopLine().getLineState() == LineState.Disconnected
					|| cell.getLeftLine().getLineState() == LineState.Disconnected)) {
				cell.connectTopLine();
				cell.connectLeftLine();
				cell.forbidBottomLine();
				cell.forbidRightLine();
				cell.setReady();
				hasChanged = true;
			} else if((cell.getBottomLine().getLineState() == LineState.Connected
					&& cell.getRightLine().getLineState() == LineState.Disconnected)
					|| (cell.getBottomLine().getLineState() == LineState.Disconnected
					&& cell.getRightLine().getLineState() == LineState.Connected)) {
				cell.connectBottomLine();
				cell.connectRightLine();
				cell.forbidTopLine();
				cell.forbidLeftLine();
				cell.setReady();
				hasChanged = true;
			} 
			
			if(cell.getTopRight().getTopLine().getLineState() == LineState.Connected
					&& cell.getTopRight().getRightLine().getLineState() == LineState.Disconnected) {
				cell.getTopRight().forbidRightLine();
				hasChanged = true;
			} else if(cell.getTopRight().getTopLine().getLineState() == LineState.Disconnected
					&& cell.getTopRight().getRightLine().getLineState() == LineState.Connected) {
				cell.getTopRight().forbidTopLine();
				hasChanged = true;
			}
			
			if(cell.getBottomLeft().getBottomLine().getLineState() == LineState.Connected
					&& cell.getBottomLeft().getLeftLine().getLineState() == LineState.Disconnected) {
				cell.getBottomLeft().forbidLeftLine();
				hasChanged = true;
			} else if(cell.getBottomLeft().getBottomLine().getLineState() == LineState.Disconnected
					&& cell.getBottomLeft().getLeftLine().getLineState() == LineState.Connected) {
				cell.getBottomLeft().forbidBottomLine();
				hasChanged = true;
			}
			
			if(cell.getTopRight().getTopLine().getLineState() == LineState.Forbiden
					&& cell.getTopRight().getRightLine().getLineState() == LineState.Disconnected) {
				cell.getTopRight().connectRightLine();
				hasChanged = true;
			} else if(cell.getTopRight().getTopLine().getLineState() == LineState.Disconnected
					&& cell.getTopRight().getRightLine().getLineState() == LineState.Forbiden) {
				cell.getTopRight().connectTopLine();
				hasChanged = true;
			}
			
			if(cell.getBottomLeft().getBottomLine().getLineState() == LineState.Forbiden
					&& cell.getBottomLeft().getLeftLine().getLineState() == LineState.Disconnected) {
				cell.getBottomLeft().connectLeftLine();
				hasChanged = true;
			} else if(cell.getBottomLeft().getBottomLine().getLineState() == LineState.Disconnected
					&& cell.getBottomLeft().getLeftLine().getLineState() == LineState.Forbiden) {
				cell.getBottomLeft().connectBottomLine();
				hasChanged = true;
			}
			
			if(cell.getBottomRight().getBottomLine().getLineState() == LineState.Connected &&
					cell.getBottomRight().getRightLine().getLineState() == LineState.Disconnected) {
				cell.getBottomRight().connectRightLine();
				hasChanged = true;
			} else if(cell.getBottomRight().getBottomLine().getLineState() == LineState.Disconnected &&
					cell.getBottomRight().getRightLine().getLineState() == LineState.Connected) {
				cell.getBottomRight().connectBottomLine();
				hasChanged = true;
			}
		}
		return hasChanged;
	}
	
	public static boolean subTwoCellWithTwoConnectedDots(Cell cell) {
		boolean hasChanged = false;
		if((cell.getTopLeft().getTopLine().getLineState() == LineState.Connected
				&& cell.getTopLeft().getLeftLine().getLineState() == LineState.Forbiden)
				|| (cell.getTopLeft().getTopLine().getLineState() == LineState.Forbiden
				&& cell.getTopLeft().getLeftLine().getLineState() == LineState.Connected)) {
			if(cell.getBottomRight().getBottomLine().getLineState() == LineState.Forbiden &&
					cell.getBottomRight().getRightLine().getLineState() == LineState.Disconnected) {
				cell.getBottomRight().connectRightLine();
				hasChanged = true;
			} else if(cell.getBottomRight().getBottomLine().getLineState() == LineState.Disconnected &&
					cell.getBottomRight().getRightLine().getLineState() == LineState.Forbiden) {
				cell.getBottomRight().connectBottomLine();
				hasChanged = true;
			} else if(cell.getBottomRight().getBottomLine().getLineState() == LineState.Connected &&
					cell.getBottomRight().getRightLine().getLineState() == LineState.Disconnected) {
				cell.getBottomRight().forbidRightLine();
				hasChanged = true;
			} else if(cell.getBottomRight().getBottomLine().getLineState() == LineState.Disconnected &&
					cell.getBottomRight().getRightLine().getLineState() == LineState.Connected) {
				cell.getBottomRight().forbidBottomLine();
				hasChanged = true;
			}
		}
		return hasChanged;
	}
	
	public static void setDotWeight(Dot dot, int newWeight) {
		dot.setWeight(newWeight);
		if(dot.getTopLine().getLineState() == LineState.Connected) {
			dot.getTopLine().setWeight(newWeight);
		}
		if(dot.getBottomLine().getLineState() == LineState.Connected) {
			dot.getBottomLine().setWeight(newWeight);
		}
		if(dot.getLeftLine().getLineState() == LineState.Connected) {
			dot.getLeftLine().setWeight(newWeight);
		}
		if(dot.getRightLine().getLineState() == LineState.Connected) {
			dot.getRightLine().setWeight(newWeight);
		}
	}
	
	public static void resetWeights(Dot[][] dots) {
		for (Dot[] dots2 : dots) {
			for (Dot dot : dots2) {
				dot.setWeight(0);
				dot.getTopLine().setWeight(0);
				dot.getBottomLine().setWeight(0);
				dot.getLeftLine().setWeight(0);
				dot.getRightLine().setWeight(0);
			}
		}
		
	}

}
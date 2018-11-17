package org.board_classes;

import java.util.ArrayList;
import java.util.Collections;

public class Dot {
	private Line topLine = null;
	private Line bottomLine = null;
	private Line leftLine = null;
	private Line rightLine = null;
	private boolean isReady = false;
	private int weight = 0;
	
	public Dot(Line topLine, Line bottomLine, Line leftLine, Line rightLine) {
		this.topLine = topLine;
		this.bottomLine = bottomLine;
		this.leftLine = leftLine;
		this.rightLine = rightLine;
		this.isReady = false;
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
	
	public void setReady() {
		this.isReady = true;
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
	public int getWeight() {
		return this.weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public boolean isReady() {
		return this.isReady;
	}
	
	public static Dot turn90(Dot inDot) {
		Line top = inDot.leftLine, bottom = inDot.rightLine, left = inDot.bottomLine, right = inDot.topLine;
		Dot dot = new Dot(top, bottom, left, right);
		if(inDot.isReady) {
			dot.setReady();
		}
		return dot;
	}
	public static int leastWeightinDot(Dot dot) {
		ArrayList<Integer> weights = new ArrayList<Integer>();
		if(dot.getTopLine().getWeight() > 0) {
			weights.add(dot.getTopLine().getWeight());
		}
		if(dot.getBottomLine().getWeight() > 0) {
			weights.add(dot.getBottomLine().getWeight());
		}
		if(dot.getLeftLine().getWeight() > 0) {
			weights.add(dot.getLeftLine().getWeight());
		}
		if(dot.getRightLine().getWeight() > 0) {
			weights.add(dot.getRightLine().getWeight());
		}
		return Collections.min(weights);
	}
}

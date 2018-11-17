package org.start_up;

import java.util.Scanner;

import org.board_classes.Board;


public class Startup {

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		int height = scanner.nextInt();
		int width = scanner.nextInt();
		int[][] cellNumbers = new int[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				cellNumbers[i][j] = scanner.nextInt();
			}
		}
		Board board = new Board(height, width, cellNumbers);
		board.solve();
		board.printBoardOnConsole();
		System.out.println();
		scanner.close();
	}

}

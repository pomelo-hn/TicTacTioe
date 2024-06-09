package lessgo;

import java.util.Scanner;

public class MainClass {
	
	static char[][] ticTacToePanel = {{'-', '-', '-'}, {'-', '-', '-'}, {'-', '-', '-'}};
	static boolean didAnyoneWin = false;
	static boolean isCrossWin = false;
	static boolean isCrossMove = false;
	
	static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		ticTacToePanelView("Добро пожаловать. Изначальное поле: ");
	
		do {
			playersMove(true);
			ticTacToePanelView("Поле: ");
			isWin(true);
			isWin(false);
			
			playersMove(false);
			ticTacToePanelView("Поле: ");	
			isWin(true);
			isWin(false);
		}
		while (!didAnyoneWin);
		
		whoWin();
		
		in.close();
	}
	
	public static void ticTacToePanelView(String message){
		System.out.println();
		System.out.println(message);
		System.out.println("");
		for (int i = 0; i < ticTacToePanel.length; i++) {
			for(int j = 0; j < ticTacToePanel[i].length; j++) {
				System.out.printf("%c ", ticTacToePanel[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void playersMove(boolean isCross) {
			
		if (!didAnyoneWin) {
			String nameOfPlayerString = "";
			char playersChar = 0;
			int row;
			int column;
			boolean isOccuped = false;
			
			if (isCross) {
				nameOfPlayerString = "крестик. ";
				playersChar = 'X';
			}
			else {
				nameOfPlayerString = "нолик. ";
				playersChar = 'O';
			}
			
			System.out.println();
			
			
			do {
				System.out.println("Ход начинает " + nameOfPlayerString + "Выберите строку: ");
				row = inputMove();
				
				System.out.println("Ход продолжает " + nameOfPlayerString + "Выберите колонку: ");
				column = inputMove();
				
				if (ticTacToePanel[row][column] == 'X' || ticTacToePanel[row][column] == 'O') {
					System.out.println("Это поле уже занято! Выберите другие параметры!");
				}
				
				
			} while (ticTacToePanel[row][column] == 'X' || ticTacToePanel[row][column] == 'O');
			
			ticTacToePanel[row][column] = playersChar;
		}
		else {
			isWin(true);
			isWin(false);
			
		}
		
	}
	
	public static int inputMove() {
		int num = 0;
		num = in.nextInt() - 1;
		
		if (num >= 3) { 
			while (num >= 3) {
				System.out.println("Значение не может быть больше 3! Введите другое: ");
				num = in.nextInt() - 1;
			}
		}
		
		if (num == 3) {
			num--;
		}
		
		return num;
	}
	
	public static void isWin(boolean isCross) {
		if (!didAnyoneWin) {
			char playersChar = 0;
			
			if (isCross) {
				playersChar = 'X';
				isCrossMove = true;
			}
			else {
				playersChar = 'O';
				isCrossMove = false;
			}
			
			boolean upperLine = (ticTacToePanel[0][0] == playersChar) && (ticTacToePanel[0][1] == playersChar) && (ticTacToePanel[0][2] == playersChar);
			boolean middleLine = (ticTacToePanel[1][0] == playersChar) && (ticTacToePanel[1][1] == playersChar) && (ticTacToePanel[1][2] == playersChar);
			boolean lowerLine = (ticTacToePanel[2][0] == playersChar) && (ticTacToePanel[2][1] == playersChar) && (ticTacToePanel[2][2] == playersChar);
			
			boolean leftLine = (ticTacToePanel[0][0] == playersChar) && (ticTacToePanel[1][0] == playersChar) && (ticTacToePanel[2][0] == playersChar);
			boolean centralLine = (ticTacToePanel[0][1] == playersChar) && (ticTacToePanel[1][1] == playersChar) && (ticTacToePanel[2][1] == playersChar);
			boolean rightLine = (ticTacToePanel[0][2] == playersChar) && (ticTacToePanel[1][2] == playersChar) && (ticTacToePanel[2][2] == playersChar);
			
			boolean upperleftDiaLine = (ticTacToePanel[0][0] == playersChar) && (ticTacToePanel[1][1] == playersChar) && (ticTacToePanel[2][2] == playersChar);
			boolean upperrightDiaLine = (ticTacToePanel[0][2] == playersChar) && (ticTacToePanel[1][1] == playersChar) && (ticTacToePanel[2][0] == playersChar);
			
			
			if (upperLine || middleLine || lowerLine || leftLine || centralLine || rightLine || upperleftDiaLine || upperrightDiaLine) {
				
				didAnyoneWin = true;
			}
		}
	}
	
	public static void whoWin() {
		if(isCrossMove && didAnyoneWin) {
			System.out.println();
			System.out.println("Победил крестик. Спасибо за игру!");
			System.out.println();
			isCrossWin = true;
		}
		else if (!isCrossMove && didAnyoneWin){
			System.out.println();
			System.out.println("Победил нолик. Спасибо за игру!");
			System.out.println();
			isCrossWin = false;
		}
	}
}

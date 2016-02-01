import java.util.Scanner;

public class Connect4 {
	public static final char NONE = ' ';
	public static final char RED = 'O';
	public static final char BLUE = 'X';
	;
	public static final int ROWS = 6;
	public static final int COLUMNS = 7;
	
	public static final int GOAL = 4;
	
	char[][] board;
	
	public Connect4() {
		this.board = new char[ROWS][COLUMNS];
		
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLUMNS; j++) {
				this.board[i][j] = NONE;
			}
		}
	}
	
	public char[][] getBoard() {
			char[][] bCopy = new char[this.board.length][this.board[0].length];
			
			for(int i = 0; i < this.board.length; i++) {
				for(int j = 0; j < this.board[0].length; j++) {
					bCopy[i][j] = this.board[i][j];
				}
			}
			
			return bCopy;
	}
	
	public int putPiece(int column, char color) {
		
		if (color != RED && color != BLUE)
			return -1;
		
		if (column < 0 || column >= this.board[0].length)
			return -1;
		
		int row = 0;
		while (row < this.board.length && this.board[row][column] == NONE) {
			row++;
		}
		
		if(row == 0) {//check if column is full
			return -1;
		}
		
		this.board[row - 1][column] = color;
		return row - 1;
	}
	
	public void printScreen() {
		System.out.printf("\n ");
		for(int i = 0; i < board[0].length; ++i) {
			System.out.printf("  %d", i);
		}
		System.out.println();
		
		System.out.printf("   ");
		for(int i = 0; i < board[0].length; ++i) {
			System.out.printf("---");
		}
		System.out.println("-");
		
		for(int i = 0; i < board.length; ++i) {
			System.out.printf("%c ", 'A' + i);
			for(int k = 0; k < board[0].length; ++k) {
				System.out.printf("| %c", board[i][k]);
			}
		System.out.println("|");
			
		System.out.printf("   ");
		for(int k = 0; k < board[0].length; ++k) {
			System.out.printf("---");
		}
		System.out.println("-");
		}
		
	}
	
	public int checkAlignment(int row, int column) {
		if (!isInBound(row, column))
			return NONE;
		
		int color = this.auxCheckAlignment(row, column, 1, 0);
		if (color != NONE)
			return color;
		
		color = this.auxCheckAlignment(row, column, 0, 1);
		if (color != NONE)
			return color;
		
		color = this.auxCheckAlignment(row, column, 1, 1);
		if (color != NONE)
			return color;
		
		color = this.auxCheckAlignment(row, column, -1, 1);
		if (color != NONE)
			return color;
		
		return NONE;
	}
	
	private int auxCheckAlignment(int row, int column, int dr, int dc){
		int i = dr;
		int j = dc;
		int nbAligned = 1;
		char color = this.board[row][column];
		
		if(color == NONE)
			return NONE;
		
		while(isInBound(row + i, column + j) && this.board[row + i][column + j] == color) {
			i += dr;
			j += dc;
			nbAligned++;
		}
		
		//reset
		i = dr;
		j= dc;
		while(isInBound(row - i, column - j) && this.board[row - i][column - j] == color) {
			i += dr;
			j += dc;
			nbAligned++;
		}
		
		if(nbAligned >= GOAL)
			return color;
		
		return NONE;
	}
	
	public boolean isInBound(int row, int column) {
		return row >= 0 && column >=0 && row < this.board.length && column < this.board[0].length;
	}
	
	public void play() {
		char currentPlayer = RED;
		
		Scanner in = new Scanner(System.in);
		int col = -1;
		int row = -1;
		
		do {
			currentPlayer = currentPlayer == RED ? BLUE : RED;
			this.printScreen();
			System.out.printf("Current player: '%c'\n", currentPlayer);
			
			//read and validate the input
			col = -1;
			row = -1;
			
			do {
				System.out.printf("Choose a column: ");
				String line = in.nextLine();
				
				if(line == null || line.length() != 1) {
					continue;
				}
				
				col = line.charAt(0) - '0';
				row = this.putPiece(col, currentPlayer);
				
			}while(row < 0);
		
		}while(this.checkAlignment(row, col) == NONE);
		
		this.printScreen();
		System.out.printf("\n!!!Winner is Player '%c' !!!", currentPlayer);
		in.close();
		
	}
}

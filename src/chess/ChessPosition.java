package chess;

import boardgame.Position;

public class ChessPosition {
	
	private char column;
	private int row;
	

	public ChessPosition(char column, int row) {
		if(column < 'a' || column > 'h' || row < 1 || row > 8) {
			throw new ChessException("Error instantiating ChessPosition. Valid values are from a1 to h8.");
		}
		this.column = column;
		this.row = row;
	}
	public char getColumn() {
		return column;
	}
	public int getRow() {
		return row;
	}

	protected Position toPosition() {
		int row1, column1;
		row1 = 8 - row;
		column1 = column - 97;
		return new Position(row1, column1);
	}
	
	protected static ChessPosition fromPosition(Position position) {
		int row1;
		char column1;
		row1 = 8 - position.getRow();
		column1 = (char) (position.getColumn() + 97);
		return new ChessPosition(column1, row1);
	}

	@Override
	
	public String toString() {
		return "" + column + row;
	}
}

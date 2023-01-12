package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	public King(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "K";
	}

	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p == null || p.getColor() != getColor(); 
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0,0);
		
		for(p.setRow(position.getRow() - 1); p.getRow() <= position.getRow() + 1 ; p.setRow(p.getRow() + 1)){
			for(p.setColumn(position.getColumn() - 1); p.getColumn() <= position.getColumn() + 1 ; p.setColumn(p.getColumn() + 1)){
				if(getBoard().positionExists(p) && canMove(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}
			}
		}
		mat[position.getRow()][position.getColumn()] = false;
		return mat;
	}

}

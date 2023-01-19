package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	private ChessMatch chessMatch;
	
	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}
	
	@Override
	public String toString() {
		return "K";
	}

	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p == null || p.getColor() != getColor(); 
	}
	
	private boolean testRookCastling(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
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
		
		//Specialmove castling
		if(getMoveCount() == 0 && !chessMatch.getCheck()) {
			//Castling kingside rook
			p.setValues(position.getRow(), position.getColumn() + 3);
			if (testRookCastling(p)) {
				Position p1 = new Position(position.getRow(), position.getColumn() + 1);
				Position p2 = new Position(position.getRow(), position.getColumn() + 2);
				if(getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
					mat[p2.getRow()][p2.getColumn()] = true;
				}
			}
			
			p.setValues(position.getRow(), position.getColumn() - 4);
			if (testRookCastling(p)) {
				Position p1 = new Position(position.getRow(), position.getColumn() - 1);
				Position p2 = new Position(position.getRow(), position.getColumn() - 2);
				Position p3 = new Position(position.getRow(), position.getColumn() - 3);
				if(getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
					mat[p2.getRow()][p2.getColumn()] = true;
				}
			}
		}
		
		
		return mat;
	}

}

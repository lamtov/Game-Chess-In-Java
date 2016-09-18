package core;

import chessPieces.Pieces;

public class Move {
	private Pieces pieces;
	private int numberNext;

	public Move(Pieces pieces, int numberNext) {
		this.pieces = pieces;
		this.numberNext = numberNext;
	}

	public Pieces getPieces() {
		return pieces;
	}

	public int getNumberNext() {
		return numberNext;
	}

	public void show() {
		String[] listLocationX = { "a", "b", "c", "d", "e", "f", "g", "h" };
		int numberlocationX = numberNext % 8;
		String locationX = listLocationX[numberlocationX];
		int locationY = (int) (numberNext / 8) + 1;
	}

	public String toString() {
		String[] listLocationX = { "a", "b", "c", "d", "e", "f", "g", "h" };
		int numberlocationX = numberNext % 8;
		String locationX = listLocationX[numberlocationX];
		int locationY = (int) (numberNext / 8) + 1;
		return pieces.getInformation() + "--->" + "[" + locationX + "," + locationY + "]";
	}

	public String toText() {
		String[] listLocationX = { "a", "b", "c", "d", "e", "f", "g", "h" };
		int numberlocationX = numberNext % 8;
		String locationX = listLocationX[numberlocationX];
		int locationY = (int) (numberNext / 8) + 1;
		return pieces.getLocationX() + pieces.getLocationY() + "->" + locationX + locationY;
	}

	public boolean isFushion() {
		if (this.getPieces().getName() == "Tuong") {
			if (this.getPieces().getNumberInBoard() == 4) {
				if (this.getNumberNext() == 6 || this.getNumberNext() == 2) {
					return true;
				}
			}
			if (this.getPieces().getNumberInBoard() == 60) {
				if (this.getNumberNext() == 62 || this.getNumberNext() == 58) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isNewQueen() {
		if (this.getPieces().getName() == "Tot") {
			if ((int) (this.getNumberNext() / 8) == 0 || (int) (this.getNumberNext() / 8) == 7) {
				return true;
			}
		}
		return false;
	}
}

package chessPieces;

import java.util.ArrayList;

import core.PositionBoard;

public class Rook extends Pieces {
	public Rook(int numberInBoard, String color, String side, PositionBoard positionBoard) {
		super(numberInBoard, color, side, positionBoard);
		this.name = "Xe";
		setInformation();
		this.setPowerBonuses();
	}

	@Override
	public void setPowerBonuses() {
		if (this.side.equals("Down")) {
			int[] bonuses = { 0, 0, 0, 5, 5, 0, 0, 0, -5, 0, 0, 0, 0, 0, 0, -5, -5, 0, 0, 0, 0, 0, 0, -5, -5, 0, 0, 0,
					0, 0, 0, -5, -5, 0, 0, 0, 0, 0, 0, -5, -5, 0, 0, 0, 0, 0, 0, -5, 5, 10, 10, 10, 10, 10, 10, 5, 0, 0,
					0, 0, 0, 0, 0, 0 };
			this.powerBonuses = bonuses;
		} else {
			int[] bonuses = { 0, 0, 0, 0, 0, 0, 0, 0, 5, 10, 10, 10, 10, 10, 10, 5, -5, 0, 0, 0, 0, 0, 0, -5, -5, 0, 0,
					0, 0, 0, 0, -5, -5, 0, 0, 0, 0, 0, 0, -5, -5, 0, 0, 0, 0, 0, 0, -5, -5, 0, 0, 0, 0, 0, 0, -5, 0, 0,
					0, 5, 5, 0, 0, 0 };
			this.powerBonuses = bonuses;
		}

	}

	public void setDefaulsePower() {
		defaulsePower = 500;
	}

	@Override
	public void setNumberCanMove() {
		ArrayList<Integer> move = new ArrayList<>();
		int listMove1[] = { 1, 2, 3, 4, 5, 6, 7 };
		int listMove2[] = { 8, 16, 24, 32, 40, 48, 56 };
		int listMove3[] = { -1, -2, -3, -4, -5, -6, -7 };
		int listMove4[] = { -8, -16, -24, -32, -40, -48,-56 };
		if (numberInBoard % 8 != 7) {
			for (int i = 0; i < listMove1.length; i++) {
				if (numberInBoard + listMove1[i] > 63 || (numberInBoard + listMove1[i] < 0)) {
					break;
				}
				if (((listMove1[i] + numberInBoard) % 8 != numberInBoard % 8)
						&& ((int) ((listMove1[i] + numberInBoard) / 8) != (int) numberInBoard / 8)) {
					continue;
				}
				if (this.getSide().equals("Down")) {
					// day la nguoi choi
					if (!positionBoard.wasSetHuman(listMove1[i] + numberInBoard)) {
						move.add(listMove1[i] + numberInBoard);
						if (positionBoard.wasSetAi(listMove1[i] + numberInBoard)) {
							break;
						}
					} else {
						break;
					}
				} else {
					// day la may
					if (!positionBoard.wasSetAi(numberInBoard + listMove1[i])) {
						move.add(listMove1[i] + numberInBoard);
						if (positionBoard.wasSetHuman(numberInBoard + listMove1[i])) {
							break;
						}
					} else {
						break;
					}
				}
			}
		}
		if ((int) (numberInBoard / 8)   != 7) {
			for (int i = 0; i < listMove2.length; i++) {
				if (numberInBoard + listMove2[i] > 63 || (numberInBoard + listMove2[i] < 0)) {
					break;
				}
				if (((listMove2[i] + numberInBoard) % 8 != numberInBoard % 8)
						&& ((int) ((listMove2[i] + numberInBoard) / 8) != (int) numberInBoard / 8)) {
					continue;
				}
				if (this.getSide().equals("Down")) {
					// day la nguoi choi
					if (!positionBoard.wasSetHuman(listMove2[i] + numberInBoard)) {
						move.add(listMove2[i] + numberInBoard);
						if (positionBoard.wasSetAi(listMove2[i] + numberInBoard)) {
							break;
						}
					} else {
						break;
					}
				} else {
					// day la may
					if (!positionBoard.wasSetAi(numberInBoard + listMove2[i])) {
						move.add(listMove2[i] + numberInBoard);
						if (positionBoard.wasSetHuman(numberInBoard + listMove2[i])) {
							break;
						}
					} else {
						break;
					}
				}
			}
		}
		if ((int) (numberInBoard / 8) != 0) {
			for (int i = 0; i < listMove4.length; i++) {
				if (numberInBoard + listMove4[i] > 63 || (numberInBoard + listMove4[i] < 0)) {
					break;
				}
				if (((listMove4[i] + numberInBoard) % 8 != numberInBoard % 8)
						&& ((int) ((listMove4[i] + numberInBoard) / 8) != (int) numberInBoard / 8)) {
					continue;
				}
				if (this.getSide().equals("Down")) {
					// day la nguoi choi
					if (!positionBoard.wasSetHuman(listMove4[i] + numberInBoard)) {
						move.add(listMove4[i] + numberInBoard);
						if (positionBoard.wasSetAi(listMove4[i] + numberInBoard)) {
							break;
						}
					} else {
						break;
					}
				} else {
					// day la may
					if (!positionBoard.wasSetAi(numberInBoard + listMove4[i])) {
						move.add(listMove4[i] + numberInBoard);
						if (positionBoard.wasSetHuman(numberInBoard + listMove4[i])) {
							break;
						}
					} else {
						break;
					}
				}
			}
		}
		if (numberInBoard % 8 != 0) {
			for (int i = 0; i < listMove3.length; i++) {
				if (numberInBoard + listMove3[i] > 63 || (numberInBoard + listMove3[i] < 0)) {
					break;
				}
				if (((listMove3[i] + numberInBoard) % 8 != numberInBoard % 8)
						&& ((int) ((listMove3[i] + numberInBoard) / 8) != (int) numberInBoard / 8)) {
					continue;
				}
				if (this.getSide().equals("Down")) {
					// day la nguoi choi
					if (!positionBoard.wasSetHuman(listMove3[i] + numberInBoard)) {
						move.add(listMove3[i] + numberInBoard);
						if (positionBoard.wasSetAi(listMove3[i] + numberInBoard)) {
							break;
						}
					} else {
						break;
					}
				} else {
					// day la may
					if (!positionBoard.wasSetAi(numberInBoard + listMove3[i])) {
						move.add(listMove3[i] + numberInBoard);
						if (positionBoard.wasSetHuman(numberInBoard + listMove3[i])) {
							break;
						}
					} else {
						break;
					}
				}
			}
		}
		numberCanMove = move;
	}

	@Override
	public void setCurrentPower() {

		this.currentPower = this.defaulsePower + this.powerBonuses[numberInBoard];
	}

	@Override
	public Pieces copy(PositionBoard newPositionBoard) {
		return new Rook(this.numberInBoard, this.color, this.side, newPositionBoard);
	}

	@Override
	public boolean equalPiece(Pieces newPieces) {
		if (this.numberInBoard == newPieces.numberInBoard) {
			if (this.name == newPieces.name) {
				if (this.side == newPieces.side) {
					if (this.color == newPieces.color) {
						return true;
					}
				}
			}
		}
		return false;
	}
}

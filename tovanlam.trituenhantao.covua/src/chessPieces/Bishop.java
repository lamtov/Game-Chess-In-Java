package chessPieces;

import java.util.ArrayList;

import core.PositionBoard;

public class Bishop extends Pieces {

	public Bishop(int numberInBoard, String color, String side,
			PositionBoard positionBoard) {
		super(numberInBoard, color, side, positionBoard);
		this.name = "Tinh";
		setInformation();
		this.setPowerBonuses();

	}

	@Override
	public void setPowerBonuses() {
		if (this.side.equals("Down")) {
			int[] bonuses = { -20, -10, -10, -10, -10, -10, -10, -20, -10, 5,
					0, 0, 0, 0, 5, -10, -10, 10, 10, 10, 10, 10, 10, -10, -10,
					0, 10, 10, 10, 10, 0, -10, -10, 5, 5, 10, 10, 5, 5, -10,
					-10, 0, 5, 10, 10, 5, 0, -10, -10, 0, 0, 0, 0, 0, 0, -10,
					-20, -10, -10, -10, -10, -10, -10, -20 };
			this.powerBonuses = bonuses;
		} else {
			int[] bonuses = { -20, -10, -10, -10, -10, -10, -10, -20, -10, 0,
					0, 0, 0, 0, 0, -10, -10, 0, 5, 10, 10, 5, 0, -10, -10, 5,
					5, 10, 10, 5, 5, -10, -10, 0, 10, 10, 10, 10, 0, -10, -10,
					10, 10, 10, 10, 10, 10, -10, -10, 5, 0, 0, 0, 0, 5, -10,
					-20, -10, -10, -10, -10, -10, -10, -20 };
			this.powerBonuses = bonuses;
		}

	}

	@Override
	public void setDefaulsePower() {
		defaulsePower = 330;
	}

	@Override
	public void setNumberCanMove() {
		ArrayList<Integer> move = new ArrayList<>();
		int listMove1[] = { 9, 18, 27, 36, 45, 54, 63 };
		int listMove2[] = { -9, -18, -27, -36, -45, -54, -63 };
		int listMove3[] = { 7, 14, 21, 28, 35, 42, 49 };
		int listMove4[] = { -7, -14, -21, -28, -35, -42, -49 };
		if (numberInBoard % 8 != 7) {
			for (int i = 0; i < listMove1.length; i++) {
				if (numberInBoard + listMove1[i] > 63
						|| (numberInBoard + listMove1[i] < 0)) {
					break;
				}
				if (((listMove1[i] + numberInBoard) % 8 == numberInBoard % 8)
						|| ((int) ((listMove1[i] + numberInBoard) / 8) == (int) numberInBoard / 8)) {
					continue;
				}
				if (this.getSide().equals("Down")) {
					if (!positionBoard
							.wasSetHuman(listMove1[i] + numberInBoard)) {
						move.add(listMove1[i] + numberInBoard);
						if (positionBoard
								.wasSetAi(listMove1[i] + numberInBoard)) {
							break;
						}
					} else {
						break;
					}
				} else {
					if (!positionBoard.wasSetAi(numberInBoard + listMove1[i])) {
						move.add(listMove1[i] + numberInBoard);
						if (positionBoard.wasSetHuman(numberInBoard
								+ listMove1[i])) {
							break;
						}
					} else {
						break;
					}
				}
				if ((numberInBoard + listMove1[i]) % 8 == 7
						|| ((numberInBoard + listMove1[i]) % 8 == 0)
						|| ((int) ((numberInBoard + listMove1[i]) / 8) == 0)
						|| ((int) ((numberInBoard + listMove1[i]) / 8) == 7)) {
					break;
				}
			}
		}
		if (numberInBoard % 8 != 0) {
			for (int i = 0; i < listMove2.length; i++) {
				if (numberInBoard + listMove2[i] > 63
						|| (numberInBoard + listMove2[i] < 0)) {
					break;
				}
				if (((listMove2[i] + numberInBoard) % 8 == numberInBoard % 8)
						|| ((int) ((listMove2[i] + numberInBoard) / 8) == (int) numberInBoard / 8)) {
					continue;
				}
				if (this.getSide().equals("Down")) {
					if (!positionBoard
							.wasSetHuman(listMove2[i] + numberInBoard)) {
						move.add(listMove2[i] + numberInBoard);
						if (positionBoard
								.wasSetAi(listMove2[i] + numberInBoard)) {
							break;
						}
					} else {
						break;
					}
				} else {
					if (!positionBoard.wasSetAi(numberInBoard + listMove2[i])) {
						move.add(listMove2[i] + numberInBoard);
						if (positionBoard.wasSetHuman(numberInBoard
								+ listMove2[i])) {
							break;
						}
					} else {
						break;
					}
				}
				if ((numberInBoard + listMove2[i]) % 8 == 7
						|| ((numberInBoard + listMove2[i]) % 8 == 0)
						|| ((int) ((numberInBoard + listMove2[i]) / 8) == 0)
						|| ((int) ((numberInBoard + listMove2[i]) / 8) == 7)) {
					break;
				}
			}
		}
		if (numberInBoard % 8 != 7) {
			for (int i = 0; i < listMove4.length; i++) {
				if (numberInBoard + listMove4[i] > 63
						|| (numberInBoard + listMove4[i] < 0)) {
					break;
				}
				if (((listMove4[i] + numberInBoard) % 8 == numberInBoard % 8)
						|| ((int) ((listMove4[i] + numberInBoard) / 8) == (int) numberInBoard / 8)) {
					continue;
				}
				if (this.getSide().equals("Down")) {
					if (!positionBoard
							.wasSetHuman(listMove4[i] + numberInBoard)) {
						move.add(listMove4[i] + numberInBoard);
						if (positionBoard
								.wasSetAi(listMove4[i] + numberInBoard)) {
							break;
						}
					} else {
						break;
					}
				} else {
					if (!positionBoard.wasSetAi(numberInBoard + listMove4[i])) {
						move.add(listMove4[i] + numberInBoard);
						if (positionBoard.wasSetHuman(numberInBoard
								+ listMove4[i])) {
							break;
						}
					} else {
						break;
					}
				}
				if ((numberInBoard + listMove4[i]) % 8 == 7
						|| ((numberInBoard + listMove4[i]) % 8 == 0)
						|| ((int) ((numberInBoard + listMove4[i]) / 8) == 0)
						|| ((int) ((numberInBoard + listMove4[i]) / 8) == 7)) {
					break;
				}
			}
		}
		if (numberInBoard % 8 != 0) {
			for (int i = 0; i < listMove3.length; i++) {
				if (numberInBoard + listMove3[i] > 63
						|| (numberInBoard + listMove3[i] < 0)) {
					break;
				}
				if (((listMove3[i] + numberInBoard) % 8 == numberInBoard % 8)
						|| ((int) ((listMove3[i] + numberInBoard) / 8) == (int) numberInBoard / 8)) {
					continue;
				}
				if (this.getSide().equals("Down")) {
					if (!positionBoard
							.wasSetHuman(listMove3[i] + numberInBoard)) {
						move.add(listMove3[i] + numberInBoard);
						if (positionBoard
								.wasSetAi(listMove3[i] + numberInBoard)) {

							break;
						}
					} else {
						break;
					}
				} else {
					if (!positionBoard.wasSetAi(numberInBoard + listMove3[i])) {
						move.add(listMove3[i] + numberInBoard);
						if (positionBoard.wasSetHuman(numberInBoard
								+ listMove3[i])) {
							break;
						}
					} else {
						break;
					}
				}
				if ((numberInBoard + listMove3[i]) % 8 == 7
						|| ((numberInBoard + listMove3[i]) % 8 == 0)
						|| ((int) ((numberInBoard + listMove3[i]) / 8) == 0)
						|| ((int) ((numberInBoard + listMove3[i]) / 8) == 7)) {
					break;
				}
			}
		}
		numberCanMove = move;
	}

	@Override
	public void setCurrentPower() {

		this.currentPower = this.defaulsePower
				+ this.powerBonuses[numberInBoard];
	}

	@Override
	public Pieces copy(PositionBoard newPositionBoard) {
		return new Bishop(this.numberInBoard, this.color, this.side,
				newPositionBoard);
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

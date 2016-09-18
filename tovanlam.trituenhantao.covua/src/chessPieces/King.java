package chessPieces;

import java.util.ArrayList;

import core.PositionBoard;

public class King extends Pieces {

	public King(int numberInBoard, String color, String side, PositionBoard positionBoard) {
		super(numberInBoard, color, side, positionBoard);
		this.name = "Tuong";
		setInformation();
		this.setPowerBonuses();

	}

	@Override
	public void setPowerBonuses() {
		if (this.side.equals("Down")) {
			int[] bonuses = { 20, 30, 10, 0, 0, 10, 30, 20, 20, 20, 0, 0, 0, 0, 20, 20, -10, -20, -20, -20, -20, -20,
					-20, -10, -20, -30, -30, -40, -40, -30, -30, -20, -30, -40, -40, -50, -50, -40, -40, -30, -30, -40,
					-40, -50, -50, -40, -40, -30, -30, -40, -40, -50, -50, -40, -40, -30, -30, -40, -40, -50, -50, -40,
					-40, -30 };
			this.powerBonuses = bonuses;
		} else {
			int[] bonuses = { -30, -40, -40, -50, -50, -40, -40, -30, -30, -40, -40, -50, -50, -40, -40, -30, -30, -40,
					-40, -50, -50, -40, -40, -30, -30, -40, -40, -50, -50, -40, -40, -30, -20, -30, -30, -40, -40, -30,
					-30, -20, -10, -20, -20, -20, -20, -20, -20, -10, 20, 20, 0, 0, 0, 0, 20, 20, 20, 30, 10, 0, 0, 10,
					30, 20 };
			this.powerBonuses = bonuses;
		}

	}

	@Override
	public void setDefaulsePower() {
		defaulsePower = 20000;
	}

	@Override
	public void setNumberCanMove() {
		ArrayList<Integer> move = new ArrayList<>();
		int listMove[] = { numberInBoard + 7, numberInBoard + 8, numberInBoard + 9, numberInBoard - 7,
				numberInBoard - 8, numberInBoard - 9, numberInBoard + 1, numberInBoard - 1 };

		if (this.getSide().equals("Down")) {
			if (!positionBoard.wasSetHuman(listMove[0]) && listMove[0] < 64 && numberInBoard % 8 != 0) {

				move.add(listMove[0]);
			}
			if (!positionBoard.wasSetHuman(listMove[2]) && listMove[2] < 64 && numberInBoard % 8 != 7) {
				move.add(listMove[2]);
			}
			if (!positionBoard.wasSetHuman(listMove[1]) && listMove[1] < 64) {
				move.add(listMove[1]);
			}
			if (!positionBoard.wasSetHuman(listMove[3]) && listMove[3] >= 0 && numberInBoard % 8 != 7) {
				move.add(listMove[3]);
			}
			if (!positionBoard.wasSetHuman(listMove[5]) && listMove[5] >= 0 && numberInBoard % 8 != 0) {
				move.add(listMove[5]);
			}
			if (!positionBoard.wasSetHuman(listMove[4]) && listMove[4] >= 0) {
				move.add(listMove[4]);
			}
			if (!positionBoard.wasSetHuman(listMove[6]) && numberInBoard % 8 != 7) {
				move.add(listMove[6]);
			}
			if (!positionBoard.wasSetHuman(listMove[7]) && numberInBoard % 8 != 0) {
				move.add(listMove[7]);
			}
			if (this.numberInBoard == 4) {
				if (!positionBoard.wasSet(5)) {
					if (!positionBoard.wasSet(6)) {
						if (positionBoard.wasSetHuman(7)) {
							if (positionBoard.getChoisePiecesHuman(7).getName() == "Xe") {
								move.add(6);
							}
						}
					}
				}
				if (!positionBoard.wasSet(3)) {
					if (!positionBoard.wasSet(2)) {
						if (!positionBoard.wasSet(1)) {
							if (positionBoard.wasSetHuman(0)) {
								if (positionBoard.getChoisePiecesHuman(0).getName() == "Xe") {
									move.add(2);
								}
							}
						}
					}
				}
			}
		} else {
			if (!positionBoard.wasSetAi(listMove[0]) && listMove[0] < 64 && numberInBoard % 8 != 0) {
				move.add(listMove[0]);
			}
			if (!positionBoard.wasSetAi(listMove[2]) && listMove[2] < 64 && numberInBoard % 8 != 7) {
				move.add(listMove[2]);
			}
			if (!positionBoard.wasSetAi(listMove[1]) && listMove[1] < 64) {
				move.add(listMove[1]);
			}
			if (!positionBoard.wasSetAi(listMove[3]) && listMove[3] >= 0 && numberInBoard % 8 != 7) {
				move.add(listMove[3]);
			}
			if (!positionBoard.wasSetAi(listMove[5]) && listMove[5] >= 0 && numberInBoard % 8 != 0) {
				move.add(listMove[5]);
			}
			if (!positionBoard.wasSetAi(listMove[4]) && listMove[4] >= 0) {
				move.add(listMove[4]);
			}
			if (!positionBoard.wasSetAi(listMove[6]) && numberInBoard % 8 != 7) {
				move.add(listMove[6]);
			}
			if (!positionBoard.wasSetAi(listMove[7]) && numberInBoard % 8 != 0) {
				move.add(listMove[7]);

			}
			if (this.numberInBoard == 60) {
				if (!positionBoard.wasSet(61)) {
					if (!positionBoard.wasSet(62)) {
						if (positionBoard.wasSetAi(63)) {
							if (positionBoard.getChoisePiecesAi(63).getName() == "Xe") {
								move.add(62);
							}
						}
					}
				}
				if (!positionBoard.wasSet(59)) {
					if (!positionBoard.wasSet(58)) {
						if (!positionBoard.wasSet(57)) {
							if (positionBoard.wasSetAi(56)) {
								if (positionBoard.getChoisePiecesAi(56).getName() == "Xe") {
									move.add(58);
								}
							}
						}
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
		return new King(this.numberInBoard, this.color, this.side, newPositionBoard);
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

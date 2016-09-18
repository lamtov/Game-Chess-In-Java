package chessPieces;

import java.util.ArrayList;

import core.PositionBoard;

public class Knight extends Pieces {

	public Knight(int numberInBoard, String color, String side, PositionBoard positionBoard) {
		super(numberInBoard, color, side, positionBoard);
		this.name = "Ma";
		setInformation();
		this.setPowerBonuses();

	}

	@Override
	public void setPowerBonuses() {
		if (this.side.equals("Down")) {
			int[] bonuses = { -50, -40, -30, -30, -30, -30, -40, -50, -40, -20, 0, 5, 5, 0, -20, -40, -30, 5, 10, 15,
					15, 10, 5, -30, -30, 0, 15, 20, 20, 15, 0, -30, -30, 5, 15, 20, 20, 15, 5, -30, -30, 0, 10, 15, 15,
					10, 0, -30, -40, -20, 0, 0, 0, 0, -20, -40, -50, -40, -30, -30, -30, -30, -40, -50 };
			this.powerBonuses = bonuses;
		} else {
			int[] bonuses = { -50, -40, -30, -30, -30, -30, -40, -50, -40, -20, 0, 0, 0, 0, -20, -40, -30, 0, 10, 15,
					15, 10, 0, -30, -30, 5, 15, 20, 20, 15, 5, -30, -30, 0, 15, 20, 20, 15, 0, -30, -30, 5, 10, 15, 15,
					10, 5, -30, -40, -20, 0, 5, 5, 0, -20, -40, -50, -40, -30, -30, -30, -30, -40, -50 };
			this.powerBonuses = bonuses;
		}

	}

	@Override
	public void setDefaulsePower() {
		defaulsePower = 320;
	}

	@Override
	public void setNumberCanMove() {
		ArrayList<Integer> move = new ArrayList<>();
		int listMove[] = { numberInBoard + 6, numberInBoard + 10, numberInBoard + 15, numberInBoard + 17,
				numberInBoard - 6, numberInBoard - 10, numberInBoard - 15, numberInBoard - 17 };
		if (this.getSide().equals("Down")) {
			for (int i = 0; i < listMove.length; i++) {
				if (!positionBoard.wasSetHuman(listMove[i])) {
					if (((listMove[i] < 64)) && ((listMove[i] >=0))) {
						if ((Math.abs(listMove[i] % 8 - numberInBoard % 8) == 1
								&& Math.abs((int) (listMove[i] / 8) - (int) (numberInBoard / 8)) == 2)
								|| (Math.abs(listMove[i] % 8 - numberInBoard % 8) == 2
										&& Math.abs((int) (listMove[i] / 8) - (int) (numberInBoard / 8)) == 1)) {
							move.add(listMove[i]);
						}
					}
				}
			}
			

		} else {
			for (int i = 0; i < listMove.length; i++) {
				if (!positionBoard.wasSetAi(listMove[i])) {
					if (((listMove[i] < 64)) && ((listMove[i] >= 0))) {
						if ((Math.abs(listMove[i] % 8 - numberInBoard % 8) == 1
								&& Math.abs((int) (listMove[i] / 8) - (int) (numberInBoard / 8)) == 2)
								|| (Math.abs(listMove[i] % 8 - numberInBoard % 8) == 2
										&& Math.abs((int) (listMove[i] / 8) - (int) (numberInBoard / 8)) == 1)) {
							move.add(listMove[i]);
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
		return new Knight(this.numberInBoard, this.color,this.side, newPositionBoard);
	}
	@Override
	public boolean equalPiece(Pieces newPieces) {
		if(this.numberInBoard == newPieces.numberInBoard){
			if(this.name == newPieces.name){
				if(this.side == newPieces.side){
					if(this.color == newPieces.color){
						return true;
					}
				}
			}
		}
		return false;
	}
	
}

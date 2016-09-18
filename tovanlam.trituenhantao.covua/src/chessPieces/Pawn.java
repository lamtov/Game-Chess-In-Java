package chessPieces;

import java.util.ArrayList;

import core.PositionBoard;

public class Pawn extends Pieces {

	public Pawn(int numberInBoard, String color, String side, PositionBoard positionBoard) {
		super(numberInBoard, color, side, positionBoard);
		this.setPowerBonuses();
		this.name = "Tot";
		setInformation();
	}

	@Override
	public void setPowerBonuses() {
		if (this.side.equals("Down")) {
			int[] bonuses = { 0, 0, 0, 0, 0, 0, 0, 0
					, 5, 10, 10, -20, -20, 10, 10
					, 5, 5, -5, -10, 0, 0, -10, 
					-5, 5, 0,
					0, 0, 20, 20, 0, 0, 0, 5, 5, 10, 25, 25, 10, 5, 5, 10, 10, 20, 30, 30, 20, 10, 10, 50, 50, 50, 50,
					50, 50, 50, 50, 0, 0, 0, 0, 0, 0, 0, 0 };
			this.powerBonuses = bonuses;
		} else {
			int[] bonuses = { 0, 0, 0, 0, 0, 0, 0, 0, 50, 50, 50, 50, 50, 50, 50, 50, 10, 10, 20, 30, 30, 20, 10, 10, 5,
					5, 10, 25, 25, 10, 5, 5, 0, 0, 0, 20, 20, 0, 0, 0, 5, -5, -10, 0, 0, -10, -5, 5, 5, 10, 10, -20,
					-20, 10, 10, 5, 0, 0, 0, 0, 0, 0, 0, 0 };
			this.powerBonuses = bonuses;
		}

	}

	@Override
	public void setDefaulsePower() {
		defaulsePower = 100;
	}

	@Override
	public void setNumberCanMove() {
		ArrayList<Integer> move = new ArrayList<>();
		int listMove[] = { numberInBoard + 7, numberInBoard + 8, numberInBoard + 9, numberInBoard - 7,
				numberInBoard - 8, numberInBoard - 9, numberInBoard + 16, numberInBoard - 16 };
		if (this.getSide().equals("Down")) {
			if (positionBoard.wasSetAi(listMove[0]) && listMove[0] < 64 && numberInBoard % 8 != 0) {
				move.add(listMove[0]);
			}
			if (positionBoard.wasSetAi(listMove[2]) && listMove[2] < 64 && numberInBoard % 8 != 7) {
				move.add(listMove[2]);
			}
			if (!positionBoard.wasSet(listMove[1]) && listMove[1] < 64) {
				move.add(listMove[1]);
			}
			if (!positionBoard.wasSet(listMove[6]) && !positionBoard.wasSet(listMove[1])
					&& (int) (numberInBoard / 8) == 1) {
				move.add(listMove[6]);
			}
		} else {
			if (positionBoard.wasSetHuman(listMove[3]) && listMove[3] >= 0 && numberInBoard % 8 != 7) {
				move.add(listMove[3]);
			}
			if (positionBoard.wasSetHuman(listMove[5]) && listMove[5] >= 0 && numberInBoard % 8 != 0) {
				move.add(listMove[5]);
			}
			if (!positionBoard.wasSet(listMove[4]) && listMove[4] >= 0) {
				move.add(listMove[4]);
			}
			if (!positionBoard.wasSet(listMove[7])&&!positionBoard.wasSet(listMove[4]) && (int) (numberInBoard / 8) == 6) {
				move.add(listMove[7]);
			}
		}

		numberCanMove = move;
	}

	public boolean isDoublePawn() {
		if (this.getSide().equals("Down")) {
			for (int i = 0; i < positionBoard.getListPiecesHuman().size(); i++) {
				if (positionBoard.getListPiecesHuman().get(i).getName().equals("Pawn")) {
					if ((positionBoard.getListPiecesHuman().get(i).numberInBoard % 8 == this.getNumberInBoard() % 8)
							&& positionBoard.getListPiecesHuman().get(i).getNumberInBoard() > this.getNumberInBoard()) {
						return true;
					}
				}
			}
		} else {
			for (int i = 0; i < positionBoard.getListPiecesAi().size(); i++) {
				if (positionBoard.getListPiecesAi().get(i).getName().equals("Pawn")) {
					if ((positionBoard.getListPiecesAi().get(i).numberInBoard % 8 == this.getNumberInBoard() % 8)
							&& positionBoard.getListPiecesAi().get(i).getNumberInBoard() < this.getNumberInBoard()) {
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean isSolatedPawn(){
		if (this.getSide().equals("Down")) {
			for (int i = 0; i < positionBoard.getListPiecesHuman().size(); i++) {
				if (positionBoard.getListPiecesHuman().get(i).getName().equals("Pawn")) {
					if (((positionBoard.getListPiecesHuman().get(i).numberInBoard % 8 == this.getNumberInBoard() % 8 -1)
							|| (positionBoard.getListPiecesHuman().get(i).numberInBoard % 8 == this.getNumberInBoard() % 8 +1))
							&& ((int)(positionBoard.getListPiecesHuman().get(i).numberInBoard / 8) ==(int) (this.getNumberInBoard() / 8) -1)) {
						return false;
					}
				}
			}
		} else {
			for (int i = 0; i < positionBoard.getListPiecesAi().size(); i++) {
				if (positionBoard.getListPiecesAi().get(i).getName().equals("Pawn")) {
					if (((positionBoard.getListPiecesAi().get(i).numberInBoard % 8 == this.getNumberInBoard() % 8 -1)
							|| (positionBoard.getListPiecesAi().get(i).numberInBoard % 8 == this.getNumberInBoard() % 8 +1))
							&& ((int)(positionBoard.getListPiecesAi().get(i).numberInBoard / 8) ==(int) (this.getNumberInBoard() / 8) +1)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	public boolean isBlockedPawn() {

		setNumberCanMove();
		if (numberCanMove.size() == 0) {
			return true;
		}
		return false;

	}
	@Override
	public void setCurrentPower() {

		this.currentPower = this.defaulsePower + this.powerBonuses[numberInBoard];
		if(isBlockedPawn() || isDoublePawn() || isSolatedPawn()){
			this.currentPower = this.currentPower -50;
		}
	}
	@Override
	public Pieces copy(PositionBoard newPositionBoard) {
		return new Pawn(this.numberInBoard, this.color,this.side, newPositionBoard);
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

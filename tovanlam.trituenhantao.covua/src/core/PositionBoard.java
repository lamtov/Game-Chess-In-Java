package core;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

import chessPanel.BoardPanel;
import chessPieces.Bishop;
import chessPieces.King;
import chessPieces.Knight;
import chessPieces.Pawn;
import chessPieces.Pieces;
import chessPieces.Queen;
import chessPieces.Rook;

public class PositionBoard {
	private ArrayList<Pieces> listPiecesHuman;
	private ArrayList<Pieces> listPiecesAi;
	private int depth;

	public void setDepth(int depth) {
		this.depth = depth;
	}

	private int value;
	private Move parentMove;
	private PositionBoard bestChild;
	private GameSetting gameSetting;
	private ArrayList<PositionBoard> listChildPosition;
	private BoardPanel boardPanel;
	private PositionBoard oldPositionBoard;
	private HashMap<Integer, String> hashMapPieces;
	private boolean aiWasEat;
	private boolean humanWasEat;
	private int noBestChild;
	private int alphabeta;
	private ArrayList<PositionBoard> listAnalyzePosition;

	public PositionBoard(ArrayList<Pieces> listPiecesHuman, ArrayList<Pieces> listPiecesAi, int depth,
			GameSetting gameSetting, BoardPanel boardPanel, HashMap<Integer, String> hashMapPieces) {
		this.listAnalyzePosition = new ArrayList<>();
		this.depth = depth;
		this.gameSetting = gameSetting;
		this.listPiecesHuman = listPiecesHuman;
		this.listPiecesAi = listPiecesAi;
		this.hashMapPieces = hashMapPieces;
		listChildPosition = new ArrayList<>();
		this.boardPanel = boardPanel;
		this.oldPositionBoard = this;
		this.humanWasEat = false;
		this.aiWasEat = false;
	}

	public PositionBoard(int depth, GameSetting gameSetting, BoardPanel boardPanel) {
		this.listAnalyzePosition = new ArrayList<>();
		this.depth = depth;
		this.listPiecesHuman = new ArrayList<>();
		this.listPiecesAi = new ArrayList<>();
		this.gameSetting = gameSetting;
		this.hashMapPieces = new HashMap<>();
		addDefaulsePieces();
		listChildPosition = new ArrayList<>();
		this.boardPanel = boardPanel;
		this.oldPositionBoard = this;
		this.humanWasEat = false;
		this.aiWasEat = false;
	}

	public PositionBoard(int depth, GameSetting gameSetting) {
		this.listAnalyzePosition = new ArrayList<>();
		this.depth = depth;
		this.listPiecesHuman = new ArrayList<>();
		this.listPiecesAi = new ArrayList<>();
		this.hashMapPieces = new HashMap<>();
		this.gameSetting = gameSetting;
		listChildPosition = new ArrayList<>();
		this.oldPositionBoard = this;
	}

	public void setValue() {
		int powerHuman = 0;
		int powerAi = 0;
		for (int i = 0; i < listPiecesHuman.size(); i++) {
			listPiecesHuman.get(i).setCurrentPower();
			powerHuman = powerHuman + listPiecesHuman.get(i).getCurrentPower();
		}
		for (int i = 0; i < listPiecesAi.size(); i++) {
			listPiecesAi.get(i).setCurrentPower();
			powerAi = powerAi + listPiecesAi.get(i).getCurrentPower();
		}
		this.value = powerAi - powerHuman;
	}

	public void setListChildPosition() {
		if (this.getDepth() % 2 == gameSetting.getLevel() % 2) {
			for (int i = 0; i < this.getListPiecesAi().size(); i++) {
				this.getListPiecesAi().get(i).setNumberCanMove();
				for (int j = 0; j < this.getListPiecesAi().get(i).getNumberCanMove().size(); j++) {
					Move move = new Move(this.getListPiecesAi().get(i),
							this.getListPiecesAi().get(i).getNumberCanMove().get(j));

					if (gameSetting.isWatchMode()) {
						PositionBoard newPosition = newPositionBoard(move);
						if (!newPosition.equal(getOldPositionBoard().getOldPositionBoard().getOldPositionBoard())) {
							listChildPosition.add(newPosition);
						}
					} else {
						listChildPosition.add(newPositionBoard(move));
					}

				}
			}
		} else {
			for (int i = 0; i < this.getListPiecesHuman().size(); i++) {
				this.getListPiecesHuman().get(i).setNumberCanMove();
				for (int j = 0; j < this.getListPiecesHuman().get(i).getNumberCanMove().size(); j++) {
					Move move = new Move(this.getListPiecesHuman().get(i),
							this.getListPiecesHuman().get(i).getNumberCanMove().get(j));
					if (gameSetting.isWatchMode()) {
						PositionBoard newPosition = newPositionBoard(move);
						if (!newPosition.equal(getOldPositionBoard().getOldPositionBoard().getOldPositionBoard())) {
							listChildPosition.add(newPosition);
						}
					} else {
						listChildPosition.add(newPositionBoard(move));
					}

				}
			}
		}
	}

	public PositionBoard copy(int depth, GameSetting gameSetting) {
		PositionBoard newPositionBoard = new PositionBoard(depth, gameSetting);
		newPositionBoard.boardPanel = this.boardPanel;
		for (int i = 0; i < listPiecesHuman.size(); i++) {
			newPositionBoard.getListPiecesHuman().add(listPiecesHuman.get(i).copy(newPositionBoard));
		}
		for (int i = 0; i < listPiecesAi.size(); i++) {
			newPositionBoard.getListPiecesAi().add(listPiecesAi.get(i).copy(newPositionBoard));
		}
		newPositionBoard.parentMove = this.parentMove;
		newPositionBoard.setBestChild(this.getBestChild());
		newPositionBoard.setOldPositionBoard(this.getOldPositionBoard());
		HashMap<Integer, String> newHashMap = new HashMap<>();
		newHashMap.putAll(hashMapPieces);
		newPositionBoard.setHashMapPieces(newHashMap);

		return newPositionBoard;
	}

	public PositionBoard newPositionBoard(Move move) {

		if (boardPanel.isHumanTurn() && !gameSetting.testMode) {
			PositionBoard newPositionBoard = this.copy(gameSetting.getLevel(), gameSetting);
			for (int i = 0; i < newPositionBoard.getListPiecesHuman().size(); i++) {
				if (newPositionBoard.getListPiecesHuman().get(i).equalPiece(move.getPieces())) {
					newPositionBoard.getListPiecesHuman().get(i).setNewLocation(move.getNumberNext());
				}
			}
			if (newPositionBoard.wasSetAi(move.getNumberNext())) {
				newPositionBoard.getListPiecesAi().remove(newPositionBoard.getChoisePiecesAi(move.getNumberNext()));
				newPositionBoard.setHumanWasEat(true);
			}
			newPositionBoard.getHashMapPieces().put(move.getPieces().getNumberInBoard(), "NoPiece");
			newPositionBoard.getHashMapPieces().put(move.getNumberNext(), "Human");
			if (move.getPieces().getName() == "Tuong") {
				if (move.getPieces().getNumberInBoard() == 4) {
					if (move.getNumberNext() == 6) {
						Pieces rook = this.getChoisePiecesHuman(7);
						Move move2 = new Move(rook, 5);
						newPositionBoard = newPositionBoard.newPositionBoard(move2);
					}
					if (move.getNumberNext() == 2) {
						Pieces rook = this.getChoisePiecesHuman(0);
						Move move2 = new Move(rook, 3);
						newPositionBoard = newPositionBoard.newPositionBoard(move2);
					}
				}
			}
			if (move.getPieces().getName() == "Tot") {
				if ((int) move.getNumberNext() / 8 == 7) {
					newPositionBoard.getListPiecesHuman()
							.remove(newPositionBoard.getChoisePiecesHuman(move.getNumberNext()));
					Pieces newQueen = new Queen(move.getNumberNext(), move.getPieces().getColor(),
							move.getPieces().getSide(), newPositionBoard);
					newPositionBoard.getListPiecesHuman().add(newQueen);
				}
			}
			newPositionBoard.parentMove = move;
			newPositionBoard.setOldPositionBoard(this);
			return newPositionBoard;
		}
		if (!boardPanel.isHumanTurn() || gameSetting.testMode) {
			PositionBoard newPositionBoard = this.copy(this.depth - 1, gameSetting);
			if (newPositionBoard.getDepth() % 2 == gameSetting.getLevel() % 2) {

				for (int i = 0; i < newPositionBoard.getListPiecesHuman().size(); i++) {
					if (newPositionBoard.getListPiecesHuman().get(i).equalPiece(move.getPieces())) {
						newPositionBoard.getListPiecesHuman().get(i).setNewLocation(move.getNumberNext());
						break;
					}
				}
				if (newPositionBoard.wasSetAi(move.getNumberNext())) {
					newPositionBoard.getListPiecesAi().remove(newPositionBoard.getChoisePiecesAi(move.getNumberNext()));
					newPositionBoard.setHumanWasEat(true);
				}
				if (move.getPieces().getName() == "Tuong") {
					if (move.getPieces().getNumberInBoard() == 4) {
						if (move.getNumberNext() == 6) {
							Pieces rook = this.getChoisePiecesHuman(7);
							Move move2 = new Move(rook, 5);
							for (int i = 0; i < newPositionBoard.getListPiecesHuman().size(); i++) {
								if (newPositionBoard.getListPiecesHuman().get(i).equalPiece(move2.getPieces())) {
									newPositionBoard.getListPiecesHuman().get(i).setNewLocation(move2.getNumberNext());
									break;
								}
							}
							newPositionBoard.getHashMapPieces().put(move2.getPieces().getNumberInBoard(), "NoPiece");
							newPositionBoard.getHashMapPieces().put(move2.getNumberNext(), "Human");
						}
						if (move.getNumberNext() == 2) {
							Pieces rook = this.getChoisePiecesHuman(0);
							Move move2 = new Move(rook, 3);
							for (int i = 0; i < newPositionBoard.getListPiecesHuman().size(); i++) {
								if (newPositionBoard.getListPiecesHuman().get(i).equalPiece(move2.getPieces())) {
									newPositionBoard.getListPiecesHuman().get(i).setNewLocation(move2.getNumberNext());
									break;
								}
							}
							newPositionBoard.getHashMapPieces().put(move2.getPieces().getNumberInBoard(), "NoPiece");
							newPositionBoard.getHashMapPieces().put(move2.getNumberNext(), "Human");
						}
					}
				}
				if (move.getPieces().getName() == "Tot") {
					if ((int) move.getNumberNext() / 8 == 7) {
						newPositionBoard.getListPiecesHuman()
								.remove(newPositionBoard.getChoisePiecesHuman(move.getNumberNext()));
						Pieces newQueen = new Queen(move.getNumberNext(), move.getPieces().getColor(),
								move.getPieces().getSide(), newPositionBoard);
						newPositionBoard.getListPiecesHuman().add(newQueen);
					}
				}
				newPositionBoard.getHashMapPieces().put(move.getPieces().getNumberInBoard(), "NoPiece");
				newPositionBoard.getHashMapPieces().put(move.getNumberNext(), "Human");
			} else {
				for (int i = 0; i < newPositionBoard.getListPiecesAi().size(); i++) {
					if (newPositionBoard.getListPiecesAi().get(i).equalPiece(move.getPieces())) {
						newPositionBoard.getListPiecesAi().get(i).setNewLocation(move.getNumberNext());

					}
				}
				if (newPositionBoard.wasSetHuman(move.getNumberNext())) {
					newPositionBoard.getListPiecesHuman()
							.remove(newPositionBoard.getChoisePiecesHuman(move.getNumberNext()));
					newPositionBoard.setAiWasEat(true);
				}
				if (move.getPieces().getName() == "Tuong") {
					if (move.getPieces().getNumberInBoard() == 60) {
						if (move.getNumberNext() == 62) {

							move.toString();
							Move move2 = new Move(this.getChoisePiecesAi(63), 61);
							for (int i = 0; i < newPositionBoard.getListPiecesAi().size(); i++) {
								if (newPositionBoard.getListPiecesAi().get(i).equalPiece(move2.getPieces())) {
									newPositionBoard.getListPiecesAi().get(i).setNewLocation(move2.getNumberNext());
									break;
								}
							}
							newPositionBoard.getHashMapPieces().put(move2.getPieces().getNumberInBoard(), "NoPiece");
							newPositionBoard.getHashMapPieces().put(move2.getNumberNext(), "Ai");
						}
						if (move.getNumberNext() == 58) {

							Move move2 = new Move(this.getChoisePiecesAi(56), 59);
							for (int i = 0; i < newPositionBoard.getListPiecesAi().size(); i++) {
								if (newPositionBoard.getListPiecesAi().get(i).equalPiece(move2.getPieces())) {
									newPositionBoard.getListPiecesAi().get(i).setNewLocation(move2.getNumberNext());
									break;
								}
							}
							newPositionBoard.getHashMapPieces().put(move2.getPieces().getNumberInBoard(), "NoPiece");
							newPositionBoard.getHashMapPieces().put(move2.getNumberNext(), "Ai");
						}
					}
				}
				if (move.getPieces().getName() == "Tot") {
					if ((int) move.getNumberNext() / 8 == 0) {
						newPositionBoard.getListPiecesAi()
								.remove(newPositionBoard.getChoisePiecesAi(move.getNumberNext()));
						Pieces newQueen = new Queen(move.getNumberNext(), move.getPieces().getColor(),
								move.getPieces().getSide(), newPositionBoard);
						newPositionBoard.getListPiecesAi().add(newQueen);
					}
				}
				newPositionBoard.getHashMapPieces().put(move.getPieces().getNumberInBoard(), "NoPiece");
				newPositionBoard.getHashMapPieces().put(move.getNumberNext(), "Ai");
			}
			newPositionBoard.parentMove = move;
			newPositionBoard.setOldPositionBoard(this);
			return newPositionBoard;

		}
		return null;

	}

	public void setBoardPanel(BoardPanel boardPanel) {
		this.boardPanel = boardPanel;
	}

	public boolean isAiWasEat() {
		return aiWasEat;
	}

	public void setAiWasEat(boolean aiWasEat) {
		this.aiWasEat = aiWasEat;
	}

	public boolean isHumanWasEat() {
		return humanWasEat;
	}

	public void setHumanWasEat(boolean humanWasEat) {
		this.humanWasEat = humanWasEat;
	}

	public boolean wasSetHuman(int number) {
		boolean wasSetHuman = false;
		if (this.hashMapPieces.get(number) == "Human") {
			wasSetHuman = true;
		}
		return wasSetHuman;
	}

	public boolean wasSetAi(int number) {
		boolean wasSetAi = false;
		if (this.hashMapPieces.get(number) == "Ai") {
			wasSetAi = true;
		}
		return wasSetAi;
	}

	public boolean wasSet(int number) {
		return wasSetAi(number) || wasSetHuman(number);
	}

	public Pieces getChoisePiecesHuman(int numberInBoard) {
		for (int i = 0; i < listPiecesHuman.size(); i++) {
			if (listPiecesHuman.get(i).getNumberInBoard() == numberInBoard) {
				return listPiecesHuman.get(i);
			}
		}
		return null;
	}

	public Pieces getChoisePiecesAi(int numberInBoard) {
		for (int i = 0; i < listPiecesAi.size(); i++) {
			if (listPiecesAi.get(i).getNumberInBoard() == numberInBoard) {
				return listPiecesAi.get(i);
			}
		}
		return null;
	}

	public void draw(Graphics g) {
		for (int i = 0; i < listPiecesHuman.size(); i++) {
			listPiecesHuman.get(i).draw(g);
		}
		for (int i = 0; i < listPiecesAi.size(); i++) {
			listPiecesAi.get(i).draw(g);
		}
	}

	public void addDefaulsePieces() {
		for (int i = 0; i < 16; i++) {
			this.hashMapPieces.put(i, "Human");
		}
		for (int i = 16; i < 47; i++) {
			this.hashMapPieces.put(i, "NoPiece");
		}
		for (int i = 48; i < 64; i++) {
			this.hashMapPieces.put(i, "Ai");
		}
		if (this.gameSetting.isHumanFirst()) {
			this.listPiecesHuman.add(new Rook(0, "Trang", "Down", this));
			this.listPiecesHuman.add(new Rook(7, "Trang", "Down", this));
			this.listPiecesHuman.add(new Knight(1, "Trang", "Down", this));
			this.listPiecesHuman.add(new Knight(6, "Trang", "Down", this));
			this.listPiecesHuman.add(new Bishop(2, "Trang", "Down", this));
			this.listPiecesHuman.add(new Bishop(5, "Trang", "Down", this));
			this.listPiecesHuman.add(new Queen(3, "Trang", "Down", this));
			this.listPiecesHuman.add(new King(4, "Trang", "Down", this));
			for (int i = 8; i < 16; i++) {
				this.listPiecesHuman.add(new Pawn(i, "Trang", "Down", this));
			}
			this.listPiecesAi.add(new Rook(63, "Den", "Up", this));
			this.listPiecesAi.add(new Rook(56, "Den", "Up", this));
			this.listPiecesAi.add(new Knight(57, "Den", "Up", this));
			this.listPiecesAi.add(new Knight(62, "Den", "Up", this));
			this.listPiecesAi.add(new Bishop(58, "Den", "Up", this));
			this.listPiecesAi.add(new Bishop(61, "Den", "Up", this));
			this.listPiecesAi.add(new Queen(59, "Den", "Up", this));
			this.listPiecesAi.add(new King(60, "Den", "Up", this));
			for (int i = 48; i < 56; i++) {
				this.listPiecesAi.add(new Pawn(i, "Den", "Up", this));
			}
		} else {
			this.listPiecesHuman.add(new Rook(0, "Den", "Down", this));
			this.listPiecesHuman.add(new Rook(7, "Den", "Down", this));
			this.listPiecesHuman.add(new Knight(1, "Den", "Down", this));
			this.listPiecesHuman.add(new Knight(6, "Den", "Down", this));
			this.listPiecesHuman.add(new Bishop(2, "Den", "Down", this));
			this.listPiecesHuman.add(new Bishop(5, "Den", "Down", this));
			this.listPiecesHuman.add(new Queen(3, "Den", "Down", this));
			this.listPiecesHuman.add(new King(4, "Den", "Down", this));
			for (int i = 8; i < 16; i++) {
				this.listPiecesHuman.add(new Pawn(i, "Den", "Down", this));
			}
			this.listPiecesAi.add(new Rook(63, "Trang", "Up", this));
			this.listPiecesAi.add(new Rook(56, "Trang", "Up", this));
			this.listPiecesAi.add(new Knight(57, "Trang", "Up", this));
			this.listPiecesAi.add(new Knight(62, "Trang", "Up", this));
			this.listPiecesAi.add(new Bishop(58, "Trang", "Up", this));
			this.listPiecesAi.add(new Bishop(61, "Trang", "Up", this));
			this.listPiecesAi.add(new Queen(59, "Trang", "Up", this));
			this.listPiecesAi.add(new King(60, "Trang", "Up", this));
			for (int i = 48; i < 56; i++) {
				this.listPiecesAi.add(new Pawn(i, "Trang", "Up", this));
			}
		}
	}

	public static PositionBoard getPositionByText(GameSetting gameSetting) {
		PositionBoard positionBoard = new PositionBoard(gameSetting.getLevel(), gameSetting);

		return positionBoard;
	}

	public void free() {
		int k = listChildPosition.size();
		while (k != 0) {
			PositionBoard besChildPosition = this.getBestChild().copy(this.getBestChild().getDepth(), gameSetting);
			besChildPosition.setAiWasEat(this.getBestChild().aiWasEat);
			besChildPosition.setHumanWasEat(this.getBestChild().humanWasEat);
			try {
				listChildPosition.remove(k - 1);
				k--;
			} catch (Throwable e) {

				e.printStackTrace();
			}
			this.setBestChild(besChildPosition);
		}
	}

	public boolean checkAiWin() {
		this.setValue();
		if (this.getValue() > 10000) {
			return true;
		}
		return false;
	}

	public boolean checkHumanWin() {
		this.setValue();
		if (this.getValue() < -10000) {
			return true;
		}
		return false;
	}

	public String toTextSave() {
		String text = "";
		for (int i = 0; i < listPiecesAi.size(); i++) {
			text = text + listPiecesAi.get(i).getText() + "\n";
		}
		for (int i = 0; i < listPiecesHuman.size(); i++) {
			if (i != listPiecesHuman.size() - 1) {
				text = text + listPiecesHuman.get(i).getText() + "\n";
			} else {
				text = text + listPiecesHuman.get(i).getText();
			}
		}
		return text;
	}

	/*****************************************************************************************************************
	 * //////////// CODE ONLY USING FOR WATCH MODE
	 * /////////////////////////////////////////////////////////////////////////
	 * /////////////////////////////////////////
	 * //////////////////////////////////////////////////////////////////////
	 * /////////////////////////////////////////////////////////////////////////
	 * /////////////////////////////////////////
	 * //////////////////////////////////////////////////////////////////////
	 *****************************************************************************************************************/
	public void setListChildPositionForHumanInWatchMode() {
		if (this.getDepth() % 2 != gameSetting.getLevel() % 2) {
			for (int i = 0; i < this.getListPiecesAi().size(); i++) {
				this.getListPiecesAi().get(i).setNumberCanMove();
				for (int j = 0; j < this.getListPiecesAi().get(i).getNumberCanMove().size(); j++) {
					Move move = new Move(this.getListPiecesAi().get(i),
							this.getListPiecesAi().get(i).getNumberCanMove().get(j));
					if (gameSetting.isWatchMode()) {
						PositionBoard newPosition = newPositionBoardForHuManInWatchMode(move);
						if (!newPosition.equal(getOldPositionBoard().getOldPositionBoard().getOldPositionBoard())) {
							listChildPosition.add(newPosition);
						}
					} else {
						listChildPosition.add(newPositionBoardForHuManInWatchMode(move));
					}
				}
			}
		} else {
			for (int i = 0; i < this.getListPiecesHuman().size(); i++) {
				this.getListPiecesHuman().get(i).setNumberCanMove();
				for (int j = 0; j < this.getListPiecesHuman().get(i).getNumberCanMove().size(); j++) {
					Move move = new Move(this.getListPiecesHuman().get(i),
							this.getListPiecesHuman().get(i).getNumberCanMove().get(j));
					if (gameSetting.isWatchMode()) {
						PositionBoard newPosition = newPositionBoardForHuManInWatchMode(move);
						if (!newPosition.equal(getOldPositionBoard().getOldPositionBoard().getOldPositionBoard())) {
							listChildPosition.add(newPosition);
						}
					} else {
						listChildPosition.add(newPositionBoardForHuManInWatchMode(move));
					}
				}
			}
		}
	}

	public PositionBoard newPositionBoardForHuManInWatchMode(Move move) {

		PositionBoard newPositionBoard = this.copy(this.depth - 1, gameSetting);
		if (newPositionBoard.getDepth() % 2 != gameSetting.getLevel() % 2) {

			for (int i = 0; i < newPositionBoard.getListPiecesHuman().size(); i++) {
				if (newPositionBoard.getListPiecesHuman().get(i).equalPiece(move.getPieces())) {
					newPositionBoard.getListPiecesHuman().get(i).setNewLocation(move.getNumberNext());
					break;
				}
			}
			if (newPositionBoard.wasSetAi(move.getNumberNext())) {
				newPositionBoard.getListPiecesAi().remove(newPositionBoard.getChoisePiecesAi(move.getNumberNext()));
				newPositionBoard.setHumanWasEat(true);
			}
			if (move.getPieces().getName() == "Tuong") {
				if (move.getPieces().getNumberInBoard() == 4) {
					if (move.getNumberNext() == 6) {
						Pieces rook = this.getChoisePiecesHuman(7);
						Move move2 = new Move(rook, 5);
						for (int i = 0; i < newPositionBoard.getListPiecesHuman().size(); i++) {
							if (newPositionBoard.getListPiecesHuman().get(i).equalPiece(move2.getPieces())) {
								newPositionBoard.getListPiecesHuman().get(i).setNewLocation(move2.getNumberNext());
								break;
							}
						}
						newPositionBoard.getHashMapPieces().put(move2.getPieces().getNumberInBoard(), "NoPiece");
						newPositionBoard.getHashMapPieces().put(move2.getNumberNext(), "Human");
					}
					if (move.getNumberNext() == 2) {
						Pieces rook = this.getChoisePiecesHuman(0);
						Move move2 = new Move(rook, 3);
						for (int i = 0; i < newPositionBoard.getListPiecesHuman().size(); i++) {
							if (newPositionBoard.getListPiecesHuman().get(i).equalPiece(move2.getPieces())) {
								newPositionBoard.getListPiecesHuman().get(i).setNewLocation(move2.getNumberNext());
								break;
							}
						}
						newPositionBoard.getHashMapPieces().put(move2.getPieces().getNumberInBoard(), "NoPiece");
						newPositionBoard.getHashMapPieces().put(move2.getNumberNext(), "Human");
					}
				}
			}
			if (move.getPieces().getName() == "Tot") {
				if ((int) move.getNumberNext() / 8 == 7) {
					newPositionBoard.getListPiecesHuman()
							.remove(newPositionBoard.getChoisePiecesHuman(move.getNumberNext()));
					Pieces newQueen = new Queen(move.getNumberNext(), move.getPieces().getColor(),
							move.getPieces().getSide(), newPositionBoard);
					newPositionBoard.getListPiecesHuman().add(newQueen);
				}
			}
			newPositionBoard.getHashMapPieces().put(move.getPieces().getNumberInBoard(), "NoPiece");
			newPositionBoard.getHashMapPieces().put(move.getNumberNext(), "Human");
		} else {
			for (int i = 0; i < newPositionBoard.getListPiecesAi().size(); i++) {
				if (newPositionBoard.getListPiecesAi().get(i).equalPiece(move.getPieces())) {
					newPositionBoard.getListPiecesAi().get(i).setNewLocation(move.getNumberNext());

				}
			}
			if (newPositionBoard.wasSetHuman(move.getNumberNext())) {
				newPositionBoard.getListPiecesHuman()
						.remove(newPositionBoard.getChoisePiecesHuman(move.getNumberNext()));
				newPositionBoard.setAiWasEat(true);
			}
			if (move.getPieces().getName() == "Tuong") {
				if (move.getPieces().getNumberInBoard() == 60) {
					if (move.getNumberNext() == 62) {

						move.toString();
						Move move2 = new Move(this.getChoisePiecesAi(63), 61);
						for (int i = 0; i < newPositionBoard.getListPiecesAi().size(); i++) {
							if (newPositionBoard.getListPiecesAi().get(i).equalPiece(move2.getPieces())) {
								newPositionBoard.getListPiecesAi().get(i).setNewLocation(move2.getNumberNext());
								break;
							}
						}
						newPositionBoard.getHashMapPieces().put(move2.getPieces().getNumberInBoard(), "NoPiece");
						newPositionBoard.getHashMapPieces().put(move2.getNumberNext(), "Ai");
					}
					if (move.getNumberNext() == 58) {

						Move move2 = new Move(this.getChoisePiecesAi(56), 59);
						for (int i = 0; i < newPositionBoard.getListPiecesAi().size(); i++) {
							if (newPositionBoard.getListPiecesAi().get(i).equalPiece(move2.getPieces())) {
								newPositionBoard.getListPiecesAi().get(i).setNewLocation(move2.getNumberNext());
								break;
							}
						}
						newPositionBoard.getHashMapPieces().put(move2.getPieces().getNumberInBoard(), "NoPiece");
						newPositionBoard.getHashMapPieces().put(move2.getNumberNext(), "Ai");
					}
				}
			}
			if (move.getPieces().getName() == "Tot") {
				if ((int) move.getNumberNext() / 8 == 0) {
					newPositionBoard.getListPiecesAi().remove(newPositionBoard.getChoisePiecesAi(move.getNumberNext()));
					Pieces newQueen = new Queen(move.getNumberNext(), move.getPieces().getColor(),
							move.getPieces().getSide(), newPositionBoard);
					newPositionBoard.getListPiecesAi().add(newQueen);
				}
			}
			newPositionBoard.getHashMapPieces().put(move.getPieces().getNumberInBoard(), "NoPiece");
			newPositionBoard.getHashMapPieces().put(move.getNumberNext(), "Ai");
		}
		newPositionBoard.parentMove = move;
		newPositionBoard.setOldPositionBoard(this);
		return newPositionBoard;
	}

	public boolean equal(PositionBoard positionBoard) {
		if (gameSetting.isAcceptSame()) {
			return false;
		}
		if (this.getDepth() != gameSetting.getLevel() - 1) {
			return false;
		}
		if (positionBoard == null) {
			return false;
		}
		for (int i = 0; i < listPiecesHuman.size(); i++) {
			Pieces pice = positionBoard.getChoisePiecesHuman(listPiecesHuman.get(i).getNumberInBoard());
			if (pice == null) {
				return false;
			}
			if (!listPiecesHuman.get(i).equalPiece(pice)) {
				return false;
			}
		}
		for (int i = 0; i < listPiecesAi.size(); i++) {
			Pieces pice = positionBoard.getChoisePiecesAi(listPiecesAi.get(i).getNumberInBoard());
			if (pice == null) {
				return false;
			}
			if (!listPiecesAi.get(i).equalPiece(pice)) {
				return false;
			}
		}
		return true;
	}

	/******************************************************************************************************************
	 * /////////////////////////////////////////////////////////////////////////
	 * //////////////////////////////////////////
	 * /////////////////////////////////////////////////////////////////////////
	 * //////////////////////////////////////////
	 ******************************************************************************************************************/
	public Move getParentMove() {
		return parentMove;
	}

	public void setParentMove(Move parentMove) {
		this.parentMove = parentMove;
	}

	public PositionBoard getBestChild() {
		return bestChild;
	}

	public void setBestChild(PositionBoard bestChild) {
		this.bestChild = bestChild;
	}

	public ArrayList<PositionBoard> getListChildPosition() {
		return listChildPosition;
	}

	public ArrayList<Pieces> getListPiecesHuman() {
		return listPiecesHuman;
	}

	public ArrayList<Pieces> getListPiecesAi() {
		return listPiecesAi;
	}

	public int getDepth() {
		return depth;
	}

	public int getValue() {
		return value;
	}

	public PositionBoard getOldPositionBoard() {
		return oldPositionBoard;
	}

	public void setOldPositionBoard(PositionBoard oldPositionBoard) {
		this.oldPositionBoard = oldPositionBoard;
	}

	public HashMap<Integer, String> getHashMapPieces() {
		return hashMapPieces;
	}

	public void setHashMapPieces(HashMap<Integer, String> hashMapPieces) {
		this.hashMapPieces = hashMapPieces;
	}

	public int getNoBestChild() {
		return noBestChild;
	}

	public void setNoBestChild(int noBestChild) {
		this.noBestChild = noBestChild;
	}

	public int getAlphabeta() {
		return alphabeta;
	}

	public ArrayList<PositionBoard> getListAnalyzePosition() {
		return listAnalyzePosition;
	}

	public void setListAnalyzePosition(ArrayList<PositionBoard> listAnalyzePosition) {
		this.listAnalyzePosition = listAnalyzePosition;
	}

	public void setAlphabeta(int alphabeta) {
		this.alphabeta = alphabeta;
	}
}

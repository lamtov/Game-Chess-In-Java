package chessPieces;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import core.GameSetting;
import core.PositionBoard;

public abstract class Pieces {
	protected int numberlocationX;
	protected String[] listLocationX = { "a", "b", "c", "d", "e", "f", "g", "h" };
	protected String locationX;
	protected int locationY;
	protected int numberInBoard;
	protected ArrayList<Integer> numberCanMove;
	protected int defaulsePower;

	protected int currentPower;
	protected int[] powerBonuses;
	protected String name;
	protected String color;

	public String getColor() {
		return color;
	}

	protected String side;
	protected String information;
	protected PositionBoard positionBoard;

	public Pieces(int numberInBoard, String color, String side,
			PositionBoard positionBoard) {
		setNewLocation(numberInBoard);
		this.color = color;
		this.side = side;
		this.positionBoard = positionBoard;
		this.setDefaulsePower();
	}

	public void setNewLocation(int newNumberInBoard) {
		this.numberInBoard = newNumberInBoard;
		this.numberlocationX = newNumberInBoard % 8;
		this.locationX = listLocationX[numberlocationX];
		this.locationY = (int) (numberInBoard / 8) + 1;
	}

	public abstract void setCurrentPower();

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		ImageIcon imageBackGround = new ImageIcon(this.getClass()
				.getResource(
						"/imgchessman/" + this.name + this.color + GameSetting.typeChessman
								+ ".png"));
		g2d.drawImage(imageBackGround.getImage(), 20 + (numberlocationX)
				* (600 / 8), 25 + (8 - locationY) * (595 / 8), 600 / 8,
				595 / 8, null);

	}

	public void setInformation() {
		this.information = "(" + this.name + "-" + this.color + ": ["
				+ this.getLocationX() + "," + this.getLocationY() + "])";
	}

	public boolean canMove(int location) {
		boolean isCanMove = false;
		for (int i = 0; i < numberCanMove.size(); i++) {
			if (numberCanMove.get(i) == location) {
				isCanMove = true;
			}
		}
		return isCanMove;
	}

	public abstract void setPowerBonuses();

	public abstract void setDefaulsePower();

	public abstract void setNumberCanMove();

	public String getLocationX() {
		return locationX;
	}

	public String getInformation() {
		return information;
	}

	public int getCurrentPower() {
		return currentPower;
	}

	public int getLocationY() {
		return locationY;
	}

	public int getNumberInBoard() {
		return numberInBoard;
	}

	public ArrayList<Integer> getNumberCanMove() {
		return numberCanMove;
	}

	public int[] getPowerBonuses() {
		return powerBonuses;
	}

	public String getName() {
		return name;
	}

	public String getSide() {
		return side;
	}

	public int getDefaulsePower() {
		return defaulsePower;
	}

	public static Pieces getPiecesByText(String string,
			PositionBoard positionBoard) {
		int k = 0;
		ArrayList<String> subString = new ArrayList<>();
		for (int i = 0; i < string.length(); i++) {
			if (String.valueOf(string.charAt(i)).equals(" ")) {
				subString.add(string.substring(k, i));
				k = i + 1;
			}
		}
		switch (subString.get(0)) {
		case "Tinh":
			return new Bishop(Integer.valueOf(subString.get(3)),
					subString.get(1), subString.get(2), positionBoard);
		case "Tuong":
			return new King(Integer.valueOf(subString.get(3)),
					subString.get(1), subString.get(2), positionBoard);
		case "Ma":
			return new Knight(Integer.valueOf(subString.get(3)),
					subString.get(1), subString.get(2), positionBoard);
		case "Tot":
			return new Pawn(Integer.valueOf(subString.get(3)),
					subString.get(1), subString.get(2), positionBoard);
		case "Hau":
			return new Queen(Integer.valueOf(subString.get(3)),
					subString.get(1), subString.get(2), positionBoard);
		case "Xe":
			return new Rook(Integer.valueOf(subString.get(3)),
					subString.get(1), subString.get(2), positionBoard);
		}
		return null;
	}

	public String getText() {
		return this.name + " " + this.color + " " + this.side + " " + this.numberInBoard + " .";
	};

	public abstract Pieces copy(PositionBoard newPositionBoard);

	public abstract boolean equalPiece(Pieces newPieces);
}

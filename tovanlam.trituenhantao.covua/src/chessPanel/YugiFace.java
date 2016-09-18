package chessPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import core.Utils;
import sound.AudioPlayer;
import animation.SpriteHorizontal;

public class YugiFace extends JPanel {
	private Image image;
	private SpriteHorizontal spriteYugiStartGame;
	private SpriteHorizontal spriteYugiEndFace3;
	private SpriteHorizontal spriteYugiChange;
	private SpriteHorizontal spriteYugiEat;
	private SpriteHorizontal spriteYugiInGame;
	private SpriteHorizontal spriteYugiShock;
	private SpriteHorizontal spriteYugiShock2;
	private SpriteHorizontal spriteYugiAtk3;
	private SpriteHorizontal spriteYugiEndFace;
	private SpriteHorizontal spriteYugiEndFace2;
	private SpriteHorizontal spriteYugiShock3;
	private SpriteHorizontal spriteYugiShock4;
	private SpriteHorizontal spriteYugiShock5;
	private SpriteHorizontal spriteYugiAtEndYourTurn;
	private SpriteHorizontal spriteYugiAtk4;
	private SpriteHorizontal spriteYugiAtk5;
	private SpriteHorizontal spriteYugiAtEndYourTurn2;
	private SpriteHorizontal yugiSprite;

	public YugiFace() throws IOException {
		this.setBorder(new LineBorder(Color.LIGHT_GRAY));
		this.setBounds(Utils.BOARD_GAME_WIDTH+5, 0, 345, 120);
		this.setBackground(Color.CYAN);
		spriteYugiEndFace3 = new SpriteHorizontal("YugiAtk2.jpg", 1, 1500);
		spriteYugiAtk3 = new SpriteHorizontal("YugiAtk3.jpg", 1, 1500);
		spriteYugiEndFace = new SpriteHorizontal("YugiEndFace.jpg", 1, 1500);
		spriteYugiEndFace2 = new SpriteHorizontal("YugiEndFace2.jpg", 1, 1500);
		spriteYugiShock3 = new SpriteHorizontal("YugiShock3.jpg", 1, 1500);
		spriteYugiShock4 = new SpriteHorizontal("YugiShock4.jpg", 1, 1500);
		spriteYugiShock5 = new SpriteHorizontal("YugiShock5.png", 1, 1500);
		spriteYugiAtEndYourTurn = new SpriteHorizontal("YugiAtEndYourTurn.jpg", 1, 1500);
		spriteYugiAtk4 = new SpriteHorizontal("YugiAtk4.png", 16, 2000);
		spriteYugiAtk5 = new SpriteHorizontal("YugiAtk5.png", 18, 2300);
		spriteYugiAtEndYourTurn2 = new SpriteHorizontal("YugiAtEndYourTurn2.png", 3, 250);
		spriteYugiStartGame = new SpriteHorizontal("YugiStart.png", 18, 4500);

		spriteYugiChange = new SpriteHorizontal("YugiChange.png", 12, 2000);
		spriteYugiEat = new SpriteHorizontal("YugiEat.png", 11, 2000);
		spriteYugiInGame = new SpriteHorizontal("YugiInGame.png", 1, 100000);
		spriteYugiShock = new SpriteHorizontal("YugiShock.jpg", 1, 1500);
		spriteYugiShock2 = new SpriteHorizontal("YugiShock2.jpg", 1, 1500);

		Timer timer = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
		});
		timer.start();
		setFaceStart();
	}

	public void setFaceStart() {
		yugiSprite = spriteYugiStartGame;
		spriteYugiStartGame.setBeforeTime();
		AudioPlayer.playStartGame();
		spriteYugiStartGame.reset();
	}

	public void setFaceAtk() {
		Random rd = new Random();
		int r = rd.nextInt(4);
		switch (r) {
		case 0:
			yugiSprite = spriteYugiEat;
			break;
		case 1:
			yugiSprite = spriteYugiChange;
			break;
		case 2:
			yugiSprite = spriteYugiAtk4;
			break;
		case 3:
			yugiSprite = spriteYugiAtk5;
			break;
		}
		yugiSprite.setBeforeTime();
		yugiSprite.reset();
	}

	public void setFaceAtEndYourTurn() {
		Random rd = new Random();
		int r = rd.nextInt(3);
		switch (r) {
		case 0:
			yugiSprite = spriteYugiAtk3;
			break;
		case 1:
			yugiSprite = spriteYugiAtEndYourTurn;
			break;
		case 2:
			yugiSprite = spriteYugiAtEndYourTurn2;
			break;
		}
		yugiSprite.setBeforeTime();
		yugiSprite.reset();
	}

	public void setFaceShock() {
		Random rd = new Random();
		int r = rd.nextInt(5);
		switch (r) {
		case 0:
			yugiSprite = spriteYugiShock;
			break;
		case 1:
			yugiSprite = spriteYugiShock2;
			break;
		case 2:
			yugiSprite = spriteYugiShock3;
			break;
		case 3:
			yugiSprite = spriteYugiShock4;
			break;
		case 4:
			yugiSprite = spriteYugiShock5;
			break;
		}
		yugiSprite.setBeforeTime();
		yugiSprite.reset();
	}

	public void setFaceEndFace() {
		Random rd = new Random();
		int r = rd.nextInt(3);
		switch (r) {
		case 0:
			yugiSprite = spriteYugiEndFace;
			break;
		case 1:
			yugiSprite = spriteYugiEndFace2;
			break;
		case 2:
			yugiSprite = spriteYugiEndFace3;
			break;

		}
		yugiSprite.setBeforeTime();
		yugiSprite.reset();

	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2D = (Graphics2D) g;
		yugiSprite.nextFrame();
		g2D.drawImage(yugiSprite.getListFrame().get(yugiSprite.getCurrentFrame()).getImageFrame(),
				(int) yugiSprite.getX0(), (int) yugiSprite.getY0(), yugiSprite.getWidthInPanel(),
				yugiSprite.getHeightInPanel(), null);
		if (yugiSprite.isEndShow()) {
			yugiSprite.reset();
			yugiSprite.setBeforeTime();
			yugiSprite = spriteYugiInGame;
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Dimension d = getSize();
		g.drawImage(Utils.IMG_PANEL, 0, 0, d.width, d.height, null);
		setOpaque(false);
		super.paintComponent(g);
	}
}

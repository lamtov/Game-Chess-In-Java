package main;

import gameframe.MenuFrame;

import java.io.File;

import sound.AudioPlayer;
import core.GameSetting;
import core.Utils;

public class MainGame {
	public static void main(String[] args) {
		GameSetting gameSetting = new GameSetting();
		new MenuFrame(gameSetting);
		new File(Utils.getCurrentDirectory() + "\\image").mkdirs();
		AudioPlayer ado = new AudioPlayer(gameSetting);
		createPicture();
	}
	
	public static void createPicture() {
		Utils.createPicture("YugiAtEndYourTurn.jpg");
		Utils.createPicture("YugiAtEndYourTurn2.png");
		Utils.createPicture("YugiAtk2.jpg");
		Utils.createPicture("YugiAtk3.jpg");
		Utils.createPicture("YugiAtk4.png");
		Utils.createPicture("YugiAtk5.png");
		Utils.createPicture("YugiBia.gif");
		Utils.createPicture("YugiChange.png");
		Utils.createPicture("YugiEat.png");
		Utils.createPicture("YugiEndFace.jpg");
		Utils.createPicture("YugiEndFace2.jpg");
		Utils.createPicture("YugiInGame.png");
		Utils.createPicture("YugiShock.jpg");
		Utils.createPicture("YugiShock2.jpg");
		Utils.createPicture("YugiShock3.jpg");
		Utils.createPicture("YugiShock4.jpg");
		Utils.createPicture("YugiShock5.png");
		Utils.createPicture("YugiStart.png");
	}
}

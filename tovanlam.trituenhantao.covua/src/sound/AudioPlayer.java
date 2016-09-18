package sound;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import chessPieces.Pieces;
import core.GameSetting;
import core.Utils;

public class AudioPlayer {
	public static GameSetting gameSetting;
	private Sound backgroundSound;
	private boolean isBG;
	private Sound backgroundSound2;
	public static boolean wasTalkInHumanTurn = false;

	public AudioPlayer(GameSetting gameSetting) {
		this.gameSetting = gameSetting;
		backgroundSound = new Sound("menu.wav");
		backgroundSound.start();
		backgroundSound2 = new Sound("m_duel1.wav");
		backgroundSound2.start();
		backgroundSound2.suspend();

		Timer timer = new Timer(100, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (gameSetting.isSoundBackground()) {
					if (Utils.inGame) {
						backgroundSound2.resume();
						backgroundSound.suspend();
					} else {
						backgroundSound.resume();
						backgroundSound2.suspend();
					}

				}
				if (!gameSetting.isSoundBackground()) {
					backgroundSound.suspend();
					backgroundSound2.suspend();
				}
			}
		});
		timer.start();
	}

	public static void playStartGame() {
		Random rd = new Random();
		int r = rd.nextInt(3);
		switch (r) {
		case 0:
			startSound("joey_start1.wav");
			break;
		case 1:
			startSound("joey_start02.wav");
			break;
		case 2:
			startSound("joey_start03.wav");
			break;
		}
	}

	public static void playClick() {
		startSound("soundClick.wav");
	}

	public static void playClickStart() {
		startSound("start.wav");
	}

	public static void playWin() {
		startSound("Win.wav");
		Random rd = new Random();
		int r = rd.nextInt(2);
		switch (r) {
		case 0:
			startSound("joey_Death.wav");
			break;
		case 1:
			startSound("joey_Lose.wav");
			break;
		}

	}

	public static void playHumanFushion() {
		startSound("joey_aFushion.wav");
	}

	public static void playAiFushion() {
		startSound("joey_Fushion.wav");
	}

	public static void playNewHau() {
		startSound("newHau.wav");
	}

	public static void playChoise() {
		startSound("choise.mid");
	}

	public static void playLose() {
		startSound("Lose.wav");
	}

	public static void playHumanMove() {
		startSound("HumanMove.mid");
	}

	public static void playAiMove() {
		startSound("AiMove.mid");
	}

	/////////////////////////////
	public static void playYugiEat() {
		if (!AudioPlayer.wasTalkInHumanTurn) {
			Random rd = new Random();
			int r = rd.nextInt(14);
			switch (r) {
			case 0:
				startSound("This is Sparta .wav");
				break;
			case 1:
				startSound("joey_Eat1.wav");
				break;
			case 2:
				startSound("This is where they die.wav");
				break;
			case 3:
				startSound("This is where we fight.wav");
				break;
			case 4:
				startSound("joey_Eat1.wav");
				break;
			case 5:
				startSound("joey_Eat1.wav");
				break;
			case 6:
				startSound("joey_Eat1.wav");
				break;
			case 7:
				startSound("joey_Eat1.wav");
				break;
			case 8:
				startSound("joey_Eat1.wav");
				break;

			case 9:
				startSound("joey_Eat1.wav");
				break;
			case 10:
				startSound("joey_Eat1.wav");
				break;
			case 11:
				startSound("joey_Eat1.wav");
				break;
			case 12:
				startSound("joey_Eat2.wav");
				break;
			case 13:
				startSound("joey_Eat2.wav");
				break;
			}
		}
		AudioPlayer.wasTalkInHumanTurn = false;
	}

	public static void playYugiEndYourTurn() {

		Random rd = new Random();
		int r = rd.nextInt(9);
		switch (r) {
		case 0:
			startSound("joey_EndYourTurn.wav");
			break;
		case 1:
			startSound("joey_EndYourTurn.wav");
			break;

		case 2:
			startSound("joey_Thislookbad.wav");

			break;

		case 3:
			startSound("joey_Thislookbad.wav");

			break;
		case 4:

			break;
		case 5:

			break;
		case 6:
			break;
		case 7:

			break;

		case 8:

			break;
		}

	}

	public static void playYugiEndTurn() {
		if (!AudioPlayer.wasTalkInHumanTurn) {
			Random rd = new Random();
			int r = rd.nextInt(6);
			switch (r) {
			case 0:
				startSound("joey_EndTurn2.wav");
				break;
			case 1:
				startSound("joey_EndTurn2.wav");
				break;

			case 2:
				startSound("joey_EndTurn2.wav");
				break;

			case 3:
				startSound("joey_EndTurn2.wav");
				break;
			case 4:
				startSound("joey_EndTurn2.wav");
				break;
			case 5:

				startSound("joey_EndTurn2.wav");
				break;

			}
		}
		AudioPlayer.wasTalkInHumanTurn = false;
	}

	public static void playYugiSock() {

		Random rd = new Random();
		int r = rd.nextInt(14);
		switch (r) {
		case 3:
			startSound("joey_Ah Not.wav");
			break;
		case 7:
			startSound("joey_Ahas.wav");
			AudioPlayer.wasTalkInHumanTurn = true;
			break;
		case 8:
			startSound("joey_AhNO.wav");

			break;
		case 0:
			startSound("joey_Shock1.wav");

			break;
		case 4:
			startSound("joey_Shock2.wav");

			break;
		case 5:
			startSound("joey_Thislookbad.wav");

			break;
		case 6:
			startSound("joey_Uw.wav");
			break;
		case 1:
			startSound("joey_Shock1.wav");
			break;
		case 2:
			startSound("joey_Shock2.wav");
			break;

		case 9:
			startSound("joey_Uw.wav");
			break;
		case 10:
			startSound("joey_Shock1.wav");
			break;
		case 11:
			startSound("joey_Shock2.wav");
			break;
		case 12:
			startSound("joey_Shock1.wav");
			break;
		case 13:
			startSound("joey_Shock2.wav");
			break;
		}

	}

	public static void playPieces(Pieces pieces) {
		switch (pieces.getName()) {
		case "Tinh":
			startSound("tinh.wav");
			break;
		case "Hau":
			startSound("hau.wav");
			break;
		case "Xe":
			startSound("xe.wav");
			break;
		case "Ma":
			startSound("ma.wav");
			break;
		default:
			AudioPlayer.playYugiEndTurn();
		}
	}

	public static void playYugiAttack() {
		startSound("yugiattack.wav");
	}

	public static void playHumanAttack() {
		startSound("humanattack.wav");
	}

	public static void startSound(String soundName) {
		if (gameSetting.isSoundGame()) {
			Sound sound = new Sound(soundName, 1);
			sound.start();
		}
	}
}

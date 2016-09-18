package core;

import java.awt.Color;

public class GameSetting {
	private int level;
	private boolean humanFirst;
	private boolean aiFirst;
	private Color backgroundColor;
	private boolean aiPlay;
	private boolean watchMode;
	private boolean soundBackground;
	private boolean soundGame;
	private boolean acceptSame;
	// ////////////////////////////////
	private boolean testing;
	public static int typeChessman = 1;
	public static int rootLevel;
	public boolean testMode;
	// /////////////////////////////////
	public GameSetting(int level, boolean humanFirst, Color backgroundColor, boolean aiPlay, boolean watchMode,
			boolean soundBackground, boolean soundGame, int typeChessman) {
		this.level = level;
		GameSetting.rootLevel = this.level;
		this.humanFirst = humanFirst;
		this.aiFirst = !humanFirst;
		this.backgroundColor = backgroundColor;
		this.aiPlay = aiPlay;
		this.watchMode = watchMode;
		this.soundBackground = soundBackground;
		this.soundGame = soundGame;
		GameSetting.typeChessman = typeChessman;
	}

	public GameSetting() {
		this.level = 4;
		GameSetting.rootLevel = this.level;
		this.humanFirst = true;
		this.aiFirst = false;
		this.backgroundColor = new Color(0xFF6600);
		this.aiPlay = true;
		this.watchMode = false;
		this.soundBackground = true;
		this.soundGame = true;
		this.acceptSame = true;
		// ////////////////////////
		this.testing = false;
		// //////////////////////
	}

	// ////////////////////////////////////////
	public boolean isTesting() {
		return testing;
	}

	public void setTesting(boolean testing) {
		this.testing = testing;
	}

	// //////////////////////////////////////
	public boolean isAcceptSame() {
		return acceptSame;
	}

	public void setAcceptSame(boolean acceptSame) {
		this.acceptSame = acceptSame;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isHumanFirst() {
		return humanFirst;
	}

	public void setHumanFirst(boolean humanFirst) {
		this.humanFirst = humanFirst;
	}

	public boolean isAiFirst() {
		return aiFirst;
	}

	public void setAiFirst(boolean aiFirst) {
		this.aiFirst = aiFirst;
	}

	public Color getBackgroundColor() {
		return backgroundColor;

	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;

	}

	public boolean isAiPlay() {
		return aiPlay;
	}

	public void setAiPlay(boolean aiPlay) {
		this.aiPlay = aiPlay;
	}

	public boolean isWatchMode() {
		return watchMode;
	}

	public void setWatchMode(boolean watchMode) {
		this.watchMode = watchMode;
	}

	public boolean isSoundBackground() {
		return soundBackground;
	}

	public void setSoundBackground(boolean soundBackground) {
		this.soundBackground = soundBackground;
	}

	public boolean isSoundGame() {
		return soundGame;
	}

	public void setSoundGame(boolean soundGame) {
		this.soundGame = soundGame;
	}

}

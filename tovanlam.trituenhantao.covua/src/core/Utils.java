package core;

import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.CodeSource;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import main.MainGame;

public class Utils {
	public static final String GAME_NAME = "Chess-AI - @Copyright KSTN-CNTT-K58-Group2";
	public static final int GAME_WIDTH = 1000;
	public static final int GAME_HEIGHT = 720;
	public static final int BOARD_GAME_WIDTH = 640;
	public static final int BOARD_GAME_HEIGHT = 640;
	public static final Image GAME_AVATAR = new ImageIcon(Utils.class.getResource("/image/avatarGame.png")).getImage();
	public static final Image BOARD_INFORMATION = new ImageIcon(Utils.class.getResource("/image/information.png")).getImage();
	public static final Image IMG_PANEL = new ImageIcon(Utils.class.getResource("/image/bgcolorpanel.jpg")).getImage();
	public static final int BUTTON_CONTROL_WIDTH = 100;
	public static final int BUTTON_CONTROL_HEIGHT = 50;
	public static final int RADIO_LEVEL_WIDTH = 40;
	public static final int RADIO_LEVEL_HEIGHT = 40;
	public static final int RADIO_LEVEL_WIDTH2 = 45;
	public static final int RADIO_LEVEL_HEIGHT2 = 50;
	public static final int GAME_MODE_RADIO_WIDTH = 90;
	public static final int GAME_MODE_RADIO_HEIGHT = 45;
	public static final String INFORMATION_INTRODUCE = 	"    ============****============== \n" 
													+   "     =    CHESS GAME - GROUP2    = \n"
													+   "     ============****============== \n";
	
	/////////////////////// For Menu Game ///////////////////////////
	public static final int MENU_GAME_WIDTH = 920;
	public static final int MENU_GAME_HEIGHT = 720;

	public static final Image BG_IMAGE_MENU = new ImageIcon(Utils.class.getResource("/image/bgImageMenu.jpg")).getImage();
	public static final ImageIcon ICON_BTN_START = new ImageIcon(Utils.class.getResource("/image/iconBtnStart.png"));
	public static final ImageIcon ICON_BTN_INSTRUCTION = new ImageIcon(Utils.class.getResource("/image/iconBtnInstruction.png"));
	public static final ImageIcon ICON_BTN_EXIT = new ImageIcon(Utils.class.getResource("/image/iconBtnExit.png"));
	public static final ImageIcon ICON_BTN_SETTING = new ImageIcon(Utils.class.getResource("/image/iconBtnSetting.png"));
	
	public static final String TITLE_MENU_ITEM_INSTRUCTIONE = "Game Instruction";
	
	/////////////////////// For SettingFrame /////////////////////////////
	public static final String TITLE_MENU_ITEM_SETTING = "Game Setting";
	public static final int SETTING_FRAME_WIDTH = 500;
	public static final int SETTING_FRAME_HEIGHT = 530;
	
	////////////// For ColorFrame, ChessmanChooserFrame //////////////////////
	public static final String TITLE_COLOR_FRAME = "Color Chooser";
	public static final String TITLE_CHESSMAN_CHOOSER_FRAME = "Chessman Chooser";
	public static final int CHESSMAN_CHOOSER_FRAME_WIDTH = 800;
	public static final int CHESSMAN_CHOOSER_FRAME_HEIGHT = 600;
	
	/////////////// For BoardPanel ///////////////////////////
	public static final String RESULT_LOSE = "Oh no! You losed.Do you want to play again?";
	public static final String RESULT_WIN = "Congratulation!. You are the winner. Do you want to play again?";
	public static final String TITLE_DIALOG_FAIL = "Fail";
	public static final String TITLE_DIALOG_SUCCESS = "Success";
	
	
	/////////////// For ButtonPanel ////////////////////////////
	public static final ImageIcon ICON_UNDO1 = resizeImageIcon("undo3", BUTTON_CONTROL_WIDTH, BUTTON_CONTROL_HEIGHT);
	public static final ImageIcon ICON_NEXT1 = resizeImageIcon("next3", BUTTON_CONTROL_WIDTH, BUTTON_CONTROL_HEIGHT);
	public static final ImageIcon ICON_MENU1 = resizeImageIcon("menu3", BUTTON_CONTROL_WIDTH, BUTTON_CONTROL_HEIGHT);
	public static final ImageIcon ICON_EXIT1 = resizeImageIcon("exit3", BUTTON_CONTROL_WIDTH, BUTTON_CONTROL_HEIGHT);
	public static final ImageIcon ICON_SHOW1 = resizeImageIcon("show3", BUTTON_CONTROL_WIDTH, BUTTON_CONTROL_HEIGHT);
	public static final ImageIcon ICON_SAVE1 = resizeImageIcon("save3", BUTTON_CONTROL_WIDTH, BUTTON_CONTROL_HEIGHT);
	
	public static final ImageIcon ICON_UNDO2 = resizeImageIcon("undo4", BUTTON_CONTROL_WIDTH, BUTTON_CONTROL_HEIGHT);
	public static final ImageIcon ICON_NEXT2 = resizeImageIcon("next4", BUTTON_CONTROL_WIDTH, BUTTON_CONTROL_HEIGHT);
	public static final ImageIcon ICON_MENU2 = resizeImageIcon("menu4", BUTTON_CONTROL_WIDTH, BUTTON_CONTROL_HEIGHT);
	public static final ImageIcon ICON_EXIT2 = resizeImageIcon("exit4", BUTTON_CONTROL_WIDTH, BUTTON_CONTROL_HEIGHT);
	public static final ImageIcon ICON_SHOW2 = resizeImageIcon("show4", BUTTON_CONTROL_WIDTH, BUTTON_CONTROL_HEIGHT);
	public static final ImageIcon ICON_SAVE2 = resizeImageIcon("save4", BUTTON_CONTROL_WIDTH, BUTTON_CONTROL_HEIGHT);
	
	public static final ImageIcon ICON_SETTING1 = resizeImageIcon("setting3", BUTTON_CONTROL_WIDTH, BUTTON_CONTROL_HEIGHT);
	public static final ImageIcon ICON_SETTING2 = resizeImageIcon("setting4", BUTTON_CONTROL_WIDTH, BUTTON_CONTROL_HEIGHT);
	
	
	
	///////////// For MainSettingPanel //////////////////////////
	public static final ImageIcon ICON_GAME_LEVEL1 = resizeImageIcon("1", RADIO_LEVEL_WIDTH, RADIO_LEVEL_HEIGHT);
	public static final ImageIcon ICON_GAME_LEVEL2 = resizeImageIcon("2", RADIO_LEVEL_WIDTH, RADIO_LEVEL_HEIGHT);
	public static final ImageIcon ICON_GAME_LEVEL3 = resizeImageIcon("3", RADIO_LEVEL_WIDTH, RADIO_LEVEL_HEIGHT);
	public static final ImageIcon ICON_GAME_LEVEL4 = resizeImageIcon("4", RADIO_LEVEL_WIDTH, RADIO_LEVEL_HEIGHT);
	public static final ImageIcon ICON_GAME_LEVEL5 = resizeImageIcon("5", RADIO_LEVEL_WIDTH, RADIO_LEVEL_HEIGHT);
	public static final ImageIcon ICON_GAME_LEVEL6 = resizeImageIcon("6", RADIO_LEVEL_WIDTH, RADIO_LEVEL_HEIGHT);
	
	public static final ImageIcon ICON_GAME_SET_LEVEL1 = resizeImageIcon("set1", RADIO_LEVEL_WIDTH, RADIO_LEVEL_HEIGHT);
	public static final ImageIcon ICON_GAME_SET_LEVEL2 = resizeImageIcon("set2", RADIO_LEVEL_WIDTH, RADIO_LEVEL_HEIGHT);
	public static final ImageIcon ICON_GAME_SET_LEVEL3 = resizeImageIcon("set3", RADIO_LEVEL_WIDTH, RADIO_LEVEL_HEIGHT);
	public static final ImageIcon ICON_GAME_SET_LEVEL4 = resizeImageIcon("set4", RADIO_LEVEL_WIDTH, RADIO_LEVEL_HEIGHT);
	public static final ImageIcon ICON_GAME_SET_LEVEL5 = resizeImageIcon("set5", RADIO_LEVEL_WIDTH, RADIO_LEVEL_HEIGHT);
	public static final ImageIcon ICON_GAME_SET_LEVEL6 = resizeImageIcon("set6", RADIO_LEVEL_WIDTH, RADIO_LEVEL_HEIGHT);
	
	public static final ImageIcon ICON_CHECK_TRUE = resizeImageIcon("checkTrue", RADIO_LEVEL_WIDTH, RADIO_LEVEL_HEIGHT);
	public static final ImageIcon ICON_CHECK_FALSE = resizeImageIcon("checkFalse", RADIO_LEVEL_WIDTH, RADIO_LEVEL_HEIGHT);
	
	public static final ImageIcon ICON_RADIO_ENABLE = resizeImageIcon("radioEnable", RADIO_LEVEL_WIDTH, RADIO_LEVEL_HEIGHT);
	public static final ImageIcon ICON_RADIO_UNABLE = resizeImageIcon("radioUnable", RADIO_LEVEL_WIDTH, RADIO_LEVEL_HEIGHT);
	
	public static final ImageIcon ICON_COLOR_CHOOSER1 = resizeImageIcon("color3", BUTTON_CONTROL_WIDTH, BUTTON_CONTROL_HEIGHT);
	public static final ImageIcon ICON_SETTING_OK1 = resizeImageIcon("ok3", BUTTON_CONTROL_WIDTH, BUTTON_CONTROL_HEIGHT);
	public static final ImageIcon ICON_COLOR_CHOOSER2 = resizeImageIcon("color1", BUTTON_CONTROL_WIDTH, BUTTON_CONTROL_HEIGHT);
	public static final ImageIcon ICON_SETTING_OK2 = resizeImageIcon("ok1", BUTTON_CONTROL_WIDTH, BUTTON_CONTROL_HEIGHT);
	
	public static final String TITLE_SETTING_GAME_MODE = "Chon Hinh Thuc Choi";
	public static final String TITLE_SETTING_CHESSMAN_MOVE_FIRST = "Chon Quan Di Truoc";
	public static final String TITLE_SETTING_CHOICE_LEVEL = "Chon Do Kho";
	public static final String TITLE_SETTING_SOUND = "Chon Cai Dat Am Thanh";
	public static final String TITLE_SETTING_CHOICE_CHESSMAN = "Chon Loai Quan Co";
	
	public static final String LABEL_SOUND_BACKGROUND = "Sound Background";
	public static final String LABEL_SOUND_GAME = "Sound Background";
	
	public static final String LABEL_CHESSMAN_BLACK = "Black Chessman";
	public static final String LABEL_CHESSMAN_WHITE = "White Chessman";
	
	public static final String LABEL_GAME_MODE_2P = "Human vs Human";
	public static final String LABEL_GAME_MODE_PAI = "Human vs AI";
	public static final String LABEL_GAME_MODE_WATCH_SAME = "Watch Same";
	
	public static final String ERROR_BUTTON_COLOR_HANDLER_IN = "None Button In!";
	public static final String ERROR_BUTTON_COLOR_HANDLER_OUT = "None Button Out!";
	
	public static final String TYPE_CHESSMAN1 = "Default";
	public static final String TYPE_CHESSMAN2 = "Classical";
	public static final String TYPE_CHESSMAN3 = "Standard";
	public static final String TYPE_CHESSMAN4 = "Roman";
	public static final String TYPE_CHESSMAN5 = "Vector";

	public static final String LABEL_COLOR_CHOOSER_VIEW = "Select Background Color For BoardChess";
	public static final String LABEL_COLOR_CHOOSER_BORDER = "Preview";
	public static final String LABEL_COLOR_CHOOSER_TITLE = "Choose Text Color";
	
	//////////////////// For PanelInformation //////////////////////////
	public static final String STRING_MODE_2P = "2 Player";
	public static final String STRING_WATCH_MODE = "Watch Mode";
	public static final String STRING_MODE_AI ="AI Play";
	
	//////////////////// For RadioPanel ////////////////////////////////
	public static final ImageIcon RADIO_MODE_ON = resizeImageIcon("on", GAME_MODE_RADIO_WIDTH, GAME_MODE_RADIO_HEIGHT);
	public static final ImageIcon RADIO_MODE_OFF = resizeImageIcon("off", GAME_MODE_RADIO_WIDTH, GAME_MODE_RADIO_HEIGHT);
	
	
	//////////////////// For AudioPlayer ///////////////////////////////
	public static boolean inGame = false;
	
	
	
	public static void changeStateOfRadioButton(JLabel radio, String iconName) {
		radio.setIcon(resizeImageIcon(iconName, RADIO_LEVEL_WIDTH, RADIO_LEVEL_HEIGHT));
	}
	
	public static ImageIcon resizeImageIcon(String iconName, int width, int height) {
		ImageIcon icon = new ImageIcon(Utils.class.getResource("/image/" + iconName + ".png"));
		Image img = icon.getImage();
		Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newImg);
		return newIcon;
	}
	
	public static ImageIcon createImageIcon(String path) {
		URL imgURL = Utils.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	
	
	
	public static String getCurrentDirectory() {
		String path = null;
		CodeSource codeSource = MainGame.class.getProtectionDomain().getCodeSource();
		try {
			File jarFile = new File(codeSource.getLocation().toURI().getPath());
			path = jarFile.getParentFile().getPath();

		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	public static void createPicture(String name) {
		File file = new File(getCurrentDirectory() + "//image//" + name);
		if (!file.exists()) {
			try {
				ExportResource("/image/" + name);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String ExportResource(String resourceName) throws Exception {
		InputStream stream = null;
		OutputStream resStreamOut = null;
		String jarFolder;
		try {
			stream = MainGame.class.getResourceAsStream(resourceName);
			if (stream == null) {
				throw new Exception("Cannot get resource \"" + resourceName + "\" from Jar file.");
			}
			int readBytes;
			byte[] buffer = new byte[4096];
			jarFolder = new File(MainGame.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath())
					.getParentFile().getPath().replace('\\', '/');
			resStreamOut = new FileOutputStream(jarFolder + resourceName);
			while ((readBytes = stream.read(buffer)) > 0) {
				resStreamOut.write(buffer, 0, readBytes);
			}
		} catch (Exception ex) {
			throw ex;
		} finally {
			stream.close();
			resStreamOut.close();
		}

		return jarFolder + resourceName;
	}
}

package gameframe;

import java.io.IOException;

import javax.swing.JFrame;

import chessPanel.BoardPanel;
import chessPanel.ButtonPanel;
import chessPanel.LevelPanel;
import chessPanel.PanelInformation;
import chessPanel.RadioPanel;
import chessPanel.YugiFace;
import core.GameSetting;
import core.Utils;

public class MainChessFrame extends JFrame {
	private BoardPanel boardPanel;
	private GameSetting gameSetting;
	private RadioPanel radioPanel;
	private LevelPanel levelPanel;
	private PanelInformation panelInformation;
	private ButtonPanel buttonPanel;
	private YugiFace yugiFace;
	private MenuFrame menuFrame;

	public MainChessFrame(GameSetting gameSetting, MenuFrame menuFrame, Boolean visible) {
		this.setSize(Utils.GAME_WIDTH, Utils.GAME_HEIGHT);
		this.setTitle(Utils.GAME_NAME);
		this.setIconImage(Utils.GAME_AVATAR);
		this.menuFrame = menuFrame;
		this.gameSetting = gameSetting;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
		addCompoment();
		this.createYuGi();
		this.boardPanel.setYugi(this.getYugiFace());
		this.setLocationRelativeTo(null);
		this.setVisible(visible);
	}

	public void addCompoment() {
		panelInformation = new PanelInformation(gameSetting);
		boardPanel = new BoardPanel(gameSetting, panelInformation);
		radioPanel = new RadioPanel(gameSetting, panelInformation, boardPanel);
		levelPanel = new LevelPanel(gameSetting, panelInformation);
		buttonPanel = new ButtonPanel(gameSetting, this);
		
		add(panelInformation);
		add(boardPanel);
		add(radioPanel);
		add(levelPanel);
		add(buttonPanel);
	}
	
	public void createYuGi() {
		try {
			yugiFace = new YugiFace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.add(yugiFace);
	}

	public BoardPanel getBoardPanel() {
		return boardPanel;
	}
	public YugiFace getYugiFace() {
		return yugiFace;
	}
	public PanelInformation getPanelInformation() {
		return panelInformation;
	}
	public MenuFrame getMenuFrame() {
		return menuFrame;
	}
}

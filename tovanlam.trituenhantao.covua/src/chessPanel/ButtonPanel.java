package chessPanel;

import gameframe.MainChessFrame;
import gameframe.SettingFrame;
import gameframe.ShowGame;
import sound.AudioPlayer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import core.ButtonSave;
import core.GameSetting;
import core.Move;
import core.Utils;

public class ButtonPanel extends JPanel implements MouseListener {
	private GameSetting gameSetting;
	private JLabel btnNext, btnUndo, btnMenu, btnExit, btnShow, btnSave;
	private MainChessFrame mainChessFrame;

	public ButtonPanel(GameSetting gameSetting, MainChessFrame mainChessFrame) {
		this.gameSetting = gameSetting;
		this.mainChessFrame = mainChessFrame;
		this.setBorder(new LineBorder(Color.LIGHT_GRAY));
		this.setBounds(Utils.BOARD_GAME_WIDTH + 5, 520, Utils.GAME_WIDTH-Utils.BOARD_GAME_WIDTH-15, 115);
		this.setFocusable(true);
		this.setBackground(Color.GRAY);
		addButton();
	}

	public JLabel createButtonControl(String iconName, Box parent) {
		JLabel btn = new JLabel(Utils.resizeImageIcon(iconName, Utils.BUTTON_CONTROL_WIDTH, Utils.BUTTON_CONTROL_HEIGHT));
		btn.setBorder(null);
		btn.addMouseListener(this);
		btn.setFocusable(true);
		parent.add(btn);
		return btn;
	}
		
	public void addButton() {
		Box boxPanel1 = Box.createHorizontalBox();
		boxPanel1.setAlignmentX(CENTER_ALIGNMENT);
		Box boxPanel2 = Box.createHorizontalBox();
		boxPanel2.setAlignmentX(CENTER_ALIGNMENT);
		
		btnUndo = createButtonControl("undo3", boxPanel1);
		btnNext = createButtonControl("next3", boxPanel1);
		btnMenu = createButtonControl("setting3", boxPanel1);
		btnSave = new ButtonSave(mainChessFrame.getBoardPanel());
		btnSave.setIcon(Utils.ICON_SAVE1);
		btnSave.addMouseListener(this);
		btnShow = new ShowGame(mainChessFrame, gameSetting);
		btnShow.setIcon(Utils.ICON_SHOW1);
		btnShow.addMouseListener(this);
		
		boxPanel2.add(btnSave);
		boxPanel2.add(btnShow);
		btnExit = createButtonControl("exit3", boxPanel2);
		
		Box boxPanel3 = Box.createVerticalBox();
		boxPanel3.add(boxPanel1);
		boxPanel3.add(boxPanel2);
		this.add(boxPanel3);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Dimension d = getSize();
		g.drawImage(Utils.IMG_PANEL, 0, 0, d.width, d.height, null);
		setOpaque(false);
		super.paintComponent(g);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		JLabel lbl = (JLabel) e.getSource();
		if(lbl == btnUndo) {
			// First Button: Undo
			AudioPlayer.playClick();
			if (!gameSetting.isAiPlay()) {	
				mainChessFrame.getPanelInformation().insertText("Undo: ");
				Move move = mainChessFrame.getBoardPanel().getPositionBoard().getParentMove();
				mainChessFrame.getPanelInformation().appendTextYellow(move.toString());
				mainChessFrame.getBoardPanel()
								.setPositionBoard(mainChessFrame.getBoardPanel().getPositionBoard().getOldPositionBoard());
				mainChessFrame.getBoardPanel().repaint();
				if (mainChessFrame.getBoardPanel().isHumanTurn()) {
					mainChessFrame.getBoardPanel().setHumanTurn(false);
				} else {
					mainChessFrame.getBoardPanel().setHumanTurn(true);
				}
			} else {
				for (int i = 0; i <= 1; i++) {
					mainChessFrame.getPanelInformation().insertText("Undo: ");
					Move move = mainChessFrame.getBoardPanel().getPositionBoard().getParentMove();
					mainChessFrame.getPanelInformation().appendTextYellow(move.toString());
					mainChessFrame.getBoardPanel().setPositionBoard(mainChessFrame.getBoardPanel().getPositionBoard().getOldPositionBoard());
					mainChessFrame.getBoardPanel().repaint();
					if (mainChessFrame.getBoardPanel().isHumanTurn()) {
						mainChessFrame.getBoardPanel().setHumanTurn(false);
					} else {
						mainChessFrame.getBoardPanel().setHumanTurn(true);
					}
				}
			}
		} else if(lbl == btnNext) {			// Second button : Next
			AudioPlayer.playClick();
				mainChessFrame.getPanelInformation().insertText("Next: ");
				if (mainChessFrame.getBoardPanel().getPositionBoard().getDepth() != 0) {
					if (mainChessFrame.getBoardPanel().getPositionBoard().getDepth() % 2 == gameSetting.getLevel()
							% 2) {
						mainChessFrame.getPanelInformation().insertText("Ai se chon nuoc sau: ");

					} else {
						mainChessFrame.getPanelInformation().insertText("Theo Ai nguoi choi se chon nuoc sau: ");
					}
					mainChessFrame.getBoardPanel()
							.setPositionBoard(mainChessFrame.getBoardPanel().getPositionBoard().getBestChild());
					mainChessFrame.getBoardPanel().repaint();
					Move move = mainChessFrame.getBoardPanel().getPositionBoard().getParentMove();
					mainChessFrame.getPanelInformation().appendTextYellow(move.toString());
				} else {
					mainChessFrame.getPanelInformation().appendTextRed("Khong ton tai");
				}
			
		} else if(lbl == btnExit) {
			AudioPlayer.playClick();
			Utils.inGame = false;
			mainChessFrame.getMenuFrame().setVisible(true);
			mainChessFrame.dispose();
		} else if(lbl == btnMenu) {
			AudioPlayer.playClick();
			new SettingFrame(gameSetting);
		} else if(lbl == btnShow) {
			
		} else if(lbl == btnSave) {
			
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		JLabel label = (JLabel) e.getSource();
		if(label == btnUndo){
			btnUndo.setIcon(Utils.ICON_UNDO2);
		} else if(label == btnNext) {
			btnNext.setIcon(Utils.ICON_NEXT2);
		} else if(label == btnShow) {
			btnShow.setIcon(Utils.ICON_SHOW2);
		} else if(label == btnMenu) {
			btnMenu.setIcon(Utils.ICON_SETTING2);
		} else if(label == btnExit) {
			btnExit.setIcon(Utils.ICON_EXIT2);
		} else {
			btnSave.setIcon(Utils.ICON_SAVE2);
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		JLabel label = (JLabel) e.getSource();
		if(label == btnUndo){
			btnUndo.setIcon(Utils.ICON_UNDO1);
		} else if(label == btnNext) {
			btnNext.setIcon(Utils.ICON_NEXT1);
		} else if(label == btnShow) {
			btnShow.setIcon(Utils.ICON_SHOW1);
		} else if(label == btnMenu) {
			btnMenu.setIcon(Utils.ICON_SETTING1);
		} else if(label == btnExit) {
			btnExit.setIcon(Utils.ICON_EXIT1);
		} else {
			btnSave.setIcon(Utils.ICON_SAVE1);
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
}

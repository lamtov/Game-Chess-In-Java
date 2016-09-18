package chessPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import core.ArtificialIntelligence;
import core.GameSetting;
import core.Utils;
import sound.AudioPlayer;

public class RadioPanel extends JPanel implements MouseListener {
	private GameSetting gameSetting;
	private PanelInformation panelInformation;
	private BoardPanel boardPanel;
	private JLabel radio2P, radioAi, radioWatch, lblWatchMode;
	private ImageIcon labelGameMode, labelWatchMode;

	public RadioPanel(GameSetting gameSetting, PanelInformation panelInformation, BoardPanel boardPanel) {
		this.setBounds(360, Utils.BOARD_GAME_HEIGHT, Utils.BOARD_GAME_WIDTH, 60);
		this.setFocusable(true);
		this.setBackground(Color.GREEN);
		this.gameSetting = gameSetting;
		this.panelInformation = panelInformation;
		this.boardPanel = boardPanel;
		
		labelGameMode = Utils.resizeImageIcon("mode", 80, 40);
		labelWatchMode = Utils.resizeImageIcon("labelWatchMode", 100, 45);
		JLabel lblGameMode = new JLabel(labelGameMode);
		lblWatchMode = new JLabel(labelWatchMode);
		
		Box b1 = Box.createHorizontalBox();
		b1.add(lblGameMode);
		Component b = Box.createRigidArea(new Dimension(40, 0));
		Box b2 = Box.createHorizontalBox();
		b2.add(lblWatchMode);
		addRadioButton(b1, b2);			// Important Method
		this.add(b1);
		this.add(b);
		this.add(b2);
		
		Timer timerSetting = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				checkRadioSelected();
			}
		});
		timerSetting.start();
	}

	public void addRadioButton(Box b1, Box b2) {
		
		radio2P = createRadioButton("2Player", "off", b1);
		radioAi = createRadioButton("AiPlay", "off", b1);
		radioWatch = createRadioButton(null, "off", b2);
		checkRadioSelected();
	}
	private void checkRadioSelected() {
		if(gameSetting.isAiPlay()) {
			radioAi.setIcon(Utils.RADIO_MODE_ON);
			radio2P.setIcon(Utils.RADIO_MODE_OFF);
			radioWatch.setIcon(Utils.RADIO_MODE_OFF);
		} else if(gameSetting.isWatchMode()) {
			radioWatch.setIcon(Utils.RADIO_MODE_ON);
			radio2P.setIcon(Utils.RADIO_MODE_OFF);
			radioAi.setIcon(Utils.RADIO_MODE_OFF);
		} else {
			radioWatch.setIcon(Utils.RADIO_MODE_OFF);
			radioAi.setIcon(Utils.RADIO_MODE_OFF);
			radio2P.setIcon(Utils.RADIO_MODE_ON);
		}
		
	}
	
	public JLabel createRadioButton(String name, String iconName, Box box) {
		JLabel radio = new JLabel(name);
		radio.setForeground(Color.BLUE);
		radio.setIcon(Utils.resizeImageIcon(iconName, Utils.GAME_MODE_RADIO_WIDTH, Utils.GAME_MODE_RADIO_HEIGHT));
		radio.addMouseListener(this);
		box.add(radio);
		return radio;
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
		AudioPlayer.playClick();
		JLabel lbl = (JLabel) e.getSource();
		if(lbl == radio2P) {
			radio2P.setIcon(Utils.RADIO_MODE_ON);
			gameSetting.setWatchMode(false);
			gameSetting.setAiPlay(false);
			panelInformation.insertText("Movde : " + "2Player");
		} else {
			radio2P.setIcon(Utils.RADIO_MODE_OFF);
		}
		
		if(lbl == radioWatch) {
			radioWatch.setIcon(Utils.RADIO_MODE_ON);
			gameSetting.setWatchMode(true);
			gameSetting.setAiPlay(false);
			panelInformation.insertText("Movde : " + "Watch");
		} else {
			radioWatch.setIcon(Utils.RADIO_MODE_OFF);
		}
		
		if(lbl == radioAi) {
			radioAi.setIcon(Utils.RADIO_MODE_ON);
			panelInformation.insertText("Movde : " + "AiPlay");
			gameSetting.setWatchMode(false);
			gameSetting.setAiPlay(true);
			if (!boardPanel.isHumanTurn()) {
				ArtificialIntelligence ai = new ArtificialIntelligence(gameSetting, boardPanel.getPositionBoard());
				boardPanel.setPositionBoard(ai.getNextPosition());
				panelInformation.appendTextYellow(boardPanel.getPositionBoard().getParentMove().toString());
				boardPanel.setHumanTurn(true);
				boardPanel.setShowCanMove(true);
				panelInformation.insertText("======***** Human Turn *****======");
				boardPanel.repaint();
				boardPanel.setShowCanMove(false);
				boardPanel.repaint();
			}
		} else {
			radioAi.setIcon(Utils.RADIO_MODE_OFF);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
}

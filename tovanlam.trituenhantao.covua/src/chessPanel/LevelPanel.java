package chessPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import core.GameSetting;
import core.Utils;
import sound.AudioPlayer;

public class LevelPanel extends JPanel implements MouseListener {
	private GameSetting gameSetting;
	private PanelInformation panelInformation;
	private ArrayList<JLabel> listRadio;
	private JLabel radio1, radio2, radio3, radio4, radio5, radio6;

	public LevelPanel(GameSetting gameSetting, PanelInformation panelInformation) {
		this.panelInformation = panelInformation;
		this.setBounds(0, Utils.BOARD_GAME_HEIGHT, 360, 60);
		this.setFocusable(true);
		this.gameSetting = gameSetting;
		
		listRadio = new ArrayList<>();
		Box box = Box.createHorizontalBox();
		ImageIcon labelIconLevel = Utils.resizeImageIcon("level", 70, 40);
		JLabel labelLevel = new JLabel(labelIconLevel);
		box.add(labelLevel);
		
		addRadioButton(box);

		this.add(box);
		Timer timerSetting = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = GameSetting.rootLevel-1;
				if(i == 0) {
					listRadio.get(0).setIcon(Utils.resizeImageIcon("set1", Utils.RADIO_LEVEL_WIDTH, Utils.RADIO_LEVEL_HEIGHT));
				} else {
					listRadio.get(0).setIcon(Utils.resizeImageIcon("1", Utils.RADIO_LEVEL_WIDTH, Utils.RADIO_LEVEL_HEIGHT));
				}
					
				if(i == 1) {
					listRadio.get(1).setIcon(Utils.resizeImageIcon("set2", Utils.RADIO_LEVEL_WIDTH, Utils.RADIO_LEVEL_HEIGHT));
				} else {
					listRadio.get(1).setIcon(Utils.resizeImageIcon("2", Utils.RADIO_LEVEL_WIDTH, Utils.RADIO_LEVEL_HEIGHT));
				}
				
				if(i == 2) {
					listRadio.get(2).setIcon(Utils.resizeImageIcon("set3", Utils.RADIO_LEVEL_WIDTH, Utils.RADIO_LEVEL_HEIGHT));
				} else {
					listRadio.get(2).setIcon(Utils.resizeImageIcon("3", Utils.RADIO_LEVEL_WIDTH, Utils.RADIO_LEVEL_HEIGHT));
				}
				
				if(i == 3) {
					listRadio.get(3).setIcon(Utils.resizeImageIcon("set4", Utils.RADIO_LEVEL_WIDTH, Utils.RADIO_LEVEL_HEIGHT));
				} else {
					listRadio.get(3).setIcon(Utils.resizeImageIcon("4", Utils.RADIO_LEVEL_WIDTH, Utils.RADIO_LEVEL_HEIGHT));
				}
				
				if(i == 4) {
					listRadio.get(4).setIcon(Utils.resizeImageIcon("set5", Utils.RADIO_LEVEL_WIDTH, Utils.RADIO_LEVEL_HEIGHT));
				} else {
					listRadio.get(4).setIcon(Utils.resizeImageIcon("5", Utils.RADIO_LEVEL_WIDTH, Utils.RADIO_LEVEL_HEIGHT));
				}
				
				if(i == 5) {
					listRadio.get(5).setIcon(Utils.resizeImageIcon("set6", Utils.RADIO_LEVEL_WIDTH, Utils.RADIO_LEVEL_HEIGHT));
				} else {
					listRadio.get(5).setIcon(Utils.resizeImageIcon("6", Utils.RADIO_LEVEL_WIDTH, Utils.RADIO_LEVEL_HEIGHT));
				}
			}
		});
		timerSetting.start();
	}

	public void addRadioButton(Box box) {
		radio1 = createRadioButton("1", "1", box);
		radio2 = createRadioButton("2", "2", box);
		radio3 = createRadioButton("3", "3", box);
		radio4 = createRadioButton("4", "4", box);
		radio5 = createRadioButton("5", "5", box);
		radio6 = createRadioButton("6", "6", box);
		switch (gameSetting.getLevel()) {
		case 1:
			Utils.changeStateOfRadioButton(radio1, "set1");
			break;
		case 2:
			Utils.changeStateOfRadioButton(radio2, "set2");
			break;
		case 3:
			Utils.changeStateOfRadioButton(radio3, "set3");
			break;
		case 4:
			Utils.changeStateOfRadioButton(radio4, "set4");
			break;
		case 5:
			Utils.changeStateOfRadioButton(radio5, "set5");
			break;
		case 6:
			Utils.changeStateOfRadioButton(radio6, "set6");
			break;
		}
	}
	
	public JLabel createRadioButton(String iconName, String actionCommand, Box box) {
		JLabel radio = new JLabel();
		radio.setIcon(Utils.resizeImageIcon(iconName, Utils.RADIO_LEVEL_WIDTH, Utils.RADIO_LEVEL_HEIGHT));
		radio.addMouseListener(this);
		radio.setBackground(null);
		listRadio.add(radio);
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
	public void mousePressed(MouseEvent me) {
		AudioPlayer.playClick();
		JLabel lbl = (JLabel) me.getSource();
		int iRadio = 4;
		if(lbl == radio1) {
			radio1.setIcon(Utils.resizeImageIcon("set1", Utils.RADIO_LEVEL_WIDTH, Utils.RADIO_LEVEL_HEIGHT));
			iRadio = 1;
		} else {
			radio1.setIcon(Utils.resizeImageIcon("1", Utils.RADIO_LEVEL_WIDTH, Utils.RADIO_LEVEL_HEIGHT));
		}
		
		if(lbl == radio2) {
			radio2.setIcon(Utils.resizeImageIcon("set2", Utils.RADIO_LEVEL_WIDTH, Utils.RADIO_LEVEL_HEIGHT));
			iRadio = 2;
		} else {
			radio2.setIcon(Utils.resizeImageIcon("2", Utils.RADIO_LEVEL_WIDTH, Utils.RADIO_LEVEL_HEIGHT));
		}
		
		if(lbl == radio3) {
			radio3.setIcon(Utils.resizeImageIcon("set3", Utils.RADIO_LEVEL_WIDTH, Utils.RADIO_LEVEL_HEIGHT));
			iRadio = 3;
		} else {
			radio3.setIcon(Utils.resizeImageIcon("3", Utils.RADIO_LEVEL_WIDTH, Utils.RADIO_LEVEL_HEIGHT));
		}
		
		if(lbl == radio4) {
			radio4.setIcon(Utils.resizeImageIcon("set4", Utils.RADIO_LEVEL_WIDTH, Utils.RADIO_LEVEL_HEIGHT));
			iRadio = 4;
		} else {
			radio4.setIcon(Utils.resizeImageIcon("4", Utils.RADIO_LEVEL_WIDTH, Utils.RADIO_LEVEL_HEIGHT));
		}
		
		if(lbl == radio5) {
			radio5.setIcon(Utils.resizeImageIcon("set5", Utils.RADIO_LEVEL_WIDTH, Utils.RADIO_LEVEL_HEIGHT));
			iRadio = 5;
		} else {
			radio5.setIcon(Utils.resizeImageIcon("5", Utils.RADIO_LEVEL_WIDTH, Utils.RADIO_LEVEL_HEIGHT));
		}
		
		if(lbl == radio6) {
			radio6.setIcon(Utils.resizeImageIcon("set6", Utils.RADIO_LEVEL_WIDTH, Utils.RADIO_LEVEL_HEIGHT));
			iRadio = 6;
		} else {
			radio6.setIcon(Utils.resizeImageIcon("6", Utils.RADIO_LEVEL_WIDTH, Utils.RADIO_LEVEL_HEIGHT));
		}
		gameSetting.setLevel(iRadio);
		GameSetting.rootLevel = gameSetting.getLevel();
		panelInformation.insertText("Level = " + iRadio);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
}

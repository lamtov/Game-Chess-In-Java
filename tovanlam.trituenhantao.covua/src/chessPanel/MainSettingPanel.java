package chessPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import core.GameSetting;
import core.SettingFrameListener;
import core.Utils;

public class MainSettingPanel extends JPanel {

	private GameSetting gameSetting;
	private ColorChooser colorChooser;
	private JFrame frameColor;
	private SettingFrameListener settingFrameListener;
	private JComboBox<Chessman> chooserChessman;
	private ArrayList<JLabel> listLevel = new ArrayList<>();
	private JLabel btnColor, btnOk;
	
	public MainSettingPanel(GameSetting gameSetting) {
		this.gameSetting = gameSetting;
		
		frameColor = new JFrame();
		colorChooser = new ColorChooser(gameSetting);
		colorChooser.setOpaque(true);
		frameColor.setContentPane(colorChooser);
		frameColor.setIconImage(Utils.GAME_AVATAR);
		frameColor.setTitle(Utils.TITLE_COLOR_FRAME);
		frameColor.pack();
		frameColor.setVisible(false);
		
		this.setBorder(BorderFactory.createEmptyBorder(5, 50, 5, 50));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JLabel lb1 = new JLabel(Utils.TITLE_SETTING_GAME_MODE);
		lb1.setAlignmentX(Component.CENTER_ALIGNMENT);
		lb1.setForeground(Color.WHITE);
		lb1.setFont(new Font("Serif", Font.BOLD, 20));
		
		JLabel lb2 = new JLabel(Utils.TITLE_SETTING_CHESSMAN_MOVE_FIRST);
		lb2.setAlignmentX(Component.CENTER_ALIGNMENT);
		lb2.setForeground(Color.WHITE);
		lb2.setFont(new Font("Serif", Font.BOLD, 20));
		
		JLabel lb3 = new JLabel(Utils.TITLE_SETTING_CHOICE_LEVEL);
		lb3.setAlignmentX(Component.CENTER_ALIGNMENT);
		lb3.setForeground(Color.WHITE);
		lb3.setFont(new Font("Serif", Font.BOLD, 20));
		
		JLabel lb4 = new JLabel(Utils.TITLE_SETTING_SOUND);
		lb4.setAlignmentX(Component.CENTER_ALIGNMENT);
		lb4.setForeground(Color.WHITE);
		lb4.setFont(new Font("Serif", Font.BOLD, 20));
		
		JLabel lb5 = new JLabel(Utils.TITLE_SETTING_CHOICE_CHESSMAN);
		lb5.setAlignmentX(Component.CENTER_ALIGNMENT);
		lb5.setForeground(Color.WHITE);
		lb5.setFont(new Font("Serif", Font.BOLD, 20));
		
		Component b1 = Box.createRigidArea(new Dimension(0, 20));
		Box box1 = Box.createVerticalBox();
		box1.add(lb1);
		box1.add(getTypePanel());
		this.add(box1);
		this.add(b1);
		
		Component b2 = Box.createRigidArea(new Dimension(0, 25));
		this.add(lb2);
		this.add(getColorPanel());
		this.add(b2);
		
		Component b3 = Box.createRigidArea(new Dimension(0, 20));
		this.add(lb3);
		this.add(getLvPanel());
		this.add(b3);
		
		Component b4 = Box.createRigidArea(new Dimension(0, 25));
		this.add(lb4);
		this.add(getSoundPanel());
		this.add(b4);
		
		Component b5 = Box.createRigidArea(new Dimension(0, 20));
		this.add(lb5);
		this.add(getChessmanChooserPanel());
		this.add(b5);
		
		Component b6 = Box.createRigidArea(new Dimension(0, 20));
		this.add(getButtonPanel());
		this.add(b6);
		
		Timer timerRadioLevel = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = gameSetting.getLevel();
				checkRadioLevel(i);
			}
		});
		timerRadioLevel.start();
	}
	

	/**
	 * 		Level Panel
	 **/
	private Box getLvPanel() {
		Box rdPanel = Box.createHorizontalBox();
		rdPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		createButtonLevel(Utils.ICON_GAME_LEVEL1, "1", rdPanel);
		createButtonLevel(Utils.ICON_GAME_LEVEL2, "2", rdPanel);
		createButtonLevel(Utils.ICON_GAME_LEVEL3, "3", rdPanel);
		createButtonLevel(Utils.ICON_GAME_LEVEL4, "4", rdPanel);
		createButtonLevel(Utils.ICON_GAME_LEVEL5, "5", rdPanel);
		createButtonLevel(Utils.ICON_GAME_LEVEL6, "6", rdPanel);
		
		int i = GameSetting.rootLevel;
		checkRadioLevel(i);
		return rdPanel;
	}
	private JLabel createButtonLevel(ImageIcon icon, String actionCmd, Box parent) {
		JLabel radio = new JLabel(icon);
		radio.addMouseListener(new RadioLevelHandler());
		listLevel.add(radio);
		parent.add(radio);
		Component c = Box.createRigidArea(new Dimension(20, 0));
		parent.add(c);
		return radio;
	}
	private void checkRadioLevel(int id) {
		for(int i = 1; i <= 6; i++) {
			if(id == i) {
				listLevel.get(i-1).setIcon(Utils.resizeImageIcon("set"+i, Utils.RADIO_LEVEL_WIDTH2, Utils.RADIO_LEVEL_HEIGHT2));
			} else {
				listLevel.get(i-1).setIcon(Utils.resizeImageIcon(""+i, Utils.RADIO_LEVEL_WIDTH2, Utils.RADIO_LEVEL_HEIGHT2));
			}
		}
	}
	
	class RadioLevelHandler implements MouseListener {
		@Override
		public void mousePressed(MouseEvent e) {
			JLabel label = (JLabel) e.getSource();
			for(JLabel lbl : listLevel) {
				int i = listLevel.indexOf(lbl) + 1;
				if(label == lbl) {
					gameSetting.setLevel(i);
					GameSetting.rootLevel = gameSetting.getLevel();
				} 
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

	/**
	 * 			Sound Panel
	 **/
	private Box getSoundPanel() {
		Box rdPanel = Box.createHorizontalBox();
		rdPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel soundBackground = new JLabel(Utils.LABEL_SOUND_BACKGROUND);
		soundBackground.setIcon(Utils.ICON_CHECK_TRUE);
		Component box = Box.createRigidArea(new Dimension(40, 0));
		JLabel soundGame = new JLabel(Utils.LABEL_SOUND_GAME);
		soundGame.setIcon(Utils.ICON_CHECK_TRUE);
		
		if (gameSetting.isSoundBackground()) {
			soundBackground.setIcon(Utils.ICON_CHECK_TRUE);
		}
		if (gameSetting.isSoundGame()) {
			soundGame.setIcon(Utils.ICON_CHECK_TRUE);
		}
		
		soundBackground.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(gameSetting.isSoundBackground()) {
					soundBackground.setIcon(Utils.ICON_CHECK_FALSE);
					gameSetting.setSoundBackground(false);
				} else {
					soundBackground.setIcon(Utils.ICON_CHECK_TRUE);
					gameSetting.setSoundBackground(true);
				}
			}
		});
		soundGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(gameSetting.isSoundGame()) {
					soundGame.setIcon(Utils.ICON_CHECK_FALSE);
					gameSetting.setSoundGame(false);
				} else {
					soundGame.setIcon(Utils.ICON_CHECK_TRUE);
					gameSetting.setSoundGame(true);
				}
			}
		});
		rdPanel.add(soundBackground);
		rdPanel.add(box);
		rdPanel.add(soundGame);
		return rdPanel;
	}

	/**
	 * 			Color Panel
	 **/
	private Box getColorPanel() {
		Box rdPanel = Box.createHorizontalBox();
		rdPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel cbBlack = new JLabel(Utils.LABEL_CHESSMAN_BLACK);
		cbBlack.setIcon(Utils.ICON_CHECK_FALSE);
		Component box = Box.createRigidArea(new Dimension(65, 0));
		JLabel cbWhite = new JLabel(Utils.LABEL_CHESSMAN_WHITE);
		cbWhite.setForeground(Color.WHITE);
		cbWhite.setIcon(Utils.ICON_CHECK_FALSE);
		if (gameSetting.isAiFirst()) {
			cbBlack.setIcon(Utils.ICON_CHECK_TRUE);
		} else {
			cbWhite.setIcon(Utils.ICON_CHECK_TRUE);
		}
		
		cbBlack.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cbBlack.setIcon(Utils.ICON_CHECK_TRUE);
				cbWhite.setIcon(Utils.ICON_CHECK_FALSE);
				gameSetting.setAiFirst(true);
				gameSetting.setHumanFirst(false);
			}
		});
		cbWhite.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cbBlack.setIcon(Utils.ICON_CHECK_FALSE);
				cbWhite.setIcon(Utils.ICON_CHECK_TRUE);
				gameSetting.setAiFirst(false);
				gameSetting.setHumanFirst(true);
			}
		});
		
		rdPanel.add(cbBlack);
		rdPanel.add(box);
		rdPanel.add(cbWhite);
		return rdPanel;
	}

	/**
	 * 			Game Mode Panel
	 **/
	private Box getTypePanel() {
		Box rdPanel = Box.createHorizontalBox();
		rdPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel rdAi = new JLabel(Utils.LABEL_GAME_MODE_PAI);
		JLabel rd2P = new JLabel(Utils.LABEL_GAME_MODE_2P);
		JLabel rdSame = new JLabel(Utils.LABEL_GAME_MODE_WATCH_SAME);
		
		rdAi.setIcon(Utils.ICON_RADIO_UNABLE);
		rd2P.setIcon(Utils.ICON_RADIO_UNABLE);
		rdSame.setIcon(Utils.ICON_RADIO_UNABLE);
		if (gameSetting.isAcceptSame()) {
			rdSame.setIcon(Utils.ICON_RADIO_ENABLE);
		}
		if (gameSetting.isAiPlay()) {
			rdAi.setIcon(Utils.ICON_RADIO_ENABLE);
		} else {
			rd2P.setIcon(Utils.ICON_RADIO_ENABLE);
		}
		
		rdAi.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				rdAi.setIcon(Utils.ICON_RADIO_ENABLE);
				rd2P.setIcon(Utils.ICON_RADIO_UNABLE);
				gameSetting.setAiPlay(true);
			}
		});
		rd2P.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				rdAi.setIcon(Utils.ICON_RADIO_UNABLE);
				rd2P.setIcon(Utils.ICON_RADIO_ENABLE);
				gameSetting.setAiPlay(false);
			}
		});
		rdSame.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(gameSetting.isAcceptSame()) {
					rdSame.setIcon(Utils.ICON_RADIO_UNABLE);
					gameSetting.setAcceptSame(false);
				} else {
					rdSame.setIcon(Utils.ICON_RADIO_ENABLE);
					gameSetting.setAcceptSame(true);
				}
			}
		});
		rdPanel.add(rd2P);
		Component area1 = Box.createRigidArea(new Dimension(10, 0));
		rdPanel.add(area1);
		rdPanel.add(rdAi);
		Component area2 = Box.createRigidArea(new Dimension(10, 0));
		rdPanel.add(area2);
		rdPanel.add(rdSame);
		return rdPanel;
	}

	/**
	 * 			Button Control Panel
	 **/
	private Box getButtonPanel() {
		Box rdPanel = Box.createHorizontalBox();
		rdPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		btnColor = new JLabel();
		btnColor.setIcon(Utils.ICON_COLOR_CHOOSER1);
		btnColor.addMouseListener(new ButtonColorHandler());
		btnOk = new JLabel();
		btnOk.setIcon(Utils.ICON_SETTING_OK1);
		btnOk.addMouseListener(new ButtonColorHandler());

		rdPanel.add(btnColor);
		Component c = Box.createRigidArea(new Dimension(20, 0));
		rdPanel.add(c);
		rdPanel.add(btnOk);
		return rdPanel;
	}
	class ButtonColorHandler implements MouseListener {
		@Override
		public void mouseEntered(MouseEvent e) {
			JLabel label = (JLabel) e.getSource();
			if(label == btnColor) {
				btnColor.setIcon(Utils.ICON_COLOR_CHOOSER2);
			} else if(label == btnOk) {
				btnOk.setIcon(Utils.ICON_SETTING_OK2);
			} else {
				System.err.println(Utils.ERROR_BUTTON_COLOR_HANDLER_IN);
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			JLabel label = (JLabel) e.getSource();
			if(label == btnColor) {
				btnColor.setIcon(Utils.ICON_COLOR_CHOOSER1);
			} else if(label == btnOk) {
				btnOk.setIcon(Utils.ICON_SETTING_OK1);
			} else {
				System.err.println(Utils.ERROR_BUTTON_COLOR_HANDLER_OUT);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			JLabel label = (JLabel) e.getSource();
			if(label == btnColor) {
				frameColor.setVisible(!frameColor.isVisible());
			} else if(label == btnOk) {
				if(settingFrameListener != null) {
					settingFrameListener.closeSettingFrameOccurred();
				}
				frameColor.dispose();
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}
		@Override
		public void mouseReleased(MouseEvent e) {
		}
	}
	
	
	/**
	 * 		Type of chessman
	 */
	private Box getChessmanChooserPanel() {
		Box rdPanel = Box.createHorizontalBox();
		rdPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		DefaultComboBoxModel<MainSettingPanel.Chessman> chessmanModel = new DefaultComboBoxModel<>();
		chooserChessman = new JComboBox<MainSettingPanel.Chessman>(chessmanModel);
		
		chessmanModel.addElement(new Chessman(1, Utils.TYPE_CHESSMAN1));
		chessmanModel.addElement(new Chessman(2, Utils.TYPE_CHESSMAN2));
		chessmanModel.addElement(new Chessman(3, Utils.TYPE_CHESSMAN3));
		chessmanModel.addElement(new Chessman(4, Utils.TYPE_CHESSMAN4));
		chessmanModel.addElement(new Chessman(5, Utils.TYPE_CHESSMAN5));
		
		chooserChessman.setEditable(false);
		chooserChessman.setSelectedIndex(GameSetting.typeChessman-1);
		chooserChessman.setPreferredSize(new Dimension(100, 30));
		chooserChessman.setSize(100, 30);
		chooserChessman.setBorder(BorderFactory.createEtchedBorder());
		
		chooserChessman.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Chessman choice = (Chessman) chooserChessman.getSelectedItem();
				GameSetting.typeChessman = choice.getId();
			}
		});
		
		rdPanel.add(chooserChessman);
		return rdPanel;
	}
	
	class ColorChooser extends JPanel implements ChangeListener {
		private static final long serialVersionUID = -37223332575085998L;
		private JColorChooser tcc;
		private JLabel banner;
		private Color color;
		private GameSetting gameSetting;
		
		public ColorChooser(GameSetting gameSetting) {
			this.setLayout(new BorderLayout());
			this.gameSetting = gameSetting;
			banner = new JLabel(Utils.LABEL_COLOR_CHOOSER_VIEW, JLabel.CENTER);
			banner.setForeground(Color.yellow);
			banner.setBackground(Color.blue);
			banner.setOpaque(true);
			banner.setFont(new Font("SansSerif", Font.BOLD, 24));
			banner.setPreferredSize(new Dimension(100, 65));

			JPanel bannerPanel = new JPanel(new BorderLayout());
			bannerPanel.add(banner, BorderLayout.CENTER);
			bannerPanel.setBorder(BorderFactory.createTitledBorder(Utils.LABEL_COLOR_CHOOSER_BORDER));

			tcc = new JColorChooser(banner.getForeground());
			tcc.getSelectionModel().addChangeListener(this);
			tcc.setBorder(BorderFactory.createTitledBorder(Utils.LABEL_COLOR_CHOOSER_TITLE));

			add(bannerPanel, BorderLayout.CENTER);
			add(tcc, BorderLayout.PAGE_END);
		}
		
		@Override
		public void stateChanged(ChangeEvent e) {
			color = tcc.getColor();
			banner.setForeground(color);
			this.gameSetting.setBackgroundColor(color);
		}
		public Color getColor() {
			return color;
		}
	}
	
	class Chessman {
		private String description;
		private int id;
		
		public Chessman(int id, String description) {
			this.description = description;
			this.id = id;
		}
		public int getId() {
			return id;
		}
		public String toString() {
			return description;
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Dimension d = getSize();
		g.drawImage(Utils.IMG_PANEL, 0, 0, d.width, d.height, null);
		setOpaque(false);
		super.paintComponent(g);
	}
	
	public void setSettingFrameListener(SettingFrameListener sfl) {
		this.settingFrameListener = sfl;
	}
}

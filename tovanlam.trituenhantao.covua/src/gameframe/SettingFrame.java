package gameframe;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import chessPanel.MainSettingPanel;
import core.GameSetting;
import core.SettingFrameListener;
import core.Utils;

public class SettingFrame extends JFrame {

	private MainSettingPanel mainSettingPanel;
	
	public SettingFrame(GameSetting gameSetting) {
		this.setSize(Utils.SETTING_FRAME_WIDTH, Utils.SETTING_FRAME_HEIGHT);
		this.setTitle(Utils.TITLE_MENU_ITEM_SETTING);
		this.setIconImage(Utils.GAME_AVATAR);
		this.setLayout(new BorderLayout());
		
		mainSettingPanel = new MainSettingPanel(gameSetting);
		mainSettingPanel.setSettingFrameListener(new SettingFrameListener() {
			@Override
			public void closeSettingFrameOccurred() {
				closeSettingFrame();
			}
		});
		this.add(mainSettingPanel, BorderLayout.CENTER);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void closeSettingFrame() {
		dispose();
	}
}

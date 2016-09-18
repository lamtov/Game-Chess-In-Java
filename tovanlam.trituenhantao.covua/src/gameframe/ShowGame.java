package gameframe;

import java.awt.Menu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

import core.GameSetting;
import core.PositionBoard;

public class ShowGame extends JLabel {
	private MainChessFrame main;
	private GameSetting gameSetting;
	private PositionBoard aiChoisePosition;

	public ShowGame(MainChessFrame mainChessFrame, GameSetting gameSetting) {
		main = mainChessFrame;
		this.setFocusable(true);
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				GameSetting gameSetting2 = new GameSetting(gameSetting.getLevel(), gameSetting.isHumanFirst(),
						gameSetting.getBackgroundColor(), gameSetting.isAiPlay(), false, false, false,
						GameSetting.typeChessman);
				gameSetting2.testMode = true;
				MenuFrame menuFrame  = new MenuFrame(gameSetting2);
				menuFrame.setVisible(false);

				MainChessFrame mainChessFrame2 = new MainChessFrame(gameSetting2, menuFrame, true);
				mainChessFrame2.setTitle("***************   ONLY FOR TEST   ****************");
				mainChessFrame2.getBoardPanel().setPositionBoard(mainChessFrame.getBoardPanel().getPositionBoard()
						.getOldPositionBoard().copy(gameSetting2.getLevel(), gameSetting2));
				mainChessFrame2.getBoardPanel().repaint();
				new FrameShowGame(mainChessFrame.getBoardPanel().getPositionBoard().getOldPositionBoard()
						.copy(gameSetting2.getLevel(), gameSetting2), gameSetting2, mainChessFrame2);

			}
		});
	}

	class FrameShowGame extends JFrame {
		public FrameShowGame(PositionBoard reBoard, GameSetting gameSetting, MainChessFrame mainChessFrame) {
			this.setSize(1300, 710);
			this.add(new PanelShowGame(reBoard, gameSetting, mainChessFrame));
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setVisible(true);
		}

	}
}

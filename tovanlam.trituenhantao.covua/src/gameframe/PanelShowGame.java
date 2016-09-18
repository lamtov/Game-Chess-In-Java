package gameframe;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import core.GameSetting;
import core.PositionBoard;

public class PanelShowGame extends JPanel {
	private PositionBoard positionBoard;
	private GameSetting gameSetting;
	private ArrayList<ArrayList<ButtonPosition>> listVertical;
	private ArrayList<JPanel> listPanel;

	public PanelShowGame(PositionBoard positionBoard, GameSetting gameSetting, MainChessFrame mainChessFrame) {
		this.positionBoard = positionBoard;
		this.gameSetting = gameSetting;
		listVertical = new ArrayList<>();
		this.setBorder(new LineBorder(Color.BLACK));
		System.out.println("Vao day1");
		this.setBackground(gameSetting.getBackgroundColor());
		this.setLayout(new GridLayout(1, gameSetting.getLevel() + 1));
		System.out.println("Vao day2");
		listPanel = new ArrayList<>();
		for (int i = 0; i <= gameSetting.getLevel(); i++) {
			listPanel.add(new JPanel());
			ArrayList<ButtonPosition> btnList = new ArrayList<>();
			listVertical.add(btnList);
		}

		System.out.println("Vao day3");
		for (int i = listPanel.size() - 1; i >= 0; i--) {
			this.add(listPanel.get(i));
		}
		AlphaBeta(-10000, 10000, this.positionBoard);
		// //positionBoard.setListChildPosition();
		// System.out.println(positionBoard.getListChildPosition().size());
		// for(int i=0;i<positionBoard.getListChildPosition().size();i++){
		// System.out.println(positionBoard.getListChildPosition().get(i).getDepth()
		// + "");
		// }
		ButtonPosition btnPosition = new ButtonPosition(positionBoard, gameSetting, this, 1, true, mainChessFrame);
		listPanel.get(gameSetting.getLevel()).add(btnPosition);
		listVertical.get(positionBoard.getDepth()).add(btnPosition);
	}

	public void showChildPoint(PositionBoard positionBoard) {

	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

	}

	class ButtonPosition extends JButton {
		private PositionBoard positionBoard;
		private GameSetting gameSetting;
		private PanelShowGame panelShowGame;

		public ButtonPosition(PositionBoard positionBoard, GameSetting gameSetting, PanelShowGame panelShowGame,
				int numberInList, boolean isBest, MainChessFrame mainChessFrame) {
			this.positionBoard = positionBoard;
			this.gameSetting = gameSetting;
			this.panelShowGame = panelShowGame;
			if (positionBoard.getDepth() % 2 == gameSetting.getLevel() % 2) {
				this.setBackground(Color.BLUE);
			} else {
				this.setBackground(Color.GREEN);
			}
			if (isBest == true) {
				this.setBackground(Color.RED);
			}
			this.setForeground(Color.WHITE);

			this.setText(numberInList + ". " + positionBoard.getParentMove().toString() + " = "
					+ positionBoard.getAlphabeta());

			this.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println(positionBoard.toTextSave());
					mainChessFrame.getBoardPanel().setPositionBoard(positionBoard);
					mainChessFrame.getBoardPanel().repaint();
					if (positionBoard.getDepth() != 0) {
						JPanel panel = panelShowGame.listPanel.get(positionBoard.getDepth() - 1);
						panel.removeAll();
						int k = positionBoard.getDepth() - 1;
						while (k != 0) {
							k--;
							panelShowGame.listPanel.get(k).removeAll();
							panelShowGame.listPanel.get(k).revalidate();
							panelShowGame.listPanel.get(k).repaint();
						}
						System.out.println(
								"///////////////////////////////////////////////////////////////////////////////");
						// for (int i = 0; i <
						// listVertical.get(positionBoard.getDepth()).size();
						// i++) {
						// if
						// (listVertical.get(positionBoard.getDepth()).get(i).getBackground()
						// == Color.YELLOW) {
						// System.out.println("depth : " +
						// positionBoard.getDepth());
						//
						// if (positionBoard.getDepth() % 2 ==
						// gameSetting.getLevel() % 2) {
						// setBackground(Color.BLUE);
						// } else {
						// setBackground(Color.GREEN);
						// }
						// } else {
						// System.out.println("depth : " +
						// positionBoard.getDepth() + "i" + i);
						// }
						// }
						if (getBackground() != Color.RED) {
							setBackground(Color.YELLOW);
						}
						// panel.setBackground(Color.black);
						panel.setLayout(new GridLayout(positionBoard.getListChildPosition().size(), 1));
						for (int i = 0; i < positionBoard.getListAnalyzePosition().size(); i++) {
							if (i != positionBoard.getNoBestChild()) {
								ButtonPosition btn = new ButtonPosition(positionBoard.getListAnalyzePosition().get(i),
										gameSetting, panelShowGame, i + 1, false, mainChessFrame);
								panel.add(btn);
							} else {
								ButtonPosition btn = new ButtonPosition(positionBoard.getListAnalyzePosition().get(i),
										gameSetting, panelShowGame, i + 1, true, mainChessFrame);
								panel.add(btn);
							}

						}
						panel.revalidate();

					}
				}
			});
		}

	}

	private int AlphaBeta(int alpha, int beta, PositionBoard positionBoard1) {
		positionBoard1.setValue();
		positionBoard1.getOldPositionBoard().getListAnalyzePosition().add(positionBoard1);
		if (positionBoard1.getDepth() != 0) {
			if (positionBoard1.getValue() < 15000 && positionBoard1.getValue() > -15000) {
				positionBoard1.setListChildPosition();
				for (int i = 0; i < positionBoard1.getListChildPosition().size(); i++) {
					positionBoard1.getListChildPosition().get(i).setDepth(positionBoard1.getDepth() - 1);
				}
			}
		}
		int alphaBeta = 0;
		if (positionBoard1.getDepth() == 0 || positionBoard1.getListChildPosition().size() == 0) {
			alphaBeta = positionBoard1.getValue();
			positionBoard1.setAlphabeta(alphaBeta);
			return alphaBeta;
		}

		if ((this.gameSetting.getLevel() - positionBoard1.getDepth()) % 2 == 0) {
			int bestChild = -10000000;

			int i = 0;
			while (i < positionBoard1.getListChildPosition().size() && bestChild < beta) {
				if (bestChild > alpha) {
					alpha = bestChild;
				}
				int value = AlphaBeta(alpha, beta, positionBoard1.getListChildPosition().get(i));
				if (value > bestChild) {
					bestChild = value;
					positionBoard1.setBestChild(positionBoard1.getListChildPosition().get(i));
					positionBoard1.setNoBestChild(i);
				}
				i++;
			}
			alphaBeta = bestChild;

		}
		if ((this.gameSetting.getLevel() - positionBoard1.getDepth()) % 2 == 1) {
			int bestChild = 10000000;
			int i = 0;
			while (i < positionBoard1.getListChildPosition().size() && bestChild > alpha) {
				if (bestChild < beta) {
					beta = bestChild;
				}
				int value = AlphaBeta(alpha, beta, positionBoard1.getListChildPosition().get(i));
				if (value < bestChild) {
					bestChild = value;
					positionBoard1.setBestChild(positionBoard1.getListChildPosition().get(i));
					positionBoard1.setNoBestChild(i);
				}
				i++;
			}
			alphaBeta = bestChild;
		}
		positionBoard1.setAlphabeta(alphaBeta);
		return alphaBeta;

	}

}

package core;

import gameframe.MainChessFrame;
import gameframe.MenuFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

import sound.AudioPlayer;
import chessPieces.Pieces;

public class ButtonLoad extends JLabel {

	public ButtonLoad(MenuFrame menuFrame, GameSetting gameSetting) {
		Icon loadIcon = new ImageIcon(this.getClass().getResource("/image/load.png"));
		setIcon(loadIcon);
		setHorizontalAlignment(CENTER);

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				AudioPlayer.playClick();
				MainChessFrame mFrame = new MainChessFrame(gameSetting, menuFrame, false);

				PositionBoard positionBoard = new PositionBoard(gameSetting.getLevel(), gameSetting);
				positionBoard.setBoardPanel(mFrame.getBoardPanel());
				for (int i = 0; i < 63; i++) {
					positionBoard.getHashMapPieces().put(i, "NoPiece");
				}
				String workingdir = System.getProperty("user.dir");
				JFileChooser fileChooser = new JFileChooser(new File(workingdir));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
				fileChooser.addChoosableFileFilter(filter);

				fileChooser.setFileFilter(filter);
				int returnVal = fileChooser.showOpenDialog(new JFrame());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					System.out.println(file);
					System.out.println("Open file : " + file.getPath());
					try {
						BufferedReader br = new BufferedReader(new FileReader(file));
						String line = br.readLine();
						while (line != null) {
							Pieces pieces = Pieces.getPiecesByText(line, positionBoard);
							if (pieces.getSide().equals("Down")) {
								positionBoard.getListPiecesHuman().add(pieces);
								positionBoard.getHashMapPieces().put(pieces.getNumberInBoard(), "Human");
							} else {
								positionBoard.getListPiecesAi().add(pieces);
								positionBoard.getHashMapPieces().put(pieces.getNumberInBoard(), "Ai");

							}
							line = br.readLine();
						}
						positionBoard.setAiWasEat(false);
						positionBoard.setHumanWasEat(false);

						mFrame.getBoardPanel().setPositionBoard(positionBoard);
						mFrame.getBoardPanel().repaint();

						menuFrame.setVisible(false);
						mFrame.setVisible(true);
						br.close();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

				}
			}
		});

	}
}
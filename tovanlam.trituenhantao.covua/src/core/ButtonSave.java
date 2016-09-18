package core;

import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

import chessPanel.BoardPanel;
import sound.AudioPlayer;

public class ButtonSave extends JLabel {

	public ButtonSave(BoardPanel boardPanel) {
		this.setFocusable(true);
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				AudioPlayer.playClick();
				String workingdir = System.getProperty("user.dir");
				JFileChooser fileChooser = new JFileChooser(new File(workingdir));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
				fileChooser.addChoosableFileFilter(filter);
				fileChooser.setFileFilter(filter);
				int returnVal = fileChooser.showSaveDialog(new JFrame());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					String nameFile = file.getName();
					if (nameFile.length() > 4) {
						String txt = nameFile.substring(nameFile.length() - 4);
						System.out.println(txt);
						if (txt.equals(".txt")) {

						} else {
							file = new File(file.toString() + ".txt");
							file = new File(file.getParentFile(), nameFile + ".txt");
						}
					} else {
						file = new File(file.toString() + ".txt");
						file = new File(file.getParentFile(), nameFile + ".txt");
					}
					System.out.println("Open file : " + file.getPath());
					try {
						BufferedWriter bw = new BufferedWriter(new FileWriter(file));
						String document = boardPanel.getPositionBoard().toTextSave();
						bw.write(document);
						bw.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		this.setDisplayedMnemonic(KeyEvent.VK_S);
	}
}

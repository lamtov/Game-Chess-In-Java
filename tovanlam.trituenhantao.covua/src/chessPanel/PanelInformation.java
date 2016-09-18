package chessPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import core.GameSetting;
import core.Utils;

public class PanelInformation extends JPanel {
	private JTextPane textPane;
	private GameSetting gameSetting;

	public PanelInformation(GameSetting gameSetting) {
		this.gameSetting = gameSetting;
		this.setLayout(new BorderLayout());
		this.setBounds(Utils.BOARD_GAME_WIDTH + 5, 125, Utils.GAME_WIDTH-Utils.BOARD_GAME_WIDTH - 15, 390);
		textPane = new JTextPane();
		textPane.setBackground(Color.BLACK);
		textPane.setCaretColor(Color.BLACK);
		textPane.setSelectedTextColor(Color.YELLOW);

		String mode;
		if (gameSetting.isAiPlay()) {
			mode = Utils.STRING_MODE_AI;
		} else if (gameSetting.isWatchMode()) {
			mode = Utils.STRING_WATCH_MODE;
		} else {
			mode = Utils.STRING_MODE_2P; 
		}
		
		appendToPane(Utils.INFORMATION_INTRODUCE + "Mode: " + mode + "\n" + "Level: " + gameSetting.getLevel(),Color.MAGENTA);
		Border emptyBorder = BorderFactory.createEmptyBorder(35, 20, 35, 20);
		this.setBorder(emptyBorder);
		this.add(new JScrollPane(textPane), BorderLayout.CENTER);
	}


	public void insertText(String string) {
		appendToPane(string, Color.RED);
	}

	public void appendTextGreen(String string) {
		appendToPane(string, Color.GREEN);
	}

	public void appendTextRed(String string) {
		appendToPane(string, Color.YELLOW);
	}
	public void appendTextYellow(String string) {
		appendToPane(string, Color.YELLOW);
	}

	public void appendToPane(String string, Color c) {
		String string2 = string +"\n";
		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

		aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
		aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

		int len = textPane.getDocument().getLength();
		textPane.setCaretPosition(len);
		textPane.setCharacterAttributes(aset, false);
		textPane.replaceSelection(" " +string2);
	}
	
	public void setText(String string) {
		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.BLUE);

		aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
		aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

		int len = textPane.getDocument().getLength();
		textPane.setCaretPosition(len);
		textPane.setCharacterAttributes(aset, false);
		textPane.replaceSelection(string);
	}
	public void appendImage(Image image) {
		textPane.insertIcon(new ImageIcon(image));
	}
	public void clear() {
		textPane.setText("");
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension d = getSize();
		g.drawImage(new ImageIcon(Utils.class.getResource("/image/boardInfo.png")).getImage(), 0, 0, d.width, d.height, null);
	}
}

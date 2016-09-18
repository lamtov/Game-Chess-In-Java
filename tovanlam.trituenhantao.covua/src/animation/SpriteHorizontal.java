package animation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import core.Utils;

public class SpriteHorizontal extends Sprite {

	public SpriteHorizontal(String namePicture, int size, int time) throws IOException {
		super(namePicture, size, time);
		BufferedImage spriteSheet = ImageIO.read(new File(Utils.getCurrentDirectory() + "//image//" + namePicture));
		this.width = spriteSheet.getWidth() / size;
		this.height = spriteSheet.getHeight();
		for (int i = 0; i < size; i++) {
			listFrame.add(new Frame(spriteSheet.getSubimage(width * i, 0, width, height),(int)time/size));
		}
		size = listFrame.size();
		if (width / 345 < height / 120) {
			heightInPanel = 120;
			widthInPanel = width / (height / 120);
			y0 = 0;
			x0 = (345 - widthInPanel) / 2;
		} else {
			heightInPanel = height / (width / 260);
			widthInPanel = 345;
			x0 = 0;
			y0 = (120 - heightInPanel) / 2;
		}
	}
}

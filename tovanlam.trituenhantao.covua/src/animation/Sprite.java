package animation;

import java.io.IOException;
import java.util.ArrayList;

import javax.naming.NameParser;

public class Sprite {
	protected ArrayList<Frame> listFrame;
	private int size;
	protected int width;
	protected int height;
	protected int widthInPanel;
	protected int heightInPanel;
	private int currentFrame;
	protected double x0, y0;
	private long beforeTime;
	public long timeRun;
	private long time;
	public Sprite(String namePicture, int size, int time) throws IOException {
		this.size = size;
		listFrame = new ArrayList<Frame>();
		timeRun = 0;
		currentFrame = 0;
		beforeTime = System.currentTimeMillis();
		this.time = time;
	}

	public int getWidthInPanel() {
		return widthInPanel;
	}

	public int getHeightInPanel() {
		return heightInPanel;
	}

	public void nextFrame() {
		
		if ((System.currentTimeMillis() - beforeTime) > listFrame.get(currentFrame).getSpeedFrame()) {
			if (currentFrame < size-1) {
				currentFrame = (currentFrame + 1);
			}
			timeRun = timeRun + System.currentTimeMillis() - beforeTime;
			beforeTime = System.currentTimeMillis();

		}

	}

	public boolean isEndShow() {
		if (timeRun >= (time+400) && currentFrame >= size-1) {
			//System.out.println("timerun = " + timeRun +">=" + "time" +time +"currentFrame  = " +currentFrame + "size " +size);
			return true;
		} else {
			return false;
		}
	}

	public void reset() {
		timeRun = 0;
		currentFrame = 0;
		beforeTime = System.currentTimeMillis();
	}

	public ArrayList<Frame> getListFrame() {
		return listFrame;
	}

	public void setListFrame(ArrayList<Frame> listFrame) {
		this.listFrame = listFrame;
	}

	public int getCurrentFrame() {
		return currentFrame;
	}

	public void setCurrentFrame(int currentFrame) {
		this.currentFrame = currentFrame;
	}

	public long getBeforeTime() {
		return beforeTime;
	}

	public void setBeforeTime() {
		beforeTime = System.currentTimeMillis();
	}

	public int getSize() {
		return size;
	}

	public double getX0() {
		return x0;
	}

	public double getY0() {
		return y0;
	}

}

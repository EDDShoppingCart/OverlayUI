package team.gif.windows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import team.gif.DataImporter;
import team.gif.sprites.Button;
import team.gif.sprites.Image;
import team.gif.sprites.Point;

public final class DataWindow extends Window {
	
	private final String layoutPic = "/Users/Patrick/Desktop/VaultButton.png";
	private final String optionsPic = "/Users/Patrick/Desktop/VaultButton.png";
	private final String passivePic = "/Users/Patrick/Desktop/CapButton.png";
	private final String activePic = "/Users/Patrick/Desktop/CapButtonFaded.png";
	private final String coordinatePath = "/Users/Patrick/Documents/workspace/CartUI/res/data.txt";
	
//	// TODO: Scale graphics with window dimensions
//	// TODO: Scale path coordinates with layout size
//	// R, G, B, x, y, W, H
	private Image layout;
	private Image options;
	
	private Button loadLayout;
	private Button loadPath;
	private Button saveOverlay;
	private List<Button> buttons = new ArrayList<Button>();
	private List<Point> points	 = new ArrayList<Point>();
	
	public DataWindow(String name) throws LWJGLException {
		super(name);
	}
	
	public DataWindow(String name, int width, int height) throws LWJGLException {
		super(name, width, height);
	}
	
	protected final void init() {
		Display.setInitialBackground(0f, 0f, 0f);
		layout	= new Image(0f, 200f, layoutPic, 1000f, 600f);
		options	= new Image(1020f, 685f, optionsPic, 240f, 100f);
		loadLayout	= new Button(1040f, 580f, passivePic, activePic, 200f, 80f);
		loadPath	= new Button(1040f, 480f, passivePic, activePic, 200f, 80f);
		saveOverlay = new Button(1040f, 380f, passivePic, activePic, 200f, 80f);
		buttons.add(loadLayout);
		buttons.add(loadPath);
		buttons.add(saveOverlay);
		
		try {
			for (float[] f : DataImporter.getCoordinates(coordinatePath)) {
				points.add(new Point(1f, 1f, 1f, f[0] + layout.getX(), f[1] + layout.getY()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected final void update() {
		
		// TODO: Create a mouse clicking listener for this, so this doesn't eat up the event for other windows
		while (Mouse.next()) {
			if (Mouse.getEventButton() == 0) {
				for (Button b : buttons) {
					if (Mouse.getEventButtonState()) {
						if (b.intersects(Mouse.getEventX(), Mouse.getEventY())) {
							b.activate();
							break;
						} else continue;
					} else {
						b.deactivate();
						if (b.intersects(Mouse.getEventX(), Mouse.getEventY())) b.fireEvent();
					}
//					if (b.intersects(Mouse.getEventX(), Mouse.getEventY())) {
//						if (Mouse.getEventButtonState()) {
//							b.activate();
//						} else {
//							b.fireEvent();
//						}
//						break;
//					} else continue;
				}
			}
		}
		
		layout.draw();
		options.draw();
		loadLayout.draw();
		loadPath.draw();
		saveOverlay.draw();
		for (Point p : points) {
			p.draw();
		}
	}
	
	protected final void end() {}
	
}

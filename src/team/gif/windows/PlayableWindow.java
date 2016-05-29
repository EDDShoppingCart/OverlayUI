package team.gif.windows;

import static org.lwjgl.opengl.GL11.*;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import team.gif.sprites.Button;

public class PlayableWindow extends Window {
	
	
	private Button button = null;
	private List<Button> buttons = new ArrayList<Button>();
	
	public PlayableWindow(String name, int width, int height) throws LWJGLException {
		super(name, width, height);
	}
	
	public PlayableWindow(String name) throws LWJGLException {
		super(name);
	}
	
	@Override
	protected final void init() {
		button = new Button(100f, 100f, "/Users/Patrick/Desktop/VaultButton.png",
							"/Users/Patrick/Desktop/VaultButtonFaded.png", 256f, 256f);
		buttons.add(button);
	}
	
	@Override
	protected final void update() {
		// Clear the screen and depth buffer
//		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glClear(GL_COLOR_BUFFER_BIT);
		
		while (Mouse.next()) {
			
			// TODO: Create a mouse clicking listener for this, so this doesn't
			//		 eat up the event for other windows
			if (Mouse.getEventButton() == 0) {
				for (Button b : buttons) {
					if (Mouse.getEventButtonState()) {
						if (b.intersects(Mouse.getEventX(), Mouse.getEventY())) b.activate();
						break;
					} else {
						b.deactivate();
					}
				}
			}
			
		}
		
		button.draw();
	}
	
}

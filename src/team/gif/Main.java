package team.gif;

import org.lwjgl.LWJGLException;
import team.gif.windows.*;
 
public class Main {
	
	// TODO: Run an initialization function to find actual screen dimensions
	// TODO: Can't tab out of fullscreen mode; have to kill the process
	// TODO: Use a keyboard button to switch in/out of fullscreen mode (look at lwjgl fullscreen page)
	public static final int screenX = 800;
	public static final int screenY = 600;
	public static final int FPS = 60;
     
    public static void main(String[] argv) {
        DataWindow display;
		
		try {
//			display = new DataWindow("Trial");
			display = new DataWindow("Trial", 1280, 800);
			display.start();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
    }
    
}
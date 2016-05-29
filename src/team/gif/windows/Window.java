package team.gif.windows;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import team.gif.Main;


public abstract class Window implements Runnable {
	
	private final Thread thread;
	
	public Window(final String name, final int width, final int height) throws LWJGLException {
		thread = new Thread(this, name);
         
        Display.setDisplayMode(new DisplayMode(width, height));
        Display.setTitle(name);
	}
	
	public Window(final String name) throws LWJGLException {
		thread = new Thread(this, name);
		Display.setFullscreen(true);
		Display.setTitle(name);
	}
	
	private final void create() {
		try {
			Display.create();
			
			glEnable(GL_TEXTURE_2D);
			glClearColor(0.0f, 0.0f, 0.0f, 0.0f);          
	        
	        // enable alpha blending
			// FIXME: Figure out what this shit does
	        glEnable(GL_BLEND);
	        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	        glViewport(0, 0, Display.getWidth(), Display.getHeight());
	        glMatrixMode(GL_MODELVIEW);
			
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			glOrtho(0, Display.getWidth(), 0, Display.getHeight(), 1, -1);
			glMatrixMode(GL_MODELVIEW);
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	protected void init() {}
	protected void update() {}
	protected void end() {}

	@Override
	public final void run() {
		if (!thread.isAlive()) throw new RuntimeException("Window has been started in exterior thread!");
		create();
		init();
		while (!Display.isCloseRequested()) {
			update();
			Display.update();
			Display.sync(Main.FPS);
		}
		end();
		Display.destroy();
		
	}
	
	public final void start() {
		thread.start();
	}
	
}

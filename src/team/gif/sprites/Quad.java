package team.gif.sprites;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;


public class Quad extends Shape {
	
	private final float width;
	private final float height;
	
	/**
	 * Creates a new quadrilateral to be drawn to the screen.
	 * @param red		Red shading 0-1
	 * @param green		Green shading 0-1
	 * @param blue		Blue shading 0-1
	 * @param width		The width of the quad
	 * @param height	The height of the quad
	 */
	public Quad(final float red,	final float green,	final float blue,
				final float x,		final float y,		final float width,	final float height) {
		super(red, green, blue, x, y);
		this.width = width;
		this.height = height;
	}
	
	public final void draw(float red, float green, float blue) {
		// set the color of the quad (R,G,B,A)
		glColor3f(red, green, blue);
		
		// draw quad
		glBegin(GL_QUADS);
	      glVertex2f(x,			y);				// low left
		  glVertex2f(x + width, y);				// low right
		  glVertex2f(x + width, y + height);	// up right
		  glVertex2f(x,			y + height);	// up left
		glEnd();
	}
	
	public void draw() {
		draw(red, green, blue);
	}
	
	/**
	 *  Returns an array of floats consisting of the lower left coordinates,
	 *  as well as the width and height of the quad.
	 * @return { x, y, width, height };
	 */
	public float[] getDimensions() {
		return new float[] { x, y, width, height };
	}
	
	public boolean intersects(float x, float y) {
		return (x >= this.x && x <= this.x + width) && (y >= this.y && y <= this.y + height);
	}
	
}

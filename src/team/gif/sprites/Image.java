package team.gif.sprites;

import static org.lwjgl.opengl.GL11.*;
import java.io.IOException;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Image {
	
	protected Texture texture;
	protected float x;
	protected float y;
	protected final float width;
	protected final float height;
	
	// TODO: Notify in console when WxH is not a factor of the original image
	public Image(float x, float y, String filename, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		try {
			
			switch (filename.substring(filename.length() - 3, filename.length())) {
				case "jpg":
					texture = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream(filename));
					break;
				case "png":
					texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(filename));
					break;
				default:
					throw new IOException("Active picture has invalid file extension. must be either .jpg or .png");
					
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
	}
	
	public void draw() {
		Color.white.bind();
        texture.bind(); // or GL11.glBind(texture.getTextureID());
//		glBindTexture(target, texture.getTextureID());
         
        // TODO: Make more efficient by finding texWidth * scale beforehand, instead of re-evaluating constantly
        glBegin(GL_QUADS);
            glTexCoord2f(0, 1);
            glVertex2f(x, y);
            glTexCoord2f(1, 1);
//            glVertex2f(x + texture.getTextureWidth(), y);
            glVertex2f(x + width, y);
            glTexCoord2f(1, 0);
//            glVertex2f(x + texture.getTextureWidth(), y + texture.getTextureHeight());
            glVertex2f(x + width, y + height);
            glTexCoord2f(0, 0);
//            glVertex2f(x, y + texture.getTextureHeight());
            glVertex2f(x, y + height);
        glEnd();
	}
	
	public boolean intersects(float x, float y) {
		return (x >= this.x && x <= this.x + width) &&
				(y >= this.y && y <= this.y + height);
	}
	
	public float getX() { return x; }
	public float getY() { return y; }
	public float getWidth() { return width; }
	public float getHeight() { return height; }

	
}

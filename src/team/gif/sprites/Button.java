package team.gif.sprites;

import java.awt.Desktop;
import java.awt.FileDialog;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Button extends Image {
	
	private Texture passiveTexture;
	private Texture activeTexture;
	private JFileChooser fc = new JFileChooser();
	
	// FIXME: I don't like this workaround.
	// FIXME: Make this actually work as a scalable button
	public Button(float x, float y, String passivePic, String activePic, float width, float height) {
		super(x, y, passivePic, width, height);
		passiveTexture = texture;
		try {
			
			switch (activePic.substring(activePic.length() - 3, activePic.length())) {
				case "jpg":
					activeTexture = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream(activePic));
					break;
				case "png":
					activeTexture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(activePic));
					break;
				default:
					throw new IOException("Active picture has invalid file extension. must be either .jpg or .png");
					
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		
	}
	
	public void activate() {
		texture = activeTexture;
		super.draw();
	}
	
	public void deactivate() {
		texture = passiveTexture;
		super.draw();
	}
	
	public void fireEvent() {
		// FIXME: make this work
		
		System.setProperty("apple.awt.fileDialogForDirectories", "true");
		
		try {
			fc.setDialogTitle("This is a chooser");
			System.out.println("0");
			int returnVal = fc.showOpenDialog(null);
			System.out.println("1");
			
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				System.out.println("Succeeded");
	            File file = fc.getSelectedFile();
	            Desktop.getDesktop().open(new File(file.getAbsolutePath()));
	        } else {
	            System.out.println("Failed");
	        }

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public final void draw() {
		super.draw();
		if (texture == activeTexture && !intersects(Mouse.getX(), Mouse.getY())) deactivate();
	}
	
}

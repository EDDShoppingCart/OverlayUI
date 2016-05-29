package team.gif.sprites;

public abstract class Shape {
	
	protected float red;
	protected float green;
	protected float blue;
	protected float x;
	protected float y;
	
	public Shape(final float red,	final float green,	final float blue,
			 	 final float x,		final float y) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.x = x;
		this.y = y;
	}
	
	public abstract void draw();
	
}

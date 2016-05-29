package team.gif.sprites;


public final class Point extends Quad {
	
	// TODO: Cut off the point if it extends beyond the boundary of the overlay
	public Point(final float red,	final float green,	final float blue,
				 final float x,		final float y) {
		super(red, green, blue, x, y, 2, 2);
	}
	
}

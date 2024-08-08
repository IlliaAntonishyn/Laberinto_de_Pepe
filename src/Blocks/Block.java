package Blocks;

public class Block {
	
	public int red;
	public int green;
	public int blue;
	
	public static final int cellHeight = 90;
	public static final int cellWidth = 90;
	
	public int width;
	public int height;
	
	public int xPos;
	public int yPos;
	
	public boolean solid;
		
	public Block(int xPos, int yPos, int width, int height, boolean solid) {
		this.xPos = xPos;
		this.yPos = yPos;
		
		this.width = width;
		this.height = height;
		
		this.solid = solid;
	}
	
	public void action() {}
	
}

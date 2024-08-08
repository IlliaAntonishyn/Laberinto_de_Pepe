package Blocks;

public class Wall extends Block{
	
	public Wall(int xPos, int yPos) {
		super(xPos, yPos, Block.cellWidth, Block.cellHeight, true);
		red = 128;
		green = 128;
		blue = 128;
	}
	
}

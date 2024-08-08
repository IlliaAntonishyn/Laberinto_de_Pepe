package Blocks;

public class Bedrock extends Block{
	
	public Bedrock(int xPos, int yPos) {
		super(xPos, yPos, Block.cellWidth, Block.cellHeight, true);
		red = 0;
		green = 0;
		blue = 0;
	}

}

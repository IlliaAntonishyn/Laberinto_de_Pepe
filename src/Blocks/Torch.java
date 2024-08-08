package Blocks;

public class Torch extends Block{

	public Torch(int xPos, int yPos) {
		super(xPos, yPos, Block.cellWidth, Block.cellHeight, false);
		generateLight();
		red = 255;
		green = 255;
		blue = 0;
	}
	
	private void generateLight() {
		
	}
	
}

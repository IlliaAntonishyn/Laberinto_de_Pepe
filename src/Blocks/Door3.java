package Blocks;

public class Door3 extends Block{

	public Door3(int xPos, int yPos) {
		super(xPos, yPos, Block.cellWidth, Block.cellHeight, true);
		red = 0;
		green = 0;
		blue = 204;
		
	}
	
	public void setOpened() {
		solid = false;
	}
	
}

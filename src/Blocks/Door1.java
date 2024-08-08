package Blocks;

public class Door1 extends Block{

	public Door1(int xPos, int yPos) {
		super(xPos, yPos, Block.cellWidth, Block.cellHeight, true);
		red = 102;
		green = 204;
		blue = 255;
	}
	
	public void setOpened() {
		solid = false;
	}
	
}

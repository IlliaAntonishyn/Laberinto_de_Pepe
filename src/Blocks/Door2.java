package Blocks;

public class Door2 extends Block{
	
	public Door2(int xPos, int yPos) {
		super(xPos, yPos, Block.cellWidth, Block.cellHeight, true);
		red = 0;
		green = 102;
		blue = 255;
	}
	
	public void setOpened() {
		solid = false;
	}

}

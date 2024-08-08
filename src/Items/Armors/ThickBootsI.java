package Items.Armors;

import A.Level;
import Entities.A.Item;
import Items.A.ArmorsI;

public class ThickBootsI extends ArmorsI{
	
	public ThickBootsI() {
		super(2, 2, 30, -5, 0);
		super.item = new Item(0, 0);
		super.item.setItem(this);
	}
	


}

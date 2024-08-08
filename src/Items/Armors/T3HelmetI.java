package Items.Armors;

import A.Level;
import Entities.A.Item;
import Items.A.ArmorsI;

public class T3HelmetI extends ArmorsI{
	
	public T3HelmetI() {
		super(2, 2, 20, 5, 10);
		super.item = new Item(0, 0);
		super.item.setItem(this);
	}
	

}

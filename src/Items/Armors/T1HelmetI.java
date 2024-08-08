package Items.Armors;

import A.Level;
import Entities.A.Item;
import Items.A.ArmorsI;

public class T1HelmetI extends ArmorsI{
	
	public T1HelmetI() {
		super(2, 2, 10, 0, 5);
		super.item = new Item(0, 0);
		super.item.setItem(this);
	}
	


}

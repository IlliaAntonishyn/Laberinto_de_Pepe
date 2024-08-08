package Items.Armors;

import A.Level;
import Entities.A.Item;
import Items.A.ArmorsI;

public class T2HelmetI extends ArmorsI{
	
	public T2HelmetI() {
		super(2, 2, 15, 5, 5);
		super.item = new Item(0, 0);
		super.item.setItem(this);
	}
	


}

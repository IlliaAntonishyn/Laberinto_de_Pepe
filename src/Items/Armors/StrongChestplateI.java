package Items.Armors;

import A.Level;
import Entities.A.Item;
import Items.A.ArmorsI;

public class StrongChestplateI extends ArmorsI{
	
	public StrongChestplateI() {
		super(3, 4, 10, 0, 15);
		super.item = new Item(0, 0);
		super.item.setItem(this);
	}
	


}

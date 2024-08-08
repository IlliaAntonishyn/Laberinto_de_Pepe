package Items.Armors;

import A.Level;
import Entities.A.Item;
import Items.A.ArmorsI;

public class SuperiorChestplateI extends ArmorsI{
	
	public SuperiorChestplateI() {
		super(3, 4, 40, 15, 20);
		super.item = new Item(0, 0);
		super.item.setItem(this);
	}
	

}

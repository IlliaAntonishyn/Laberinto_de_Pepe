package Items.Armors;

import A.Level;
import Entities.A.Item;
import Items.A.ArmorsI;

public class LightChestplateI extends ArmorsI{
	
	public LightChestplateI() {
		super(3, 4, 20, 10, 0);
		super.item = new Item(0, 0);
		super.item.setItem(this);
	}

}

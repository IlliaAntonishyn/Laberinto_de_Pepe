package Items.Armors;

import A.Level;
import Entities.A.Item;
import Items.A.ArmorsI;

public class HeavyChestplateI extends ArmorsI{
	
	public HeavyChestplateI() {
		super(3, 4, 50, -10, 0);
		super.item = new Item(0, 0);
		super.item.setItem(this);
	}

}

package Items.Armors;

import A.Level;
import Entities.A.Item;
import Items.A.ArmorsI;

public class MinerHelmetI extends ArmorsI{
	
	public MinerHelmetI() {
		super(2, 2, 10, 0, 0);
		super.item = new Item(0, 0);
		super.item.setItem(this);
	}
	


}

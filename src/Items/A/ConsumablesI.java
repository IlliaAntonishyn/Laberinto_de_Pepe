package Items.A;

import A.Level;

public class ConsumablesI extends ItemI{
	
	public int stamina;
	public int health;
	
	public ConsumablesI(int width, int height, int quantity, int stamina, int health) {
		super(width, height, quantity);
		this.stamina = stamina;
		this.health = health;
	}
	
	public void action() {
		Level.player.health += health;
		Level.player.stamina += stamina;
		quantity--;
	}

}

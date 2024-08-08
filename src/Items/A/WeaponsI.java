package Items.A;

import java.awt.Color;

import Entities.A.Auxiliar;

public class WeaponsI extends ItemI{
		
	protected int strength;
	protected int stamina;

	public WeaponsI(int width, int height, int strength, int stamina) {
		super(width, height, 1);
		this.strength = strength;
		this.stamina = stamina;
		color = Color.red;
	}
	
}

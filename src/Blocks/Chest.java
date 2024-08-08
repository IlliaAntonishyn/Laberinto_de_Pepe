package Blocks;

import java.awt.Point;
import java.util.Random;

import A.Frame;
import A.Main;
import Items.Consumables.*;
import Items.A.ItemI;
import Items.A.TorchI;
import Items.Armors.*;
import Items.Bombs.*;
import Items.Keys.*;
import Items.Weapons.*;
import Panels.Inventory;

public class Chest extends Block{
	
	public Inventory inventory;
	
	public final int inventoryHeight = 7;
	public final int inventoryWidth = 5;
	
	public Chest(int xPos, int yPos) {
		super(xPos + 5, yPos + 5, Block.cellWidth - 10, Block.cellHeight - 10, true);
		red = 210;
		green = 105;
		blue = 30;
				
		inventory = new Inventory(inventoryWidth, inventoryHeight);	
	}
	
	public void setLoot() {
		Random rng = new Random();
		int number = rng.nextInt(195);
		addLoot(number);
		number = rng.nextInt(195);
		addLoot(number);
	}
	
	public Point randomLocation() {
		Random rng = new Random();
		return new Point(rng.nextInt(inventoryWidth), rng.nextInt(inventoryHeight));
	}
	
	private void addLoot(int number) {
		int counter = 0;
		Point location = randomLocation();
		ItemI item;
		if(number >= 0 && number < 20){
			item = new AppleI(1);
		}else if(number >= 20 && number < 35){
			item = new BreadI(1);
		}else if(number >= 35 && number < 50){
			item = new CheeseI(1);
		}else if(number >= 50 && number < 60){
			item = new HealingPotionI(1);
		}else if(number >= 60 && number < 90){
			item = new TorchI(1);
		}else if(number >= 90 && number < 100){
			item = new SmallBombI(1);
		}else if(number >= 100 && number < 107){
			item = new MediumBombI(1);
		}else if(number >= 107 && number < 111){
			item = new BigBombI(1);
		}else if(number >= 119 && number < 124){
			item = new BootsI();
		}else if(number >= 124 && number < 129){
			item = new QuickBootsI();
		}else if(number >= 129 && number < 134){
			item = new ThickBootsI();
		}else if(number >= 134 && number < 139){
			item = new HeavyChestplateI();
		}else if(number >= 139 && number < 144){
			item = new LightChestplateI();
		}else if(number >= 144 && number < 149){
			item = new StrongChestplateI();
		}else if(number >= 149 && number < 150){
			item = new SuperiorChestplateI();
		}else if(number >= 150 && number < 155){
			item = new MinerHelmetI();
		}else if(number >= 155 && number < 160){
			item = new T1HelmetI();
		}else if(number >= 160 && number < 165){
			item = new T2HelmetI();
		}else if(number >= 165 && number < 170){
			item = new T3HelmetI();
		}else if(number >= 170 && number < 175){
			item = new SwordI();
		}else if(number >= 175 && number < 180){
			item = new SpearI();
		}else if(number >= 180 && number < 185){
			item = new WandI();
		}else if(number >= 185 && number < 190){
			item = new MaceI();
		}else{
			item = new DaggerI();
		} 
		while(!inventory.checkIfFits(item, location.y, location.x) && counter < 20) {
			location = randomLocation();
			counter++;
		}
		if(counter < 20) inventory.placeItem(item, location.y, location.x);
	}
	
	public void action() {
		Main.gamePanel.openChest(inventory);
	}
	
}

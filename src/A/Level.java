package A;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import Blocks.*;
import Entities.A.Entity;
import Entities.A.Heart;
import Entities.A.Item;
import Entities.A.Player;
import Entities.Auxiliars.*;
import Entities.Auxiliars.Projectile;
import Entities.Enemies.ExplosiveEnemy;
import Entities.Enemies.MeleeEnemy;
import Entities.Enemies.RangeEnemy;
import Items.A.ItemI;
import Items.A.TorchI;
import Items.Armors.*;
import Items.Bombs.*;
import Items.Consumables.*;
import Items.Keys.T1KeyI;
import Items.Keys.T2KeyI;
import Items.Keys.T3KeyI;
import Items.Weapons.*;

public class Level {
	
	public static Block[][] field;
	private static List<Chest> chests;
	public static List<Entity> entityList;
	
	private static int width;
	private static int height;
	
	public static Player player;
	
	public static Point end;
	
	public static void loadNewLevel(String url) throws FileNotFoundException{
		chests = new ArrayList<Chest>();
		entityList = new ArrayList<Entity>(); 
		
		File file = new File(url);
		Scanner data = new Scanner(file);
		height = data.nextInt();
		width = data.nextInt();
		field = new Block[height][width];
		height *= Item.itemHeight;
		width *= Item.itemWidth;
		end = new Point(data.nextInt(), data.nextInt());
		
		int counter;
		for(int i = 0; i < field.length; i++) {
			for(int j = 0; j < field[i].length; j++) {
				counter = data.nextInt();
				switch (counter) {
				case 0:
					field[i][j] = new StoneFloor(j * Block.cellWidth, i * Block.cellHeight);
					break;
				case 1:
					field[i][j] = new Wall(j * Block.cellWidth, i * Block.cellHeight);
					break;
				case 2:
					field[i][j] = new Door1(j * Block.cellWidth, i * Block.cellHeight);
					break;
				case 3:
					field[i][j] = new Door2(j * Block.cellWidth, i * Block.cellHeight);
					break;
				case 4:
					field[i][j] = new Door3(j * Block.cellWidth, i * Block.cellHeight);
					break;
				case 5:
					field[i][j] = new Bedrock(j * Block.cellWidth, i * Block.cellHeight);
					break;
				}
			}
		}
		Random rng = new Random();
		counter = data.nextInt();
		int chestNum;
		int totalChestNum = 0;
		int chestX;
		int chestY;
		Chest chest;
		for(int i = 0; i < counter; i++) {
			chestNum = data.nextInt();
			for(int j = 0; j < chestNum; j++) {
				chestY = data.nextInt();
				chestX = data.nextInt();
				chest = new Chest(chestX * Block.cellWidth, chestY * Block.cellHeight);
				field[chestY][chestX] = chest;
				chests.add(chest);
				chest.setLoot();
			}
			ItemI key = new T1KeyI();
			switch(i) {
			case 0:
				key = new T1KeyI();
				break;
			case 1:
				key = new T2KeyI();
				break;
			case 2:
				key = new T3KeyI();
				break;
			}
			chests.get(rng.nextInt(chestNum) + totalChestNum).inventory.addItem(key);
			totalChestNum += chestNum;
		}
		player = new Player();
		entityList.add(player);
		counter = data.nextInt();
		for(int i = 0; i < counter; i++) {
			int height = data.nextInt();
			int width = data.nextInt();
			int type = data.nextInt();
			switch(type) {
				case 0:
					entityList.add(new MeleeEnemy(width * Block.cellWidth, height * Block.cellHeight));
					break;
				case 1:
					entityList.add(new RangeEnemy(width * Block.cellWidth, height * Block.cellHeight));
					break;
				case 2:
					entityList.add(new ExplosiveEnemy(width * Block.cellWidth, height * Block.cellHeight));
					break;
			}
		}
	}
	
	private static String getDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");  
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);  
	}
	
	public static void loadLevel(String url) throws FileNotFoundException {
		chests = new ArrayList<Chest>();
		entityList = new ArrayList<Entity>(); 
		File file = new File("PrivateSaves/" + url);
		Scanner data = new Scanner(file);
		field = new Block[data.nextInt()][data.nextInt()];
		end = new Point(data.nextInt(), data.nextInt());
		int cell;
		for(int i = 0; i < field.length; i++) {
			for(int j = 0; j < field[i].length; j++) {
				cell = data.nextInt();
				switch(cell) {
					case 0:
						field[i][j] = new StoneFloor(j * Block.cellWidth, i * Block.cellHeight);
						break;
					case 1:
						field[i][j] = new Wall(j * Block.cellWidth, i * Block.cellHeight);
						break;
					case 2:
						field[i][j] = new Door1(j * Block.cellWidth, i * Block.cellHeight);
						break;
					case 3:
						field[i][j] = new Door2(j * Block.cellWidth, i * Block.cellHeight);
						break;
					case 4:
						field[i][j] = new Door3(j * Block.cellWidth, i * Block.cellHeight);
						break;
					case 5:
						field[i][j] = new Bedrock(j * Block.cellWidth, i * Block.cellHeight);
						break;
					case 6:
						field[i][j] = new Torch(j * Block.cellWidth, i * Block.cellHeight);
						break;
				}
			}
		}
		Main.frameCount = data.nextInt();
		int counter = data.nextInt();
		String entityType;
		for(int i = 0; i < counter; i++) {
			entityType = data.next();
			entityType = data.next();
			if(entityType.contains("Player")) {
				player = new Player();
				entityList.add(player);
			}else if(entityType.contains("Item")) {
				entityList.add(new Item(0, 0));
			}else if(entityType.contains("BombEntity")) {
				entityList.add(new BombEntity(0, 0, 0, 0));
			}else if(entityType.contains("DaggerAttack")) {
				entityList.add(new DaggerAttack(0, 0, 0));
			}else if(entityType.contains("Explosion")) {
				entityList.add(new Explosion(0, 0, 0, 0));
			}else if(entityType.contains("FistAttack")) {
				entityList.add(new FistAttack(0, 0, 0, 0));
			}else if(entityType.contains("MaceAttack")) {
				entityList.add(new MaceAttack(0, 0, 0, 0));
			}else if(entityType.contains("Projectile")) {
				entityList.add(new Projectile(0, 0, 0, 0));
			}else if(entityType.contains("SpearAttack")) {
				entityList.add(new SpearAttack(0, 0, 0, 0));
			}else if(entityType.contains("SwordAttack")) {
				entityList.add(new SwordAttack(0, 0, 0, 0));
			}else if(entityType.contains("ExplosiveEnemy")) {
				entityList.add(new ExplosiveEnemy(0, 0));
			}else if(entityType.contains("MeleeEnemy")) {
				entityList.add(new MeleeEnemy(0, 0));
			}else {
				entityList.add(new RangeEnemy(0, 0));
			}
			
			entityList.get(i).xPos = data.nextDouble();
			entityList.get(i).yPos = data.nextDouble();
			entityList.get(i).xVel = data.nextDouble();
			entityList.get(i).yVel = data.nextDouble();
		}
		player.health = data.nextInt();
		player.stamina = data.nextInt();
		player.hit = false;
		player.leftReady = false;
		player.rightReady = false;
		
		counter = data.nextInt();
		String itemName;
		int xInventory;
		int yInventory;
		int quantity;
		for(int i = 0; i < counter; i++) {
			itemName = data.next();
			itemName = data.next();
			xInventory = data.nextInt();
			yInventory = data.nextInt();
			quantity = data.nextInt();
			player.inventory.placeItem(findItem(itemName), yInventory, xInventory);
			player.inventory.list.get(i).xInventory = xInventory;
			player.inventory.list.get(i).yInventory = yInventory;
			player.inventory.list.get(i).quantity = quantity;			
		}
		counter = data.nextInt();
		for(int i = 0; i < counter; i++) {
			itemName = data.next();
			itemName = data.next();
			if(!itemName.equalsIgnoreCase("null")) {
				player.equipment.equipment[i] = findItem(itemName);
				player.equipment.equipment[i].quantity = data.nextInt();
			}else {
				itemName = data.next();
			}
		}
		counter = data.nextInt();
		int counter2;
		int xChest;
		int yChest;
		for(int i = 0; i < counter; i++) {
			xChest = data.nextInt();
			yChest = data.nextInt();
			chests.add(new Chest(xChest - 5, yChest - 5));
			field[yChest / Block.cellHeight][xChest / Block.cellWidth] = chests.get(i);
			counter2 = data.nextInt();
			for(int j = 0; j < counter2; j++) {
				itemName = data.next();
				itemName = data.next();
				xInventory = data.nextInt();
				yInventory = data.nextInt();
				quantity = data.nextInt();
				chests.get(i).inventory.addItem(findItem(itemName));
				chests.get(i).inventory.list.get(j).quantity = quantity;
			}
		}
	}
	
	private static ItemI findItem(String itemName) {
		if(itemName.contains("TorchI")) {
			return new TorchI(0);
		}else if(itemName.contains("BootsI")) {
			return new BootsI();
		}else if(itemName.contains("HeavyChestplateI")) {
			return new HeavyChestplateI();
		}else if(itemName.contains("MineHelmetI")) {
			return new MinerHelmetI();
		}else if(itemName.contains("QuickBootsI")) {
			return new QuickBootsI();
		}else if(itemName.contains("StrongChestplateI")) {
			return new StrongChestplateI();
		}else if(itemName.contains("SuperiorChestplateI")) {
			return new SuperiorChestplateI();
		}else if(itemName.contains("T1HelmetI")) {
			return new T1HelmetI();
		}else if(itemName.contains("T2HelmetI")) {
			return new T2HelmetI();
		}else if(itemName.contains("T3HelmetI")) {
			return new T3HelmetI();
		}else if(itemName.contains("LightChestplateI")) {
			return new LightChestplateI();
		}else if(itemName.contains("ThickBootsI")) {
			return new ThickBootsI();
		}else if(itemName.contains("BigBombI")) {
			return new BigBombI(0);
		}else if(itemName.contains("MediumBombI")) {
			return new MediumBombI(0);
		}else if(itemName.contains("SmallBombI")) {
			return new SmallBombI(0);
		}else if(itemName.contains("AppleI")) {
			return new AppleI(0);
		}else if(itemName.contains("BreadII")) {
			return new BreadI(0);
		}else if(itemName.contains("CheeseI")) {
			return new CheeseI(0);
		}else if(itemName.contains("HealingPotionI")) {
			return new HealingPotionI(0);
		}else if(itemName.contains("T1KeyI")) {
			return new T1KeyI();
		}else if(itemName.contains("T2KeyI")) {
			return new T2KeyI();
		}else if(itemName.contains("T3KeyI")) {
			return new T3KeyI();
		}else if(itemName.contains("DaggerI")) {
			return new DaggerI();
		}else if(itemName.contains("MAceI")) {
			return new MaceI();
		}else if(itemName.contains("SpearI")) {
			return new SpearI();
		}else if(itemName.contains("SwordI")) {
			return new SwordI();
		}else {
			return new WandI();
		}
	}
	
	public static void saveLevel() {
		String date = getDate();
		try {
			FileWriter writer = new FileWriter("PublicSaves/" + date + ".txt");
			writer.write(height / Item.itemHeight + " " + width / Item.itemWidth + "\n");
			for (int i = 0; i < field.length; i++) {
				for (int j = 0; j < field[i].length; j++) {
					if (field[i][j].getClass() == Wall.class || field[i][j].getClass() == Bedrock.class) {
						writer.write("▒");
					}else if((int) player.xPos / Block.cellWidth == j && (int) player.yPos / Block.cellHeight == i) {
						writer.write("☺");
					}else if(end.y == j && end.x == i) {
						writer.write("♥");
					}else{
						writer.write(" ");
					}
				}
				writer.write("\n");
			}
			writer.close();
			
			writer = new FileWriter("PrivateSaves/" + date + ".txt");
			writer.write(height / Item.itemHeight + " " + width / Item.itemWidth + "\n");
			writer.write(end.x + " " + end.y + "\n");
			for (int i = 0; i < field.length; i++) {
				for (int j = 0; j < field[i].length; j++) {
					if (field[i][j].getClass() == Wall.class) {
						writer.write("1 ");
					}else if(field[i][j].getClass() == Bedrock.class) {
						writer.write("5 ");
					}else if(field[i][j].getClass() == Door1.class) {
						writer.write("2 ");
					}else if(field[i][j].getClass() == Door2.class) {
						writer.write("3 ");
					}else if(field[i][j].getClass() == Door3.class) {
						writer.write("4 ");
					}else if(field[i][j].getClass() == Torch.class){
						writer.write(6);
					}else {
						writer.write("0 ");
					}
				}
				writer.write("\n");
			}
			writer.write(Main.frameCount + "\n");
			writer.write(entityList.size() + "\n");
			for(int i = 0; i < entityList.size(); i++) {
				Entity entity = entityList.get(i);
				writer.write(entityList.get(i).getClass().toString() + "\n");
				writer.write((int) entity.xPos + " " + (int) entity.yPos + " " + (int) entity.xVel + " " + (int) entity.yVel + "\n");
			}
			writer.write(player.health + " " + player.stamina + "\n");
			writer.write(player.inventory.list.size() + "\n");
			for(int i = 0; i < player.inventory.list.size(); i++) {
				ItemI item = player.inventory.list.get(i);
				writer.write(player.inventory.list.get(i).getClass().toString() + "\n");
				writer.write(item.xInventory + " " + item.yInventory + " " + item.quantity + "\n");
			}
			writer.write("5\n");
			for(int i = 0; i < player.equipment.equipment.length; i++) {
				if(player.equipment.equipment[i] == null) {
					writer.write("null null\n");
					writer.write(-1 + "\n");
				}else{
					writer.write(player.equipment.equipment[i].getClass().toString() + "\n");
					writer.write(player.equipment.equipment[i].quantity + "\n");
				}
			}
			writer.write(chests.size() + "\n");
			for(int i = 0; i < chests.size(); i++) {
				writer.write(chests.get(i).xPos + " " + chests.get(i).yPos + "\n");
				Chest chest = chests.get(i);
				writer.write(chest.inventory.list.size() + "\n");
				for(int j = 0; j < chest.inventory.list.size(); j++) {
					ItemI item = chest.inventory.list.get(j);
					writer.write(chest.inventory.list.get(j).getClass().toString() + "\n");
					writer.write(item.xInventory + " " + item.yInventory + " " + item.quantity + "\n");
				}
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateEntities() {
		for(int i = entityList.size() - 1; i >= 0; i--) {
			entityList.get(i).updateEntity(); 
		}
		if(!findEnemies()) entityList.add(new Heart(end.y * Block.cellHeight, end.x * Block.cellWidth));
	}
	
	private static boolean findEnemies() {
		for(int i = entityList.size() - 1; i >= 0; i--) {
			if(entityList.get(i).getClass() == MeleeEnemy.class || entityList.get(i).getClass() == RangeEnemy.class || entityList.get(i).getClass() == ExplosiveEnemy.class)
				return true;
		}
		return false;
	}
	
	
	

}

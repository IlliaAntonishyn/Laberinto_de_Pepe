package Panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import A.Frame;
import A.Level;
import Items.A.ItemI;
import Items.Armors.*;

public class PlayerInventory extends JPanel implements MouseListener{
	
	public ItemI holding;
	
	private Inventory inventory = Level.player.inventory;
	private Equipment equipment = Level.player.equipment;
	
	private final int inventoryX = (3 * Frame.width / 2 - inventory.width) / 2;
	private final int inventoryY = (Frame.height - inventory.height) / 2;
	
	private final int equipmentX = (Frame.width / 2 - equipment.width) / 2;
	private final int equipmentY = (Frame.height - equipment.height) / 2;
	
	public PlayerInventory() {
		this.setSize(Frame.width, Frame.height);
		this.setLocation(0, 0);
		this.setLayout(null);
		this.setVisible(true);
		this.setOpaque(false);
		this.addMouseListener(this);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.orange);
		g.fillRect(inventoryX, inventoryY, inventory.width, inventory.height);
		g.fillRect(equipmentX, equipmentY, equipment.width, equipment.height);
		
		g.setColor(Color.DARK_GRAY);
		for(int i = 0; i < inventory.yCells; i++) {
			for(int j = 0; j < inventory.xCells; j++) {
				g.fillRect(j * ItemI.cellWidth + inventoryX, i * ItemI.cellHeight + inventoryY, ItemI.cellWidth - 1, ItemI.cellHeight - 1);
			}
		}
		
		if(equipment.equipment[0] != null) g.setColor(equipment.equipment[0].color);
		else g.setColor(Color.DARK_GRAY);
		g.fillRect((equipment.width - equipment.cellWidth) / 2 + equipmentX, (equipment.height / 4 - equipment.cellHeight) / 2 + equipmentY, equipment.cellWidth, equipment.cellHeight);
		if(equipment.equipment[1] != null) g.setColor(equipment.equipment[1].color);
		else g.setColor(Color.DARK_GRAY);
		g.fillRect((equipment.width - equipment.cellWidth) / 2 + equipmentX, (3 * equipment.height / 4 - equipment.cellHeight) / 2 + equipmentY, equipment.cellWidth, equipment.cellHeight);
		if(equipment.equipment[2] != null) g.setColor(equipment.equipment[2].color);
		else g.setColor(Color.DARK_GRAY);
		g.fillRect((equipment.width - equipment.cellWidth) / 2 + equipmentX, (7 * equipment.height / 4 - equipment.cellHeight) / 2 + equipmentY, equipment.cellWidth, equipment.cellHeight);
		if(equipment.equipment[3] != null) g.setColor(equipment.equipment[3].color);
		else g.setColor(Color.DARK_GRAY);
		g.fillRect(10 + equipmentX, equipment.height / 2 + equipmentY, equipment.cellWidth, equipment.cellHeight);
		if(equipment.equipment[4] != null) g.setColor(equipment.equipment[4].color);
		else g.setColor(Color.DARK_GRAY);
		g.fillRect(equipment.width - equipment.cellWidth - 10 + equipmentX, equipment.height / 2 + equipmentY, equipment.cellWidth, equipment.cellHeight);
		
		for(int i = 0; i < inventory.list.size(); i++) {
			ItemI item = inventory.list.get(i);
			g.setColor(item.color);
			g.fillRect(item.xInventory * ItemI.cellWidth + inventoryX, item.yInventory * ItemI.cellHeight + inventoryY, item.width * ItemI.cellWidth, item.height * ItemI.cellHeight);
//			System.out.println(item.ammount.getLocation().x + " " + item.ammount.getLocation().y);
		}
		
		if(holding != null) {
			g.setColor(holding.color);
			g.fillRect(this.getMousePosition().x, this.getMousePosition().y, holding.width * ItemI.cellWidth, holding.height * ItemI.cellHeight);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if(x >= inventoryX && x < inventoryX + inventory.width && y >= inventoryY && y < inventoryY + inventory.height) {
			if(holding == null) {
				if(inventory.inventory[(y - inventoryY) / ItemI.cellHeight][(x - inventoryX) / ItemI.cellWidth] != null) {
					holding = inventory.inventory[(y - inventoryY) / ItemI.cellHeight][(x - inventoryX) / ItemI.cellWidth];
					inventory.removeItem(holding, holding.yInventory, holding.xInventory);
				}
			}else {
				if(inventory.inventory[(y - inventoryY) / ItemI.cellHeight][(x - inventoryX) / ItemI.cellWidth] != null) {
					if(inventory.inventory[(y - inventoryY) / ItemI.cellHeight][(x - inventoryX) / ItemI.cellWidth].getClass() == holding.getClass()) {
						inventory.inventory[(y - inventoryY) / ItemI.cellHeight][(x - inventoryX) / ItemI.cellWidth].quantity += holding.quantity;
					}
				}else if(inventory.checkIfFits(holding, (y - inventoryY) / ItemI.cellHeight, (x - inventoryX) / ItemI.cellWidth)) {
					inventory.placeItem(holding, (y - inventoryY) / ItemI.cellHeight, (x - inventoryX) / ItemI.cellWidth);
				}
				holding = null;		
			}
		}else if(x >= equipmentX && x <= equipmentX + equipment.width && y >= equipmentY && x <= equipmentY + equipment.height){
			if(holding == null) {
				if(x >= (equipment.width - equipment.cellWidth) / 2 + equipmentX 
						&& x <= (equipment.width - equipment.cellWidth) / 2 + equipmentX + equipment.cellWidth
						&& y >= (equipment.height / 4 - equipment.cellHeight) / 2 + equipmentY 
						&& y <= (equipment.height / 4 - equipment.cellHeight) / 2 + equipmentY + equipment.cellHeight
						&& equipment.equipment[0] != null) {
					holding = equipment.equipment[0];
					equipment.equipment[0] = null;
				}else if(x >= (equipment.width - equipment.cellWidth) / 2 + equipmentX
						&& x <= (equipment.width - equipment.cellWidth) / 2 + equipmentX + equipment.cellWidth
						&& y >= (3 * equipment.height / 4 - equipment.cellHeight) / 2 + equipmentY
						&& y <= (3 * equipment.height / 4 - equipment.cellHeight) / 2 + equipmentY + equipment.cellHeight
						&& equipment.equipment[1] != null) {
					holding = equipment.equipment[1];
					equipment.equipment[1] = null;
				}else if(x >= (equipment.width - equipment.cellWidth) / 2 + equipmentX
						&& x <= (equipment.width - equipment.cellWidth) / 2 + equipmentX + equipment.cellWidth
						&& y >= (7 * equipment.height / 4 - equipment.cellHeight) / 2 + equipmentY
						&& y <= (7 * equipment.height / 4 - equipment.cellHeight) / 2 + equipmentY + equipment.cellHeight
						&& equipment.equipment[2] != null) {
					holding = equipment.equipment[2];
					equipment.equipment[2] = null;
				}else if(x >= 10 + equipmentX && x <= 10 + equipmentX + equipment.cellWidth
						&& y >= equipment.height / 2 + equipmentY && y <= equipment.height / 2 + equipmentY + equipment.cellHeight
						&& equipment.equipment[3] != null) {
					holding = equipment.equipment[3];
					equipment.equipment[3] = null;
				}else if(x >= equipment.width - equipment.cellWidth - 10 + equipmentX && x <= equipment.width - 10 + equipmentX
						&& y >= equipment.height / 2 + equipmentY && y <= equipment.height / 2 + equipmentY + equipment.cellHeight
						&& equipment.equipment[4] != null) {
					holding = equipment.equipment[4];
					equipment.equipment[4] = null;
				}
			}else {
				if(x >= (equipment.width - equipment.cellWidth) / 2 + equipmentX 
						&& x <= (equipment.width - equipment.cellWidth) / 2 + equipmentX + equipment.cellWidth
						&& y >= (equipment.height / 4 - equipment.cellHeight) / 2 + equipmentY 
						&& y <= (equipment.height / 4 - equipment.cellHeight) / 2 + equipmentY + equipment.cellHeight
						&& (holding.getClass() == MinerHelmetI.class || holding.getClass() == T1HelmetI.class
						|| holding.getClass() == T2HelmetI.class || holding.getClass() == T3HelmetI.class)
						&& equipment.equipment[0] == null) {
					equipment.equipment[0] = holding;
					holding = null;
				}else if(x >= (equipment.width - equipment.cellWidth) / 2 + equipmentX
						&& x <= (equipment.width - equipment.cellWidth) / 2 + equipmentX + equipment.cellWidth
						&& y >= (3 * equipment.height / 4 - equipment.cellHeight) / 2 + equipmentY
						&& y <= (3 * equipment.height / 4 - equipment.cellHeight) / 2 + equipmentY + equipment.cellHeight
						&& (holding.getClass() == HeavyChestplateI.class || holding.getClass() == LightChestplateI.class
						|| holding.getClass() == StrongChestplateI.class || holding.getClass() == SuperiorChestplateI.class)
						&& equipment.equipment[1] == null) {
					equipment.equipment[1] = holding;
					holding = null;
				}else if(x >= (equipment.width - equipment.cellWidth) / 2 + equipmentX
						&& x <= (equipment.width - equipment.cellWidth) / 2 + equipmentX + equipment.cellWidth
						&& y >= (7 * equipment.height / 4 - equipment.cellHeight) / 2 + equipmentY
						&& y <= (7 * equipment.height / 4 - equipment.cellHeight) / 2 + equipmentY + equipment.cellHeight
						&& (holding.getClass() == BootsI.class || holding.getClass() == QuickBootsI.class || holding.getClass() == ThickBootsI.class)
						&& equipment.equipment[2] == null) {
					equipment.equipment[2] = holding;
					holding = null;
				}else if(x >= 10 + equipmentX && x <= 10 + equipmentX + equipment.cellWidth
						&& y >= equipment.height / 2 + equipmentY && y <= equipment.height / 2 + equipmentY + equipment.cellHeight
						&& (equipment.equipment[3] == null || equipment.equipment[3].getClass() == holding.getClass())) {
					if(equipment.equipment[3] == null) {
						equipment.equipment[3] = holding;
					}else {
						equipment.equipment[3].quantity += holding.quantity;
					}
					holding = null;
				}else if(x >= equipment.width - equipment.cellWidth - 10 + equipmentX && x <= equipment.width - 10 + equipmentX
						&& y >= equipment.height / 2 + equipmentY && y <= equipment.height / 2 + equipmentY + equipment.cellHeight
						&& (equipment.equipment[4] == null || equipment.equipment[4].getClass() == holding.getClass())) {
					if(equipment.equipment[4] == null) {
						equipment.equipment[4] = holding;
					}else {
						equipment.equipment[4].quantity += holding.quantity;
					}
					holding = null;
				}
			}
		}else {
			holding.item.xPos = Level.player.xPos;
			holding.item.yPos = Level.player.yPos;
			Level.entityList.add(holding.item);
			holding = null;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
}

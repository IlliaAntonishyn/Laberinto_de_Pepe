package Panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import A.Frame;
import A.Level;
import Entities.A.Item;
import Items.A.ItemI;

public class ChestInventory extends JPanel implements MouseListener{

	public ItemI holding;
	
	private Inventory inventory = Level.player.inventory;
	private Inventory chest;
	
	private final int inventoryX = (3 * Frame.width / 2 - inventory.width) / 2;
	private final int inventoryY = (Frame.height - inventory.height) / 2;
	
	private final int chestX = (Frame.width / 2 - 5 * ItemI.cellWidth) / 2;;
	private final int chestY = (Frame.height - 7 * ItemI.cellHeight) / 2;
	
	public ChestInventory() {
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
		g.fillRect(chestX, chestY, chest.width, chest.height);
		
		g.setColor(Color.DARK_GRAY);
		for(int i = 0; i < inventory.yCells; i++) {
			for(int j = 0; j < inventory.xCells; j++) {
				g.fillRect(j * ItemI.cellWidth + inventoryX, i * ItemI.cellHeight + inventoryY, ItemI.cellWidth - 1, ItemI.cellHeight - 1);
			}
		}
		for(int i = 0; i < chest.yCells; i++) {
			for(int j = 0; j < chest.xCells; j++) {
				g.fillRect(j * ItemI.cellWidth + chestX, i * ItemI.cellHeight + chestY, ItemI.cellWidth - 1, ItemI.cellHeight - 1);
			}
		}
		
		for(int i = 0; i < inventory.list.size(); i++) {
			ItemI item = inventory.list.get(i);
			g.setColor(item.color);
			g.fillRect(item.xInventory * ItemI.cellWidth + inventoryX, item.yInventory * ItemI.cellHeight + inventoryY, item.width * ItemI.cellWidth, item.height * ItemI.cellHeight);
		}
		for(int i = 0; i < chest.list.size(); i++) {
			ItemI item = chest.list.get(i);
			g.setColor(item.color);
			g.fillRect(item.xInventory * ItemI.cellWidth + chestX, item.yInventory * ItemI.cellHeight + chestY, item.width * ItemI.cellWidth, item.height * ItemI.cellHeight);
		}
		
		if(holding != null) {
			g.setColor(holding.color);
			g.fillRect(this.getMousePosition().x, this.getMousePosition().y, holding.width * ItemI.cellWidth, holding.height * ItemI.cellHeight);
		}
	}
	
	public void setChest(Inventory chest) {
		this.chest = chest;
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
			
		}else if(x >= chestX && x < chestX + chest.width && y >= chestY && y < chestY + chest.height) {
			if(holding == null) {
				if(chest.inventory[(y - chestY) / ItemI.cellHeight][(x - chestX) / ItemI.cellWidth] != null) {
					holding = chest.inventory[(y - chestY) / ItemI.cellHeight][(x - chestX) / ItemI.cellWidth];
					chest.removeItem(holding, holding.yInventory, holding.xInventory);
				}
			}else {
				if(chest.inventory[(y - chestY) / ItemI.cellHeight][(x - chestX) / ItemI.cellWidth] != null) {
					if(chest.inventory[(y - chestY) / ItemI.cellHeight][(x - chestX) / ItemI.cellWidth].getClass() == holding.getClass()) {
						chest.inventory[(y - chestY) / ItemI.cellHeight][(x - chestX) / ItemI.cellWidth].quantity += holding.quantity;
					}
				}else if(chest.checkIfFits(holding, (y - chestY) / ItemI.cellHeight, (x - chestX) / ItemI.cellWidth)) {
					chest.placeItem(holding, (y - chestY) / ItemI.cellHeight, (x - chestX) / ItemI.cellWidth);
				}
				holding = null;	
			}
		}else if(holding != null){
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

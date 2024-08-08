package Panels;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import A.Frame;
import A.Level;
import A.Main;
import Items.A.ItemI;

public class Inventory{
	
	public List<ItemI> list = new ArrayList<ItemI>();
	public ItemI[][] inventory;
	
	public final int width;
	public final int height;
	
	public final int xCells;
	public final int yCells;
	
	public Inventory(int xCells, int yCells) {
		this.xCells = xCells;
		this.yCells = yCells;
		inventory = new ItemI[yCells][xCells];
		
		width = xCells * ItemI.cellWidth;
		height = yCells * ItemI.cellHeight;
	}
	
	public void addItem(ItemI item) {
		for(int i = 0; i < yCells; i++) {
			for(int j = 0; j < xCells; j++) {
				if(checkIfFits(item, i, j)) {
					placeItem(item, i, j);
					i = yCells;
					j = xCells;
				}
			}
		}
	}
	
	public boolean checkIfFits(ItemI item, int i, int j) {
		if(item.width + j > inventory[0].length || item.height + i > inventory.length) return false;
		for(int f = 0; f < item.height; f++) {
			for(int k = 0; k < item.width; k++) {
				if(inventory[i + f][j + k] != null) return false;
			}
		}
		return true;
	}
	
	public void placeItem(ItemI item, int i, int j) {
		item.xInventory = j;
		item.yInventory = i;
		for(int f = 0; f < item.height; f++) {
			for(int k = 0; k < item.width; k++) {
				inventory[i + f][j + k] = item;
			}
		}
		list.add(item);
	}
	
	public void removeItem(ItemI item, int i, int j) {
		int width = item.width;
		int height = item.height;
		for(int f = 0; f < height; f++) {
			for(int k = 0; k < width; k++) {
				inventory[i + f][j + k] = null;
			}
		}
		list.remove(item);
	}
	


}

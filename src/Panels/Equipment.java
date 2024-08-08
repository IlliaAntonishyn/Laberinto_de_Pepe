package Panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import A.Frame;
import Items.A.ItemI;

public class Equipment{
	
	public ItemI[] equipment;
	
	public final int width = 300;
	public final int height = 450;
	
	public final int cellWidth = 70;
	public final int cellHeight = 70;
	
	public Equipment() {
		equipment = new ItemI[5];
	}
	
}

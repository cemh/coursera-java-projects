package guimodule;

import processing.core.*;

public class mydisplay extends PApplet{
	
	PImage photo;
	
	
	
	// putting smiley face on screen.
	public void setup(){
	
		//set size.
		size(400,400);
		// set background color.
		//background(200,200,200);	
		photo= loadImage("after_cookie.jpg");
		
	}
	public void draw(){
		/*
		//fill color yellow.
		fill(255,255,0);
		// position x and y and size. 
		ellipse(200,200,390,390);
		fill(0,0,0);
		ellipse(120,130,50,70);
		fill(0,0,0);
		ellipse(280,130,50,70);
		
		//no fill color.
		noFill();
		//position , size , start angle, draw angle.
		arc(200,280,100,100,0,PI/2);
		*/
		image(photo, 0, 0);
	}
}

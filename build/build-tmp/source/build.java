import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class build extends PApplet {

ArrayList<String> imgNames;
ArrayList<PImage> imgs;
int imgIndex;
float noiseMapScale = 0.005f;
float lineWidthStart = 10;
float lineWidth;

public void setup() {
	lineWidth = lineWidthStart;
	frameRate(5000);
	rectMode(CENTER);
	imgIndex = -1;

	//initialize arraylists
	imgNames = new ArrayList<String>();
	imgs = new ArrayList<PImage>();



	imgNames.add("OmBul_jul.png");
	imgNames.add("OmBul_paaske.png");
	for (int i = 0; i < imgNames.size(); i++) {
		imgs.add(loadImage(imgNames.get(i)));
	}
	
	background(0);
	changeImage();
}

public void draw() {
	PImage img = imgs.get(imgIndex);

	int x = PApplet.parseInt(random(img.width));
	int y = PApplet.parseInt(random(img.height));

	float angle = TWO_PI*noise(noiseMapScale*x,noiseMapScale*y);


	int index = (y*img.width + x);
	stroke(img.pixels[index]);
	strokeWeight(lineWidth);

	pushMatrix();
	translate(
		x,
		y
		);

	rotate(angle);

	rect(0,0,20,0);

	popMatrix();
	if ((frameCount%1000 == 0) && lineWidth != 1) {
		lineWidth -= 1;
	}

}

public void changeImage() {
	lineWidth = lineWidthStart;
	imgIndex++;
	if (imgIndex >= imgNames.size()) {
		imgIndex = 0;
	}

	imgs.get(imgIndex).loadPixels();
}

public void mousePressed() {
	changeImage();
}
  public void settings() { 	size(800,600); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "build" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}

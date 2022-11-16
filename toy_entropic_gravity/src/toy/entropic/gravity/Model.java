package toy.entropic.gravity;
import processing.core.*;

public class Model extends PApplet{
	
	Simulation sim;
	boolean run = true;
	float offsetX, offsetY;
	public static void main(String[] args) {
		PApplet.main(new String[] {toy.entropic.gravity.Model.class.getName()});
	}
	
	// method used only for setting the size of the window
    public void settings(){
    	size((int)(displayWidth * 0.7), (int)(displayHeight * 0.7));
    }
    
    public void setup(){
        surface.setTitle("Toy Entropic Gravity");
        surface.setLocation(10, 10);
        strokeWeight(1);
        
        sim = new Simulation(height / 2 - 5, 1000, 2);
        offsetX = width - 5 - sim.getRadius();
        offsetY = height / 2;
    }
    
    public void draw(){
    	if(run) {
	    	sim.update();
	    	
	    	background(130);
	    	stroke(255);
	    	fill(0, 200, 0);
	    	rect(20, 20, 40, 40);
	    	fill(200, 0 ,0);
	    	rect(70, 20, 40, 40);
	    	
	    	fill(255);
	    	stroke(0);
	    	strokeWeight(1);
	    	circle(offsetX, offsetY, 2 * sim.getRadius());
	    	
	    	for(Chord c : sim.getChords()) {
	    		if(c.isExcluded()) {
	    			stroke(150);
	    		} else {
	    			stroke(0);
	    		}
	    		line(offsetX + c.getX1(), offsetY + c.getY1(), offsetX + c.getX2(), offsetY + c.getY2());
	    	}
	    	
	    	for(Particle p : sim.getParticles()) {
	    		noFill();
	    		strokeWeight(1);
	    		stroke(255,0,0);
	    		circle(offsetX + p.getX(), offsetY + p.getY(), p.getR() * 2);
	    	}
	    	
	    	noFill();
	    	stroke(0);
	    	strokeWeight(1);
	    	circle(offsetX, offsetY, 2 * sim.getRadius());
    	}
    }
    
    public void mousePressed() {
    	if(mouseX>20 && mouseX<60 && mouseY>20 && mouseY<60) {
    		run = true;
    	}
    	if(mouseX>70 && mouseX<110 && mouseY>20 && mouseY<60) {
    		run = false;
    	}
    }
}
package ch.zhaw.ads;

public class HilbertServer {
	double distance = 0.4;
	double angle = -90;
	Turtle turtle = new Turtle();
	
	public HilbertServer() {
		
	}
	
	public void execute(String step) {
		int steps = Integer.parseInt(step);
		hilbert(steps, distance, angle);
//		hilbert(steps, distance, angle);
//		hilbert(steps, distance, angle);
	}
	
	 private void hilbert(int depth, double dist, double angle) {
	        if (depth >= 0) {
	            turtle.turn(-angle);
	            hilbert(depth-1, dist, -angle);
	            turtle.move(dist);
	            turtle.turn(angle);
	            hilbert(depth-1, dist, angle);
	            turtle.move(dist);
	            hilbert(depth-1, dist, angle);
	            turtle.turn(angle);
	            turtle.move(dist);
	            hilbert(depth-1, dist, -angle);
	            turtle.turn(-angle);
	        }
	    }

}

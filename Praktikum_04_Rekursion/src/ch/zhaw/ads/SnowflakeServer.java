package ch.zhaw.ads;

public class SnowflakeServer {
	Turtle turtle;
	double distance = 0.7;

	public SnowflakeServer() {
		turtle = new Turtle();
	}

	public void execute(String step) {
		int steps = Integer.parseInt(step);
		snowflake(steps, distance);
		snowflake(steps, distance);
		snowflake(steps, distance);
	}

	void snowflake(int stufe, double dist) {
		if (stufe == 0) {
			turtle.move(dist);
		} else {
			stufe--;
			dist = dist / 3;
			snowflake(stufe, dist);
			turtle.turn(60);
			snowflake(stufe, dist);
			turtle.turn(-120);
			snowflake(stufe, dist);
			turtle.turn(60);
			snowflake(stufe, dist);
		}
	}

}

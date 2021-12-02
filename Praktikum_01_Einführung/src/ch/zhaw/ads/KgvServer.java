package ch.zhaw.ads;

public class KgvServer implements CommandExecutor {
	
	public String execute(String s) {
		String[] numbers = s.split("[ ,]+");
		int a = Integer.parseInt(numbers[0]);
		int b = Integer.parseInt(numbers[1]);
		return Integer.toString(kgv(a,b));
		}
	
	public int kgv(int a, int b) {
		int ab = Math.abs(a*b);
		return ab/ggT(a, b);
	}
	
	private int ggT(int a, int b) {
		while (b!= 0) {
			int c = a % b;
			a = Math.max(b, c);
			b = Math.min(b, c);
		}
		return a;
	}
}
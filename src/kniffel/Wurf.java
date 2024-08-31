package kniffel;

import java.util.Random;

public class Wurf {
	private static Random random = new Random();
	private int[] zahlen;
	final public static Wurf defaultWurf = new Wurf(new int[] {1,1,1,1,1});
	
	public Wurf(int[] zahlen) {
		this.zahlen = zahlen;
	}
	
	public int sumOfAll() {
		int sum = 0;
		for(int i : zahlen) {
			sum += i;
		}
		return sum;
	}
	
	public int get(int pos) {
		if(pos >= zahlen.length || pos < 0) {
			return 0;
		}
		else {
			return zahlen[pos];
		}
	}
	
	public int count(int num) {
		int count = 0;
		for(int i : zahlen) {
			if(i == num) {
				count++;
			}
		}
		return count;
	}
	
	public boolean includes(int num) {
		for(int i : zahlen) {
			if(i == num) {
				return true;
			}
		}
		return false;
	}
	
	public static Wurf wuerfeln() {
		int[] zahlen = new int[5];
		for(int i = 0; i < zahlen.length; i++) {
			zahlen[i] = random.nextInt(6) + 1;
		}
		return new Wurf(zahlen);
	}
	
	public void neuWuerfeln(boolean[] Wuerfel) {
		for(int i = 0; i < zahlen.length; i++) {
			if(Wuerfel[i]) {
				zahlen[i] = random.nextInt(6) + 1;
			}
		}
	}
}
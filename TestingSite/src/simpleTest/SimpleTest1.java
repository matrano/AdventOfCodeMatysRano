package simpleTest;

public class SimpleTest1 {

	public static void main(String[] args) {
		Integer i1 = 0b001;
		Integer i2 = 0b0;
		double numberAdd = Math.pow(2, Math.pow(3, 2) - 1);
		
		for (int idx = 0; idx < numberAdd; idx++) {
			i2 += i1;
		}
		
		System.out.println(Integer.toBinaryString(i2));

	}

}

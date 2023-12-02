package ECC;

import java.math.BigInteger;

class Point {
	
	public static final Point INFINITY_POINT = new Point(BigInteger.ZERO, BigInteger.ONE, BigInteger.ZERO);
	
	private BigInteger x;
	private BigInteger y;
	private BigInteger z;
	
	Point() {}
	
	Point(BigInteger x, BigInteger y, BigInteger z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	BigInteger GetX() {
		return x;
	}
	
	BigInteger GetY() {
		return y;
	}
	
	BigInteger GetZ() {
		return z;
	}
}

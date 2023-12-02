package ECC;

import java.math.BigInteger;

public class Program {
	public static void main(String[] args) {
		EllipticCurve ecc = new EllipticCurve(BigInteger.ZERO,
											  new BigInteger("7"),
											  new BigInteger("115792089237316195423570985008687907853269984665640564039457584007908834671663"));
		
		Point g = new Point(new BigInteger("55066263022277343669578718895168534326250603453777594175500187360389116729240"),
							new BigInteger("32670510020758816978083085130507043184471273380659243275938904335757337482424"),
							BigInteger.ONE);
		
		Point result = ecc.EllipticCurvePointMultiplication(g, 10);
		
		System.out.print("(" + result.GetX() + ", " + result.GetY() + ")");
	}
}

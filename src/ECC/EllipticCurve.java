package ECC;

import java.math.BigInteger;

class EllipticCurve {
	private BigInteger a;
	@SuppressWarnings("unused")
	private BigInteger b;
	private BigInteger p;
	
	public EllipticCurve(BigInteger a, BigInteger b, BigInteger p) {
		this.a = a;
        this.b = b;
        this.p = p;
	}
	
	Point EllipticCurvePointAddition(Point point1, Point point2) {
        if(point1 == null || point2 == null)
            return null;
        
        if(point1.GetZ() == BigInteger.ZERO)
            return point2;

        else if(point2.GetZ() == BigInteger.ZERO)
            return point1;
        
        BigInteger lambda = BigInteger.ZERO;
		BigInteger numerator = BigInteger.ZERO;
		BigInteger denominator = BigInteger.ZERO;

		if(point1.GetX() == point2.GetX()) {
        	BigInteger yAddition = (point1.GetY().add(point2.GetY())).mod(p);
        	
        	if(yAddition == BigInteger.ZERO)
            	return Point.INFINITY_POINT;
            
            numerator = point1.GetX().multiply(point1.GetX()).multiply(BigInteger.valueOf(3)).add(a);
            denominator = point1.GetY().add(point1.GetY());
        }
        
        else {
            numerator = point2.GetY().subtract(point1.GetY());
            denominator = point2.GetX().subtract(point1.GetX());
        }
        
        lambda = numerator.multiply(denominator.modInverse(p));
        BigInteger newXCoordinate = CalculateXCoordinate(lambda, point1, point2);
		BigInteger newYCoordinate = CalculateYCoordinate(lambda, point1, newXCoordinate);
        
        return new Point(newXCoordinate,
                    newYCoordinate,
                    BigInteger.ONE);
    }
	
	Point EllipticCurvePointMultiplication(Point p, int n) {
		Point result = Point.INFINITY_POINT;
		BigInteger bigIntegerN = BigInteger.valueOf(n);
		int indexStart = bigIntegerN.bitLength()-1;
		
		for(int i=indexStart; i>=0; --i) {
			result = EllipticCurvePointAddition(result, result);
			
			if(bigIntegerN.testBit(i))
				result = EllipticCurvePointAddition(result, p);
		}
		
		return result;
	}
	
	private BigInteger CalculateXCoordinate(BigInteger lambda, Point p1, Point p2) {
		return lambda.multiply(lambda).subtract(p1.GetX()).subtract(p2.GetX()).mod(p);
	}
	
	private BigInteger CalculateYCoordinate(BigInteger lambda, Point p1, BigInteger x) {
		return lambda.multiply(p1.GetX().subtract(x)).subtract(p1.GetY()).mod(p);
	}
}

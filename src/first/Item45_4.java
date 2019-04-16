package first;

import java.math.BigInteger;
import java.util.stream.Stream;
import static java.math.BigInteger.*;

/**
 * 스트림으로 메르센 소수 구하기
 */
public class Item45_4 {
	public static void main(String[] args) {
		primes().map(p -> TWO.pow(p.intValueExact()).subtract(ONE))
		.filter(mersenne -> mersenne.isProbablePrime(50))
		.limit(20)
		.forEach(mp -> System.out.println(mp.bitLength() + " : "+mp));
	}
	
	static Stream<BigInteger> primes() {
		return Stream.iterate(TWO, BigInteger::nextProbablePrime);
	}
}

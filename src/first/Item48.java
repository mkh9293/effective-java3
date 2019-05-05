package first;

import java.math.BigInteger;
import java.util.stream.LongStream;

public class Item48 {
	public static void main(String[] args) {
		long l = 10;
		
		long start = System.currentTimeMillis();
		System.out.println(pi(l));
		long end = System.currentTimeMillis();
		System.out.println((end - start)/1000.0);
	}
	
	public static long pi(long n) {
		return LongStream.rangeClosed(2, n)
						 .parallel()
						 .mapToObj(BigInteger::valueOf)
						 .filter(i -> i.isProbablePrime(50))
						 .count();
	}
}

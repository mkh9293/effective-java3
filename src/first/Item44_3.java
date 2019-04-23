package first;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.IntPredicate;
import java.util.function.LongBinaryOperator;
import java.util.function.LongFunction;
import java.util.function.ToLongFunction;

/**
 * 기본 함수형 인터페이스 (변형)
 */
public class Item44_3 {
	public static void main(String[] args) {
		
		// 기본 int형을 받는 IntPredicate (long, double 도 있음)
		IntPredicate i = (x) -> x < 0;
		System.out.println(i.test(123));
		
		// long을 받고 반환하는 LongBinaryOperator 
		LongBinaryOperator l = (x, y) -> x/y;
		System.out.println(l.applyAsLong(50, 10));
		
		// 유일하게 Function의 변형만 매개변수화됨. (long 을 받아 String으로 반환)   -> 반환타입을 매개변수화
		LongFunction<String> lf = (f) -> Long.toString(f);
		System.out.println(lf.apply(Long.MAX_VALUE));
		
		// 위와 다른점은 입력을 매개변수화.
		ToLongFunction<String> lf2 = (x) -> Long.parseLong(x);
		System.out.println(lf2.applyAsLong("2"));
		
		// 기본 함수형 인터페이스의 인수 개수 변형 
		// BiPredicate 인수를 2개 받아서 처리 (참고로 기존 Predicate는 1개임.)
		BiPredicate<Integer, Integer> b1 = (x, y) -> x > y;
		System.out.println(b1.test(2, 3));
		
		// BiFunction (String, Integer 타입의 인수를 받아서 String 타입의 결과 반환)
		BiFunction<String, Integer, String> bi2 = (x, y) -> x + " " +y;
		System.out.println(bi2.apply("hello World", 2));
		
		
	}
}

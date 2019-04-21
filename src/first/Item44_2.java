package first;

import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * 기본 함수형 인터페이스 적용 예시
 */
public class Item44_2 {
	public static void main(String[] args) {
		// UnaryOperator
		UnaryOperator<String> i = (x) -> x.toUpperCase();
		System.out.println(i.apply("testUnaryOperator"));
		
		// BinaryOperator
		BinaryOperator<Integer> adder = (n1, n2) -> n1 + n2;
		System.out.println(adder.apply(10, 20));
		
		BinaryOperator<Integer> bi1 = BinaryOperator.minBy(Comparator.reverseOrder());
		BinaryOperator<Integer> bi2 = BinaryOperator.maxBy(Comparator.reverseOrder());
		System.out.println(bi1.apply(2, 3));
		System.out.println(bi2.apply(2, 3));
		
		
		// Predicate
		Predicate<String> p = (s) -> s.length() > 2;
		System.out.println(p.test("test"));

		// Predicate and
		Predicate<String> p1 = (s) -> s.length() > 2;
		Predicate<String> p2 = (s) -> s.length() < 5;
		System.out.println(p1.and(p2).test("test123"));
		
		// Predicate negate (반대)
		System.out.println(p.negate().test("t"));
		
		// Predicate or 		
		System.out.println(p1.or(p2).test("test"));
		
		// Predicate isEqual
		Predicate<String> p3 = Predicate.isEqual("test");
		System.out.println(p3.test("test1"));
		
		
		// Function (Integer를 인수로 받고, String 타입으로 리턴)
		Function<Integer, String> f1 = (f) -> Integer.toString(f);
		System.out.println(f1.apply(3).length()); // apply(3)으로 3을 인수로 주고 Integer.toString(f)을 통해 String으로 반환된다. 
		
		System.out.println(calc((f2) -> "Result: "+(f2 * 2), 10));
		
		// Function compose
		Function<String, Integer> f3 = (f) -> Integer.parseInt(f);
		System.out.println(f1.compose(f3).apply("30").length()); // f3의 결과 적용 후 f1 적용. f3의 결과로 Integer 리턴 -> 그 결과인 Integer값을 f1에서 받아서 처리.
		
		// Function andThen
		System.out.println(f1.andThen(f3).apply(30).byteValue()); // compose의 반대. f1 -> f3
		
		// Function identity (인수로 들어온 결과를 그대로 반환)
		Function<Integer, Integer> f4 = Function.identity();
		Function<Integer, Integer> f5 = f -> f;
		System.out.println(f4.apply(4));
		System.out.println(f5.apply(5));
		
		// Supplier (인수를 받지 않고 값을 반환)
		Supplier<String> s = () -> "test";
		System.out.println(s.get());
		
		System.out.println(make(Employee::new));
	
		// Consumer (인수를 소비하는 함수)
		Consumer<String> c = (x) -> System.out.println(x);
		c.accept("test");
		
		Consumer<Integer> c1 = (x) -> {
			System.out.println("x : "+x);
		};
		c1.accept(10);
		
		// Consumer andThen 
		Consumer<String> c3 = (x) -> System.out.println(x.toLowerCase());
		c.andThen(c3).accept("TEST"); // c 적용 후 c3 적용
	}
	
	// Function
	static String calc(Function<Integer, String> bi, Integer i) {
		return bi.apply(i);
	}
	
	static Employee make(Supplier<Employee> e) {
		return e.get();
	}
	
}

//Supplier
class Employee {
	
	@Override
	public String toString() {
		return "Employee Test";
	}
}

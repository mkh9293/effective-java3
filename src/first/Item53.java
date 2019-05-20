package first;

import java.util.EnumSet;

/**
 * 가변인수는 신중히 사용.
 */
public class Item53 {
	public static void main(String[] args) {
//		System.out.println(sum()); // 0
//		System.out.println(sum(1, 2, 3)); // 6
//		System.out.println(min());
		System.out.println(min2(5,6)); // 5
	}
	
	
	// 가변인수 메서드는 호출 시마다 메서드를 생성, 초기화하기 때문에  성능최적화를 위해
	// 다음과 같이 메서드를 다중정의하여도 된다.
	public static void foo() {}
	public static void foo(int a1) {}
	public static void foo(int a1, int a2) {}
	public static void foo(int a1, int a2, int a3) {}
	public static void foo(int a1, int a2, int a3, int a4) {}
	public static void foo(int a1, int a2, int a3, int a4, int... rest) {}
	
	
	public static int min2(int firstArg, int...remaingArgs) {
		int min = firstArg;
		for(int arg : remaingArgs) {
			if(arg < min) min = arg;
		}
		return min;
	}                
	
	public static int min(int...args) {
		if(args.length == 0) {
			throw new IllegalArgumentException("인수가 1개 이상 필요함.");
		}
		int min = args[0];
		
		for(int i=1; i<=args.length; i++) {
			if(args[i] < min) min = args[i];
		}
		
		return min;
	}
	
	public static int sum(int... args) {
		int sum = 0;
		for(int arg : args) {
			sum += arg;
		}
		return sum;
	}
}

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
	// ( 참고로 매개변수는 될 수 있으면 5개 이하로 사용하는게 오류 발생률을 줄일 수 있다. )  
	public static void foo() {}
	public static void foo(int a1) {}
	public static void foo(int a1, int a2) {}
	public static void foo(int a1, int a2, int a3) {}
	public static void foo(int a1, int a2, int a3, int a4) {}
	public static void foo(int a1, int a2, int a3, int a4, int... rest) {}
	
	
	// 적절한 사용 예시.
	// 만약, min()함수와 같이 인수가 1개 이상 무조건 필요하다면, 매개변수 1개는 무조건 받도록 처리하여
	// 1. 예외 처리코드 제거(더 보기 좋은 코드)
	// 2. 컴파일 시점에 오류 체크 가능 
	public static int min2(int firstArg, int...remaingArgs) {
		int min = firstArg;
		for(int arg : remaingArgs) {
			if(arg < min) min = arg;
		}
		return min;
	}                
	
	// 좋지못한 사용 예시.
	// 매개변수의 개수가 1개 이상일 경우를 예외 처리하여 보기 안좋은 코드
	// 컴파일 시점이 아닌 런타임 시점에 이슈 발생.
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
	
	// 가변인수 사용 예시1. 
	// 인수를 0개 이상 받아서 처리할 수 있다.
	public static int sum(int... args) {
		int sum = 0;
		for(int arg : args) {
			sum += arg;
		}
		return sum;
	}
}

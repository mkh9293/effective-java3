package first;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Item39_2 {
	public static void main(String[] args) throws ClassNotFoundException {
		int tests = 0;
		int passed = 0;
		
		Class<?> testClass = Class.forName("first.Sample2");
		for(Method m : testClass.getDeclaredMethods()) {
			if(m.isAnnotationPresent(ExceptionTest.class)) {
				tests++;
				try {
					m.invoke(null);
					System.out.printf("테스트 %s 실패: 예외를 던지지 않음%n", m);
				} catch(InvocationTargetException wrappedEx) {
					Throwable exc = wrappedEx.getCause();
					Class<? extends Throwable> excType = m.getAnnotation(ExceptionTest.class).value();
					if(excType.isInstance(exc)) {
						passed++;
					} else {
						System.out.printf("테스트 %s 실패: 기대한 예외 %s, 발생한 예외: %s%n", m, excType.getName(), exc);
					}
					
				} catch(Exception exc) {
					System.out.printf("잘못 사용한 @ExceptionTest: "+m);
				}
				
			}
		}
		
		System.out.printf("성공: %d, 실패: %d%n", passed, tests-passed);
	}
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface ExceptionTest {
	Class<? extends Throwable> value();
}

class Sample2 {
	@ExceptionTest(ArithmeticException.class)
	public static void m1() {
		int i = 0;
		i = i / i;
	}
	
	@ExceptionTest(ArithmeticException.class)
	public static void m2() {
		int[] a = new int[0];
		int i = a[1];
	}
	
	@ExceptionTest(ArithmeticException.class)
	public static void m3() {}
}

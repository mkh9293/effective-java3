package first;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Item39_4 {
	public static void main(String[] args) throws ClassNotFoundException {
		int tests = 0;
		int passed = 0;
		
		Class<?> testClass = Class.forName("first.Sample4");
		Class<ExceptionTest3> e = ExceptionTest3.class;
		for(Method m : testClass.getDeclaredMethods()) {
			if(m.isAnnotationPresent(ExceptionTest2.class) || m.isAnnotationPresent(ExceptionContainer.class)) {
				tests++;
				try {
					m.invoke(null);
					System.out.printf("테스트 %s 실패: 예외를 던지지 않음.", m);
				} catch(Throwable wrappedExc) {
					Throwable exc = wrappedExc.getCause();
					int oldPassed = passed;
					ExceptionTest3[] excTests = m.getAnnotationsByType(ExceptionTest3.class);
					
					for(ExceptionTest3 excTest : excTests) {
						System.out.println(excTest.test());
						if(excTest.values().isInstance(exc)) {
							passed++;
							break;
						}
					}
				
					if(passed == oldPassed) {
						System.out.printf("테스트 %s 실패: %s %n",m, exc);
					}
				}
			}
		}
	}
}


class Sample4 {
	@ExceptionTest3(values=IndexOutOfBoundsException.class, test="123")
	@ExceptionTest3(values=NullPointerException.class, test="321")
	public static void doubleBad() {
		List<String> list = new ArrayList<>();
		list.addAll(5, null);
	}
}

// 2. 컨테이너 애너테이션의 class 객체를 @Reapeatable애너테이션의 속성값으로 전달필요
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(ExceptionContainer.class)
@interface ExceptionTest3{
	Class<? extends Throwable> values();
	String test();
}

// 1. Repeatable 어노테이션을 반환하는 컨테이너 애너테이션 정의
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface ExceptionContainer {
	ExceptionTest3[] value();
}


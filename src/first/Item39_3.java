package first;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Item39_3 {
	public static void main(String[] args) throws ClassNotFoundException {
		int tests = 0;
		int passed = 0;
		
		Class<?> testClass = Class.forName("first.Sample3");
		for(Method m : testClass.getDeclaredMethods()) {
			if(m.isAnnotationPresent(ExceptionTest2.class)) {
				tests++;
				try {
					m.invoke(null);
					System.out.printf("테스트 %s 실패: 예외를 던지지 않음.", m);
				} catch(Throwable wrappedExc) {
					Throwable exc = wrappedExc.getCause();
					int oldPassed = passed;
					Class<? extends Throwable>[] excTypes = m.getAnnotation(ExceptionTest2.class).value();
					for(Class<? extends Throwable> excType : excTypes) {
						if(excType.isInstance(exc)) {
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

class Sample3 {
	@ExceptionTest2({IndexOutOfBoundsException.class,
					 NullPointerException.class})
	public static void doubleBad() {
		List<String> list = new ArrayList<>();
		list.addAll(5, null);
	}
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface ExceptionTest2 {
	Class<? extends Throwable>[] value();
}
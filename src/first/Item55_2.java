package first;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;

/**
 * 
 * 옵셔널 반환은 신중히..
 */
public class Item55_2 {
	public static void main(String[] args) throws TemperTantrumException {
		// api를 사용하는 클라이언트 입장에서는 반드시 옵셔널 처리를 해야한다.
		// 그러므로, 클라이언트는 기존 null을 반환하는 코드에 비해
		// 예상못한 결과에 대처할 수 있다.
		
		List<String> words = new ArrayList<>(Arrays.asList());
		
		// 결과가 없을 경우 기본 값 지정.
		String lastWordInLexicon = max2(words).orElse("no word");
		System.out.println(lastWordInLexicon);
		
		// 값이 null일 경우, null! 문자열을 리턴함.
		System.out.println(Optional.ofNullable(null).orElse("null!"));
		
		// 원하는 예외를 던질 수 있음.
		Optional.ofNullable(null).orElseThrow(TemperTantrumException::new);
		
		// 만약 항상 값이 채워져 있다고 확신할 수 있는 경우. (null이면 NoSuchElementException 발생함)
		System.out.println(Optional.ofNullable(null).get());
		
		// 기본값 설정 비용을 낮출 수 있는 orElseGet() 메소드
		// 즉, of()의 값이 null일 경우만 해당 메소드가 실행되므로 기본값 비용을 낮출 수 있다는 뜻.
		// 기존 orElse()는 null이 아니어도 ifNull()메소드가 한 번은 실행됨.
		String o = Optional.of("is not null").orElseGet(()->ifNull());
		System.out.println("o : " + o);
	
	}
	
	public static String ifNull() {
		System.out.println("this method is not null");
		return "result";
	}
	
	public static <E extends Comparable<E>> Optional<E> max2(Collection<E> c) {
		return c.stream().max(Comparator.naturalOrder());
	}
	
	public static <E extends Comparable<E>> Optional<E> max(Collection<E> c) {
		if(c.isEmpty()) return Optional.empty();
		
		E result = null;
		for(E e : c) {
			if(result == null || e.compareTo(result) > 0) {
				result = Objects.requireNonNull(e);
			}
		}
		return Optional.of(result);
	}
}

class TemperTantrumException extends Exception {
	public TemperTantrumException() {
		super("TemperT Exception !");
	}
}
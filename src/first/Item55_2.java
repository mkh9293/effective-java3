package first;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 
 * 옵셔널 반환은 신중히..
 */
public class Item55_2 {
	public static void main(String[] args) {
		// api를 사용하는 클라이언트 입장에서는 반드시 옵셔널 처리를 해야한다.
		// 그러므로, 클라이언트는 기존 null을 반환하는 코드에 비해
		// 예상못한 결과에 대처할 수 있다.
		
		List<String> words = new ArrayList<>(Arrays.asList());
		
		// 결과가 없을 경우 기본 값 지정.
		String lastWordInLexicon = max(words).orElse("no word");
		System.out.println(lastWordInLexicon);
		
		//TODO continue...
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

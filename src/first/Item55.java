package first;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * 옵셔널 반환은 신중히..
 *
 */
public class Item55 {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		Optional op = max2(list);
		
		// 여러 객체들을 담을 수도 있다.
		Optional<HashMap<String, Object>> ops = Optional.empty();
		Map<String, Object> map = new HashMap<>();
		
		map.put("Session", new Session());
		map.put("User", new User());
		map.put("Errors", new Errors());
		ops.of(map);
	}
	
	public static <E extends Comparable<E>> Optional<E> max2(Collection<E> c) {
		if(c.isEmpty()) return Optional.empty();
		
		E result = null;
		for(E e : c) {
			if(result == null || e.compareTo(result) > 0) {
				result = Objects.requireNonNull(e);
			}
		}
		return Optional.of(result);
	}
	
	// 컬렉션에서 최댓값 구함.
	public static <E extends Comparable<E>> E max(Collection<E> c) {
		if(c.isEmpty()) throw new IllegalArgumentException("empty Collection");
		
		E result = null;
		for(E e : c) {
			if(result == null || e.compareTo(result) > 0) {  // result가 비어있거나 | e 값이 기존 result보다 클 경우,
				result = Objects.requireNonNull(e);
			}
		}
		return result;
	}
}

class Session {
}

class User {
}

class Errors {	
}

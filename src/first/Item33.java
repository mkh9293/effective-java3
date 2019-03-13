package first;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 
 * @item33 - 타입 안전 이종 컨테이너 고려 (type safe heterogeneous container pattern)  
 * @question - 1. asSubclass 실 사용 사례
 */
public class Item33 {
	public static void main(String[] args) {
		Favorites f = new Favorites();
		
		f.putFavorite(String.class, "Java");
		f.putFavorite(Integer.class, 0xcafebabe);
		f.putFavorite(Class.class, Favorites.class);
		
		String favoriteString = f.getFavorite(String.class);
		int favoriteInteger = f.getFavorite(Integer.class);
		Class<?> favoriteClass = f.getFavorite(Class.class);
		
		System.out.printf("%s %x %s%n", favoriteString, favoriteInteger, favoriteClass.getName());
		 
	}
}

// Set<E>, Map<K,V> 처럼 클래스가 매개변수화 되어 있으니 한번 타입을 지정하면 그 타입만 저장할 수 있다.
// Favorites는 클래스가 아닌 메소드를 매개변수화하여 해당 문제를 해결함. 
class Favorites {
	// Class<?>는 비한정적 와일드카드라 null외에는 오류가 나야 정상이지만,
	// 지금은 Map의 키로써 와일드카드가 중첩되어 사용되고 있다. (외에 "이는 모든 키가 서로 다른 매개변수화 타입일 수 있다"는 말은 고민)   
	private Map<Class<?>, Object> favorites = new HashMap<>(); 
	
	public <T> void putFavorite(Class<T> type, T instance) {
		favorites.put(Objects.requireNonNull(type), type.cast(instance)); // cast 메서드는 받아온 인수를 받아온 타입 그대로 반환해준다. (type safe)
	}
	
	public <T> T getFavorite(Class<T> type) {
		return type.cast(favorites.get(type));
	}
}

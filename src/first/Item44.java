package first;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiPredicate;

public class Item44 {
	public static void main(String[] args) {
		ExtendMap e = new ExtendMap((map, el) -> map.size() > 2);
		e.put("1", "test1");
		e.put("2", "test2");
		e.put("3", "test3");
		
		System.out.println(e.get("1"));
	}
}

/**
 *  맵의 size가 2 이상인 경우 가장 먼저 저장된 요소가 제거된다.
 */
class ExtendMap extends LinkedHashMap {
	/**
	 * 함수형 인터페이스 사용 전
	 */
	/*@Override
	protected boolean removeEldestEntry(Entry arg0) {
		return size() > 2;
	}*/
	
	
	/**
	 * 커스텀 함수형 인터페이스 사용 시
	 */
/*	private EldestEntryRemovalFunction rm;
	
	public ExtendMap(EldestEntryRemovalFunction rm) {
		this.rm = rm;
	}
	
	@Override
	protected boolean removeEldestEntry(Entry arg0) {
		return rm.remove(this, arg0);
	}*/
	
	
	/**
	 * 표준 함수형 인터페이스 사용
	 */
	private BiPredicate bi;
	public ExtendMap(BiPredicate<Map, Map.Entry> bi) {
		this.bi = bi;
	} 
	
	@Override
	protected boolean removeEldestEntry(Map.Entry arg0) {
		return bi.test(this, arg0);
	}
}

/**
 * 커스텀 함수형 인터페이스 사용 시
 */
//@FunctionalInterface
//interface EldestEntryRemovalFunction<K, V>{
//	boolean remove(Map<K,V> map, Map.Entry<K, V> eldest);
//}
package first;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * 다중정의는 신중히 사용.
 * 오버로딩은 컴파일 타임에 메소드 지정(정적),
 * 오버라이딩은 런타임 시 지정 (동적)
 * 
 * 오버로딩 시, 주의사항
 * 1. 매개변수가 같은 다중정의 피해라 (차라리 다른 이름의 메소드를 만들어라. ex: writeBoolean(boolean), writeInt(int), writeLong(long))
 * 2. 만약 매개변수가 같은 다중정의 메서드라도 타입이 완전 다르다면 혼란을 덜 준다 (오토박싱 안되는 타입) -> Object, Collection 등 오토박싱의 위험이 있는 타입은 피해라  
 * 3. 만약 다중정의하는 메소드의 기능이 똑같다면 다행이다.
 */
public class Item52 {
	public static void main(String[] args) {
		// 메소드 오버로딩은 컴파일 시에 타입이 지정되므로, collections의 collection<?> 타입의 메소드만 찾아간다. 
		Collection<?>[] collections = {
				new HashSet<String>(),
				new ArrayList<BigInteger>(),
				new HashMap<String, String>().values()
		};

		for(Collection<?> c : collections) { 
			System.out.println(CollectionClassifier.classify(c));
		}
		
		// removeSetList 메소드 실행
		SetList.removeSetList();
	}
}


// 메소드 다중정의 시, 오토박싱의 위험성을 보여줌.
class SetList {
	public static void removeSetList() {
		Set<Integer> set = new TreeSet<>();
		List<Integer> list = new ArrayList<>();
		
		for(int i = -3; i < 3; i++) {
			set.add(i);
			list.add(i);
		}
		
		for(int i = 0; i < 3; i++) {
			set.remove(i);
			
			// list.remove(i); 의 동작과는 완전히 다른 결과가 나온다.
			// remove(int) 는 index에 위치한 데이터를 제거하고
			// remove(Object) 는 데이터를 제거한다.
			list.remove((Object) i);  
		}
		
		System.out.println(set + " " + list);
	}
}

// 오버로딩은 컴파일 시에 메소드가 지정됨.
class CollectionClassifier {
	// 의도한 동작대로 되도록 수정 
	public static String classify(Collection<?> c) {
		return c instanceof Set ? "Set" : c instanceof List ? "List" : "etc";		
	}
	
	public static String classify(Set<?> s) {
		return "Set";
	}
	
	public static String classify(List<?> lst) {
		return "List";
	}
	
//	public static String classify(Collection<?> c) {
//		return "etc";
//	}
}

package first;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Item47 {
	public static void main(String[] args) {
		Set<Integer> set = new HashSet<>(Arrays.asList(10,1,2,5));
		Collection<Set<Integer>> c = PowerSet.of(set);
		c.forEach(System.out::println);
	}
}

class PowerSet {
	public static final <E> Collection<Set<E>> of(Set<E> s) {
		List<E> src = new ArrayList<>(s);
		if(src.size() > 30) {
			throw new IllegalArgumentException("too big size(maxium 30).: " + s);
		}
		
		return new AbstractList<Set<E>>() {
			@Override
			public int size() {
				return 1 << src.size(); // 1의 비트값을 src의 크기 만큼 왼쪽으로 옮김 -> src 개수의 2의 제곱값 결과를 구함.  
			}
			
			@Override
			public boolean contains(Object o) {
				return o instanceof Set && src.containsAll((Set) o);
			}
			
			@Override
			public Set<E> get(int index) {
				Set<E> result = new HashSet<>();
				for(int i=0; index != 0; i++, index >>= 1) { 
					if((index & 1) == 1) {
						result.add(src.get(i));
					}
				}
				return result;
			}
		};
	}
}

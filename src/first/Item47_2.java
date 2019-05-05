package first;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Item47_2 {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>(Arrays.asList(1,2,5));
		SubLists.of(list).forEach(System.out::println);		
	}
}

// 멱집합 구하기 
class SubLists {
	
	// of 메소드 하나로 멱집합 구현
	public static <E> Stream<List<E>> of(List<E> list) {
		return IntStream.range(0, list.size())
						.mapToObj(start -> IntStream.rangeClosed(start+1, list.size())
													.mapToObj(end -> list.subList(start, end)))
						.flatMap(x -> x);
	}
	
	// prefixes, suffixes 메소드로 구분하여 멱집합 구현ㅌ
//	public static <E> Stream<List<E>> of(List<E> list) {
//		return Stream.concat(Stream.of(Collections.emptyList()),
//							 prefixes(list).flatMap(SubLists::suffixes));
//	}
	
	private static <E> Stream<List<E>> prefixes(List<E> list) {
		return IntStream.rangeClosed(1, list.size()).mapToObj(end -> list.subList(0, end));
	}
	
	private static <E> Stream<List<E>> suffixes(List<E> list) {
		return IntStream.range(0, list.size()).mapToObj(start -> list.subList(start, list.size()));
	}
}

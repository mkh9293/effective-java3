package first;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Item46 {
	public static void main(String[] args) {
		String str = "main main args main args string string args main";
		
		// 단어별 빈도 수 구하기
		Map<String, Long> map;
		try(Stream<String> words = Stream.of(str.split(" "))){
			map = words.collect(groupingBy(String::toLowerCase, counting()));
		}
		
		// 빈도수가 가장 높은 순서대로 2개 뽑기
		List<String> list = map.keySet().stream().sorted(Comparator.comparing(map::get).reversed()).limit(2).collect(toList());
		list.forEach(System.out::println);
		
		
		
		
	}
}


package first;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Item46_2 {
	public static void main(String[] args) {
		String str = "main main args main args string string args main";
//		Stream<String> words = Stream.of(str.split(" "));
		
		// groupingBy 메소드로 map을 생성하는 첫번째 방식
		Map<String, List<String>> map = Stream.of(str.split(" ")).collect(Collectors.groupingBy(word -> alphabetize(word)));
		System.out.println(map.keySet());
		System.out.println(map.values());
		
		System.out.println();
		
		// groupingBy 메소드로 map을 생성하는 두번째 방식
		Map<String, Long> freq = Stream.of(str.split(" ")).collect(Collectors.groupingBy(word->word, Collectors.counting()));
		System.out.println(freq.keySet());
		System.out.println(freq.values());
		
		
	}
	
	static String alphabetize(String str) {
		char[] arr = str.toCharArray();
		Arrays.sort(arr);
		return new String(arr);
	}
}

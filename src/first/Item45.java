package first;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * 스트림 사용 예시1.
 * 입력받은 minGroupSize 보다 사이즈가 큰 아나그램 집합의 값들을 출력하는 예제
 */
public class Item45 {
	public static void main(String[] args) throws IOException{
		// args[0] = "staple stapel stplea staples"
		// args[1] = 2
		String dics = args[0];
		int minGroupSize = Integer.parseInt(args[1]);
		
		Map<String, Set<String>> groups = new HashMap<>();
		try(Scanner s = new Scanner(dics)){
			while(s.hasNext()) {
				String word = s.next();
				
				// computeIfAbsent 메서드는 키값이 존재할 경우, 키에 해당하는 값을 반환하지만
				// 키가 없을 경우, 두번째 인수에 적용된 코드(함수 객체)를 계산하여 키와 매핑한 후에 반환한다. (즉, 키가 존재할 경우 두번째 인수 실행 x) 
				groups.computeIfAbsent(alphabetize(word), (un)->new TreeSet<>()).add(word);
			}
		}
		
		for(Set<String> group : groups.values()) {
			if(group.size() >= minGroupSize) System.out.println(group.size() + " : " + group);
		}
	}
	
	static String alphabetize(String s) {
		char[] a = s.toCharArray();
		Arrays.sort(a);
		return new String(a);
	}
}

package first;

import java.util.EnumSet;

/**
 * 
 * EnumSet 기본 사용법. (AbstractSet을 상속받아 관련 메소드 사용 가능) 
 * EnumSet은 AbstractSet을 상속받았으나, 내부 구현은 long 타입의 비트필드를 사용한다. (long타입의 값을 비트연산한 비트필드) 
 * 비트연산은 단순 계산이므로 매우 빠른 속도와 적은 메모리를 사용한다.  
 * 
 */
public class Item53_3 {
	public static void main(String[] args) {
		// Enum 전체를 담는다. 
		EnumSet e1 = EnumSet.allOf(STUDY.class);
		System.out.println(e1); 		  						 // 전체 출력 [SQL, JAVA, TESTCODE, SPRING, PATTERN]
		System.out.println(e1.range(STUDY.JAVA, STUDY.SPRING));	 // JAVA와 SPRING 사이의 enum 출력 [JAVA, TESTCODE, SPRING]
	
		// 복사. clone
		EnumSet e2 = EnumSet.copyOf(e1);
		System.out.println(e2);  // [SQL, JAVA, TESTCODE, SPRING, PATTERN]
		
		// Study 타입의 새로운 EnumSet 생성 
		e2 = EnumSet.noneOf(STUDY.class);
		System.out.println(e2);  // []
		
		// 특정 enum 값으로 초기화 
		EnumSet e3 = EnumSet.of(STUDY.JAVA, STUDY.TESTCODE);
		System.out.println(e3); // [JAVA, TESTCODE]
		
		// e3에 있는 값은 제외한 EnumSet으로 초기화
		e2 = EnumSet.complementOf(e3);
		System.out.println(e2); // [SQL, SPRING, PATTERN]
	}
	
	enum STUDY {
		SQL, JAVA, TESTCODE, SPRING, PATTERN 
	}
}



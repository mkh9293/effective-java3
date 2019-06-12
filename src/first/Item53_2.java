package first;

import java.lang.reflect.Field;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

/**
 * EnumSet은 내부적으로 RegularEnumSet, JumboEnumSet을 통해 생성되는데,
 * RegularEnumSet, JumboEnumSet은 long 타입 필드인 elements 를 통해 비트연산으로 메서드들을 구현했다.
 * 즉, 비트필드와 비견된 속도를 낼 수 있다.
 * 
 * 아래 코드는 EnumSet의 값을 long 타입으로 변환한 결과를 보여준다. (참조: https://coderanch.com/t/492329/java/Enum-Long)
 */
public class Item53_2 {
    public static void main(String a[]) {
    	// 참고. 비트필드 -> 비트 연산을 사용한 필드. 단순계산이므로 시간복잡도는 O(1)
    	int STYLE_BOARD = 1 << 1; 
    	System.out.println(STYLE_BOARD);
    	
    	// EnumSet은 Enum들을 Set할 때 사용.
    	EnumSet<TestEnum> ee = EnumSet.of(TestEnum.A, TestEnum.B);
    	
        EnumSet<TestEnum> t = EnumSet.noneOf(TestEnum.class); // empty enumSet
        System.out.println("Empty enum : " + getBinaryVector(t));
 
        for(TestEnum tt: TestEnum.values() ){
            t.clear();
            t.add(tt);
            System.out.println("Enum = " + tt.name() + " : " + getBinaryVector(t));
        }
        t.clear();
        for(TestEnum tt: TestEnum.values() ){
            t.add(tt);
            System.out.println("Enums : " + getBinaryVector(t));
        }
       
    }
    static public String getBinaryVector(EnumSet e) {
        Class<?> c = e.getClass();
        try {
            Field elements = c.getDeclaredField("elements");
            elements.setAccessible(true);
            return Long.toBinaryString(elements.getLong(e));
        } catch (NoSuchFieldException ex) {
            throw new RuntimeException("Error while accessing bitVector", ex);
        } catch (SecurityException ex) {
            throw new RuntimeException("Error while accessing bitVector", ex);
        } catch (IllegalAccessException ex) {
            throw new RuntimeException("Error while accessing bitVector", ex);
        }
    }
}

enum TestEnum {
    A, B, C, D, E, F, G, H
};
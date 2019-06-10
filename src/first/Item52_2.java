package first;

import java.util.List;

/**
 * 오버라이드는 동적으로 메소드를 찾아서 호출.
 */
public class Item52_2 {
	public static void main(String[] args) {
		// wineList에 각 Wine, SpartWine, Champagne 객체를 할당했다. (하위 클래스의 객체 자체를 할당)
		List<Wine> wineList = List.of(new Wine(), new SparkWine(), new Champagne());
		
		// 각 wine은 하위 클래스 객체 자체를 할당받았고
		// for문 내부에 wine.name()은 하위 클래스에서 호출된 메소드 이므로
		// 당연히 각 클래스의 메소드가 호출된다.
		for(Wine wine : wineList) {
			System.out.println(wine.name());
		}
	}
}

class Wine {
	String name() { return "포도주";	}
}

class SparkWine extends Wine {
	@Override
	String name() { return "발효성 포도주";	}
}

class Champagne extends SparkWine {
	@Override
	String name() { return "샴페인";	}
}

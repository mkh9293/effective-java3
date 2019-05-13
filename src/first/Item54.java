package first;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * null이 아닌, 빈 컬렉션이나 배열을 반환하라
 */
public class Item54 {
	public static void main(String[] args) {
		Shop shop = new Shop();
		List<Cheese> cheeses = shop.getCheeses_bad();
		if(cheeses != null && cheeses.contains(Cheese.STILTON));
	}
}

enum Cheese {
	STILTON;
}

class Shop {
	private final List<Cheese> cheeseInStock = null;

	private static final Cheese[] EMPTY_CHEESE_ARRAY = new Cheese[0];
	
	/**
	 * 배열 예시
	 */
	public Cheese[] getCheese_array() {
		return cheeseInStock.toArray(new Cheese[0]);
	}
	
	/**
	 * 배열 최적화 예시
	 */
	public Cheese[] getCheese_array_nice() {
		return cheeseInStock.toArray(EMPTY_CHEESE_ARRAY);
	}
	
	/**
	 * 배열 나쁜 예
	 */
	public Cheese[] getCheese_array_bad() {
		return cheeseInStock.toArray(new Cheese[cheeseInStock.size()]);
	}
	
	
	/**
	 * 최적화 코드
	 */
	public List<Cheese> getCheeses_nice() {
		return cheeseInStock.isEmpty() ? Collections.emptyList() : new ArrayList<>(cheeseInStock);
	}
	
	/**
	 * 올바른 예
	 */
	public List<Cheese> getCheeses_good() {
		return new ArrayList<>(cheeseInStock);
	}
	
	/**
	 * 나쁜 예
	 */
	public List<Cheese> getCheeses_bad() {
		return cheeseInStock.isEmpty() ? null : new ArrayList<>();
	}

}

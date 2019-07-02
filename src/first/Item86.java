package first;

public class Item86 {
	public static void main(String[] args) {
		// 강조. Serializable을 사용여부는 신중해야 함.
		
		// 1. Serializable을 구현하면 릴리즈한 뒤에 수정이 어렵다.
		// 2. Serializable 구현은 버그와 보안 구멍이 생길 위험이 높다.
		// 3. Serializable 을 구현하면 신 버전을 릴리즈할 때, 테스트가 늘어난다. 
		// 4. 상속용으로 설계된 클래스는 대부분 Serializable을 구현하면 안되며, 인터페이스도 마찬가지로 확장하면 안된다.
		// 5. 내부 클래스는 직렬화를 구현하면 안된다.
		
		// 정리
		// 클래스가 보호된 환경에서만 쓰이지 않는다면 Serializable 구현은 아주 신중해야한다.
	}
}

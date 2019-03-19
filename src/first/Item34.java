package first;

/**
 * 
 * @item34 - 열거타입 사용
 *
 */
public class Item34 {
	public static void main(String[] args) {
		double earthWeight = Double.parseDouble("30");
		double mass = earthWeight / Planet.EARTH.surfaceGravity();
		for (Planet p : Planet.values()) {
			System.out.printf("%s에서의 무게는 %f이다.%n", p, p.surfaceWeight(mass));
		}
	}
}

// 열거타입은 실제로는 클래스이기 때문에 기존 클래스와 똑같이 사용 가능하다. 
enum Planet {
	MERCURY(3.302e+23, 2.439e6),
	VENUS(4.869E+24, 6.052E6),
	EARTH(5.975e+24, 6.378e6);
	
	// 열거타입은 근본적으로 불변이라 모든 필드는 final로 두고 public 메서드로 접근하도록 하는게 좋다.
	private final double mass;
	private final double radius;
	private final double surfaceGravity;
	private static final double G = 6.67300e-11;
	
	Planet(double mass, double radius) {
		this.mass = mass;
		this.radius = radius;
		surfaceGravity = G * mass / (radius * radius);
	}
	
	public double mass() { return mass; }
	public double radius() { return radius; }
	public double surfaceGravity() { return surfaceGravity; }
	
	public double surfaceWeight(double mass) { return mass * surfaceGravity; }
	
}

// 밖에서 접근하는 생성자가 있을 수 없으니 final, 상수 하나당 인스턴스 하나만 생성되어 static final
enum Apple { FUJI, PIPPIN, GRANNY_SMITH }
enum Orange { NAVEL, TEMPLE, BLOOD }


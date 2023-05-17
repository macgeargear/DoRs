package config;

public enum Color {
	Base("#D9D9D9"),
	Grass("#A4D778"),
	Rock("#0E8468"),
	EMPTY("#ABB2B9"),
	P1("#E67E22"),
	P2("#E74C3C"),
	P3("#8E44AD"),
	P4("#3498DB");
	
	private String color;
	Color(String color) {
		this.color = color;
	}
}

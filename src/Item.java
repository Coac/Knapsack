
public class Item {
	private int weight;
	private int value;
	
	public Item(int value, int weight) {
		this.value = value;
		this.weight = weight;
	}
	
	
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return this.value + ";" + this.weight;
	}
}

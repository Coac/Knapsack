package knapsack;

/**
 * Item defined by a weight and a qualitative value.
 */
public class Item {
    private int weight;
    private int value;

    /**
     * Create a new item.
     *
     * @param value qualitative value
     * @param weight item weight
     */
    public Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }

    /**
     * Get the item weight.
     *
     * @return the item weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Get the item value.
     *
     * @return the item value
     */
    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.value + ";" + this.weight;
    }
}

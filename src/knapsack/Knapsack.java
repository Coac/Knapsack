package knapsack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Knapsack example using branch & bound algorithm.
 *
 * @author Quentin de Longraye <quentin@dldl.fr>
 * @author Victor Le <m.victor.le@gmail.com>
 */
public class Knapsack {
    private List<Item> items;
    private final List<Item> bag;
    private static final int CAPACITY = 16;

    private int maxValue = 0;
    private int currentWeight = 0;
    private int currentValue = 0;

    private List<Item> bestBag;

    /**
     * Create a new Knapsack.
     *
     * @param n number of different available items
     */
    public Knapsack(int n) {
        this.bag = new ArrayList<>();
        this.bestBag = new ArrayList<>();

        this.initItems(n);
    }

    /**
     * Init a list of n different items.
     *
     * @param n number of different items
     */
    public void initItems(int n) {
        this.items = new ArrayList<>();

        for (int i = 0; i < n; ++i) {
            int weight = new Random().nextInt(10);
            int value = new Random().nextInt(10);
            this.items.add(new Item(value, weight));
        }
    }

    /**
     * Add an item to the bag.
     *
     * @param item item to be added
     */
    public void addItemInBag(Item item) {
        this.currentWeight += item.getWeight();
        this.currentValue += item.getValue();
        this.bag.add(item);

        if (this.currentValue > this.maxValue) {
            this.maxValue = this.currentValue;
            System.out.print(this.maxValue + " " + this.currentWeight);
            System.out.println(this.bestBag);

            this.bestBag = new ArrayList<>(this.bag);
        }
    }

    /**
     * Remove an item form the bag.
     *
     * @param item item to be removed
     */
    public void removeItemInBag(Item item) {
        this.currentWeight -= item.getWeight();
        this.currentValue -= item.getValue();
        this.bag.remove(item);
    }

    public boolean canEnterInBag(Item item) {
        return item.getWeight() + this.currentWeight <= Knapsack.CAPACITY;
    }

    /**
     * Find the best item combination using branch & bound.
     *
     * @param index current item index
     */
    public void solve(int index) {
        if (index >= this.items.size()) {
            return;
        }

        Item item = this.items.get(index);

        if (this.canEnterInBag(item)) {
            this.addItemInBag(item);
            this.solve(index + 1);
            this.removeItemInBag(item);
        }

        this.solve(index + 1);
    }

    public static void main(String[] args) {
        Knapsack knapsack = new Knapsack(100);
        long time = System.nanoTime();

        knapsack.solve(0);

        System.out.println("Time : " + (System.nanoTime() - time) / 1000000000.0 + " seconds");
    }
}

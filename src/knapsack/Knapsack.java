package knapsack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Knapsack {

    private List<Item> items;

    private final List<Item> bag;
    private final int capacity = 16;

    private int maxValue = 0;

    private int currentWeight = 0;
    private int currentValue = 0;

    private List<Item> bestBag;


    public Knapsack(int n) {
        this.bag = new ArrayList<>();
        this.bestBag = new ArrayList<>();

        this.initItems(n);
    }

    public void initItems(int n) {
        this.items = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int weight = new Random().nextInt(10);
            int value = new Random().nextInt(10);
            this.items.add(new Item(value, weight));
        }
        /*
		// ------------- Exemple du professeur -----
		this.items = new ArrayList<>();
		this.items.add(new knapsack.Item(45, 3));
		this.items.add(new knapsack.Item(30, 5));
		this.items.add(new knapsack.Item(45, 9));
		this.items.add(new knapsack.Item(10, 5));
		// ------------- -----------------------------
*/

    }


    public void addItemInBag(Item item) {
        this.currentWeight += item.getWeight();
        this.currentValue += item.getValue();
        this.bag.add(item);

        if (this.currentValue > this.maxValue) {
            this.maxValue = this.currentValue;
            System.out.print(this.maxValue + " ");
            System.out.print(this.currentWeight);


            System.out.println(this.bestBag);
            this.bestBag = new ArrayList<>(this.bag); // clone
        }
    }

    public void removeItemInBag(Item item) {
        this.currentWeight -= item.getWeight();
        this.currentValue -= item.getValue();
        this.bag.remove(item);
    }

    public boolean canEnterInBag(Item item) {
        return item.getWeight() + this.currentWeight <= this.capacity;
    }

    public void solve(int index) {
        if (index == this.items.size()) {
            return;
        }

        Item item = this.items.get(index);

        if (this.canEnterInBag(item)) {
            this.addItemInBag(item);
            solve(index + 1);
            this.removeItemInBag(item);
        }

        solve(index + 1);
    }

    public static void main(String[] args) {
        Knapsack sack = new Knapsack(100);

        long time = System.nanoTime();

        sack.solve(0);

        System.out.println("Time : " + (System.nanoTime() - time) / 1000000000.0 + " seconds");


    }

}

package Algorithm_week10;

public class Item {
    private int itemNum;
    private int value;
    private int weight;

    public Item(int n, int v, int w) {
        itemNum = n;
        value = v;
        weight = w;
    }

    public int getItemNum() {
        return itemNum;
    }

    public int getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }
}

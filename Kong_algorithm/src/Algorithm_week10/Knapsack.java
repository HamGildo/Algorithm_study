package Algorithm_week10;

public class Knapsack {
    private int[][] OPT;

    public Knapsack(int n, int w) {
        OPT = new int[n+1][w+1];
        for(int i = 0; i < w+1; i++) {
            OPT[0][i] = 0;
        }
    }

    public int[][] optArray(Item[] items, int weight){
        for(int i = 1; i < items.length+1; i++) {
            for(int w = 1; w < weight+1; w++) {
                int temp1 = OPT[i-1][w];
                int temp2 = items[i].getValue() + OPT[i-1][w-(items[i].getWeight())];
                if (items[i].getWeight() > w) {
                   OPT[i][w] = OPT[i-1][w];
                }
                else if(temp1 > temp2){
                    OPT[i][w] = temp1;
                }
                else {
                    OPT[i][w] = temp2;
                }
            }
        }

        return OPT;
    }







}

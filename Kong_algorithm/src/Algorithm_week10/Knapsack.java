package Algorithm_week10;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Knapsack {
    private int[][] OPT;
    private int[][] opt_item;
    //private int[] opt_item;

    public Knapsack(int size, int w) {
        OPT = new int[size][w+1];
        opt_item = new int[size][w+1];
        for(int i = 0; i < w+1; i++) {
            OPT[0][i] = 0;
        }
    }

    public int[][] optArray(ArrayList<Item> items, int weight){ //OPT값을 채우는 함수
        for(int i = 1; i < items.size(); i++) {
            for(int w = 1; w < weight+1; w++) {
                if (items.get(i).getWeight() > w) {
                    OPT[i][w] = OPT[i-1][w];
                    opt_item[i][w] = 0;
                }
                else {
                    int temp1 = OPT[i - 1][w];
                    int temp2 = items.get(i).getValue() + OPT[i - 1][w - (items.get(i).getWeight())];
                    if (temp1 > temp2) {
                        OPT[i][w] = temp1;
                        opt_item[i][w] = 0;
                    } else {
                        OPT[i][w] = temp2;
                        opt_item[i][w] = 1;
                    }
                }
            }
        }
        return OPT;
    }

    private void findItem(){

    }

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.print("가방의 크기를 입력하세요(0-50) : ");
        String size = input.next();
        int n = Integer.parseInt(size);
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(0,0,0));

        //파일입력
        File file = new File("C:\\Users\\ham54\\Desktop\\CSE\\tests\\data10_knapsack.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String read; // 버퍼에서 읽어들인 한 줄을 저장할 장소
        while((read = bufferedReader.readLine()) != null) {
            String[] arr_d = read.split(",");
            Item I = new Item(Integer.parseInt(arr_d[0]), Integer.parseInt(arr_d[1]), Integer.parseInt(arr_d[2]));
            items.add(I);
        }

        Knapsack test = new Knapsack(items.size(), n);
        test.optArray(items, n);

    }







}

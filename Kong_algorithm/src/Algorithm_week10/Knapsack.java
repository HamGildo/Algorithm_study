package Algorithm_week10;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Knapsack {
    private int[][] OPT;
    Stack<Integer> opt_items;

    public Knapsack(int size, int w) { //생성자
        OPT = new int[size][w+1];
        for(int i = 0; i < w+1; i++) {
            OPT[0][i] = 0;
        }
    }

    private int[][] optArray(ArrayList<Item> items, int weight){ //OPT값을 채우는 함수
        for(int i = 1; i < items.size(); i++) {
            for(int w = 1; w < weight+1; w++) {
                if (items.get(i).getWeight() > w) {
                    OPT[i][w] = OPT[i-1][w];
                }
                else {
                    int temp1 = OPT[i - 1][w];
                    int temp2 = items.get(i).getValue() + OPT[i - 1][w - (items.get(i).getWeight())];
                    if (temp1 > temp2) {
                        OPT[i][w] = temp1;
                    } else {
                        OPT[i][w] = temp2;
                    }
                }
            }
        }
        findItem(items, weight);
        return OPT;
    }

    private void findItem(ArrayList<Item> items, int weight){ // 가방에 들어있는 아이템을 추적하는 함수
        opt_items = new Stack<>();
        int cmpNum, currentNum;
        int i = OPT.length-1;
        int wi = weight;
        while(true) {
            cmpNum = OPT[i-1][wi];
            currentNum = OPT[i][wi];
            if(cmpNum == 0) break;
            else if(currentNum == cmpNum) {
                i--;
            }
            else {
                opt_items.push(i);
                wi -= items.get(i).getWeight();
                i--;
            }
        }

    }

    public void resultPrint(ArrayList<Item> items, int weight) {
        int[][] optPrint;
        optPrint = optArray(items, weight);
        findItem(items, weight);
        for(int i = 0; i < items.size(); i++) {
            for(int j = 0; j < weight+1; j++) {
                System.out.print(optPrint[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("max : "+ optPrint[items.size()-1][weight]);
        System.out.print("items : ");
        while(!opt_items.empty()) {
            System.out.print(opt_items.pop() + " ");
        }
        System.out.println();
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
        test.resultPrint(items, n);

    }







}

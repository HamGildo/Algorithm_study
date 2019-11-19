package Algorithm_week11;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Bellman_Ford {
//201702087_함지희
    private boolean bellman_ford(double[] graph, ArrayList<Edge> edges, int s) {
        init_single_source(graph, s);
        boolean isNegative_cycle = false;
        for(int i = 0; i < graph.length; i++) { // 정점의 수 -1 만큼 반복 + 음수 사이클 확인을 위해 한번 더 반복 = 결국 정점의 수만큼 반복
            System.out.printf("===============iteration %d================\n",i);
            for(int j = 0; j < edges.size(); j++) { // 모든 간선을 확인
                Edge edge_j = edges.get(j);
                double current_d = graph[edge_j.getV2()];
                double temp_d = graph[edge_j.getV1()] + edge_j.getW();
                if(current_d > temp_d) {
                    if(i == graph.length-1) { // 마지막 반복에서 업데이트가 일어나면 음수 사이클
                        isNegative_cycle = true;
                    }

                    graph[edge_j.getV2()] = temp_d;
                    if(Double.isInfinite(current_d)) {
                        System.out.printf("Update distance of %d from Inf to %d\n", edge_j.getV2(), (int)temp_d);
                    }
                    else {
                        System.out.printf("Update distance of %d from %d to %d\n", edge_j.getV2(), (int) current_d, (int) temp_d);
                    }

                }
            }
            System.out.printf("iteration %d distance : [", i); // 현재 그래프 출력
            for(int k = 0; k < graph.length; k++){
                if(k == graph.length-1) {
                    System.out.print((int)graph[k]);
                    break;
                }
                System.out.print((int)graph[k]+", ");
            }
            System.out.println("]");
            System.out.println();
        }

        if(isNegative_cycle) return false;  // 음수 사이클 false 반환
        else return true;//음수 사이클이 아니면 true

    }

    private void init_single_source(double[] graph, int s) { // 시작정점을 제외하고 무한대로 설정
        for(int i = 0; i < graph.length; i++) {
            if(i == s) graph[i] = 0;
            else graph[i] = Double.POSITIVE_INFINITY;
        }
    }

    public void resultPrint(double[] graph, ArrayList<Edge> edges, int s) {
        if(bellman_ford(graph, edges, s)) { //음수 사이클 x
            System.out.print("Final distance [");
            for(int i = 0; i < graph.length; i++) {
                if(i == graph.length-1) {
                    System.out.print((int)graph[i]);
                    break;
                }
                System.out.print((int)graph[i]+", ");
            }
            System.out.println("]");
        }
        else { //음수 사이클 o
            System.out.println("The graph has negative cycle.");
        }
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Edge> edges1 = new ArrayList<>();
        ArrayList<Edge> edges2 = new ArrayList<>();
        double[] graph1;
        double[] graph2;

        //파일입력
        File file1 = new File("C:\\Users\\ham54\\Desktop\\CSE\\tests\\data11_bellman_ford_1.txt");
        File file2 = new File("C:\\Users\\ham54\\Desktop\\CSE\\tests\\data11_bellman_ford_2.txt");
        FileReader fileReader1 = new FileReader(file1);
        FileReader fileReader2 = new FileReader(file2);
        BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
        BufferedReader bufferedReader2 = new BufferedReader(fileReader2);
        String read; // 버퍼에서 읽어들인 한 줄을 저장할 장소
        String read2;
        //graph1 입력
        read = bufferedReader1.readLine(); // graph를 만들기위해 첫줄부터 읽음
        String[] arr_d = read.split(",");
        graph1 = new double[arr_d.length];
        while((read = bufferedReader1.readLine()) != null) {
            arr_d = read.split(",");
            Edge I = new Edge(Integer.parseInt(arr_d[0]), Integer.parseInt(arr_d[1]), Integer.parseInt(arr_d[2]));
            edges1.add(I);
        }
        //graph2 입력
        read2 = bufferedReader2.readLine(); // graph를 만들기위해 첫줄부터 읽음
        String[] arr_d2 = read2.split(",");
        graph2 = new double[arr_d2.length];
        while((read2 = bufferedReader2.readLine()) != null) {
            arr_d2 = read2.split(",");
            Edge I = new Edge(Integer.parseInt(arr_d2[0]), Integer.parseInt(arr_d2[1]), Integer.parseInt(arr_d2[2]));
            edges2.add(I);
        }

        Bellman_Ford test = new Bellman_Ford();
        // 둘 다 시작정점을 0으로 해주었다.
        test.resultPrint(graph1, edges1, 0);
        System.out.println();
        test.resultPrint(graph2, edges2, 0);
    }


}

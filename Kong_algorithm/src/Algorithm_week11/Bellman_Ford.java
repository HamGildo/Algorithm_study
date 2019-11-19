package Algorithm_week11;

import java.util.ArrayList;
import java.util.Arrays;

public class Bellman_Ford {

    public boolean bellman_ford(double[] graph, ArrayList<Edge> edges, int s) {
        init_single_source(graph, s);
        for(int i = 0; i < graph.length-1; i++) { // 정점의 수 -1 만큼 반복
            System.out.printf("===============iteration %d================\n",i);
            for(int j = 0; j < edges.size(); j++) { // 모든 간선을 확인
                Edge edge_j = edges.get(j);
                double current_d = graph[edge_j.getV2()];
                double temp_d = graph[edge_j.getV1()] + edge_j.getW();
                if(current_d > temp_d) {
                    graph[edge_j.getV2()] = temp_d;
                    if(Double.isInfinite(current_d)) {
                        System.out.printf("Update distance of %d from Inf to %d\n", edge_j.getV2(), (int)temp_d);
                    }
                    System.out.printf("Update distance of %d from %d to %d\n", edge_j.getV2(), (int)current_d, (int)temp_d);
                }
            }
            System.out.printf("iteration %d distance : [", i); // 현재 그래프 출력
            for(int k = 0; k < graph.length; k++){
                System.out.print((int)graph[k]+", ");
            }
            System.out.println("]");
            System.out.println();
        }

        for(int i = 0; i < edges.size(); i++) { // 음수 사이클 존재를 확인하기 위한 추가 반복
            Edge edge_i = edges.get(i);
            double current_d = graph[edge_i.getV2()];
            double temp_d = graph[edge_i.getV1()] + edge_i.getW();
            if(current_d > temp_d) {
                return false;
            }
        }

        return true;
    }

    private void init_single_source(double[] graph, int s) { // 시작정점을 제외하고 무한대로 설정
        for(int i = 0; i < graph.length; i++) {
            if(i == s) graph[i] = 0;
            else graph[i] = Double.POSITIVE_INFINITY;
        }
    }



}

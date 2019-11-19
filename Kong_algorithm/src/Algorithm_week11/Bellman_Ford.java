package Algorithm_week11;

import java.util.ArrayList;

public class Bellman_Ford {

    public boolean bellman_ford(double[] graph, int[][] w, int s) {
        init_single_source(graph, s);
        for(int i = 0; i < graph.length-1; i++) { // 정점의 수 -1 만큼 반복

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

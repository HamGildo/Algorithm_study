package Algorithm_week11;

import java.util.ArrayList;

public class Bellman_Ford {

    public boolean bellman_ford(int[] graph, int[][] w, int s) {
        init_single_source(graph, s);

        return true;
    }

    private void init_single_source(int[] graph, int s) { // 시작정점을 제외하고 무한대로 설정
        for(int i = 0; i < graph.length; i++) {
            if(i == s) continue;
            graph[i] = (int)Double.POSITIVE_INFINITY;
        }
    }



}

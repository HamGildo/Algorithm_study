package Algorithm_week12;

import sun.security.provider.certpath.Vertex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Dijkstra {
    Vertex[] d;
    ArrayList<Integer> S;
    Queue<Vertex> queue;

    public class Vertex implements Comparable<Vertex>{
        int name;
        int d;

        public Vertex(int name, int d) {
            this.name = name;
            this.d = d;
        }

        public void setD(int d) {
            this.d = d;
        }

        @Override
        public int compareTo(Vertex v) {
            if (this.d > v.d) return 1;
            else if (this.d < v.d) return -1;
            else return 0;
        }
    }

    public Dijkstra() {
        queue = new PriorityQueue<>();
        S = new ArrayList<>();
    }

    private void initialization(int n, int s) {
        // d배열을 초기화
        d = new Vertex[n+1];
        d[s] = new Vertex(s, 0);
        // s를 제외한 나머지는 무한대(MaxInt)
        for(int i = 1; i < d.length; i++) {
            if(i != s-1) d[i] = new Vertex(i, Integer.MAX_VALUE);
        }
        //Q에 d의 요소들을 다 넣음
        for(int j = 1; j < n; j++) {
            queue.add(d[j]);
        }
    }

    public void Dijkstra_algorithm(int[][] distance, int s) {
        initialization(distance.length, s);
        int index = 0;
        while(!queue.isEmpty()) {
            System.out.println("=========================================");
            Vertex u = queue.poll(); //Q에서 하나 꺼냄
            S.add(u.name); //꺼낸 값을 S에 넣음
            System.out.printf("S[%d] : d[%d] = %d\n", index++, u.name, u.d);
            System.out.println("-----------------------------------------");
            Vertex[] qqq = (Vertex[]) queue.toArray(); //queue를 배열로 전환
            for(int i = 0; i < qqq.length; i++) {
                int temp = distance[u.name-1][qqq[i].name-1];
                if(temp != Integer.MAX_VALUE) {
                    temp += d[u.name].d;
                }

                if(temp < d[qqq[i].name].d) {
                    System.out.printf("Q[%d] : d[%d] = %d -> d[%d] = %d\n",i ,qqq[i].name
                            ,d[qqq[i].name].d, qqq[i].name, temp);
                    queue.remove(d[qqq[i].name]);
                    d[qqq[i].name].setD(temp);
                    queue.add(d[qqq[i].name]);
                }
                else {
                    System.out.printf("Q[%d] : d[%d] = %d\n",i ,qqq[i].name
                            ,d[qqq[i].name].d);
                }
            }
        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("C:\\Users\\ham54\\Desktop\\CSE\\tests\\data12.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String readline;


    }

}

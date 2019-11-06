package Algorithm_week08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Segmented_Least_Squares {
    public double make_SLS(int n, Point[] p, int c) {
        double[] OPT = new double[n+1];
        OPT[0] = 0;
        double[][] errorSum = new double[n+1][n+1]; // i ~ j 까지의 SSE를 저장하는 배열
        //최소 오차를 갖는 그래프의 a와 b를 저장하기 위한 배열
        double[][] a_arr = new double[n+1][n+1], b_arr = new double[n+1][n+1];

        //계산에 필요한 변수들이다. 차례대로 시그마xy, 시그마x, 시그마y, 시그마x제곱이다.
        double Sxy=0, Sx=0, Sy=0, Sx_2=0;
        //SSE를 구하기 위한 변수이다.
        double a,b,N;
        //SSE들의 집합을 구한다.
        for(int j = 1; j < n+1; j++) {
            for(int i = 1; i < j+1; i++) {
                for(int k = i; k < j+1; k++) {
                    Sxy += p[k-1].getX() * p[k].getY();
                    Sx += p[k-1].getX();
                    Sy += p[k-1].getY();
                    Sx_2 += Math.pow(p[k].getX(), 2);
                }
                N = j - i + 1;
                a = (N*Sxy - Sx*Sy) / (N*Sx_2 - Math.pow(Sx,2));
                b = (Sy - a*Sx)/N;

                a_arr[i][j] = a;
                b_arr[i][j] = b;

                for(int k2 = i; k2 < j+1; k2++) {
                    errorSum[i][j] += Math.pow((p[k2-1].getY() - a*p[k2-1].getX() - b),2);
                }
            }
        }
        //OPT배열 생성
        for(int j = 1; j < n+1; j++) {
            for(int i = 1; i < j+1; i++) {
               OPT[j] = errorSum[i][j] + c + OPT[i-1];
            }
        }
        //최종값 리턴
        return OPT[n];
    }

    public static void main(String[] args) throws IOException {
        //파일입력
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\ham54\\Desktop\\CSE\\tests\\data08.txt");
        //버퍼
        byte[] readBuffer = new byte[fileInputStream.available()];
        while(fileInputStream.read(readBuffer) != -1){}
        //바이트 -> 문자열 -> 파싱
        String input = new String(readBuffer);
        String[] inputs = input.split(",");
        //int배열 생성
        double[] arrayA = new double[inputs.length];
        for(int i = 0; i < arrayA.length; i++) {
            arrayA[i] = Double.parseDouble(inputs[i]);
        }
        int n = (int)arrayA[0];
        double c =  arrayA[arrayA.length-1];
        //point 배열을 생성한다.
        Point[] points = new Point[n];
        for(int i = 0; i < n; i++){
            points[i] = new Point(arrayA[2*i+1],arrayA[2*i+2]);
        }
    }





}

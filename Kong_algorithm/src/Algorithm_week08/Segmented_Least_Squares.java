package Algorithm_week08;

public class Segmented_Least_Squares {
    public double make_SLS(int n, Point[] p, int c) {
        double[] OPT = new double[n+1];
        OPT[0] = 0;
        double[][] errorSum = new double[n+1][n+1]; // i ~ j 까지의 에러의 합

        //계산에 필요한 변수들이다. 차례대로 시그마xy, 시그마x, 시그마y, 시그마x제곱이다.
        double Sxy=0, Sx=0, Sy=0, Sx_2=0;
        //SSE를 구하기 위한 변수이다.
        double a,b;
        //SSE들의 집합을 구한다.
        for(int j = 1; j < n+1; j++) {
            for(int i = 1; i < j+1; i++) {
                for(int k = i; k < j+1; k++) {
                    Sxy += p[k].getX() * p[k].getY();
                    Sx += p[k].getX();
                    Sy += p[k].getY();
                    Sx_2 += Math.pow(p[k].getX(), 2);
                }
                a = (j*Sxy - Sx*Sy) / (j*Sx_2 - Math.pow(Sx,2));
                b = (Sy - a*Sx)/j;
                for(int k2 = i; k2 < j+1; k2++) {
                    errorSum[k2][j] += Math.pow((p[k2].getY() - a*p[k2].getX() - b),2);
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





}

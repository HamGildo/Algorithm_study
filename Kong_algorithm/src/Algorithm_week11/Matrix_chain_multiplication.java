package Algorithm_week11;

import java.io.*;
import java.util.ArrayList;

public class Matrix_chain_multiplication {

    private int[][] Matrix_chain_order(ArrayList<Matrix> matrices) {
        int n = matrices.size();
        int[][] m = new int[n][n];

        for(int i = 1; i < n; i++){
            m[i][i] = 0;
        }
        for(int l = 2; l < n; l++) { // l = chain_length
            for(int i = 1; i < n-l+1; i++) {
                int j = i + l - 1;
                m[i][j] = Integer.MAX_VALUE;
                for(int k = i; k < j; k++) {
                    int q = m[i][k] + m[k+1][j]
                            + matrices.get(i).getRow()*matrices.get(k).getColumn()*matrices.get(j).getColumn();
                    if(q < m[i][j]) {
                        m[i][j] = q;
                    }
                }
            }
        }

        return m;
    }

    public void resultPrint(ArrayList<Matrix> matrices) {
        int[][] m;
        m = Matrix_chain_order(matrices);

        for(int i = 1; i < matrices.size(); i++) {
            for(int j = 1; j < matrices.size(); j++) {
                System.out.print(m[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Matrix> matrices = new ArrayList<>();

        //파일입력
        File file = new File("C:\\Users\\ham54\\Desktop\\CSE\\tests\\data11_matrix_chain.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String read; // 버퍼에서 읽어들인 한 줄을 저장할 장소
        matrices.add(new Matrix(0,0)); // 행렬을 저장하는 배열의 0번 칸은 쓰이지 않음
        while((read = bufferedReader.readLine()) != null) {
            String[] arr_d = read.split(",");
            Matrix I = new Matrix(Integer.parseInt(arr_d[0]), Integer.parseInt(arr_d[1]));
            matrices.add(I);
        }

        Matrix_chain_multiplication test = new Matrix_chain_multiplication();
        test.resultPrint(matrices);


    }




}

package Algorithm_week11;

import java.io.*;
import java.util.ArrayList;

public class Matrix_chain_multiplication {

    private int[][] Matrix_chain_order(Matrix[] matrices) {
        int n = matrices.length;
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
                            + matrices[i].getRow()*matrices[k].getColumn()*matrices[j].getColumn();
                    if(q < m[i][j]) {
                        m[i][j] = q;
                    }
                }
            }
        }

        return m;
    }

    public void resultPrint(Matrix[] matrices) {
        int[][] m;
        m = Matrix_chain_order(matrices);

        for(int i = 1; i < matrices.length; i++) {
            for(int j = 1; j < matrices.length; j++) {
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
        while((read = bufferedReader.readLine()) != null) {
            String[] arr_d = read.split(",");
            Matrix I = new Matrix(Integer.parseInt(arr_d[0]), Integer.parseInt(arr_d[1]));
            matrices.add(I);
        }




    }




}

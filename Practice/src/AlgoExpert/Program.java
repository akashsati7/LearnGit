package AlgoExpert;

import java.util.ArrayList;
import java.util.List;

class Program {

/*

  .  .  .  .
     .  .  .
  .     .  .
* */
    public static void main(String[] args){
        int[][] riverAndLand = {{1,0,1,1,0},
                                {1,0,1,0,0} ,
                                {0,0,1,1,1} ,
                                {1,1,1,0,1} ,
                                {1,1,1,1,0},
                                {1,1,1,1,0} };
        System.out.println(riverSizes(riverAndLand));
    }

    public static List<Integer> riverSizes(int[][] matrix) {
        // Write your code here.
        List<Integer> riverSizes = new ArrayList();
        List<String> includedCells = new ArrayList<>();
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                if(matrix[i][j]==1 && !includedCells.contains(i+" "+j)){
                    List<String> newIncludedCells = new ArrayList();
                    newIncludedCells.add(i+" "+j);
                    int val = 1 + recursion(matrix,i,j,newIncludedCells);
                    includedCells.addAll(newIncludedCells);
                    riverSizes.add(val);
                }
            }
        }
        return riverSizes;
    }


    public static int recursion(int[][] matrix,int row,int col,List<String> includedCells){
        int colMinusOne = col-1,rowMinusOne = row-1,colPlusOne = col+1,rowPlusOne = row+1;

        int count = 0;

        // look left (col-1)
        if(colMinusOne>=0 && matrix[row][colMinusOne]==1 && !includedCells.contains(row+" "+colMinusOne)){
            includedCells.add(row+" "+colMinusOne);
            count += 1 + recursion(matrix,row,colMinusOne,includedCells);
        }
        // look right (col+1)
        if(colPlusOne<matrix[row].length && matrix[row][colPlusOne]==1 && !includedCells.contains(row+" "+colPlusOne)){
            includedCells.add(row+" "+colPlusOne);
            count += 1 + recursion(matrix,row,colPlusOne,includedCells);
        }
        // look up (row-1)
        if(rowMinusOne>=0 && matrix[rowMinusOne][col]==1 && !includedCells.contains(rowMinusOne+" "+col)){
            includedCells.add(rowMinusOne+" "+col);
            count += 1 + recursion(matrix,rowMinusOne,col,includedCells);
        }
        // look down (row+1)
        if(rowPlusOne<matrix.length && matrix[rowPlusOne][col]==1 && !includedCells.contains(rowPlusOne+" "+col)){
            includedCells.add(rowPlusOne+" "+col);
            count += 1 + recursion(matrix,rowPlusOne,col,includedCells);
        }
        return count;
    }
}

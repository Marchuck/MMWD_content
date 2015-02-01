package com.example.yyy;


public class MatrixHandler {
	public static final int INFINITY = 500;
	
	public Boner getResult(String string){
		int[] vector = StringToIntArray(string, AsteriskCounter(string)+1);
		int[][] matrix = Vector2Matrix(vector);
		return ReduceMatrix(matrix);
		
	}
	public String ReducedMatrixAsString(int[][] matrix){
		String str = "";
		
		for (int i = 0; i < matrix[0].length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if(matrix[i][j] == SearchMaximumValue(matrix[i]))
					str+=" #";
				else if(matrix[i][j] < 10)
					str+=" "+ matrix[i][j];
				else
					str+= matrix[i][j];
				str+= ", ";
			}
			str+= "\n";
		}
		return str;
	}
	
	
	public int AsteriskCounter(String line){
		int stars=0;
		for (int i = 0; i < line.length(); i++) {
			if(line.charAt(i) == '*')
				++stars;
		}
		return stars;
	}
	
	public int[] StringToIntArray(String line, int resultArrayLength) {
		int[] resultArray = new int[resultArrayLength];
		int index = 0;
	    int number = 0;

	    for (int i = 0, n = line.length(); i < n; i++) {
	        char c = line.charAt(i);
	        
	        
	        if (c == '*') {
	            resultArray[index] = number;
	            index++;
	            number = 0;
	        }
	        else if (Character.isDigit(c)) {
	            int digit = Character.getNumericValue(c);
	            number = number * 10 + digit;
	        }
	    }

	    if (index < resultArray.length) {
	        resultArray[index] = number;
	    }
	    return resultArray;
	}
	/**
	 * @param vector
	 * @return 2-dimensional matrix of vector's values
	 */
	public int[][] Vector2Matrix(int[] vector){
		int len = (int)Math.sqrt(vector.length);
		if(len*len != vector.length)
			return null;
		
		int matrix[][] = new int[len][len];
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				matrix[i][j] = vector[len*i + j];
			}
		}
		return matrix;
	}
	public int[] column(int[][] matrix, int col){
		int[] vect = new int [matrix[0].length];
		for (int i = 0; i < matrix[0].length; i++) {
			vect[i] = matrix[i][col];
		}
		return vect;
	}
	/**
	 * 
	 * @param matrix
	 * @return Matrix which contain zeros
	 */
	public Boner ReduceMatrix(int[][] matrix){
		int dimension = matrix[0].length;
		int min = matrix[0][0];
		int LowerBound=0;
		//deal with rows
		for (int i = 0; i < dimension; i++) {
			min = SearchMinimumValue(matrix[i]);
			LowerBound += min;
			for (int j = 0; j < dimension; j++) {
				matrix[i][j] -= min;
			}
		}
		//deal with cols
		for (int i = 0; i < dimension; i++) {
			min = SearchMinimumValue(column(matrix,i));
			LowerBound += min;
			for (int j = 0; j < dimension; j++) {
				matrix[j][i] -= min;
			}
		}
		
		return new Boner(matrix, LowerBound);
	}
	public int[] getRow(int[][] matrix, int i){
		return matrix[i];
	}
	public int SearchMinimumValue(int[] arr){
		int min = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if(arr[i] < min)
				min = arr[i];
		}
		return min;
	}
	public int SearchMaximumValue(int[] arr){
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if(arr[i] > max)
				max = arr[i];
		}
		return max;
	}

}

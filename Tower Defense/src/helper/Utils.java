package helper;

import java.util.ArrayList;

public class Utils {
	public static int[][] ArrayList2DInt(ArrayList<Integer> list, int ySize, int xSize) {
		int[][] newArr = new int[ySize][xSize];
		
		for(int j = 0; j < newArr.length; j++) {
			for(int i = 0; i < newArr[j].length; i++) {
				int index = j*ySize + i;
				newArr[j][i] = list.get(index);
			}
		}
		return newArr;
	}
}

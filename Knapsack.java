/*
	PrintSubSets.java
*/
import java.io.*;
import java.util.*;

public class Knapsack
{
	public static void main( String[] args )
	{	
		Scanner in;
		try {
			in = new Scanner(new FileReader(args[0]));
			int[] set = new int[16];
			for(int i = 0; i < 16; i++ ){
				set[i] = in.nextInt();
			}
			int target = in.nextInt();
			//loaded the set and initialized a target
			
			for ( int i=0 ; i< 65536 ; ++i )
			{	String bitmap = toBitString( i, set.length );
				int[] temp = new int[16];
				int total = 0;
				//System.out.format("bitmap %s  { ", bitmap );
				for ( int bindx=0 ; bindx<set.length ; ++bindx )
					if ( bitmap.charAt(bindx)=='1' )
						temp[bindx] = set[bindx];
						
				for(int p = 0; p<16; p++){
				total = temp[p] + total;
				}
				if(total == target){
					for(int t = 0; t<16; t++){
						if(temp[t] != 0){
							System.out.print(temp[t] + " " );
						}
					}
				System.out.print("\n");
				}else{
					continue;
				}
			}
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	} // END MAIN

	// i.e number 31 converted to a width of 5 bits = "11111"
	//     nuumber 7 converted to a width of 5 bits = "00111"
	static String toBitString( int number, int width )
	{
		String bitstring = "";
		while (number > 0)
		{	if (number % 2 == 0)
				bitstring = "0" + bitstring;
			else
				bitstring = "1" + bitstring;
			number /= 2 ;
		}
		while ( bitstring.length() < width )
				bitstring = "0" + bitstring;
		return bitstring;
	}
} // END CLASS
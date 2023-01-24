
/*
	Deck class
*/


public class Deck
{
	private int[] deck;
	private final int MAX_DECK_SIZE = 30;
	public Deck( int numCards )
	{	if ( numCards%2 != 0 || numCards > MAX_DECK_SIZE ) 
		{
			System.out.format("\nINVALID DECK SIZE: (" + numCards + "). Must be an small even number <= %d\n", MAX_DECK_SIZE);
			System.exit(0);
		}
		// YOU DO THIS => init deck to be exactly numCards long
        this.deck = new int[numCards];
		// YOU DO THIS => fill deck with with 0 1 2 3 ... numCards-1 in order
        for(int i  = 0; i<numCards  ; i++ ){
            deck[i] = i;
        }
	}
	
	public String toString()
	{
		String deckStr = "";
		for ( int i=0 ; i < deck.length ; ++i )
			deckStr += deck[i] + " ";
		return deckStr;
	}

	// ONLY WORKS ON DECK WITH EVEN NUMBER OF CARDS
	// MODIFIES THE MEMBER ARRAY DECK
	public void inShuffle()
	{
		// YOUR CODE HERE DELETE LINE
        int[] tem = new int[deck.length];
		int counter1= 0;
		int counter2 = deck.length/2;
		
		for(int i = 0; i < deck.length; i++ ){
			if(i % 2 == 0){
				tem[i] = deck[counter2];
				counter2++;
			}else{
				tem[i] = deck[counter1];
				counter1++;
				
			}
		}
		deck = tem ;	
		

	}

	// ONLY WORKS ON DECK WITH EVEN NUMBER OF CARDS
	// MODIFIES THE MEMBER ARRAY DECK
	public void outShuffle()
	{
		// YOUR CODE HERE DELETE LINE
        int[] tem = new int[deck.length];
		int counter1= 0;
		int counter2 = deck.length/2;
		for(int i = 0; i < deck.length; i++ ){
			if(i % 2 == 0){
				tem[i] = deck[counter1];
				counter1++;
				
			}else{
				tem[i] = deck[counter2];
				counter2++;
				
			} 
		}
		deck = tem;	

	}
	
	// RETURNS TRUE IF DECK IN ORIGINAL SORTED:  0 1 2 3 ...
	public boolean inSortedOrder()
	{
		// YOUR CODE HERE DELETE LINE
        if(deck.length == 0 || deck.length ==1)
            return true;
        for(int i =1; i < deck.length; i++){
            if(deck[i] < deck[i -1])
                return false;
        }
        return true;
		//return false; // JUST HERE TO COMPILE
	}
	
	//RETURNS BINARY
	public String toBitString( int n ) 
	{
		if(n == 0){
			return "";
		}
		int power = 0;
		int bi = (int) ((Math.log(n) / Math.log(2)) + 1);
		char temp[] = new char [bi];
		for(int i= 0; i<temp.length; i++){
			temp[i] = '0';
		}
		while(n != 0){
			for(int i = 0;Math.pow(2, i) <= n; i++){
				power = i;
			}
			temp[(temp.length - 1) - power] = '1';
			n = (int) (n - Math.pow(2,power));
			}
		String str = new String(temp);	
		return str;
	}
		
		

		
			
// END DECK CLASS
public static void main(String[] args){
int n = 273;
int power = 0;
int bi = (int) ((Math.log(n) / Math.log(2)) + 1);
//System.out.print(bi);
char temp[] = new char[bi];
for(int i= 0; i<temp.length; i++){
	temp[i] = '0';

}
while(n != 0){
	for(int i = 0;Math.pow(2, i) <= n; i++){
		power = i;
	}
	temp[(temp.length - 1) - power] = '1';
	n = (int) (n - Math.pow(2,power));
	}

	
String str = new String(temp);
System.out.print(str);
}
//System.out.print(n = (int) (n - Math.pow(2,power)));
}






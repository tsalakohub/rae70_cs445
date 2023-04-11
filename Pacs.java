import java.io.*;
import java.util.*;

public class Pacs
{	public static void main( String args[] ) throws Exception
	{	BufferedReader memberToPacsFile = new BufferedReader(new FileReader( "member2Pacs.txt"));
		BufferedReader AllPacsFile= new BufferedReader(new FileReader("allPacs.txt"));
		
        TreeSet<String> allPacs= new TreeSet<String>();
		while( AllPacsFile.ready())
			allPacs.add(AllPacsFile.readLine());			
		AllPacsFile.close();
        
        TreeMap<String, TreeSet<String>> pacToMembers = new TreeMap<String, TreeSet<String>>(); // THE MAP THAT GETS PRINTED	
		
		while(memberToPacsFile.ready())
        {
            
            String tokens[] = memberToPacsFile.readLine().split("\\s+");
            String member = tokens[0];
            for(int i = 1; i< tokens.length ; i++){
                if(!pacToMembers.containsKey(tokens[i])){
                    TreeSet<String> members = new TreeSet<String>();
                    members.add(member);
                    pacToMembers.put(tokens[i],members);
                }
                else{
                    TreeSet<String>members = pacToMembers.get(tokens[i]);
                    members.add(member);
                }
            }
        }

	for(String pac : allPacs){
        System.out.print( pac + " ");
        TreeSet<String> members = pacToMembers.get(pac);
        if(members == null){
            System.out.println(" ");
        }else{
            for(String mem : members ){
                System.out.print(mem + " ");
                
            }
            System.out.println();
        }
        

    }
} // END MAIN
} // CLASS

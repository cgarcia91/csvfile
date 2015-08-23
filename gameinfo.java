import java.util.*;
import java.io.*;

class gameinfo{
	public static void main(String args[]){
		
		// Game ID,Game Name,Description,MSRP
		// Game ID,Price,Stock
		// Game ID,Game Name,Description,Price,Stock,Percent Off

		
		
		
		String str = "hello";
		char comma = 'a';
		int item = 0;
		int start = 0;
		int num = 0;
		int q=0; //stores number of quotations encountered

	
		
		
		List<String> GameID = new ArrayList<String>();
		List<String> description = new ArrayList<String>();//arrays to hold the information from both csv files
		List<String> name = new ArrayList<String>();
		List<String> msrp = new ArrayList<String>();
		List<String> stock = new ArrayList<String>();
		List<String> price = new ArrayList<String>();
		
		
		try(BufferedReader br1 = new BufferedReader(new FileReader("game.csv"));
				BufferedReader br2 = new BufferedReader(new FileReader("inventory.csv"));//creating the reader for the csv files and output file
					PrintWriter outputF = new PrintWriter(new FileWriter("final.csv"))){
			
			
			br1.readLine();			//skips the first line
			while((str = br1.readLine()) != null){		//reading a line while check for the end of the source file
				while(comma != ','){
					comma = str.charAt(start+num);				
					num++;
				}
				GameID.add(item,str.substring(start, start+num-1));			//takes the game id from the a game
				start += num;
				num = 0;
				comma = 'a';
				
				while(comma != ',' | (q%2) != 0){
					comma = str.charAt(start+num);				
					num++;
					if(comma == '"')++q;
				}
				name.add(item,str.substring(start, start+num-1));		//takes the name of the game
				start += num;
				num = 0;
				q = 0;
				comma = 'a';
				
				while(comma != ',' | (q%2) != 0){
					comma = str.charAt(start+num);
					num++;	
					if(comma == '"')++q;
				}
				description.add(item,str.substring(start, start+num-1));		//takes the description of the game
				start += num;
				num = 0;
				q =0;
				comma = 'a';
						
				while(comma != ',' && (start+num) < str.length()){
					comma = str.charAt(start+num);
					num++;	
				}
				msrp.add(item,str.substring(start, start+num)); 		//takes the mrsp of the game
				item++;		//iterates to a new game
				start = 0;		//resets values for the next loop
				comma = 'a';
				num = 0;
			}
			
			
			
			
			br2.readLine(); //skips the first line
			
			while((str = br2.readLine()) != null){		//reads the line as well as check for the end of the source file
				while(comma != ','){
					comma = str.charAt(start+num);				
					num++;
				}
				for(int k = 0; k<GameID.size();k++){		//compares the id number with know id numbers
					if(GameID.get(k).equals(str.substring(start, start+num-1))){
						start += num;
						num = 0;
						comma = 'a';
						
						while(comma != ','){
							comma = str.charAt(start+num);				
							num++;
						}
						price.add(k,str.substring(start, start+num-1));		//saves price for known game
						start += num;
						num = 0;
						comma = 'a';
						
						while(comma != ',' && (start+num) < str.length()){
							comma = str.charAt(start+num);				
							num++;
						}
						stock.add(k,str.substring(start, start+num));		//saves stock for known game
						start = 0;
						num = 0;
						comma = 'a';
						break; //breaks out of loop once known game is found and info stored
					}
				}
			}
			
			outputF.println("Game ID,Game Name,Description,Price,Stock,Percent Off");		//writes the first line of output file
			
			for(int j = 0; j < GameID.size(); j++)	//loops through all known games and outputs info
				outputF.println(GameID.get(j)+","+name.get(j)+","+description.get(j)+","+price.get(j)+","+stock.get(j)+","
						+((1-(Double.parseDouble(price.get(j))/Double.parseDouble(msrp.get(j))))*100));
		
					
			
			
		}
		catch(IOException exc){
			System.out.print("I/O Error: "+exc);
		}
	}
}

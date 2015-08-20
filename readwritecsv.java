import java.io.*;


class readwritecsv{
	public static void main(String args[]){
		
		String str;
		
		
		
		
		try(BufferedReader br1 = new BufferedReader(new FileReader("test1.csv"));
				BufferedReader br2 = new BufferedReader(new FileReader("test2.csv"));
					PrintWriter outputF = new PrintWriter(new FileWriter("test.csv",true))){
			while((str = br1.readLine()) != null){
				outputF.println(str);
			}
			while((str = br2.readLine()) != null){
				outputF.println(str);
			}
		}
		catch(IOException exc){
			System.out.println("I/O Error: "+exc);
		}
		
	}
}
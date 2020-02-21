package DataStructuresAssignment1;

import java.util.Arrays; 
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
public class Assignment1{
	
	public static void main(String[] args) throws IOException {
		BufferedReader entry = new BufferedReader(new java.io.FileReader("regional-us-weekly-2020-01-17--2020-01-24.csv"));
		BufferedReader entry2 = new BufferedReader(new java.io.FileReader("regional-us-weekly-2020-01-17--2020-01-24.csv"));
		int eleCount = 0;
		Scanner file = new Scanner(entry);
		Scanner file2 = new Scanner(entry2);
		int skipLine = 0;
		//Skips two beginning lines 
		while(skipLine < 2) {
			file.nextLine();
			skipLine++;
		}
		skipLine=0;
		//Gathering the number count for the amount of artists on the list.
		while(file.hasNext()) { 
			String linet = file.nextLine();
			String[] values = linet.split(",");
			String number = values[0];
			eleCount = Integer.parseInt(number);
		}
		file.close();
		//File2 will be used by a String through .nextLine(). First, we need to skip the two lines.
		while(skipLine < 2) {
			file2.nextLine();
			skipLine++;
		}
		//Array for artists that have double quotes and artists that don't.
		String[] artists = new String[eleCount];
		/*This loop will add the nextLine to a String, then onto the artists array.
		 *The artists array will be used for another array that will be made after the loop.
		 */
		for(int i = 0; i < eleCount;i++) {
			String musicLine = file2.nextLine();
				//This split doesn't separate the commas inside the element.
				String[] musicLineArray =  musicLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
				//"[2]" is where Artist is located
				artists[i] = musicLineArray[2];
		}
		file2.close();

		//Creating Artist Array that removes Double quotations on their name.
		String[] artistsNonQuotes = new String[eleCount];
		for(int i = 0;i<artists.length;i++){
			//Turning element into a String
			String strLine = artists[i].toString();
			//Using the beginning position to see if it's a double quote.
			String strPositionNum = strLine.substring(0,1);
			//Beginning double quote checker. 
			if(strPositionNum.startsWith("\"")) {
				//substring that begins after the first quotation to the last quotation.
				artistsNonQuotes[i] = strLine.substring(1, strLine.length()-1);
			}
			//If there are no quotes, then the artist name is included without the need of a substring.
			else {
			 artistsNonQuotes[i] = strLine;
			}
		}
		/*Since the previous for loop adds all artists names and doesn't have double quotes surrounding them, we can sort it in Alphabetical order.
		 *We were not able to sort before performing the operations inside the for loop, since the quotation marks caused ordering conflicts.
		 */
		Arrays.sort(artistsNonQuotes,String.CASE_INSENSITIVE_ORDER);
		for(int i= 0; i <artistsNonQuotes.length;i++) {
			System.out.println(artistsNonQuotes[i]);
		}
		/*Next, we need to display the artists as well as the amount of times they appear on the list.
		 *We can do this by incorporating selection sort.
		 */
		System.out.println("Artists and amount of their tracks****");
		int n = artistsNonQuotes.length;
		String ContinueValue = null;
		for(int i = 0; i<artistsNonQuotes.length; i++) {
			int j = 0;
			String duplicate = artistsNonQuotes[i];
			int dupCount = 0;
			for (j=i; j<artistsNonQuotes.length;j++) {
				if(artistsNonQuotes[j].equals(ContinueValue))
					break;
				if(artistsNonQuotes[j].equals(duplicate))
					dupCount++;
				if(j == artistsNonQuotes.length-1) {
					//This keeps record of the artists and the number of their tracks on the list.
					System.out.println("Number of tracks from " +duplicate +" is: "  + dupCount);
					ContinueValue = duplicate;
				}
				}
			} 
		}
} //2-20-2020

	



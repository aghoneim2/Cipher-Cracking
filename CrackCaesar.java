import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class CrackCaesar {

	private static HashMap<String, String> dict = new HashMap<String, String>();
	private static List<String> cipher = new ArrayList<String>();
	private static List<String> plaintext = new ArrayList<String>();
	private static int offset = 1;
	
	//method to read in the dictionary and store it
	public static void setDictionary(String theName){
		String filename = theName;
		try{
			BufferedReader reader = new BufferedReader(new FileReader (filename));
			String line;
			while ((line = reader.readLine()) != null){
				dict.put(line.toLowerCase(), line.toLowerCase());
			}
			reader.close();
		}catch(Exception e){
			System.out.println("error reading file");
		}
	}

	public static void setCipher(String theName){
		String filename = theName;
                
		Scanner byLine = null;
		try{
			byLine = new Scanner(new File(filename));
		}catch(FileNotFoundException e){
			System.out.println("cipher file not found");
			return;
		}
		while(byLine.hasNextLine()){
			Scanner words = new Scanner(byLine.nextLine());
			while (words.hasNext()){
				String s = words.next();
				cipher.add(s);
			}
			words.close();
		}
		byLine.close();
	}

	public static void compareCipher(){
		int [] arr = new int[25];
		int maxIndex = 0;

		for (int i = 0; i < 30; i++){
            		String word = cipher.get(i);
			
            		while(offset < 26){
                        	String newString = "";

                        	for (int j = 0; j < word.length(); j++){
                        		Character c = word.charAt(j);
                        		if (Character.isLetter(c)){
                            			c = Character.toLowerCase(c);
                              			int ascii = (int) c;
                                		if ((ascii+offset)>=122){
                                          		int diff = 122-ascii;
                                     			int temp = offset;
                                     			temp = temp - diff;
                                     			ascii = 96+temp;
                                		}else{
                                  			ascii += (offset);
                                		}

                                		c = (char) ascii;
                                   		newString+=c;
                     				}else{
                     					newString+=c;
                     				}
                    		}
                        	if (dict.get(newString)==null){
                        			arr[offset-1]+=0;
                        	}else{
                    				arr[offset-1]+=1;
                        	}
                        	offset++;     		
            		}
            		offset = 1;	
			}
			for (int m = 0; m < arr.length; m++){
				if (arr[m] > arr[maxIndex])
					maxIndex = m;
			}

		offset = maxIndex;
	}

	public static void decrypt(){
		String newString = "";
		for (int i = 0; i < cipher.size(); i++){		
			String word = cipher.get(i);
			for (int j = 0; j < word.length(); j++){
				Character c = word.charAt(j);
				if (Character.isLetter(c)){
					c = Character.toLowerCase(c);
					int ascii = (int) c;
					if ((ascii+offset)>=122){
						int diff = 122-ascii;
						int temp = offset;
						temp = temp - diff;
						ascii = 97+temp;
					}else{
						ascii += (offset+1);
					}
					
					c = (char) ascii;
					newString+=c;
				}else{
					newString+=c;
				}	
			}
			plaintext.add(newString);
			newString = "";
		}
	
	}
	
	public static void printText(){
		for (int i = 0; i < plaintext.size(); i++){
			System.out.print(plaintext.get(i) + " ");
		}
		if  (plaintext.size()==0){
			System.out.println("Empty plaintext");
		}
	}

	public static void main (String [] args){
		setCipher(args[0]);
		setDictionary(args[1]);
		compareCipher();
		decrypt();
		printText();
	}



}

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Arrays;

public class CrackCipher {

	private static HashMap<Character, Integer> cipherChar = new HashMap<Character, Integer>();
	private static HashMap<Character, Integer> knownChar = new HashMap<Character, Integer>();
	private static HashMap<Character, Character> decryption = new HashMap<Character, Character>();
	private static List<Character> cipher = new ArrayList<Character>();
	private static List<String> plaintext = new ArrayList<String>();
	private static List<Character> known = new ArrayList<Character>();
	
	//method to read in the cipher and store it
	public static void setCipher(String theName){
		String filename = theName;
		try{
			BufferedReader reader = new BufferedReader(new FileReader (filename));
			int r;
			while ((r = reader.read()) != -1){
				char ch = (char) r;
				ch = Character.toLowerCase(ch);
				if ((Character.isLetter(ch) || ch == ' ') == false)
					continue;
				if (cipherChar.get(ch) == null){
					cipherChar.put(ch, 1);
				}
				else{
					int x = cipherChar.get(ch);
					cipherChar.put(ch, (++x));
				}
				
			}
			reader.close();
		}catch(Exception e){
			System.out.println("error reading file ciphertext");
		}
	}

	public static void setKnown(String theName){
		String filename = theName;
		try{
			BufferedReader reader = new BufferedReader(new FileReader (filename));
			int r;
			while ((r = reader.read()) != -1){
				char ch = (char) r;
				ch = Character.toLowerCase(ch);
				if ((Character.isLetter(ch) || ch == ' ') == false)
					continue;
				if (knownChar.get(ch) == null){
					knownChar.put(ch, 1);
				}
				else{
					int x = knownChar.get(ch);
					knownChar.put(ch, (++x));
				}
			}
			reader.close();
		}catch(Exception e){
			System.out.println("error reading file knowntext");
		}

	}

	public static void sortCipher(){
		int [] temp = new int[27];
		int counter = 0;
		for (Character key: cipherChar.keySet()){
			temp[counter] = cipherChar.get(key);
			counter++;
		}
		Arrays.sort(temp);
		for (int i = 0; i < temp.length; i++){
			for (Character key : cipherChar.keySet()){
				if(cipherChar.get(key) == temp[i]){
					cipher.add(i, key);
					break;
				}
			}
		}
	}

	public static void sortKnown(){
                int [] temp = new int[27];
                int counter = 0;
                for (Character key: knownChar.keySet()){
                        temp[counter] = knownChar.get(key);
                        counter++;
                }
                Arrays.sort(temp);
                for (int i = 0; i < temp.length; i++){
                        for (Character key : knownChar.keySet()){
                                if(knownChar.get(key) == temp[i]){
                                        known.add(i, key);
                                        break;
                                }
                        }
                }
        }
	
	public static void decrypt(String theName){
		for (int i = 0; i < cipher.size(); i++){
			decryption.put(cipher.get(i), known.get(i));
		}
		
		String filename = theName;
                try{
                        BufferedReader reader = new BufferedReader(new FileReader (filename));
                        int r;
                        while ((r = reader.read()) != -1){
                                char ch = (char) r;
                            	ch = Character.toLowerCase(ch);
                                if (decryption.get(ch) == null){
                              		System.out.print(ch);
                                }
                                else{
                                        System.out.print(decryption.get(ch));
                                }

                        }
                        reader.close();
                }catch(Exception e){
                        System.out.println("error reading file ciphertext");
                }

				
	}

	public static void main(String [] args){
		setCipher(args[0]);
		setKnown(args[1]);
		sortCipher();
		sortKnown();
		decrypt(args[0]);
		//for (Character key: cipherChar.keySet()){
		//	System.out.println("_" + key + "_" + ": " + cipherChar.get(key));
		//}
		//System.out.println("-----------------------------------");
		//for (Character key: knownChar.keySet()){
                //        System.out.println("_" + key + "_" + ": " + knownChar.get(key));
                //}

	}
}

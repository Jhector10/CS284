package anagrams;

/*
 * Creator: Simrun Heir
 * Assignment: CS 284 HW 6
 * Pledge: "I pledge my Honor that I have abided by the Stevens Honor System."
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Anagrams {
	final Integer[] primes =  
			{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 
			31, 37, 41, 43, 47, 53, 59, 61, 67, 
			71, 73, 79, 83, 89, 97, 101};
	Map<Character,Integer> letterTable = new HashMap<>();
	Map<Long,ArrayList<String>> anagramTable = new HashMap<>();

	/**
	 * builds hash table for letterTable
	 */
	private void buildLetterTable() {
		Character alphabet[] = 
			{'a','b','c','d','e','f','g','h','i','j','k','l',
			'm','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		for (int i=0; i<26; i++) {
			letterTable.put(alphabet[i], this.primes[i]);
		}
	}

	Anagrams() {
		buildLetterTable();
		anagramTable = new HashMap<Long,ArrayList<String>>();
	}

	/**
	 * computes hash code for string s and adds word to anagramTable
	 * @param s string to be added
	 */
	private void addWord(String s) {
		if (s.isBlank()==true) {
			throw new IllegalStateException("addWord(String s): String s has no characters or is empty");
		}
		long hashCode = myhashcode(s);
		//check to see if hashCode is in the table
		if (anagramTable.containsKey(hashCode)==false) {
			//add thing to anagramTable
			ArrayList<String> hashedWord = new ArrayList<>();
			hashedWord.add(s);
			anagramTable.put(hashCode, hashedWord);
		} else {
			ArrayList<String> anagrams = anagramTable.get(hashCode);
			if (!anagrams.contains(s)) {
				anagrams.add(s);
			}
			anagramTable.put(hashCode, anagrams);
		}
	}
	
	/**
	 * computes hash code of string s through unique factorization
	 * all anagrams of a word should
	 * @param s input string
	 * @return hash code of string s as a long
	 */
	private long myhashcode(String s) {
		if (s.isBlank()==true) {
			throw new IllegalStateException("myhashcode(String s): String s has no characters or is empty");
		} else {
			long hashCode = 1;
			for (int i=0; i<s.length(); i++) {
				hashCode *= letterTable.get(s.charAt(i));
			}
			return hashCode;
		}
	}
	
	public void processFile(String s) throws IOException {
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		while ((strLine = br.readLine()) != null)   {
		  this.addWord(strLine);
		}
		br.close();
	}
	
	/**
	 * Return entries of anagramTable that have largest number of anagrams
	 * throws exception when anagramTable is empty
	 * @return array list of all entries in anagramTable with largest number of anagrams
	 */
	private ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries() {
		if (anagramTable.isEmpty()==true) {
			throw new IllegalStateException("getMaxEntries: no entries in list");
		} else {
			Set <Map.Entry<Long,ArrayList<String>>> keys = anagramTable.entrySet();
			ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries = new ArrayList<Map.Entry<Long, ArrayList<String>>>();
			int maxSize = 0;
			
			for (Map.Entry<Long,ArrayList<String>> current : keys) {
				if (maxEntries.isEmpty()) { 
					//anagramTable is empty
					maxSize = current.getValue().size();
					maxEntries.add(current);
				} else { 
					//anagramTable isn't empty
					int currentSize = current.getValue().size();
					if (maxSize==currentSize) {
						//new key value pair has same number of values as max value
						maxEntries.add(current);
					} else if (currentSize>maxSize) {
						//new max value found
						maxEntries.clear();
						maxEntries.add(current);
						maxSize = currentSize;
					}
				}
			}
			return maxEntries;
		}
	    
	}
	
	public static void main(String[] args) {
		Anagrams a = new Anagrams();

		final long startTime = System.nanoTime();    
		try {
			a.processFile("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime/1000000000);
		System.out.println("Time: "+ seconds);
		System.out.println("List of max anagrams: "+ maxEntries);
	}
}

import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

class LZCore {
	
	int dictSize = 256;
	
	// The `encode` method takes a string as input and returns a list of integers representing the compressed data.
	List<Integer> encode(String s) {
		// Create a HashMap called `dictionary` to store the character mappings.
		Map <String, Integer> dictionary = new HashMap<>();
		
		// Populate the dictionary with the ASCII characters.
		for (int i = 0; i < dictSize; i++)
			dictionary.put(String.valueOf((char) i), i);
		
		// Create an empty string called `currentChars` to store the current characters being processed.
		String currentChars = "";
		
		// Create an empty ArrayList called `result` to store the encoded output.
		List<Integer> result = new ArrayList<>();
		
		// Loop through each character in the input string.
		for (char character : s.toCharArray()) {
			// Append the current character to the `currentChars` string.
			String charsToAdd = currentChars + character;
			
			// Check if the `dictionary` contains the `charsToAdd` string.
			if (dictionary.containsKey(charsToAdd)) {
				// If so, update `currentChars` to include `character`.
				currentChars = charsToAdd;
			} else {
				// Otherwise, add the current mapping to the `result` list and add a new mapping to the `dictionary`.
				result.add(dictionary.get(currentChars));
				dictionary.put(charsToAdd, dictSize++);
				currentChars = String.valueOf(character);
			}
		}
		
		// Add the final mapping to the `result` list.
		if (!currentChars.isEmpty()) {
			result.add(dictionary.get(currentChars));
		}
		
		// Print the encoded output to the console.
		System.out.println("Encoded: " + result.stream().map(String::valueOf).collect(Collectors.joining("")));
		
		// Return the encoded output as a list of integers.
		return result;
	}
	
	// The `decode` method takes a list of integers as input and returns the original uncompressed string.
	String decode(List<Integer> encodedText) {
		// Create a HashMap called `dictionary` to store the character mappings.
		Map <Integer, String> dictionary = new HashMap<>();
		
		// Populate the dictionary with the ASCII characters.
		for (int i = 0; i < dictSize; i++)
			dictionary.put(i, String.valueOf((char) i));
		
		// Get the first integer from the `encodedText` list and convert it to a character.
		String characters = String.valueOf((char) encodedText.remove(0).intValue());
		
		// Create a StringBuilder called `result` to store the decoded output.
		StringBuilder result = new StringBuilder(characters);
		
		// Loop through each remaining integer in the `encodedText` list.
		for (int code : encodedText) {
			// Get the corresponding string from the `dictionary`.
			String entry = dictionary.containsKey(code) ? 
						dictionary.get(code) : characters + characters.charAt(0);
			
			// Append the decoded string to the `result` StringBuilder.
			result.append(entry);
			
			// Add a new mapping to the `dictionary`.
			dictionary.put(dictSize++, characters + entry.charAt(0));
			
			// Update the `characters` string to the decoded string.
			characters = entry;
		}
		
		// Return the decoded output as a string.
		return result.toString();
	}
}

// The `L

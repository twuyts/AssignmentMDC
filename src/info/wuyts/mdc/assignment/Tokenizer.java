package info.wuyts.mdc.assignment;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Simple tokenizer class.
 * Takes a string or a stream of strings and returns a stream of words.
 * All punctuation characters and non-ASCII characters are removed.
 *  
 */
public class Tokenizer {

	public static Stream<String> tokenizeFile(String path)  {
		Stream<String> stream;
		try {
			stream = Files.lines(Paths.get(path), Charset.forName("Cp1252"));
		} catch (IOException e) {
			return null;
		}			
		return tokenize(stream);
	}
	
	public static Stream<String> tokenize(String string) {
		return tokenize(Stream.of(string));		
	}
	
	public static Stream<String> tokenizeParallel(Stream<String> s) {
		return s.parallel()
				.flatMap(Pattern.compile("[\\s\\p{Punct}]+")::splitAsStream)
				.map(Tokenizer::cleanWord);
	}

	public static Stream<String> tokenize(Stream<String> s) {
		return s.sequential()
				.flatMap(Pattern.compile("[\\s\\p{Punct}]+")::splitAsStream)
				.map(Tokenizer::cleanWord);
	}

	public static String cleanWord(String s) {
		return s.replaceAll("[^a-zA-Z0-9]+", "");
	}
}

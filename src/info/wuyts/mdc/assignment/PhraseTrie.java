package info.wuyts.mdc.assignment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A simple trie (or prefix tree) representing a dictionary of phrases.
 * 
 * The dictionary can be initialized from
 * - a stream of phrases
 * - an array of phrases
 * - a pathname pointing to a file containing phrases (1 line per phrase)
 * 
 */
public class PhraseTrie {

	public PhraseTrieNode root;
	

	public PhraseTrie() {
		this.root = new PhraseTrieNode();
	}
	
	public static PhraseTrie of(Stream<String> stream) {
		PhraseTrie trie = new PhraseTrie();
		stream.forEach(trie::addPhrase);
		return trie;
	}

	public static PhraseTrie of(String path) throws IOException {
		Stream<String> stream;
		stream = Files.lines(Paths.get(path));
		PhraseTrie trie = PhraseTrie.of(stream);
		stream.close();
		return trie;
	}
	
	public static PhraseTrie of(String... phrases) {
		return PhraseTrie.of(Stream.of(phrases));
	}
		
	public void addPhrase(String phrase) {
		PhraseTrieNode node = this.root;
		List<String> words = Stream.of(phrase)				
					.flatMap(Pattern.compile("\\s+")::splitAsStream)
					.map(Tokenizer::cleanWord)
					.collect(Collectors.toList());
		
		for (String word: words) {
			node = node.addWord(word);
		}
		node.phrase = phrase;
	}
}

package info.wuyts.mdc.assignment.test.unit;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.stream.Stream;

import org.junit.Test;

import info.wuyts.mdc.assignment.PhraseCollector;
import info.wuyts.mdc.assignment.PhraseTrie;

public class PhraseCollectorTest {

	@Test
	public final void testPhraseCollector() {
		Stream<String> text = Stream.of( 
				"The","quick","brown","fox",
				"jumps","over","the","lazy","dog",
				"The","quick",
				"brown","fox","jumps","over","the","lazy","dog",
				"The","quick","brown","fox","jumps",
				"The","quick","brown","fox","jumps","over","the","lazy","dog",
				"A","quick","brown","fox","jumps","over","the","lazy","dog");
		
		
		String[] phrases = {
				"The",
				"The quick brown fox",
				"The quick fox",
				"brown fox jumps over",
				"The quick red fox"};
		
		PhraseTrie trie = PhraseTrie.of(phrases);
		
		Map<String,Integer> found = text.collect(new PhraseCollector<String>(trie));
		
		assertEquals(3, found.size());
		assertEquals(8, found.get("The").intValue());
		assertEquals(4, found.get("The quick brown fox").intValue());
		assertNull(found.get("The quick fox"));
		assertEquals(4, found.get("brown fox jumps over").intValue());
		assertNull(found.get("The quick red fox"));
	}
}

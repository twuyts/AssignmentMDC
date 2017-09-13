package info.wuyts.mdc.assignment.test.unit;

import static org.junit.Assert.*;

import org.junit.Test;

import info.wuyts.mdc.assignment.PhraseTrie;;

public class PhraseTrieTest {

	@Test
	public final void testOfStringArray() {
		String[] phrases = {  
				"University of Antwerp",
				"University of Namur",
				"London School of Economics"};
		PhraseTrie trie = PhraseTrie.of(phrases);
		assertNotNull(trie.root.find("University"));
		assertNotNull(trie.root.find("University").find("of").find("Antwerp"));
		assertNotNull(trie.root.find("University").find("of").find("Namur"));
		assertNotNull(trie.root.find("London").find("School").find("of").find("Economics"));
		assertNull(trie.root.find("University").phrase);
		assertEquals(trie.root.find("University").find("of").find("Namur").phrase,
				"University of Namur");
		assertEquals(trie.root.find("University").find("of").find("Antwerp").phrase,
				"University of Antwerp");
		assertEquals(trie.root.find("London").find("School").find("of").find("Economics").phrase,
				"London School of Economics");
	}

}

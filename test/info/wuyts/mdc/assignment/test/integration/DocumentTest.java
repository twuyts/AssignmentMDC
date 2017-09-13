package info.wuyts.mdc.assignment.test.integration;

import static org.junit.Assert.*;

import org.junit.Test;

import info.wuyts.mdc.assignment.Document;
import info.wuyts.mdc.assignment.PhraseTrie;

public class DocumentTest {

	@Test
	public final void testFindPhrases() {
		PhraseTrie trie = PhraseTrie.of(
				"The",
				"The quick brown fox",
				"The quick fox",
				"brown fox jumps over",
				"The quick red fox",
				"The slow red fox"
				);

		Document doc = new Document("brownfox.txt");
		doc.findPhrases(trie);
		
		assertEquals(3, doc.phraseCounts.size());
		assertEquals(8, doc.phraseCounts.get("The").intValue());
		assertEquals(4, doc.phraseCounts.get("The quick brown fox").intValue());
		assertNull(doc.phraseCounts.get("The quick fox"));
		assertEquals(4, doc.phraseCounts.get("brown fox jumps over").intValue());
		assertNull(doc.phraseCounts.get("The quick red fox"));
		assertNull(doc.phraseCounts.get("The slow red fox"));
	}

}

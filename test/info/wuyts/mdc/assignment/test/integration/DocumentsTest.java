package info.wuyts.mdc.assignment.test.integration;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import info.wuyts.mdc.assignment.Document;
import info.wuyts.mdc.assignment.Documents;
import info.wuyts.mdc.assignment.PhraseTrie;

public class DocumentsTest {

	@Test
	public final void testProcessFiles() {
		PhraseTrie trie = PhraseTrie.of(
				"The",
				"The quick brown fox",
				"The quick fox",
				"brown fox jumps over",
				"The quick red fox",
				"The slow red fox"
				);

		List<Document> foundList = Documents.processFiles(trie,"brownfox.txt", "redfox.txt");
		
		for (Document doc : foundList) {
			switch (doc.filename) {
			case "brownfox.txt":
				assertEquals(3, doc.phraseCounts.size());
				assertEquals(8, doc.phraseCounts.get("The").intValue());
				assertEquals(4, doc.phraseCounts.get("The quick brown fox").intValue());
				assertNull(doc.phraseCounts.get("The quick fox"));
				assertEquals(4, doc.phraseCounts.get("brown fox jumps over").intValue());
				assertNull(doc.phraseCounts.get("The quick red fox"));
				assertNull(doc.phraseCounts.get("The slow red fox"));
				break;
			case "redfox.txt":
				assertEquals(2, doc.phraseCounts.size());
				assertEquals(8, doc.phraseCounts.get("The").intValue());
				assertNull(doc.phraseCounts.get("The quick brown fox"));
				assertNull(doc.phraseCounts.get("The quick fox"));
				assertNull(doc.phraseCounts.get("brown fox jumps over"));
				assertNull(doc.phraseCounts.get("The quick red fox"));
				assertEquals(4,doc.phraseCounts.get("The slow red fox").intValue());
				break;
			default:
				fail();
				break;
			}
		}

	}

}

package info.wuyts.mdc.assignment;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Process a list of documents (files) 
 * and return a list of Document objects, 
 * providing details of the phrases found in each document
 *   
 */
public class Documents {
	public static List<Document> processFiles(PhraseTrie phrases, String... files ) {
		return Stream.of(files)
				.parallel()
				.map(Document::new)
				.map(doc -> doc.findPhrases(phrases))
				.collect(Collectors.toList());
	}

	public static List<Document> processFilesSequentially(PhraseTrie phrases, String... files) {
		return Stream.of(files)
				.sequential()
				.map(Document::new)
				.map(doc -> doc.findPhrases(phrases))
				.collect(Collectors.toList());
	}

}

package info.wuyts.mdc.assignment;

import java.util.Map;
/** 
 * Class to read a filename and search for phrases
 *    
 */
public class Document {
	public String filename;
	public Map<String, Integer> phraseCounts;
	
	public Document(String filename) {
		this.filename = filename;
	}

	public Document findPhrases(PhraseTrie phrases) {
		this.phraseCounts = Tokenizer.tokenizeFile(this.filename)
				.sequential()
				.collect(new PhraseCollector<String>(phrases));
		return this;
	}
}

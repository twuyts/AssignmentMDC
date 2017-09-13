package info.wuyts.mdc.assignment;

import java.util.Map;
import java.util.TreeMap;
/**
 * Represents a single node in the PhraseTrie.
 * 
 * If the path leading up to the node describes a complete phrase
 * the phrase property will contain the phrase.
 * Otherwise it will contain null.
 * 
 * @author Tim
 *
 */
public class PhraseTrieNode {
	public String phrase;
    private Map<String, PhraseTrieNode> children;
      
    public PhraseTrieNode() {
    	this.phrase = null;
    	this.children = new TreeMap<String, PhraseTrieNode>(String.CASE_INSENSITIVE_ORDER);
    }

    public PhraseTrieNode addWord(String word) {
    	PhraseTrieNode wordNode = this.children.get(word);

		if (null == wordNode) {
			wordNode = new PhraseTrieNode();
			this.children.put(word, wordNode);
		}
		return wordNode;
    }
    
	public PhraseTrieNode find(String word) {
		return this.children.get(word);
	}
}

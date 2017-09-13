package info.wuyts.mdc.assignment;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * A stream collector that uses a PhraseTrie to
 * collect and count all matching phrases.
 * 
 * The output is a map of found phrases with the number of times 
 * each phrase was encountered. 
 * 
 */

public class PhraseCollector<T> implements Collector<T, 
													PhraseCollector.PhraseCollectorAccumulator, 
													Map<String, Integer>> {

	public static class PhraseCollectorAccumulator {
		public HashMap<String, Integer> phrasesFound;
		public List<PhraseTrieNode> matchingNodes;
				
		PhraseCollectorAccumulator(PhraseTrieNode root) {
			phrasesFound = new HashMap<String, Integer>();
			matchingNodes = new ArrayList<PhraseTrieNode>();
			matchingNodes.add(root);
		}

		public void addPhraseFound(String phrase) {
			phrasesFound.merge(phrase, 1, Integer::sum);		
		}
		
		public Map<String, Integer> getFoundPhrases() {
			return this.phrasesFound;
		}

		public PhraseCollectorAccumulator combine(PhraseCollectorAccumulator other) {
			return null;
		}
	}
	
	private PhraseTrie phrases;
		
	public PhraseCollector(PhraseTrie phrases) {
		this.phrases = phrases;
	}
		
	@Override
	public Supplier<PhraseCollectorAccumulator> supplier() {
		return () -> new PhraseCollectorAccumulator(phrases.root);
	}

	@Override
	public BiConsumer<PhraseCollectorAccumulator, T> accumulator() {
		return (pca, word) -> {
			List<PhraseTrieNode> matchingChildren = new ArrayList<PhraseTrieNode>();
			PhraseTrieNode foundNode;			
			
			for(PhraseTrieNode node:pca.matchingNodes) {
				foundNode = node.find((String) word);
				if(foundNode != null) {
					matchingChildren.add(foundNode);
					if(foundNode.phrase != null) {
						pca.addPhraseFound(foundNode.phrase);
					}
				}
			}
			
			pca.matchingNodes.clear();
			pca.matchingNodes.add(phrases.root);
			pca.matchingNodes.addAll(matchingChildren);
		};
	}

	@Override
	public Function<PhraseCollectorAccumulator, Map<String, Integer>> finisher() {
		return PhraseCollectorAccumulator::getFoundPhrases;
	}

	@Override
	public BinaryOperator<PhraseCollectorAccumulator> combiner() {
		return PhraseCollectorAccumulator::combine;
	}

	@Override
	public Set<Characteristics> characteristics() {
		return Collections.emptySet();
	}
}
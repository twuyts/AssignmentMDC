# Description
This project is my solution to the phrase detection assignment (see separate document).
It is the result of 3 afternoons of Java coding, after a hibernation period of
over *ten* years (the era of Java 5!).

# Project structure
The project is build up around the Java stream API. The reason for this choice was 
mainly because the problem seemed to be a candidate for parallelism, and streams 
would allow me to get more acquainted with basic functional programming paradigms.

The task of searching for phrases in a document is broken down to
* build a trie (prefix tree) to hold the list of phrases to be recognized
* break down a document into words (tokens)
* go over all the words in the document and collect the matches using the trie

## PhraseTrie
PhraseTrie is a simple implementation of [a trie (or prefix tree)](https://en.wikipedia.org/wiki/Trie), 
where each node (PhraseTrieNode) represents a 
word in the search phrase. In order to know, when traversing the trie, if we have 
seen a complete phrase, the node representing the last word of the phrase is tagged with the full phrase. 

## Tokenizer
The tokenizer returns a stream of tokens (words), removing punctuation and foreign characters.

## PhraseCollector 
The bulk of the work happens in the PhraseCollector class. This is an implementation of 
the java.util.stream.Collector interface. The basic idea is to traverse the document and 
advance through the trie at the same time when a word matches any of the children of the current
position(s) in the trie. 

The PhraseCollector will output a Map with the phrase as a key, and the number
of times the document contains the phrase as value.

## Document
The document pulls everything together. It takes a filename and a PhraseTrie. The output of the PhraseCollector, i.e. a Map<phrase, count>, is available through the phraseCount property.

## Documents
Documents is a class will take a list of filenames (paths) and will process them. This process is parallelized.

It returns a simple list of Document objects.

# Parallelism
Although the idea was to use the streams API to benefit from potential parallelism, it turns out that the benefits are very small. After waisting some hours on trying to parallelize the collector, I came to the conclusion it can't be done. The algorithm for searching phrases based on a stream of words always requires to keep some form of state when it processes the document. This could be mitigated by using a sliding window approach, with a window size set to the largest possible phrase. Unfortunately, the java stream api does not provide any windowing functions. So in the end, the only parts that can be parallelized are the tokenizer, and parsing multiple documents.

  


 



 
package info.wuyts.mdc.assignment.test.unit;

import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.*;

import org.junit.Test;

import info.wuyts.mdc.assignment.Tokenizer;

public class TokenizerTest {

	@Test
	public final void TokenizeStringShouldReturnStreamOfWords() {
		Stream<String> s = Tokenizer.tokenize("Hello World");
		List<String> l = s.collect(Collectors.toList());
		assertEquals(l.size(),2);
		assertEquals(l.get(0), "Hello");
		assertEquals(l.get(1), "World");
	}
	
	@Test
	public final void TokenizeStringShouldRemovePunctuationFromWords() {
		Stream<String> s = Tokenizer.tokenize("Hello, World!How's Life?");
		List<String> l = s.collect(Collectors.toList());
		assertEquals(l.size(),5);
		assertEquals(l.get(0), "Hello");
		assertEquals(l.get(1), "World");
		assertEquals(l.get(2), "How");
		assertEquals(l.get(3), "s");
		assertEquals(l.get(4), "Life");
	}
	
	@Test
	public final void TokenizeStringShouldRemoveForeignCharactersFromWords() {
		Stream<String> s = Tokenizer.tokenize("j'ai reçu mon diplôme á l'Université de Namur");
		List<String> l = s.collect(Collectors.toList());
		assertEquals(l.size(),10);
		assertEquals(l.get(0), "j");
		assertEquals(l.get(1), "ai");
		assertEquals(l.get(2), "reu");
		assertEquals(l.get(4), "diplme");
		assertEquals(l.get(5), "");
		assertEquals(l.get(7), "Universit");		
	}
	
}


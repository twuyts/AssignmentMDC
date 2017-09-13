package info.wuyts.mdc.assignment.test.integration;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.atomic.LongAdder;

import org.junit.Ignore;
import org.junit.Test;

import info.wuyts.mdc.assignment.Document;
import info.wuyts.mdc.assignment.Documents;
import info.wuyts.mdc.assignment.PhraseTrie;

public class HugeFileTest {

	private void checkOutput(List<Document> foundList) {
		assertEquals(6, foundList.size());
		long totalMatches = 0;

		for(Document document: foundList) { 
			LongAdder a = new LongAdder();
			document.phraseCounts.values().forEach(a::add);
			totalMatches += a.longValue();

			System.out.format("%d matches of %d phrases in file %s\n" , a.intValue(), document.phraseCounts.size(),document.filename);
		}
		assertEquals(642, totalMatches);	
	}
	
	@Test
	public final void testParallel() throws Exception {
		
		PhraseTrie dict;
		
		long startTime = System.currentTimeMillis();
		dict = PhraseTrie.of("universities.txt");
		
		long stopTime = System.currentTimeMillis();
		System.out.format("\nTime needed for loading phrases: %d ms\n", stopTime-startTime);
		
		startTime = System.currentTimeMillis();
		List<Document> foundList = Documents.processFiles(dict, "holmes.txt","war_peace.txt","surgery.txt","history_usa.txt","enc_brit_1.txt", "enc_brit_2.txt"); 
		stopTime = System.currentTimeMillis();
		System.out.format("Time needed for counting phrases in %d files: %d ms\n", foundList.size(), stopTime-startTime);
		
		checkOutput(foundList);
	}

	@Test
	@Ignore
	public final void testSequential() throws Exception {
		
		PhraseTrie dict;
		
		long startTime = System.currentTimeMillis();
		dict = PhraseTrie.of("universities.txt");
		
		long stopTime = System.currentTimeMillis();
		System.out.format("\nTime needed for loading phrases: %d ms\n", stopTime-startTime);
		
		startTime = System.currentTimeMillis();
		List<Document> foundList = Documents.processFilesSequentially(dict, "holmes.txt","war_peace.txt","surgery.txt","history_usa.txt","enc_brit_1.txt", "enc_brit_2.txt"); 
		stopTime = System.currentTimeMillis();
		System.out.format("Time needed for counting phrases SEQUENTIALLY in %d files: %d ms\n", foundList.size(), stopTime-startTime);
		
		checkOutput(foundList);
	}

}

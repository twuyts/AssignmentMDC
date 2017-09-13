Assignment
----------

A *tokenizer* is an algorithm that splits a string in a sequence of words, often
regularizing the input by e.g. removing punctuation and foreign characters.

Start the assignment by writing a simple tokenizer that returns a
*java.util.Stream* of words.

Then, write an algorithm that is able to recognize a number of pre-defined
*phrases* in this stream of words. A phrase is a short sequence of words.

Write a couple of unit tests, and one larger integration test that uses both
tokenizer and phrase recognition algorithm, in the following context: the
pre-defined phrases consists of a list of universities:

-   University of Antwerp

-   University of Namur

-   Université de Namur

-   London School of Economics

-   Emory School of Medicine

-   ...

Design the recognition algorithm is such a way that it can handle phrase lists
of large sizes (10,000+) efficiently. The input text can be copied from the
internet.

 

Please do not spend more than a few hours on this assignment. Take care of
writing correct, efficient, and visually pleasing Java code.

 

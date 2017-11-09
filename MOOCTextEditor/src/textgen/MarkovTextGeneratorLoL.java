package textgen;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;





/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	private static final boolean String = false;

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		
		// TODO: Implement this method.
		String[] wordsinlist = sourceText.split("[\\s]+"); // dividing with the empty spaces.
	
		HashMap<String, Integer> wordCounts = new HashMap<String, Integer>();
		
        for (int i =0; i < wordsinlist.length; i++) {  
        	if (wordCounts.containsKey(wordsinlist[i])) {
                wordCounts.put(wordsinlist[i], wordCounts.get(wordsinlist[i]) + 1 );
              
                	for(ListNode s:wordList)
                	{	
                		//ListNode a = wordList.get(same);
                		if (s.getWord().equals(wordsinlist[i]))
                		{
                			if (i+1< wordsinlist.length)
                			{ 
                				s.addNextWord(wordsinlist[i+1]);
                			}
                		}
                	}	
            }
            else {// it is unique.
                wordCounts.put(wordsinlist[i], 1); 
                
                ListNode a = new ListNode(wordsinlist[i]);
                if (i+1< wordsinlist.length)
                {
                	a.addNextWord(wordsinlist[i+1]);
                    wordList.add(a);
                }
                else{//end of the list.
                	wordList.add(a);
                }
            }
        }
       // System.out.println(wordList);
        ListNode starter = wordList.get(0);
        
		int index = wordList.size()-1;
	    ListNode last = wordList.get(index); 
	    last.addNextWord(starter.getWord());
	  //  System.out.println(wordList); 
	}
	
	private char[] words(int i) {
		// TODO Auto-generated method stub
		return null;
	}


	private Object s(int i) {
		// TODO Auto-generated method stub
		return null;
	}
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	    // TODO: Implement this method
		String generated="";
		String feed="";
		Random n = this.rnGenerator; // new RAndom(42);
		// Random(seed) equals to:
		// Random rnd = new Random();
		// rnd.setSeed(seed);
		//System.out.println(n.nextInt(1000));
		if (numWords==0)
		{
			return "";
		}
		if (wordList != null) // wordList is created.
		{ 
			if (wordList.isEmpty()) // not trained.
			{
				//System.out.println("wordlist is not null. it is empty.");
				return "";
			}
			else{ //wordList  trained.
				//System.out.println("wordlist is not null.it is not empty.");
				ListNode starter = wordList.get(0);
				generated = starter.getWord();
				feed += generated;
				feed +=" ";
				
				for (int i=1; i < numWords; i ++)
				{	
					generated = findInlist(generated,n);
					feed += generated;
					feed +=" ";
				}	
				
				//System.out.println(feed);
				return feed;
			}
		}
		else
		{ // wordList is not created.
			System.out.println("wordlist is null");	
		}
		return null;
	}
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		// TODO: Implement this method.
		this.wordList = new LinkedList<ListNode>();
		this.starter = "";
		this.rnGenerator = new Random();
		train(sourceText);
	}
	
	// TODO: Add any private helper methods you need here.
	public String findInlist(String generated,Random n){
		for (ListNode s : wordList)
		{
			if ( s.getWord().equals(generated) )
			{
				generated = s.getRandomNextWord(n);
				return generated;
			}
			
		}
		return generated;	// could not find in list.
	}
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		//String leo="hi there hi leo";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		
		System.out.println(gen.generateText(20));//20
		
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
		int nextsize = this.nextWords.size();
		
		if(nextsize == 1)
		{
			return this.nextWords.get(0);
		}
		
		int a = generator.nextInt(nextsize);
		//System.out.println(a);
		return this.nextWords.get(a);
		
	    //return null;
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}



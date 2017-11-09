package spelling;

import java.util.TreeSet;

/**
 * @author UC San Diego Intermediate MOOC team
 *
 */
public class DictionaryBST implements Dictionary 
{
   private TreeSet<String> dict;
	
    // TODO: Implement the dictionary interface using a TreeSet.  
 	// You'll need a constructor here
	public DictionaryBST()
	{
		this.dict= new TreeSet<String>();
		
	}
    
    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	// TODO: Implement this method
    	String lowerWord = word.toLowerCase();
    	if (!isWord(lowerWord)) //if not in dictionary.
    	{
    		//System.out.println(" word it is "+ lowerWord);
        	dict.add(lowerWord);
        	return true;
    	} 
    	else 
    	{ // it is in the dictionary.
    		//System.out.println(word + " is word");
    		return false;
    	}
    }


    /** Return the number of words in the dictionary */
    public int size()
    {
    	// TODO: Implement this method
    	return this.dict.size();
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
    	//TODO: Implement this method
    	//System.out.println("isWord " + s + "\n");
    	String lowerWord = s.toLowerCase();
    	if( dict.contains(lowerWord))
    	{
    		return true;
    	} 
    	else 
    	{
    		return false;
    	}
    }
    

}

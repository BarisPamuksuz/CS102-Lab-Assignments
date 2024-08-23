/**
 * @author Barış Pamuksuz 22202238
 * Lab02 assignment
 * Simple Language Model
 */

import java.util.ArrayList;
import java.util.Random;

public class Word{
    
    Random rand = new Random();
    String text;
     public Word(String wordText){
        text = wordText;
        canBeFollowedBy = new ArrayList<Word>();
    }
    ArrayList<Word> canBeFollowedBy = new ArrayList<Word>();

    public void addFollowedBy(Word w){
        this.canBeFollowedBy.add(w);  
    }
    public Word getRandomFollowingWord(){
        int randomIndex = rand.nextInt(canBeFollowedBy.size());
        Word followingWord = canBeFollowedBy.get(randomIndex);
        return followingWord;
    }
    public boolean isEndingWord(Word endingWord){
        for(Word each : canBeFollowedBy){
            if(each.text.equals(endingWord.text)){
                return true;
            }
        }
        return false;
    }
}



    





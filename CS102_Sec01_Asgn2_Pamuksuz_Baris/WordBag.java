/**
 * @author Barış Pamuksuz 22202238
 * Lab02 assignment
 * Simple Language Model
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class WordBag {

    Word startWord = new Word("<START>");
    Word endWord = new Word("<END>");
    Random rand = new Random();
    ArrayList<Word> allWords = new ArrayList<Word>();

    public void processSentence(String sentence) {
        String[] wordsOfASent = sentence.split(" ");
        ArrayList<String> wordsOFaSent = new ArrayList<String>();
    
        for (String wordText : wordsOfASent) {
            wordsOFaSent.add(wordText); 
        }
        wordsOFaSent.add(0, startWord.text);
        wordsOFaSent.add(endWord.text);
        
        for (int i = 0; i < wordsOFaSent.size() - 1; i++) {
            Word currentWord = findOrCreate(wordsOFaSent.get(i));
            Word followingWord = findOrCreate(wordsOFaSent.get(i + 1));
            currentWord.addFollowedBy(followingWord);
            
        }
    }
    public Word findOrCreate(String wordText){

        for( Word word : allWords){
            if(word.text.equals(wordText)){
                return word;
            }
        }
        Word newWord = new Word(wordText);
        allWords.add(newWord);
        return newWord;

    }
    public String generateSentence(int softLimit, int hardLimit){
        
        String sentence = "";
        Word current = allWords.get(0);
        int i = 0;
        while(i < softLimit){
            current = current.getRandomFollowingWord();
            if(!current.text.equals(endWord.text)){
                sentence += " " + current.text;
                i++;
            }
            else{
                return sentence;
            }
        }
        while(i >= softLimit && i < hardLimit){
            if(current.isEndingWord(endWord)){
                return sentence;
            }
            else{
                current = current.getRandomFollowingWord();
                sentence += " "+ current.text;
                i++;
            }
        }
        return sentence;
    }
    public void writeToTextFile(String outputName, int sentenceCount, int softLimit, int hardLimit){
         try {
            FileWriter writer = new FileWriter(outputName+".txt");
            while(sentenceCount > 0){
                String tempSentence = generateSentence(softLimit, hardLimit);
                writer.write(tempSentence+"\n");
                sentenceCount--;
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

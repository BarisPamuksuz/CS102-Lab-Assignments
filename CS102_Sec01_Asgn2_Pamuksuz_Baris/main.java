/**
 * @author Barış Pamuksuz 22202238
 * Lab02 assignment
 * Simple Language Model
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class main {
    
    public static void displayMenu(){   
        System.out.println("1. Generate Sentence");
        System.out.println("2. Output Sentences to Text File");
        System.out.println("3. Exit");
        System.out.print("Please choose an option: ");  
    }
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        int choice;
        WordBag bag = new WordBag();
         try { 
            
            BufferedReader reader = new BufferedReader(new FileReader(new File("sentences.txt")));
            String currentLine = reader.readLine();
            
            while (currentLine != null ) {   
                bag.processSentence(currentLine); 
                currentLine = reader.readLine();
            }
        } catch (FileNotFoundException e) { 
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println("Input file \"sentences.txt\" processed.");
        System.out.println();
        displayMenu();
        choice = input.nextInt();
        while(choice == 1 || choice == 2 || choice != 3){
            int softLimit;
            int hardLimit;
            if(choice == 1){
                System.out.print("Soft Limit: ");
                softLimit = input.nextInt();
                System.out.print("Hard Limit: ");
                hardLimit = input.nextInt();
                System.out.println();
                String generatedSentence = bag.generateSentence(softLimit, hardLimit);
                System.out.println("Generated Sentence: " + generatedSentence.toString());
                System.out.println();
                displayMenu();
                choice = input.nextInt();  
            }   
            else if(choice == 2){
                System.out.print("File Name: ");
                String outputFileName = input.next();
                System.out.print("Sentence Count: ");
                int count = input.nextInt();
                System.out.print("Soft Limit: ");
                softLimit = input.nextInt();
                System.out.print("Hard Limit: ");
                hardLimit = input.nextInt();
                System.out.println();
                bag.writeToTextFile(outputFileName, count, softLimit, hardLimit);
                System.out.println("Saved to "+ outputFileName+ ".txt");
                System.out.println();
                displayMenu();
                choice = input.nextInt();
            }
            else{
                System.out.print("Invalid choice. Please input valid number: ");
                choice = input.nextInt();
            }
        }
        if(choice == 3){
            System.out.println("Bye..");
        }
        
        input.close();
    }
}


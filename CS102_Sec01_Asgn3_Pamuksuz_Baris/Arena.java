import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Arena {

    ArrayList<Unit> allUnits = new ArrayList<Unit>();
    ArrayList<Unit> playerUnits = new ArrayList<Unit>();
    ArrayList<Unit> computerUnits = new ArrayList<Unit>();
    Random rand = new Random();
    public static boolean continueOrNot(ArrayList<Unit> playerUnits, ArrayList<Unit> computerUnits) {
        int totalDeads = 0;
        for (Unit u : playerUnits) {
            if (u.isDead == true) {
                totalDeads++;
            }
        }
        if (totalDeads == 7) {
            System.out.println("The computer wins the game");
            return false;
        } else {
            totalDeads = 0;
            for (Unit k : computerUnits) {
                if (k.isDead) {
                    totalDeads++;
                }
            }
            if (totalDeads == 7) {
                System.out.println("The player wins the game");
                return false;
            }
        }
        return true;
    }

    public Arena() {
        
        
    
        
        playerUnits.add(new Warrior()); 
        for (int i = 1; i <= 6; i++) {
            Unit randomUnit = getRandomUnit();
            playerUnits.add(randomUnit);
        }
    
        
        computerUnits.add(new Warrior()); 
        for (int i = 1; i <= 6; i++) {
            Unit randomUnit = getRandomUnit();
            computerUnits.add(randomUnit);
        }
    }
    
    
    public Unit getRandomUnit() {
        ArrayList<Unit> unitTypes = new ArrayList<>();
        unitTypes.add(new Archer());
        unitTypes.add(new Healer());
        unitTypes.add(new Rogue());
        unitTypes.add(new Bard());
        unitTypes.add(new Wizard());
        unitTypes.add(new Necromancer());
    
        int randomIndex = rand.nextInt(unitTypes.size());
        return unitTypes.get(randomIndex);
    }
    

    void battle(int playerIndex, int computerIndex) {

        Unit tempPlayerUnit = playerUnits.get(playerIndex);
        Unit tempCompUnit = computerUnits.get(computerIndex);

        playerUnits.remove(playerIndex);
        computerUnits.remove(computerIndex);

        System.out.print("Player: ");
        tempPlayerUnit.firstPhase(tempCompUnit, playerUnits, computerUnits);
        System.out.print("Computer: ");
        tempCompUnit.firstPhase(tempPlayerUnit, computerUnits, playerUnits);
        continueOrNot(playerUnits, computerUnits);

        if (tempCompUnit.isDead || tempPlayerUnit.isDead) {
            if(tempCompUnit.isDead && tempPlayerUnit.isDead){
                System.out.println("They are both dead now.");
            }
            else if(tempCompUnit.isDead){
                System.out.println(tempCompUnit.getInfo()+ " is dead now.");
                tempCompUnit.health = 0;
                tempPlayerUnit.level++;
                tempPlayerUnit.maximumHealth++;
                tempPlayerUnit.attackPoints++;
                
            }
            else if(tempPlayerUnit.isDead){
                System.out.println(tempPlayerUnit.getInfo() + " is dead now.");
                tempPlayerUnit.health = 0;
                tempCompUnit.level++;
                tempCompUnit.maximumHealth++;
                tempCompUnit.attackPoints++;
            }
           
            System.out.println("Battle ended after phase 1");
        } 
        else {
            System.out.print("Player: ");
            tempPlayerUnit.secondPhase(tempCompUnit, playerUnits, computerUnits);
            System.out.print("Computer: ");
            tempCompUnit.secondPhase(tempPlayerUnit, computerUnits, playerUnits);
            continueOrNot(playerUnits, computerUnits);

            if (tempCompUnit.isDead || tempPlayerUnit.isDead) {
            if(tempCompUnit.isDead && tempPlayerUnit.isDead){
                System.out.println("They are both dead now.");
            }
            else if(tempCompUnit.isDead){
                System.out.println(tempCompUnit.getInfo()+ " is dead now.");
                tempCompUnit.health = 0;
                tempPlayerUnit.level++;
                tempPlayerUnit.maximumHealth++;
                tempPlayerUnit.attackPoints++;
                
            }
            else if(tempPlayerUnit.isDead){
                System.out.println(tempPlayerUnit.getInfo() + " is dead now.");
                tempPlayerUnit.health = 0;
                tempCompUnit.level++;
                tempCompUnit.maximumHealth++;
                tempCompUnit.attackPoints++;
            }
                System.out.println("Battle ended after phase 2");

            } 
            else {
                System.out.print("Player: ");
                tempPlayerUnit.thirdPhase(tempCompUnit, playerUnits, computerUnits);
                System.out.print("Computer: ");
                tempCompUnit.thirdPhase(tempPlayerUnit, computerUnits, playerUnits);
                
                if (tempCompUnit.isDead || tempPlayerUnit.isDead) {
                if(tempCompUnit.isDead && tempPlayerUnit.isDead){
                    System.out.println("They are both dead now.");
                }
                else if(tempCompUnit.isDead){
                    System.out.println(tempCompUnit.getInfo()+ " is dead now.");
                    tempCompUnit.health = 0;
                    tempPlayerUnit.level++;
                    tempPlayerUnit.maximumHealth++;
                    tempPlayerUnit.attackPoints++;
                
                }
                else if(tempPlayerUnit.isDead){
                    System.out.println(tempPlayerUnit.getInfo() + " is dead now.");
                    tempPlayerUnit.health = 0;
                    tempCompUnit.level++;
                    tempCompUnit.maximumHealth++;
                    tempCompUnit.attackPoints++;
                }
                }
                System.out.println("Battle ended after phase 3");
                continueOrNot(playerUnits, computerUnits);
            }
        }
        playerUnits.add(playerIndex, tempPlayerUnit);
        computerUnits.add(playerIndex, tempCompUnit);

    }

    public static void main(String[] args) {
        Random random = new Random();
        Scanner input = new Scanner(System.in);
        int turn = 1;
        Arena arena = new Arena();
        ArrayList<String> playerMenu = new ArrayList<String>();
        ArrayList<String> computerMenu = new ArrayList<String>();

        while (continueOrNot(arena.playerUnits, arena.computerUnits) && turn < 21) {
            System.out.println();
            System.out.println("Turn: " + turn);
            System.out.println("Computer's units:\n");
            for (int i = 0; i < arena.computerUnits.size(); i++) {
                Unit currentUnit = arena.computerUnits.get(i);
                System.out.printf("%d. %s, LVL: %d, ATK: %d, HEALTH: %d/%d\n", i + 1, currentUnit.getInfo(),
                        currentUnit.level, currentUnit.getAttack(), currentUnit.health, currentUnit.getMaxHealth());
                computerMenu
                        .add(currentUnit.getInfo() + ", LVL: " + currentUnit.level + ", ATK: " + currentUnit.getAttack()
                                + ", HEALTH: " + currentUnit.health + "/" + currentUnit.getMaxHealth());
            }
            System.out.println("\nPlayer's units:\n");
            for (int i = 0; i < arena.playerUnits.size(); i++) {
                Unit currentUnit = arena.playerUnits.get(i);
                System.out.printf("%d. %s, LVL: %d, ATK: %d, HEALTH: %d/%d\n", i + 1, currentUnit.getInfo(),
                        currentUnit.level, currentUnit.getAttack(), currentUnit.health, currentUnit.getMaxHealth());
                playerMenu
                        .add(currentUnit.getInfo() + ", LVL: " + currentUnit.level + ", ATK: " + currentUnit.getAttack()
                                + ", HEALTH: " + currentUnit.health + "/" + currentUnit.getMaxHealth());
            }
            System.out.print("Which unit you choose: ");
            int choice;
            int randomChoiceForcomputer;
            do {
                randomChoiceForcomputer= random.nextInt(arena.computerUnits.size());
            }while(arena.computerUnits.get(randomChoiceForcomputer).isDead);

            do {
                choice = input.nextInt();
                if (choice > 7 || choice < 1 || arena.playerUnits.get(choice-1).isDead) {
                    System.out.print("Not a valid index, please enter again: ");
                }
            } while (choice > 7 || choice < 1 || arena.playerUnits.get(choice-1).isDead);

            System.out.println();
            System.out.println();
            System.out.println("You choose " + playerMenu.get(choice - 1));
            System.out.println("Computer choices " + computerMenu.get(randomChoiceForcomputer));
            

            arena.battle(choice - 1, randomChoiceForcomputer);
            playerMenu.clear();
            computerMenu.clear();
            turn++;
        }
    }

}

import java.util.ArrayList;

public class Warrior extends Unit{   
        
        public Warrior(){
            super("Warrior", 1, 3);
            maximumHealth = level + 2;
            attackPoints = level + 1;
        }
        @Override
        protected void setLevelUporDown(int level){
            maximumHealth += level;
            health+= level;
            attackPoints+= level;
        }
        
        
        @Override
        protected int getAttack(){
            return attackPoints;
        }
        @Override
        protected int getMaxHealth(){
            return maximumHealth;
        }
        @Override
        protected void firstPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
            System.out.println("Warrior does nothing");
        }
        @Override
        protected void secondPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
            int damageAmount = getAttack();
            arenaOpponent.damage(damageAmount);
            System.out.printf("%s damaged arena opponent %s by %d\n", this.getInfo(), arenaOpponent.getInfo(), damageAmount);
            
        }
        @Override
        protected void thirdPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
            int damageAmount = getAttack();
            arenaOpponent.damage(damageAmount);
            System.out.printf("%s damaged arena opponent %s by %d\n", this.getInfo(), arenaOpponent.getInfo(), damageAmount);
            
        }
    }

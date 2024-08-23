import java.util.ArrayList;

public class Archer extends Unit{
        
    
    public Archer(){
            super("Archer", 1, 2);
            maximumHealth = level + 1;
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
            int damageAmount = getAttack();
            arenaOpponent.damage(damageAmount);
            System.out.printf("%s damaged arena opponent %s by %d\n", this.getInfo(), arenaOpponent.getInfo(), damageAmount);
            if(arenaOpponent.isDead){
                System.out.println("Archer killed " + arenaOpponent.getInfo());
            }
        }
        @Override
        protected void secondPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
            
            Unit randomOpponent = chooseRandomUnit(enemyWaiting);
            while(randomOpponent.equals(null)){
                randomOpponent = chooseRandomUnit(enemyWaiting);
            }
            int damageAmount = getAttack();
            randomOpponent.damage(damageAmount);
            System.out.printf("%s damaged waiting opponent %s by %d\n", this.getInfo(), randomOpponent.getInfo(), damageAmount);
            if(randomOpponent.isDead){
                System.out.println("Archer killed " + randomOpponent.getInfo());
                this.level++;
                this.setLevelUporDown(1);
            }
        }
        @Override
        protected void thirdPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
            this.heal(1);
            System.out.println("Archer healed self by " + 1);
        }
    }

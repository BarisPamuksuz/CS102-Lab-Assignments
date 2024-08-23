import java.util.ArrayList;

public class Bard extends Unit{
        
    
        public Bard(){
            super("Bard", 1, 2);
            maximumHealth = level + 1;
            attackPoints = level;
        }
        
        protected void setLevelUporDown(int level){
            maximumHealth += level;
            health+= level;
            attackPoints+= level;
        }
        
        
        
        protected int getAttack(){
            return attackPoints;
        }
        protected int getMaxHealth(){
            return maximumHealth;
        }
        
        protected void firstPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
            int damageAmount = getAttack();
            arenaOpponent.damage(damageAmount);
            System.out.printf("%s damaged arena opponent %s by %d\n", this.getInfo(), arenaOpponent.getInfo(), damageAmount);
            if(arenaOpponent.isDead){
                System.out.println("Bard killed " + arenaOpponent.getInfo());
            }
        }

        protected void secondPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
            if(health < maximumHealth){
                this.heal(1);
                System.out.println("Bard healed self by " + 1);
            }
            else{
                System.out.println("Bard cannot heal since it is full health ");
            }
        }
        
        protected void thirdPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting) {
            Unit randomAliveAllyUnit = chooseRandomUnit(allyWaiting);
            while(randomAliveAllyUnit.isDead){
                randomAliveAllyUnit = chooseRandomUnit(allyWaiting);
            }
            randomAliveAllyUnit.level++;
            randomAliveAllyUnit.setLevelUporDown(1);
            System.out.println("Bard leveled up random alive ally unit. That is: " + randomAliveAllyUnit.getInfo());
        }
    }
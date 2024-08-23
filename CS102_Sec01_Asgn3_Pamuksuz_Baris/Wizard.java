import java.util.ArrayList;

public class Wizard extends Unit{
        
        public Wizard(){
            
            super("Wizard", 1, 3);
            maximumHealth = level + 2;
            attackPoints = 1;
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
            System.out.println("Wizard does nothing");
        }
        @Override
        protected void secondPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
            System.out.println("Wizard does nothing");
        }
        @Override
        protected void thirdPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){

            int damageAmount = getAttack();

            for(int i = 0; i < enemyWaiting.size(); i++){
                Unit currentEnemyUnit = enemyWaiting.get(i);
                if(!currentEnemyUnit.isDead){
                    currentEnemyUnit.damage(damageAmount);
                    System.out.printf("%s damaged waiting opponent %s by %d\n", this.getInfo(), currentEnemyUnit.getInfo(), damageAmount);
                    if(currentEnemyUnit.isDead){
                        System.out.println("Wizard killed " + currentEnemyUnit.getInfo());
                        this.level++;
                        this.setLevelUporDown(1);
                    }
                }
            }

        }
    }
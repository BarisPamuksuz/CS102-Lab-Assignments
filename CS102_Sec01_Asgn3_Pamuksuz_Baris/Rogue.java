import java.util.ArrayList;

public class Rogue extends Unit{
        
        
        public Rogue(){
            super("Rogue", 1, 1);
            maximumHealth = level;
            attackPoints = level + 2;
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
                System.out.println("Rogue killed " + arenaOpponent.getInfo());
            }
        }
        @Override
        protected void secondPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
            int damageAmount = getAttack();
            arenaOpponent.damage(damageAmount);
            System.out.printf("%s damaged arena opponent %s by %d\n", this.getInfo(), arenaOpponent.getInfo(), damageAmount);
            if(arenaOpponent.isDead){
                System.out.println("Rogue killed " + arenaOpponent.getInfo());
            }
        }
        @Override
        protected void thirdPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
            
            Unit randomAliveEnemyUnit = chooseRandomUnit(enemyWaiting);
                while(!randomAliveEnemyUnit.isDead){
                    randomAliveEnemyUnit = chooseRandomUnit(enemyWaiting);
                }
            int damageAmount = getAttack();
            randomAliveEnemyUnit.damage(damageAmount);
            System.out.printf("%s damaged arena opponent %s by %d\n", this.getInfo(), randomAliveEnemyUnit.getInfo(), damageAmount);
            if(randomAliveEnemyUnit.isDead){
                System.out.println("Rogue killed " + randomAliveEnemyUnit.getInfo());
                this.level++;
                this.setLevelUporDown(1);
            }
        }
    }

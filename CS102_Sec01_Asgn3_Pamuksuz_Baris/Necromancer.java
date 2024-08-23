import java.util.ArrayList;

public class Necromancer extends Unit{

        
        public Necromancer(){
            super("Necromancer", 1, 2);
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
                System.out.println("Necromancer killed " + arenaOpponent.getInfo());
                this.level++;
                this.setLevelUporDown(1);
            }
        }

        protected void secondPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting) {
            ArrayList<Unit> deadAllyUnits = new ArrayList<Unit>();
            for (int i = 0; i < allyWaiting.size(); i++) {
                Unit currentUnit = allyWaiting.get(i);
                if (currentUnit.isDead) {
                    deadAllyUnits.add(currentUnit);
                }
            }
            Unit randomDeadAlly = chooseRandomUnit(deadAllyUnits);
            if (randomDeadAlly != null) {
                randomDeadAlly.revive();
                System.out.println("Necromancer revives random dead ally unit. That is: " + randomDeadAlly.getInfo());
            } else {
                this.damage(1);;
                System.out.println("Necromancer lost health by one since there is no dead ally");
            }
        }
        
        protected void thirdPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
            if(arenaOpponent.level > 1){
                arenaOpponent.level--;
                arenaOpponent.setLevelUporDown(-1);
                System.out.println("Necromancer decreased opponent's level by one. That is " + arenaOpponent.getInfo());
            }
            else{
                System.out.println("Necromancer cannot decrease opponent's level since it is minimum , 1.");
            }
        }
    }
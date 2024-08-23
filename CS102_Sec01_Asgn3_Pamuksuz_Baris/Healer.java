import java.util.ArrayList;

public class Healer extends Unit {

    

    public Healer() {
        super("Healer", 1, 3);
        maximumHealth = level + 2;
        attackPoints = level;
    }
    @Override
    protected void setLevelUporDown(int level) {
        maximumHealth += level;
        health += level;
        attackPoints += level;
    }
    @Override
    protected int getAttack() {
        return attackPoints;
    }
    @Override
    protected int getMaxHealth() {
        return maximumHealth;
    }
    @Override
    protected void firstPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting) {
        boolean isAllDead = true;
        int i = 0;
        Unit unitToHeal = null;

        while (i < allyWaiting.size()) {
            if (!allyWaiting.get(i).isDead) {
                isAllDead = false;
                break;
            }
            i++;
        }

        if (isAllDead) {
            System.out.println("Nothing to do, everybody died...");
        } else if (this.health == level + 2) {
            int k = 0;
            do {
                unitToHeal = chooseRandomUnit(allyWaiting);
                k++;
            } while ((unitToHeal.health == unitToHeal.getMaxHealth())
                    && k < 6);

            if (k == 6) {
                System.out.println("Everybody is at full HP, doing nothing.");
            } else if (unitToHeal != null) {
                if (!this.isDead && !unitToHeal.isDead) {
                    unitToHeal.heal(level);
                    System.out.println("Healer healed " + unitToHeal.getInfo() + " by " + level);
                } else {
                    System.out.println("Healer does nothing due to being dead.");
                }
            }
        } else {
            heal(level);
            System.out.println("Healer healed self by " + level);
        }
    }
    @Override
    protected void secondPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting) {
        int damageAmount = getAttack();
        arenaOpponent.damage(damageAmount);
        System.out.printf("%s damaged arena opponent %s by %d\n", this.getInfo(), arenaOpponent.getInfo(),
                damageAmount);
        if (arenaOpponent.isDead) {
            System.out.println("Healer killed " + arenaOpponent.getInfo());
        }
    }
    @Override
    protected void thirdPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting) {
        Unit unitToRevive = chooseRandomUnit(allyWaiting);
        boolean isAllAlive = true;
        int i = 0;

        while (i < allyWaiting.size()) {
            if (allyWaiting.get(i).isDead) {
                isAllAlive = false;
                break;
            }
            i++;
        }

        if (isAllAlive) {
            System.out.println("Healer can do nothing as everybody at full HP");
        }
        else{
            int m = allyWaiting.size();
            do{
                unitToRevive.chooseRandomUnit(allyWaiting);
                m--;
            }while(!unitToRevive.isDead && m > 0);
        unitToRevive.revive();
        System.out.println("Healer revived " + unitToRevive.getInfo());
        
        }

        
        

    }
}

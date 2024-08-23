import java.util.ArrayList;
import java.util.Random;

public abstract class Unit{

    protected Random rand = new Random();
    protected String name;
    protected int health;
    protected int maximumHealth;
    protected int attackPoints;
    protected int level;
    protected boolean isDead;


    public Unit(String name,  int level, int health){
        this.name = name;
        this.level = level;
        this.health = health;
    }

    protected void setLevelUporDown(int level){
        maximumHealth += level;
        
        attackPoints+= level;
    }
    

    protected void damage(int damageAmount){
            health -= damageAmount;
            if(health <= 0){
                this.health = 0;
                
                isDead = true;
                
            }
    }
    protected void increaseLevel(){
        level += 1;
    }
    protected void decreaseLevel(){
        level-= 1;
    }
    protected void revive(){
        if(isDead){
            health = getMaxHealth();
            if(level > 1){level -= 1;setLevelUporDown(-1);}
            
        }
        isDead = false;
    }
    protected void heal(int healAmount){
            
        health += healAmount;
        if(health > getMaxHealth()){
                health = getMaxHealth();
        }
    }
    protected  String getInfo(){
        return name;
    }

    protected Unit chooseRandomUnit(ArrayList<Unit> Units) {
        if (Units.isEmpty()) {
            return null;
        }
        
        int randomIndex = rand.nextInt(Units.size());
        Unit chosen = Units.get(randomIndex);
        return chosen;
    }
    

    protected abstract int getAttack();
    protected abstract int getMaxHealth();
    protected abstract void firstPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting);
    protected abstract void secondPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting);
    protected abstract void thirdPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting);
}

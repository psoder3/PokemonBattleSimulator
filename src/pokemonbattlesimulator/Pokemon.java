package pokemonbattlesimulator;

import java.util.ArrayList;

public class Pokemon {
    String name;
    String imageURL;
    String type;
    int max_health;
    int current_health;
    int level;
    int speed;
    int attack;
    int id;
    
    ArrayList<Move> moves;
    
    public Pokemon(int id, String name, String imageURL, String type, int max_health, int health, int level, int speed, int attack, ArrayList<Move> moves)
    {
        this.id = id;
        this.name = name;
        this.imageURL = imageURL;
        this.type = type;
        this.max_health = max_health;
        this.current_health = health;
        this.level = level;
        this.speed = speed;
        this.attack = attack;
        this.moves = moves;
    }
    
    public void heal()
    {
        this.current_health = this.max_health;
    }
    
}

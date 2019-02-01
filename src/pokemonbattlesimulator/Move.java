package pokemonbattlesimulator;

public class Move {
    String name;
    double accuracy;
    int power;
    
    public Move(String name, double accuracy, int power)
    {
        this.name = name;
        this.accuracy = accuracy;
        this.power = power;
    }
    
    @Override
    public String toString()
    {
        return name;
    }
    
}

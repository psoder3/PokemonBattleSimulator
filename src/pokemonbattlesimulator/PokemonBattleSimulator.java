package pokemonbattlesimulator;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PokemonBattleSimulator {

    Pokemon pokemon1;
    Pokemon pokemon2;
    BattleFrame frame;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){

            public void run(){

                PokemonBattleSimulator battle_sim = new PokemonBattleSimulator();
                try {
                    battle_sim.frame = new BattleFrame(battle_sim);
                } catch (IOException ex) {
                    Logger.getLogger(PokemonBattleSimulator.class.getName()).log(Level.SEVERE, null, ex);
                }
        
            }

        });
    }
    
    public Pokemon importPokemon(Scanner f_reader, int id)
    {
        String name = f_reader.nextLine();
        String imageURL = f_reader.nextLine();
        String type = f_reader.nextLine();
        int hp = Integer.parseInt(f_reader.nextLine());
        int level = Integer.parseInt(f_reader.nextLine());
        int attack = Integer.parseInt(f_reader.nextLine());
        int speed = Integer.parseInt(f_reader.nextLine());
        ArrayList<Move> moves = new ArrayList();
        for (int i = 0; i < 4; i++)
        {
            String move_name = f_reader.nextLine();
            double move_accuracy = Double.parseDouble(f_reader.nextLine());
            int move_power = Integer.parseInt(f_reader.nextLine());

            Move move = new Move(move_name,move_accuracy,move_power);
            moves.add(move);
        }
        return new Pokemon(id, name,imageURL,type,hp,hp,level,attack,speed,moves);
    }
    
    public void importDataFromFile(File file)
    {
        try {
            Scanner f_reader = new Scanner(file);
            //IMPORT POKEMON 1 FROM FILE
            pokemon1 = importPokemon(f_reader,0);
            //IMPORT POKEMON 2 FROM FILE
            pokemon2 = importPokemon(f_reader,1);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PokemonBattleSimulator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void runManyBattles()
    {
        int total_battles = 10000;
        int p1_battles_won = 0;
        int p2_battles_won = 0;
        
        for (int i = 0; i < total_battles; i++)
        {
            if (battle() == 1)
            {
                p1_battles_won++;
            }
            else
            {
                p2_battles_won++;
            }
            pokemon1.heal();
            pokemon2.heal();
        }
        
        double p1WinPercentage = (p1_battles_won*1.0 / total_battles) * 100;
        double p2WinPercentage = (p2_battles_won*1.0 / total_battles) * 100;
        
        DecimalFormat df = new DecimalFormat("#.00");
        
        String result_text = "After " + total_battles + " battles, here are the win percentages:\n";
        result_text += pokemon1.name + ": " + df.format(p1WinPercentage) + "%\n";
        result_text += pokemon2.name + ": " + df.format(p2WinPercentage) + "%";
        frame.outputPane.setText(result_text);
    }
    
 
    private int battle()
    {
        
        while (pokemon1.current_health > 0 && pokemon2.current_health > 0)
        {
            eachAttack();            
        }
        
        // One of the pokemon has fainted
        if (pokemon1.current_health <= 0)
        {
            //System.out.println(pokemon1.name + " has fainted. There were " + turn_counter + " turns in the battle");
            // return the value 2, indicating that pokemon 2 is the winner
            return 2;
        }
        else
        {
            //System.out.println(pokemon2.name + " has fainted. There were " + turn_counter + " turns in the battle");
            // return the value 1, indicating that pokemon 1 is the winner
            return 1;
        }
        
    }
    
    public void eachAttack()
    {
        // set move dropdowns to random
        int r1 = (int)(Math.random()*4);
        int r2 = (int)(Math.random()*4);
        frame.p1Combo.setSelectedIndex(r1);
        frame.p2Combo.setSelectedIndex(r2);
        
        // Determine who goes first
        int index_goes_first = checkWhoGoesFirst();
        Pokemon p1;
        Pokemon p2;
        if (index_goes_first == 1)
        {
            // p1 goes first
            p1 = pokemon1;
            p2 = pokemon2;
        }
        else
        {
            // p2 goes first
            p1 = pokemon2;
            p2 = pokemon1;
        }

        int damage_done = attack(p1,p2);
        p2.current_health -= damage_done;
        if (pokemon1.current_health <= 0 || pokemon2.current_health <= 0)
        {
            return;
        }
        damage_done = attack(p2,p1);
        p1.current_health -= damage_done;
    }
    
    
    
    // Students must code this function
    public int attack(Pokemon p1, Pokemon p2)
    {
        // return the amount of damage p1 does to p2
        // p1 could be pokemon1 or pokemon2,
        // it is just referring to whoever is attacking
        // currently
        int damage_dealt = 0;
        
        
        
        
        return damage_dealt;
    }
    
    
    
    // Students must code this function
    public int checkWhoGoesFirst()
    {
        // return 1 if Pokemon 1 attacks first
        // return 2 if Pokemon 2 attacks first
        
        
        
        
        return 1;
    }
    
    
    
    
    
}

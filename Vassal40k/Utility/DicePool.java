package Vassal40k.Utility;

import java.util.ArrayList;
import java.util.Random;

public class DicePool
{
    protected static Random rng = new Random();
    
    private ArrayList<Dice> dice = new ArrayList<Dice>();
    private int dieSides = 6;
    
    public DicePool(int numberOfDice)
    {
        for (int i = 0; i < numberOfDice; i++)
            dice.add(new Dice(dieSides));
    }
    
    public int[] Roll()
    {
        ArrayList<Integer> rolls = new ArrayList<Integer>();
        for (int i = 0; i < dice.size(); i++)
        {
            if (!dice.get(i).reRolled)
            {
                rolls.add(dice.get(i).Roll());
            }
        }
        
        int[] result = new int[rolls.size()];
        for (int i = 0; i < rolls.size(); i++)
            result[i] = rolls.get(i);
        return result;
    }
    
    public int GetSuccesses(int targetNumber)
    {
        int result = 0;
        for (Dice d : dice)
        {
            if (d.latestResult >= targetNumber)
                result++;
        }
        return result;
    }
    
    public int GetFailures(int targetNumber)
    {
        int result = 0;
        for (Dice d : dice)
        {
            if (d.latestResult < targetNumber)
                result++;
        }
        return result;
    }
    
    private class Dice
    {
        private int sides = 0;
        public int initialResult = 0;
        public int rerolledResult = 0;
        public int latestResult = 0;
        public boolean rolled = false;
        public boolean reRolled = false;
        
        public Dice(int _sides)
        {
            sides = _sides;
        }
        
        public int Roll()
        {
            if (!rolled)
            {
                initialResult = (int)(rng.nextFloat() * sides + 1.0F);
                latestResult = initialResult;
                rolled = true;
            }
            else if (!reRolled)
            {
                rerolledResult = (int)(rng.nextFloat() * sides + 1.0F);
                latestResult = rerolledResult;
                reRolled = true;
            }
            
            return latestResult;
        }
    }
}
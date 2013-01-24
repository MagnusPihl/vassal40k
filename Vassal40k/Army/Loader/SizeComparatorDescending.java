package Vassal40k.Army.Loader;

import Vassal40k.Army.Loader.Converters.Box;
import java.util.Comparator;

public class SizeComparatorDescending implements Comparator<Box>
{
    @Override
    public int compare(Box box1, Box box2)
    {
        return box2.getArea() - box1.getArea();   //Descending order, make it box1-box2 to go ascending
    }
}
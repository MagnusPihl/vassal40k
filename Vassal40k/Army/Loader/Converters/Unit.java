package Vassal40k.Army.Loader.Converters;

import java.util.ArrayList;

public class Unit implements Box
{
    private static final int PADDING = 20;
    
    public ArrayList<AbstractModel> Models = new ArrayList<AbstractModel>();

    private int x = 0;
    @Override
    public int getX() { return x; }
    @Override
    public void setX(int _x) { x = _x; }

    private int y = 0;
    @Override
    public int getY() { return y; }
    @Override
    public void setY(int _y) { y = _y; }
    
    public void applyOffset()
    {
        for (AbstractModel model : Models)
        {
            model.setX(model.getX() + x);
            model.setY(model.getY() + y);
        }
    }

    @Override
    public int getWidth()
    {
        int rightmost = 0;
        for (AbstractModel model : Models)
        {
            int r = model.getX() + model.getWidth();
            if (r > rightmost)
                rightmost = r;
        }
        return rightmost - getX() + PADDING;
    }
    @Override
    public int getHeight()
    {
        int lowest = 0;
        for (AbstractModel model : Models)
        {
            int l = model.getY() + model.getHeight();
            if (l > lowest)
                lowest = l;
        }
        return lowest - getY() + PADDING;
    }
    @Override
    public int getArea() { return getWidth() * getHeight(); }
}
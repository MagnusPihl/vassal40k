package Vassal40k.Army.Loader.Converters;

import VASSAL.counters.GamePiece;
import java.awt.Point;

public abstract class AbstractModel implements Box
{
    protected abstract GamePiece buildGamePiece();
    
    public abstract String getVassalID();
    public abstract String getOriginalID();
    
    protected String type;
    protected String state;
    
    private GamePiece gamePiece = null;
    public GamePiece getGamePiece()
    {
        if (gamePiece == null)
            gamePiece = buildGamePiece();
        return gamePiece;
    }
    
    @Override
    public int getX() { return getGamePiece().getPosition().x; }
    @Override
    public void setX(int x)
    {
        Point p = new Point(x, getGamePiece().getPosition().y);
        getGamePiece().setPosition(p);
    }
    @Override
    public int getY() { return getGamePiece().getPosition().y; }
    @Override
    public void setY(int y)
    {
        Point p = new Point(getGamePiece().getPosition().x, y);
        getGamePiece().setPosition(p);
    }
    int width = -1;
    @Override
    public int getWidth()
    {
        if (width < 0)
            width = getGamePiece().boundingBox().width;
        return width;
        //return (int) (getGamePiece().getShape().getBounds().width * 1.5);
    }
    int height = -1;
    @Override
    public int getHeight()
    {
        if (height < 0)
            height = getGamePiece().boundingBox().height;
        return height;
        //return (int) (getGamePiece().getShape().getBounds().height * 1.5);
    }
    @Override
    public int getArea() { return getWidth() * getHeight(); }
}
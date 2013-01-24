package Vassal40k.Army.Loader.Converters;

import VASSAL.build.GameModule;
import VASSAL.counters.GamePiece;

public class Vassal40kModel extends AbstractModel
{
    public Vassal40kModel(String _type, String _state, String _posX, String _posY)
    {
        type = _type;
        state = _state;
        setX(Integer.parseInt(_posX));
        setY(Integer.parseInt(_posY));
    }
    
    @Override
    public String getVassalID()
    {
        return "";
    }

    @Override
    public String getOriginalID()
    {
        return "";
    }
    
    @Override
    protected GamePiece buildGamePiece()
    {
        GamePiece gp = GameModule.getGameModule().createPiece(type);
        gp.setState(state);
        return gp;
    }
}

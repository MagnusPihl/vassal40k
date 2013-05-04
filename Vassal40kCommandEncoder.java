import VASSAL.build.module.BasicCommandEncoder;
import VASSAL.counters.Decorator;
import VASSAL.counters.GamePiece;

public class Vassal40kCommandEncoder extends BasicCommandEncoder
{
    @Override
    public Decorator createDecorator(String type, GamePiece inner)
    {
        return createFromReflection(type, inner);
    }
    
    protected Decorator createFromReflection(String type, GamePiece inner)
    {
        try
        {
            String className = type.substring(0, Math.min(type.indexOf(";"), type.indexOf(",")));
            Class c = Class.forName("Vassal40k.Traits." + className);
            if (c != null)
                return (Decorator)c.getConstructor(String.class, GamePiece.class).newInstance(type, inner);
            else
                return super.createDecorator(type, inner);
        }
        catch (Exception ex)
        {
            return super.createDecorator(type, inner);
        }
    }
}
package Traits;

import VASSAL.build.GameModule;
import VASSAL.build.module.Chatter;
import VASSAL.build.module.documentation.HelpFile;
import VASSAL.command.ChangeTracker;
import VASSAL.command.Command;
import VASSAL.counters.Decorator;
import VASSAL.counters.GamePiece;
import VASSAL.counters.KeyCommand;
import VASSAL.counters.PieceEditor;
import VASSAL.i18n.TranslatablePiece;
import VASSAL.tools.SequenceEncoder;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.Box;
import javax.swing.KeyStroke;

public class TheRedThirst extends Decorator implements TranslatablePiece
{
    public static final String ID = "TheRedThirst;";
    protected static boolean defaultHasRolled = false;
    protected static boolean defaultHasRedThirst = false;
    protected boolean hasRolled = false;
    protected boolean hasRedThirst = false;
    protected KeyCommand[] keyCommand = null;
    
    public TheRedThirst()
    {
        this(ID + Boolean.toString(defaultHasRolled) + "," + Boolean.toString(defaultHasRedThirst), null);
    }
    public TheRedThirst(String type, GamePiece inner)
    {
        mySetType(type);
        setInner(inner);
    }
    
    @Override
    public PieceEditor getEditor()
    {
        return new TraitEditor();
    }
    
    public static void WriteLine(String output)
    {
        Command displayText = new Chatter.DisplayText(GameModule.getGameModule().getChatter(), output);
        
        displayText.execute();
        GameModule.getGameModule().sendAndLog(displayText);
    }
    
    @Override
    public void mySetState(String string)
    {
        WriteLine("mySetState: " + string);
        SequenceEncoder.Decoder decoder = new SequenceEncoder.Decoder(string, ',');
        hasRolled = decoder.nextBoolean(hasRolled);
        hasRedThirst = decoder.nextBoolean(hasRedThirst);
    }

    @Override
    public String myGetState()
    {
        SequenceEncoder encoder = new SequenceEncoder(',');
        encoder.append(hasRolled);
        encoder.append(hasRedThirst);
        WriteLine("myGetState: " + encoder.getValue());
        return encoder.getValue();
    }

    @Override
    public Object getLocalizedProperty (Object key)
    {
        return getProperty(key);
    }

    @Override
    public Object getProperty (Object key)
    {
        if (key.equals("hasRolled"))
        {
            return hasRolled;
        }
        else if (key.equals("hasRedThirst"))
        {
            return hasRedThirst;
        }
        else
        {
            return super.getProperty(key);
        }
    }

    public List<String> getPropertyNames()
    {
        ArrayList<String> l = new ArrayList<>();
        l.add("hasRolled");
        l.add("hasRedThirst");
        return l;
    }

    @Override
    public String myGetType()
    {
        return ID + myGetState();
    }
    
    @Override
    public void mySetType(String type)
    {
        type = type.substring(ID.length());
        mySetState(type);
    }

    @Override
    protected KeyCommand[] myGetKeyCommands()
    {
        if (keyCommand == null)
        {
            keyCommand = new KeyCommand[1];
            keyCommand[0] = new KeyCommand("The Red Thirst", KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK), this);
        }
        return keyCommand;
    }

    @Override
    public Command myKeyEvent(KeyStroke ks)
    {
        if (!hasRolled && keyCommand[0].matches(ks))
        {
            final ChangeTracker ct = new ChangeTracker(this);

            Random rng = new Random();
            int dice = (int)(rng.nextFloat() * 6 + 1.0F);
            if (dice == 6)
            {
                hasRedThirst = true;
                WriteLine("* (" + dice + "): " + getName() + " has The Red Thirst!");
                keyCommand[0] = new KeyCommand("Has the Red Thirst", null, this);
            }
            else
            {
                hasRedThirst = false;
                WriteLine("* (" + dice + "): " + getName() + " does NOT have The Red Thirst!");
                keyCommand[0] = new KeyCommand("Does not have the Red Thirst", null, this);
            }
            hasRolled = true;

            return ct.getChangeCommand();
        }
        return null;
    }

    @Override
    public void draw(Graphics grphcs, int i, int i1, Component cmpnt, double d)
    {
        piece.draw(grphcs, i, i1, cmpnt, d);
    }

    @Override
    public Rectangle boundingBox() {
        return piece.boundingBox();
    }

    @Override
    public Shape getShape() {
        return piece.getShape();
    }

    @Override
    public String getName() {
        return piece.getName();// "The Red Thirst";
    }
    
    @Override
    public String getDescription()
    {
        return "The Red Thirst";
    }

    @Override
    public HelpFile getHelpFile ()
    {
        return null;
    }
    
    public class TraitEditor implements PieceEditor
    {
        private Box box;
        
        public TraitEditor()
        {
            box = Box.createVerticalBox();
        }

        @Override
        public Component getControls ()
        {
            return box;
        }

        @Override
        public String getType ()
        {
            return ID + ";";
        }

        @Override
        public String getState ()
        {
            return "";
        }   
    }
}
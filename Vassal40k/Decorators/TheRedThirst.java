//package Vassal40k.Decorators;

import VASSAL.build.GameModule;
import VASSAL.build.module.Chatter;
import VASSAL.command.Command;
import VASSAL.counters.Decorator;
import VASSAL.counters.KeyCommand;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;

public class TheRedThirst extends Decorator
{
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
    }

    @Override
    public String myGetState()
    {
        WriteLine("myGetState");
        return "RedThirstState";
    }

    @Override
    public String myGetType()
    {
        return "";
    }

    @Override
    protected KeyCommand[] myGetKeyCommands()
    {
        KeyCommand[] cmds = new KeyCommand[1];
        cmds[0] = new KeyCommand("Roll", KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK), this);
        return cmds;
    }

    @Override
    public Command myKeyEvent(KeyStroke ks)
    {
        Command cmd = new Chatter.DisplayText(GameModule.getGameModule().getChatter(), "myKeyEvent");
        return cmd;
    }

    @Override
    public void draw(Graphics grphcs, int i, int i1, Component cmpnt, double d)
    {
    }

    @Override
    public Rectangle boundingBox() {
        return new Rectangle(1, 1);
    }

    @Override
    public Shape getShape() {
        return new Rectangle(1, 1);
    }

    @Override
    public String getName() {
        return "The Red Thirst";
    }
    
}
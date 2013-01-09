package Vassal40k.Buttons;

import VASSAL.build.AbstractConfigurable;
import VASSAL.build.AutoConfigurable;
import VASSAL.build.Buildable;
import VASSAL.build.GameModule;
import VASSAL.build.module.documentation.HelpFile;
import VASSAL.build.module.properties.MutablePropertiesContainer;
import VASSAL.build.module.properties.MutableProperty;
import VASSAL.configure.AutoConfigurer;
import VASSAL.configure.Configurer;
import VASSAL.configure.ConfigurerFactory;
import VASSAL.configure.ConfigurerWindow;
import VASSAL.configure.IconConfigurer;
import VASSAL.tools.LaunchButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.swing.KeyStroke;

public abstract class BaseButton extends AbstractConfigurable
{
    protected class ButtonAttribute
    {
        public String name;
        public String description;
        public Class<?> type;
        public ButtonAttributeMethods methods;

        public ButtonAttribute(String _name, String _description, Class<?> _type, ButtonAttributeMethods _methods)
        {
            name = _name;
            description = _description;
            type = _type;
            methods = _methods;
        }

        public String getAttributeString() { return "";}
        public void setAttribute(Object o) {}
    }
    protected interface ButtonAttributeMethods
    {
        public String getter();
        public void setter(Object o);
    }

    private List<ButtonAttribute> attributes = new ArrayList<ButtonAttribute>();
    private String[] attributeNamesCache = null;
    private String[] attributeDescriptionsCache = null;
    private Class<?>[] attributeTypesCache = null;
    protected void AddAttribute(String _name, String _description, Class<?> _type, ButtonAttributeMethods methods)
    {
        attributes.add(new ButtonAttribute(_name, _description, _type, methods));
    }

    protected abstract void OnClick();

    protected LaunchButton launchButton;
    protected String buttonTooltip = "";
    protected Random rng;
    protected MutableProperty.Impl property = new MutableProperty.Impl("", this);

    protected void Initialize(String name, String text, String tooltip, boolean showDialog)
    {
        final BaseButton THIS = this;
        final boolean SHOW_DIALOG = showDialog;
        ActionListener rollAction = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent evt)
            {
                if (SHOW_DIALOG)
                {
                    AutoConfigurer ac = new AutoConfigurer(THIS);
                    ConfigurerWindow w = new ConfigurerWindow(ac, true);

                    ac.getConfigurer("name").getControls().getParent().remove(ac.getConfigurer("name").getControls());
                    ac.getConfigurer("text").getControls().getParent().remove(ac.getConfigurer("text").getControls());
                    ac.getConfigurer("icon").getControls().getParent().remove(ac.getConfigurer("icon").getControls());
                    ac.getConfigurer("tooltip").getControls().getParent().remove(ac.getConfigurer("tooltip").getControls());
                    ac.getConfigurer("hotkey").getControls().getParent().remove(ac.getConfigurer("hotkey").getControls());

                    w.pack();
                    w.setLocationRelativeTo(launchButton.getTopLevelAncestor());
                    w.setVisible(true);
                    
                    if (!w.isCancelled())
                        OnClick();
                }
                else
                {
                    OnClick();
                }
            }
        };

        launchButton = new LaunchButton(null, "tooltip", "text", "hotkey", "icon", rollAction);
        
        AddAttribute("name", "Name: ", String.class, new ButtonAttributeMethods()
        {
            @Override public String getter() { return getConfigureName(); }
            @Override public void setter(Object o)
            {
                setConfigureName((String) o);
                property.setPropertyName(getConfigureName() + "_result");
                launchButton.setToolTipText((String) o);
            }
        });
        AddAttribute("text", "Text: ", String.class, new ButtonAttributeMethods()
        {
            @Override public String getter() { return launchButton.getAttributeValueString("text"); }
            @Override public void setter(Object o)
            {
                launchButton.setAttribute("text", o);
            }
        });
        AddAttribute("icon", "Icon: ", IconConfig.class, new ButtonAttributeMethods()
        {
            @Override public String getter() { return launchButton.getAttributeValueString("icon"); }
            @Override public void setter(Object o)
            {
                launchButton.setAttribute("icon", o);
            }
        });
        AddAttribute("tooltip", "Tooltip: ", String.class, new ButtonAttributeMethods()
        {
            @Override public String getter() { return buttonTooltip.length() == 0 ? launchButton.getAttributeValueString("text") : buttonTooltip; }
            @Override public void setter(Object o)
            {
                buttonTooltip = ((String) o);
                launchButton.setAttribute("tooltip", o);
            }
        });
        AddAttribute("hotkey", "Hotkey: ", KeyStroke.class, new ButtonAttributeMethods()
        {
            @Override public String getter() { return launchButton.getAttributeValueString("hotkey"); }
            @Override public void setter(Object o)
            {
                launchButton.setAttribute("hotkey", o);
            }
        });
        
        setAttribute("name", name);
        setAttribute("text", text);
        launchButton.setAttribute("tooltip", tooltip);
    }

    @Override
    public String[] getAttributeNames ()
    {
        if (attributeNamesCache == null || attributeNamesCache.length != attributes.size())
        {
            attributeNamesCache = new String[attributes.size()];
            for (int i = 0; i < attributes.size(); i++)
            {
                attributeNamesCache[i] = attributes.get(i).name;
            }
        }
        return attributeNamesCache;
    }

    @Override
    public String[] getAttributeDescriptions ()
    {
        if (attributeDescriptionsCache == null || attributeDescriptionsCache.length != attributes.size())
        {
            attributeDescriptionsCache = new String[attributes.size()];
            for (int i = 0; i < attributes.size(); i++)
            {
                attributeDescriptionsCache[i] = attributes.get(i).description;
            }
        }
        return attributeDescriptionsCache;
    }

    @Override
    public Class<?>[] getAttributeTypes ()
    {
        if (attributeTypesCache == null || attributeTypesCache.length != attributes.size())
        {
            attributeTypesCache = new Class<?>[attributes.size()];
            for (int i = 0; i < attributes.size(); i++)
            {
                attributeTypesCache[i] = attributes.get(i).type;
            }
        }
        return attributeTypesCache;
    }

    @Override
    public String getAttributeValueString (String key)
    {
        for (int i = 0; i < attributes.size(); i++)
        {
            if (attributes.get(i).name.equals(key))
            {
                return attributes.get(i).methods.getter();
            }
        }

        return launchButton.getAttributeValueString(key);
    }

    @Override
    public void setAttribute (String key, Object o)
    {
        if (key.equals("label"))
        {
            setAttribute("name", o);
            setAttribute("text", o);
            return;
        }
        else
        {
            for (int i = 0; i < attributes.size(); i++)
            {
                if (attributes.get(i).name.equals(key))
                {
                    attributes.get(i).methods.setter(o);
                    return;
                }
            }
        }

        //Only if the others fail...
        launchButton.setAttribute(key, o);
    }

    @Override
    public void addTo (Buildable parent)
    {
        rng = GameModule.getGameModule().getRNG();
        GameModule.getGameModule().getToolBar().add(launchButton);
        property.setPropertyValue("1");
        property.addTo((MutablePropertiesContainer) parent);
    }

    @Override
    public void removeFrom (Buildable bldbl)
    {
        GameModule.getGameModule().getToolBar().remove(launchButton);
        GameModule.getGameModule().getToolBar().revalidate();
    }

    @Override
    public HelpFile getHelpFile ()
    {
        return null;
    }

    @Override
    public Class[] getAllowableConfigureComponents ()
    {
        return new Class[0];
    }

    public int DiceRoll(int sides)
    {
        return (int)(rng.nextFloat() * sides + 1.0F);
    }
    
    public int[] DiceRoll(int sides, int dice)
    {
        int[] rolls = new int[dice];
        for(int i = 0; i < dice; i++)
            rolls[i] = DiceRoll(sides);
        Arrays.sort(rolls);
        return rolls;
    }

    public String CommaSeparateIntegers(int[] input)
    {
        if (input.length == 0)
            return "";

        String output = input[0] + "";
        for (int i = 1; i < input.length; i++)
            output += ", " + input[i];

        return output;
    }
    
    public int NumberOfSuccesses(int[] rolls, int minimumRequired)
    {
        int count = 0;
        for (int roll : rolls)
            if (roll >= minimumRequired)
                count++;
        return count;
    }
    public int NumberOfFailures(int[] rolls, int minimumRequired)
    {
        return rolls.length - NumberOfSuccesses(rolls, minimumRequired);
    }

    public String Pluralize(String word, int count)
    {
        if (count == 1)
            return word;
        else if (word.equals("inch"))
            return "inches";
        else
            return word + "s";
    }

    public String PluralizeIsAre (int count)
    {
        if (count == 1)
            return "is";
        else
            return "are";
    }
    
    public static class IconConfig implements ConfigurerFactory
    {
        @Override
        public Configurer getConfigurer(AutoConfigurable c, String key, String name)
        {
            return new IconConfigurer(key, name, "/images/die.gif");
        }
    }
}
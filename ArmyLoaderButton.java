import javax.swing.JFileChooser;

public class ArmyLoaderButton extends BaseButton
{
    public ArmyLoaderButton()
    {
        Initialize("Load Army", "Load Army", "Load Army", false);
    }
    
    @Override
    protected void OnClick ()
    {
        JFileChooser fileDlg = new JFileChooser();
        fileDlg.showOpenDialog(launchButton);
    }
}
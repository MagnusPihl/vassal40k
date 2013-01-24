package Vassal40k.Army;

import VASSAL.build.module.Map;
import VASSAL.build.module.PlayerRoster;
import Vassal40k.Army.Loader.RosterLoader;
import Vassal40k.Buttons.BaseButton;
import Vassal40k.Utility.Chatbox;
import javax.swing.JFileChooser;

public class LoadArmyButton extends BaseButton
{
    public LoadArmyButton()
    {
        Initialize("Load Army", "Load Army", "Load Army", false);
    }

    @Override
    protected void OnClick ()
    {
        /*
        for (Map map : Map.getMapList())
        {
            List<GamePiece> gamePieces = new ArrayList<GamePiece>();
            SaveArmyButton.GetGamePieces(gamePieces, map.getAllPieces());
            if (gamePieces.size() == 0)
                continue;
            Chatbox.WriteLine(map.getMapName());
            Chatbox.WriteLine("-----------");
            for (GamePiece gp : gamePieces)
            {
                Chatbox.WriteLine(" -- " + gp.getName() + " -- ");
                Chatbox.WriteLine(("-Type-"));
                Chatbox.WriteLine(gp.getType());
                Chatbox.WriteLine(("-State-"));
                Chatbox.WriteLine(gp.getState());
                Chatbox.WriteLine("");
            }
            Chatbox.WriteLine("-----------");
            Chatbox.WriteLine("");
            Chatbox.WriteLine("");
        }
        */
        
        JFileChooser fileDlg = new JFileChooser();
        fileDlg.setFileFilter(new ArmyFileFilter());
        if (fileDlg.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
            return;
        
        String side = PlayerRoster.getMySide();
        for (Map map : Map.getMapList())
        {
            if (map.getMapName().equals(side))
            {
                RosterLoader.Load(fileDlg.getSelectedFile(), map);
                
                return;
            }
        }
        Chatbox.WriteLine("It is not possible to load armies for " + side + ". Re-join the game as Player 1 or Player 2.");
    }
    
}
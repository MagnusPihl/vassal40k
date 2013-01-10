package Vassal40k.Army;

import Vassal40k.Buttons.BaseButton;
import VASSAL.build.GameModule;
import VASSAL.build.module.ChartWindow;
import VASSAL.build.module.Map;
import VASSAL.build.module.PlayerRoster;
import VASSAL.build.widget.MapWidget;
import VASSAL.build.widget.PieceSlot;
import VASSAL.command.AddPiece;
import VASSAL.command.Command;
import VASSAL.counters.BasicPiece;
import VASSAL.counters.GamePiece;
import java.util.Collection;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ArmyLoaderButton extends BaseButton
{
    public ArmyLoaderButton()
    {
        Initialize("Load Army", "Load Army", "Load Army", false);
    }
    
    @Override
    protected void OnClick ()
    {
        List<Map> maps = Map.getMapList();
        //JFileChooser fileDlg = new JFileChooser();
        //fileDlg.showOpenDialog(launchButton);
        //String side = PlayerRoster.getMySide();
        //GamePiece gp = GameModule.getGameModule().getGameState().getPieceForId("3261");
        /*if (gp == null)
            gp = GameModule.getGameModule().createPiece("3261");
        if (gp == null)
            gp = GameModule.getGameModule().createPiece(BasicPiece.ID + ";;3261;;");
        if (gp == null)
            gp = GameModule.getGameModule().createPiece("End Torpedo");*/
        //if (gp == null)
        //    gp = GameModule.getGameModule().createPiece(BasicPiece.ID + ";;SM_CHAR_Calgar.png;;");
        
        /*
        
        Collection<GamePiece> gamePieces = GameModule.getGameModule().getGameState().getAllPieces();
        for (GamePiece piece : gamePieces)
        {
            //if (piece.getId().contains("1212"))
                MessageBox(piece.getName());
        }*/
        /*
        PieceSlot slot = new PieceSlot();
        slot.setGpId("3261");
        gp = slot.getPiece();
        
        if (gp == null)
            MessageBox("gp is null");
        else
        {
            MessageBox(gp.getName());

            //Command cmd = new AddPiece(gp);
            //cmd.execute();
            maps.get(0).addPiece(gp);
            //GameModule.getGameModule().sendAndLog(cmd);
        }*/
    }
    
    public static void MessageBox(String message)
    {
        JOptionPane.showMessageDialog(null, message, "Message Box", JOptionPane.INFORMATION_MESSAGE);
    }
}
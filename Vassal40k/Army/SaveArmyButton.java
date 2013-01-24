package Vassal40k.Army;

import VASSAL.build.module.Map;
import VASSAL.build.module.PlayerRoster;
import VASSAL.counters.GamePiece;
import VASSAL.counters.Stack;
import Vassal40k.Army.Saver.Vassal40kRosterSaver;
import Vassal40k.Buttons.BaseButton;
import Vassal40k.Utility.Chatbox;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class SaveArmyButton extends BaseButton
{
    public SaveArmyButton()
    {
        Initialize("Save Army", "Save Army", "Save Army", false);
    }
    
    public static void GetGamePieces(List<GamePiece> existing, GamePiece[] newPieces)
    {
        for (GamePiece gp : newPieces)
        {
            Rectangle r = gp.boundingBox();
            if (gp.getClass() == Stack.class)
            {
                Stack stack = (Stack)gp;
                GamePiece[] stackContents = new GamePiece[stack.getPieceCount()];
                for (int i = 0; i < stackContents.length; i++)
                    stackContents[i] = stack.getPieceAt(i);
                GetGamePieces(existing, stackContents);
            }
            else
            {
                existing.add(gp);
            }
        }
    }
    
    @Override
    protected void OnClick ()
    {
        String side = PlayerRoster.getMySide();
        for (Map map : Map.getMapList())
        {
            if (map.getMapName().equals(side))
            {
                List<GamePiece> gamePieces = new ArrayList<>();
                GetGamePieces(gamePieces, map.getAllPieces());
                String xml = Vassal40kRosterSaver.Save(gamePieces);
                if (xml == null)
                {
                    Chatbox.WriteLine("An error occurred while saving " + side + "'s army. Aborting.");
                    return;
                }
                
                JFileChooser fileDlg = new JFileChooser()
                {
                    @Override
                    public void approveSelection()
                    {
                        File f = getSelectedFile();
                        if(f.exists() && getDialogType() == SAVE_DIALOG)
                        {
                            int result = JOptionPane.showConfirmDialog(this,"The file exists, overwrite?","Existing file",JOptionPane.YES_NO_CANCEL_OPTION);
                            switch(result)
                            {
                                case JOptionPane.YES_OPTION:
                                    super.approveSelection();
                                    return;
                                case JOptionPane.NO_OPTION:
                                    return;
                                case JOptionPane.CLOSED_OPTION:
                                    return;
                                case JOptionPane.CANCEL_OPTION:
                                    cancelSelection();
                                    return;
                            }
                        }
                        else
                            super.approveSelection();
                    }
                };
                fileDlg.setFileFilter(new ArmyFileFilter());
                fileDlg.setSelectedFile(new File("MyArmy.v40kArmy"));
                if (fileDlg.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
                {
                    File file = fileDlg.getSelectedFile();
                    if (!file.getPath().toLowerCase().endsWith(".v40karmy"))
                        file = new File(file.getPath() + ".v40kArmy");
                    
                    try
                    {
                        PrintWriter outFile = new PrintWriter(new FileWriter(file));
                        outFile.println(xml);
                        outFile.close();
                        
                        Chatbox.WriteLine("Succesfully saved army for " + side + " to " + file.getName() + ".");
                    }
                    catch(Exception ex)
                    {}
                }
                
                return;
            }
        }
        Chatbox.WriteLine("It is not possible to save armies for " + side + ". Re-join the game as Player 1 or Player 2.");
    }
}
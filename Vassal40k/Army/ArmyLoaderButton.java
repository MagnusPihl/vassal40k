package Vassal40k.Army;

import Vassal40k.Buttons.BaseButton;
import VASSAL.build.GameModule;
import VASSAL.build.module.ChartWindow;
import VASSAL.build.module.Map;
import VASSAL.build.module.PieceWindow;
import VASSAL.build.module.PlayerRoster;
import VASSAL.build.widget.MapWidget;
import VASSAL.build.widget.PieceSlot;
import VASSAL.command.AddPiece;
import VASSAL.command.Command;
import VASSAL.counters.BasicPiece;
import VASSAL.counters.Clone;
import VASSAL.counters.GamePiece;
import VASSAL.counters.PieceCloner;
import Vassal40k.Utility.Chatbox;
import java.awt.Point;
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
        //String type = "nonRect;m,-6,-14,l,-6,-13,l,-8,-13,l,-8,-12,l,-9,-12,l,-9,-11,l,-10,-11,l,-10,-10,l,-11,-10,l,-11,-9,l,-12,-9,l,-12,-8,l,-13,-8,l,-13,-7,l,-13,-6,l,-14,-6,l,-14,-5,l,-14,-4,l,-14,-3,l,-14,-2,l,-14,-1,l,-14,0,l,-14,1,l,-14,2,l,-14,3,l,-14,4,l,-14,5,l,-14,6,l,-14,7,l,-13,7,l,-13,8,l,-13,9,l,-12,9,l,-12,10,l,-11,10,l,-11,11,l,-10,11,l,-10,12,l,-9,12,l,-9,13,l,-7,13,l,-7,14,l,-5,14,l,-5,15,l,5,15,l,5,14,l,7,14,l,7,13,l,9,13,l,9,12,l,10,12,l,10,11,l,11,11,l,11,10,l,12,10,l,12,9,l,13,9,l,13,8,l,13,7,l,14,7,l,14,6,l,14,5,l,15,5,l,15,4,l,15,3,l,15,2,l,15,1,l,15,0,l,15,-1,l,15,-2,l,15,-3,l,15,-4,l,14,-4,l,14,-5,l,14,-6,l,14,-7,l,13,-7,l,13,-8,l,12,-8,l,12,-9,l,11,-9,l,11,-10,l,11,-11,l,9,-11,l,9,-12,l,8,-12,l,8,-13,l,6,-13,l,6,-14,c	replace;Kill;75,130;+/null/mark\\;Layer\\\\	immob\\;i\\;I\\\\\\	rotate\\;6\\;\\;\\;\\;\\;88,520\\;\\;Rotate\\\\\\\\	emb2\\;\\;0\\;\\;\\;0\\;\\;\\;0\\;\\;\\;\\;1\\;false\\;0\\;0\\;Counter_B1.png,Counter_B2.png,Counter_B3.png\\;,,\\;true\\;\\;88,520\\;Random\\;false\\;\\;1\\\\\\\\\\	clone\\;Clone\\;67,130\\\\\\\\\\\\	delete\\;Delete\\;68,130\\\\\\\\\\\\\\	piece\\;\\;\\;\\;Craters/Terrain\\\\	\\\\\\	0\\\\\\\\	1\\;\\\\\\\\\\	\\\\\\\\\\\\	\\\\\\\\\\\\\\	null\\;36\\;20\\;;null;0;0;false;88,520;Remove as Casualty;;0;false\\	footprint;77,520;Movement Trail;true;true;2;102,0,0;255,0,0;100;0;20;30;1.0\\\\	translate;Move Backward 1\";40,130;0;-25;false;0;0;0;0;Move Backward 1 Inch\\\\\\	translate;Move Forward 1\";38,130;0;25;false;0;0;0;0;Move Forward 1 Inch\\\\\\\\	translate;Move 6\" backwards;40,65;0;-150;true;0;0;0;0;Move 6\" backwards\\\\\\\\\\	translate;Move 6\" forward;38,65;0;150;true;0;0;0;0;Move 6\" forward\\\\\\\\\\\\	mark;Layer\\\\\\\\\\\\\\	delete;Delete;68,130\\\\\\\\\\\\\\\\	clone;Clone;67,130\\\\\\\\\\\\\\\\\\	markmoved;Counter_New Moved Symbol.png;5;5;Mark Moved;77,130\\\\\\\\\\\\\\\\\\\\	label;73,130;Model Information;10;204,204,204;0,0,0;c;8;c;20;b;c;$pieceName$ ($label$);Dialog;0;0;TextLabel;\\\\\\\\\\\\\\\\\\\\\\	rotate;1;39,65;Rotate Freely;;;Rotate Freely\\\\\\\\\\\\\\\\\\\\\\\\	rotate;16;39,130;37,130;Rotate CW;Rotate CCW;;;\\\\\\\\\\\\\\\\\\\\\\\\\\	label;87,130;Wounds;11;204,204,204;0,0,0;c;8;c;-20;b;c;$pieceName$ ($label$);Dialog;1;0;TextLabel;\\\\\\\\\\\\\\\\\\\\\\\\\\\\	emb2;;2;;;2;J;;0;;;;1;false;1;-4; ,SM_UM_marine_veteran_jumppack.png;,;true;Jump pack;;;false;;1;1;true;;74,130;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\	emb2;;2;;;2;;;0;;;;1;false;1;-4;SM_UM_marine_captain.png;;true;Head;;;false;;1;1;true;;;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\	emb2;;2;;;2;;;0;;;;1;false;1;-4;SM_UM_marine_body.png;;true;Body;;;false;;1;1;true;;;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\	emb2;;2;;Right  weapon;2;];;0;;;;1;false;1;-4;SM_UM_marine_r_ccw.png,SM_UM_marine_r_ps.png,SM_UM_marine_r_lc.png,SM_UM_marine_r_th.png,SM_UM_marine_r_pf.png,SM_UM_marine_r_pistol.png,SM_UM_marine_r_plasma_pistol.png,SM_UM_marine_r_rb.png,SM_UM_marine_r_sb.png,SM_UM_marine_r_shield.png,SM_UM_termi_r_cf.png,SM_UM_termi_r_cp.png,SM_UM_termi_r_cm.png;+ - CCW,+ - Power weapon,+ - Lightning Claw,+ - Thunder Hammer,+ - Power Fist,+ - Pistol,+ - Plasma Pistol,+ - Sword,+ - Storm Bolter,+ - Shield,+ - Combi Flamer,+ - Combi Plasma,+ - Combi Melta;true;Right arms;;;false;;1;1;true;;93,130;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\	emb2;;2;;Left  weapon;2;[;;0;;;;1;false;1;-4;SM_UM_marine_l_ccw.png,SM_UM_marine_l_ps.png,SM_UM_marine_l_lc.png,SM_UM_marine_l_th.png,SM_UM_marine_l_pf.png,SM_UM_marine_l_pistol.png,SM_UM_marine_l_plasma_pistol.png,SM_UM_marine_l_rb.png,SM_UM_marine_l_sb.png,SM_UM_marine_l_shield.png;+ - CCW,+ - Power weapon,+ - Lightning Claw,+ - Thunder Hammer,+ - Power Fist,+ - Pistol,+ - Plasma Pistol,+ - Sword,+ - Storm Bolter,+ - Shield;true;Left arms;;;false;;1;1;true;;91,130;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\	emb2;;2;;;2;;;0;;;;1;false;1;-4;SM_UM_marine_master_mantle.png;;true;Mantle;;;false;;1;1;true;;;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\	emb2;;2;;Toggle Slottabase;2;B;;0;;;;1;false;0;0;Base_25mm-green.png,Base_25mm-aqua.png,Base_25mm-black.png,Base_25mm-blue.PNG,Base_25mm-brown.png,Base_25mm-darkgreen.png,Base_25mm-green copy.png,Base_25mm-grey.png,Base_25mm-lime.PNG,Base_25mm-mud.PNG,Base_25mm-orange.png,Base_25mm-pink.png,Base_25mm-purple.png,Base_25mm-red.png,Base_25mm-sand.PNG,Base_25mm-white.png, ;,,,,,,,,,,,,,,,,;true;Model Base;;;false;;1;1;true;;66,130;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\	emb2;24\";8;6;;0;;;0;;;;1;false;0;0;25mm24.PNG;;true;24\";;;false;;1;1;false;54,520;;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\	emb2;18\";8;5;;0;;;0;;;;1;false;0;0;25_18.png;;true;18\";;;false;;1;1;false;53,520;;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\	emb2;12\";8;4;;0;;;0;;;;1;false;0;0;25mm12.PNG;;true;12\";;;false;;1;1;false;52,520;;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\	emb2;6\";8;3;;0;;;0;;;;1;false;0;0;25mm6.PNG;;true;6\";;;false;;1;1;false;51,520;;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\	emb2;2\";8;2;;0;;;0;;;;1;false;0;0;25mm2.PNG;;true;2\";;;false;;1;1;false;50,520;;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\	emb2;1\";8;1;;0;;;0;;;;1;false;0;0;25_1.png;;true;1\";;;false;;1;1;false;49,520;;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\	submenu;Range Circles;1\",2\",6\",12\",18\",24\"\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\	piece;;;DH_GreyKnightTerminator_StormBolter.PNG;Chapter Master";
        
        List<Map> maps = Map.getMapList();
        //GamePiece chapterMaster = GameModule.getGameModule().createPiece(type);
        //maps.get(0).addPiece(chapterMaster);
        //chapterMaster.setPosition(new Point(40,40));
        List<PieceWindow> coll = GameModule.getGameModule().getAllDescendantComponentsOf(PieceWindow.class);
        PieceWindow wnd = coll.get(0);
        MessageBox(wnd.getConfigureName());
        List<PieceSlot> slots = wnd.getAllDescendantComponentsOf(PieceSlot.class);
        GamePiece somePiece = slots.get(0).getPiece();
        maps.get(1).addPiece(somePiece);
        somePiece.setPosition(new Point(40, 40));
        /*GamePiece[] pieces = maps.get(0).getAllPieces();
        for (GamePiece gp : pieces)
        {
            if (gp.getName().contains("Chapter Master"))
            {
                Chatbox.WriteLine("Name: " + gp.getName());
                Chatbox.WriteLine("Localized Name: " + gp.getLocalizedName());
                Chatbox.WriteLine("Type: " + gp.getType());
                Chatbox.WriteLine("Id: " + gp.getId());
                Chatbox.WriteLine("State: " + gp.getState());
                gp.setPosition(new Point(40, 40));
                Clone clone = PieceCloner.getInstance().clonePiece(piece);
                clone.setMap(maps.get(0));
                clone.setPosition(new Point(50, 50));
            }
        }*/
        JFileChooser fileDlg = new JFileChooser();
        fileDlg.showOpenDialog(launchButton);
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
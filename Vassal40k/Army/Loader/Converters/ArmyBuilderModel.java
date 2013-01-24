package Vassal40k.Army.Loader.Converters;

import VASSAL.counters.GamePiece;
import Vassal40k.Army.Loader.RosterLoader;
import Vassal40k.Utility.Chatbox;
import java.util.HashSet;

public class ArmyBuilderModel extends AbstractModel
{
    private String unit = null;
    private HashSet<String> wargear = new HashSet<>();
    
    public ArmyBuilderModel(String unitID, String... wargearIDs)
    {
        unit = unitID;
        for (String wg : wargearIDs)
            AddWargear(wg);
    }
    
    public void AddWargear(String wargearID)
    {
        if (wargearID != null && !wargearID.equals(""))
            wargear.add(wargearID);
    }
    
    @Override
    protected GamePiece buildGamePiece()
    {
        return RosterLoader.GetPieceFromPalette(getVassalID());
    }
    
    @Override
    public String getOriginalID()
    {
        return unit;
    }

    @Override
    public String getVassalID()
    {
        switch (unit)
        {
            /**
            * Ork HQ
            */
            case "orWarboss":
                if (wargear.contains("orWarbike"))
                    return "ORK:134";
                else
                    return "ORK:132";
            case "orWarbossM":
                return "ORK:133";
            case "orBigM":
                if (wargear.contains("orWarbike"))
                    return "ORK:198";
                else
                    return "ORK:135";
            case "orBigMMA":
                return "ORK:136";
            case "orWeird":
                if (wargear.contains("orWarpH"))
                    return "ORK:138";
                else
                    return "ORK:137";
            case "orZogW":
                return "ORK:139";
            case "orGhaz":
                return "ORK:140";
            case "orGrotS":
                return "ORK:141";
            case "orWaz":
                return "ORK:142";
            /**
             * Ork Elite
             */
            case "orNobzMo":
                if (wargear.contains("orWarbike") && wargear.contains("orWaaagh2"))
                    return "ORK:147";
                else if (wargear.contains("orWarbike"))
                    return "ORK:146";
                else if (wargear.contains("orWaaagh2"))
                    return "ORK:144";
                else
                    return "ORK:143";
            case "orPainBN":
                if (wargear.contains("orWarbike"))
                    return "ORK:148";
                else
                    return "ORK:145";
            case "orBurnaB":
                return "ORK:149";
            case "orMekB":
                if (wargear.contains("orGrotOi"))
                    return "ORK:150,ORK:151";
                else
                    return "ORK:150";
            case "orTankB":
                return "ORK:152";   //Missing Tankhammer and Bomb Squig
            case "orTankN":
                return "ORK:154";
            case "orLoota":
                return "ORK:156";
            case "orKommando":
                return "ORK:159";   //Missing Specialist weapon
            case "orKommN":
                return "ORK:161";
            case "orSnikR":
                return "ORK:162";
            case "orMegaN":
                return "ORK:163";
            /**
             * Ork Troops
             */
            case "orBoyz":  //Missing Specialist Weapon
                if (wargear.contains("orShoota"))
                    return "ORK:165";
                else //if (wargear.contains("orChSlug"))    //Not needed, since we need a default
                    return "ORK:164";
            case "orBoyzNSl":
                return "ORK:167";
            case "orArdB":  //Missing Specialist weapon
                if (wargear.contains("orShoota"))
                    return "ORK:169";
                else //if (wargear.contains("orChSlug"))    //Not needed, since we need a default
                    return "ORK:168";
            case "orArdN":
                return "ORK:171";
            case "orGretchin":
                return "ORK:172";
            case "orRuntH":
                return "ORK:173";
            /**
             * Ork Fast Attack
             */
            case "orDeffK":
                return "ORK:174";
            case "orStormB":
                return "ORK:175";
            case "orStormN":
                return "ORK:176";
            case "orZagS":
                return "ORK:177";
            case "orWarBike":
                return "ORK:178";
            case "orBikeN":
                return "ORK:179";
            case "orWarB":
                return "ORK:180";
            case "orWarT":
                return "ORK:181";
            case "orSkorcha":
                return "ORK:182";
            case "orDakkajet":
                return "ORK:203";
            case "orBrnBommr":
                return "ORK:204";
            case "orBltzBomr":
                return "ORK:205";
            /**
             * Ork Heavy Support
             */
            case "orDeffD":
                return "ORK:183";
            case "orKillerK":
                return "ORK:184";
            case "orLootedW":
                return "ORK:185";
            case "orBattleW":
                return "ORK:186";
            case "orBigGunz":   //Missing ammo runts
                String result = "";
                if (wargear.contains("orLobba"))
                    result = "ORK:188";
                else if (wargear.contains("orZzap"))
                    result = "ORK:189";
                else
                    result = "ORK:187";
                if (wargear.contains("orGunKGrB"))
                    result += "ORK:190";
                return result;
            case "orGunKGret":
                return "ORK:172";
            case "orRuntHGK":
                return "ORK:192";
            case "orFlashG":
                return "ORK:193";
            case "orPainB":
                return "ORK:195";
            case "orBadrukk":
                return "ORK:196";
            /**
             * Ork Transports
             */
            case "orTrukk":
                Chatbox.WriteLine("Ork Trukk!");
                return "ORK:197";
        }
        
        return null;
    }
}
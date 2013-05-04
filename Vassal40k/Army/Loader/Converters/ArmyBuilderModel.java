package Vassal40k.Army.Loader.Converters;

import VASSAL.counters.GamePiece;
import Vassal40k.Army.Loader.RosterLoader;
import java.util.HashSet;

public class ArmyBuilderModel extends AbstractModel
{
    private String model = null;
    private HashSet<String> wargear = new HashSet<String>();
    
    public ArmyBuilderModel(String modelID, String... wargearIDs)
    {
        model = modelID;
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
        return model;
    }

    @Override
    public String getVassalID()
    {
        /**
        * Ork HQ
        */
        if (model.equals("orWarboss"))
        {
            if (wargear.contains("orWarbike"))
                return "ORK:134";
            else
                return "ORK:132";
        }
        else if (model.equals("orWarbossM"))
        {
            return "ORK:133";
        }
        else if (model.equals("orBigM"))
        {
            if (wargear.contains("orWarbike"))
                return "ORK:198";
            else
                return "ORK:135";
        }
        else if (model.equals("orBigMMA"))
        {
            return "ORK:136";
        }
        else if (model.equals("orWeird"))
        {
            if (wargear.contains("orWarpH"))
                return "ORK:138";
            else
                return "ORK:137";
        }
        else if (model.equals("orZogW"))
        {
            return "ORK:139";
        }
        else if (model.equals("orGhaz"))
        {
            return "ORK:140";
        }
        else if (model.equals("orGrotS"))
        {
            return "ORK:141";
        }
        else if (model.equals("orWaz"))
        {
            return "ORK:142";
        /**
         * Ork Elite
         */
        }
        else if (model.equals("orNobzMo"))
        {
            if (wargear.contains("orWarbike") && wargear.contains("orWaaagh2"))
                return "ORK:147";
            else if (wargear.contains("orWarbike"))
                return "ORK:146";
            else if (wargear.contains("orWaaagh2"))
                return "ORK:144";
            else
                return "ORK:143";
        }
        else if (model.equals("orPainBN"))
        {
            if (wargear.contains("orWarbike"))
                return "ORK:148";
            else
                return "ORK:145";
        }
        else if (model.equals("orBurnaB"))
        {
            return "ORK:149";
        }
        else if (model.equals("orMekB"))
        {
            if (wargear.contains("orGrotOi"))
                return "ORK:150,ORK:151";
            else
                return "ORK:150";
        }
        else if (model.equals("orTankB"))
        {
            return "ORK:152";   //Missing Tankhammer and Bomb Squig
        }
        else if (model.equals("orTankN"))
        {
            return "ORK:154";
        }
        else if (model.equals("orLoota"))
        {
            return "ORK:156";
        }
        else if (model.equals("orKommando"))
        {
            return "ORK:159";   //Missing Specialist weapon
        }
        else if (model.equals("orKommN"))
        {
            return "ORK:161";
        }
        else if (model.equals("orSnikR"))
        {
            return "ORK:162";
        }
        else if (model.equals("orMegaN"))
        {
            return "ORK:163";
        }
        /**
         * Ork Troops
         */
        else if (model.equals("orBoyz"))
        {
            if (wargear.contains("orShoota"))
                return "ORK:165";
            else //if (wargear.contains("orChSlug"))    //Not needed, since we need a default
                return "ORK:164";
        }
        else if (model.equals("orBoyzNSl"))
        {
            return "ORK:167";
        }
        else if (model.equals("orArdB"))
        {
            if (wargear.contains("orShoota"))
                return "ORK:169";
            else //if (wargear.contains("orChSlug"))    //Not needed, since we need a default
                return "ORK:168";
        }
        else if (model.equals("orArdN"))
        {
            return "ORK:171";
        }
        else if (model.equals("orGretchin"))
        {
            return "ORK:172";
        }
        else if (model.equals("orRuntH"))
        {
            return "ORK:173";
        }
        /**
         * Ork Fast Attack
         */
        else if (model.equals("orDeffK"))
        {
            return "ORK:174";
        }
        else if (model.equals("orStormB"))
        {
            return "ORK:175";
        }
        else if (model.equals("orStormN"))
        {
            return "ORK:176";
        }
        else if (model.equals("orZagS"))
        {
            return "ORK:177";
        }
        else if (model.equals("orWarBike"))
        {
            return "ORK:178";
        }
        else if (model.equals("orBikeN"))
        {
            return "ORK:179";
        }
        else if (model.equals("orWarB"))
        {
            return "ORK:180";
        }
        else if (model.equals("orWarT"))
        {
            return "ORK:181";
        }
        else if (model.equals("orSkorcha"))
        {
            return "ORK:182";
        }
        else if (model.equals("orDakkajet"))
        {
            return "ORK:203";
        }
        else if (model.equals("orBrnBommr"))
        {
            return "ORK:204";
        }
        else if (model.equals("orBltzBomr"))
        {
            return "ORK:205";
        }
        /**
         * Ork Heavy Support
         */
        else if (model.equals("orDeffD"))
        {
            return "ORK:183";
        }
        else if (model.equals("orKillerK"))
        {
            return "ORK:184";
        }
        else if (model.equals("orLootedW"))
        {
            return "ORK:185";
        }
        else if (model.equals("orBattleW"))
        {
            return "ORK:186";
        }
        else if (model.equals("orBigGunz"))  //Missing Ammo Runts
        {
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
        }
        else if (model.equals("orGunKGret"))
        {
            return "ORK:172";
        }
        else if (model.equals("orRuntHGK"))
        {
            return "ORK:192";
        }
        else if (model.equals("orFlashG"))
        {
            return "ORK:193";
        }
        else if (model.equals("orPainB"))
        {
            return "ORK:195";
        }
        else if (model.equals("orBadrukk"))
        {
            return "ORK:196";
        }
        /**
         * Ork Transports
         */
        else if (model.equals("orTrukk"))
        {
            return "ORK:197";
        }
        else
        {
            return null;
        }
    }
}
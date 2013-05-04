package Vassal40k.Army.Loader.Converters;

import VASSAL.counters.GamePiece;
import Vassal40k.Army.Loader.RosterLoader;
import java.util.HashSet;

public class BattleScribeModel extends AbstractModel
{
    private String model = null;
    private HashSet<String> wargear = new HashSet<String>();
    
    public BattleScribeModel(String modelID, String... wargearIDs)
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
         * Space Marine HQ
         */
        if (model.equals("25ac7f87-f017-47e5-8ee7-367a84f58180"))
        {    //Chapter Master
            if (wargear.contains("bdfcf9aa-71ba-40c1-acc8-10c9e875dd8b"))   //Bike
                return "SM:1213";
            if (wargear.contains(""))   //Terminator
                return "SM:1214";
            else
                return "SM:1212";
        }
        else if (model.equals("6dbc882a-afdf-47ef-b0d4-986137fc479f"))
        {    //Honour Guard
            return "SM:1215";
        }
        else if (model.equals("b84ff222-9161-4aa3-91de-1d95093a02ea"))
        {    //Chapter Champion
            return "SM:1216";
            //Chapter Champion w/ Banner: 1217
        }
        else if (model.equals("ac09669c-93ab-42e9-8585-94ef80430a3d"))
        {    //Captain
            //Bike, 1219
            //Terminator, 1220
            return "SM:1218";
        }
        else if (model.equals("82ee84d8-6ef0-4b42-84cb-d2113cd66b78"))
        {    //Command Squad Veteran
            //Bike, 1225
            return "SM:1221";
            //Champion 1222
            //Bike, 1226
            //Banner Bearer 1223
            //Bike, 1227
        }
        else if (model.equals("2b848c3c-e8d8-4ae3-bbfb-6ce7fef8ac54"))
        {    //Command Squad Apothecary
            //Bike, 1228
            return "SM:1224";
        }
        else if (model.equals("bde12b9a-4747-47db-b3ff-a707fd83f71a"))
        {    //Chaplain
            //Bike, 1230
            //Terminator, 1231
            return "SM:1229";
        }
        else if (model.equals("28e747cd-1193-4eb6-9ab5-6b3e741f87cc"))
        {    //Librarian
            //Bike, 1233
            //Terminator, 1234
            return "SM:1332";
        }
        else if (model.equals("38255e9f-6739-4da7-b5ca-3aea0397f399"))
        {    //Master of the Forge
            //Bike, 1236
            return "SM:1235";
        }
        else if (model.equals("b85444a9-b540-49ca-ae01-b074bef4559d"))
        {    //Servitor
            return "SM:1237";
        }
        else if (model.equals("89f15d1b-0a29-469f-af66-74745a8e4ee3"))
        {    //Marneus Calgar
            return "SM:1238";
        }
        else if (model.equals("a6dd5627-e0e2-4f37-acc7-1c82b0da0cb1"))
        {    //Cato Sicarius
            return "SM:1239";
        }
        else if (model.equals("3961ae93-2391-4948-a875-f7f282d29d75"))
        {    //Chief Librarian Tigurius
            return "SM:1240";
        }
        else if (model.equals("781c4dd8-5459-482d-bd1a-18f7171129ba"))
        {    //Chaplain Cassius
            return "SM:1241";
        }
        else if (model.equals("3e332cbe-98fb-4737-bbfe-6ac265b6957d"))
        {    //Darnath Lysander
            return "SM:1321";
        }
        else if (model.equals("751b91ce-83fa-4e00-8b6d-b2212b8d5675"))
        {    //Pedro Kantor
            return "SM:1395";
        }
        else if (model.equals("5ec72a94-9f5e-4706-b0eb-8ed8d8f7b069"))
        {    //Vulkan He'Stan
            return "SM:1469";
        }
        else if (model.equals("2fa112c3-ce71-4fb7-a94a-e74f5903e3cf"))
        {    //Kor'Sarro Khan
            //Bike, 1544
            return "SM:1543";
        }
        else if (model.equals("26c60684-4614-44ae-ae2e-a02b9697013f"))
        {    //Kayvaan Shrike
            return "SM:1618";
        }
        else if (model.equals("e9c53ef6-b3d6-4a1f-9e84-c9a250035903"))
        {    //Sergeant Telion
            return "SM:1266";
            //Chronus, 1291 ?
        }
        /**
         * Space Marine Elites
         */
        else if (model.equals("abdf8694-fc27-4869-9d22-ce506b612588"))
        {    //Dreadnought
            return "SM:1242";
        }
        else if (model.equals("9858ef5d-0a0b-4605-bce3-1404097a469f"))
        {    //Ironclad Dreadnought
            return "SM:1243";
            //Contemptor Dreadnought, 1244
        }
        else if (model.equals("763642f1-cd22-4d0a-bfcd-431a000d1733"))
        {    //Terminator
            return "SM:1244";
        }
        else if (model.equals("d70a4514-4256-4216-8b8f-875022b6113a"))
        {    //Terminator Sergeant
            return "SM:1245";
        }
        else if (model.equals("0fa24fd7-7351-4b7d-99f8-3509aa795fa2"))
        {    //Assault Terminator
            return "SM:1246";
        }
        else if (model.equals("ef328c70-398d-4890-98a7-cb5e1eb23c0b"))
        {    //Assault Terminator Sergeant
            return "SM:1247";
        }
        else if (model.equals("6047d015-33c9-4de1-8855-464ac09769f1"))
        {    //Sternguard
            return "SM:1248";
            //Special weapons: 1249,1250
        }
        else if (model.equals("86f077ae-b316-41d0-ab30-0cbf134f2553"))
        {    //Sternguard Sergeant
            return "SM:1251";
        }
        else if (model.equals("e0d0bd2a-e9d8-42a0-9b7e-33d361c7fdb9"))
        {    //Techmarine
            //Bike, 1254
            return "SM:1252";
        }
        else if (model.equals("a54a83be-ac59-400f-a727-d1e2642ac37b"))
        {    //Legion of the Damned
            return "SM:1255";   //1256, 1257
        }
        else if (model.equals("3bddedc9-6f62-4695-b4c0-5b80df69886a"))
        {    //Legion of the Damned Sergeant
            return "SM:1258";
        }
        /**
         * Space Marine Troops
         */
        else if (model.equals("e36192c7-312f-4dc9-b381-c04f652ef3f6"))
        {    //Tactical Squad
            return "SM:1259"; //1260, 1261
        }
        else if (model.equals("9283fd14-af24-495f-ae7b-da332d5c30a4"))
        {    //Tactical Squad Sergeant
            return "SM:1262";
        }
        else if (model.equals("328c4f21-c6a1-4bca-b439-301272cdaa82"))
        {    //Scout Squad
            return "SM:1263"; //1264
        }
        else if (model.equals("bad5ac44-d84d-4889-9259-181cce32dda0"))
        {    //Scout Squad Sergeant
            return "SM:1265";
        }
        /**
         * Space Marine Fast Attack
         */
        else if (model.equals("2438e0c1-cf3d-4d7b-becf-5c54c8c6a2e5"))
        {    //Land Speeder
            return "SM:1267";
        }
        else if (model.equals("45ed254c-f697-4d89-9235-2f4a386a050a"))
        {    //Land Speeder Storm
            return "SM:1268";
        }
        else if (model.equals("dc9dd528-cab5-4541-871e-d71004c6e66g"))
        {    //Stormtalon Gunship
            return "SM:2061";
        }
        else if (model.equals("6410d329-3faa-4d2b-a7b5-ebfd3c72b98d"))
        {    //Biker
        }
        else if (model.equals("1e2115ba-c1e0-42c0-bea1-2e6e15ddf902"))
        {    //(as Troops)
            return "SM:1268"; //1269
        }
        else if (model.equals("d195f039-c796-4480-97a9-e8e0632149d7"))
        {    //Biker Sergeant
        }
        else if (model.equals("089a8b47-4bf2-42c5-9bdd-7afb2f6643d6"))
        {    //(as Troops)
            return "SM:1270";
        }
        else if (model.equals("5167fcd5-aa24-4481-b015-564754764a03"))
        {    //Attack Bike
            return "SM:1271";
        }
        else if (model.equals("971b01e0-2a92-4f18-9075-1eb739f40659"))
        {    //Assault Squad
            return "SM:1272"; //1273
        }
        else if (model.equals("6f7390c8-6d6a-487d-98ec-fd973f69736d"))
        {    //Assault Squad Sergeant
            return "SM:1274";
        }
        else if (model.equals("7a1746f8-b651-4364-a773-e6c8d870df01"))
        {    //Vanguard
            return "SM:1275";
        }
        else if (model.equals("ba2ea04a-9b95-4fe2-a297-dc72e0236510"))
        {    //Vanguard Sergeant
            return "SM:1276";
        }
        else if (model.equals("b4381661-123a-427f-8976-f4687d4d6cbf"))
        {    //Scout Bike
            return "SM:1278";
        }
        else if (model.equals("9c2c491d-a8f3-4d67-ac00-6bd690fcd419"))
        {    //Scout Bike Sergeant
            return "SM:1279";
        }
        /**
         * Space Marine Heavy Support
         */
        else if (model.equals("dc9dd528-cab5-4541-871e-d71004c6e00b"))
        {    //Predator
            return "SM:1280";
        }
        else if (model.equals("a8dd6021-25fa-4f2d-a465-ecc4dc22aad5"))
        {    //Vindicator
            return "SM:1281";
        }
        else if (model.equals("e3673913-5424-493f-9400-3fadbb3e85ef"))
        {    //Whirlwind
            return "SM:1282";
        }
        else if (model.equals("a63e478f-195e-422d-8414-54786cda6373"))
        {    //Land Raider
            return "SM:1283";
        }
        else if (model.equals("e3d71975-6326-4134-aa6a-8361a4d6b914"))
        {    //Land Raider Crusader
            return "SM:1284";
        }
        else if (model.equals("bd52a3d0-d294-49a5-841f-aba15916b9f0"))
        {    //Land Raider Redeemer
            return "SM:1285";
        }
        else if (model.equals("80dcce1b-31b5-45a4-bb19-7b295801f52e"))
        {    //Devastator Squad
            return "SM:1286"; //1287
        }
        else if (model.equals("a811f517-d453-4743-913c-8c16c3976dd0"))
        {    //Devastator Squad Sergeant
            return "SM:1288";
        }
        else if (model.equals("76d9f3ea-4355-4a89-8792-ffa0ddb8ff28"))
        {    //Thunderfire Cannon
            return "SM:1290";
        }
        /**
         * Space Marine Transports
         */
        else if (model.equals("2bc558f5-9615-44fd-9f3e-3aa061024ff1"))
        {    //Rhino
            return "SM:1292";
        }
        else if (model.equals("ec651dc5-3aff-409f-8ffa-fd01e01b33be"))
        {    //Razorback
            return "SM:1293";
        }
        else if (model.equals("2e516082-2cfd-4460-8ec9-f10d7e460190"))
        {    //Drop Pod
            return "SM:1294";
        }
        
        /**
         * Ork HQ
         */
        else if (model.equals("6cfcacce-52dd-4a52-a20d-ac12d0ca1f5f"))
        {    //Ork Warboss
            if (wargear.contains("orWarbike"))
                return "ORK:134";
            else
                return "ORK:132";
        }
        else if (model.equals("orWarbossM"))
        {
            return "ORK:133";
        }
        else if (model.equals("0aea13ce-b69e-457a-a2d7-e5b46b1bb9d1"))
        {    //Big Mek
            if (wargear.contains("orWarbike"))
                return "ORK:198";
            else
                return "ORK:135";
        }
        else if (model.equals("orBigMMA"))
        {
            return "ORK:136";
        }
        else if (model.equals("e8cc5c84-70e7-4e40-b8f8-48ad9b6308ba"))
        {    //Weirdboy
            if (wargear.contains("orWarpH"))
                return "ORK:138";
            else
                return "ORK:137";
        }
        else if (model.equals("1b9ee438-c7d7-4cce-b8e2-4a8cb31edcd0"))
        {    //Old Zogwort
            return "ORK:139";
        }
        else if (model.equals("9bbaa65b-366d-46ec-9131-b0f24ac98158"))
        {    //Ghazghkull Thraka
            return "ORK:140";
        }
        else if (model.equals("6103794d-b3ab-4ca9-adc8-7f1994f6de9d"))
        {    //Mad Dok Grotsnik
            return "ORK:141";
        }
        else if (model.equals("8d8848c6-1e9d-4178-b423-d6003a78277a"))
        {    //Wazdakka Gutsmek
            return "ORK:142";
        }
        /**
         * Ork Elite
         */
        else if (model.equals("5b0b4f1a-b508-43d0-8e71-52bd02dd5bec"))
        {
        }
        else if (model.equals("c7afdfbd-2a1d-4552-ab63-0ca733c1b428"))
        {    //Nob
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
        else if (model.equals("207c2441-de1e-43d1-ac4c-6b51a2867ca3"))
        {    //Burna Boy
            return "ORK:149";
        }
        else if (model.equals("orMekB"))
        {
            if (wargear.contains("orGrotOi"))
                return "ORK:150,ORK:151";
            else
                return "ORK:150";
        }
        else if (model.equals("2997a1ef-a731-4944-982b-304c564ec0ff"))
        {    //Tank Busta
            return "ORK:152";   //Missing Tankhammer and Bomb Squig
        }
        else if (model.equals("orTankN"))
        {
            return "ORK:154";
        }
        else if (model.equals("f393869d-62b8-4737-bee1-73f15ba57d5c"))
        {    //Loota
            return "ORK:156";
        }
        else if (model.equals("4c5df639-c716-41f1-891b-74be1d85d0d1"))
        {    //Kommando
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
        else if (model.equals("7f45b912-fb9d-40d2-87c1-eacf30a3675a"))
        {    //Mega-Nob
            return "ORK:163";
        }
        /**
         * Ork Troops
         */
        else if (model.equals("1bf5e0e5-3d8a-b1d4-1ce1-42834d1ae346"))
        {    //Boyz
            if (wargear.contains("d0a4d12c-8837-2a6b-c775-8d38c4ff0ea7"))
                return "ORK:165";
            //Choppa & Slugga
            return "ORK:164";
        }
        else if (model.equals("e74220ab-bf79-8173-a7c8-5129e82aeb43"))
        {    //Boy with special weapon
            if (wargear.contains("9e258fd1-eded-0875-2532-40a23690c63c"))   //Big Shoota
                return "ORK:166";
            if (wargear.contains("fc9060fa-4292-4f36-5902-70242f66429a"))   //Rokkit Launcha
                return "ORK:166";
        }
        else if (model.equals("orBoyzNSl"))
        {
            return "ORK:167";
        }
        else if (model.equals("86e1b019-ac5b-d5b2-6440-d8c44bdec29e"))
        {    //'Ard Boyz
            if (wargear.contains("orShoota"))
                return "ORK:169";
            else //if (wargear.contains("orChSlug"))    //Not needed, since we need a default
                return "ORK:168";
        }
        else if (model.equals("orArdN"))
        {
            return "ORK:171";
        }
        else if (model.equals("7834a999-4a07-4b1d-bf3d-a797dde66a77"))
        {    //Gretchin
            return "ORK:172";
        }
        else if (model.equals("07fcff26-a5a5-4d40-9b45-2d80994a40a6"))
        {    //Runtherd
            return "ORK:173";
        }
        /**
         * Ork Fast Attack
         */
        else if (model.equals("53afae11-657e-4e5d-94cd-52a893a1a9cd"))
        {    //Deffkopta
            return "ORK:174";
        }
        else if (model.equals("a0f60c88-3f6c-4a19-8f0f-0542280a1bd9"))
        {    //Stormboyz
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
        else if (model.equals("cfacc9bd-95fa-43da-8ee2-d0777ed6cfd0"))
        {    //Warbike
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
        else if (model.equals("d11fb756-9026-43fa-b2fe-452c21603a0f"))
        {    //Dakka Jet
            return "ORK:203";
        }
        else if (model.equals("c823ced3-5743-42de-87f2-8afe3b891320"))
        {    //Burna Bommer
            return "ORK:204";
        }
        else if (model.equals("dbf70e28-f1f5-4497-818f-70bd6f256f36"))
        {    //Blitza Bommer
            return "ORK:205";
        }
        /**
         * Ork Heavy Support
         */
        else if (model.equals("cf31b614-58ae-483c-8743-a8c4841f21cb"))
        {    //Deff Dread
            return "ORK:183";
        }
        else if (model.equals("de88eb86-dc3f-4d8e-a140-21850a1bc7fe"))
        {    //Killa Kan
            return "ORK:184";
        }
        else if (model.equals("783d0a73-1d9a-44a1-9f6e-6da9cd3b14bf"))
        {    //Looted Wagon
            return "ORK:185";
        }
        else if (model.equals("9fb2d714-5e05-4674-b2f8-430e090ebce6"))
        {    //Battle Wagon
            return "ORK:186";
        }
        else if (model.equals("634dad65-6565-4335-8e8d-5cf900204d60"))
        {    //Big Gunz
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
        else if (model.equals("3858776f-d7d9-4acc-b4eb-b86ca7c718a3"))
        {    //Flash Gitz
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
        
        return null;
    }
}
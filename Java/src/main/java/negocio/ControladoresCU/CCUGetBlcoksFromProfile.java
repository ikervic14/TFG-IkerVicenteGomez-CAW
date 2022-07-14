package negocio.ControladoresCU;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import negocio.modelo.Block;
import negocio.modelo.CAW;
import negocio.modelo.Profile;

public class CCUGetBlcoksFromProfile {
    public static Map<Integer,String> getBlocksFromProfile(int id) throws IllegalArgumentException{
        
        Profile selectedProfile= selectProfile(id);
        HashMap<Integer,String> result= new HashMap<Integer,String>();
        for(Block b : selectedProfile.getBlocks()){
            result.put(b.getId(),b.getName());
        }
        return result;   
        
    }

    private static Profile selectProfile(int id) throws IllegalArgumentException{
        CAW instance= CAW.getInstance();
        List<Profile> list= instance.getProfiles();
        for (Profile p : list){
            if(p.getId()==id){
                instance.selectProfile(p);
                return p;
            }
        }
        throw new IllegalArgumentException("No profile with this id");
    }
}

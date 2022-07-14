package negocio.ControladoresCU;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import negocio.modelo.CAW;
import negocio.modelo.Profile;

public class CCUGetLoadedProfiles {

    public static Map<Integer,String> getLoadedProfiles(){
        CAW instance= CAW.getInstance();
        List<Profile> lista= instance.getProfiles();
        HashMap<Integer,String> result= new HashMap<Integer,String>();
        for (Profile p : lista){
            result.put( p.getId(),p.getName());
        }
        return result;   
    }
}

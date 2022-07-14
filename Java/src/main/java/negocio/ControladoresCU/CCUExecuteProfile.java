package negocio.ControladoresCU;

import java.util.List;

import negocio.modelo.CAW;
import negocio.modelo.Profile;

public class CCUExecuteProfile {


    public static void executeProfile( int id ) throws IllegalArgumentException{
        CAW instance= CAW.getInstance();
        Profile epro=instance.getExecutingProfile();
        List<Profile> list= instance.getProfiles();
        for (Profile p : list){
            if(p.getId()==id){
                if(epro==null){
                    p.execute();
                    instance.executeProfile(p);
                }
                else if ( p.getId()!=epro.getId()){
                    epro.stop();
                    p.execute();
                    instance.executeProfile(p);
                    
                }
                return;
            }
        }
        throw new IllegalArgumentException(" No profile with this id");
    }
}

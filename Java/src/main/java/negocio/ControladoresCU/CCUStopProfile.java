package negocio.ControladoresCU;

import java.util.List;

import negocio.modelo.CAW;
import negocio.modelo.Profile;

public class CCUStopProfile {
    
    
    public static void stopProfile( int id ) throws IllegalArgumentException{
        CAW instance= CAW.getInstance();
        List<Profile> list= instance.getProfiles();
        Profile epro= instance.getExecutingProfile();
        for (Profile p : list){
            if(p.getId()==id){
                if (p.getExecution()){
                    p.stop();
                    if(epro!=null && epro.getId()==p.getId())
                        instance.executeProfile(null);
                }
                return;
            }
        }
        throw new IllegalArgumentException(" No profile with this id");
    }
}

package negocio.ControladoresCU;

import java.util.List;

import negocio.modelo.CAW;
import negocio.modelo.Profile;

public class CCUDeleteProfile {
   public static void deleteProfile(int id) throws IllegalArgumentException{ 
        CAW instance= CAW.getInstance();
        List<Profile> list= instance.getProfiles();
        for( Profile prof : list){
            if(prof.getId()==id ){
               if(prof.getExecution()){
                   prof.stop();
               }
               instance.deleteProfile(prof);
               return;
            }
        }
        throw new IllegalArgumentException(" No profile with this id");
   }
}

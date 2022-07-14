package negocio.ControladoresCU;

import negocio.modelo.CAW;
import negocio.modelo.Profile;

public class CCUDeleteBlockFromProfile{


        public static void deleteBlockFromProfile(int id) throws IllegalStateException, IllegalArgumentException{
            CAW instance= CAW.getInstance();
            Profile selectecProfile= instance.getSelectedProfile();
            if(selectecProfile==null){
                throw new IllegalStateException(" No profile selected");
            }
            if(selectecProfile.getExecution()){
                selectecProfile.stopBlock(id);
                
            }
            selectecProfile.deleteBlock(id);
        }
}
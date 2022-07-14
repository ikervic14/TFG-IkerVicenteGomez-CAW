package negocio.ControladoresCU;




import negocio.modelo.CAW;
import negocio.modelo.Profile;

public class CCULoadProfile {
    public static void LoadProfile(String pathToFile) throws IllegalArgumentException{

        CAW instance= CAW.getInstance();      
        instance.addProfile(new Profile(pathToFile,instance.getNextId()));
       

    }
}

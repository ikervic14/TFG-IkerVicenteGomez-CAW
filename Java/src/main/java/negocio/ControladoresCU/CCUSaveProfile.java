package negocio.ControladoresCU;

import java.util.List;
import negocio.modelo.CAW;
import negocio.modelo.Profile;


public class CCUSaveProfile {

    private static final String[] invalidChars={".","/","(",")","[","]"," "};


    public static boolean hasFile(int id) throws IllegalArgumentException{
        return getProfile(id).getFile()!=null;
    }
       

    public static void save(int id, String file) throws IllegalArgumentException{

        for(String s : invalidChars){
            if(file.contains(s)){
                throw new IllegalArgumentException(" No profile with this id");
            }
        }
        Profile profile=getProfile(id);
        profile.save(file);
 
    }


    public static void save(int id) throws IllegalArgumentException{
        Profile profile=getProfile(id);
        profile.save();

        
    }


    private static Profile getProfile( int id)throws IllegalArgumentException{
        CAW instance= CAW.getInstance();
        List<Profile> list= instance.getProfiles();
        for( Profile prof : list){
            if(prof.getId()==id){
                return prof;
            }
        }
        throw new IllegalArgumentException("No profile with this id");
    }
}

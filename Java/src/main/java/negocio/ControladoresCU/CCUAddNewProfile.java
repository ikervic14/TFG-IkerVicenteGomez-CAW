package negocio.ControladoresCU;


import negocio.modelo.CAW;
import negocio.modelo.Profile;

public class CCUAddNewProfile {


    private static final String[] invalidChars={".","/","(",")","[","]"," ",":"};


    public static void  createProfile(String name) throws IllegalStateException, IllegalArgumentException{
        CAW instance= CAW.getInstance();
        if(!checkValidName(name)){
            throw new IllegalArgumentException(" invalid name");
        }
        instance.addProfile(new Profile(instance.getNextId(),name));
    }



    
    private static boolean checkValidName(String name){
        for(String s : invalidChars){
            if(name.contains(s)){
                return false;
            }
        }
        return true;
    }
}

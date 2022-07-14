package negocio.ControladoresCU;



import negocio.modelo.Block;
import negocio.modelo.CAW;
import negocio.modelo.Profile;

public class CCUAddBlockToProfile {

    private static final String[] invalidChars={".","/","(",")","[","]"," ",":"};



    public  static void addBlock( String name) throws IllegalStateException, IllegalArgumentException{
        CAW instance= CAW.getInstance();
        Profile profile = instance.getSelectedProfile();
        if(profile==null){
            throw new IllegalStateException(" No profile selected");
        }
        for(String s : invalidChars){
            if(name.contains(s)){
                throw new IllegalArgumentException(" invalid name");
            }
        }
        Block bloque=new Block(name, profile.getNextId());
        profile.addBlock(bloque);
        if(profile.getExecution()){
            bloque.execute();
        }
    }


    
}
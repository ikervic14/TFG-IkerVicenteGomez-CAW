package negocio.modelo;

import java.util.ArrayList;
import java.util.List;

public class CAW {
    private List<Profile> loadedProfiles;
    private int nextId;
    private Profile executingProfile;
    private Profile selectedProfile;
    private Block selectedBlock;


    private static CAW instance;
    private CAW(){
        loadedProfiles=new ArrayList<Profile>();
        nextId=0;
        executingProfile=null;
        selectedProfile=null;
        selectedBlock=null;
    }
    public static CAW getInstance(){
        if(instance==null){
            instance = new CAW();
        }
        return instance;
        
    }
    
    public int getNextId(){
        return nextId;
    }


    public List<Profile> getProfiles(){
        return loadedProfiles;
    }


    public void addProfile(Profile profile){
        if(profile==null){
            throw new IllegalArgumentException("Profile can't be null");
        }
        if(loadedProfiles.contains(profile)){
            throw new IllegalStateException("Profile is already loaded");
        }
        loadedProfiles.add(profile);
        nextId++;
    }


    public void deleteProfile(Profile p){
        if(p==null){
            throw new IllegalArgumentException("Profile can't be null");
        }
        if(!loadedProfiles.contains(p)){
            throw new IllegalStateException("Profile is not loaded");
        }
        loadedProfiles.remove(p);
    }

    
    public void executeProfile(Profile profile){
        executingProfile=profile;
    }


    public Profile getExecutingProfile(){
        return executingProfile;
    }


    public void selectProfile(Profile profile){
        selectedProfile=profile;
    }


    public Profile getSelectedProfile(){
        return selectedProfile;
    }

    public void selectBlock(Block block){
        selectedBlock=block;
    }

    public Block getSelectedBlock(){
        return selectedBlock;
    }
}

package negocio.modelo;

import java.util.Map;

import com.fasterxml.jackson.jr.ob.JSON;

public class AudioComponent {
    
    private int id;
    private String name;
    public AudioComponent(int id, String name){
        if(name == null){ 
            throw new IllegalArgumentException("Name cant be null");
        }
        this.id=id;
        this.name=name;
    }
    public AudioComponent(String json, int id){
        Map<String,Object> jsonM=null;
        
        try{
            jsonM= JSON.std.mapFrom(json);
        }catch(Exception e){
           throw new IllegalArgumentException("Invalid Json");
        }

        Object JsonName=jsonM.get("name");

        if(!JsonName.getClass().equals(String.class)  ){
            throw new IllegalArgumentException("Invalid Json");
        }
        

        this.name=(String) JsonName;

        this.id=id;
    }
    

    public int getId(){
        return id;
    }
    public void setName(String name){
        if(name==null){
            throw new IllegalArgumentException("Name cant be null");
        }
        this.name=name;
    }
    public String getName(){
        return name;
    }

}

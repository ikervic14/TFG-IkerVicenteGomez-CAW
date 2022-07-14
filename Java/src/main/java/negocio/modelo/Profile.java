

package negocio.modelo;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.fasterxml.jackson.jr.ob.*;

import com.fasterxml.jackson.jr.ob.impl.DeferredMap;


public class Profile{



    private int id;
    private String name;
    private String route;
    private boolean execution;
    private List<Block> blocks;
    private int nextId;



    public Profile( int id, String name){
        if(name==null){
            throw new IllegalArgumentException("Name can't be null");
        }
        this.id=id;
        this.name=name;
        this.execution=false;
        this.blocks= new ArrayList<Block> ();
        nextId=0;

    }

    public Profile( String json,int id){
        Map<String,Object> jsonM=null;

        try{
            File file= new File(json);
            Scanner scn = new Scanner(file);
            StringBuilder builder= new StringBuilder();
            while(scn.hasNextLine()){
                builder.append(scn.nextLine()).append("\n");
            }
            scn.close();
            jsonM= JSON.std.mapFrom(builder.toString());
        

        Object jsonName=jsonM.get("name");;
        Object jsonBlock=jsonM.get("blocks");
        if(!jsonName.getClass().equals(String.class) || !jsonBlock.getClass().equals(ArrayList.class)){
            throw new IllegalArgumentException("Invalid Json");
        }

        this.name=(String)jsonName;
        this.id=id;
        this.execution=false;
        this.nextId=0;
        this.blocks=new ArrayList<Block>();
        this.route=json;
        ArrayList<?>  lista=(ArrayList<?>) jsonBlock;
        if(lista.size()>0 ){
            if(lista.get(0).getClass().equals(DeferredMap.class)){
                for(int i=0;i<lista.size();i++){
                    String bstr="";
                    try{
                        bstr=JSON.std.asString(lista.get(i));
                    }catch(Exception e){
                        throw new IllegalArgumentException("Invalid Json");
                    }
                    addBlock(new Block(this.getNextId(),bstr));
                    
                }
            }
            else{
                throw new IllegalArgumentException("Invalid Json");
            }
        }
        }catch(Exception e){
            throw new IllegalArgumentException("Invalid Json");
        }

    }

    public int getNextId(){
        return nextId;
    }
    
    public int getId(){
        return id;
    }


    public Block getBlock(int id){
        for (Block b : blocks){
            if( b.getId()==id){
                return b;
            }
        }
        throw new IllegalStateException("Block not in profile");
    }


    public void addBlock(Block block){
        if(block==null){
            throw new IllegalArgumentException("Block can't be null");
        }
        for( Block otp : blocks){
            if( otp.getId()==block.getId()){
                throw new IllegalStateException("block already in profile");
            }
        }
        blocks.add(block);
        nextId++;
    }

    public List<Block> getBlocks(){
        return blocks;
    }

    public String getName(){
        return name;
    }

    public void stop(){
        execution=false;
        for(Block b : blocks){
            b.stop();
        }
    }
   


    public void stopBlock(int id) throws IllegalArgumentException{
        for(Block b : blocks){
            if(b.getId()==id){
                b.stop();
                return;
            }
        }
        throw new IllegalArgumentException("invalid value");
    }
    public void deleteBlock(int id) throws IllegalArgumentException{
        for(Block b : blocks){
            if(b.getId()==id){
                blocks.remove(b);
                return;
            }
        }
        throw new IllegalArgumentException("invalid value");
    }

    public void execute(){
        if(execution==true){
            throw new IllegalStateException("profile already running");
        }
        execution=true;
        for(Block b : blocks){
            b.execute();
        }
    }


    public boolean getExecution(){
        return execution;
    }

    public String getFile(){
        return route;
    }

    public void save(String pathtoFile){
        if(pathtoFile==null){
            throw new IllegalArgumentException("pathToFile can't be null");
        }
        route=pathtoFile+"/"+name+".json";
        save();

    }

    public void save(){
        if(route==null){
            throw new IllegalStateException("No file associated");
        }
        DeferredMap map= new DeferredMap(true);
        map.put("name",name);
        ArrayList<Object> blockList= new ArrayList<Object>();
        for (Block b : blocks){
            blockList.add(b.toJsonMap());
        }
        map.put("blocks",blockList);
        try {
           File f= new File(route);
        
            JSON.std.with(JSON.Feature.PRETTY_PRINT_OUTPUT).write(map,f);
        } catch (Exception e) {
            throw new IllegalArgumentException("Bad File");
        }
    }
}

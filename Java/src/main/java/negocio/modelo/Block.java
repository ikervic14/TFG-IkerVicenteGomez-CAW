package negocio.modelo;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.jr.ob.JSON;
import com.fasterxml.jackson.jr.ob.impl.DeferredMap;



public class Block {
    private int id;
    private List<AudioInput> inputs;
    private List<AudioOutput> outputs;
    private String name;
    private boolean execution;
    private int nextId;
    private ArrayList<Process> procesos;


    public Block( String name , int id){
        if (name==null){
            throw new IllegalArgumentException("Name can't be null");
        }
        this.name=name;
        this.id=id;
        this.outputs= new ArrayList<AudioOutput>();
        this.inputs=new ArrayList<AudioInput>();
        this.execution=false;
        this.nextId=0;
        procesos= new ArrayList<Process>();


    }
    public int getNextId(){
        return nextId;
    }

    public Block(int id,String json){
        Map<String,Object> jsonM=null;

        
        try{
            jsonM= JSON.std.mapFrom(json);
        }catch(Exception e){
           throw new IllegalArgumentException("Invalid Json");
        }


        Object jsonName=jsonM.get("name");
        Object jsonInputs=jsonM.get("inputs");
        Object jsonOutputs=jsonM.get("outputs");

        if(!jsonName.getClass().equals(String.class) || !jsonInputs.getClass().equals(ArrayList.class) || !jsonOutputs.getClass().equals(ArrayList.class)){
            throw new IllegalArgumentException("Invalid Json");
        }


        this.id=id;
        this.nextId=0;
        this.execution=false;
        this.name=(String)jsonName;
        this.inputs= new ArrayList<AudioInput>();
        this.outputs= new ArrayList<AudioOutput>();
        procesos= new ArrayList<Process>();

        ArrayList<?>  listaIn=(ArrayList<?>) jsonInputs;
        ArrayList<?>  listaOut=(ArrayList<?>) jsonOutputs;
        if(listaIn.size()>0){
             if(listaIn.get(0).getClass().equals(DeferredMap.class)){
                for(int i=0;i<listaIn.size();i++){
                    String instr;
                    try{
                        instr=JSON.std.asString(listaIn.get(i));
                    }catch(Exception e){
                        throw new IllegalArgumentException("Invalid Json");
                    }
                    addInput(new AudioInput(this.getNextId(),instr));
                }
             }else{
                throw new IllegalArgumentException("Invalid Json");
             }
        }
        if(listaOut.size()>0){
            if(listaOut.get(0).getClass().equals(DeferredMap.class)){
               for(int i=0;i<listaOut.size();i++){
                    String outstr;
                    try{
                        outstr=JSON.std.asString(listaOut.get(i));
                    }catch(Exception e){
                        throw new IllegalArgumentException("Invalid Json");
                    }
                   addOutput(new AudioOutput(outstr,this.getNextId()));
               }
            }else{
               throw new IllegalArgumentException("Invalid Json");
            }
       }
        
    }

    public int getNumInputs(){
        return inputs.size();
    }

    public DeferredMap toJsonMap(){
        DeferredMap map= new DeferredMap(true);
        map.put("name",name);
        ArrayList<Object> inputList= new ArrayList<Object>();
        ArrayList<Object> OutputList= new ArrayList<Object>();
        for (AudioInput b : inputs){
            inputList.add(b.toJsonMap());
        }
        for (AudioOutput b : outputs){
            OutputList.add(b.toJsonMap());
        }
        map.put("inputs",inputList);
        map.put("outputs",OutputList);
        return map;

    }
    public   String getName(){
        return name;
    }

    public void setName(String name){
        if (name==null){
            throw new IllegalArgumentException("Name can't be null");
        }
        this.name = name;
    }

    public int  getId(){
        return id;
    }


    public void stop(){
        for (Process p : procesos){
            p.destroy();
        }
        procesos= new ArrayList<Process>();
        execution=false;
    }


    public void execute(){
        if(execution==true){
            throw new IllegalStateException("Can`t execute a executing block");
        }
        for ( AudioInput aIn : inputs){
            String idIn= "";
            Map<String , String> mapa=AudioInput.getAvailableSources(aIn.getType());
            for(String key : mapa.keySet()){
                if(mapa.get(key).equals(aIn.getName())){
                    idIn=key;
                }
            }
            if(idIn!=""){
                for (AudioOutput aOut : outputs){
                    String idOut= "";
                    Map<String , String> mapa2=AudioOutput.getAvailableDevices();
                    for(String key : mapa2.keySet()){
                        if(mapa2.get(key).equals(aOut.getName())){
                            idOut=key;
                        }
                    } 
                    if( idOut!=""){
                        if(aIn.getType()==InputType.APP){
                            try{
                            ProcessBuilder pb= new ProcessBuilder("./CAW4",idIn,idOut);
                            procesos.add(pb.start());
                            }catch(Exception e){}
                        }else if( aIn.getType()==InputType.INPUTDEVICE){
                            try{
                                ProcessBuilder pb= new ProcessBuilder("./CAW3",idIn,idOut);
                                procesos.add(pb.start());
                                }catch(Exception e){}
                        }
                    }
                }
            }
        }
        execution=true;
    }


    public boolean getExecution(){
        return execution;
    }


    public void addInput(AudioInput input){
        if(input==null){
            throw new IllegalArgumentException("Audio input can't be null");
        }
        for( AudioInput otp : inputs){
            if( otp.getId()==input.getId()){
                throw new IllegalStateException("output already in block");
            }
        }
        inputs.add(input);
        nextId++;
    }
    

    public void deleteInput(int  id)throws IllegalArgumentException{
        for( AudioInput inp : inputs){
            if( inp.getId()==id){
                inputs.remove(inp);
                return;
            }
        }
        throw new IllegalArgumentException("invalid value");
    }


    public List<AudioInput> getInputs(){
        return inputs;
    }

    
    public void addOutput(AudioOutput output){
        if(output==null){
            throw new IllegalArgumentException("Audio input can't be null");
        }
        for( AudioOutput otp : outputs){
            if( otp.getId()==output.getId()){
                throw new IllegalStateException("output already in block");
            }
        }
        outputs.add(output);
        nextId++;
    }


    public void deleteOutput (int id)throws IllegalArgumentException{
        for( AudioOutput otp : outputs){
            if( otp.getId()==id){
                outputs.remove(otp);
                return;
            }
        }
        throw new IllegalArgumentException("invalid value");
    }


    public List<AudioOutput> getOutputs(){
        return outputs;
    }


    

}

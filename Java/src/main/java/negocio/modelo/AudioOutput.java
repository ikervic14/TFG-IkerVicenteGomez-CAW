package negocio.modelo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.jr.ob.impl.DeferredMap;

public class AudioOutput extends AudioComponent{



    public AudioOutput(int id, String name){
        super(id,name);

       
    }

   public AudioOutput(String json, int id){
        super(json,id);

   }
    public static  Map<String,String> getAvailableDevices(){
        HashMap<String,String> sources=new HashMap<String,String>();
        
        try{
            ProcessBuilder pb= new ProcessBuilder("./CAW2", "OUT");
            Process p= pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader( p.getInputStream()));
            String linea;
            while((linea=reader.readLine())!=null){
                String splited[]=linea.split(":-:");
                sources.put(splited[1],splited[0]);
            }
        }
        catch (Exception e){
            sources=null;
        }
        return sources;
    }


    public DeferredMap toJsonMap(){
        DeferredMap map= new DeferredMap(true);
        map.put("name",getName());

        return map;
    }
}

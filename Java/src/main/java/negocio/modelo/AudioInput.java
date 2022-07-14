package negocio.modelo;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.filechooser.FileSystemView;

import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import com.fasterxml.jackson.jr.ob.JSON;
import com.fasterxml.jackson.jr.ob.impl.DeferredMap;

public class AudioInput extends AudioComponent{
    private InputType type;

    public AudioInput(int id, String name,InputType type){
        super(id,name);
        if(type==null){
            throw new IllegalArgumentException("Type can't be null");
        }
        this.type=type;
       
    }
    public AudioInput(int id, String json){
        super(json, id);
        Map<String,Object> jsonM=null;
        
        try{
            jsonM= JSON.std.mapFrom(json);
        }catch(Exception e){
           throw new IllegalArgumentException("Invalid Json");
        }
        Object JsonType=jsonM.get("type");

        if(!JsonType.getClass().equals(String.class) ){
            throw new IllegalArgumentException("Invalid Json");
        }
        this.type=InputType.valueOf((String) JsonType);

    }

    public InputType getType(){
        return type;
    }

    public static  Map<String,String> getAvailableSources(InputType type){
        HashMap<String,String> sources=new HashMap<String,String>();
        if(type== InputType.APP){
            try{
                ProcessBuilder pb= new ProcessBuilder("./CAW1.exe");
                Process p= pb.start();
                BufferedReader reader = new BufferedReader(new InputStreamReader( p.getInputStream()));
                String linea;
                while((linea=reader.readLine())!=null){
                    String splited[]=linea.split(":-:");
                    File file= new File(splited[1]);
                    String name=file.getName();
                    String  shortName=name.substring(0,name.lastIndexOf('.'));
                    sources.put(splited[0],shortName);
                    Icon ic= FileSystemView.getFileSystemView().getSystemIcon(file);
                    File fileOut=new File("icons/"+shortName+".png");
                    if(ic.getIconWidth()>=0 && ic.getIconHeight()>=0){
                        BufferedImage bufImage= new BufferedImage(ic.getIconWidth(),ic.getIconHeight(),BufferedImage.TYPE_INT_RGB);
                        Graphics2D grafics= bufImage.createGraphics();
                        ic.paintIcon(null, grafics, 0, 0);
                        grafics.dispose();
                        ImageIO.write(bufImage,"png",fileOut);
                    }
                }
                p.waitFor();
            }
            catch (Exception e){
                sources=null;
            }
        }
        else if(type==InputType.INPUTDEVICE){
            try{
                ProcessBuilder pb= new ProcessBuilder("./CAW2", "IN");
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
        }
        return sources;
    }

    public DeferredMap toJsonMap(){
        DeferredMap map= new DeferredMap(true);
        map.put("name",getName());
        map.put("type",type.toString());
        return map;
    }
}

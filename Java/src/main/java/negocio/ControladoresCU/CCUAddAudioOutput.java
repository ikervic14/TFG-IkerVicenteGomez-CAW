package negocio.ControladoresCU;

import java.util.Map;

import negocio.modelo.AudioOutput;
import negocio.modelo.Block;
import negocio.modelo.CAW;

public class CCUAddAudioOutput {

    
    public static Map<String,String> getOutputDevices(){
        return AudioOutput.getAvailableDevices();
    }

    public static void addAudioOutput(String name){
        CAW instance= CAW.getInstance();
        Block block = instance.getSelectedBlock();
        block.addOutput(new AudioOutput(block.getNextId(), name));
    }
}
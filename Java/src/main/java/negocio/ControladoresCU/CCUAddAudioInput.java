package negocio.ControladoresCU;

import negocio.modelo.AudioInput;
import negocio.modelo.Block;
import negocio.modelo.CAW;
import negocio.modelo.InputType;

public class CCUAddAudioInput {


    public static void addEntrada(String name , String type){
        
        CAW instance = CAW.getInstance();
        Block block = instance.getSelectedBlock();
        block.addInput(new AudioInput(block.getNextId(), name, InputType.valueOf(type)));
        if(block.getExecution()){
            block.stop();
            block.execute();
        }
    }
}

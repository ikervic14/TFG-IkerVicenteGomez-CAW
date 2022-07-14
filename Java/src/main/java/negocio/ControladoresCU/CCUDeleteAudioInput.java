package negocio.ControladoresCU;

import negocio.modelo.Block;
import negocio.modelo.CAW;

public class CCUDeleteAudioInput {
    public static void  deleteInput(int id) throws IllegalArgumentException,IllegalStateException{
        CAW instance= CAW.getInstance();
        Block block = instance.getSelectedBlock();
        if(block==null){
            throw new IllegalStateException(" No block selected");
        }
        block.deleteInput(id);
        if(block.getExecution()){
            block.stop();
            block.execute();
        }
        


    }
}

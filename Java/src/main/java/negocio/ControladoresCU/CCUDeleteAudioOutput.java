package negocio.ControladoresCU;

import negocio.modelo.Block;
import negocio.modelo.CAW;

public class CCUDeleteAudioOutput {
    
    public static void  deleteOutput(int id){
        CAW instance= CAW.getInstance();
        Block block = instance.getSelectedBlock();
        if(block==null){
            throw new IllegalStateException(" No block selected");
        }
        block.deleteOutput(id);
        if(block.getExecution()){
            block.stop();
            block.execute();
        }

    }
}

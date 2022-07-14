package negocio.ControladoresCU;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import negocio.modelo.AudioInput;
import negocio.modelo.AudioOutput;
import negocio.modelo.Block;
import negocio.modelo.CAW;
import negocio.modelo.InputType;
import negocio.modelo.Profile;

public class CCUGetBlockConfiguration {


    public static Map<Integer,String> getBlocInputs(int id) throws IllegalArgumentException{
        CAW instance= CAW.getInstance();
        Profile profile= instance.getSelectedProfile();
        Block block= profile.getBlock(id);
        if(block==null){
            throw new IllegalArgumentException("No block with this id in  the selected profile");
        }
        instance.selectBlock(block);
        List<AudioInput> inputs = block.getInputs();
        HashMap<Integer,String> in= new HashMap<Integer,String>();
        for (AudioInput au : inputs){
            String name= "";
            if(au.getType()==InputType.APP){
                name = "App:-: ";
            }
            else{
                name = "InputDevice:-: ";
            }
            in.put(au.getId(),name+ au.getName());
        }
        return in;
    }


    public static Map<Integer,String> getBlocOutputs(int id){
        CAW instance= CAW.getInstance();
        Profile profile= instance.getSelectedProfile();
        Block block= profile.getBlock(id);
        instance.selectBlock(block);
        List<AudioOutput> outputs = block.getOutputs();
        HashMap<Integer,String> out= new HashMap<Integer,String>();
        for (AudioOutput au : outputs){
            out.put(au.getId(),au.getName());
        }
        return out;
    }


}

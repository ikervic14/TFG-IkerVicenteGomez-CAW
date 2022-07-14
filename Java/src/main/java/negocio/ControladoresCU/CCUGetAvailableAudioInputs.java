package negocio.ControladoresCU;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import negocio.modelo.AudioInput;
import negocio.modelo.InputType;

public class CCUGetAvailableAudioInputs {


    public static Map<String,String> getInputSources(String type) throws IllegalArgumentException{
        return AudioInput.getAvailableSources( InputType.valueOf(type));
    }

    public static List<String> getTypes(){
        ArrayList<String> list= new ArrayList<String>();
        InputType array[]= InputType.values();
        for (InputType inp : array ){
            list.add(inp.toString());
        }
        return list;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.paresvistacontrolador;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import negocio.ControladoresCU.CCUAddAudioInput;
import negocio.ControladoresCU.CCUAddAudioOutput;
import negocio.ControladoresCU.CCUAddBlockToProfile;
import negocio.ControladoresCU.CCUAddNewProfile;
import negocio.ControladoresCU.CCUDeleteAudioInput;
import negocio.ControladoresCU.CCUDeleteAudioOutput;
import negocio.ControladoresCU.CCUDeleteBlockFromProfile;
import negocio.ControladoresCU.CCUDeleteProfile;
import negocio.ControladoresCU.CCUExecuteProfile;
import negocio.ControladoresCU.CCUGetAvailableAudioInputs;
import negocio.ControladoresCU.CCUGetBlcoksFromProfile;
import negocio.ControladoresCU.CCUGetBlockConfiguration;
import negocio.ControladoresCU.CCUGetLoadedProfiles;
import negocio.ControladoresCU.CCULoadProfile;
import negocio.ControladoresCU.CCUSaveProfile;
import negocio.ControladoresCU.CCUStopProfile;

/**
 *
 * @author ikerc
 */
public class ControladorVista {
    private Vista vista;
    private int selectedProfile;
    private int selectedBlock;
    private int executingProfile;
    
    public ControladorVista(Vista v){
        vista=v;
        selectedProfile=-1;
        selectedBlock=-1;
        executingProfile=-1;
    }
    
    public void processEventNewProfile(){
        vista.showNewProfileDialog();
        
    }
    
    public void processEventNewProfileDialogClose(String result){
        if(result==null || result==""){
            return ;
        }
        try{
            CCUAddNewProfile.createProfile(result);
            reloadProfiles();   
        }
        catch(Exception e){
            vista.showAlert(e.getMessage());
        }
    }

    public void processEventLoadProfile(){
        vista.showFileSelector();
    }

    public void ProcessEventFileSelectorClose(int result, File selectedFile){
        if(result==JFileChooser.APPROVE_OPTION){
            try{
                CCULoadProfile.LoadProfile(selectedFile.toPath().toString());
                reloadProfiles();
            }
            catch(Exception e){
                vista.showAlert(e.getMessage());
            }
        }
         
    }

    private void reloadProfiles(){
        vista.clearProfiles();
        Map<Integer,String> map =CCUGetLoadedProfiles.getLoadedProfiles();
        for (int key:map.keySet() ){
            vista.addPerfil(map.get(key),key);
            
        } 
        vista.selectProfile(String.valueOf(selectedProfile)); 
        vista.setEjecucion(String.valueOf(executingProfile));
    }

    public void processEventSaveProfile(){
        if(selectedProfile==-1){
            vista.showAlert("No hay ningun perfil seleccionado");
        }
        else{
            try{
                if(CCUSaveProfile.hasFile(selectedProfile)){
                    CCUSaveProfile.save(selectedProfile);
                }
                else{
                    vista.showSaveSelector();
                }
            }
            catch(Exception e){
                vista.showAlert(e.getMessage());
            }
           
        }
    }

    public void ProcessEventSaveSelectorClose(int result, File selectedFile){
        if(result==JFileChooser.APPROVE_OPTION){
            try{
            CCUSaveProfile.save(selectedProfile, selectedFile.toPath().toString());
            }
            catch(Exception e){
                vista.showAlert(e.getMessage());
            }
        }
       
    }

    public void processEventStopProfile(){
        if(executingProfile>=0){
            try{
                CCUStopProfile.stopProfile(executingProfile);
                executingProfile=-1;
                vista.setEjecucion("a");
            }
            catch(Exception e){
                vista.showAlert(e.getMessage());
            }
        }
        else{
            vista.showAlert("No hay ningun perfil en ejecución");
        }
    }
      
    public void processEventSelectProfile(String id) {
        if(selectedProfile!=Integer.parseInt(id)){
            selectedProfile=Integer.parseInt(id);
            selectedBlock=-1;
            reloadBlocks();
            vista.selectProfile(id);

        
        }
    }

    private void reloadBlocks(){

        try{
        Map<Integer,String> map =CCUGetBlcoksFromProfile.getBlocksFromProfile(selectedProfile);
        vista.clearBlocks();
        for (int key:map.keySet() ){
            
            vista.addBlock(map.get(key),key);
            if(key==selectedBlock){
                vista.selectBlock(String.valueOf(selectedBlock));
            }
        } 
        }
        catch(Exception e){
            vista.showAlert(e.getMessage());
        }
        
    }

    public void processEventRemoveProfile(String  idStr, String name){
        int id=Integer.parseInt(idStr);
        int confirm= vista.showDeleteConfirm("¿Seguro que quieres elimiar el perfil "+ name+" ?");
        if(confirm== JOptionPane.YES_OPTION){
            try{
                CCUDeleteProfile.deleteProfile(id);
                vista.removeProfile(String.valueOf(id));
                if(selectedProfile==id){
                    vista.clearBlocks();
                    vista.clearMain();
                    selectedBlock=-1;
                    selectedProfile=-1;
                }
                if(executingProfile==id){
                    executingProfile=-1;
                }
            }
            catch(Exception e){
                vista.showAlert(e.getMessage());
            }
        }
    }

    public void processEventExecuteProfile(String idStr){
        int id=Integer.parseInt(idStr);
        if(executingProfile!=id){
            try{
                CCUExecuteProfile.executeProfile(id);
                executingProfile=id;
                vista.setEjecucion(String.valueOf(id));
            }
            catch(Exception e){
                vista.showAlert(e.getMessage());
            }
        }
    }

    public void processEventSelectBlock(String id){
        if(selectedBlock!=Integer.parseInt(id)){
            
            if(reloadConfiguration(Integer.parseInt(id))){
            
                vista.selectBlock(id);
                selectedBlock=Integer.parseInt(id);
            }

        
        }
    }
    

    private boolean reloadConfiguration(int id){
        try{
            Map<Integer,String> mapIn =CCUGetBlockConfiguration.getBlocInputs(id);
            Map<Integer,String> mapout =CCUGetBlockConfiguration.getBlocOutputs(id);
            vista.clearMain();

        for (int key:mapIn.keySet() ){
            String l[]=mapIn.get(key).split(":-:");
            boolean icon;
            if(l[0].equals("App"))
                icon=true;
            else if(l[0].equals("InputDevice"))
                icon=false;
            else{
                throw new IllegalArgumentException("Not a valid type");
            }
            vista.addInput(l[1],key,icon);
        } 
        for (int key:mapout.keySet() ){
            vista.addOutput(mapout.get(key),key);
        } 
        return true;
        }
        catch(Exception e){
            vista.showAlert(e.getMessage());
            return false;
        }
        
    }
    public void processEventAddBlock(){
        if (selectedProfile!=-1)
            vista.showNewBlockDialog();
    }


    public void processEventNewBlockDialogClose(String result){
        if(result==null || result==""){
            return ;
        }
        try{
            CCUAddBlockToProfile.addBlock(result);
            reloadBlocks();   
        }
        catch(Exception e){
            vista.showAlert(e.getMessage());
        }
    }

    public void processEventRemoveBlock(String idStr){
        int id=Integer.parseInt(idStr);
        int confirm= vista.showDeleteConfirm("¿Seguro que quieres elimiar el bloque ?");
        if(confirm== JOptionPane.YES_OPTION){
            try{
                CCUDeleteBlockFromProfile.deleteBlockFromProfile(id);
                vista.removeBlock(idStr);
                if(selectedBlock==id){
                    vista.clearMain();
                    selectedBlock=-1;
                }
            }
            catch(Exception e){
                vista.showAlert(e.getMessage());
            }
        }
    }

    public void processEventRemoveInput(String idStr){
        int id=Integer.parseInt(idStr);
        int confirm= vista.showDeleteConfirm("¿Seguro que quieres elimiar la entrada ?");
        if(confirm== JOptionPane.YES_OPTION){
            try{
                CCUDeleteAudioInput.deleteInput(id);
                vista.removeInput(idStr);
            }
            catch(Exception e){
                vista.showAlert(e.getMessage());
            }
        }
    }

    public void processEventRemoveOutput(String idStr){

        int id=Integer.parseInt(idStr);
        int confirm= vista.showDeleteConfirm("¿Seguro que quieres elimiar la salida ?");
        if(confirm== JOptionPane.YES_OPTION){
            try{
                CCUDeleteAudioOutput.deleteOutput(id);
                vista.removeOutput(idStr);
            }
            catch(Exception e){
                vista.showAlert(e.getMessage());
            }
        }
    }

    public void processEventAddOutput(){
        if(selectedBlock!=-1){
            try{
                Map<String,String> map=CCUAddAudioOutput.getOutputDevices();
                String names[] = new String[map.size()];
                int i=0;
                for(String key : map.keySet() ){
                    names[i]=map.get(key);
                    i++;
                }
                String name=vista.showOutputOptions(names);
                if(name!=null){
                    CCUAddAudioOutput.addAudioOutput(name);
                    reloadConfiguration(selectedBlock);
                }
            }
            catch(Exception e){
                vista.showAlert(e.getMessage());
            }
        }
        else{
            vista.showAlert("Ningun bloque seleccionado");
        }
    }

    public void processEventAddInput(){
        if(selectedBlock!=-1){
            try{
            List<String> lista=CCUGetAvailableAudioInputs.getTypes();
            vista.showInputTypeOptions(lista.toArray());
            }
            catch(Exception e){
                vista.showAlert(e.getMessage());
            }
        }
        else{
            vista.showAlert("Ningun bloque seleccionado");
        }
    }
    public void processEventSelectInputType(String result){
        if(result!=null){
            try{
                Map<String,String> map=CCUGetAvailableAudioInputs.getInputSources(result);
                vista.showInputOptions(map.values().toArray(),result);
            }
            catch(Exception e){
                vista.showAlert(e.getMessage());
            }
        }
    }

    public void processEventSelectInput(String result,String type){
        try{
            if(result!=null){
                CCUAddAudioInput.addEntrada(result, type);;
                reloadConfiguration(selectedBlock);
            }
        }
        catch(Exception e){
            vista.showAlert(e.getMessage());
        }
    }
}

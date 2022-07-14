/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.paresvistacontrolador;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.io.File;


import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;



/**
 *
 * @author ikerc
 */
public class Vista extends javax.swing.JFrame {

    /**
     * Creates new form Vista
     */
    private ControladorVista controlador;
    
    public Vista() {
        initComponents();
        controlador= new ControladorVista(this);
        
    }
    public void clearProfiles(){
        perfiles.removeAll();
    }
    public void addPerfil(String name,int id ){
        
        
        
        
       
        
        JLabel etiqueta2= new JLabel();
        etiqueta2.setText(" ");
        etiqueta2.setForeground(new java.awt.Color(0,255,0));
        etiqueta2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        etiqueta2.setFont(new java.awt.Font("Tahoma", 1, 9));
        
        JLabel buttonPlay= new JLabel();
        buttonPlay.setOpaque(false);
        buttonPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/play.png"))); // NOI18N
        buttonPlay.setMaximumSize(new java.awt.Dimension(15, 15));
        buttonPlay.setMinimumSize(new java.awt.Dimension(15, 15));
        buttonPlay.setName(String.valueOf(id)); // NOI18N
        buttonPlay.setPreferredSize(new java.awt.Dimension(15, 15));
        buttonPlay.setVisible(false);
        buttonPlay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                showButton(((Component)evt.getSource()).getName(),true);

            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                
                showButton(((Component)evt.getSource()).getName(),false);
            
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                controlador.processEventExecuteProfile(((Component)evt.getSource()).getName());
            }
        });
        
        
        JLabel buttonRemove= new JLabel();
        buttonRemove.setOpaque(false);
        buttonRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/remove.png"))); // NOI18N
        buttonRemove.setMaximumSize(new java.awt.Dimension(15, 15));
        buttonRemove.setMinimumSize(new java.awt.Dimension(15, 15));
        buttonRemove.setName(String.valueOf(id)); // NOI18N
        buttonRemove.setPreferredSize(new java.awt.Dimension(15, 15));
        buttonRemove.setName(String.valueOf(id));
        buttonRemove.setVisible(false);
        buttonRemove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                showButton(((Component)evt.getSource()).getName(),true);

            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                
                showButton(((Component)evt.getSource()).getName(),false);
            
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JLabel c= (JLabel) evt.getSource();
                controlador.processEventRemoveProfile(c.getName(),c.getText());
            }
        });
        
        JPanel espacio=new JPanel();
        espacio.setMaximumSize(new java.awt.Dimension(15, 15));
        espacio.setMinimumSize(new java.awt.Dimension(15, 15));
        espacio.setPreferredSize(new java.awt.Dimension(15, 15));
        espacio.setOpaque(false);
        
        
        
        JPanel pan2=new JPanel();
        pan2.setMaximumSize(new java.awt.Dimension(130, 15));
        pan2.setMinimumSize(new java.awt.Dimension(130, 15));
        pan2.setPreferredSize(new java.awt.Dimension(130, 15));
        pan2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 3));
        pan2.setOpaque(false);
        pan2.add(etiqueta2);
        pan2.add(buttonRemove);
        pan2.add(buttonPlay);
        pan2.add(espacio);
        
        JLabel etiqueta= new JLabel();
        if(name.length()>15){
            String shortName=name.substring(0, 12);
            shortName=shortName+"...";
            etiqueta.setText(shortName);
        }
        else{
        etiqueta.setText(name);
        }
        etiqueta.setForeground(new java.awt.Color(255,255,255));
        etiqueta.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);


        JPanel pan= new JPanel();
        pan.setMaximumSize(new java.awt.Dimension(230, 20));
        pan.setMinimumSize(new java.awt.Dimension(230, 20));
        pan.setPreferredSize(new java.awt.Dimension(230, 20));
        pan.setLayout(new java.awt.BorderLayout());
        pan.setBackground(new java.awt.Color(10,10,11));
        pan.setName(String.valueOf(id));
        pan.add(etiqueta, java.awt.BorderLayout.WEST);
        pan.add(pan2, java.awt.BorderLayout.LINE_END);
        pan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                showButton(((Component)evt.getSource()).getName(),true);

            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                
                showButton(((Component)evt.getSource()).getName(),false);
            
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                controlador.processEventSelectProfile(((Component)evt.getSource()).getName());
                
            
            }
        });
      
        perfiles.add(pan);
        Dimension d= new Dimension(perfiles.getWidth(),perfiles.getComponentCount()*20);
        perfiles.setPreferredSize(d);
        repaint();
        
        


    }
    public void showButton(String id,boolean modo){
        for(Component c : perfiles.getComponents()){
            if(c.getName().equals(id)){         
                if(c.getClass()==JPanel.class){
                    for( Component c1 : ((JPanel)c).getComponents()){
                        if(c1.getClass()==JPanel.class){
                            for(Component com : ((JPanel)c1).getComponents()){
                                if(com.getName()!= null){
                                    com.setVisible(modo);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public void setEjecucion(String id){
          for(Component c : perfiles.getComponents()){        
            if(c.getClass()==JPanel.class){
                for( Component c1 : ((JPanel)c).getComponents()){
                    if(c1.getClass()==JPanel.class){
                        for(Component com : ((JPanel)c1).getComponents()){
                            if(com.getName()== null){
                                if(com.getClass()==JLabel.class){
                                    if(c.getName().equals(id)){
                                        ((JLabel)com).setText(" En ejecución");
                                    }
                                    else{
                                         ((JLabel)com).setText("");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public void removeProfile(String id){
        for (Component c : perfiles.getComponents()){
            if(c.getName().equals(id)){
                perfiles.remove(c);
            }
        }
        perfiles.setVisible(false);
        perfiles.setVisible(true);
    }
    public void selectProfile(String id){
        for(Component c : perfiles.getComponents()){
            if(c.getName().equals(id)){   
                c.setBackground(new Color(50,50,50));
            }
            else{
                c.setBackground(new Color(10,10,11));
            }
        }
    }
    public void selectBlock(String id){
        for (Component c : Blocks.getComponents()){
            if(c.getClass()==JLabel.class && !c.getName().equals("add")){
            JLabel lab=(JLabel)c;
                if(!lab.getName().equals(id) ){

                    lab.setIcon(new ImageIcon(getClass().getResource("/Images/blockP.png")));
                    lab.setFont(new java.awt.Font("Tahoma", 1, 11));
                }
                else{
                    lab.setIcon(new ImageIcon(getClass().getResource("/Images/blockPSelected.png")));
                    lab.setFont(new java.awt.Font("Tahoma", 1, 12));
                }
            }
        }
        for (Component c : BlocksOver.getComponents()){
            if(c.getClass()==JPanel.class){
                JPanel p= (JPanel)c;
                if(!p.getName().equals( id ))
                    p.removeAll();
                else{
                    JLabel x= new JLabel();
                    x.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                    x.setForeground(new java.awt.Color(204, 0, 0));
                    x.setText("x");
                    x.setName(String.valueOf(id));
                    x.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            JLabel c= (JLabel) evt.getSource();
                            controlador.processEventRemoveBlock(c.getName());
                        }
                    });

                    
                    p.add(x);
                    p.setVisible(true); 

                }
                
            }
        }
        
        Blocks.setVisible(false);
        Blocks.setVisible(true);
        BlocksOver.setVisible(false);
        BlocksOver.setVisible(true);
    }

    public void addBlock(String name, int id ){
        JLabel lab= new JLabel();
        lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/blockP.png"))); // NOI18N
        if(name.length()>9){
            String shortName=name.substring(0, 8);
            shortName=shortName+"...";
            lab.setText(shortName);
        }
        else{
            lab.setText(name);
        }
        lab.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lab.setIconTextGap(0);
        lab.setName(String.valueOf(id)); // NOI18N
        lab.setFont(new java.awt.Font("Tahoma", 1, 11));
        
        lab.setPreferredSize(new Dimension(94, 23));
        Blocks.add(lab,Blocks.getComponentCount()-1);

        JPanel pan= new JPanel();

        pan.setMinimumSize(new java.awt.Dimension(94, 23));
        pan.setName(String.valueOf(id));
        pan.setOpaque(false);
        pan.setPreferredSize(new java.awt.Dimension(94, 23));
        pan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                controlador.processEventSelectBlock(((Component)evt.getSource()).getName());
            }
        });
        pan.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 2));

        BlocksOver.add(pan);
        
        Blocks.setVisible(false);
        Blocks.setVisible(true);
        BlocksOver.setVisible(false);
        BlocksOver.setVisible(true);
     }      

    public void showNewProfileDialog(){
        String result=JOptionPane.showInputDialog(this,"Introduce el nombre del nuevo Perfil");
        controlador.processEventNewProfileDialogClose(result);
    }
    public void showNewBlockDialog(){
        String result=JOptionPane.showInputDialog(this,"Introduce el nombre del nuevo Bloque");
        controlador.processEventNewBlockDialogClose(result);
    }
    public void showFileSelector(){
        selectFile.setCurrentDirectory(new File("Perfiles"));
        int result=selectFile.showOpenDialog(this);
        controlador.ProcessEventFileSelectorClose(result,selectFile.getSelectedFile());
    }
    public void showSaveSelector(){
        saveFile.setCurrentDirectory(new File("Perfiles"));
        int result=saveFile.showOpenDialog(this);
        controlador.ProcessEventSaveSelectorClose(result,saveFile.getCurrentDirectory());
    }
    public void showAlert(String message){
        JOptionPane.showMessageDialog(this, message);
    }

    public void clearBlocks(){
        for (Component c : Blocks.getComponents()){
            if(!c.getName().equals("add"))
                Blocks.remove(c);
        }
        BlocksOver.removeAll();
        BlocksOver.setVisible(false);
        BlocksOver.setVisible(true);
        Blocks.setVisible(false);
        Blocks.setVisible(true);
    }
    public void clearMain(){
        for (Component c : entradas.getComponents()){
            if(!c.getName().equals("add"))
                entradas.remove(c);
        }
        for (Component c : salidas.getComponents()){
            if(!c.getName().equals("add"))
                salidas.remove(c);
        }

        entradas.setVisible(false);
        entradas.setVisible(true);
        salidas.setVisible(false);
        salidas.setVisible(true);
    }

    public int showDeleteConfirm(String text){
        return JOptionPane.showConfirmDialog(this, text, "Eliminar", JOptionPane.YES_NO_OPTION);
    }

    public void addInput( String name,int id,boolean icon){
        JLabel Inicon= new JLabel();
        if(icon && new javax.swing.ImageIcon("icons/"+name.strip()+".png").getIconHeight()>0){
           
            Inicon.setIcon(new javax.swing.ImageIcon("icons/"+name.strip()+".png")); 
        
        }
        else{
            Inicon.setIcon(new javax.swing.ImageIcon("icons/mic.png"));
        }
        JPanel panIc = new JPanel();
        panIc.setOpaque(false);
        panIc.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));
        panIc.add(Inicon);
        
        JPanel panDel= new JPanel();
        panDel.setOpaque(false);
        panDel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 0));

        JLabel delIcon= new JLabel();
        delIcon.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        delIcon.setForeground(new java.awt.Color(255, 51, 51));
        delIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        delIcon.setText("x");
        delIcon.setMaximumSize(new java.awt.Dimension(16, 16));
        delIcon.setMinimumSize(new java.awt.Dimension(16, 16));
        delIcon.setPreferredSize(new java.awt.Dimension(16, 16));
        delIcon.setName(String.valueOf(id));
        delIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                controlador.processEventRemoveInput(((Component)evt.getSource()).getName());
            }
        });
        panDel.add(delIcon);

        JPanel panTop= new JPanel();
        panTop.setOpaque(false);
        panTop.setPreferredSize(new java.awt.Dimension(16, 16));
        panTop.setLayout(new java.awt.GridLayout());

        panTop.add(panIc);
        panTop.add(panDel);

        JLabel labName=new JLabel();
        labName.setText(name);
        labName.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jPanel9.add(labName, java.awt.BorderLayout.CENTER);
        
        JPanel panelIn= new JPanel();
        panelIn.setBackground(new java.awt.Color(204, 255, 204));
        panelIn.setLayout(new java.awt.BorderLayout());

        panelIn.add(panTop, java.awt.BorderLayout.PAGE_START);
        panelIn.add(labName, java.awt.BorderLayout.CENTER);

        JPanel panOut= new JPanel();
        panOut.setBackground(new java.awt.Color(204, 255, 204));
        panOut.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 255, 204), 15, true));
        panOut.setOpaque(false);
        panOut.setName(String.valueOf(id));
        panOut.setLayout(new java.awt.GridLayout());
        panOut.setMinimumSize(new Dimension(141, 60));
        panOut.setMaximumSize(new Dimension(141, 60));
        panOut.setPreferredSize(new Dimension(141, 60));
        panOut.add(panelIn);

        entradas.add(panOut,entradas.getComponentCount()-1);
        entradas.setVisible(false);
        entradas.setVisible(true);
        
    }
    public void addOutput( String name,int id){

        JLabel Inicon= new JLabel();
        Inicon.setIcon(new javax.swing.ImageIcon("icons/alt.png"));
        JPanel panIc = new JPanel();
        panIc.setOpaque(false);
        panIc.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));
        panIc.add(Inicon);
        
        JPanel panDel= new JPanel();
        panDel.setOpaque(false);
        panDel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 0));

        JLabel delIcon= new JLabel();
        delIcon.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        delIcon.setForeground(new java.awt.Color(255, 51, 51));
        delIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        delIcon.setText("x");
        delIcon.setMaximumSize(new java.awt.Dimension(16, 16));
        delIcon.setMinimumSize(new java.awt.Dimension(16, 16));
        delIcon.setPreferredSize(new java.awt.Dimension(16, 16));
        delIcon.setName(String.valueOf(id));
        delIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                controlador.processEventRemoveOutput(((Component)evt.getSource()).getName());
            }
        });
        panDel.add(delIcon);

        JPanel panTop= new JPanel();
        panTop.setOpaque(false);
        panTop.setPreferredSize(new java.awt.Dimension(16, 16));
        panTop.setLayout(new java.awt.GridLayout());

        panTop.add(panIc);
        panTop.add(panDel);

        JLabel labName=new JLabel();
        labName.setText(name);
        labName.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jPanel9.add(labName, java.awt.BorderLayout.CENTER);

        JPanel panelIn= new JPanel();
        panelIn.setBackground(new java.awt.Color(153,153,255));
        panelIn.setLayout(new java.awt.BorderLayout());

        panelIn.add(panTop, java.awt.BorderLayout.PAGE_START);
        panelIn.add(labName, java.awt.BorderLayout.CENTER);

        JPanel panOut= new JPanel();
        panOut.setBackground(new java.awt.Color(153,153,255));
        panOut.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153,153,255), 15, true));
        panOut.setOpaque(false);
        panOut.setName(String.valueOf(id));
        panOut.setLayout(new java.awt.GridLayout());
        panOut.setMinimumSize(new Dimension(141, 60));
        panOut.setMaximumSize(new Dimension(141, 60));
        panOut.setPreferredSize(new Dimension(141, 60));
        panOut.add(panelIn);

        salidas.add(panOut,salidas.getComponentCount()-1);
        salidas.setVisible(false);
        salidas.setVisible(true);
    }

    public void removeBlock(String id){
        for (Component c : Blocks.getComponents()){
            if(c.getName().equals(id)){
                Blocks.remove(c);
            }
        }
        for (Component c : BlocksOver.getComponents()){
            if(c.getName().equals(id)){
                BlocksOver.remove(c);
            }
        }
        BlocksOver.setVisible(false);
        BlocksOver.setVisible(true);
        Blocks.setVisible(false);
        Blocks.setVisible(true);
    }
    public void removeInput(String id){
        for (Component c : entradas.getComponents()){
            if(c.getName().equals(id)){
                entradas.remove(c);
            }
        }
        
        entradas.setVisible(false);
        entradas.setVisible(true);

    }

    public void removeOutput(String id){
        for (Component c : salidas.getComponents()){
            if(c.getName().equals(id)){
                salidas.remove(c);
            }
        }
        
        salidas.setVisible(false);
        salidas.setVisible(true);

    }

    public String showOutputOptions(String names[]){
         String result=(String)  JOptionPane.showInputDialog(this, "selecciona la salida deseada", "salidas",  JOptionPane.QUESTION_MESSAGE, null, names, names[0]);
        return result;
    }

    public void showInputTypeOptions(Object lista[]){
        String result=(String) JOptionPane.showInputDialog(this, "selecciona el tipo de entrada deseado", "Tipos de entradas",  JOptionPane.QUESTION_MESSAGE, null, lista, lista[0]);
        controlador.processEventSelectInputType(result);
    }

    public void showInputOptions(Object lista[],String type){
        String result=(String) JOptionPane.showInputDialog(this, "selecciona la entrada deseada", "entradas",  JOptionPane.QUESTION_MESSAGE, null, lista, lista[0]);
        controlador.processEventSelectInput(result,type);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        

        selectFile = new javax.swing.JFileChooser();
        saveFile = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        NewButton = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        OpenButton = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        SaveButton = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        StopButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        perfiles = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        Blocks = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        BlocksOver = new javax.swing.JPanel();
        addInPannel = new javax.swing.JPanel();
        AddInInPanel = new javax.swing.JPanel();
        AddInIcon = new javax.swing.JLabel();
        addOutPannel = new javax.swing.JPanel();
        AddOutInPanel = new javax.swing.JPanel();
        AddOutIcon = new javax.swing.JLabel();
        
        addInPannel.setBackground(new java.awt.Color(204, 255, 204));
        addInPannel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 255, 204), 15, true));
        addInPannel.setOpaque(false);
        addInPannel.setLayout(new java.awt.GridLayout());
        addInPannel.setName("add");

        AddInInPanel.setBackground(new java.awt.Color(204, 255, 204));
        AddInInPanel.setLayout(new java.awt.BorderLayout());

        AddInIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AddInIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/nuevo.png"))); // NOI18N
        
        AddInInPanel.add(AddInIcon, java.awt.BorderLayout.CENTER);
        AddInInPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                controlador.processEventAddInput();
            }
        });
        addInPannel.add(AddInInPanel); 

        addOutPannel.setBackground(new java.awt.Color(153,153,255));
        addOutPannel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153,153,255), 15, true));
        addOutPannel.setOpaque(false);
        addOutPannel.setLayout(new java.awt.GridLayout());
        addOutPannel.setName("add");

        AddOutInPanel.setBackground(new java.awt.Color(153,153,255));
        AddOutInPanel.setLayout(new java.awt.BorderLayout());

        AddOutIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AddOutIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/nuevo.png"))); // NOI18N
        AddOutInPanel.add(AddOutIcon, java.awt.BorderLayout.CENTER);
        AddOutInPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                controlador.processEventAddOutput();
            }
        });
        addOutPannel.add(AddOutInPanel); 
        
        selectFile.setAcceptAllFileFilterUsed(false);
        selectFile.setBackground(java.awt.Color.black);
        selectFile.setCurrentDirectory(new java.io.File("C:\\Program Files\\NetBeans-12.0\\.\\Perfiles"));
        selectFile.setDialogTitle("");
        selectFile.setFileFilter(new FileNameExtensionFilter("json","json"));

        saveFile.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        saveFile.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(false);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBackground(new java.awt.Color(10, 10, 11));
        jPanel1.setMaximumSize(new java.awt.Dimension(32767, 80));
        jPanel1.setMinimumSize(new java.awt.Dimension(0, 80));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 80));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        jPanel3.setBackground(new java.awt.Color(10, 10, 11));
        jPanel3.setMaximumSize(new java.awt.Dimension(80, 80));
        jPanel3.setMinimumSize(new java.awt.Dimension(80, 80));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 10));

        jPanel7.setBackground(new java.awt.Color(50, 50, 51));
        jPanel7.setMaximumSize(new java.awt.Dimension(60, 60));
        jPanel7.setMinimumSize(new java.awt.Dimension(60, 60));
        jPanel7.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        NewButton.setBackground(new java.awt.Color(10, 10, 11));
        NewButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        NewButton.setForeground(new java.awt.Color(204, 0, 0));
        NewButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/nuevo.png"))); // NOI18N
        NewButton.setText("Nuevo");
        NewButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 0, 0), new java.awt.Color(204, 0, 0), new java.awt.Color(160, 0, 0), new java.awt.Color(160, 0, 0)));
        NewButton.setBorderPainted(false);
        NewButton.setDefaultCapable(false);
        NewButton.setFocusPainted(false);
        NewButton.setFocusable(false);
        NewButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        NewButton.setMaximumSize(new java.awt.Dimension(60, 60));
        NewButton.setMinimumSize(new java.awt.Dimension(60, 60));
        NewButton.setPreferredSize(new java.awt.Dimension(60, 60));
        NewButton.setRequestFocusEnabled(false);
        NewButton.setRolloverEnabled(false);
        NewButton.setVerifyInputWhenFocusTarget(false);
        NewButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        NewButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                NewButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                NewButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                NewButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                NewButtonMouseReleased(evt);
            }
        });
        NewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewButtonActionPerformed(evt);
            }
        });
        jPanel7.add(NewButton);

        jPanel3.add(jPanel7);

        jPanel1.add(jPanel3);

        jPanel8.setBackground(new java.awt.Color(10, 10, 11));
        jPanel8.setMaximumSize(new java.awt.Dimension(80, 80));
        jPanel8.setMinimumSize(new java.awt.Dimension(80, 80));
        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 10));

        jPanel9.setBackground(new java.awt.Color(50, 50, 51));
        jPanel9.setMaximumSize(new java.awt.Dimension(60, 60));
        jPanel9.setMinimumSize(new java.awt.Dimension(60, 60));
        jPanel9.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel9MouseEntered(evt);
            }
        });
        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        OpenButton.setBackground(new java.awt.Color(10, 10, 11));
        OpenButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        OpenButton.setForeground(new java.awt.Color(204, 0, 0));
        OpenButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/carpeta.png"))); // NOI18N
        OpenButton.setText("Abrir");
        OpenButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 0, 0), new java.awt.Color(204, 0, 0), new java.awt.Color(160, 0, 0), new java.awt.Color(160, 0, 0)));
        OpenButton.setBorderPainted(false);
        OpenButton.setDefaultCapable(false);
        OpenButton.setFocusPainted(false);
        OpenButton.setFocusable(false);
        OpenButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        OpenButton.setIconTextGap(10);
        OpenButton.setMaximumSize(new java.awt.Dimension(60, 60));
        OpenButton.setMinimumSize(new java.awt.Dimension(60, 60));
        OpenButton.setPreferredSize(new java.awt.Dimension(60, 60));
        OpenButton.setRequestFocusEnabled(false);
        OpenButton.setRolloverEnabled(false);
        OpenButton.setVerifyInputWhenFocusTarget(false);
        OpenButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        OpenButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                OpenButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                OpenButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                OpenButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                OpenButtonMouseReleased(evt);
            }
        });
        OpenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenButtonActionPerformed(evt);
            }
        });
        jPanel9.add(OpenButton);

        jPanel8.add(jPanel9);

        jPanel1.add(jPanel8);

        jPanel17.setBackground(new java.awt.Color(10, 10, 11));
        jPanel17.setForeground(new java.awt.Color(10, 10, 11));
        jPanel17.setMaximumSize(new java.awt.Dimension(80, 80));
        jPanel17.setMinimumSize(new java.awt.Dimension(80, 80));
        jPanel17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 10));

        jPanel18.setBackground(new java.awt.Color(50, 50, 51));
        jPanel18.setMaximumSize(new java.awt.Dimension(60, 60));
        jPanel18.setMinimumSize(new java.awt.Dimension(60, 60));
        jPanel18.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel18.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        SaveButton.setBackground(new java.awt.Color(10, 10, 11));
        SaveButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        SaveButton.setForeground(new java.awt.Color(204, 0, 0));
        SaveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/save.png"))); // NOI18N
        SaveButton.setText("Guardar");
        SaveButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 0, 0), new java.awt.Color(204, 0, 0), new java.awt.Color(160, 0, 0), new java.awt.Color(160, 0, 0)));
        SaveButton.setBorderPainted(false);
        SaveButton.setDefaultCapable(false);
        SaveButton.setFocusPainted(false);
        SaveButton.setFocusable(false);
        SaveButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        SaveButton.setIconTextGap(5);
        SaveButton.setMaximumSize(new java.awt.Dimension(60, 60));
        SaveButton.setMinimumSize(new java.awt.Dimension(60, 60));
        SaveButton.setPreferredSize(new java.awt.Dimension(60, 60));
        SaveButton.setRequestFocusEnabled(false);
        SaveButton.setRolloverEnabled(false);
        SaveButton.setVerifyInputWhenFocusTarget(false);
        SaveButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        SaveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SaveButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SaveButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                SaveButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                SaveButtonMouseReleased(evt);
            }
        });
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });
        jPanel18.add(SaveButton);

        jPanel17.add(jPanel18);

        jPanel1.add(jPanel17);

        jPanel10.setBackground(new java.awt.Color(10, 10, 11));
        jPanel10.setForeground(new java.awt.Color(10, 10, 11));
        jPanel10.setMaximumSize(new java.awt.Dimension(80, 80));
        jPanel10.setMinimumSize(new java.awt.Dimension(80, 80));
        jPanel10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 10));

        jPanel11.setBackground(new java.awt.Color(50, 50, 51));
        jPanel11.setMaximumSize(new java.awt.Dimension(60, 60));
        jPanel11.setMinimumSize(new java.awt.Dimension(60, 60));
        jPanel11.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        StopButton.setBackground(new java.awt.Color(10, 10, 11));
        StopButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        StopButton.setForeground(new java.awt.Color(204, 0, 0));
        StopButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/stop.png"))); // NOI18N
        StopButton.setText("Parar");
        StopButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 0, 0), new java.awt.Color(204, 0, 0), new java.awt.Color(160, 0, 0), new java.awt.Color(160, 0, 0)));
        StopButton.setBorderPainted(false);
        StopButton.setDefaultCapable(false);
        StopButton.setFocusPainted(false);
        StopButton.setFocusable(false);
        StopButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        StopButton.setMaximumSize(new java.awt.Dimension(60, 60));
        StopButton.setMinimumSize(new java.awt.Dimension(60, 60));
        StopButton.setPreferredSize(new java.awt.Dimension(60, 60));
        StopButton.setRequestFocusEnabled(false);
        StopButton.setRolloverEnabled(false);
        StopButton.setVerifyInputWhenFocusTarget(false);
        StopButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        StopButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                StopButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                StopButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                StopButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                StopButtonMouseReleased(evt);
            }
        });
        StopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StopButtonActionPerformed(evt);
            }
        });
        jPanel11.add(StopButton);

        jPanel10.add(jPanel11);

        jPanel1.add(jPanel10);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(204, 102, 0));
        jPanel2.setFocusable(false);
        jPanel2.setMinimumSize(new java.awt.Dimension(0, 200));
        jPanel2.setPreferredSize(new java.awt.Dimension(0, 200));
        jPanel2.setRequestFocusEnabled(false);
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(new java.awt.Color(10, 10, 11));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 0, 0), 2, true), "Perfiles", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(204, 0, 0))); // NOI18N
        jPanel4.setMaximumSize(new java.awt.Dimension(250, 32767));
        jPanel4.setMinimumSize(new java.awt.Dimension(250, 100));
        jPanel4.setPreferredSize(new java.awt.Dimension(250, 500));

        scroll.setBackground(new java.awt.Color(10, 10, 11));
        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setToolTipText("");
        scroll.setMaximumSize(new java.awt.Dimension(148, 32767));
        scroll.setMinimumSize(new java.awt.Dimension(148, 5));
        scroll.setPreferredSize(new java.awt.Dimension(148, 100));

        perfiles.setBackground(new java.awt.Color(10, 10, 11));
        perfiles.setMaximumSize(new java.awt.Dimension(178, 1000));
        perfiles.setMinimumSize(new java.awt.Dimension(178, 0));
        perfiles.setPreferredSize(new java.awt.Dimension(178, 0));
        perfiles.setLayout(new javax.swing.BoxLayout(perfiles, javax.swing.BoxLayout.PAGE_AXIS));
        scroll.setViewportView(perfiles);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel4, java.awt.BorderLayout.LINE_START);

        jPanel5.setLayout(new java.awt.BorderLayout());
        jPanel5.setBackground(new java.awt.Color(250, 10, 11));
        jPanel12.setBackground(new java.awt.Color(10, 10, 11));
        jPanel12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(150, 150, 150), 3, true));
        jPanel12.setFocusable(false);
        jPanel12.setPreferredSize(new java.awt.Dimension(800, 10000));
        jPanel12.setLayout(new java.awt.BorderLayout());

        jPanel16.setBackground(new java.awt.Color(204, 204, 204));

        jPanel16.setLayout(new java.awt.GridLayout(2,0));
        entradas= new JPanel();
        
        entradas.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT,10,40));
        entradas.add(addInPannel);
        entradas.setOpaque(false);
        salidas= new JPanel();
        salidas.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT,10,10));
        salidas.add(addOutPannel);
        salidas.setOpaque(false);
        jPanel16.add(entradas);
        jPanel16.add(salidas);
        jPanel12.add(jPanel16, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel12, java.awt.BorderLayout.CENTER);

        jLayeredPane1.setMinimumSize(new java.awt.Dimension(21, 22));
        jLayeredPane1.setPreferredSize(new java.awt.Dimension(50, 20));

        jScrollPane1.setBorder(null);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        Blocks.setBackground(new java.awt.Color(10, 10, 11));
        Blocks.setMaximumSize(new java.awt.Dimension(32767, 20));
        Blocks.setMinimumSize(new java.awt.Dimension(0, 20));
        Blocks.setPreferredSize(new java.awt.Dimension(50, 20));
        Blocks.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/blockPAdd.png"))); // NOI18N
        jLabel5.setName("add"); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        Blocks.add(jLabel5);

        jScrollPane1.setViewportView(Blocks);

        BlocksOver.setBackground(new java.awt.Color(0, 102, 102));
        BlocksOver.setMaximumSize(new java.awt.Dimension(32767, 20));
        BlocksOver.setMinimumSize(new java.awt.Dimension(50, 20));
        BlocksOver.setOpaque(false);
        BlocksOver.setPreferredSize(new java.awt.Dimension(50, 20));
        BlocksOver.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));




        jLayeredPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(BlocksOver, javax.swing.JLayeredPane.PALETTE_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BlocksOver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 750, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BlocksOver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel5.add(jLayeredPane1, java.awt.BorderLayout.PAGE_START);

        jPanel2.add(jPanel5, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);
        pack();
        setSize(new Dimension(1016,500));
        

    }// </editor-fold>//GEN-END:initComponents

    private void NewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewButtonActionPerformed
        controlador.processEventNewProfile();
    }//GEN-LAST:event_NewButtonActionPerformed

    private void NewButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NewButtonMouseEntered
       NewButton.setContentAreaFilled(false);
       NewButton.setFont(new java.awt.Font("Tahoma", 1, 12)); 
    }//GEN-LAST:event_NewButtonMouseEntered

    private void NewButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NewButtonMouseExited
        NewButton.setBorderPainted(false);
        NewButton.setContentAreaFilled(true);
        NewButton.setFont(new java.awt.Font("Tahoma", 1, 11));
    }//GEN-LAST:event_NewButtonMouseExited

    private void NewButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NewButtonMousePressed
   
      NewButton.setBorderPainted(true);
    }//GEN-LAST:event_NewButtonMousePressed

    private void OpenButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OpenButtonMouseEntered
       OpenButton.setContentAreaFilled(false);
        OpenButton.setFont(new java.awt.Font("Tahoma", 1, 12)); 
    }//GEN-LAST:event_OpenButtonMouseEntered

    private void OpenButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OpenButtonMouseExited
        OpenButton.setBorderPainted(false);
        OpenButton.setContentAreaFilled(true);
        OpenButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    }//GEN-LAST:event_OpenButtonMouseExited

    private void OpenButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OpenButtonMousePressed
         OpenButton.setBorderPainted(true);
 
    }//GEN-LAST:event_OpenButtonMousePressed

    private void OpenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenButtonActionPerformed
        controlador.processEventLoadProfile();
        
        
        
    }//GEN-LAST:event_OpenButtonActionPerformed

    private void StopButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StopButtonMouseEntered
       StopButton.setContentAreaFilled(false);
       StopButton.setFont(new java.awt.Font("Tahoma", 1, 12)); 
    }//GEN-LAST:event_StopButtonMouseEntered

    private void StopButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StopButtonMouseExited
        StopButton.setBorderPainted(false);
        StopButton.setContentAreaFilled(true);
        StopButton.setFont(new java.awt.Font("Tahoma", 1, 11)); 
    }//GEN-LAST:event_StopButtonMouseExited

    private void StopButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StopButtonMousePressed
        StopButton.setBorderPainted(true);
       
    }//GEN-LAST:event_StopButtonMousePressed

    private void StopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StopButtonActionPerformed
        controlador.processEventStopProfile();
    }//GEN-LAST:event_StopButtonActionPerformed

    private void jPanel9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseEntered

    }//GEN-LAST:event_jPanel9MouseEntered



    private void NewButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NewButtonMouseReleased
      NewButton.setBorderPainted(false);
    }//GEN-LAST:event_NewButtonMouseReleased

    private void OpenButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OpenButtonMouseReleased
        OpenButton.setBorderPainted(false);
    }//GEN-LAST:event_OpenButtonMouseReleased

    private void StopButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StopButtonMouseReleased
         StopButton.setBorderPainted(false);
    }//GEN-LAST:event_StopButtonMouseReleased

    private void SaveButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaveButtonMouseEntered
       SaveButton.setContentAreaFilled(false);
       SaveButton.setFont(new java.awt.Font("Tahoma", 1, 12)); 
    }//GEN-LAST:event_SaveButtonMouseEntered

    private void SaveButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaveButtonMouseExited
        SaveButton.setBorderPainted(false);
        SaveButton.setContentAreaFilled(true);
        SaveButton.setFont(new java.awt.Font("Tahoma", 1, 11)); 
    }//GEN-LAST:event_SaveButtonMouseExited

    private void SaveButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaveButtonMousePressed
        SaveButton.setBorderPainted(true);
    }//GEN-LAST:event_SaveButtonMousePressed

    private void SaveButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaveButtonMouseReleased
       SaveButton.setBorderPainted(false);
    }//GEN-LAST:event_SaveButtonMouseReleased

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
        controlador.processEventSaveProfile();
    }//GEN-LAST:event_SaveButtonActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        controlador.processEventAddBlock();
    }//GEN-LAST:event_jLabel5MouseClicked




    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
              
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vista().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Blocks;
    private javax.swing.JPanel BlocksOver;
    private javax.swing.JButton NewButton;
    private javax.swing.JButton OpenButton;
    private javax.swing.JButton SaveButton;
    private javax.swing.JButton StopButton;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;

    private javax.swing.JPanel perfiles;
    private javax.swing.JFileChooser saveFile;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JFileChooser selectFile;
    private javax.swing.JPanel entradas;
    private javax.swing.JPanel salidas;
    private javax.swing.JLabel AddInIcon;
    private javax.swing.JPanel AddInInPanel;
    private javax.swing.JPanel addInPannel;
    private javax.swing.JLabel AddOutIcon;
    private javax.swing.JPanel AddOutInPanel;
    private javax.swing.JPanel addOutPannel;
    // End of variables declaration//GEN-END:variables
}

package com;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import com.fasterxml.jackson.jr.ob.impl.DeferredMap;

import negocio.modelo.AudioInput;
import negocio.modelo.AudioOutput;
import negocio.modelo.Block;
import negocio.modelo.InputType;

public class BlockTest {
    

    @Test
    public void ConstructorTectCorrect(){
        Block block = new Block("name",1);
        assertNotNull(block);
        assertEquals("name",block.getName());
        assertEquals(1, block.getId());
        assertEquals(0, block.getNumInputs());
    }

    @Test
    public void ConstructorTectCorrect2(){
        Block block = new Block("",-1);
        assertNotNull(block);
        assertEquals("",block.getName());
        assertEquals(-1, block.getId());
        assertEquals(0,block.getInputs().size());
        assertEquals(0,block.getOutputs().size());
        assertEquals(0, block.getNumInputs());
    }

    @Test(expected = IllegalArgumentException.class)
    public void ConstructorTectIncorrect(){
        new Block(null,-1);
    }

    @Test
    public void ConstructorJsonCorrect(){
        Block block= new Block(1,"{\"inputs\":[{\"name\":\"nombre\",\"type\":\"APP\"},{\"name\":\"nombre2\",\"type\":\"APP\"}],\"outputs\":[{\"name\":\"nombre3\"},{\"name\":\"nombre4\"}],\"name\":\"nombre\"}");
        assertNotNull(block);
        assertEquals("nombre",block.getName());
        assertEquals(1, block.getId());
        assertEquals(2,block.getInputs().size());
        assertEquals(2,block.getOutputs().size());
        assertEquals(2, block.getNumInputs());
    }

    @Test
    public void ConstructorJsonCorrect2(){
        Block block= new Block(1,"{\"inputs\":[{\"name\":\"nombre\",\"type\":\"APP\"},{\"name\":\"nombre2\",\"type\":\"APP\"}],\"outputs\":[],\"name\":\"\"}");
        assertNotNull(block);
        assertEquals("",block.getName());
        assertEquals(1, block.getId());
        assertEquals(2,block.getInputs().size());
        assertEquals(0,block.getOutputs().size());
        assertEquals(2, block.getNumInputs());
    }

    @Test(expected = IllegalArgumentException.class)
    public void ConstructorJsonIncorrect(){
        new Block(1,"{\"inputs\":[{\"name\":\"nombre\",\"type\":\"APP\"},{\"name\":\"nombre2\",\"type\":\"APP\"}],\"outputs\":\"s\",\"name\":\"\"}");

    }
    @Test(expected = IllegalArgumentException.class)
    public void ConstructorJsonIncorrect2(){
        new Block(1,"hgh");

    }

    @Test
    public void ToJsonMapCorrect2(){
        Block block = new Block("name",1);
        DeferredMap map=block.toJsonMap();
        assertEquals("name",map.get("name"));
        assertEquals(ArrayList.class, map.get("inputs").getClass());
        assertEquals(ArrayList.class, map.get("outputs").getClass());

    }
    @Test
    public void SetNameCorrect(){
        Block block = new Block("name",1);
        assertEquals("name",block.getName());
        block.setName("name2");
        assertEquals("name2",block.getName());
    }
    @Test
    public void SetNameCorrect2(){
        Block block = new Block("name",1);
        assertEquals("name",block.getName());
        block.setName("");
        assertEquals("",block.getName());
    }


    @Test(expected = IllegalArgumentException.class)
    public void SetNameIncorrect(){
        Block block = new Block("name",1);
        block.setName(null);
    }

    @Test
    public void ExecutingTestCorrect(){
        Block block = new Block("name",1);
        assertFalse(block.getExecution());
        block.execute();
        assertTrue(block.getExecution());
        block.stop();
        assertFalse(block.getExecution());
        block.stop();
        assertFalse(block.getExecution());
    }

    @Test(expected = IllegalStateException.class)
    public void ExecutingTestIncorrect(){
        Block block = new Block("name",1);
        block.execute();
        block.execute();
    }

    @Test
    public void AddInputTestCorrect(){
        Block block = new Block("name",1);
        AudioInput in= new AudioInput(0, "s", InputType.APP);
        assertEquals(0, block.getInputs().size());
        block.addInput(in);
        assertEquals(1, block.getInputs().size());
        assertEquals(in, block.getInputs().get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void AddInputTestIncorrect(){
        Block block = new Block("name",1);
        block.addInput(null);
        
    }

    @Test(expected = IllegalStateException.class)
    public void AddInputTestIncorrect2(){
        Block block = new Block("name",1);
        AudioInput in= new AudioInput(0, "s", InputType.APP);
        block.addInput(in);
        block.addInput(in);
    }

    @Test
    public void DeleteInputTestCorrect(){
        Block block = new Block("name",1);
        AudioInput in= new AudioInput(0, "s", InputType.APP);
        block.addInput(in);
        assertEquals(1, block.getInputs().size());
        block.deleteInput(0);
        assertEquals(0, block.getInputs().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void DeleteInputTestIncorrect(){
        Block block = new Block("name",1);
        AudioInput in= new AudioInput(0, "s", InputType.APP);
        block.addInput(in);
        block.deleteInput(3);
    }







    @Test
    public void AddOutputTestCorrect(){
        Block block = new Block("name",1);
        AudioOutput out= new AudioOutput(0, "s");
        assertEquals(0, block.getOutputs().size());
        block.addOutput(out);
        assertEquals(1, block.getOutputs().size());
        assertEquals(out, block.getOutputs().get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void AddOutputTestIncorrect(){
        Block block = new Block("name",1);
        block.addOutput(null);
        
    }

    @Test(expected = IllegalStateException.class)
    public void AddOutputTestIncorrect2(){
        Block block = new Block("name",1);
        AudioOutput in= new AudioOutput(0, "s");
        block.addOutput(in);
        block.addOutput(in);
    }

    @Test
    public void DeleteOutputTestCorrect(){
        Block block = new Block("name",1);
        AudioOutput in= new AudioOutput(0, "s");
        block.addOutput(in);
        assertEquals(1, block.getOutputs().size());
        block.deleteOutput(0);
        assertEquals(0, block.getOutputs().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void DeleteOutputTestIncorrect(){
        Block block = new Block("name",1);
        AudioOutput in= new AudioOutput(0, "s");
        block.addOutput(in);
        block.deleteOutput(3);
    }
}

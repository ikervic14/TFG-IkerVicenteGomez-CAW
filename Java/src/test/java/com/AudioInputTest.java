package com;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import org.junit.Test;

import com.fasterxml.jackson.jr.ob.impl.DeferredMap;

import negocio.modelo.AudioInput;
import negocio.modelo.InputType;

public class AudioInputTest {
    

    @Test
    public void ConstructorTestCorrect(){
        AudioInput in= new AudioInput(1, "name", InputType.APP);
        assertNotNull(in);
        assertEquals(1, in.getId());
        assertEquals("name",in.getName());
        assertEquals(InputType.APP,in.getType());
    }

    @Test
    public void ConstructorTestCorrect2(){
        AudioInput in= new AudioInput(-1, "", InputType.INPUTDEVICE);
        assertNotNull(in);
        assertEquals(-1, in.getId());
        assertEquals("",in.getName());
        assertEquals(InputType.INPUTDEVICE,in.getType());
    }

    @Test(expected = IllegalArgumentException.class)
    public void ConstructorTestIncorrect(){
       new AudioInput(1, null, InputType.INPUTDEVICE);
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void ConstructorTestIncorrect2(){
       new AudioInput(1, "name", null);
        
    }

    @Test
    public void ConstructorJsonTestCorrect(){
        AudioInput in= new AudioInput(1, "{\"name\":\"name\",\"type\":\"APP\"}");
        assertNotNull(in);
        assertEquals(1, in.getId());
        assertEquals("name",in.getName());
        assertEquals(InputType.APP,in.getType());
    }

    @Test
    public void ConstructorJsonTestCorrect2(){
        AudioInput in= new AudioInput(1, "{\"name\":\"\",\"type\":\"INPUTDEVICE\"}");
        assertNotNull(in);
        assertEquals(1, in.getId());
        assertEquals("",in.getName());
        assertEquals(InputType.INPUTDEVICE,in.getType());
    }

    @Test(expected = IllegalArgumentException.class)
    public void ConstructorJsonTestIncorrect(){
        new AudioInput(1, "{\"name\":\"\",\"type\":\"gh\"}");

    }

    @Test(expected = IllegalArgumentException.class)
    public void ConstructorJsonTestIncorrect2(){
        new AudioInput(1, "{\"name\":4,\"type\":\"APP\"}");

    }

    @Test(expected = IllegalArgumentException.class)
    public void ConstructorJsonTestIncorrect3(){
        new AudioInput(1, "df");

    }


    @Test
    public void ToJsonMapTestCorrect2(){
        AudioInput in= new AudioInput(1, "name", InputType.APP);
        DeferredMap map= in.toJsonMap();
        assertNotNull(map);
        assertEquals("name", map.get("name"));
        assertEquals("APP",map.get("type"));
    }
}

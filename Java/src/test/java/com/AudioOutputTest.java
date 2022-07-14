package com;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import org.junit.Test;

import com.fasterxml.jackson.jr.ob.impl.DeferredMap;

import negocio.modelo.AudioOutput;

public class AudioOutputTest {
    

    @Test
    public void ConstructorTestCorrect(){
        AudioOutput in= new AudioOutput(1, "name");
        assertNotNull(in);
        assertEquals(1, in.getId());
        assertEquals("name",in.getName());
    }

    @Test
    public void ConstructorTestCorrect2(){
        AudioOutput in= new AudioOutput(-1, "");
        assertNotNull(in);
        assertEquals(-1, in.getId());
        assertEquals("",in.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void ConstructorTestIncorrect(){
       new AudioOutput(1, null);
        
    }


    @Test
    public void ConstructorJsonTestCorrect(){
        AudioOutput in= new AudioOutput( "{\"name\":\"name\"}",1);
        assertNotNull(in);
        assertEquals(1, in.getId());
        assertEquals("name",in.getName());
    }

    @Test
    public void ConstructorJsonTestCorrect2(){
        AudioOutput in= new AudioOutput( "{\"name\":\"\"}",1);
        assertNotNull(in);
        assertEquals(1, in.getId());
        assertEquals("",in.getName());

    }



    @Test(expected = IllegalArgumentException.class)
    public void ConstructorJsonTestIncorrect(){
        new AudioOutput( "{\"name\":4,\"type\":\"APP\"}",1);

    }

    @Test(expected = IllegalArgumentException.class)
    public void ConstructorJsonTestIncorrect2(){
        new AudioOutput( "df",1);

    }


    @Test
    public void ToJsonMapTestCorrect2(){
        AudioOutput in= new AudioOutput(1, "name");
        DeferredMap map= in.toJsonMap();
        assertNotNull(map);
        assertEquals("name", map.get("name"));

    }
}

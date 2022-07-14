package com;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import negocio.modelo.AudioComponent;
public class AudioComponentTest {

    @Test
    public void ConstructorTestCorrect(){
        AudioComponent com= new AudioComponent(2, "name");
        assertNotNull(com);
        assertEquals(2, com.getId());
        assertEquals("name",com.getName());
    }

    @Test
    public void ConstructorTestCorrect2(){
        AudioComponent com= new AudioComponent(-5, "");
        assertNotNull(com);
        assertEquals(-5, com.getId());
        assertEquals("",com.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void ConstructorTestIncorrect(){
        new AudioComponent(2, null);
    }

    @Test
    public void ConstructorJsonTestCorrect(){
        AudioComponent com= new AudioComponent("{\"name\":\"name\"}", 3);
        assertNotNull(com);
        assertEquals(3, com.getId());
        assertEquals("name",com.getName());
    }

    @Test
    public void ConstructorJsonTestCorrect2(){
        AudioComponent com= new AudioComponent("{\"name\":\"\"}", -3);
        assertNotNull(com);
        assertEquals(-3, com.getId());
        assertEquals("",com.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void ConstructorJsonTestIncorrect(){
        new AudioComponent("{\"name\":5}", 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ConstructorJsonTestIncorrect2(){
        new AudioComponent(null, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ConstructorJsonTestIncorrect3(){
        new AudioComponent("sdsds", 3);
    }

    @Test
    public void SetNameTestCorrect(){
        AudioComponent com= new AudioComponent(1,"name");
        assertEquals("name",com.getName());
        com.setName("a");
        assertEquals("a",com.getName());
    }

    @Test
    public void SetNameTestCorrect2(){
        AudioComponent com= new AudioComponent(1,"name");
        assertEquals("name",com.getName());
        com.setName("");
        assertEquals("",com.getName());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void SetNameTestIncorrect(){
        AudioComponent com= new AudioComponent(1,"name");
        com.setName(null);

    }
}

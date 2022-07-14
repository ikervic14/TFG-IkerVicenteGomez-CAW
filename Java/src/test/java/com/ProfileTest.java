package com;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import negocio.modelo.Block;
import negocio.modelo.Profile;

public class ProfileTest {
    

    @Test
    public void ConstructorTestCorrect(){
        Profile p = new Profile(0, "name");
        assertEquals(0, p.getId());
        assertEquals(0, p.getBlocks().size());
        assertEquals("name", p.getName());
        assertFalse(p.getExecution());
    }

    @Test(expected = IllegalArgumentException.class)
    public void ConstructorTestIncorrect(){
        new Profile(0, null);

    }

    @Test
    public void ConstructorJsonTestCorrect(){
        Profile p = new Profile("./ejemplo.json",0 );
        assertEquals(0, p.getId());
        assertEquals(2, p.getBlocks().size());
        assertEquals("perfil", p.getName());
        assertFalse(p.getExecution());
    }

    @Test(expected = IllegalArgumentException.class)
    public void ConstructorJsonTestIncorrect(){
        new Profile("./perfil.json",0 );
    }

    @Test(expected = IllegalArgumentException.class)
    public void ConstructorJsonTestIncorrect2(){
        new Profile("",0 );
    }

    @Test
    public void AddBlockTestCorrect(){
        Profile p = new Profile(0, "name");
        assertEquals(0, p.getBlocks().size());
        Block b= new Block("name", 0);
        p.addBlock(b);
        assertEquals(1, p.getBlocks().size());
        assertEquals(b, p.getBlocks().get(0));
        assertEquals(b, p.getBlock(0));
    }

    @Test(expected = IllegalStateException.class)
    public void AddBlockTestIncorrect(){
        Profile p = new Profile(0, "name");
        Block b= new Block("name", 0);
        p.addBlock(b);
        p.addBlock(b);
    }

    @Test(expected = IllegalArgumentException.class)
    public void AddBlockTestIncorrect2(){
        Profile p = new Profile(0, "name");
        p.addBlock(null);
    }

    @Test(expected = IllegalStateException.class)
    public void GetBlockTestIncorrect(){
        Profile p = new Profile(0, "name");
        p.getBlock(2);
    }

    @Test
    public void DeleteBlockTestCorrect(){
        Profile p = new Profile(0, "name");
        Block b= new Block("name", 0);
        p.addBlock(b);
        assertEquals(1, p.getBlocks().size());
        p.deleteBlock(0);
        assertEquals(0, p.getBlocks().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void DeleteBlockTestIncorrect(){
        Profile p = new Profile(0, "name");
        p.deleteBlock(0);
    }

    @Test
    public void ExecutingTestCorrect(){
        Profile p = new Profile(0,"name");
        Block b= new Block("name", 3);
        p.addBlock(b);
        assertFalse(p.getExecution());
        p.execute();
        assertTrue(p.getExecution());
        assertTrue(b.getExecution());
        p.stopBlock(3);
        assertTrue(p.getExecution());
        assertFalse(b.getExecution());
        p.stop();
        assertFalse(p.getExecution());
        p.stop();
        assertFalse(p.getExecution());
    }

    @Test(expected = IllegalStateException.class)
    public void ExecutingTestIncorrect(){
        Profile p = new Profile(1,"name");
        p.execute();
        p.execute();
    }

    @Test
    public void SaveTestCorrect(){
        Profile p = new Profile(1,"name");
        assertNull(p.getFile());
        p.save(".");
        assertNotNull(p.getFile());
    }

    @Test(expected = IllegalArgumentException.class)
    public void SaveTestIncorrect(){
        Profile p = new Profile(1,"name");
        p.save(null);
    }

    @Test(expected = IllegalStateException.class)
    public void SaveTestIncorrect2(){
        Profile p = new Profile(1,"name");
        p.save();
    }

    @Test(expected = IllegalArgumentException.class)
    public void SaveTestIncorrect3(){
        Profile p = new Profile(1,"name");
        p.save("fdsfdsfdsfs");
    }


}

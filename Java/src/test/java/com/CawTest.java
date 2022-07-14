package com;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import negocio.modelo.Block;
import negocio.modelo.CAW;
import negocio.modelo.Profile;

public class CawTest {
    @Test
    public void GetInstanceTestCorrect(){
        CAW caw= CAW.getInstance();
        assertNotNull(caw);
        CAW caw2= CAW.getInstance();
        assertNotNull(caw2);
        assertEquals(caw,caw2);
        assertEquals(0,caw.getProfiles().size());
        assertNull(caw.getSelectedBlock());
        assertNull(caw.getSelectedProfile());
        assertNull(caw.getExecutingProfile());
    }

    @Test
    public void AddProfileTestCorrect(){
        CAW caw= CAW.getInstance();
        assertNotNull(caw);
        Profile p= new Profile(0, "name");
        assertEquals(0,caw.getProfiles().size());
        caw.addProfile(p);
        assertEquals(1,caw.getProfiles().size());
        assertEquals(p,caw.getProfiles().get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void AddProfileTestIncorrect(){
        CAW caw= CAW.getInstance();
        caw.addProfile(null);
    }
    @Test(expected = IllegalStateException.class)
    public void AddProfileTestIncorrect2(){
        CAW caw= CAW.getInstance();
        Profile p= new Profile(0, "n");
        caw.addProfile(p);
        caw.addProfile(p);
    }
    @Test
    public void DeleteProfileTestCorrect(){
        CAW caw= CAW.getInstance();
        Profile p= new Profile(0, "name");
        int a=caw.getProfiles().size();
        caw.addProfile(p);
        assertEquals(a+1,caw.getProfiles().size());
        caw.deleteProfile(p);
        assertEquals(a,caw.getProfiles().size());

    }

    @Test(expected = IllegalArgumentException.class)
    public void DeleteProfileTestIncorrect(){
        CAW caw= CAW.getInstance();
        caw.deleteProfile(null);
    }

    @Test(expected = IllegalStateException.class)
    public void DeleteProfileTestIncorrect2(){
        CAW caw= CAW.getInstance();
        Profile p= new Profile(3, "name");
        caw.deleteProfile(p);
    }

    @Test 
    public void ExecuteProfileTestCorrect(){
        CAW caw= CAW.getInstance();
        Profile p= new Profile(0, "name");
        assertNull(caw.getExecutingProfile());
        caw.executeProfile(p);
        assertEquals(p, caw.getExecutingProfile());
        caw.executeProfile(null);
        assertNull(caw.getExecutingProfile());
    }

    @Test 
    public void SelectProfileTestCorrect(){
        CAW caw= CAW.getInstance();
        Profile p= new Profile(0, "name");
        assertNull(caw.getSelectedProfile());
        caw.selectProfile(p);
        assertEquals(p, caw.getSelectedProfile());
        caw.selectProfile(null);
        assertNull(caw.getSelectedProfile());
    }

    @Test 
    public void SelectBlockTestCorrect(){
        CAW caw= CAW.getInstance();
        Block b= new Block("name",0);
        assertNull(caw.getSelectedBlock());
        caw.selectBlock(b);
        assertEquals(b, caw.getSelectedBlock());
        caw.selectBlock(null);
        assertNull(caw.getSelectedBlock());
    }

    

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dkit.oopca5.client;

import com.dkit.oopca5.core.Course;
import com.dkit.oopca5.core.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author markb
 */
public class CourseChoicesManagerTest
{
    
    public CourseChoicesManagerTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of getStudentChoices method, of class CourseChoicesManager.
     */
    @Test
    public void testGetStudentChoices()
    {
        System.out.println("getStudentChoices");
        String caoNumber = "10000002";
        StudentManager studentManager = new StudentManager();
        CourseManager courseManager = new CourseManager();
        CourseChoicesManager instance = new CourseChoicesManager(studentManager,courseManager);
        List<String> expResult = new ArrayList<>();
        expResult.add("MU567");
        List<String> result = instance.getStudentChoices(caoNumber);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateChoices method, of class CourseChoicesManager.
     */
    @Test
    public void testUpdateChoices() throws Exception
    {
        System.out.println("updateChoices");
        int caoNumber = 10000013;
        StudentManager studentManager = new StudentManager();
        CourseManager courseManager = new CourseManager();
        CourseChoicesManager instance = new CourseChoicesManager(studentManager,courseManager);
        List<String> result = new ArrayList<>();
        result.add("DK821");
        instance.updateChoices(caoNumber, result);
        List<String> expResult = new ArrayList<>();
        expResult = instance.getStudentChoices("10000013");

        assertEquals(expResult, result);
    }


    /**
     * Test of login method, of class CourseChoicesManager.
     */
    @Test
    public void testLogin() throws Exception
    {
        System.out.println("login");
        int caoNumber = 10000001;
        String password = "Password1";
        StudentManager studentManager = new StudentManager();
        CourseManager courseManager = new CourseManager();
        CourseChoicesManager instance = new CourseChoicesManager(studentManager,courseManager);
        boolean expResult = true;
        boolean result = instance.login(caoNumber, password);
        assertEquals(expResult, result);

    }

    /**
     * Test of register method, of class CourseChoicesManager.
     */
    @Test
    public void testRegister()
    {
        System.out.println("register");
        Student S = new Student(12345677,"1998-09-11","Password12");
        StudentManager studentManager = new StudentManager();
        CourseManager courseManager = new CourseManager();
        CourseChoicesManager instance = new CourseChoicesManager(studentManager,courseManager);
        boolean expResult = true;
        boolean result = instance.register(S);
        assertEquals(expResult, result);

    }
    
}

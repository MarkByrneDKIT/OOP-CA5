/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dkit.oopca5.client;

import com.dkit.oopca5.Exceptions.DaoException;
import com.dkit.oopca5.core.Student;
import com.dkit.oopca5.server.MySqlCourseDao;
import com.dkit.oopca5.server.MySqlStudentDao;
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
public class StudentManagerTest
{
    
    public StudentManagerTest()
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
     * Test of getStudent method, of class StudentManager.
     */
    @Test
    public void testGetStudent() throws DaoException {
        System.out.println("getStudent");
        int caoNumber = 10000001;
        MySqlStudentDao instance = new MySqlStudentDao();
        Student expResult = new Student(10000001,"2001-09-04","Password1");
        Student result = instance.findStudent(caoNumber);
        assertEquals(expResult, result);
        //Student{caoNumber=10000001, dateOfBirth='2001-09-04', password='Password1'}
    }
    
}

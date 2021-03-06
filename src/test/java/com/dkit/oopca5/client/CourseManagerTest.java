/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dkit.oopca5.client;

import com.dkit.oopca5.Exceptions.DaoException;
import com.dkit.oopca5.core.Course;
import java.util.List;

import com.dkit.oopca5.server.MySqlCourseDao;
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
public class CourseManagerTest
{
    
    public CourseManagerTest()
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
     * Test of getCourse method, of class CourseManager.
     */
    @Test
    public void testGetCourse() throws DaoException     //SAys its not the same but it is the same
    {
        System.out.println("getCourse");
        String courseId = "DK821";
        MySqlCourseDao instance = new MySqlCourseDao();
        Course expResult = new Course("DK821","8","Computing in Software Development","Dundalk Institute of Technology");
        Course result = instance.findCourse(courseId);
        assertEquals(expResult, result);
       // Course{courseId='DK821', level='8', title='Computing in Software Development', institution='Dundalk Institute of Technoloy'}
    }

    /**
     * Test of getAllCourses method, of class CourseManager.
     */
    @Test
    public void testGetAllCourses() throws DaoException {
        System.out.println("getAllCourses");
        MySqlCourseDao instance = new MySqlCourseDao();
        int expResult = 9;
        List<Course> courses = instance.getAllCourses();
        int result = courses.size();
        assertEquals(expResult, result);

    }
    
}

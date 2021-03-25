package com.dkit.oopca5.client;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import com.dkit.oopca5.core.Course;
import com.dkit.oopca5.core.Student;
import com.dkit.oopca5.server.CourseDaoInterface;
import com.dkit.oopca5.server.MySqlCourseDao;
import com.dkit.oopca5.Exceptions.DaoException;

/**
 * CoursesManager
 * This software component Encapsulates the storage and management of
 * all courses available through the CAO system.
 * Only administrators would typically be allowed to update this data,
 * but other users can get a COPY of all the courses by calling getAllCourses().
 * The Web Client would need this data to display the course codes,
 * course titles and institutions to the student.
 */

public class CourseManager {
    //hashmap<string,course> like "DK821"

    private HashMap<String,Course> courseMap = new HashMap<String, Course>();


    // Store all the Course details.
    // Requires fast access given courseId.

    public CourseManager() {
        CourseDaoInterface courseDao = new MySqlCourseDao();

        try {
            List<Course> courseList = courseDao.getAllCourses();
            for(Course course : courseList)
            {
                courseMap.put(course.getCourseId(), course);
            }


        } catch (
                DaoException e) {
            e.printStackTrace();
        }

    }

    public Course getCourse(String courseId )
    {
       CourseDaoInterface courseDao = new MySqlCourseDao();
        try {
            Course course = courseDao.findCourse(courseId);
            return course;
        } catch (
                DaoException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Course> getAllCourses()
    {
        ArrayList<Course> clonedList = new ArrayList<>(); //clones list of courses

        for(Map.Entry<String, Course> entry : courseMap.entrySet())
        {
            Course course = entry.getValue();
            clonedList.add(new Course(course));
        }
        return clonedList;
    }

    public void addCourse(Course course)
    {
        if(course != null)
        {
            courseMap.put(course.getCourseId(), new Course(course));
        }
        //   else
        //  {
        //       throw new illegalArgumentException();
        //   }
    }

    public void removeCourse(String courseId)
    {
        courseMap.remove(courseId);
    }


}








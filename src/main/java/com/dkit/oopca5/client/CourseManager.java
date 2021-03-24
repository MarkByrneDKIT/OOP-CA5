package com.dkit.oopca5.client;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import com.dkit.oopca5.core.Course;
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
        courseMap = new HashMap<>();

        // Hardcode some values to get started
        // load from text file "courses.dat" and populate coursesMap
    }

    public Course getCourse(String courseId )
    {
        Course course = courseMap.get(courseId);
        return new Course(course); //clone
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


    public void saveCoursesToFile()
    {
        ArrayList<Course> clonedList = new ArrayList<>(); //cloned list of students

        for(Map.Entry<String, Course> entry : courseMap.entrySet())
        {
            Course course = entry.getValue();
            clonedList.add(new Course(course));
        }

        try(BufferedWriter courseFile = new BufferedWriter(new FileWriter("Courses.txt") ))
        {

            for(Course course : clonedList)
            {
                courseFile.write(course.getCourseId() + " " + course.getLevel() + " " + course.getTitle() + " " + course.getInstitution());
                courseFile.write("\n");
            }
        }
        catch(IOException ioe)
        {
            System.out.println("Could not save courses.");
        }
    }

    protected void loadCoursesFromFile()
    {
        System.out.println("Reading course DB from file...");

        try {
            Scanner courseFile = new Scanner(new File("Courses.txt"));

            while (courseFile.hasNext())
            {


                String courseID = courseFile.next();

                String level = courseFile.next();
                String title= courseFile.next();
                String institution= courseFile.next();


                System.out.println("Course ID: " + courseID + " Level: " + level + " Title: " + title + " Institution: " + institution);
                Course course = new Course(courseID, level, title, institution);
                courseMap.put(course.getCourseId(), new Course(course));

            }
            System.out.println("All courses loaded");
            courseFile.close();
            //     System.out.println("All Courses: " + courseMap); // print them all

        }
        catch (IOException e)
        {
            System.out.println("IOException thrown in loadCoursesFromFile() "+e.getMessage());
        }

    }


    // editCourse(courseId);       // not required for this iteration

}








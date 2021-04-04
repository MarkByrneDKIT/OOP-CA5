package com.dkit.oopca5.client;


import com.dkit.oopca5.Exceptions.DaoException;
import com.dkit.oopca5.core.Course;
import com.dkit.oopca5.core.Student;
import com.dkit.oopca5.server.MySqlStudentDao;
import com.dkit.oopca5.server.StudentDaoInterface;
import com.dkit.oopca5.server.MySqlCourseChoicesDAO;
import com.dkit.oopca5.server.CourseChoiceDAOInterface;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




import java.util.*;

public class CourseChoicesManager {

    // reference to constructor injected studentManager
    private StudentManager studentManager;

    // reference to constructor injected courseManager
    private CourseManager courseManager;

    // Store all the Course details -  fast access
    private HashMap<String, List<Course>> courseDetails = new HashMap<>();

    // caoNumber, course selection list - for fast access
    private HashMap<Integer, List<String>> selectedChoices = new HashMap<>();

    // CourseChoicesManager DEPENDS on both the StudentManager and CourseManager to access
    // student details and course details.  So, we receive a reference to each via
    // the constructor.
    // This is called "Dependency Injection", meaning that we
    // inject (or pass in) objects that this class requires to do its job.
    //
    CourseChoicesManager(StudentManager studentManager, CourseManager courseManager) {
        this.studentManager = studentManager;
        this.courseManager = courseManager;

    }

    public List<String> getStudentChoices(String caoNumber)
    {
        List<String> courseChoices = new ArrayList<>();

        CourseChoiceDAOInterface choicesDao = new MySqlCourseChoicesDAO();
        try {
             courseChoices = choicesDao.findAllChoices(caoNumber);
            return courseChoices;

        } catch (
                DaoException e) {
            e.printStackTrace();
        }

        return null;

       // return selectedChoices.get(caoNumber);
    }

    public void updateChoices(int caoNumber, List<String> choices) throws DaoException {
        CourseChoiceDAOInterface choicesDao = new MySqlCourseChoicesDAO();

        for(int i = 0; i < choices.size();i++)
        {
            choicesDao.updateChoices(caoNumber,choices.get(i));
        }

       // selectedChoices.put(caoNumber, choices);
    }

    public List<Course> getAllCourses()
    {
        return courseManager.getAllCourses();
    }

    boolean login(int caoNumber, String password) throws DaoException
    {
        StudentDaoInterface studentDao = new MySqlStudentDao();

        boolean loggedIn = false;

        try
        {
            loggedIn = studentDao.login(caoNumber, password);
        }
        catch (DaoException e)
        {
            e.printStackTrace();
        }
        return loggedIn;
    }

    boolean register(Student S)
    {
        StudentDaoInterface studentDao = new MySqlStudentDao();
        boolean studentRegister = false;

        try {
            studentRegister = studentDao.registerStudent(S);



        } catch (
                DaoException e) {
            e.printStackTrace();
        }
        return studentRegister;
    }
}



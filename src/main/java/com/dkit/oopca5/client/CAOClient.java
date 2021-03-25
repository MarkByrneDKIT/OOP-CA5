package com.dkit.oopca5.client;

/* The client package should contain all code and classes needed to run the Client
 */

/* The CAOClient offers students a menu and sends messages to the server using TCP Sockets
 */

import com.dkit.oopca5.Exceptions.DaoException;
import com.dkit.oopca5.core.Course;
import com.dkit.oopca5.core.Student;
import com.dkit.oopca5.server.MySqlStudentDao;
import com.dkit.oopca5.server.StudentDaoInterface;
import com.dkit.oopca5.server.MySqlCourseDao;
import com.dkit.oopca5.server.CourseDaoInterface;


import java.util.List;
import java.util.Scanner;

public class CAOClient
{
    private static Scanner keyboard = new Scanner(System.in);
    private static Scanner kb = new Scanner(System.in);

    public static void main(String[] args)
    {
        //load students
        StudentManager studentManager = new StudentManager();

        CourseManager courseManager = new CourseManager();

        Student s = studentManager.getStudent(10000001);
        System.out.println("Student: 1000001: " + s);

        Course c = courseManager.getCourse("DK821");
        System.out.println("Course: DK821: " + c);


        loginMenu();
        mainMenu();

    }

    private static void loginMenu()
    {
        StudentManager studentManager = new StudentManager();

        CourseManager courseManager = new CourseManager();

        CourseChoicesManager choicesManager = new CourseChoicesManager(studentManager, courseManager);

        LoginMenuOptions optionSelect = LoginMenuOptions.CONTINUE;

        while(optionSelect != LoginMenuOptions.QUIT)
        {
            try
            {
                loginMenu2();
                optionSelect = LoginMenuOptions.values()[Integer.parseInt(keyboard.nextLine().trim())];
                switch(optionSelect)
                {
                    case REGISTER:
                        System.out.print("Please Enter Your CaoNumber: ");
                        int newCaoNum = kb.nextInt();
                        System.out.print("Please Enter your Date of Birth: ");
                        String newDoB = kb.next();
                        System.out.print("Please Enter Your Password: ");
                        String newPassword = kb.next();

                        Student newS = new Student(newCaoNum, newDoB, newPassword);

                        if(choicesManager.register(newS) == true)
                        {
                            System.out.println("Successfully Registered!");
                            optionSelect = LoginMenuOptions.QUIT;
                        }
                        else
                        {
                            System.out.println("Failed to register");
                        }
                        break;

                    case LOGIN:
                        System.out.print("Please Enter Your CaoNumber: ");
                        String caoNum = kb.next();
                        System.out.println("Please Enter Your Password: ");
                        String password = kb.next();
                        break;

                    case QUIT:
                        break;

                    default:
                        System.out.println("Selection out of range. Try again");
                }
            }
            catch(IllegalArgumentException e)
            {
                System.out.println("Selection out of range. Please try again.");
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
                System.out.println("Selection out of range. Please try again.");
            }
        }
    }

    private static void mainMenu()
    {
        StudentManager studentManager = new StudentManager();

        CourseManager courseManager = new CourseManager();

        CourseChoicesManager choicesManager = new CourseChoicesManager(studentManager, courseManager);

        MenuOptions selectOption = MenuOptions.CONTINUE;

        while(selectOption != MenuOptions.QUIT)
        {
            try
            {
                menu();
                selectOption = MenuOptions.values()[Integer.parseInt(keyboard.nextLine().trim())];

                switch (selectOption)
                {
                    case DISPLAY_COURSE:
                        System.out.print("Please Enter the CourseId of the Course You Would Like To Display: ");
                        String courseId = kb.next();
                        System.out.println(courseManager.getCourse(courseId));

                        break;

                    case DISPLAY_ALL_COURSES:
                        List<Course> courses = courseManager.getAllCourses();
                        for(int i = 0; i < choicesManager.getAllCourses().size(); i++)
                        {
                            System.out.println(courses.get(i));
                        }
                        break;


                    case DISPLAY_CURRENT_CHOICES:

                        break;

                    case UPDATE_CURRENT_CHOICES:

                        break;


                    case QUIT:

                        break;


                    default:
                        System.out.println("Selection out of range. Try again");

                }
            }
            catch(IllegalArgumentException e)
            {
                System.out.println("Selection out of range. Please try again.");
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
                System.out.println("Selection out of range. Please try again.");
            }
        }
    }

    private static void loginMenu2()
    {
        System.out.println("\n Enter: ");
        System.out.println(("\t1. Register"));
        System.out.println(("\t2. Login"));
        System.out.println(("\t3. Quit"));
        System.out.print(("\n Selection ->"));
        System.out.println();
    }

    private static void menu ()
    {
        System.out.println("\n Enter: ");
        System.out.println(("\t1. Display Course"));
        System.out.println(("\t2. Display All Courses"));
        System.out.println(("\t3. Display All Current Choices"));
        System.out.println(("\t4. Update Choices"));
        System.out.println(("\t6. Quit"));
        System.out.print(("\n Selection ->"));
        System.out.println();
    }
}

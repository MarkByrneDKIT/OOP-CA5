package com.dkit.oopca5.client;

/* The client package should contain all code and classes needed to run the Client
 */

/* The CAOClient offers students a menu and sends messages to the server using TCP Sockets
 */

import com.dkit.oopca5.Exceptions.DaoException;
import com.dkit.oopca5.core.Colours;
import com.dkit.oopca5.core.Course;
import com.dkit.oopca5.core.Student;
import com.dkit.oopca5.client.RegexChecker;
import com.dkit.oopca5.server.MySqlCourseChoicesDAO;
import com.dkit.oopca5.server.MySqlCourseDao;
import com.dkit.oopca5.server.MySqlStudentDao;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CAOClient
{
    private static Scanner keyboard = new Scanner(System.in);
    private static Scanner kb = new Scanner(System.in);

    public static void main(String[] args)
    {

        RegexChecker regexChecker = new RegexChecker();


        Student currentStudent = new Student(0,"","");

        LoginMenuOptions optionSelect = LoginMenuOptions.CONTINUE;

        while(optionSelect != LoginMenuOptions.QUIT)
        {
            try
            {
                loginMenu2();

                MySqlStudentDao studentDao = new MySqlStudentDao();

                optionSelect = LoginMenuOptions.values()[Integer.parseInt(keyboard.nextLine().trim())];
                switch(optionSelect)
                {
                    case REGISTER:
                        boolean success1 = false;
                        boolean success2 = false;
                        boolean success3 = false;
                        Student newS = new Student(0,"","");
                        int newCaoNum = 0;
                        String newDoB = "";
                        String newPassword = "";

                        while(success1 == false && success2 == false && success3 == false)
                        {
                            try {
                                String pattern1 = "^[0-9]{8}$";
                                System.out.print("Please Enter Your CaoNumber: ");
                                newCaoNum = kb.nextInt();
                                String caoNumString = Integer.toString(newCaoNum);
                                if(RegexChecker.checkFormat(caoNumString,pattern1))
                                {
                                    success1 = true;
                                }
                            }
                            catch (IllegalArgumentException e) {
                                e.printStackTrace();
                            }
                            try
                            {
                                String pattern2 = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$";      //YYYY-MM-DD

                                System.out.print("Please Enter your Date of Birth: ");
                                newDoB = kb.next();

                                if(RegexChecker.checkFormat(newDoB,pattern2))
                                {
                                    success2 = true;
                                }
                            }
                            catch (IllegalArgumentException e) {
                                e.printStackTrace();
                            }
                            try
                            {
                                String pattern3 = "^([A-Za-z][0-9])*";  //More than 7 And Contains Letters And Numbers
                                System.out.print("Please Enter Your Password: ");
                                newPassword = kb.next();

                                if(RegexChecker.checkFormat(newPassword,pattern3))
                                {
                                    success3 = true;
                                }
                            }
                            catch (IllegalArgumentException e) {
                                e.printStackTrace();
                            }

                            newS = new Student(newCaoNum, newDoB, newPassword);
                        }
                        if(studentDao.registerStudent(newS))
                        {
                            System.out.println(Colours.GREEN + "Successfully Registered!" + Colours.RESET);
                            optionSelect = LoginMenuOptions.QUIT;
                        }
                        else
                        {
                            System.out.println(Colours.RED + "Failed to register" + Colours.RESET);
                        }
                        break;

                    case LOGIN:
                        System.out.print("Please Enter Your CaoNumber: ");
                        int caoNum = kb.nextInt();
                        currentStudent.setCaoNumber(caoNum);
                        System.out.print("Please Enter Your Password: ");
                        String password = kb.next();


                        if(studentDao.login(caoNum, password))
                        {
                            System.out.println(Colours.GREEN + "Successfully Logged In!" + Colours.RESET);
                            optionSelect = LoginMenuOptions.QUIT;
                        }
                        else
                        {
                            System.out.println(Colours.RED + "Failed To Log In" + Colours.RESET);
                        }
                        break;

                    case QUIT:
                        break;

                    default:
                        System.out.println(Colours.RED + "Selection out of range. Try again" + Colours.RESET);
                }
            }
            catch(IllegalArgumentException e)
            {
                System.out.println(Colours.RED + "Selection out of range. Please try again." + Colours.RESET);
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
                System.out.println(Colours.RED + "Selection out of range. Please try again." + Colours.RESET);
            }
            catch (DaoException throwables)
            {
                throwables.printStackTrace();
            }
        }

        mainMenu(currentStudent);

    }


    private static void mainMenu(Student currentStudent)
    {
        MySqlCourseDao courseDao = new MySqlCourseDao();

        MySqlCourseChoicesDAO choicesDAO = new MySqlCourseChoicesDAO();

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
                        System.out.println(courseDao.findCourse(courseId));

                        break;

                    case DISPLAY_ALL_COURSES:
                        List<Course> courses = courseDao.getAllCourses();
                        for(int i = 0; i < courseDao.getAllCourses().size(); i++)
                        {
                            System.out.println(courses.get(i));
                        }
                        break;


                    case DISPLAY_CURRENT_CHOICES:
                        int numberCao = currentStudent.getCaoNumber();
                        String caoNumber = Integer.toString(numberCao);
                        List<String> choices = choicesDAO.findAllChoices(caoNumber);
                        for(int i = 0; i < choices.size(); i++)
                        {
                            System.out.println(choices.get(i));
                        }
                        break;

                    case UPDATE_CURRENT_CHOICES:
                        System.out.print("How many choices would you like to add? ->");
                        int amount = kb.nextInt();

                        int caoNum2 = currentStudent.getCaoNumber();
                        List<String> newChoices = new ArrayList<>();

                        for(int i = 0; i < amount; i++)
                        {
                            System.out.print("Please enter the CourseID: ");
                            String newCourseID = kb.next();
                            newChoices.add(newCourseID);
                        }
                        for(int i = 0; i < newChoices.size();i++)
                        {
                            choicesDAO.updateChoices(caoNum2,newChoices.get(i));
                        }
                        break;


                    case QUIT:

                        break;


                    default:
                        System.out.println(Colours.RED + "Selection out of range. Try again" + Colours.RESET);

                }
            }
            catch(IllegalArgumentException e)
            {
                System.out.println(Colours.RED + "Selection out of range. Please try again." + Colours.RESET);
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
                System.out.println(Colours.RED + "Selection out of range. Please try again." + Colours.RESET);
            }
            catch (DaoException throwables)
            {
                throwables.printStackTrace();
            }
        }
    }

    private static void loginMenu2()
    {
        System.out.println(Colours.GREEN + "\n Enter: " + Colours.RESET);
        System.out.println(("\t1. Register"));
        System.out.println(("\t2. Login"));
        System.out.println((Colours.RED + "\t3. Quit" + Colours.RESET));
        System.out.print((Colours.PURPLE + "\n Selection -> " + Colours.RESET));
    }

    private static void menu ()
    {
        System.out.println(Colours.GREEN + "\n Enter: " + Colours.RESET);
        System.out.println(("\t1. Display Course"));
        System.out.println(("\t2. Display All Courses"));
        System.out.println(("\t3. Display All Current Choices"));
        System.out.println(("\t4. Update Choices"));
        System.out.println((Colours.RED + "\t6. Quit" + Colours.RESET));
        System.out.print((Colours.PURPLE + "\n Selection -> " + Colours.RESET));
    }
}

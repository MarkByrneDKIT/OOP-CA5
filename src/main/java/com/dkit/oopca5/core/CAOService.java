package com.dkit.oopca5.core;

/* The CAOService class has constants to define all of the messages that are sent between the Client and Server
 */

public class CAOService
{
    public static final int PORT_NUM = 50025;
    public static final String HOSTNAME = "localhost";

    public static final String BREAKING_CHARACTER = "%%";

    public static final String REGISTER_COMMAND = "REGISTER";
    public static final String SUCCESSFUL_REGISTER = "REGISTERED";
    public static final String FAILED_REGISTER = "REG FAILED";              //DONE

    public static final String LOGIN_COMMAND = "LOGIN";
    public static final String SUCCESSFUL_LOGIN = "LOGIN SUCCESSFUL";
    public static final String FAILED_LOGIN = "LOGIN FAILED";

    public static final String DISPLAY_COURSE = "FIND COURSE";
    public static final String DISPLAY_ALL_COURSES = "FIND ALL COURSES";

    public static final String DISPLAY_CURRENT_CHOICES = "DISPLAY CURRENT CHOICES";

    public static final String UPDATE_CURRENT_CHOICES = "UPDATE CURRENT CHOICES";



}

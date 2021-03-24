package com.dkit.oopca5.client;

// StudentManager encapsulates the storage and ability
// to manipulate student objects


import com.dkit.oopca5.Exceptions.DaoException;
import com.dkit.oopca5.core.Student;
import com.dkit.oopca5.server.MySqlStudentDao;
import com.dkit.oopca5.server.StudentDaoInterface;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class StudentManager {

    // Store all students in data structure
    private HashMap<Integer, Student> studentMap = new HashMap<Integer, Student>();

    public StudentManager() {
        StudentDaoInterface studentDao = new MySqlStudentDao();

        try {
                List<Student> studentList = studentDao.findAllStudents();
                for(Student student : studentList)
            {
                studentMap.put(student.getCaoNumber(), student);
            }
            System.out.println("studentMap dump: " + studentMap);


        } catch (
                DaoException e) {
            e.printStackTrace();
        }
    }

    public Student getStudent(int caoNumber) {
        Student student = studentMap.get(caoNumber);
        return new Student(student); //clone
    }

    public void addStudent(Student student)
    {
        if(student != null)
        {
            studentMap.put(student.getCaoNumber(), new Student(student));   //clone the student and add to clone list
        }
        //   else
        //  {
        //       throw new illegalArgumentException();
        //   }
    }

    public List<Student> getAllStudents()
    {
        ArrayList<Student> clonedList = new ArrayList<>(); //cloned list of students

        for(Map.Entry<Integer, Student> entry : studentMap.entrySet())
        {
            Student student = entry.getValue();
            clonedList.add(new Student(student));
        }
        return clonedList;
    }


    public void removeStudent(int caoNumber)
    {
        studentMap.remove(caoNumber);
    }



}

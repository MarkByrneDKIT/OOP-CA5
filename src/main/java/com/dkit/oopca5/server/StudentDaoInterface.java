package com.dkit.oopca5.server;

import com.dkit.oopca5.Exceptions.DaoException;
import com.dkit.oopca5.core.Student;

import java.util.List;

public interface StudentDaoInterface {

    public List<Student> findAllStudents() throws DaoException;

    public boolean registerStudent(Student s) throws DaoException;

    public Student findStudent(int caoNum) throws DaoException;


}

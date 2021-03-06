package com.dkit.oopca5.server;

/*
 *   D00228088
 *    Mark Byrne
 */

import com.dkit.oopca5.Exceptions.DaoException;
import com.dkit.oopca5.core.Student;

import java.util.List;

public interface StudentDaoInterface {

    public List<Student> findAllStudents() throws DaoException;

    public boolean registerStudent(Student s) throws DaoException;

    public Student findStudent(int caoNum) throws DaoException;

    public boolean login(int caoNumber, String password) throws DaoException;


}

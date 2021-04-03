package com.dkit.oopca5.server;

import com.dkit.oopca5.Exceptions.DaoException;
import com.dkit.oopca5.core.Course;
import com.dkit.oopca5.core.Student;

import java.util.List;

public interface CourseChoiceDAOInterface {

    public List<String> findAllChoices(String caoNum) throws DaoException;

    public void updateChoices(int caoNum, String choice) throws DaoException;
}

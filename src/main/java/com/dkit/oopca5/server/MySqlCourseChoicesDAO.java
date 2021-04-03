package com.dkit.oopca5.server;

import com.dkit.oopca5.Exceptions.DaoException;
import com.dkit.oopca5.core.Student;
import com.dkit.oopca5.core.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlCourseChoicesDAO extends MySqlDao implements CourseChoiceDAOInterface {


    public List<String> findAllChoices(String caoNum) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> courses = new ArrayList<>();
        String course = null;

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "SELECT courseID FROM student_courses WHERE caoNumber = ?";

            ps = con.prepareStatement(query);

            ps.setString(1,caoNum);

            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next())
            {
                course = rs.getString("courseID");
                courses.add(course);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findAllChoices() " + e.getMessage());
        } finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (con != null)
                {
                    freeConnection(con);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findAllChoices() " + e.getMessage());
            }
        }
        return courses;     // may be empty
    }

    public void updateChoices(int caoNum, String choice) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "INSERT INTO student_courses VALUES (?,?)";
            ps = con.prepareStatement(query);

            ps.setInt(1, caoNum);
            ps.setString(2, choice);

            //Using a PreparedStatement to execute SQL...
            ps.executeUpdate();


        } catch (SQLException e)
        {
            throw new DaoException("updateChoices() " + e.getMessage());
        } finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (con != null)
                {
                    freeConnection(con);
                }
            } catch (SQLException e)
            {
                throw new DaoException("updateChoices() " + e.getMessage());
            }
        }
    }
}

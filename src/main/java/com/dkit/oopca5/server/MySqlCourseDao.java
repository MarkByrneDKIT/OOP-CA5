package com.dkit.oopca5.server;

import com.dkit.oopca5.Exceptions.DaoException;
import com.dkit.oopca5.core.Course;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlCourseDao extends MySqlDao implements CourseDaoInterface
{

    public Course findCourse(String id) throws DaoException              //TODO FIX SQL statement
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Course course = new Course("","","","");

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM COURSE WHERE courseID = ?";

            ps = con.prepareStatement(query);

            ps.setString(1,id);

            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next())
            {
                String courseID = rs.getString("courseID");
                String level = rs.getString("level");
                String title = rs.getString("title");
                String institution = rs.getString("institution");
                course.setCourseId(courseID);
                course.setLevel(level);
                course.setTitle(title);
                course.setInstitution(institution);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findCourse() " + e.getMessage());
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
                throw new DaoException("findCourse() " + e.getMessage());
            }
        }
        return course;     // may be empty
    }

    public List<Course> getAllCourses() throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Course> courses = new ArrayList<>();

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM COURSE";
            ps = con.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next())
            {
                String courseID = rs.getString("courseID");
                String level = rs.getString("level");
                String title = rs.getString("title");
                String institution = rs.getString("institution");
                Course c = new Course(courseID, level, title, institution);
                courses.add(c);
            }
        } catch (SQLException e)
        {
            throw new DaoException("displayAllCourses() " + e.getMessage());
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
                throw new DaoException("displayAllCourses() " + e.getMessage());
            }
        }
        return courses; // may be empty
    }


}

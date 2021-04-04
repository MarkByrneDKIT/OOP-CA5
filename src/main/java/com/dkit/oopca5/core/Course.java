package com.dkit.oopca5.core;


import java.util.Objects;

public class Course {

    private String courseId;   // e.g. DK821
    private String level;      // e.g. 7, 8, 9, 10
    private String title;      // e.g. BSc in Computing in Software Development
    private String institution; // Dundalk Institute of Technology

    // Copy Constructor
    // Accepts a Course object as an argument and copies all the field values
    // into a new Course object. Returns the new cloned object.
    // (add here)



    // Constructor
    public Course(String courseId, String level,String title, String institution) {
        this.courseId = courseId;
        this.level = level;
        this.title = title;
        this.institution = institution;
    }

    //Copy Constructor
    public Course(Course otherCourse)
    {
        this.courseId = otherCourse.courseId;
        this.level = otherCourse.level;
        this.title = otherCourse.title;
        this.institution = otherCourse.institution;
    }





    public String getCourseId() {
        return courseId;
    }
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public String getInstitution() {
        return institution;
    }
    public void setInstitution(String institution) {
        this.institution = institution;
    }
    public String getTitle() { return title;  }
    public void setTitle(String title) { this.title = title; }

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", level='" + level + '\'' +
                ", title='" + title + '\'' +
                ", institution='" + institution + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(courseId, course.courseId) && Objects.equals(level, course.level) && Objects.equals(title, course.title) && Objects.equals(institution, course.institution);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, level, title, institution);
    }
}


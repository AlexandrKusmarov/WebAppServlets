package model;

import java.util.Date;

public class Courses {

    private Long idCourses;
    private String theme;
    private String nameOfCourses;
    private Date startOfCourses;
    private Date endOfCourses;

    public Courses() {
    }

    public Long getIdCourses() {
        return idCourses;
    }

    public void setIdCourses(Long idCourses) {
        this.idCourses = idCourses;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getNameOfCourses() {
        return nameOfCourses;
    }

    public void setNameOfCourses(String nameOfCourses) {
        this.nameOfCourses = nameOfCourses;
    }

    public Date getStartOfCourses() {
        return startOfCourses;
    }

    public void setStartOfCourses(Date startOfCourses) {
        this.startOfCourses = startOfCourses;
    }

    public Date getEndOfCourses() {
        return endOfCourses;
    }

    public void setEndOfCourses(Date endOfCourses) {
        this.endOfCourses = endOfCourses;
    }
}

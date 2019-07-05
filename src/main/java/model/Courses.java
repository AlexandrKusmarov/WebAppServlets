package model;

import java.util.Date;

public class Courses {

    private Long idCourses;
    private String theme;
    private String nameOfCourses;
    private Date startOfCourses;
    private Date endOfCourses;
    private Integer price;

    public Courses() {
    }

    public Courses(String theme, String nameOfCourses, Date startOfCourses, Date endOfCourses, Integer price) {
        this.theme = theme;
        this.nameOfCourses = nameOfCourses;
        this.startOfCourses = startOfCourses;
        this.endOfCourses = endOfCourses;
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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

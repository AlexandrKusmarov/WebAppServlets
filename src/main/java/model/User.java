package model;

import java.util.Set;

public class User {

    private Long idUser;
    private String login;
    private String password;
    private String email;
    private Role role;
    private boolean isActive;
    private Integer mark;
    private Set<Courses> coursesSet;


    private Journal journal;


    public User() {
    }

    //Constructor for Students
    public User(String login, String password, String email, Integer mark, Set<Courses> coursesSet,boolean isActive) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.mark = mark;
        this.coursesSet = coursesSet;
        this.isActive = isActive;
    }

    //Constructor for registration
    public User(String login, String password, String email, Role role,boolean isActive) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.isActive = isActive;
    }
    //Constructor for registration
    public User(Long idUser, String login, String password, String email, Role role,boolean isActive) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.isActive = isActive;
        this.idUser = idUser;
    }

    //Constructor for Teachers
    public User( String login, String password, String email, Role role) {
        this.login = login;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Set<Courses> getCoursesSet() {
        return coursesSet;
    }

    public void setCourcesSet(Set<Courses> courcesSet) {
        this.coursesSet = courcesSet;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}

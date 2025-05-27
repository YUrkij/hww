package hw2.BDDataClasses;

public class Group {
    private int id;
    private String name;
    private int year;
    private int departmentId;

    public Group(String name, int year, int departmentId) {
        this.name = name;
        this.year = year;
        this.departmentId = departmentId;
    }

    public Group(int id, String name, int year, int departmentId) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.departmentId = departmentId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}

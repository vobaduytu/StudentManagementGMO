package model;

/**
 * This class declares the fields of the student attributes
 */

public class Student {
    private int id;
    private String name;
    private String address;
    private String dob;
    private String gender;
    private Integer phoneNumber;
    private String email;
    private int idClass;

    public Student() {
    }

    public Student(String newName, String newDob, String newGender, Integer newPhone) {
        this.name = newName;
        this.dob = newDob;
        this.gender = newGender;
        this.phoneNumber = newPhone;
    }

    public Student(int id, String newName, String newDob, String newGender, Integer newPhone) {
        this.id = id;
        this.name = newName;
        this.dob = newDob;
        this.gender = newGender;
        this.phoneNumber = newPhone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String isGender() {
        return gender;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdClass() {
        return idClass;
    }

    public void setIdClass(int idClass) {
        this.idClass = idClass;
    }

    @Override
    public String toString() {
        return "Model.Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", dob='" + dob + '\'' +
                ", gender=" + gender +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", idClass=" + idClass +
                '}';
    }
}

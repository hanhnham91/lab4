package com.example.lab4.model;

import android.content.ContentValues;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "student")
public class Student {
    @PrimaryKey(autoGenerate = true)
    public Integer studentId;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "gender")
    public String gender;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "tel")
    public String tel;

    @ColumnInfo(name = "dob")
    public String dob;

    @ColumnInfo(name = "finalScore")
    public float finalScore;



    public static Student fromContentValues(ContentValues contentValues) {
        Student s = new Student();
        if (contentValues.containsKey("studentId")) {
            s.studentId = contentValues.getAsInteger("studentId");
        }
        if (contentValues.containsKey("name")) {
            s.name = contentValues.getAsString("name");
        }
        if (contentValues.containsKey("gender")) {
            s.gender = contentValues.getAsString("gender");
        }
        if (contentValues.containsKey("email")) {
            s.email = (contentValues.getAsString("email"));
        }
        if (contentValues.containsKey("tel")) {
            s.tel =(contentValues.getAsString("tel"));
        }
        if (contentValues.containsKey("dob")) {
            s.dob =(contentValues.getAsString("dob"));
        }

        if (contentValues.containsKey("finalScore")) {
            s.finalScore =(contentValues.getAsFloat("finalScore"));
        }
        return s;
    }


//    @PrimaryKey(autoGenerate = true)
//    private Integer studentId;
//
//    @ColumnInfo(name = "name")
//    private String name;
//
//    @ColumnInfo(name = "gender")
//    private String gender;
//
//    @ColumnInfo(name = "email")
//    private String email;
//
//    @ColumnInfo(name = "tel")
//    private String tel;
//
//    @ColumnInfo(name = "dob")
//    private String dob;
//
//
//    public Integer getStudentId() {
//        return studentId;
//    }
//    public void setId(Integer studentId) {
//        this.studentId = studentId;
//    }
//
//    public String getName() {
//        return name;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public String getEmailAddress() {
//        return email;
//    }
//    public void setEmailAddress(String email) {
//        this.email = email;
//    }
//
//    public String getTel() {
//        return tel;
//    }
//    public void setTel(String tel) { this.tel = tel; }
//
//    public String getDOB() {
//        return dob;
//    }
//    public void setDOB(String dob) {
//        this.dob = dob;
//    }
//
//
//    public static Student fromContentValues(ContentValues contentValues) {
//        Student s = new Student();
//        if (contentValues.containsKey("studentId")) {
//            s.setId(contentValues.getAsInteger("studentId"));
//        }
//        if (contentValues.containsKey("name")) {
//            s.setName(contentValues.getAsString("name"));
//        }
//        if (contentValues.containsKey("gender")) {
//            s.setGender(contentValues.getAsString("gender"));
//        }
//        if (contentValues.containsKey("email")) {
//            s.setEmailAddress(contentValues.getAsString("email"));
//        }
//        if (contentValues.containsKey("tel")) {
//            s.setTel(contentValues.getAsString("tel"));
//        }
//        if (contentValues.containsKey("dob")) {
//            s.setDOB(contentValues.getAsString("dob"));
//        }
//        return s;
//    }
}

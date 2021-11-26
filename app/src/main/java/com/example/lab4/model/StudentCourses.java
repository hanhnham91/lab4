package com.example.lab4.model;

import android.content.ContentValues;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Relation;

import java.util.List;

@Entity(tableName = "student_courses" ,primaryKeys = {"studentId", "courseId"})
public class StudentCourses {
    @NonNull
    public Integer studentId;
    @NonNull
    public Integer courseId;

    public String semester;
    public float score;


    public static StudentCourses fromContentValues(ContentValues contentValues) {
        StudentCourses s = new StudentCourses();
        if (contentValues.containsKey("studentId")) {
            s.studentId = contentValues.getAsInteger("studentId");
        }
        if (contentValues.containsKey("courseId")) {
            s.courseId = contentValues.getAsInteger("courseId");
        }
        if (contentValues.containsKey("semester")) {
            s.semester = contentValues.getAsString("semester");
        }
        if (contentValues.containsKey("score")) {
            s.score = contentValues.getAsFloat("score");
        }
        return s;
    }
}

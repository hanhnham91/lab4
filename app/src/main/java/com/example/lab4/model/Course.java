package com.example.lab4.model;

import android.content.ContentValues;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "course")
public class Course {
    @PrimaryKey(autoGenerate = true)
    public Integer courseId;
    public String courseDecription;
    public String courseDurationYear;

    public static Course fromContentValues(ContentValues contentValues) {
        Course s = new Course();
        if (contentValues.containsKey("courseId")) {
            s.courseId = contentValues.getAsInteger("courseId");
        }
        if (contentValues.containsKey("courseDecription")) {
            s.courseDecription = contentValues.getAsString("courseDecription");
        }
        if (contentValues.containsKey("courseDurationYear")) {
            s.courseDurationYear = contentValues.getAsString("courseDurationYear");
        }
        return s;
    }
}

package com.example.lab4.model;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class RelationModel {
    @Embedded
    public Student student;
    @Relation(
            parentColumn = "studentId",
            entityColumn = "courseId",
            associateBy = @Junction(StudentCourses.class)
    )
    public List<Course> courseList;
}

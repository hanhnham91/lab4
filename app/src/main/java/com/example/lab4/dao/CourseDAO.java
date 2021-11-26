package com.example.lab4.dao;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.lab4.model.Course;
import com.example.lab4.model.Student;

@Dao
public interface CourseDAO {

    @Insert
    long insert(Course c);

    @Query("SELECT * FROM course")
    Cursor findAll();

    @Query("SELECT * from course where courseId = :courseId")
    Cursor  getStudent(long courseId);

    @Query("DELETE FROM course WHERE courseId = :courseId ")
    int delete(long courseId);

    @Update
    int update(Course c);
}

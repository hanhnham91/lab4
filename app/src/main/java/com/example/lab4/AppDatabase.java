package com.example.lab4;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.lab4.dao.CourseDAO;
import com.example.lab4.dao.StudentDAO;
import com.example.lab4.model.Course;
import com.example.lab4.model.Student;
import com.example.lab4.model.StudentCourses;

@Database(entities = {Student.class, Course.class, StudentCourses.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "test_student_db";
    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).build();
        }
        return INSTANCE;
    }

    public abstract StudentDAO getStudentDao();
    public abstract CourseDAO getCourseDao();
}
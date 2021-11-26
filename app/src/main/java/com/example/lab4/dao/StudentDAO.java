package com.example.lab4.dao;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.lab4.model.RelationModel;
import com.example.lab4.model.Student;
import com.example.lab4.model.StudentCourses;

import java.util.List;

@Dao
public interface StudentDAO {
    /**
     * Insert a person data into the table
     *
     * @return row ID for newly inserted data
     */
    @Insert
    long insert(Student student);

    /**
     * select all person
     *
     * @return A {@link Cursor} of all person in the table
     */
    @Query("SELECT * FROM student")
    Cursor findAll();

    @Query("SELECT * from student where studentId = :studentId")
    Cursor  getStudent(long studentId);

    /**
     * Delete a person by ID
     *
     * @return A number of persons deleted
     */
    @Query("DELETE FROM student WHERE studentId = :studentId ")
    int delete(long studentId);

    /**
     * Update the person
     *
     * @return A number of persons updated
     */
    @Update
    int update(Student student);



    @Transaction
    @Query("SELECT * FROM student")
    public List<RelationModel> getAllStudentWithCourses();

    @Transaction
    @Query("SELECT * FROM student where studentId = :studentId")
    public List<RelationModel> getStudentWithCourses(long studentId);


    @Insert
    long insertSC(StudentCourses sc);

    @Query("SELECT * FROM student_courses")
    Cursor findAllSC();

}


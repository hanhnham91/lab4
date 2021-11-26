package com.example.lab4.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.util.Log;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lab4.AppDatabase;
import com.example.lab4.dao.CourseDAO;
import com.example.lab4.dao.StudentDAO;
import com.example.lab4.model.Course;
import com.example.lab4.model.RelationModel;
import com.example.lab4.model.Student;
import com.example.lab4.model.StudentCourses;
import com.google.gson.Gson;

import java.util.List;

public class StudentContentProvider extends ContentProvider {

    public static final String TAG = StudentContentProvider.class.getName();

    private StudentDAO studentDao;
    private CourseDAO courseDao;

    public static final String AUTHORITY = "com.example.lab4.provider";

    //path
    public static final String STUDENT_TABLE_NAME = "student";
    public static final String SC_TABLE_NAME = "student_courses";
    public static final String COURSE_TABLE_NAME = "course";
    public static final String STUDENT_COURSE = "studentcourse";

    public static final int ID_STUDENT_DATA = 1;
    public static final int ID_STUDENT_DATA_ITEM = 2;
    public static final int ID_SC_DATA = 3;
    public static final int ID_SC_DATA_ITEM = 4;
    public static final int ID_COURSE_DATA = 5;
    public static final int ID_COURSE_DATA_ITEM = 6;
    public static final int ID_STUDENT_WITH_COURSE_DATA = 7;
    public static final int ID_STUDENT_WITH_COURSE_DATA_ITEM = 8;


    public static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, STUDENT_COURSE, ID_STUDENT_WITH_COURSE_DATA);
        uriMatcher.addURI(AUTHORITY, STUDENT_COURSE + "/*", ID_STUDENT_WITH_COURSE_DATA_ITEM);

        uriMatcher.addURI(AUTHORITY, STUDENT_TABLE_NAME, ID_STUDENT_DATA);
        uriMatcher.addURI(AUTHORITY, STUDENT_TABLE_NAME + "/*", ID_STUDENT_DATA_ITEM);

        uriMatcher.addURI(AUTHORITY, SC_TABLE_NAME, ID_SC_DATA);
        uriMatcher.addURI(AUTHORITY, SC_TABLE_NAME + "/*", ID_SC_DATA_ITEM);

        uriMatcher.addURI(AUTHORITY, COURSE_TABLE_NAME, ID_COURSE_DATA);
        uriMatcher.addURI(AUTHORITY, COURSE_TABLE_NAME + "/*", ID_COURSE_DATA_ITEM);
    }

    @Override
    public boolean onCreate() {
        studentDao = AppDatabase.getInstance(getContext()).getStudentDao();
        courseDao = AppDatabase.getInstance(getContext()).getCourseDao();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Log.d(TAG, "query");
        Cursor cursor;
        switch (uriMatcher.match(uri)) {
            case ID_STUDENT_DATA:
                cursor = studentDao.findAll();

                if (getContext() != null) {
                    cursor.setNotificationUri(getContext().getContentResolver(), uri);
                    return cursor;
                }

            case ID_STUDENT_WITH_COURSE_DATA:
                List<RelationModel> rs = studentDao.getAllStudentWithCourses();
                MatrixCursor cursor1 = new MatrixCursor(new String[]{"studentId", "data"});
                for (RelationModel s : rs) {
                    Gson gson = new Gson();
                    String json = gson.toJson(s);
                    cursor1.newRow().add("studentId", s.student.studentId).add("data", json);
                }
                cursor1.setNotificationUri(getContext().getContentResolver(), uri);
                return cursor1;

            case ID_STUDENT_WITH_COURSE_DATA_ITEM:
                List<RelationModel> student = studentDao.getStudentWithCourses(ContentUris.parseId(uri));
                MatrixCursor cursor2 = new MatrixCursor(new String[]{"studentId", "data"});
                for (RelationModel s : student) {
                    Gson gson = new Gson();
                    String json = gson.toJson(s);
                    cursor2.newRow().add("studentId", s.student.studentId).add("data", json);
                }
                cursor2.setNotificationUri(getContext().getContentResolver(), uri);
                return cursor2;
            case ID_STUDENT_DATA_ITEM:
                cursor = studentDao.getStudent(ContentUris.parseId(uri));
                return cursor;

            case ID_COURSE_DATA:
                cursor = courseDao.findAll();

                if (getContext() != null) {
                    cursor.setNotificationUri(getContext().getContentResolver(), uri);
                    return cursor;
                }

            case ID_SC_DATA:
                cursor = studentDao.findAllSC();

                if (getContext() != null) {
                    cursor.setNotificationUri(getContext().getContentResolver(), uri);
                    return cursor;
                }
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);

        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Log.d(TAG, "insert");
        switch (uriMatcher.match(uri)) {
            case ID_STUDENT_DATA:
                if (getContext() != null) {
                    long id = studentDao.insert(Student.fromContentValues(values));
                    if (id != 0) {
                        getContext().getContentResolver().notifyChange(uri, null);
                        return ContentUris.withAppendedId(uri, id);
                    }
                }
            case ID_STUDENT_DATA_ITEM:
                throw new IllegalArgumentException("Invalid URI: Insert failed" + uri);

            case ID_COURSE_DATA:
                if (getContext() != null) {
                    long id = courseDao.insert(Course.fromContentValues(values));
                    if (id != 0) {
                        getContext().getContentResolver().notifyChange(uri, null);
                        return ContentUris.withAppendedId(uri, id);
                    }
                }

            case ID_SC_DATA:
                if (getContext() != null) {
                    StudentCourses aaa = StudentCourses.fromContentValues(values);
                    System.out.println(aaa);
                    long id = studentDao.insertSC(aaa);
                    if (id != 0) {
                        getContext().getContentResolver().notifyChange(uri, null);
                        return ContentUris.withAppendedId(uri, id);
                    }
                }
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(TAG, "delete");
        switch (uriMatcher.match(uri)) {
            case ID_STUDENT_DATA:
                throw new IllegalArgumentException("Invalid uri: cannot delete");
            case ID_STUDENT_DATA_ITEM:
                if (getContext() != null) {
                    int count = studentDao.delete(ContentUris.parseId(uri));
                    getContext().getContentResolver().notifyChange(uri, null);
                    return count;
                }
            default:
                throw new IllegalArgumentException("Unknown URI:" + uri);
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(TAG, "update");
        switch (uriMatcher.match(uri)) {
            case ID_STUDENT_DATA:
                if (getContext() != null) {
                    int count = studentDao.update(Student.fromContentValues(values));
                    if (count != 0) {
                        getContext().getContentResolver().notifyChange(uri, null);
                        return count;
                    }
                }
            case ID_STUDENT_DATA_ITEM:
                throw new IllegalArgumentException("Invalid URI:  cannot update");
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

    }

}
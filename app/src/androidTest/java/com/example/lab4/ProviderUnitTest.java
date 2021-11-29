package com.example.lab4;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.util.Log;
import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.example.lab4.provider.StudentContentProvider.AUTHORITY;
import static com.example.lab4.provider.StudentContentProvider.STUDENT_TABLE_NAME;
import static com.example.lab4.provider.StudentContentProvider.COURSE_TABLE_NAME;
import static com.example.lab4.provider.StudentContentProvider.SC_TABLE_NAME;

import static com.example.lab4.provider.StudentContentProvider.STUDENT_COURSE;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ProviderUnitTest {

    private static final String TAG = ProviderUnitTest.class.getName();
    private static final String STUDENT_URI = "content://" + AUTHORITY + "/" + STUDENT_TABLE_NAME;
    private static final String COURSE_URI = "content://" + AUTHORITY + "/" + COURSE_TABLE_NAME;
    private static final String STUDENT_COURSE_URI = "content://" + AUTHORITY + "/" + SC_TABLE_NAME;
    private static final String Student_With_COURSES_URI = "content://" + AUTHORITY + "/" + STUDENT_COURSE;
    private ContentResolver contentResolver;

    @Before
    public void setUp() {
        Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(), AppDatabase.class).allowMainThreadQueries().build();
        contentResolver = InstrumentationRegistry.getInstrumentation().getContext().getContentResolver();
    }

//    @Test
//    public void insertTestData() {
//        insertStudents();
//        insertCourses();
//        insertStudentCOurses();
//    }

    @Test
    public void findAll() {
        Cursor cursor3 = contentResolver.query(Uri.parse(STUDENT_URI), null, null, null, null);

//        Cursor cursor3 = contentResolver.query(Uri.parse(Student_With_COURSES_URI), null, null, null, null);
        assertNotNull(cursor3);
        Log.d(TAG, "querySCs: " + DatabaseUtils.dumpCursorToString(cursor3));
        if (cursor3.moveToFirst()) {
            do {
            }
            while (cursor3.moveToNext());
        }
        cursor3.close();
    }


    @Test
    public void findOne() {
        Cursor cursor3 = contentResolver.query(Uri.parse(Student_With_COURSES_URI + "/2"), null, null, null, null);
        assertNotNull(cursor3);
        Log.d(TAG, "querySCs: " + DatabaseUtils.dumpCursorToString(cursor3));
        if (cursor3.moveToFirst()) {
            do {
            }
            while (cursor3.moveToNext());
        }
        cursor3.close();
    }


    private void insertStudents() {
        Uri insertUri = contentResolver.insert(Uri.parse(STUDENT_URI), getStudent("Le Thi Ngan"));
        insertUri = contentResolver.insert(Uri.parse(STUDENT_URI), getStudent("Nguyen Van Thuy"));
        assertNotNull(insertUri);
    }

    private void insertCourses() {
        Uri insertUri = contentResolver.insert(Uri.parse(COURSE_URI), getCourse("Toan", "HK1-2022"));
        insertUri = contentResolver.insert(Uri.parse(COURSE_URI), getCourse("Ly", "HK1-2022"));
        insertUri = contentResolver.insert(Uri.parse(COURSE_URI), getCourse("Hoa", "HK1-2022"));
        insertUri = contentResolver.insert(Uri.parse(COURSE_URI), getCourse("Van", "HK1-2022"));
        insertUri = contentResolver.insert(Uri.parse(COURSE_URI), getCourse("Anh", "HK1-2022"));
        insertUri = contentResolver.insert(Uri.parse(COURSE_URI), getCourse("Nhac", "HK1-2022"));
        insertUri = contentResolver.insert(Uri.parse(COURSE_URI), getCourse("The duc", "HK1-2022"));
        assertNotNull(insertUri);
    }

    private void insertStudentCOurses() {
        Uri insertUri = contentResolver.insert(Uri.parse(STUDENT_COURSE_URI), getStudentCourse(1, 1, "HK1-2022"));
        insertUri = contentResolver.insert(Uri.parse(STUDENT_COURSE_URI), getStudentCourse(1, 2, "HK1-2022"));
        insertUri = contentResolver.insert(Uri.parse(STUDENT_COURSE_URI), getStudentCourse(1, 3, "HK1-2022"));
        insertUri = contentResolver.insert(Uri.parse(STUDENT_COURSE_URI), getStudentCourse(1, 4, "HK1-2022"));
        insertUri = contentResolver.insert(Uri.parse(STUDENT_COURSE_URI), getStudentCourse(1, 5, "HK1-2022"));
        insertUri = contentResolver.insert(Uri.parse(STUDENT_COURSE_URI), getStudentCourse(1, 6, "HK1-2022"));

        insertUri = contentResolver.insert(Uri.parse(STUDENT_COURSE_URI), getStudentCourse(2, 1, "HK1-2022"));
        insertUri = contentResolver.insert(Uri.parse(STUDENT_COURSE_URI), getStudentCourse(2, 2, "HK1-2022"));
        insertUri = contentResolver.insert(Uri.parse(STUDENT_COURSE_URI), getStudentCourse(2, 3, "HK1-2022"));
        insertUri = contentResolver.insert(Uri.parse(STUDENT_COURSE_URI), getStudentCourse(2, 4, "HK1-2022"));
        insertUri = contentResolver.insert(Uri.parse(STUDENT_COURSE_URI), getStudentCourse(2, 6, "HK1-2022"));
        insertUri = contentResolver.insert(Uri.parse(STUDENT_COURSE_URI), getStudentCourse(2, 7, "HK1-2022"));
        assertNotNull(insertUri);
    }

    private ContentValues getStudent(String name) {
        ContentValues rs = new ContentValues();
        rs.put("name", name);
        rs.put("gender", "female");
        rs.put("email", name.replaceAll("\\W", "").toLowerCase()+"@email.com");
        rs.put("tel", "987654321");
        rs.put("dob", "1994-11-05");
        return rs;
    }

    private ContentValues getCourse(String courseDecription, String courseDurationYear) {
        ContentValues rs = new ContentValues();
        rs.put("courseDecription", courseDecription);
        rs.put("courseDurationYear", courseDurationYear);
        return rs;
    }

    private ContentValues getStudentCourse(Integer studentId, Integer courseId, String semester) {
        ContentValues rs = new ContentValues();
        rs.put("studentId", studentId);
        rs.put("courseId", courseId);
        rs.put("semester", semester);
        return rs;
    }


//    @Test
//    public void deleteOne() {
//        Uri itemUri = contentResolver.insert(uri, getContentValues("a"));
//        assertNotNull(itemUri);
//
//        Cursor cursor = contentResolver.query(uri, new String[]{"name"}, null, null, null);
//        assertNotNull(cursor);
//        cursor.close();
//
//        int count = contentResolver.delete(itemUri, null, null);
//        assertNotNull(count);
//    }

//    @Test
//    public void deleteAll() {
//        int count = contentResolver.delete(STUDENT_URI_ID, null, null);
//        assertNotNull(count);
//    }

}
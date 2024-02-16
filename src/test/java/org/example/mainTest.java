package org.example;
import org.example.Main.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class mainTest {
    @Test
    void courseCreation() {
        Boolean createcourseTest = org.example.Main.registerCourse("100", "test java", "Patrick");
        assertEquals(true, createcourseTest);
    }


    @Test
    void studentCreation(){
        Boolean createstudentTest = org.example.Main.registerStudent("25754", "elvito", "mugisha");
        assertEquals(true, createstudentTest);
    }


    @Test
    void studentUpdate(){
        Boolean updatestudentTest = org.example.Main.updateStudent("25754", "elvito_update", "mugisha");
        assertEquals(true, updatestudentTest);
    }


    @Test
    void courseUpdate(){
        Boolean updatecourseTest = org.example.Main.updateCourse("100", "webtech", "Jerome");
        assertEquals(true, updatecourseTest);
    }


    @Test
    void courseDelete(){
        org.example.Main.deleteCourse("100");
        String name = org.example.Main.viewCourseById("100");
        assertNull("On Purpose", name);
    }


    @Test
    void  studentDelete(){
        org.example.Main.deleteStudent("25754");
        String name = org.example.Main.viewStudentById("25754");
        assertNull("On Purpose", name);
    }

}

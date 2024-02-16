package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("AUCA COURSE STUDENT");
        System.out.print("==============MAIN MENU:===============");
        System.out.print("\n1.Register course ");
        System.out.print("\n2.Register student ");
        System.out.print("\n3.Update Course ");
        System.out.print("\n4.Update Student ");
        System.out.print("\n5.Delete Course ");
        System.out.print("\n4.Delete Student ");
        System.out.print("\n0.Exit ");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        int choice = Integer.parseInt(reader.readLine());
        switch (choice) {
            case 1:
                System.out.println("Course Id: ");
                String courseId = reader.readLine();
                System.out.println("course name: ");
                String name = reader.readLine();
                System.out.println("lecture: ");
                String lecture = reader.readLine();
                if(registerCourse(courseId, name, lecture))
                    System.out.println("You have recorded a course");
                break;
            case 2:
                System.out.println("Student Id: ");
                String studentId = reader.readLine();
                System.out.println("first name: ");
                String firstname = reader.readLine();
                System.out.println("lastname: ");
                String lastname = reader.readLine();
                if(registerStudent(studentId, firstname, lastname))
                    System.out.println("You have recorded a student");
                break;
            case 3:
                getCourses();
                System.out.println("course Id to update: ");
                String courseid = reader.readLine();
                System.out.println("New course name: ");
                String name1 = reader.readLine();
                System.out.println("New lecture: ");
                String lecture1 = reader.readLine();
                if(updateCourse(courseid, name1, lecture1))
                    System.out.println("You have updated a course");
            case 4:
                getStudents();
                System.out.println("student Id to update: ");
                String studentid = reader.readLine();
                System.out.println("New First name: ");
                String firstname1 = reader.readLine();
                System.out.println("New Last Name: ");
                String lastname1 = reader.readLine();
                if(updateStudent(studentid, firstname1, lastname1))
                    System.out.println("You have updated a course");
                break;
            case 5:
                getCourses();
                System.out.println("\ncourse Id to delete: ");
                String coid = reader.readLine();
                deleteCourse(coid);
                break;
            case 6:
                getStudents();
                System.out.println("\nstudent Id to delete: ");
                String soid = reader.readLine();
                deleteStudent(soid);
                break;

            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Try again... We do not have that option");
                break;
        }
    }

    public static Boolean registerCourse(String courseId, String courseName, String lectureName) {
        try {
            String sql = "INSERT INTO Course (courseId, name, lecture) VALUES (?, ?, ?)";
            Connection con = getConnection();

            PreparedStatement statement = con.prepareStatement(sql);
            // set values
            //statement.setInt(1, id);
            statement.setString(1, courseId);
            statement.setString(2, courseName);
            statement.setString(3, lectureName);

            // count affected rows and return the count
            int rowsAffected = statement.executeUpdate();
            return rowsAffected >= 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Boolean registerStudent(String studentId, String firstName, String lastName) {
        try {
            String sql = "INSERT INTO Student (studentId, firstName, lastName) VALUES (?, ?, ?)";
            Connection con = getConnection();

            PreparedStatement statement = con.prepareStatement(sql);
            // set values
            //statement.setInt(1, id);
            statement.setString(1, studentId);
            statement.setString(2, firstName);
            statement.setString(3, lastName);

            // count affected rows and return the count
            int rowsAffected = statement.executeUpdate();
            return rowsAffected >= 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Boolean updateCourse(String courseId, String courseName, String lectureName) {
        try {
            String sql_query = "UPDATE Course SET name = ?, lecture = ? WHERE courseId = ?";
            Connection con = getConnection();

            PreparedStatement statement = con.prepareStatement(sql_query);
            statement.setString(1, courseName);
            statement.setString(2, lectureName);
            statement.setString(3, courseId);


            int rowsAffected = statement.executeUpdate();
                return rowsAffected >= 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateStudent(String studentId, String firstName, String lastName) {
        try {
            String sql_query = "UPDATE Student SET firstName = ?, lastName = ? WHERE studentId = ?";
            Connection con = getConnection();

            PreparedStatement statement = con.prepareStatement(sql_query);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, studentId);


            int rowsAffected = statement.executeUpdate();
            return rowsAffected >= 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void getCourses() {
        try {
            String sql = "SELECT * FROM course";
            Connection con = getConnection(); // establish connection
            PreparedStatement statement = con.prepareStatement(sql);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String id = result.getString("courseId");
                String name = result.getString("name");
                String lecture = result.getString("lecture");

                System.out.print("Courseid: " + id);
                System.out.print(" | Name: " + name);
                System.out.print(" | lecture: " + lecture);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getStudents() {
        try {
            String sql = "SELECT * FROM student";
            Connection con = getConnection(); // establish connection
            PreparedStatement statement = con.prepareStatement(sql);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String id = result.getString("studentId");
                String firstname = result.getString("firstName");
                String lastname = result.getString("lastName");

                System.out.print("studentId: " + id);
                System.out.print(" | FirstName: " + firstname);
                System.out.print(" | LastName: " + lastname);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCourse(String courseId) {
        try {
            String sql_query = "DELETE FROM course WHERE courseId = ?";
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement(sql_query);
            statement.setString(1, courseId);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected >= 1)
                System.out.println("Deleted!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteStudent(String studentId) {
        try {
            String sql_query = "DELETE FROM student WHERE studentId = ?";
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement(sql_query);
            statement.setString(1, studentId);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected >= 1)
                System.out.println("Deleted!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String viewCourseById(String courseId) {
        String theName = "";
        try {
            String sql_query = "SELECT name FROM course where courseId=?";
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement(sql_query);
            statement.setString(1, courseId);

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                theName = result.getString("name");
            }
            return theName;
        } catch (SQLException e) {
            e.printStackTrace();

            return null;
        }
    }

    public static String viewStudentById(String studentId) {
        String theName = "";
        try {
            String sql_query = "SELECT firstName FROM course where studentId=?";
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement(sql_query);
            statement.setString(1, studentId);

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                theName = result.getString("name");
            }
            return theName;
        } catch (SQLException e) {
            e.printStackTrace();

            return null;
        }
    }
    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "elvisgasana",
                    "");
            return conn;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
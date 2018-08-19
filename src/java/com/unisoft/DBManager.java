package com.unisoft;


import java.sql.*;          
import java.util.ArrayList;

public class DBManager {
    private static final String DBDRIVER = "com.mysql.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/j2ee";
    private static final String DBUSER = "root";
    private static final String DBPASSWORD = "";
    
    static{
        try{
            Class.forName(DBDRIVER);
        }catch(Exception ex){
            System.out.println("JSPDemo  : "+ex);
        }
    }
    
    public static boolean insertStudent(Student s){
        boolean status = false;
        try(Connection conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD)){           
            PreparedStatement st = conn.prepareStatement("insert into students values(?,?,?,?,?,?)");
            st.setInt(1, s.getRollNumber());
            st.setString(2, s.getName());
            st.setString(3, s.getGender());
            st.setString(4, s.getEmail());
            st.setString(5, s.getMobileNumber());
            st.setString(6, s.getCourse());           
            st.executeUpdate();
            status = true;
        }
        catch(Exception ex){  
            System.out.println("DBManager.addStudent  : "+ex);
        }    
        return status;
    }
    public static boolean updateStudent(Student s){
        boolean status = false;
        try(Connection conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD)){           
            PreparedStatement st = conn.prepareStatement("update students set Name=?, Gender=?, Email=?, MobileNumber=?, Course=? where rollNumber=?");            
            st.setString(1, s.getName());
            st.setString(2, s.getGender());
            st.setString(3, s.getEmail());
            st.setString(4, s.getMobileNumber());
            st.setString(5, s.getCourse());  
            st.setInt(6, s.getRollNumber());
            st.executeUpdate();
            status = true;
        }
        catch(Exception ex){            
        }    
        return status;
    }    
    public static boolean deleteStudent(int rollNumber){
        boolean status = false;
        try(Connection conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD)){           
            PreparedStatement st = conn.prepareStatement("delete from students where rollNumber=?");             
            st.setInt(1, rollNumber);
            st.executeUpdate();
            status = true;
        }
        catch(Exception ex){            
        }    
        return status;
    } 
    public static boolean deleteAll(String[] values){
        boolean status = false;
        try(Connection conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD)){           
            for(String str : values){
                PreparedStatement st = conn.prepareStatement("delete from students where rollNumber=?");             
                st.setInt(1, Integer.parseInt(str));
                st.executeUpdate();
            }
            status = true;
        }
        catch(Exception ex){            
        }    
        return status;
    }    
    public static Student getStudent(int rollNumber){
        Student s = null;
        try(Connection conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD)){           
            PreparedStatement st = conn.prepareStatement("select * from students where rollNumber=?");
            st.setInt(1, rollNumber);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                s = new Student();
                s.setRollNumber(rs.getInt(1));
                s.setName(rs.getString(2));
                s.setGender(rs.getString(3));
                s.setEmail(rs.getString(4));
                s.setMobileNumber(rs.getString(5));
                s.setCourse(rs.getString(6));
            }
            rs.close();
        }
        catch(Exception ex){            
        }    
        return s;
    }    
    public static ArrayList<Student> getAllStudents(){
        ArrayList<Student> list = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD)){           
            PreparedStatement st = conn.prepareStatement("select * from students");
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Student s = new Student();
                s.setRollNumber(rs.getInt(1));
                s.setName(rs.getString(2));
                s.setGender(rs.getString(3));
                s.setEmail(rs.getString(4));
                s.setMobileNumber(rs.getString(5));
                s.setCourse(rs.getString(6));
                list.add(s);
            }
            rs.close();
        }
        catch(Exception ex){            
        }    
        return list;
    }   
    
    public static UserInfo authenticateUser(String email, String passwd){
        UserInfo info = null;
        try(Connection conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD)){           
            PreparedStatement st = conn.prepareStatement("select Email,Name,RoleName,Verified from users where email=? and passwd=password(?)");             
            st.setString(1, email);
            st.setString(2, passwd);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                info = new UserInfo();
                info.setEmail(rs.getString(1));
                info.setName(rs.getString(2));
                info.setRoleName(rs.getString(3));
                info.setVerified(rs.getInt(4));
            }
        }
        catch(Exception ex){  
            System.out.println("DBManager auhtenticateUser : "+ex);
        }    
        return info;        
    }
}

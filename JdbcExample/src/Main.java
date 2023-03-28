import org.postgresql.util.PSQLException;

import java.sql.*;

public class Main {
    public static void main(String[] args) {


        //data base olusturalim school
        //student tablomuz olusturduk..
        //student sinifi olusturacagiz



        String url = "jdbc:postgresql://localhost:5432/school";
        String username = "postgres";
        String password = "12345678";

        Connection connection = null ;
        //String sql ="Insert into student(name,surname,city) values('ali' ,'yaz' , 'Ankara') ";

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url,username,password);
            System.out.println("Baglanti gerceklesti");
            //execute(connection,sql);
            Student student = new Student("mesut","kis",36,"Ankara");
            //createStudent(connection,student);
            Student student1 = new Student("memet","son",22,"Kars");
            //updateStudent(connection,student1,2);
            //countByCityName(connection,"Ankara");
            deleteStudent(connection,2);

        }catch (PSQLException P){
            System.err.println("lutfen database ismini kontrol edin");
        }
        catch (Exception e){
            System.out.println("Beklenmedik bir hata meydana geldi");
        }
        finally {
            try {
                if(connection != null) {
                    connection.close();
                }
                System.out.println("Baglanti kapatildi");
            } catch (SQLException e) {
                System.err.println("Kapatma hatasi");
            }
        }

    }
    public static void  execute (Connection connection,String sql){

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Islem basarili");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void createStudent(Connection connection,Student student){
        String sql ="Insert into student(name,surname,city ,age) values(? ,? , ? , ?) ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getCity());
            preparedStatement.setInt(4, student.getAge());
            preparedStatement.executeUpdate();
            System.out.println(student.getName() + "Veri Tabanina eklendi");
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateStudent(Connection connection ,Student student , int id){
        String sql = "update student set name =? ,surname=?,city=?,age=? where id=? " ;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getCity());
            preparedStatement.setInt(4, student.getAge());
           preparedStatement.setInt(5,id);
           int effectedRow = preparedStatement.executeUpdate();
           if(effectedRow>0){
               System.out.println("Update islemi gerceslesti");
           }else{
               System.out.println("Lutfen gecerli bir id giriniz");
           }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void countByCityName(Connection connection , String cityName){
        String sql = "select count(*) from student where lower(city) = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,cityName.toLowerCase());

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            System.out.println(resultSet.getInt("count"));

            while(resultSet.next()){
                System.out.println(resultSet.getString("name") + " " + resultSet.getString("surname"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void deleteStudent(Connection connection , int id){
        String sql = "DELETE FROM student WHERE id =?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
package com.muhammetkarakurt.repository;

import com.muhammetkarakurt.entity.Person;
import com.muhammetkarakurt.util.JDBCConstant;
import com.muhammetkarakurt.util.JDBCHelper;

import java.sql.*;

public class PersonRepository  implements IPersonRepository {


    public void insertPerson(Person person) {

        String sql = "insert into persons(first_name,last_name, joined_date ,email) values(?,?,?,?)";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCHelper.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setDate(3, new Date(person.getJoinedDate().getTime()));
            preparedStatement.setString(4, person.getEmail());
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println(person.getFirstName() + " Veri Tabanına Eklendi");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }

    private void displayPersons(ResultSet rs) throws SQLException {
        while (rs.next()) {
            //Display values
            System.out.print("ID: " + rs.getInt("id"));
            System.out.print(", FirsName: " + rs.getString("first_name"));
            System.out.print(", LastName: " + rs.getString("last_name"));
            System.out.println(", Date: " + rs.getDate("joined_date"));
            System.out.print(", Email: " + rs.getString("email"));
        }
    }


    public void getAllPersons() {
        String sql = "SELECT * FROM persons ORDER BY id ASC ";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection = JDBCHelper.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                //Display values
                System.out.println();
                System.out.print("ID: " + rs.getInt("id"));
                System.out.print(", FirsName: " + rs.getString("first_name"));
                System.out.print(", LastName: " + rs.getString("last_name"));
                System.out.print(", Date: " + rs.getDate("joined_date"));
                System.out.print(", Email: " + rs.getString("email"));
                connection.commit();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                JDBCHelper.closeConnection(connection);
                JDBCHelper.closePreparedStatement(preparedStatement);
                JDBCHelper.closeResultSet(rs);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }

    @Override
    public void deleteAllRecords() {
        String sql = "DELETE FROM persons" ;
        Connection connection = null;
        PreparedStatement preparedStatement = null;


        try {
            connection = JDBCHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            int effectedRow=preparedStatement.executeUpdate();
            if (effectedRow < 1){               /// hocaya sor
                System.out.println("Veri tabaninda zaten veri yok.");
            }else{
                System.out.println("Veri tabani sifirlandi");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                JDBCHelper.closeConnection(connection);
                JDBCHelper.closePreparedStatement(preparedStatement);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void updateEmail(String email, int id) {
        String sql = "UPDATE persons SET  email=? WHERE id=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;


        try {
            connection = JDBCHelper.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,email);
            preparedStatement.setInt(2,id);
            int effectedRow =preparedStatement.executeUpdate();
            if (effectedRow>0){
                connection.commit();
                System.out.println("Update islemi basarili");
            }else{
                System.out.println("Lutfen isleminizi kontrol ediniz \n Veri guncellenemedi..!!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                JDBCHelper.closeConnection(connection);
                JDBCHelper.closePreparedStatement(preparedStatement);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void getPersonById(int byId) {
        String sql = "SELECT id, first_name, last_name, joined_date, email FROM persons WHERE id=?" ;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,byId);
            resultSet = preparedStatement.executeQuery();

            if(resultSet != null){      //Hocaya sorr
            while(resultSet.next()){
                System.out.println(resultSet.getInt("id") + " " + resultSet.getString("first_name")+" "
                        +resultSet.getString("last_name")+" "+resultSet.getDate("joined_date")+" "
                        + resultSet.getString("email"));
            }}else{
                System.out.println("Lutfen gecerli bir ID giriniz...");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                JDBCHelper.closeConnection(connection);
                JDBCHelper.closePreparedStatement(preparedStatement);
                JDBCHelper.closeResultSet(resultSet);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void deletePerson(int byId1) {

        String sql = "DELETE FROM persons WHERE id=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JDBCHelper.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,byId1);
            int effectedRow=preparedStatement.executeUpdate();
            if (effectedRow>0){
                System.out.println("Islem gerceklesti");
                connection.commit();
            }else{
                System.out.println("Lutfen gecerli bir ID giriniz....");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                JDBCHelper.closeConnection(connection);
                JDBCHelper.closePreparedStatement(preparedStatement);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


}

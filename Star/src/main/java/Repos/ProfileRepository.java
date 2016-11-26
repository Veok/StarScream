package Repos;

import Computer.Profile;
import Repos.Mappers.IMapRSIntoEntity;

import java.sql.*;

public class ProfileRepository extends BaseRepository<Profile> {
    public ProfileRepository(Connection connection, IMapRSIntoEntity<Profile> mapper){
        super(connection, mapper);
    }

    protected String CreateTableSQL() {
        return "CREATE TABLE Profile(" +
                "ID int GENERATED BY DEFAULT AS IDENTITY" +
                "Name VARCHAR (30)" +
                "Surname VARCHAR(40)" +
                "Email VARCHAR(50)" +
                "Password VARCHAR (18)" +
                "TotalPrice int" +
                ")";
    }

    @Override
    protected String tableName() {
        return "Profile";
    }

    protected String InsertSQL(){
       return "INSERT INTO Profile(Name, Surname, Email, Password, TotalPrice) VALUES (?, ?, ?, ?, ?)";
    }

    protected String UpdateSQL(){
       return "UPDATE Profile set Name = ?, Surname = ?, Email = ?, Password = ?, TotalPrice = ?";
    }

    @Override
    protected void setupInsert(Profile entity) throws SQLException{
        Insert.setString(1, entity.getName());
        Insert.setString(2, entity.getSurname());
        Insert.setString(3, entity.getEmail());
        Insert.setString(4, entity.getPassword());
        Insert.setInt(5, entity.getTotalPrice());
    }

    @Override
    protected void setupUpdate(Profile entity) throws SQLException{
        Update.setString(1, entity.getName());
        Update.setString(2, entity.getSurname());
        Update.setString(3, entity.getEmail());
        Update.setString(4, entity.getPassword());
        Update.setInt(5, entity.getTotalPrice());
    }
}

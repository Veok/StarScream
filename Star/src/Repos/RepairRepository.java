package Repos;

import Computer.Repair;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepairRepository {
    private Connection connection;

    private String CreateTableSQL = "CREATE TABLE Repair(" +
            "ID int GENERATED BY DEFAULT AS IDENTITY" +
            "ProfileID int" +
            "NameOfDevice VARCHAR(50)" +
            "DateOfAcceptance date" +
            "ExpectedDateOfReception date" +
            "Price int" +
            ")";

    private Statement createTable;

    private String InsertSQL = "INSERT INTO Repair(Profile ID, NameOfDevice, DateOfAcceptance, ExpectedDateOfReception, Price) VALUES (?, ?, ?, ?, ?)";
    private String DeleteSQL = "DELETE FROM Repair WHERE ID = ?";
    private String UpdateSQL = "UPDATE Repair set Profile ID = ?, NameOfDevice = ?, DateOfAcceptance = ?, ExpectedDateOfReception = ?, Price = ? WHERE ID = ?";
    private String SelectByIdSQL = "SELECT * FROM Repair WHERE ID=?";
    private String SelectAllSQL = "SELECT * FROM Repair";

    private PreparedStatement Insert;
    private PreparedStatement Delete;
    private PreparedStatement Update;
    private PreparedStatement SelectByID;
    private PreparedStatement SelectAll;

    public RepairRepository(Connection connection){
        this.connection = connection;

        try{
            createTable = connection.createStatement();

            boolean tableExists = false;
            ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
            while (rs.next()){
                if(rs.getString("TABLE_NAME").equalsIgnoreCase("Repair")){
                    tableExists = true;
                    break;
                }
            }
            if(!tableExists){
                createTable.executeUpdate(CreateTableSQL);
                Insert = connection.prepareStatement(InsertSQL);
                Delete = connection.prepareStatement(DeleteSQL);
                Update = connection.prepareStatement(UpdateSQL);
                SelectByID = connection.prepareStatement(SelectByIdSQL);
                SelectAll = connection.prepareStatement(SelectAllSQL);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Repair get(int RepairID){
        try{
            SelectByID.setInt(1, RepairID);
            ResultSet rs = SelectByID.executeQuery();
            while (rs.next()){
                Repair result = new Repair();
                result.setID(RepairID);
                result.setProfileID(rs.getInt("ProfileID"));
                result.setPrice(rs.getInt("Price"));
                result.setStartRepairDate(rs.getDate("DateOfAcceptance"));
                result.setEndRepairDate(rs.getDate("ExpectedDateOfReception"));
                result.setNameOfDeviceInRepair(rs.getString("NameOfDevice"));
                return result;
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public List<Repair> getAll(){
        try {
            List<Repair> result = new ArrayList<Repair>();
            ResultSet rs = SelectAll.executeQuery();
            while (rs.next()){
                Repair r = new Repair();
                r.setID(rs.getInt("ID"));
                r.setProfileID(rs.getInt("ProfileID"));
                r.setPrice(rs.getInt("Price"));
                r.setStartRepairDate(rs.getDate("DateOfAcceptance"));
                r.setEndRepairDate(rs.getDate("ExpectedDateOfReception"));
                r.setNameOfDeviceInRepair(rs.getString("NameOfDevice"));
                result.add(r);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public void Delete (Repair R){
        try{
            Delete.setInt(1, R.getID());
            Delete.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void Add (Repair R){
        try {
            Insert.setInt(1, R.getPrice());
            Insert.setInt(2, R.getProfileID());
            Insert.setDate(3, R.getStartRepairDate());
            Insert.setDate(4, R.getEndRepairDate());
            Insert.setString(5, R.getNameOfDeviceInRepair());
            Insert.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void Update (Repair R){
        try {
            Update.setInt(1, R.getPrice());
            Update.setInt(2, R.getProfileID());
            Update.setDate(3, R.getStartRepairDate());
            Update.setDate(4, R.getEndRepairDate());
            Update.setString(5, R.getNameOfDeviceInRepair());
            Update.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
package Computer;

import java.sql.Date;
import java.util.List;

public class Repair extends PriceList {
//Price = get.somethingFromPriceList
    private int ID;
    private int Price;
    private Date StartRepairDate;
    private Date EndRepairDate;
    private String NameOfDeviceInRepair;
    private List<Repair> Repairs;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public Date getStartRepairDate() {
        return StartRepairDate;
    }

    public void setStartRepairDate(Date startRepairDate) {
        StartRepairDate = startRepairDate;
    }

    public Date getEndRepairDate() {
        return EndRepairDate;
    }

    public void setEndRepairDate(Date endRepairDate) {
        EndRepairDate = endRepairDate;
    }

    public String getNameOfDeviceInRepair() {
        return NameOfDeviceInRepair;
    }

    public void setNameOfDeviceInRepair(String nameOfDeviceInRepair) {
        NameOfDeviceInRepair = nameOfDeviceInRepair;
    }


}

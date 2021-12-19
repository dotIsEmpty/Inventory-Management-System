package sample;

/**
 * Supplied class Part.java
 */

import java.awt.*;

/**
 *
 * @author Josh Brown
 */
public abstract class Part {
    private static int count = 1;
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    private int machineID;
    private String companyName;

    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = count;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        count = count + 2;
    }

    public Part() {
    }

    /**
     * @return the id
     */
    public int getId() { return id; }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        //id = count;
        this.id = count; //this.id = id;
        count = count + 2;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    //Delete machineID getter if not useful
    /**
     * @return the machine ID
     */
    public int getMachineID() {
        return machineID;
    }

    //Delete machineID setter if not useful
    /**
     * @param machineID the machineID to set
     */
    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }

    //Delete company name getter if not useful
    /**
     * @return the company name
     */
    public String getCompanyName() {
        return companyName;
    }

    //Delete company name setter if not useful
    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}

class InHouse extends Part {
    private int machineID;

    public InHouse(int id, String name, double price, int stock, int min, int max, int machineID) {
        super(id, name, price, stock, min, max); //Invokes constructors of the superclass (Part class)
        this.machineID = machineID;
    }

    public InHouse() {
    }

    public void setMachineID(int machineID){
        this.machineID = machineID;
    }

    public int getMachineID() {
        return machineID;
    }
}

class OutSourced extends Part{
    //private int id;
    private String companyName;
    public OutSourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    public OutSourced() {
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }
}

package Grocerry_Management;

public class Check_Availibility_Output {
    boolean isAvailable;
    int cost = 0;
    int quantity = 0;



    public Check_Availibility_Output(boolean isAvailable) {
        this.isAvailable = isAvailable;
        this.cost = 0;
        this.quantity =  0;
    }

    public Check_Availibility_Output(boolean b, String item_name, int cost, int quantity) {
        this.isAvailable = isAvailable;
        this.cost = cost;
        this.quantity = quantity;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getCost() {
        return cost;
    }

    public int getQuantity() {
        return quantity;
    }
}

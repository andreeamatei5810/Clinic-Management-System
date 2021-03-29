package ClinicRelated;

public class Medicine {

    private String medicineName;
    private int price;
    private String companyName;

    public Medicine(String medicineName, int price, String companyName) {
        this.medicineName = medicineName;
        this.price = price;
        this.companyName = companyName;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "Name: " + medicineName;
    }
}

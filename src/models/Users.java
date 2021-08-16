package models;

public class Users {
    private String firstName;
    private String lastName;
    private String address;
    private String NIC;
    private String contactNo;


    public Users(String firstName, String lastName, String address, String NIC, String contactNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.NIC = NIC;
        this.contactNo = contactNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
}

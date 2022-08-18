package users;

public class User {
    private String ID;
    private String Name;
    private String Email;
    private String Contact;
    private String Address;
    private String Password;
    private String UserType;

    public User(String ID, String name, String email, String contact, String address, String password, String userType) {
        this.ID = ID;
        Name = name;
        Email = email;
        Contact = contact;
        Address = address;
        Password = password;
        UserType = userType;
    }

    public User() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }
}

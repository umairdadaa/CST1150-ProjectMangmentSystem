package users;

public class Project {
    private String ID;
    private String Title;
    private String Client;
    private String Requirements;
    private String Cost;
    private String DeliveryDate;



    public Project(String id, String title, String client, String requirements, String cost, String deliveryDate) {
        ID=id;
        Title = title;
        Client = client;
        Requirements = requirements;
        Cost = cost;
        DeliveryDate = deliveryDate;
    }

    public Project() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getClient() {
        return Client;
    }

    public void setClient(String client) {
        Client = client;
    }

    public String getRequirements() {
        return Requirements;
    }

    public void setRequirements(String requirements) {
        Requirements = requirements;
    }

    public String getCost() {
        return Cost;
    }

    public void setCost(String cost) {
        Cost = cost;
    }

    public String getDeliveryDate() {
        return DeliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        DeliveryDate = deliveryDate;
    }
}

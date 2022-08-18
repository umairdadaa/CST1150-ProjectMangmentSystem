package users;

public class Resource {
    private String ResID;
    private String ResName;
    private String ResEmail;
    private String UserType;
    private String ResType;

    public Resource() {
    }

    public Resource(String resID, String resName, String resEmail, String userType, String resType) {
        ResID = resID;
        ResName = resName;
        ResEmail = resEmail;
        UserType = userType;
        ResType = resType;
    }

    public String getResID() {
        return ResID;
    }

    public void setResID(String resID) {
        ResID = resID;
    }

    public String getResName() {
        return ResName;
    }

    public void setResName(String resName) {
        ResName = resName;
    }

    public String getResEmail() {
        return ResEmail;
    }

    public void setResEmail(String resEmail) {
        ResEmail = resEmail;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }

    public String getResType() {
        return ResType;
    }

    public void setResType(String resType) {
        ResType = resType;
    }
}

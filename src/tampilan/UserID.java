package tampilan;

public class UserID {

    private static String userLogin;

    public static void setUserLogin(String id){
        userLogin = id;
    }

    public static String getUserLogin(){
        return userLogin;
    }
}
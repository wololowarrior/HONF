package honf.harshil.com.honf;

/**
 * Created by harshil on 11.10.16.
 */

public class reg {
    String uname;
    String name;
    String password;
    String number;
    public reg(){

    }
    public reg(String uname,String name,String password,String number){
        this.uname=uname;
        this.name=name;
        this.password=password;
        this.number=number;
    }
    public String getuname(){
        return uname;
    }
    public String getName(){
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getNumber() {
        return number;
    }
}

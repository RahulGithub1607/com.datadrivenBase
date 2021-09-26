package Rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {
    public static void main(String[] args) throws IOException {

        Properties config =new Properties();
        Properties OR =new Properties();
        //read Config properties

        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//test//resources//Config.properties");
        config.load(fis);
        System.out.println(config.getProperty("browser"));

        // read OR file

        fis =new FileInputStream(System.getProperty("user.dir") + "//src//test//resources//OR.properties");
        OR.load(fis);
        System.out.println(OR.getProperty("Login_button"));



    }
}

package com.ohgiraffers.section02.template;

import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTemplate {

    /* DB와의 연결 객체가 필요할 때마다 호출 할 수 있는 메소드 정의 */
    public static Connection getConnection(){       // static 정적 메모리 영역에 올라간다. 처음에 만들고 더 이상 만들지 않는다.

        Connection con = null;
        Properties prop = new Properties();

        try {
            prop.load(new FileReader("src/main/java/com/ohgiraffers/config/connection-info.properties"));

            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");

            Class.forName(driver);

            con = DriverManager.getConnection(url, prop);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return con;
    }

    /* DB 연결 객체 반납 시 호출할 메소드 */
    public static void close(Connection con){

        /* Connection 객체가 존재하면서 닫히지 않았을 때 닫아준다. */
        try {
            if(con != null && !con.isClosed()) {        //con.close(); <오류 / NullPointerException
                con.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}

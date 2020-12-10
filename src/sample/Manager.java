package sample;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Manager {
    public void init(){
        String drivername = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        try {
            Class.forName(drivername);
            System.out.println("Success");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("fail");
        }
        String url ="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Studentman;user=sa;password=123456";
        try {
            Connection connection = DriverManager.getConnection(url);
            /*如果需要初始化数据库，在这里执行
             */
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int login(String username, String code){
        /*
        在这里判断用户输入的用户名和密码是否符合要求
        最高权限返回2
        普通权限返回1
        没有该用户返回0
         */
        return 1;
    }

    public List<House> getPing() throws IOException {
        /*
        执行对平层的查询，以List<House>返回
         */
        List<House> list = new LinkedList();
        for(int inte = 0; inte < 10; inte++){
            list.add(new House("huxing",234,"@yue.png","SAAAAAAAA"));
        }
        return list;
    }
}

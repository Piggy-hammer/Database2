package sample;

import com.sun.glass.ui.Size;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class Manager {

    Connection connection;

    public void init() {
        String drivername = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        try {
            Class.forName(drivername);
            System.out.println("Success");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("fail");
        }
        String password = "password=694907182";//自己的密码
        String url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=HouseManager;user=sa;";
        url += password;
        try {
            connection = DriverManager.getConnection(url);
            /*如果需要初始化数据库，在这里执行
             */
            System.out.println("成功连接数据库");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int login(String username, String password) {
        /*
        在这里判断用户输入的用户名和密码是否符合要求
        最高权限返回2
        普通权限返回1
        没有该用户返回0
         */
        String sql = "select * from UserName where ID =? and Password =?";
        //System.out.println(username+" "+password);
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int power = 0;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int num = resultSet.getInt(3);
                System.out.println(num);
                if (num == 2) {
                    System.out.println("尊敬的神，登录成功");
                    return 2;
                } else if (num == 1) {
                    System.out.println("用户您好，登录成功");
                    return 1;
                } else {
                    System.out.println("用户名或密码错误");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return power;
    }

    public ResultSet getHouse(String structure) {
        /*
        执行对House查询，以ResultSet返回
        structure：pingceng,yueceng,etc.
         */
        String sql = "select * from House where Structure =?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, structure);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public List<House> getPing() throws IOException {
        List<House> list = new LinkedList();
        try {
            ResultSet resultSet = getHouse("平层");
            while (resultSet.next()) {
                String location = resultSet.getString(1);
                int size = resultSet.getInt(2);
                String structure = resultSet.getString(3);
                String picture = resultSet.getString(4);
                int price = resultSet.getInt(6);
                System.out.println(structure + price + picture + location);
                House house = new House(structure, price, picture, location, size);
                list.add(house);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<House> getYue() {
        List<House> list = new LinkedList();
        try {
            ResultSet resultSet = getHouse("跃层");
            while (resultSet.next()) {
                String location = resultSet.getString(1);
                int size = resultSet.getInt(2);
                String structure = resultSet.getString(3);
                String picture = resultSet.getString(4);
                int price = resultSet.getInt(6);
                System.out.println(structure + price + picture + location);
                House house = new House(structure, price, picture, location, size);
                list.add(house);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<House> getShu() {
        List<House> list = new LinkedList();
        try {
            ResultSet resultSet = getHouse("雅墅");
            while (resultSet.next()) {
                String location = resultSet.getString(1);
                int size = resultSet.getInt(2);
                String structure = resultSet.getString(3);
                String picture = resultSet.getString(4);
                int price = resultSet.getInt(6);
                System.out.println(structure + price + picture + location);
                House house = new House(structure, price, picture, location, size);
                list.add(house);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<House> getHolder(String user) throws IOException {
        /*
        执行对所有房东是user的房屋的查询，以List<House>返回
        user:房屋持有者的身份证号
         */
        String sql = "select * from House where HouseHolderID =?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<House> list = new LinkedList();
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, user);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String location = resultSet.getString(1);
                int size = resultSet.getInt(2);
                String structure = resultSet.getString(3);
                String picture = resultSet.getString(4);
                int price = resultSet.getInt(6);
                System.out.println(structure + price + picture + location);
                House house = new House(structure, price, picture, location, size);
                list.add(house);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*for (int inte = 0; inte < 10; inte++) {
            list.add(new House("huxing", 234, "/2f9dd9642431bbef883d72313dd8aed4.jpg", "SAAifsgoihguioashdguhwdosiughawoushgouihAAAAAA"));
        }*/
        return list;
    }

    public void rent(String loc, String user) {
        /*
        用户名为user的客户，租用了loc号房产
         */
        int md5 = user.hashCode();
        String RentingID = String.valueOf(md5);
        String sql1 = "update House set ForRentOrNot = '否' where HLocation =?";
        String sql2 = "insert into Renting values(?,?,?,?,?,?,?,?) ";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql1);
            statement.setString(1, loc);
            statement.executeQuery();
            statement = connection.prepareStatement(sql2);
            //statement.setString();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(user + "fsgag" + loc);

    }

    public void delete(String loc, String holder) {
        /*
        用户名为holder的客户，删除了loca号房产
         */
        String sql1 = "delete from House set where HLocation =? and ";
        String sql2 = "insert into Renting values(?,?,?,?,?,?,?,?) ";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql1);
            statement.setString(1, loc);
            statement.executeQuery();
            statement = connection.prepareStatement(sql2);
            //statement.setString();

            System.out.println("成功删除");
            return;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("删除过程中出现错误");
    }

    public void insert(House house, String holder) {
        /*
        用户名为holder的客户，添加了loca号房产，holder是房东
         */
        String loc = house.Loca;
        String structure = house.huXing;
        String pic = house.Pic;
        int size = house.Size;
        String sql1 = "insert into House values(?,?,?,?,?,?,?,?,?,?) ";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql1);
            statement.setString(1, loc);
            statement.setInt(2, size);
            statement.setString(3, null);
            statement.setString(4, null);
            statement.setString(5, holder);
            statement.setInt(6, 0);
            statement.setInt(7, 0);
            statement.setString(8, "否");
            statement.setString(9, "否");
            statement.setString(10, "一川公司塘朗营业部");
            statement.executeQuery();
            //statement.setString();

            System.out.println("成功添加");
            return;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("添加中发生错误");
    }


    public ObservableList<HouseInformation>
    HouseSearch(String Location, String stucture, String size, String price, String Owner) {
        /*
        返回符合上诉条件的房源信息
        其中 structure以“平层”或“所有房型”的形式给出； size以“<100”或“100~150”或具体值形式给出； price以“<2000”或“2000~4000”或具体值形式给出
        具体形式参见 AdministratorController
        全部则是*
         */
        String sql = "select * from House where HLocation =? and Structure=? and Size between ? and ? and SalePrice between ? and ? and HouseHolderID = ? ";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ObservableList<HouseInformation> list = FXCollections.observableArrayList();
        try {
            statement = connection.prepareStatement(sql);
            if (Location.equals("所有地址")) {
                statement.setString(1, "House.HLocation");
                System.out.println("1");
            } else
                statement.setString(1, Location);
            if (stucture.equals("所有户型")) {
                statement.setString(2, "House.Structure");
                System.out.println("2");
            } else
                statement.setString(2, stucture);
            if (size.equals("所有面积")) {
                statement.setString(3, "0");
                statement.setString(4, "999999999999");
                System.out.println("3");
            } else {
                String size_up;//higher bound
                String size_do;//lowwer bound
                if (size.substring(0, 1).equals(">")) {
                    statement.setString(3, size);
                } else if (size.substring(0, 1).equals("<")) {
                    statement.setString(3, size);
                } else {
                    String size_new[] = size.split("~");
                    size_up = size_new[1];
                    size_do = size_new[0];
                    String statement_size = "between " + size_do + " and " + size_up;
                    statement.setString(3, statement_size);
                }
            }
            if (price.equals("所有价格")) {
                statement.setString(5, "0");
                statement.setString(6, "99999999999999");
                System.out.println("4");
            } else {
                String price_up;//higher bound
                String price_do;//lowwer bound
                if (price.substring(0, 1).equals(">")) {
                    statement.setString(3, price);
                } else if (price.substring(0, 1).equals("<")) {
                    statement.setString(3, price);
                } else {
                    String price_new[] = size.split("~");
                    price_up = price_new[1];
                    price_do = price_new[0];
                    String statement_size = "between " + price_do + " and " + price_up;
                    statement.setString(3, statement_size);
                }
            }
            if (Owner.equals("所有房东")) {
                statement.setString(7, "House.HouseHolderID");
                System.out.println("5");
            } else {
                statement.setString(5, Owner);
            }
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String location = resultSet.getString(1);
                int size_new = resultSet.getInt(2);
                String structure = resultSet.getString(3);
                String picture = resultSet.getString(4);
                int price_new = resultSet.getInt(6);
                String ownerID = resultSet.getString(5);
                System.out.println(structure + price + picture + location);
                HouseInformation houseIn = new HouseInformation(location, structure, price_new, size_new, picture, ownerID);
                list.add(houseIn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*ObservableList list = FXCollections.observableArrayList();
        for (int t = 0; t < 100; t++)
            list.add(new HouseInformation(RString(10), RString(15), (int) (Math.random() * 100), (int) (Math.random() * 9999), RString(30), RString(20)));
        list.add(new HouseInformation("a", "pingceng", 101, 2005, "pic", "我"));*/
        return list;
    }

    public String RString(int n) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String o = "";
        for (int t = 0; t < n; t++) o += str.charAt((int) (Math.random() * 61));
        return o;
    }

    public void insertF(HouseInformation houseInformation) {
        //新增一个房源
    }

    public ObservableList<RenterInformation> getRenter(String ID, String name, String sex, String tel, String wechat) {
        ObservableList<RenterInformation> list = FXCollections.observableArrayList();
        return list;
    }

    public void insertR(RenterInformation renterInformation) {
        //新增一个租房客
    }

    public void deleteR(String id) {
        //删除一个提供id的租房客
    }
}

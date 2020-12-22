package sample;

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
        最高权限返回3
        管理权限返回2
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
                System.out.println("您的权限是" + num);
                if (num == 3) {
                    System.out.println("真正的神，登录成功");
                    return 3;
                } else if (num == 2) {
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

    public List<HouseInformation> getPing(String datefrom, String dateto) {
        List<HouseInformation> list = new LinkedList();
        try {
            ResultSet resultSet = getHouse("平层");
            while (resultSet.next()) {
                String location = resultSet.getString(1);
                int size = resultSet.getInt(2);
                String structure = resultSet.getString(3);
                String picture = resultSet.getString(4);
                int price = resultSet.getInt(6);
                System.out.println(structure + price + picture + location);
                HouseInformation house = new HouseInformation(structure, price, picture, location, size,);
                list.add(house);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HouseInformation> getYue(String datefrom, String dateto) {
        List<HouseInformation> list = new LinkedList();
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

    public List<HouseInformation> getShu(String datefrom, String dateto) {
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

    public List<HouseInformation> getHolder(String user) throws IOException {
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

    public String rent(HouseInformation e, String userId, String datefrom, String dateto) {
        /*
        用户名为userId的客户，租用了loc号房产，返回合约号
         */
        int md5 = userId.hashCode();
        String RentingID = String.valueOf(md5);
        String sql1 = "update House set ForRentOrNot = '否' where HLocation =?";
        String sql2 = "insert into Renting values(?,?,?,?,?,?,?,?) ";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql1);
            statement.setString(1, loc);
            statement.execute();
            statement = connection.prepareStatement(sql2);
            //statement.setString();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(user + "fsgag" + loc);

        return RentingID;
    }

    public void delete(String loc, String holder) {
        /*
        用户名为holder的客户，删除了loca号房产
         */
        String sql1 = "delete from House where HLocation =?  ";
        String sql2 = "insert into Renting values(?,?,?,?,?,?,?,?) ";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql1);
            statement.setString(1, loc);
            statement.execute();
            statement = connection.prepareStatement(sql2);
            //statement.setString();

            System.out.println("成功删除");
            return;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("删除过程中出现错误");
    }


    public ObservableList<HouseInformation>
    HouseSearch(String Location, String stucture, String size, String price, String Owner) {
        /*
        返回符合上诉条件的房源信息
        其中 structure以“平层”或“所有房型”的形式给出； size以“<100”或“100~150”或具体值形式给出； price以“<2000”或“2000~4000”或具体值形式给出
        具体形式参见 AdministratorController
        全部则是*

        20201219说明：没有实现防注入
         */
        String sql = "select * from House where ";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ObservableList<HouseInformation> list = FXCollections.observableArrayList();
        try {
            statement = connection.prepareStatement(sql);
            if (Location.equals("所有地址")) {
                sql += "HLocation = House.HLocation";
                System.out.println("1");
            } else
                sql += "HLocation = " + Location + " ";
            if (stucture.equals("所有户型")) {
                sql += " and Structure = House.Structure";
                System.out.println("2");
            } else
                sql += " and Stucture = " + stucture + " ";
            if (size.equals("所有面积")) {
                sql += " and Size = House.Size";
                System.out.println("3");
            } else {
                String size_up;//higher bound
                String size_do;//lowwer bound
                if (size.substring(0, 1).equals(">")) {
                    sql += " and Size = " + size + " ";
                } else if (size.substring(0, 1).equals("<")) {
                    sql += " and Size = " + size + " ";
                } else {
                    String size_new[] = size.split("~");
                    size_up = size_new[1];
                    size_do = size_new[0];
                    String statement_size = "between " + size_do + " and " + size_up;
                    sql += " and Size  " + statement_size + " ";
                }
            }
            if (price.equals("所有价格")) {
                sql += " and SalePrice = House.SalePrice";
                System.out.println("4");
            } else {
                String price_up;//higher bound
                String price_do;//lowwer bound
                if (price.substring(0, 1).equals(">")) {
                    sql += " and Price = " + price + " ";
                } else if (price.substring(0, 1).equals("<")) {
                    sql += " and Price = " + price + " ";
                } else {
                    String price_new[] = size.split("~");
                    price_up = price_new[1];
                    price_do = price_new[0];
                    String statement_size = "between " + price_do + " and " + price_up;
                    sql += " and Price = " + statement_size + " ";
                }
            }
            if (Owner.equals("所有房东")) {
                sql += " and HouseHolderID =  House.HouseHolderID";
                System.out.println("5");
            } else {
                sql += " and HouseHolderID = " + Owner + " ";
            }
            statement = connection.prepareStatement(sql);
            System.out.println(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String location = resultSet.getString(1);
                int size_new = resultSet.getInt(2);
                String structure = resultSet.getString(3);
                String picture = resultSet.getString(4);
                int price_new = resultSet.getInt(6);
                System.out.println(7);
                String ownerID = resultSet.getString(5);
                System.out.println(structure + "," + price_new + "," + picture + "," + location);
                HouseInformation houseIn = new HouseInformation(location, structure, size_new, price_new, picture, ownerID);
                list.add(houseIn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*for (int t = 0; t < 100; t++)
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
        HouseInformation house = houseInformation;
        String pic = null;
        String loc = house.getLocation();
        String structure = house.getStructure();
        pic = house.getPic();
        pic = "file:/" + pic.replaceAll("\\\\", "/");
        System.out.println(pic+" bbbb");
        int size = house.getSize();
        String holder = house.getOwner();
        String sql1 = "insert into House values(?,?,?,?,?,?,?,?,?,?) ";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql1);
            statement.setString(1, loc);
            statement.setInt(2, size);
            statement.setString(3, structure);
            statement.setString(4, pic);
            statement.setString(5, holder);
            statement.setInt(6, 0);
            statement.setInt(7, 0);
            statement.setString(8, "否");
            statement.setString(9, "否");
            statement.setString(10, "一川公司塘朗营业部");
            statement.execute();
            //statement.setString();

            System.out.println("成功添加");
            return;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("添加中发生错误");
    }

    public ObservableList<RenterInformation> getRenter(String ID, String name, String sex, String tel, String wechat) {
        ObservableList<RenterInformation> list = FXCollections.observableArrayList();
        String sql = "select * from Renter where ";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            if (ID.equals("所有ID")) {
                sql += " RenterID = Renter.RenterID";
                System.out.println("1");
            } else
                sql += " RenterID = " + ID + " ";
            if (name.equals("所有姓名")) {
                sql += " and RenterName = Renter.RenterName";
                System.out.println("2");
            } else
                sql += " and RenterName = " + name + " ";
            if (sex.equals("所有性别")) {
                sql += " and RSex = Renter.RSex";
                System.out.println("3");
            } else {
                sql += " and RSex = " + sex;
            }
            if (tel.equals("所有手机号")) {
                sql += " and RTel = Renter.RTel";
                System.out.println("4");
            } else {
                sql += " and RTel = " + tel;
            }
            if (wechat.equals("所有微信号")) {
                sql += " and RWechat = Renter.RWechat";
                System.out.println("5");
            } else {
                sql += " and RWechat = " + wechat;
            }
            statement = connection.prepareStatement(sql);
            System.out.println(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String newID = resultSet.getString(1);
                String newName = resultSet.getString(2);
                String newSex = resultSet.getString(3);
                String newTel = resultSet.getString(4);
                String newWechat = resultSet.getString(5);
                System.out.println(6);
                System.out.println(newID + "," + newName + "," + newSex + "," + newTel + "," + newWechat);
                RenterInformation renterInfo = new RenterInformation(newID, newName, newSex, newTel, newWechat);
                list.add(renterInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*for (int t = 0; t < 100; t++)
            list.add(new HouseInformation(RString(10), RString(15), (int) (Math.random() * 100), (int) (Math.random() * 9999), RString(30), RString(20)));
        list.add(new HouseInformation("a", "pingceng", 101, 2005, "pic", "我"));*/
        return list;
    }

    public void insertR(RenterInformation renterInformation) {
        //新增一个租房客
        String id = renterInformation.getID();
        String name = renterInformation.getName();
        String sex = renterInformation.getSex();
        String tel = renterInformation.getTel();
        String wechat = renterInformation.getWechat();
        String sql1 = "insert into Renter values(?,?,?,?,?) ";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql1);
            statement.setString(1, id);
            statement.setString(2, name);
            statement.setString(3, sex);
            statement.setString(4, tel);
            statement.setString(5, wechat);

            statement.execute();
            //statement.setString();

            System.out.println("成功添加");
            return;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("添加中发生错误");
    }

    public void deleteR(String id) {
        //删除一个提供id的租房客
        String sql1 = "delete from Renter where RenterID = ? ";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql1);
            statement.setString(1, id);
            statement.execute();
            System.out.println("成功删除");
            return;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("删除中发生错误");
    }

    public ObservableList<DealInformation> RentingSearch(String rentingId, String renterId, String householderId, String location, String datefrom, String dateto, String price) {
        //搜索符合上诉条件的renting信息, dafrom格式为"2020-12-19",除确定值外，可能传入"所有合约号","所有租户ID","所有房东ID","所有地址"或“2000~4000”
        ObservableList<DealInformation> list = FXCollections.observableArrayList();
        return list;
    }

    public void deleteD(String dealId) {
        //删除合约号为rentingId的renting记录
    }

    public void insertD(DealInformation dealInformation) {
        //新建renting中记录
    }

    public ObservableList<AuthorityInformation> AuthoritySearch(String id, int authority) {
        //搜索用户名为id, 权限等级为authority的用户,可能出现“所有用户名”,authority = 9代表搜索所有权限等级
    }

    public void deleteA(String id) {
        //删除用户名为id的用户
    }

    public void insertA(AuthorityInformation authorityInformation) {
        //插入用户
    }
}

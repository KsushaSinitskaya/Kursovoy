package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB {

    private static Connection connection;

    public static void connect(String database, String user, String password, String port) {
        try {
            connection = DriverManager.getConnection(("jdbc:mysql://localhost:" + port + "/" +
                    database + "?serverTimezone=UTC"), user, password);
        } catch (SQLException sqlexc) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            sqlexc.printStackTrace();
        }
    }

    public static String login(String login, String password) {
        ResultSet resultSet;
        String accountType = "";

        String select = "SELECT * FROM users WHERE login=? and password=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                accountType = resultSet.getString("account_type");
            } else {
                accountType = "";
            }
        } catch (SQLException e) {
            System.err.println("login function exception");
            e.printStackTrace();
        }
        return accountType;
    }

    public static List<String> getMakes() {
        ResultSet resultSet;
        List<String> makes = new ArrayList<>(0);

        String select = "SELECT * FROM make";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                makes.add(resultSet.getString("make"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return makes;
    }

    public static List<String> getModels(String make) {
        ResultSet resultSet;
        List<String> models = new ArrayList<>(0);

        String select = "SELECT * FROM " + make + "_models";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder temp = new StringBuilder();
                temp.append(resultSet.getString("price")).append(" ").
                        append(resultSet.getString("model"));
                models.add(temp.toString());
            }
        } catch (SQLException e) {
        }
        return models;
    }

    public static boolean addModel(String make, String model, int price){
        String addAudi="INSERT INTO dealership.audi_models (model, price) VALUES (?, ?)";
        String addBMW="INSERT INTO dealership.bmw_models (model, price) VALUES (?, ?)";
        String addMercedes="INSERT INTO dealership.mercedes_models (model, price) VALUES (?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            if(make.equalsIgnoreCase("audi"))
                preparedStatement = connection.prepareStatement(addAudi);
            else if(make.equalsIgnoreCase("bmw"))
                preparedStatement = connection.prepareStatement(addBMW);
            else if(make.equalsIgnoreCase("mercedes"))
                preparedStatement = connection.prepareStatement(addMercedes);
            preparedStatement.setString(1, model);
            preparedStatement.setInt(2, price);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static ArrayList<String> getOrders() {
        ResultSet resultSet;
        ArrayList<String> ordersList = new ArrayList<>(0);
        String select = "SELECT * FROM dealership.order";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder order = new StringBuilder();
                order.append(resultSet.getInt(1) + " " + resultSet.getString(2) +
                        " " + resultSet.getString(3) + " " + resultSet.getInt(5) + " " +
                        resultSet.getString(6) + " " + resultSet.getString(7) +
                        " " + resultSet.getString(4));
                ordersList.add(order.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersList;
    }

    public static ArrayList<String> getOrderClient(String name, String surname) {
        ResultSet resultSet;
        ArrayList<String> ordersList = new ArrayList<>(0);
        String select = "SELECT * FROM dealership.order WHERE name=? and surname=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder order = new StringBuilder();
                order.append(resultSet.getString("make")).append(" ").
                        append(resultSet.getString("year")).append(" ").
                        append(resultSet.getString("price")).append(" ").
                        append(resultSet.getBoolean("order_accepted")).append(" ").
                        append(resultSet.getBoolean("order_completed")).append(" ").
                        append(resultSet.getString("color")).append(" ").
                        append(resultSet.getString("country")).append(" ").
                        append(resultSet.getString("insurance")).append(" ").
                        append(resultSet.getString("model"));
                ordersList.add(order.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersList;
    }

    public static ArrayList<String> getOrdersManager() {
        ResultSet resultSet;
        ArrayList<String> list = new ArrayList<>(0);
        String select = "SELECT * FROM dealership.order";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder order = new StringBuilder();
                order.append(resultSet.getInt("number")).append(" ").
                        append(resultSet.getString("name")).append(" ").
                        append(resultSet.getString("surname")).append(" ").
                        append(resultSet.getString("make")).append(" ").
                        append(resultSet.getString("year")).append(" ").
                        append(resultSet.getString("price")).append(" ").
                        append(resultSet.getBoolean("order_accepted")).append(" ").
                        append(resultSet.getBoolean("order_completed")).append(" ").
                        append(resultSet.getString("color")).append(" ").
                        append(resultSet.getString("country")).append(" ").
                        append(resultSet.getString("insurance")).append(" ").
                        append(resultSet.getString("model"));
                list.add(order.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<String> getSupplierOrder() {
        ResultSet resultSet;
        ArrayList<String> list = new ArrayList<>(0);
        String select = "SELECT * FROM dealership.supplier_order";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder order = new StringBuilder();
                order.append(resultSet.getInt("id")).append(" ").
                        append(resultSet.getString("supplier")).append(" ").
                        append(resultSet.getString("make")).append(" ").
                        append(resultSet.getString("color")).append(" ").
                        append(resultSet.getInt("quantity")).append(" ").
                        append(resultSet.getString("model"));
                list.add(order.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<String> getMakeStatistics() {
        ResultSet resultSet;
        ArrayList<String> list = new ArrayList<>(0);
        String select = "SELECT make FROM dealership.order";
        double mercedes = 0;
        double audi = 0;
        double bmw = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("make").equalsIgnoreCase("bmw")) bmw++;
                if (resultSet.getString("make").equalsIgnoreCase("audi")) audi++;
                if (resultSet.getString("make").equalsIgnoreCase("mercedes")) mercedes++;
            }
            double mercedesPart = mercedes / (bmw + mercedes + audi);
            double audiPart = audi / (bmw + mercedes + audi);
            double bmwPart = bmw / (bmw + mercedes + audi);
            list.add(Double.toString(mercedesPart));
            list.add(Double.toString(audiPart));
            list.add(Double.toString(bmwPart));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<String> getYearStats() {
        ResultSet resultSet;
        ArrayList<String> list = new ArrayList<>(0);
        String select = "SELECT year FROM dealership.order";
        int year6=0;
        int year7=0;
        int year8=0;
        int year9=0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt("year")==2016) year6++;
                if (resultSet.getInt("year")==2017) year7++;
                if (resultSet.getInt("year")==2018) year8++;
                if (resultSet.getInt("year")==2019) year9++;
            }
            list.add(Integer.toString(year6));
            list.add(Integer.toString(year7));
            list.add(Integer.toString(year8));
            list.add(Integer.toString(year9));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

        public static boolean addOrderClient(String order) {
        String[] orderDetails = order.split(" ", 12);
        System.out.println(order);
        String insert = "INSERT INTO dealership.order (make, year, name, surname, price, color, country, insurance, model)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, orderDetails[1]);
            preparedStatement.setString(2, orderDetails[2]);
            preparedStatement.setString(3, orderDetails[3]);
            preparedStatement.setString(4, orderDetails[4]);
            preparedStatement.setString(5, orderDetails[5]);
            preparedStatement.setString(6, orderDetails[6]);
            preparedStatement.setString(7, orderDetails[7]);
            preparedStatement.setString(8, orderDetails[8] + " " +
                    orderDetails[9] + " " + orderDetails[10]);
            preparedStatement.setString(9, orderDetails[11]);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean addSupplierOrder(String order) {
        String[] orderDetails = order.split(" ", 6);
        System.out.println(order);
        String insert = "INSERT INTO dealership.supplier_order (make, supplier, model, quantity, color) " +
                "VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, orderDetails[1]);
            preparedStatement.setString(2, orderDetails[3]);
            preparedStatement.setString(3, orderDetails[5]);
            preparedStatement.setString(4, orderDetails[4]);
            preparedStatement.setString(5, orderDetails[2]);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean removeOrder(int orderNumber) {

        String delete = "DELETE FROM dealership.order WHERE number=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setInt(1, orderNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static boolean addManager(String name, String surname, String login, String password) {
        String insertUser = "INSERT INTO dealership.users (account_type, login, password) VALUES (?, ?, ?)";
        String insertManager = "INSERT INTO dealership.managers (name, surname, login, password) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatementUser = connection.prepareStatement(insertUser);
            PreparedStatement preparedStatementManager = connection.prepareStatement(insertManager);
            preparedStatementUser.setString(1, "manager");
            preparedStatementUser.setString(2, login);
            preparedStatementUser.setString(3, password);
            preparedStatementManager.setString(1, name);
            preparedStatementManager.setString(2, surname);
            preparedStatementManager.setString(3, login);
            preparedStatementManager.setString(4, password);
            preparedStatementUser.executeUpdate();
            preparedStatementManager.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean addClient(String name, String surname, String login, String password) {
        String insertUser = "INSERT INTO dealership.users (account_type, login, password) VALUES (?, ?, ?)";
        String insertClient = "INSERT INTO dealership.clients (name, surname, login, password) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatementUser = connection.prepareStatement(insertUser);
            PreparedStatement preparedStatementClient = connection.prepareStatement(insertClient);
            preparedStatementUser.setString(1, "client");
            preparedStatementUser.setString(2, login);
            preparedStatementUser.setString(3, password);
            preparedStatementClient.setString(1, name);
            preparedStatementClient.setString(2, surname);
            preparedStatementClient.setString(3, login);
            preparedStatementClient.setString(4, password);
            preparedStatementUser.executeUpdate();
            preparedStatementClient.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static boolean addSupplier(String name, String country, String make) {
        String insert = "INSERT INTO dealership.supplier (country, make, name) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, country);
            preparedStatement.setString(2, make);
            preparedStatement.setString(3, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static String getClientData(String login, String password) {
        ResultSet resultSet;
        String list = "";
        String select = "SELECT * FROM dealership.clients WHERE login=? and password=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list = resultSet.getString("name") + " " +
                        resultSet.getString("surname");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static String getManagerData(String login, String password) {
        ResultSet resultSet;
        String list = "";
        String select = "SELECT * FROM dealership.managers WHERE login=? and password=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list = resultSet.getString("name") + " " +
                        resultSet.getString("surname");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<String> getInsurance() {
        ResultSet resultSet;
        ArrayList<String> list = new ArrayList<>(0);
        String select = "SELECT * FROM dealership.insurance";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString("price") + " " +
                        resultSet.getString("type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<String> getSupplier(String make) {
        ResultSet resultSet;
        ArrayList<String> list = new ArrayList<>(0);
        String select = "SELECT * FROM dealership.supplier WHERE make=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setString(1, make);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<String> getCountry(String make) {
        ResultSet resultSet;
        ArrayList<String> list = new ArrayList<>(0);

        String select = "SELECT * FROM dealership.supplier WHERE make=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setString(1, make);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString("country"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean addInsurance(String price, String type) {
        String insert = "INSERT INTO dealership.insurance (type, price) VALUES (?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, type);
            preparedStatement.setInt(2, Integer.parseInt(price));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static boolean mark(int orderNumber, String marking) {
        String updateAccept = "UPDATE dealership.order SET order_accepted=1 where number=?";
        String updateComplete = "UPDATE dealership.order SET order_completed=1 where number=?";
        PreparedStatement preparedStatement = null;
        try {
            if (marking.equals("accept")) {
                preparedStatement = connection.prepareStatement(updateAccept);
            }
            if (marking.equals("complete")) {
                preparedStatement = connection.prepareStatement(updateComplete);
            }
            preparedStatement.setInt(1, orderNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
}

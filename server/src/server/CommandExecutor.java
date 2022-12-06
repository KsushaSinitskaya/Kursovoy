package server;

import db.DB;

public class CommandExecutor {

    public static Object split(String command) {
        String[] commandNumber = command.split(" ", 2);
        String[] commands;
        Object result;
        switch (commandNumber[0]) {
            case "login":
                commands = command.split(" ", 3);
                result = DB.login(commands[1], commands[2]);
                break;
            case "getmakes":
                result = DB.getMakes();
                break;
            case "getmodels":
                commands = command.split(" ", 2);
                result = DB.getModels(commands[1]);
                break;
            case "vieworder":
                result = DB.getOrders();
                break;
            case "vieworderclient":
                commands = command.split(" ", 3);
                result = DB.getOrderClient(commands[1], commands[2]);
                break;
            case "viewordersmanager":
                result = DB.getOrdersManager();
                break;
            case "viewsupplierorders":
                result = DB.getSupplierOrder();
                break;
            case "addorderclient":
                result = DB.addOrderClient(command);
                break;
            case "addsupplierorder":
                result = DB.addSupplierOrder(command);
                break;
            case "removeorder":
                commands = command.split(" ", 2);
                result = DB.removeOrder(Integer.parseInt(commands[1]));
                break;
            case "addmanager":
                commands = command.split(" ", 5);
                result = DB.addManager(commands[1], commands[2], commands[3], commands[4]);
                break;
            case "addsupplier":
                commands = command.split(" ", 4);
                result = DB.addSupplier(commands[1], commands[2], commands[3]);
                break;
            case "getclientdata":
                commands = command.split(" ", 3);
                result = DB.getClientData(commands[1], commands[2]);
                break;
            case "getmanagerdata":
                commands = command.split(" ", 3);
                result = DB.getManagerData(commands[1], commands[2]);
                break;
            case "addclient":
                System.out.println(command);
                commands = command.split(" ", 5);
                result = DB.addClient(commands[1], commands[2], commands[3], commands[4]);
                break;
            case "getinsurance":
                result = DB.getInsurance();
                break;
            case "getcountry":
                commands = command.split(" ", 2);
                result = DB.getCountry(commands[1]);
                break;
            case "getsupplier":
                commands = command.split(" ", 2);
                result = DB.getSupplier(commands[1]);
                break;
            case "addinsurance":
                commands = command.split(" ", 3);
                result = DB.addInsurance(commands[1], commands[2]);
                break;
            case "markaccepted":
                commands = command.split(" ", 2);
                result = DB.mark(Integer.parseInt(commands[1]), "accept");
                break;
            case "markcompleted":
                commands = command.split(" ", 2);
                result = DB.mark(Integer.parseInt(commands[1]), "complete");
                break;
            case "getmakesstatistics":
                result = DB.getMakeStatistics();
                break;
            case "getyearstats":
                result = DB.getYearStats();
                break;
            case "addmodel":
                System.out.println(command);
                commands = command.split(" ", 4);
                result = DB.addModel(commands[1], commands[2], Integer.parseInt(commands[3]));
                break;
            default:
                result = false;
                break;
        }
        return result;
    }
}

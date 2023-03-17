package BLL;

import Data.*;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @invariant isWellFormed()
 */

public class DeliveryService extends Observable implements IDeliveryServiceProcessing, Serializable {
    private static final long serialVersionUID = 8735017722641974109L;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private List<User> users;
    private Map<Order, List<MenuItem>> placedOrders;
    private List<MenuItem> menu;
    private List<MenuItem> shoppingBasket;
    private List<MenuItem> compositeItem;
    private List<Order> orders;

    public DeliveryService() {
        users = new ArrayList<>();
        placedOrders = new TreeMap<>();
        menu = new ArrayList<>();
        shoppingBasket = new ArrayList<>();
        compositeItem = new ArrayList<>();
        orders = new ArrayList<>();
    }

    public List<MenuItem> getitems() {
        return menu;
    }
    public List<User> getUsers() {
        return users;
    }
    public Map<Order, List<MenuItem>> getPlacedOrders() {
        return placedOrders;
    }
    public List<MenuItem> getMenu() {
        return menu;
    }
    public List<MenuItem> getShoppingBasket() {
        return shoppingBasket;
    }
    public List<MenuItem> getCompositeItem() {
        return compositeItem;
    }
    public List<Order> getOrders() {
        return orders;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setPlacedOrders(Map<Order, List<MenuItem>> placedOrders) {
        this.placedOrders = placedOrders;
    }

    public void setMenu(List<MenuItem> menu) {
        this.menu = menu;
    }

    public void setShoppingBasket(List<MenuItem> shoppingBasket) {
        this.shoppingBasket = shoppingBasket;
    }

    public void setCompositeItem(List<MenuItem> compositeItem) {
        this.compositeItem = compositeItem;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addToCompositeList(String item) {
        assert item != "";
        MenuItem aux = null;
        for(MenuItem i : menu) {
            if(i.toString().equals(item)) {
                aux = i;
                break;
            }
        }
        assert aux != null;
        compositeItem.add(aux);
    }

    public void addToShoppingBasket(String item) {
        assert item != "";
        MenuItem aux = null;
        for(MenuItem i : menu) {
            if(i.toString().equals(item)) {
                aux = i;
                break;
            }
        }
        assert  aux != null;
        shoppingBasket.add(aux);
    }

    public void addOrder(Order o, List<MenuItem> list) {
        placedOrders.put(o,list);
    }

    public boolean verifyIfCompositeExists(String menuName) {
        for(MenuItem i : menu) {
            if(i.getTitle().equals(menuName)) {
                return true;
            }
        }

        return false;
    }

    public boolean verifyIfMenuItemExists(MenuItem item, List<MenuItem> list) {
        for(MenuItem i : list ) {
            if(i.getTitle().equals(item.getTitle())) {
                return true;
            }
        }
        return false;
    }


    public boolean isWellFormed(){
        if(menu.size()!=0) {
            return true;
        }
        else return false;
    }

    public String getMenuItems() {
        String str="";
        for(MenuItem m : menu) {
            str = str + m.toString() + "\n";
        }
        return str;
    }


    @Override
    public List<MenuItem> getFromCsv(String fileName) {
        assert fileName != null;
        Pattern pattern = Pattern.compile(",");
        try (Stream<String> line = Files.lines(Path.of(fileName))){
            menu = line.skip(1).distinct().map(line1->{
                String[] attrib = pattern.split(line1);
                return new BaseProduct(
                        attrib[0],
                        Double.parseDouble(attrib[1]),
                        Double.parseDouble(attrib[2]),
                        Double.parseDouble(attrib[3]),
                        Double.parseDouble(attrib[4]),
                        Double.parseDouble(attrib[5]),
                        Double.parseDouble(attrib[6]));
            }).distinct().collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return menu;

    }

    public void importProducts() {
        List<MenuItem> items = getFromCsv("products.csv");
        for(MenuItem item : items) {
            if(verifyIfMenuItemExists(item,menu)==false) {
                menu.add(item);
            }
        }
        //menu.addAll(items);
    }

    public int checkCredentials(String username, String password, String role) {
        for(User u : users) {
            if(u.getName().equals(username) && u.getPassword().equals(password) && u.getRole().equals(role)) {
                return 1;
            }  //everything is ok

            if(!u.getName().equals(username) && u.getPassword().equals(password) && u.getRole().equals(role)) {
                return 2;
            } //wrong name

            if(u.getName().equals(username) && !u.getPassword().equals(password) && u.getRole().equals(role)) {
                return 3;
            } //wrong password

            if(u.getName().equals(username) && !u.getPassword().equals(password) && !u.getRole().equals(role)) {
                return 4;
            } //wrong role
        }

        return 5; //no user found
    }

    public boolean checkUsernameExistence(String username) {
        for(User u : users) {
            if(u.getName().equals(username)) {
                return false;
            }
        }

        return true;
    }

    /**
     * @pre !checkUsernameExistence(username) numele utilizatorului trebuie sa fie unic
     * @param role
     * @param username
     * @param password
     */
    public void addUser(String role, String username, String password) {
        assert !checkUsernameExistence(username) : "Username already exists!";

        if(role.equals("client")) {
            users.add(new Client(username, password));
        }

        if(role.equals("administrator")) {
            users.add(new Administrator(username, password));
        }

        if(role.equals("employee")) {
            users.add(new Employee(username, password));
        }

    }

    public User findUser(String name) {
        for(User u : users) {
            if(u.getName().equals(name)) {
                return u;
            }
        }
        return null;
    }

    /**
     * @pre isWellFormed(), title!="" && rating>=0 && calories >= 0 && protein >= 0 && fat >= 0 && sodium >= 0 && price >=0;
     * @param title
     * @param rating
     * @param calories
     * @param protein
     * @param fat
     * @param sodium
     * @param price
     * @post isWellFormed()
     */

    @Override
    public void addMenuItem(String title, Double rating, Double calories, Double protein, Double fat, Double sodium, Double price) {
        assert isWellFormed();
        assert title!="" && rating>=0 && calories >= 0 && protein >= 0 && fat >= 0 && sodium >= 0 && price >=0;
        BaseProduct baseProduct = new BaseProduct(title, rating, calories, protein, fat, sodium, price);
        menu.add(baseProduct);
        assert isWellFormed();
    }


    /**
     * @pre isWellFormed()
     * @param name numele produsului
     * @post isWellFormed()
     */
    @Override
    public void createMenuItem(String name) {
        assert isWellFormed();
        assert name!="";
        CompositeProduct product = new CompositeProduct(name, compositeItem);
        if(verifyIfCompositeExists(name)==false) {
            //menu.add(product);
            addMenuItem(name, product.computeRating(), product.computeCalories()/2, product.computeProtein()/2, product.computeFat()/2, product.computeSodium()/2, product.computePrice()/2);
        }
        assert isWellFormed();
    }


    /**
     * Aceasta metoda sterge din meniu un produs, cautandu-l dupa nume si comparandu-l dupa Stringul primit ca parametru
     * @pre isWellFormed
     * @param name
     * @post isWellFormed
     */
    @Override
    public void deleteMenuItem(String name) {
        assert isWellFormed();
        MenuItem item = null;
        for(MenuItem m : menu)
        {
            String title = m.getTitle();
            if(name.equals(title))
            {
                item = m;
            }
        }
        menu.remove(item);
        assert isWellFormed();
    }

    /**
     * @pre value!="" && toSetValue!="" && Double.parseDouble(value) > 0 && Double.parseDouble(toSetValue) >=0
     * @param field1
     * @param value
     * @param field2
     * @param toSetValue
     * @post isWellFormed
     */

    @Override
    public void modifyMenuItem(String field1, String value, String field2, String toSetValue) {
        assert value!="" && toSetValue!="" && Double.parseDouble(value) > 0 && Double.parseDouble(toSetValue) >=0  : "Invalid data";
        for (MenuItem menuItem : menu) {
            if (menuItem instanceof BaseProduct) {
                if (((BaseProduct) menuItem).getValue(field1).equals(value)) {
                    ((BaseProduct) menuItem).setValue(field2, toSetValue);
                }
            }
        }

        assert isWellFormed();
    }

    /**
     * Aceasta metoda scrie in fisier .txt datele comenzilor plasate intr-un interval de ore
     * @pre start >= 0 && start < 24 && end >= 0 && end < 24 && start < end
     * @param start
     * @param end
     * @return
     */

    @Override
    public String report1(int start, int end) {  //generateTimeIntervalReport
        assert start >= 0 && start < 24 && end >= 0 && end < 24 && start < end;

        List<Order> selectedOrders = orders.stream()
                .filter(o -> o.getHour() >= start && o.getHour() < end)
                .collect(Collectors.toList());

        String content = "";
        content = content.concat("Time Interval Report\n");

        for (Order order : selectedOrders) {
            content = content.concat(order.toString());
        }

        FileWriter writer = new FileWriter();
        writer.writeInFile(content,"Raport1.txt");

        return content;
    }

    /**
     * Aceasta metoda scrie in fisier produsele comandate de mai multe ori decat numarul dat ca si parametru
     * @pre amount >= 0
     * @param amount
     * @return
     */

    @Override
    public String report2(int amount) {  //generateOrderedMoreReport
        assert amount >= 0;

        String msg = "";
        msg = msg.concat("Ordered More Than Report\n");

        for (MenuItem item : menu) {
            long count = placedOrders.entrySet()
                    .stream()
                    .filter(o -> o.getValue().contains(item))
                    .count();

            if (count >= amount)
                msg = msg.concat(item.toString());
        }

        FileWriter writer = new FileWriter();
        writer.writeInFile(msg,"Raport2.txt");

        return msg;
    }

    /**
     * Aceasta metoda scrie intr-un fisier numele clientilor care au comandat de mai multe ori decat numarul precizat ca si parametru
     * si acestea sunt de o suma mai mare ca si suma data ca si parametru
     * @param amount
     * @param times
     * @return
     */

    @Override
    public String report3(int amount, int times) {
        assert times >= 0 && amount >= 0;

        String msg = "";
        msg = msg.concat("Clients Ordered More Than Report\n");

        for (User user : users) {
            if (user instanceof Client) {
                long nrTimes = placedOrders.entrySet()
                        .stream()
                        .distinct()
                        .filter(o -> o.getKey().getClientName().equals(user.getName()))
                        .count();

                if (nrTimes >= times) {
                    List<Order> selectedOrders = orders.stream()
                            .filter(o -> o.getClientName().equals(user.getName()))
                            .collect(Collectors.toList());

                    double price = 0;
                    for (Order order : selectedOrders) {
                        List<MenuItem> menuItems = placedOrders.get(order);

                        for (MenuItem menuItem : menuItems) {
                            price += menuItem.computePrice();
                        }
                    }

                    if (price >= amount) {
                        msg = msg.concat(user.toString());
                    }
                }
            }
        }

        FileWriter writer = new FileWriter();
        writer.writeInFile(msg, "Raport3.txt");

        return msg;
    }

    /**
     * Aceasta metoda scrie intr-un fisier produsele care au fost comandate intr-o data precizata ca si parametru, impreuna cu
     * numarul de cate ori au fost comandate
     * @param day
     * @param month
     * @param year
     * @return
     */

    @Override
    public String report4(Integer day, Integer month, Integer year) {
        assert day >= 1 && day <= 31 && month >= 1 && month <= 12;

        HashSet<MenuItem> menuItems = (HashSet<MenuItem>) placedOrders.entrySet()
                .stream()
                .filter(p -> p.getKey().getDayOfMonth() == day
                        && p.getKey().getMonth() == (month - 1)
                        && p.getKey().getYear() == year)
                .flatMap(p -> p.getValue().stream())
                .collect(Collectors.toSet());

        String msg = "";
        msg = msg.concat("Ordered In Day Report\n");

        for (MenuItem item : menuItems) {
            long nbOfTimes = placedOrders.entrySet()
                    .stream()
                    .filter(p -> p.getValue().contains(item))
                    .count();

            msg = msg.concat(item.getTitle() + " " + nbOfTimes  + "\n");
        }

        FileWriter writer = new FileWriter();
        writer.writeInFile(msg, "Raport4.txt");

        return msg;
    }

    @Override
    public String searchByTitle(String title) {
        assert !title.equals("") : "Invalid title!";
        String str = "";
        str = menu.stream()
                .filter(t -> t.getTitle().contains(title))
                .map(MenuItem::toString)
                .collect(Collectors.joining("\n"));
        assert str != null;
        assert isWellFormed();
        return str;
    }

    @Override
    public String searchByRating(Double rating) {
        assert rating >= 0 : "Invalid rating!";
        String str = "";

        str = menu.stream()
                .filter(t -> t instanceof BaseProduct &&  (compareTwoDoubles(((BaseProduct) t).getRating(),rating)))
                .map(MenuItem::toString)
                .collect(Collectors.joining("\n"));

        assert str != null;
        assert isWellFormed();
        return str;
    }

    public boolean compareTwoDoubles(Double d1, Double d2) {
        return Math.floor(d1 * 10000) == Math.floor(d2 * 10000);
    }


    @Override
    public String searchByCalories(Double calories) {
        assert calories >= 0 : "Invalid calories!";
        String str = "";
        str = menu.stream()
                .filter(t -> t instanceof BaseProduct && (compareTwoDoubles(((BaseProduct) t).getCalories(),calories)))
                .map(MenuItem::toString)
                .collect(Collectors.joining("\n"));
        assert str != null;
        assert isWellFormed();
        return str;
    }

    @Override
    public String searchByProtein(Double protein) {
        assert protein >= 0 : "Invalid protein!";
        String str = "";
        str = menu.stream()
                .filter(t -> t instanceof BaseProduct && (compareTwoDoubles(((BaseProduct) t).getProtein(),protein)))
                .map(MenuItem::toString)
                .collect(Collectors.joining("\n"));
        assert str != null;
        assert isWellFormed();
        return str;
    }

    @Override
    public String searchByFat(Double fat) {
        assert fat >= 0 : "Invalid fat!";
        String str = "";
        str = menu.stream()
                .filter(t -> t instanceof BaseProduct && (compareTwoDoubles(((BaseProduct) t).getFat(),fat)))
                .map(MenuItem::toString)
                .collect(Collectors.joining("\n"));
        assert str != null;
        assert isWellFormed();
        return str;
    }

    @Override
    public String searchBySodium(Double sodium) {
        assert sodium >= 0 : "Invalid sodium!";
        String str = "";
        str = menu.stream()
                .filter(t -> t instanceof BaseProduct && (compareTwoDoubles(((BaseProduct) t).getSodium(),sodium)))
                .map(MenuItem::toString)
                .collect(Collectors.joining("\n"));
        assert str != null;
        assert isWellFormed();
        return str;
    }

    @Override
    public String searchByPrice(Double price) {
        assert price >= 0 : "Invalid price!";
        String str = "";
        str = menu.stream()
                .filter(t -> t instanceof BaseProduct && (compareTwoDoubles(((BaseProduct) t).getPrice(),price)))
                .map(MenuItem::toString)
                .collect(Collectors.joining("\n"));
        assert str != null;
        assert isWellFormed();
        return str;
    }

    @Override
    public void createOrder(String clientName, List<MenuItem> list) {
        Integer orderID = placedOrders.size() + 1;
        Order order = new Order(orderID, clientName);
        orders.add(order);
        placedOrders.put(order, list);

        Double totalPrice = 0.0;
        FileWriter fileWriter = new FileWriter();
        String msg = "BILL " + orderID + "\n";
        msg = msg + "Order date: " + order.getDate() + "\n";
        msg = msg + "Client name: " + clientName + "\n";
        msg = msg + "Order items: ";
        for(MenuItem item : list) {
            msg = msg + item.getTitle() + ",";
            totalPrice = totalPrice + item.computePrice();
        }
        msg = msg + "\n" + "Order price: " + totalPrice;
        fileWriter.writeInFile(msg,"filename.txt");

        //observer
        String employeeMessage = "COMANDA NOUA";
        setChanged();
        notifyObservers(employeeMessage);
    }

    public void displayOrders() {
        for (Map.Entry<Order, List<MenuItem>> entry : placedOrders.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

}

package vn.viettuts.qlsv.dao;

import vn.viettuts.qlsv.entity.User;
import vn.viettuts.qlsv.entity.XML.UserXML;
import vn.viettuts.qlsv.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private final String FILE_PATH ="src/main/resources/users.xml";
    private List<User> users;

    public UserDao(){
        users = readFromXML();
        if(users == null){
            users = new ArrayList<>();
        }

        System.out.println(users);
    }

    private List<User> readFromXML() {
        List<User> result = new ArrayList<>();
        UserXML userXML = (UserXML) FileUtils.readXMLFile(FILE_PATH, UserXML.class);

        if(userXML != null){
            result = userXML.getUsers();
        }

        return result;
    }

    private void writeToXML() {
        UserXML userXML = new UserXML();
        userXML.setUsers(users);
        FileUtils.writeXMLtoFile(FILE_PATH, userXML);
    }

    public boolean checkUser(User user){
        return users.stream().anyMatch(u -> u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword()));
    }
}

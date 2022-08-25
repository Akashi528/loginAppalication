package dao;

import data.User;

public interface loginInterface {
    public void save(User user);
    public void delete(User user);
    public User find(String userID);
    public void change(User user);
    public User getBack(String userID);
}

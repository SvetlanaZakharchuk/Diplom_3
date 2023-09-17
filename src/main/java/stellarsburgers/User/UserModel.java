//класс для пользователя, которго создаем через API
package stellarsburgers.User;

public class UserModel {
    private String email;
    private String password;
    private String name;

    public UserModel(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

}

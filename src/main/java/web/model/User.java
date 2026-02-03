package web.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "age")
    private Byte age;
    
    public User() {
    
    }
    
    public User(String name, String lastName, byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public Byte getAge() {
        return age;
    }
    
    public void setAge(Byte age) {
        this.age = age;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User[");
        sb.append("id=").append(id).append(", ");
        sb.append("name=").append(name).append(", ");
        sb.append("lastName=").append(lastName).append(", ");
        sb.append("age=").append(age);
        sb.append("]");
        return sb.toString();
    }
}

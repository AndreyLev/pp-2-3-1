package web.dto;

import javax.validation.constraints.NotNull;

public class AddUserRequestDto {
    
    @NotNull
    private String name;
    @NotNull
    private String lastName;
    @NotNull
    private Byte age;
    
    public AddUserRequestDto() {
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
        sb.append("AddUserRequestDto[");
        sb.append("name=").append(name).append(", ");
        sb.append("lastName=").append(lastName).append(", ");
        sb.append("age=").append(age);
        sb.append("]");
        return sb.toString();
    }
}

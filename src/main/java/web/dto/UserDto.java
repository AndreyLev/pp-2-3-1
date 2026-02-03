package web.dto;

import jakarta.validation.constraints.*;

public class UserDto {
    private Long id;
    
    @NotNull
    @NotBlank(message = "Имя обязательно")
    @Size(min = 2, max = 50)
    private String name;
    
    @NotNull
    @NotBlank(message = "Фамилия обязательна")
    @Size(min = 2, max = 50)
    private String lastName;
    
    @NotNull
    @Min(value = 18, message = "Возраст >= 18")
    @Max(value = 99, message = "Возраст <= 99")
    private Byte age;
    
    public UserDto(String name, String lastName, Byte age) {
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
        sb.append("UserDto[");
        sb.append("id=").append(id).append(", ");
        sb.append("name=").append(name).append(", ");
        sb.append("lastName=").append(lastName).append(", ");
        sb.append("age=").append(age);
        sb.append("]");
        return sb.toString();
    }
}

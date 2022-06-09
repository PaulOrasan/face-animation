package faceanimation.model;

import lombok.Getter;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
public class User extends ModelEntity<UUID> implements Serializable {
    private String name;
    private String surname;
    private String email;

    public User() {}

    public User(String name, String surname, String email) {
        super(UUID.randomUUID());
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public User(UUID id, String name, String surname, String email) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User person = (User) o;
        return email.equals(person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}

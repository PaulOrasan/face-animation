package faceanimation.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
public class AppUser extends ModelEntity<UUID> implements Serializable {
    private String name;
    private String surname;
    private String username;
    private String password;

    public AppUser() {}

    public AppUser(String name, String surname, String username) {
        super(UUID.randomUUID());
        this.name = name;
        this.surname = surname;
        this.username = username;
    }

    public AppUser(UUID id, String name, String surname, String username) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.username = username;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + username + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser person = (AppUser) o;
        return username.equals(person.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}

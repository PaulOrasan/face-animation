package faceanimation.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class ModelEntity<E> implements Serializable {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private E id;

    public E getId() {
        return id;
    }

    public void setId(E newId) {
        this.id = newId;
    }

    public ModelEntity(E id) {
        this.id = id;
    }

    public ModelEntity() {}
}

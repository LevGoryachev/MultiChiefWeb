package ru.goryachev.multichief.auth.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "perm_name")
    private String perm_name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPerm_name() {
        return perm_name;
    }

    public void setPerm_name(String perm_name) {
        this.perm_name = perm_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Permission)) return false;
        Permission that = (Permission) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getPerm_name(), that.getPerm_name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPerm_name());
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", perm_name='" + perm_name + '\'' +
                '}';
    }
}

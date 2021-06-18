package ru.goryachev.multichief.auth.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rank")
    private String rank;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return Objects.equals(getId(), role.getId()) &&
                Objects.equals(getRank(), role.getRank());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRank());
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", rank='" + rank + '\'' +
                '}';
    }
}

package top.yjzloveyzh.common.pojo;

import java.util.List;

public class Role {
    private int id;
    private String name;
    private List<Permission> permissions;

    public Role() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "Role [id=" + id + ", name=" + name + ", permissions=" + permissions + "]";
    }
}
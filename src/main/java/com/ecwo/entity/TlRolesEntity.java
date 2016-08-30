package com.ecwo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

/**
 * User: tor
 * Date: 28.03.12
 * Time: 18:45
 * Роль, а точнее привилегия минимальный кирпичек
 */

@javax.persistence.Table(name = "TLROLES", schema = "UNIVERSITYAUDIT")
@NamedQueries({
        @NamedQuery(name = TlRolesEntity.FIND_ALL_BY_NAME_Q, query = "SELECT p FROM TlRolesEntity as p WHERE p.name LIKE :pname"),
        @NamedQuery(name = TlRolesEntity.FIND_ALL, query = "SELECT p FROM TlRolesEntity as p ")
})
@Entity
public class TlRolesEntity {
    public static final String FIND_ALL_BY_NAME_Q = "findAllRolesLikeName";
    public static final String FIND_ALL = "findAllRoles";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_Roles")
    @SequenceGenerator(name = "gen_Roles", schema = "UNIVERSITYAUDIT", sequenceName = "TLROLES_SEQ")
    @javax.persistence.Column(name = "ROLEID")
    private Integer id;

    @NotNull(message = "{TlRolesEntity.name.notNull}")
    @Size(min = 1, max = 20, message = "{TlRolesEntity.name.size}")
    @Basic
    @javax.persistence.Column(name = "NAME", nullable = false, insertable = true, updatable = true, length = 20, precision = 0)
    private String name;
    @ManyToMany
    @JoinTable(schema = "UNIVERSITYAUDIT", name = "TLGROUPTOROLELINK",
            joinColumns = @JoinColumn(name = "ROLEID"),
            inverseJoinColumns = @JoinColumn(name = "GROUPID"))
    private Collection<TlGroupEntity> tlGroup;

    public Integer getId() {
        return id;
    }


    public void setId(Integer roleid) {
        this.id = roleid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<TlGroupEntity> getTlGroup() {
        return tlGroup;
    }

    public void setTlGroup(Collection<TlGroupEntity> tlGroup) {
        this.tlGroup = tlGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TlRolesEntity that = (TlRolesEntity) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    /*  @OneToMany(mappedBy = "tlrolesByRoleid")
    private Collection<TlgrouptorolelinkEntity> tlgrouptorolelinksByRoleid;

    public Collection<TlgrouptorolelinkEntity> getTlgrouptorolelinksByRoleid() {
        return tlgrouptorolelinksByRoleid;
    }

    public void setTlgrouptorolelinksByRoleid(Collection<TlgrouptorolelinkEntity> tlgrouptorolelinksByRoleid) {
        this.tlgrouptorolelinksByRoleid = tlgrouptorolelinksByRoleid;
    }*/
}

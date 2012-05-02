package com.ecwo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by IntelliJ IDEA.
 * User: tor
 * Date: 28.03.12
 * Time: 18:45
 * $Rev::               $:  Revision of last commit
 * $Author::            $:  Author of last commit
 * $Date::              $:  Date of last commit
 * Группа Ролей
 */
@javax.persistence.Table(name = "TLGROUP", schema = "UNIVERSITYAUDIT")
@NamedQueries(value = @NamedQuery(name = TlGroupEntity.FIND_ALL_GROUPS_BY_NAME_Q, query = "SELECT g FROM TlGroupEntity as g WHERE g.name like :name"))
@Entity
public class TlGroupEntity {

    public static final String FIND_ALL_GROUPS_BY_NAME_Q = "TlGroupEntity.findAllGroupsByName";
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_Group")
    @SequenceGenerator(name = "gen_Group", schema = "UNIVERSITYAUDIT", sequenceName = "TLGROUP_SEQ")
    @javax.persistence.Column(name = "GROUPID")
    @Id
    private Integer groupid;

    @NotNull(message = "{RoleGroup.name.notnull}")
    @Size(min = 1,max = 40,message ="{RoleGroup.name.size}" )
    @javax.persistence.Column(name = "NAME", nullable = false, insertable = true, updatable = true, length = 40, precision = 0)
    @Basic
    private String name;

    @ManyToMany(mappedBy = "tlGroup")
    private Collection<TlRolesEntity> roles;

    @ManyToMany(mappedBy = "roleGroups")
    private Collection<TeacherLoginEntity> teacherLogin;

    public Collection<TeacherLoginEntity> getTeacherLogin() {
        return teacherLogin;
    }

    public void setTeacherLogin(Collection<TeacherLoginEntity> teacherLogin) {
        this.teacherLogin = teacherLogin;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<TlRolesEntity> getRoles() {
        return roles;
    }

    public void setRoles(Collection<TlRolesEntity> roles) {
        this.roles = roles;
    }

    public void addRole(TlRolesEntity rolesEntity) {
        if (getRoles() == null) {
            roles = new HashSet<TlRolesEntity>();
        }
        getRoles().add(rolesEntity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TlGroupEntity that = (TlGroupEntity) o;

        if (groupid != null ? !groupid.equals(that.groupid) : that.groupid != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = groupid != null ? groupid.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}

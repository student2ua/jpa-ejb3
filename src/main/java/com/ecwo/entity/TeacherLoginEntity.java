package com.ecwo.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: tor
 * Date: 28.03.12
 * Time: 18:45
 * $Rev::               $:  Revision of last commit
 * $Author::            $:  Author of last commit
 * $Date::              $:  Date of last commit
 * Основная ячейка для логина пароля
 */
@Entity
@javax.persistence.Table(name = "TEACHERLOGIN", schema = "UNIVERSITYAUDIT")
@NamedQueries({
        @NamedQuery(name = TeacherLoginEntity.FIND_BY_LOGIN_Q, query = "from TeacherLoginEntity as t where t.login = :login"),
        @NamedQuery(name = TeacherLoginEntity.FIND_BY_HUMAN_Q, query = "from TeacherLoginEntity as t where t.humanid = :human")
}
)
public class TeacherLoginEntity {
    public static final String FIND_BY_LOGIN_Q = "findByLogin";
    public static final String FIND_BY_HUMAN_Q = "findByHuman";

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_USERS")
    @SequenceGenerator(name = "gen_USERS", schema = "UNIVERSITYAUDIT", sequenceName = "TEACHERLOGIN_SEQ")
    @javax.persistence.Column(name = "ID")
    @Id
    private Integer id;

    @javax.persistence.Column(name = "HUMANID", unique = true, nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    private Integer humanid;
    @NotNull(message = "{Login.notNull}")
    @Size(min = 1, max = 20, message = "{Login.size}")
    @javax.persistence.Column(name = "LOGIN", unique = true, nullable = false, length = 20, precision = 0)
    @Basic
    private String login;


    @javax.persistence.Column(name = "PASSWORD", nullable = false, insertable = true, updatable = true, length = 50, precision = 0)
    @Basic
    private String password;

    @ManyToMany
    @JoinTable(schema = "UNIVERSITYAUDIT", name = "TEACHERGROUP_TL_LINK",
            joinColumns = @JoinColumn(name = "TLID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "TGROUPID", referencedColumnName = "GROUPID"))
    private Collection<TlGroupEntity> roleGroups;

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHumanid() {
        return humanid;
    }

    public void setHumanid(Integer humanid) {
        this.humanid = humanid;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<TlGroupEntity> getRoleGroups() {
        return roleGroups;
    }

    public void setRoleGroups(Collection<TlGroupEntity> roleGroups) {
        this.roleGroups = roleGroups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeacherLoginEntity that = (TeacherLoginEntity) o;

        if (humanid != null ? !humanid.equals(that.humanid) : that.humanid != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (humanid != null ? humanid.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    /*  private Collection<TeachergroupTlLinkEntity> teachergroupTlLinksById;

     @OneToMany(mappedBy = "teacherloginByTlid")
    public Collection<TeachergroupTlLinkEntity> getTeachergroupTlLinksById() {
        return teachergroupTlLinksById;
    }

    public void setTeachergroupTlLinksById(Collection<TeachergroupTlLinkEntity> teachergroupTlLinksById) {
        this.teachergroupTlLinksById = teachergroupTlLinksById;
    }

    private Collection<TlgrouplinkEntity> tlgrouplinksById;

    @OneToMany(mappedBy = "teacherloginByTloginid")
    public Collection<TlgrouplinkEntity> getTlgrouplinksById() {
        return tlgrouplinksById;
    }

    public void setTlgrouplinksById(Collection<TlgrouplinkEntity> tlgrouplinksById) {
        this.tlgrouplinksById = tlgrouplinksById;
    }*/
}

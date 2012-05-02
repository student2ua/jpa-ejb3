package com.ecwo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: tor
 * Date: 28.03.12
 * Time: 18:45
 * $Rev::               $:  Revision of last commit
 * $Author::            $:  Author of last commit
 * $Date::              $:  Date of last commit
 * объединение учителей  (хуманов)
 */
@javax.persistence.Table(name = "TEACHERGROUP", schema = "UNIVERSITYAUDIT")
@Entity
public class TeacherGroupEntity {

    @javax.persistence.Column(name = "GROUPID", nullable = false, insertable = true, updatable = true, length = 22, precision = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_TeacherGroup")
    @SequenceGenerator(name = "gen_TeacherGroup", schema = "UNIVERSITYAUDIT", sequenceName = "TEACHERGROUP_SEQ")
    private Integer Id;

    @NotNull (message = "{HumanGroup.name.notNull}")
    @Size(min = 1,max = 40,message = "{HumanGroup.name.size}")
    @javax.persistence.Column(name = "NAME", nullable = false, insertable = true, updatable = true, length = 40, precision = 0)
    @Basic
    private String name;

    @ManyToMany
    @JoinTable(schema = "UNIVERSITYAUDIT", name = "TEACHERGROUP_TL_LINK",
            joinColumns = @JoinColumn(name = "TGROUPID", referencedColumnName = "GROUPID"),
            inverseJoinColumns = @JoinColumn(name = "TLID", referencedColumnName = "ID"))
    private Set<TeacherLoginEntity> teachers;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer groupid) {
        this.Id = groupid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<TeacherLoginEntity> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<TeacherLoginEntity> teachers) {
        this.teachers = teachers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeacherGroupEntity that = (TeacherGroupEntity) o;

        if (Id != null ? !Id.equals(that.Id) : that.Id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = Id != null ? Id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    /*  private Collection<TeachergroupTlLinkEntity> teachergroupTlLinksByGroupid;

    @OneToMany(mappedBy = "teachergroupByTgroupid")
    public Collection<TeachergroupTlLinkEntity> getTeachergroupTlLinksByGroupid() {
        return teachergroupTlLinksByGroupid;
    }

    public void setTeachergroupTlLinksByGroupid(Collection<TeachergroupTlLinkEntity> teachergroupTlLinksByGroupid) {
        this.teachergroupTlLinksByGroupid = teachergroupTlLinksByGroupid;
    }*/
}

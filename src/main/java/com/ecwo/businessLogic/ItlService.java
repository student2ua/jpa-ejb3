package com.ecwo.businessLogic;

import com.ecwo.businessLogic.errors.ValidationException;
import com.ecwo.entity.TeacherLoginEntity;
import com.ecwo.entity.TlGroupEntity;
import com.ecwo.entity.TlRolesEntity;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tor
 * Date: 12.04.12
 * Time: 14:03
 * $Rev::               $:  Revision of last commit
 * $Author::            $:  Author of last commit
 * $Date::              $:  Date of last commit
 * To change this template use File | Settings | File Templates.
 */
public interface ItlService {
    TeacherLoginEntity user_Create(String login, String rawPass);

    TeacherLoginEntity user_Create(TeacherLoginEntity entity);

    void user_Del(TeacherLoginEntity entity);

    void user_ChangePassword(TeacherLoginEntity entity, String rawPass) throws ValidationException;

    List<TeacherLoginEntity> user_findByLogin(String login);

    TlRolesEntity role_Create(String name) throws ValidationException;

    TlRolesEntity role_Create(TlRolesEntity entity) throws ValidationException;

    void role_Del(TlRolesEntity entity);

    TlRolesEntity role_reName(TlRolesEntity old, String newName) throws ValidationException;

    List<TlRolesEntity> role_findByName(String name);

    List<TlRolesEntity> role_List();

    TlGroupEntity roleGroup_Create(String name) throws ValidationException;

    TlGroupEntity roleGroup_Create(TlGroupEntity entity) throws ValidationException;

    void roleGroup_Del(TlGroupEntity entity);

    List<TlGroupEntity> roleGroup_findByName(String name);

    TlGroupEntity roleGroup_addRole(TlGroupEntity groupEntity, TlRolesEntity rolesEntity) throws ValidationException;
}

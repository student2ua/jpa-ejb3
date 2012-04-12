package com.ecwo.businessLogic;

import com.ecwo.businessLogic.errors.ValidationException;
import com.ecwo.entity.TeacherLoginEntity;
import com.ecwo.entity.TlGroupEntity;
import com.ecwo.entity.TlRolesEntity;
import com.ecwo.service.CrudServiceBean;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

import static com.ecwo.service.QueryParam.with;

/**
 * Created by IntelliJ IDEA.
 * User: tor
 * Date: 09.04.12
 * Time: 13:54
 * $Rev::               $:  Revision of last commit
 * $Author::            $:  Author of last commit
 * $Date::              $:  Date of last commit
 */
/* public List<TlRolesEntity> role_List() {
    TypedQuery<TlRolesEntity> findAllUsersQuery = em.createNamedQuery("info.galleria.domain.User.findAllUsers", TlRolesEntity.class);
    List<TlRolesEntity> resultList = findAllUsersQuery.getResultList();
    return resultList;
}*/
@Stateless
@EJB(name ="java:global/businessLogic/TLService" ,beanInterface = ItlService.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class TLService implements ItlService {
    @EJB
    CrudServiceBean service;

    @Override
    public TeacherLoginEntity user_Create(String login, String rawPass) {
        TeacherLoginEntity entity = new TeacherLoginEntity();
        entity.setLogin(login);
        //@todo add generate pass from rawpass
        entity.setPassword(rawPass);

        return this.user_Create(entity);
    }

    @Override
    public TeacherLoginEntity user_Create(TeacherLoginEntity entity) {
        return service.create(entity);
    }

    @Override
    public void user_Del(TeacherLoginEntity entity) {
        service.delete(TeacherLoginEntity.class, entity.getId());
    }

    @Override
    public void user_ChangePassword(TeacherLoginEntity entity, String rawPass) throws ValidationException {
        entity.setLogin(rawPass);
        validate(entity);
        service.update(entity);
    }

    @Override
    public List<TeacherLoginEntity> user_findByLogin(String login) {
        return (List<TeacherLoginEntity>) service.findWithNamedQuery(TeacherLoginEntity.FIND_BY_LOGIN_Q,
                with("login", login).parameters());

    }

    @Override
    public TlRolesEntity role_Create(String name) throws ValidationException {
        if (this.role_findByName(name).isEmpty()) {
            TlRolesEntity entity = new TlRolesEntity();
            entity.setName(name);
            return this.role_Create(entity);
        } else throw new IllegalArgumentException("Not unique");

    }

    @Override
    public TlRolesEntity role_Create(TlRolesEntity entity) throws ValidationException {
        validate(entity);
        return service.create(entity);
    }

    @Override
    public void role_Del(TlRolesEntity entity) {
        service.delete(TlRolesEntity.class, entity.getId());
    }

    @Override
    public TlRolesEntity role_reName(TlRolesEntity old, String newName) throws ValidationException {
        old.setName(newName);
        validate(old);
        return service.update(old);
    }

    @Override
    public List<TlRolesEntity> role_findByName(String name) {
        return (List<TlRolesEntity>) service.findWithNamedQuery(TlRolesEntity.FIND_ALL_BY_NAME_Q,
                with("name", name).parameters());
    }

    @Override
    public List<TlRolesEntity> role_List() {
        return service.findWithNamedQuery(TlRolesEntity.FIND_ALL);
    }

    @Override
    public TlGroupEntity roleGroup_Create(String name) throws ValidationException {
        if (this.roleGroup_findByName(name).isEmpty()) {
            TlGroupEntity entity = new TlGroupEntity();
            entity.setName(name);
            return this.roleGroup_Create(entity);
        } else throw new IllegalArgumentException("Not unique");

    }

    @Override
    public TlGroupEntity roleGroup_Create(TlGroupEntity entity) throws ValidationException {
        validate(entity);
        return service.create(entity);
    }

    @Override
    public void roleGroup_Del(TlGroupEntity entity) {
        service.delete(TlGroupEntity.class, entity.getGroupid());
    }

    @Override
    public List<TlGroupEntity> roleGroup_findByName(String name) {
        return (List<TlGroupEntity>) service.findWithNamedQuery(TlGroupEntity.FIND_ALL_GROUPS_BY_NAME_Q,
                with("name", name).parameters());
    }

    @Override
    public TlGroupEntity roleGroup_addRole(TlGroupEntity groupEntity, TlRolesEntity rolesEntity) throws ValidationException {
        validate(rolesEntity);
        if (!groupEntity.getRoles().contains(rolesEntity)) {
            groupEntity.addRole(rolesEntity);
        }
        return groupEntity;
    }

    private void validate(Object entity) throws ValidationException {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set violations = validator.validate(entity);
        if (violations.size() > 0) {
            throw new ValidationException(violations);
        }
    }

}

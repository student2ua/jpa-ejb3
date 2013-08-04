package com.ecwo.service;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: tor
 * Date: 29.03.12
 * Time: 17:16
 * $Rev::               $:  Revision of last commit
 * $Author::            $:  Author of last commit
 * $Date::              $:  Date of last commit
 * http://www.adam-bien.com/roller/abien/entry/generic_crud_service_aka_dao
 */
public interface CrudService {

    public <T> T create(T t);

    public <T> T find(@NotNull Class type, @NotNull Object id);

    public <T> T update(T t);

    public void delete(Class type, Object id);

    @NotNull
    public <T> List<T> findWithNamedQuery(String queryName);

    @NotNull
    public List findWithNamedQuery(String queryName, int resultLimit);

    @NotNull
    public List findWithNamedQuery(String namedQueryName, Map parameters);

    @NotNull
    public List findWithNamedQuery(String namedQueryName, Map parameters, int resultLimit);
    // List<BaseEntity> findByCriteria(BaseEntityCriteria b)
}

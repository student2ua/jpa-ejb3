package com.ecwo.service;

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

    public <T> T find(Class type, Object id);

    public <T> T update(T t);

    public void delete(Class type, Object id);

    public <T> List <T>  findWithNamedQuery(String queryName);

    public List findWithNamedQuery(String queryName, int resultLimit);

    public List findWithNamedQuery(String namedQueryName, Map parameters);

    public List findWithNamedQuery(String namedQueryName, Map parameters, int resultLimit);
   // List<BaseEntity> findByCriteria(BaseEntityCriteria b)
}

package com.ecwo.service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: tor
 * Date: 11.04.12
 * Time: 17:33
 * $Rev::               $:  Revision of last commit
 * $Author::            $:  Author of last commit
 * $Date::              $:  Date of last commit
 * <pre>
 * int size = this.crudServiceBean.findWithNamedQuery(Book.BY_NAME_AND_PAGES,
 * with("name",book.getName()).
 * and("pages", book.getNumberOfPages()).
 * parameters()).
 * size(); <pre/>
 */
public class QueryParam {
    private Map parameters = null;

    private QueryParam(String name, Object value) {
        this.parameters = new HashMap();
        this.parameters.put(name, value);
    }

    public static QueryParam with(String name, Object value) {
        return new QueryParam(name, value);
    }

    public QueryParam and(String name, Object value) {
        this.parameters.put(name, value);
        return this;
    }

    public Map parameters() {
        return this.parameters;
    }
}

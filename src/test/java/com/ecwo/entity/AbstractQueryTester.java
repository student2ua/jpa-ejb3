package com.ecwo.entity;

/**
 * Created by IntelliJ IDEA.
 * User: tor
 * Date: 22.04.13
 * Time: 19:28
 * $Rev::               $:  Revision of last commit
 * $Author::            $:  Author of last commit
 * $Date::              $:  Date of last commit
 * To change this template use File | Settings | File Templates.
 */

import javax.persistence.Query;

//import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
//import org.apache.commons.lang3.builder.ToStringStyle;
public abstract class AbstractQueryTester {

    protected static void populateParameters(Query query, String parameters) {
        for (String parameter : parameters.split(";")) {
            String parameterKey = parameter.split("-")[0];
            String parameterValue = parameter.split("-")[1];
            String parameterType = parameter.split("-")[2];

            query.setParameter(parameterKey, configureParameterValue(parameterValue, parameterType));
        }
    }

    private static Object configureParameterValue(String parameterValue, String parameterType) {
        if (parameterType.equalsIgnoreCase("integer")) {
            try {
                return Integer.parseInt(parameterValue);
            } catch (Exception e) {

                throw new IllegalArgumentException("Invalid parameter value as number: " + parameterValue);
            }
        }

        if (parameterType.equalsIgnoreCase("string")) {
            return parameterValue;
        }
        if (parameterType.equalsIgnoreCase("person")) {
            int personId;

            try {
                personId = Integer.valueOf(parameterValue);
            } catch (Exception e) {

                throw new IllegalArgumentException("Invalid parameter value as number: " + parameterValue);
            }

            return new TeacherLoginEntity(personId);
        }
        throw new IllegalArgumentException("Invalid parameter type: " + parameterType);
    }

    protected static void printResult(Object result) throws Exception {
        if (result == null) {
            System.out.print("NULL");
        } else if (result instanceof Object[]) {
            Object[] row = (Object[]) result;
            System.out.print("[");
            for (int i = 0; i < row.length; i++) {
                printResult(row[i]);
            }
            System.out.print("]");
        } else if (result instanceof Long || result instanceof Double || result instanceof String) {
            System.out.print(result.getClass().getName() + ": " + result);
        } else {
//            System.out.print(ReflectionToStringBuilder.toString(result, ToStringStyle.SHORT_PREFIX_STYLE));
        }
        System.out.println();
    }
}
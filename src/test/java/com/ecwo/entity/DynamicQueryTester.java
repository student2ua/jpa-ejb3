package com.ecwo.entity;

/**
 * Created by IntelliJ IDEA.
 * User: tor
 * Date: 22.04.13
 * Time: 19:44
 * $Rev::               $:  Revision of last commit
 * $Author::            $:  Author of last commit
 * $Date::              $:  Date of last commit
 * To change this template use File | Settings | File Templates.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class DynamicQueryTester extends AbstractQueryTester {
    public static void main(String[] args) throws IOException {
        CodeGenerator.startConnection();

        CodeGenerator.generateData();

        EntityManager em = CodeGenerator.getEntityManager();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (;;) {
            System.out.print("Type your JPQL and press Enter > ");
            String dynamicQuery = reader.readLine();

            if (dynamicQuery.equals("quit")) {
                break;
            }

            if (dynamicQuery.length() == 0) {
                continue;
            }

            System.out.println("Type the namedQuery parameters.");
            System.out.println("All paramters should be like: id-2-integer;name-John-string");
            System.out.println("Or just press enter for 0 parameters");
            String parameters = reader.readLine();

            try {
                Query query = em.createQuery(dynamicQuery);

                if (parameters.length() > 0) {
                    populateParameters(query, parameters);
                }

                @SuppressWarnings("rawtypes")
                List result = query.getResultList();

                if (result.size() > 0) {
                    int count = 0;
                    for (Object o : result) {
                        System.out.print(++count + " ");
                        printResult(o);
                    }
                } else {
                    System.out.println("0 results returned");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        CodeGenerator.closeConnection();
    }
}

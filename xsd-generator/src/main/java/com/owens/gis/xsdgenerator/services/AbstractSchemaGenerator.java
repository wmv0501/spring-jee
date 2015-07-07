package com.owens.gis.xsdgenerator.services;

import com.owens.gis.xsdgenerator.utils.CustomSchemaOutputResolver;
import com.owens.gis.xsdgenerator.utils.CustomSchemaStringOutputResolver;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.SchemaOutputResolver;
import java.io.FileInputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author wvergara, created on 6/30/15.
 */
public abstract class AbstractSchemaGenerator<T> implements SchemaGenerator<T> {

    private Class<T> type;

    protected Class<T> getType() {
        return type;
    }

    private String schema;

    public AbstractSchemaGenerator() {
    }

    @Override
    public void generateSchema() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];

        FileInputStream fis = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(getType());
            CustomSchemaOutputResolver sor = new CustomSchemaOutputResolver(getType().getSimpleName());
            sor.createOutput("",getType().getName());
            jaxbContext.generateSchema(sor);

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (fis != null)
                try {
                    fis.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
        }
    }

    public String getSchema() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];

        FileInputStream fis = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(getType());
            CustomSchemaStringOutputResolver sor = new CustomSchemaStringOutputResolver();
            sor.createOutput("",getType().getName());
            jaxbContext.generateSchema(sor);
             schema = sor.getSchema();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (fis != null)
                try {
                    fis.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
        }
        return schema;
    }


}

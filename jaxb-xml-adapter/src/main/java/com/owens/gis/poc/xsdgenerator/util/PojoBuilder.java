package com.owens.gis.poc.xsdgenerator.util;


import javassist.*;
import javassist.bytecode.ConstPool;

import java.util.Map;

/**
 * @author wvergara, created on 7/2/15.
 */
public class PojoBuilder {


    protected CtClass ctClass;
    protected Map<String, Class<?>> classVar;
    protected ClassPool clsPool;
    protected ConstPool constpool;

    public PojoBuilder(String className) throws NotFoundException {
        clsPool = ClassPool.getDefault();
        this.ctClass = clsPool.makeClass(className);
        constpool = ctClass.getClassFile().getConstPool();
    }

    public PojoBuilder withClassVariables(Map<String, Class<?>> classVar) {
        this.classVar = classVar;
        return this;
    }

    public PojoBuilder withClassVariable(String variableName, Class<?> type) {
        this.classVar.put(variableName, type);
        return this;
    }

    public PojoBuilder withClassVariable(CtField classVariable) throws CannotCompileException {
        this.ctClass.addField(classVariable);
        return this;
    }

    //TODO: replaced if there's a library to generate getter is available
    protected CtMethod generateGetter(CtClass declaringClass, String fieldName, Class fieldClass)
            throws CannotCompileException {

        String getterName = "get" + fieldName.substring(0, 1).toUpperCase()
                + fieldName.substring(1);

        StringBuffer sb = new StringBuffer();
        sb.append("public ").append(fieldClass.getName()).append(" ")
                .append(getterName).append("(){").append("return this.")
                .append(fieldName).append(";").append("}");
        return CtMethod.make(sb.toString(), declaringClass);
    }

    //TODO: replaced if there's a library to generate setter is available
    protected CtMethod generateSetter(CtClass declaringClass, String fieldName, Class fieldClass)
            throws CannotCompileException {

        String setterName = "set" + fieldName.substring(0, 1).toUpperCase()
                + fieldName.substring(1);

        StringBuffer sb = new StringBuffer();
        sb.append("public void ").append(setterName).append("(")
                .append(fieldClass.getName()).append(" ").append(fieldName)
                .append(")").append("{").append("this.").append(fieldName)
                .append("=").append(fieldName).append(";").append("}");
        return CtMethod.make(sb.toString(), declaringClass);
    }

    protected static CtClass resolveCtClass(Class clazz) throws NotFoundException {
        ClassPool pool = ClassPool.getDefault();
        return pool.get(clazz.getName());
    }

    public Class build() throws CannotCompileException, NotFoundException {
        for (Map.Entry<String, Class<?>> entry : classVar.entrySet()) {
            ctClass.addField(generateField(entry.getValue(), entry.getKey()));
            ctClass.addMethod(generateGetter(ctClass, entry.getKey(), entry.getValue()));
            ctClass.addMethod(generateSetter(ctClass, entry.getKey(), entry.getValue()));
        }
        return ctClass.toClass();
    }

    public CtField generateField(Class<?> type, String name) throws CannotCompileException, NotFoundException {
        return new CtField(resolveCtClass(type), name, ctClass);
    }

}


package com.owens.gis.poc.xsdgenerator.util;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.StringMemberValue;

/**
 * @author wvergara, created on 7/6/15.
 */
public class JaxbPojoBuilder extends PojoBuilder {

    public JaxbPojoBuilder(String className) throws NotFoundException {
        super(className);
    }

    @Override
    protected CtMethod generateGetter(CtClass declaringClass, String fieldName, Class fieldClass) throws CannotCompileException {
        CtMethod getterMethod = super.generateGetter(declaringClass, fieldName, fieldClass);
        addXmlElementAnnotation(getterMethod, fieldName);
        return getterMethod;
    }

    private void addXmlElementAnnotation(CtMethod getterMethod, String fieldName){
        AnnotationsAttribute attr = new AnnotationsAttribute(constpool, AnnotationsAttribute.visibleTag);
        Annotation annot = new Annotation("javax.xml.bind.annotation.XmlElement", constpool);
        annot.addMemberValue("name", new StringMemberValue(fieldName + "-name", ctClass.getClassFile().getConstPool()));
        attr.addAnnotation(annot);
        getterMethod.getMethodInfo().addAttribute(attr);
    }
}

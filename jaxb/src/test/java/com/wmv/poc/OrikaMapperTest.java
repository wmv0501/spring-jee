package com.wmv.poc;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrikaMapperTest {

    /**
     * @param args
     */
    public static void main(String[] args) {


        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(A.class, B.class).byDefault()
                .byDefault()
                .customize(new CustomMapper<A, B>() {
                    @Override
                    public void mapAtoB(A a, B b, MappingContext context) {

                        String typeStr = a.getType();
                        DataType dataType = DataType.byValue("string");
                        Class<?> aClass;
                        switch (dataType) {
                            case STRING:
                                aClass = String.class;
                                break;
                            case BOOLEAN:
                                aClass = Boolean.class;
                                break;
                            default:
                                aClass = String.class;
                        }

                        b.setClazz(aClass);

                    }
                }).register();

        A a = new A("address", "string");


        MapperFacade mapper = mapperFactory.getMapperFacade();
        B  b = mapper.map(a, B.class);

        System.out.println("b.getType().getName() = " + b.getClazz().getName());
    }


    public enum DataType {
        STRING("string"), BOOLEAN("boolean");

        private String value;

        private static Map<String, DataType> map = new HashMap<String, DataType>();

        static {
            for (DataType dataType : DataType.values()) {
                map.put(dataType.getValue(), dataType);
            }
        }

        DataType(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public static DataType byValue(String value) {
            return map.get(value);
        }
    }

    public static class A {
        String name;
        String type;

        public A() {
        }
        public A(String name, String type) {
            this.name = name;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class B {
        String name;
        Class<?> clazz;

        public B() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Class<?> getClazz() {
            return clazz;
        }

        public void setClazz(Class<?> clazz) {
            this.clazz = clazz;
        }
    }


    public static class Pair {
        public String theKey;
        public String theValue;
    }

    public static class Source {
        public List<Pair> pairs = new ArrayList<OrikaMapperTest.Pair>();

        public Source add(String key, String value) {
            final Pair e = new Pair();
            e.theKey = key;
            e.theValue = value;
            pairs.add(e);
            return this;

        }
    }

    public static class Destination {
        public Map<String, String> values;

        @Override
        public String toString() {
            return "Destination [values=" + values + "]";
        }

    }

}
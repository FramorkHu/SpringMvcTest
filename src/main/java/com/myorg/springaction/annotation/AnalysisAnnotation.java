package com.myorg.springaction.annotation;

import java.lang.reflect.Method;

/**
 * Created by huyan on 15/7/30.
 */
public class AnalysisAnnotation {

    public static void main(String[] args) {
        try {
            //通过运行时反射API获得annotation信息
            Class rt_class = Class.forName("com.myorg.springaction.annotation.Utility");
            Method[] methods = rt_class.getMethods();

            boolean flag = rt_class.isAnnotationPresent(Description.class);

            if(flag)
            {
                Description description = (Description)rt_class.getAnnotation(Description.class);
                System.out.println("Utility's Description--->"+description.value());
                for (Method method : methods) {
                    if(method.isAnnotationPresent(Author.class))
                    {
                        Author author = (Author)method.getAnnotation(Author.class);
                        System.out.println("Utility's Author--->"+author.name()+" from "+author.group());

                    }
                }
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

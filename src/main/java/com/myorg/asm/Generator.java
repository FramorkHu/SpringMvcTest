package com.myorg.asm;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by huyan on 2015/9/24.
 */
public class Generator {

    public static void main(String[] args) throws Exception{

        String className = "com.myorg.asm.Account";

        ClassReader classReader = new ClassReader(className);
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        ClassAdapter classAdapter = new AddSecurityCheckClassAdapter(classWriter);
        classReader.accept(classAdapter, ClassReader.SKIP_DEBUG);

        byte[] data = classWriter.toByteArray();


        File file = new File(getClassPath(className));
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(data);
        outputStream.close();

    }

    public static String getClassPath(String className){

        String allName = className.replace(".", File.separator);

        return "target/classes/"+allName+".class";

    }

}

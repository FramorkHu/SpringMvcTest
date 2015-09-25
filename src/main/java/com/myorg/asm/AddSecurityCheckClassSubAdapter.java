package com.myorg.asm;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

/**
 * Created by huyan on 2015/9/25.
 */
public class AddSecurityCheckClassSubAdapter extends ClassAdapter {

    private String enhancedClassName;

    public AddSecurityCheckClassSubAdapter(ClassVisitor classVisitor) {
        super(classVisitor);
    }


    @Override
    public void visit(final int version, final int access, final String name,
                      final String signature, final String superName,
                      final String[] interfaces) {

        enhancedClassName = name+"EnhancedNameByAsm";

        super.visit(version, access, enhancedClassName, signature,
                name, interfaces);

    }

    @Override
    public MethodVisitor visitMethod(final int access, final String name,
                                     final String desc, final String signature, final String[] exceptions) {
        MethodVisitor methodVisitor = cv.visitMethod(access, name, desc, signature, exceptions);
        MethodVisitor wrappedMv = methodVisitor;
        if (methodVisitor != null){
            if (name.equals("operate")){
                wrappedMv = new AddSecurityCheckMethodAdapter(methodVisitor);
            } else if ("<init>".equals(name)){
                wrappedMv = new ChangeToChildConstructorMethodAdapter(methodVisitor, enhancedClassName);
            }
        }
        return wrappedMv;
    }
}

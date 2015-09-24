package com.myorg.asm;

import aj.org.objectweb.asm.ClassWriter;
import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

/**
 * Created by huyan on 2015/9/24.
 */
public class AddSecurityCheckClassAdapter extends ClassAdapter {

    public AddSecurityCheckClassAdapter(ClassVisitor classVisitor) {
        super(classVisitor);
    }


    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature,exceptions);
        MethodVisitor wrappedMv = mv;
        if (mv != null) {
            // 对于 "operation" 方法
            if (name.equals("operate")) {
                // 使用自定义 MethodVisitor，实际改写方法内容
                wrappedMv = new AddSecurityCheckMethodAdapter(mv);
            }
        }
        return wrappedMv;
    }
}

package com.myorg.asm;

import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Created by huyan on 2015/9/25.
 */
public class ChangeToChildConstructorMethodAdapter extends MethodAdapter {
    private String superClassName;

    public ChangeToChildConstructorMethodAdapter(MethodVisitor methodVisitor, String superClassName) {
        super(methodVisitor);
        this.superClassName = superClassName;
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name,
                                String desc) {

        if (opcode == Opcodes.INVOKESPECIAL && "<init>".equals(name)){
            owner = superClassName;
        }
        super.visitMethodInsn(opcode, owner, name, desc);
    }
}

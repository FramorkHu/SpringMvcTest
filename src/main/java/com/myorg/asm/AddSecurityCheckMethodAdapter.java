package com.myorg.asm;

import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Created by huyan on 2015/9/24.
 */
public class AddSecurityCheckMethodAdapter extends MethodAdapter {
    public AddSecurityCheckMethodAdapter(MethodVisitor methodVisitor) {
        super(methodVisitor);
    }

    @Override
    public void visitCode() {
        //需要写路径名称
        //写入包名无法识别类 com.myorg.asm.SecurityChecker
        visitMethodInsn(Opcodes.INVOKESTATIC,"com/myorg/asm/SecurityChecker","check","()V");
    }

}

package com.myorg.asm;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

/**
 * Created by huyan on 2015/9/25.
 */
public class SecureAccountGenerator {


    private static AccountGeneratorClassLoader classLoader =
            new AccountGeneratorClassLoader();

    private static Class secureAccountClass;

    public Account generateSecureAccount() throws Exception {
        if (null == secureAccountClass) {
            ClassReader cr = new ClassReader("com/myorg/asm/Account");
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            ClassAdapter classAdapter = new AddSecurityCheckClassSubAdapter(cw);
            cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
            byte[] data = cw.toByteArray();
            secureAccountClass = classLoader.defineClassFromClassFile(
                    "com.myorg.asm.AccountEnhancedNameByAsm",data);
        }
        return (Account) secureAccountClass.newInstance();
    }

    private static class AccountGeneratorClassLoader extends ClassLoader {
        public Class defineClassFromClassFile(String className,
                                              byte[] classFile) throws ClassFormatError {
            return defineClass(className , classFile, 0, classFile.length);
        }
    }

    public static void main(String args[]) throws Exception{
        Account account =
                new SecureAccountGenerator().generateSecureAccount();
        account.operate();
    }

}

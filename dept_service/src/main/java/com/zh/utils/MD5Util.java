package com.zh.utils;

import com.zh.entity.SysUser;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class MD5Util {
    private static String algorithmName = "md5";
    private static int hashIterations = 10;

    public static void encryptPassword(SysUser user) {
        String newPassword = new SimpleHash(algorithmName, user.getPassword(),
                ByteSource.Util.bytes(user.getName()), hashIterations).toHex();
        user.setPassword(newPassword);

    }
    public static void main(String[] args) {
        MD5Util passwordHelper = new MD5Util();
        SysUser user = new SysUser();
        user.setName("ADMIN");
        user.setPassword("ADMIN");
        passwordHelper.encryptPassword(user);
        System.out.println(user);
    }
}

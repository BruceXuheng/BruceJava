package com.bruce.chen.part3.bitwise;

/**
 * 位运算的运用-权限控制,add,query,modify,del
 */
public class Permission {

    private static final int ALLOW_SELECT = 1 << 0;
    private static final int ALLOW_INSERT = 1 << 1;
    private static final int ALLOW_UPDATE = 1 << 2;
    private static final int ALLOW_DELETER = 1 << 3;

    //当前的权限状态
    private int flag;

    //设置权限
    public void setPermission(int permission) {
        flag = permission;
    }

    /*增加权限,可以一项或者多项*/
    public void addPermission(int permssion) {
        flag = flag | permssion;
    }

    /*删除权限 有某些权限*/
    /*1&~0   1*/
    /*1&~1   0*/
    /*0&~0   0*/
    /*0&~1   0*/
    public void disablePermission(int permission) {
        flag = flag & ~permission;
    }

    /*是否拥有某些权限*/
    public boolean isAllow(int permission) {
        return (flag & permission) == permission;
    }

    /*是否不拥有某些权限*/
    public boolean isNotAllow(int permission){
        return (flag&permission)==0;
    }

    public static void main(String[] args) {
        int flag = 15;
        Permission permission = new Permission();
        permission.setPermission(flag);
        permission.disablePermission(ALLOW_DELETER|ALLOW_INSERT);
        System.out.println(permission.isAllow(ALLOW_SELECT));
        System.out.println(permission.isAllow(ALLOW_INSERT));
        System.out.println(permission.isAllow(ALLOW_DELETER));
        System.out.println(permission.isAllow(ALLOW_UPDATE));
        System.out.println(ALLOW_SELECT);
        System.out.println(Integer.toBinaryString(15));

    }

}

package com.bruce.chen.part3.safeclass;


/**
 * 类说明：委托给线程安全的类来做
 */
public class SafePublicFinalUser {

    private final SyncFinalUser user;

    public SafePublicFinalUser(FinalUserVo user) {
        this.user = new SyncFinalUser(user);
    }

    public static class SyncFinalUser {
        private final FinalUserVo userVo;
        private Object lock = new Object();

        public SyncFinalUser(FinalUserVo userVo) {
            this.userVo = userVo;
        }

        public int getAge() {
            synchronized (lock) {
                return userVo.getAge();
            }
        }

        public void setAge(int age) {
            synchronized (lock) {
                userVo.setAge(age);
            }
        }
    }

}

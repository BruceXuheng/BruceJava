package com.bruce.chen.part3.safeclass;


/**
 * 仿Collections对容器的包装，将内部成员对象进行线程安全包装
 */
public class SafePublicUser {

    private final UserVo userVo;

    public UserVo getUserVo() {
        return userVo;
    }

    public SafePublicUser(UserVo userVo) {
        this.userVo = new SynUser(userVo);
    }
    private static class SynUser extends UserVo{
        private final UserVo userVo;
        private final Object lock = new Object();

        public SynUser(UserVo userVo) {
            this.userVo = userVo;
        }

        @Override
        public int getAge() {
            synchronized (lock){
                return userVo.getAge();
            }
        }

        @Override
        public void setAge(int age) {
            synchronized (lock){
                userVo.setAge(age);
            }
        }
    }


}

package biz;

/**
 * Created by cwh on 2018/9/6.
 */

public interface IUserBiz {
    public void login(String username,String password,OnLoginListener loginListener);
}

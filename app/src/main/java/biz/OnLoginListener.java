package biz;

import bean.User;

/**
 * Created by cwh on 2018/9/6.
 */

public interface OnLoginListener {
    void loginSuccess(User user);
    void loginFailed();
}

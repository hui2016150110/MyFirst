package mvp_view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.cwh.mypermission.R;
import com.example.cwh.mypermission.broadcastpaBestPractice.BaseActivity;
import com.example.cwh.mypermission.broadcastpaBestPractice.ShowMainActivity;

import bean.User;
import mvp_presenter.UserLoginPresenter;

/**
 * Created by cwh on 2018/9/6.
 */

public class UserLoginAcivity extends BaseActivity implements IUserLoginView {

    private EditText mEtUsername,mEtPassword;
    private Button mBtnLogin,mBtnClear;
    private ProgressBar mPbLoading;
    private UserLoginPresenter mUserLoginPresenter = new UserLoginPresenter(this);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acivity_user_login);
        initViews();
    }

    private void initViews(){
        mBtnClear =(Button) findViewById(R.id.clear_button);
        mBtnLogin = (Button) findViewById(R.id.login_button);
        mEtPassword = (EditText) findViewById(R.id.password_editText);
        mEtUsername = (EditText)findViewById(R.id.username_editText);
        mPbLoading = (ProgressBar)findViewById(R.id.progressBar);
        mBtnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mUserLoginPresenter.login();
            }
        });
        mBtnClear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mUserLoginPresenter.clear();
            }
        });

    }

    @Override
    public String getUserName() {
        return mEtUsername.getText().toString();
    }

    @Override
    public String getPassword() {
        return mEtPassword.getText().toString();
    }

    @Override
    public void clearUserName() {
        mEtUsername.setText("");
    }

    @Override
    public void clearPassword() {
        mEtPassword.setText("");
    }

    @Override
    public void showLoading() {
        mPbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mPbLoading.setVisibility(View.GONE);
    }

    @Override
    public void toMainActivity(User user) {
        Toast.makeText(this, user.getUsername() +
                " login success , to MainActivity", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this,
                "login failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startNewActivity() {
        Intent intent = new Intent(this, ShowMainActivity.class);
        startActivity(intent);
    }
}

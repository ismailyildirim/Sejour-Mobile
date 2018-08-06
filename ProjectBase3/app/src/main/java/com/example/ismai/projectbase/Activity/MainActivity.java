package com.example.ismai.projectbase.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.example.ismai.projectbase.R;

public class MainActivity extends AppCompatActivity {
    EditText txt_userName, txt_password;
    Button btn_login, btn_userName;
    Switch remember;
    static ProgressDialog loadingBar;
    String username, password;
    Context context = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        tanimla();
        islev();
    }

    public void tanimla() {
        loadingBar = new ProgressDialog(this);
        txt_userName = findViewById(R.id.txt_username);
        txt_password = findViewById(R.id.txt_password);
        btn_userName = findViewById(R.id.btn_username);
        btn_login = findViewById(R.id.btn_login);
        remember = findViewById(R.id.remember);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String savedUserName = sharedPref.getString("userName", "");
        String savedPassword = sharedPref.getString("passWord", "");
        Boolean savedRemember = sharedPref.getBoolean("isChecked", false);
        remember.setChecked(savedRemember);
        txt_userName.setText(savedUserName);
        txt_password.setText(savedPassword);

    }

    public void islev() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setProgressDialog();
                hatirla();
                UtilsRetrofit.getOurInstance().istek(username, password, context);


            }
        });
        btn_userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_userName.setText("");
            }
        });
    }

    public void hatirla() {
        username = txt_userName.getText().toString();
        password = txt_password.getText().toString();
        Boolean isChecked = remember.isChecked();

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        if (remember.isChecked()) {
            editor.putString("userName", username);
            editor.putString("passWord", password);
            editor.putBoolean("isChecked", isChecked);

        } else {
            editor.remove("userName");
            editor.remove("passWord");
            editor.remove("isChecked");
        }

        editor.commit();
    }

    public void setProgressDialog() {
        loadingBar.setTitle("Hosgeldiniz");
        loadingBar.setMessage("Giris yapılıyor...");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();
    }


}

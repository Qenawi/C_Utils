package com.panda.cvsandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends AppCompatActivity
{
    @BindView(R.id.main)
    TextView main;
    private CService cService;
    HashMap<String, String> Headers = new HashMap<>();
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tst);
        ButterKnife.bind(this);
        cService = new CService(this);
        //getNatonalites();
        Headers.put("Accept-Language", getResources().getString(R.string.AcceptLanguage));
        Login();
    }
    private void Login()
    {
        /*
        HashMap<String,String>parameters=new HashMap<String, String>();
        parameters.put("email","test.com");
        parameters.put("password","123");
        parameters.put("token_mobile_push","aaaaaa");
        parameters.put("device_type","android");
        cService.SendData(new login_response(), Headers, "login", parameters, new CService.CsCallBack()
        {
            @Override
            public <T> void Sucess(T Resposne)
            {
                login_response login_response=(login_response)Resposne;


                Contstants.Token ="Bearer "+login_response.getData().getToken();
                Shared_prefs.save_id(login_response.getData().getUser().getUserId(), TestActivity.this);
                Shared_prefs.save_Loged("in", TestActivity.this);
                String Json = Convertrs.login_data_to_ST(login_response.getData().getUser());
                Shared_prefs.saveProfileJson(TestActivity.this, Json);
                Headers.put("Authorization", Contstants.Token);

                main.setText(" "+"\n");
                main.append(login_response.getData().getUser().getFirstName()+"\t"+login_response.getData().getUser().getMobileNumber()+
                        login_response.getData().getUser().getUserAddress()+"\t"+login_response.getData().getUser().getLastName()+"\t"+login_response.getData().getUser().getUserType()+"\n");
                main.append("---------------------------------------------------- \n");
                getCountries();


            }
            @Override
            public void Faild(Throwable t)
            {
                Log.v("faild", t.getMessage());
                getCountries();

            }
        });
        */
    }

    @Override
    protected void onPause() {
        super.onPause();
        cService.Detach();
    }
}

package com.panda.cvsandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.panda.cvsandroid.C_Service.CService;
import com.panda.cvsandroid.models.MovieResponse;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends AppCompatActivity
{
    @BindView(R.id.main)
    TextView main;
    private CService cService;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tst);
        ButterKnife.bind(this);
        cService = new CService(this);
        get_Dummy_data_movies1();
    }
    private void Test()
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
    private void get_Dummy_data_movies1()
    {
        HashMap<String, String> Headers = new HashMap<>();
        HashMap<String, String> Params = new HashMap<>();
        Params.put("api_key",Contstants.TMDP_API_KEY);
        String Url=Contstants.Movies_BASE_URL+Contstants.PopularURL;
        cService.FetchData(new MovieResponse(), Headers, Url, Params, new CService.CsCallBack()
        {
            @Override
            public <T> void Sucess(T Resposne)
            {
                MovieResponse e= (MovieResponse) Resposne;
                main.append("\n");
                main.append("-----------------------------------------------------------------");
                main.append("\n");
                main.append("Sucess");
                for (int i =0; i< e.getMovies().size();i++)
                {
                    main.append(e.getMovies().get(i).getTitle()+"\t"+e.getMovies().get(i)
                    .getOverview()
                    );
                 main.append("\n");
                }
                get_Dummy_data_movies2();
            }

            @Override
            public void Faild(Throwable t)
            {
                Log.v("faildHeHe", t.getMessage());

            }
        });
    }
    private void get_Dummy_data_movies2()
    {
        HashMap<String, String> Headers = new HashMap<>();
        HashMap<String, String> Params = new HashMap<>();
        String Url=Contstants.Movies_BASE_URL+Contstants.Top_RatedURL;
        cService.FetchData(new MovieResponse(), Headers, Url, Params, new CService.CsCallBack()
        {
            @Override
            public <T> void Sucess(T Resposne)
            {
                main.append("\n");
                main.append("-----------------------------------------------------------------");
                main.append("\n");
                MovieResponse e= (MovieResponse) Resposne;
                main.append("\n");
                main.append("Sucess");
                for (int i =0; i< e.getMovies().size();i++)
                {
                    main.append(e.getMovies().get(i).getTitle()+"\t"+e.getMovies().get(i)
                            .getOverview()
                    );
                    main.append("\n");
                }
            }

            @Override
            public void Faild(Throwable t)
            {
                Log.v("faildHeHe", t.getMessage());

            }
        });
    }
    @Override
    protected void onPause()
    {
        super.onPause();
    }
}

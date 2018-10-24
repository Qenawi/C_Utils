package com.panda.cvsandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.panda.cvsandroid.network.models.login.login_response;
import com.panda.cvsandroid.network.models.register.countries_response;
import com.panda.cvsandroid.network.models.register.nationality_response;
import com.panda.cvsandroid.network.models.search_result.Search_Result;
import com.panda.cvsandroid.network.models.skillsAndJops.skillsAndjopsreponse;

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
    }
    public void getNatonalites()
    {
        cService.<nationality_response>FetchData(new nationality_response(), Headers, "nationalities", new HashMap<>(), new CService.CsCallBack() {
                    @Override
                    public <T> void Sucess(T Resposne) {
                        nationality_response item = (nationality_response) Resposne;
                        for (int i = 0; i < item.getData().size(); i++) {
                            main.append("\t");
                            main.append(item.getData().get(i).getNationalityName() + " / y " + item.getData().get(i).getNationalityId());
                            main.append(" "+"\n");

                        }
                        main.append("---------------------------------------------------- \n");
                        getjops();
                    }



            @Override
                    public void Faild(Throwable t) {
                        Log.v("faild", t.getLocalizedMessage());
                    }
                }


        );

    }
    public void getCountries()
    {
        cService.<countries_response>FetchData(new countries_response(), Headers, "countries", new HashMap<>(), new CService.CsCallBack() {
                    @Override
                    public <T> void Sucess(T Resposne) {

                        countries_response item = (countries_response) Resposne;
                        for (int i = 0; i < item.getData().size(); i++)
                        {
                            main.append("\t");
                            main.append(item.getData().get(i).getCountryName() + " / y " + item.getData().get(i).getCountryId());
                            main.append(" "+"\n");

                        }
                        main.append("---------------------------------------------------- \n");
                        getNatonalites();
                    }

                    @Override
                    public void Faild(Throwable t) {
                        Log.v("faild", t.getLocalizedMessage());

                    }
                }


        );

    }
    private void getjops()
    {
        String url="skills";

        cService.<skillsAndjopsreponse>FetchData(new skillsAndjopsreponse(), Headers, url, new HashMap<>(), new CService.CsCallBack() {
                    @Override
                    public <T> void Sucess(T Resposne) {

                        skillsAndjopsreponse item = (skillsAndjopsreponse) Resposne;
                        for (int i = 0; i < item.getData().size(); i++)
                        {
                            main.append("\t");
                            main.append(item.getData().get(i).getSkillName()+" "+item.getData().get(i).getSkillId());
                            main.append("\n");
                            for (int j=0;j<item.getData().get(i).getJopitems().size();j++)
                            {
                                main.append(item.getData().get(i).getJopitems().get(j).getJobName()+"  ?  "+item.getData().get(i).getJopitems().get(j).getJobId());
                            }



                        }
                        main.append("---------------------------------------------------- \n");
getSearch();
                    }

                    @Override
                    public void Faild(Throwable t) {
                        Log.v("faild", t.getLocalizedMessage());

                    }
                }


        );


    }

    private void getSearch()
    {
        String url="employees";
        HashMap<String,String>parameters=new HashMap<String, String>();
        parameters.put("skill_id","0");
        parameters.put("job_id","0");
        parameters.put("country_id","0");
        parameters.put("nationality_id","0");
        parameters.put("job_type","0");
        cService.<Search_Result>FetchData(new Search_Result(), Headers, url,parameters, new CService.CsCallBack()
        {
            @Override
            public <T> void Sucess(T Resposne)
            {

                Search_Result item = (Search_Result) Resposne;
                for (int i = 0; i < item.getData().size(); i++)
                {
                    main.append(item.getData().get(i).getEmployeeName()+" \t "+item.getData().get(i).getEmployeeBio()+"\n"+item.getData().get(i).getProfilePath());
                    main.append("\n");
                }
                main.append("---------------------------------------------------- \n");
            }

            @Override
            public void Faild(Throwable t)
            {
                Log.v("faild", t.getMessage());

            }});

    }

    @Override
    protected void onPause() {
        super.onPause();
        cService.Detach();
    }
}

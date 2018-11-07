package com.panda.cvsandroid.mvvm_and_databinding.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.panda.cvsandroid.C_Service.CService;
import com.panda.cvsandroid.Contstants;
import com.panda.cvsandroid.R;
import com.panda.cvsandroid.databinding.ActivityMainBinding;
import com.panda.cvsandroid.models.Movie;
import com.panda.cvsandroid.models.MovieResponse;
import com.panda.cvsandroid.mvvm_and_databinding.viewmodel.DataViewModel;
import com.panda.cvsandroid.mvvm_and_databinding.viewmodel.View_model;

import java.util.ArrayList;
import java.util.HashMap;

import static android.widget.LinearLayout.VERTICAL;

public class MainActivity extends AppCompatActivity {

    private DataViewModel dataViewModel;
    private CService cService;
    private View_model view_model;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        View view = bind();
        cService = new CService(this);
        view_model= ViewModelProviders.of(this).get(View_model.class);
        initRecyclerView(view);
        if (view_model.getData()==null||view_model.getData().isEmpty())
        get_Dummy_data_movies1();
        else
         {
          dataViewModel.setUp((ArrayList<Movie>) view_model.getData());
         }
    }

    private void get_Dummy_data_movies1() {
        dataViewModel.setLockLayout(true);
        HashMap<String, String> Headers = new HashMap<>();
        HashMap<String, String> Params = new HashMap<>();
        Params.put("api_key", Contstants.TMDP_API_KEY);
        String Url = Contstants.Movies_BASE_URL + Contstants.PopularURL;
        cService.FetchData(new MovieResponse(), Headers, Url, Params, new CService.CsCallBack() {
            @Override
            public <T> void Sucess(T Resposne)
            {
                dataViewModel.setLockLayout(false);
                view_model.setData(((MovieResponse)Resposne).getMovies());
                dataViewModel.setUp((ArrayList<Movie>) view_model.getData());
            }

            @Override
            public void Faild(Throwable t) {
                Log.v("faildHeHe", t.getMessage());

            }
        });
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        dataViewModel.setUp(null);
    }
    @Override
    protected void onPause() {
        super.onPause();
        dataViewModel.tearDown();
    }

    private View bind() {
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        dataViewModel = new DataViewModel();
        binding.setViewModel(dataViewModel);
        return binding.getRoot();
    }

    private void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.data_recycler_view);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), VERTICAL));
    }
}
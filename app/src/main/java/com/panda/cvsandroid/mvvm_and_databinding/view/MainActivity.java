package com.panda.cvsandroid.mvvm_and_databinding.view;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.panda.cvsandroid.R;
import com.panda.cvsandroid.WorkManger.CompressWorker;
import com.panda.cvsandroid.databinding.ActivityMainBinding;
import com.panda.cvsandroid.mvvm_and_databinding.viewmodel.DataViewModel;
import com.panda.cvsandroid.mvvm_and_databinding.viewmodel.View_model;
import com.panda.cvsandroid.network.models.MovieResponse;
import com.panda.cvsandroid.network.models.Moviex;
import com.panda.cvsandroid.utils.Contstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import static android.widget.LinearLayout.VERTICAL;

@SuppressLint("RestrictedApi")
public class MainActivity extends AppCompatActivity  {

    private DataViewModel dataViewModel;
    private CService cService;
    private View_model view_model;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        View view = bind();
        textView=view.findViewById(R.id.TextView);
        cService = new CService(this);
        view_model = ViewModelProviders.of(this).get(View_model.class);
        initRecyclerView(view);

            Constraints c = new Constraints.Builder().setRequiresCharging(true).setRequiredNetworkType(NetworkType.CONNECTED).build();
            PeriodicWorkRequest compressionWork =
                    new PeriodicWorkRequest.Builder(CompressWorker.class, 20, TimeUnit.SECONDS)
                            .setConstraints(c).build();
        UUID uuid=compressionWork.getId();
        Log.v("QEnawi",compressionWork.getStringId());
        WorkManager.getInstance().enqueueUniquePeriodicWork("MrQenawi200",ExistingPeriodicWorkPolicy.REPLACE,compressionWork);
        WorkManager.getInstance().getWorkInfoByIdLiveData(compressionWork.getId()).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(@Nullable WorkInfo workInfo)
            {
                if (workInfo!=null)
                {
                    textView.append(workInfo.getState().name()+'\n'+workInfo.getState().toString()+'\n');

                }
            }
        });
    }

    private void get_Dummy_data_movies1() {
        dataViewModel.setLockLayout(true);
        HashMap<String, String> Headers = new HashMap<>();
        HashMap<String, String> Params = new HashMap<>();
        Params.put("api_key", Contstants.TMDP_API_KEY);
        String Url = Contstants.Movies_BASE_URL + Contstants.PopularURL;
        cService.FetchData(new MovieResponse(), Headers, Url, Params, new CService.CsCallBack() {
            @Override
            public <T> void Sucess(T Resposne) {
                dataViewModel.setLockLayout(false);
                view_model.setData(((MovieResponse) Resposne).getMoviexs());
                dataViewModel.setUp((ArrayList<Moviex>) view_model.getData(), (ArrayList<String>) view_model.getData2());
            }

            @Override
            public void Faild(Throwable t) {
                Log.v("faildHeHe", t.getMessage());

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        dataViewModel.setUp(null, null);
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
        RecyclerView recyclerView2 = view.findViewById(R.id.data_recycler_view2);

        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), VERTICAL));
        recyclerView2.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), VERTICAL));

    }


}
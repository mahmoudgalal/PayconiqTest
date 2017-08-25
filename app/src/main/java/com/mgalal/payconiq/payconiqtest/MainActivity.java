package com.mgalal.payconiq.payconiqtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mgalal.payconiq.payconiqtest.adapters.ReposAdapter;
import com.mgalal.payconiq.payconiqtest.model.Repo;
import com.mgalal.payconiq.payconiqtest.viewmodel.BaseViewModel;
import com.mgalal.payconiq.payconiqtest.viewmodel.OnDataLoadedListener;
import com.mgalal.payconiq.payconiqtest.viewmodel.ReposViewModel;

import java.util.List;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements OnDataLoadedListener<Repo> {

    private static final String TAG = MainActivity.class.getName();
    private static final int BOTTOM_THRESHOLD = 3;
    private RecyclerView reposRecycler;
    private TextView loadDataTxt;
    private LinearLayout loadContainer;
    private ReposViewModel viewModel;
    private ReposAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    void init(){
        //initializing realm
        Realm.init(this);
        reposRecycler = (RecyclerView) findViewById(R.id.repos_recycler_view);
        loadContainer = (LinearLayout) findViewById(R.id.loader_container);
        loadDataTxt = (TextView) findViewById(R.id.load_data_txt);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        reposRecycler.setHasFixedSize(true);
        reposRecycler.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                layoutManager.getOrientation());
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider));
        reposRecycler.addItemDecoration(dividerItemDecoration);

        viewModel = new ReposViewModel(this,this);
        viewModel.onStart();
        adapter = new ReposAdapter(this,viewModel.getData());
        reposRecycler.setAdapter(adapter);
        requestNextPage();
        loadDataTxt.setVisibility(View.VISIBLE);
        reposRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int firstVisibleItemPosition;
            private int lastVisibleItemPosition;
            private boolean scrollDown = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();

                if (visibleItemCount > 0 && newState == RecyclerView.SCROLL_STATE_IDLE) {

                    if (firstVisibleItemPosition <= 0) {//up scroll edge

                    }
                    /**
                     * if scrolling down and reached the third from bottom ,fire a next page request
                     */
                    if (scrollDown && firstVisibleItemPosition+visibleItemCount+BOTTOM_THRESHOLD -1
                            >= totalItemCount) {
                        Log.d(TAG,String.format("lastVisibleItemPosition=%d  vs count:%d vs first:%d",
                                lastVisibleItemPosition,totalItemCount,firstVisibleItemPosition));
                       requestNextPage();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d(TAG,String.format("dx:%d dy:%d",dx,dy));
                scrollDown=dy>0;
                lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onStop();
    }
    void requestNextPage(){

        if(Utils.isInternetConnectionExist(this)) {
            if(!viewModel.isDataFullyLoaded()) {
                loadContainer.setVisibility(View.VISIBLE);
                viewModel.getNextPage();
            }
        }else {
            loadContainer.setVisibility(View.GONE);
            loadDataTxt.setVisibility(View.GONE);
            Utils.showToast(this, "No Internet connection !");
        }
    }

    @Override
    public boolean onDataLoaded(boolean success, List<Repo> data, Object extra) {
        loadContainer.setVisibility(View.GONE);

        if(!success){
            Utils.showToast(this,"Error:"+extra);
            loadDataTxt.setVisibility(View.GONE);
        }else{
            Log.d(TAG,"Items :"+viewModel.getData().size());
            if(extra != null && ((String)extra).equals("RealmChange")){
                //Notification from realm
                loadDataTxt.setVisibility(View.GONE);
            }
        }
        adapter.notifyDataSetChanged();
        return false;
    }
}

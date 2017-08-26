package com.mgalal.payconiq.payconiqtest.viewmodel;

import android.content.Context;
import android.util.Log;

import com.mgalal.payconiq.payconiqtest.R;
import com.mgalal.payconiq.payconiqtest.Utils;
import com.mgalal.payconiq.payconiqtest.model.Repo;
import com.mgalal.payconiq.payconiqtest.networking.Network;
import com.mgalal.payconiq.payconiqtest.networking.services.ReposFetcherService;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by fujitsu-lap on 25/08/2017.
 */

public class ReposViewModel implements BaseViewModel<Repo>,RealmChangeListener<RealmResults<Repo>> {
    private static final String TAG = ReposViewModel.class.getName();
    private static final String LAST_FETCHED_PAGE_INDEX = "com.mgalal.payconiq.payconiqtest.LAST_FETCHED_PAGE_INDEX";
    private static final String DATA_FULLY_LOADED_KEY = "com.mgalal.payconiq.payconiqtest.DATA_FULLY_LOADED_KEY";
    private Realm mRealm;
    private int currentPage = 0;
    private OnDataLoadedListener<Repo> listener;
    private Context context;
    private RealmResults<Repo> repoRealmResults;
    private AtomicBoolean operationRunning;
    public static final int ITEMS_PER_PAGE = 15;
    private boolean started =  false;
    private boolean dataFullyLoaded ;
    private  Call<List<Repo>> call;


    public ReposViewModel(Context context){
        this.context = context;
        init();
    }
    public ReposViewModel(Context context,OnDataLoadedListener<Repo> listener){
        this.listener = listener;
        this.context = context;
        init();
    }
    private void init(){
        operationRunning =  new AtomicBoolean(false);
        currentPage = context.getSharedPreferences(Utils.SHARED_PREF_FN,0).
                getInt(LAST_FETCHED_PAGE_INDEX,0);
        dataFullyLoaded = context.getSharedPreferences(Utils.SHARED_PREF_FN,0).
                getBoolean(DATA_FULLY_LOADED_KEY,false);
    }
    @Override
    public void onStart() {
        started = true;
        mRealm =  Realm.getDefaultInstance();
        repoRealmResults = mRealm.where(Repo.class).findAllAsync();
        repoRealmResults.addChangeListener(this);
    }

    public boolean isStarted() {
        return started;
    }

    @Override
    public void onStop() {
        //storing last fetched page for later use
        context.getSharedPreferences(Utils.SHARED_PREF_FN,0).edit().
                putInt(LAST_FETCHED_PAGE_INDEX,currentPage).commit();
        context.getSharedPreferences(Utils.SHARED_PREF_FN,0).edit().
                putBoolean(DATA_FULLY_LOADED_KEY,dataFullyLoaded).commit();
        started = false;
        if(call != null && !call.isCanceled())
            call.cancel();
        if(repoRealmResults != null)
            repoRealmResults.removeAllChangeListeners();
        if(mRealm != null && !mRealm.isClosed())
            mRealm.close();
    }

    @Override
    public void getNextPage() {
        if(!started)
            throw new UnsupportedOperationException("Start the View model before use !");
        if(operationRunning.get() || dataFullyLoaded)
            return;
        final Network network = Network.getInstance();
        ReposFetcherService service = network.createService(ReposFetcherService.class);
        call = service.getUserRepos(currentPage+1,ITEMS_PER_PAGE);
        operationRunning.set(true);
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                if(!started)
                    return;
                operationRunning.set(false);
                int responseCode = response.code();
                String rateHeader = response.headers().get("X-RateLimit-Remaining");

                Log.d(TAG,String.format("Response status:%d rateHeader:%s",responseCode,rateHeader));
                if(responseCode > 400){
                    if(listener != null)
                        listener.onDataLoaded(false,null,"Error code:"+responseCode);
                    return;
                }
                List<Repo>repos = response.body();

                boolean inserted =  false;
                if(repos != null) {
                    currentPage++;
                    Log.d(TAG,String.format("Retrieved %d repo item for page:%d",repos.size(),currentPage));
                    inserted = insertData(repos);
                    if(repos.size() == 0){//data fully loaded
                        dataFullyLoaded  = true;
                    }
                }
                if(listener != null)
                    listener.onDataLoaded(true,repos,null);
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                if(!started)
                    return;
                t.printStackTrace();
                Log.d(TAG,t.getMessage());
                operationRunning.set(false);
                if(listener != null)
                    listener.onDataLoaded(false,null,"Error requesting data:"+t.getMessage());
            }
        });

    }
    private boolean insertData(List<Repo> repos){
        boolean inserted =false;
        if(!started)
            return false;
        mRealm.beginTransaction();
        inserted = mRealm.copyToRealmOrUpdate(repos).size() == repos.size();
        mRealm.commitTransaction();
        if(inserted)
        Log.d(TAG,"Items copied to realm");
        return inserted;
    }
    public List<Repo> getData(){
        if(!started)
            throw new UnsupportedOperationException("Start the View model before use !");
        return repoRealmResults;
    }

    @Override
    public List<Repo> getPage(int page) {
        return null;
    }

    @Override
    public int getCurrentPage() {
        return currentPage;
    }
    public boolean isOperationRunning(){
        return operationRunning.get();
    }
    public boolean isDataFullyLoaded() {
        return dataFullyLoaded;
    }

    @Override
    public void onChange(RealmResults<Repo> repos) {
        if(listener != null)
            listener.onDataLoaded(true,repos,"RealmChange");
    }
}

package com.mgalal.payconiq.payconiqtest;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.mgalal.payconiq.payconiqtest.model.Repo;
import com.mgalal.payconiq.payconiqtest.viewmodel.OnDataLoadedListener;
import com.mgalal.payconiq.payconiqtest.viewmodel.ReposViewModel;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import io.realm.Realm;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ReposViewModelInstrumentedTest implements OnDataLoadedListener<Repo> {
    private CountDownLatch countDownLatch = new CountDownLatch(2);
    private ReposViewModel viewModel;
    private Handler handler ;
    @Test
    public void testViewModel() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        handler = new Handler(Looper.getMainLooper());
        Realm.init(appContext);
        viewModel = new ReposViewModel(appContext,this);
        handler.post(new Runnable() {
            @Override
            public void run() {
                viewModel.onStart();
                viewModel.getNextPage();
            }
        });
        //stop the thread till we receive a respond
        countDownLatch.await(20, TimeUnit.SECONDS);
        handler.post(new Runnable() {
            @Override
            public void run() {
                viewModel.onStop();
            }
        });
    }

    @Override
    public boolean onDataLoaded(boolean success, List<Repo> data, Object extra) {
        if(success ){
            if(extra == null) {
                //asserting backend fetch
                assertEquals(ReposViewModel.ITEMS_PER_PAGE,data.size());
            }else{
                //asserting Database fetch
                assertNotEquals(0,viewModel.getData().size());
            }
        }else {
                assertNotNull(extra);
            countDownLatch.countDown();
        }
        //resume the test thread
        countDownLatch.countDown();
        return false;
    }
}

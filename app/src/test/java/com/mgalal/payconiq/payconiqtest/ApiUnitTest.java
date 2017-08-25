package com.mgalal.payconiq.payconiqtest;

import com.mgalal.payconiq.payconiqtest.model.Repo;
import com.mgalal.payconiq.payconiqtest.networking.Network;
import com.mgalal.payconiq.payconiqtest.networking.services.ReposFetcherService;

import org.junit.Test;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ApiUnitTest {
    @Test
    public void checkAPIService() throws Exception {
        //Assert Networking engine and API
        final Network network = Network.getInstance();
        ReposFetcherService service = network.createService(ReposFetcherService.class);
        Call<List<Repo>> call = service.getUserRepos(1,15);
        Response<List<Repo>> response = call.execute();
        int ret = response.code();
        //assertNotEquals(ret,403);
        //assertNotEquals(ret,404);
        assertEquals(200,ret);
        assertEquals(15,response.body().size());
    }

}
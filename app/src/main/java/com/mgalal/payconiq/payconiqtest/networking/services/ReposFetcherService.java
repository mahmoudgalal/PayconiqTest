package com.mgalal.payconiq.payconiqtest.networking.services;

import com.mgalal.payconiq.payconiqtest.model.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by fujitsu-lap on 25/08/2017.
 *
 * Repos service
 */

public interface ReposFetcherService {
    @GET("repos")
    Call<List<Repo>> getUserRepos(@Query("page") int page, @Query("per_page") int perPage);
}

package com.mgalal.payconiq.payconiqtest.viewmodel;



import java.util.List;

import io.realm.RealmObject;

/**
 * Created by fujitsu-lap on 25/08/2017.
 *
 * Base View model operations
 */

public interface BaseViewModel<T extends RealmObject> {
//Life cycle methods
    void onStart();
    void onStop();

    /**
     * Should request the next applicable page from the backend
     */
    void getNextPage();
    List<T> getPage(int page);
    int getCurrentPage();
}

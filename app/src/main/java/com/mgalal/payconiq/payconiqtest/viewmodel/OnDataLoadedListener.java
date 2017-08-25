package com.mgalal.payconiq.payconiqtest.viewmodel;

import java.util.List;

/**
 * Created by fujitsu-lap on 25/08/2017.
 */

public interface OnDataLoadedListener<T> {
    /**
     * Reports data load events for consumer
     * @param success load success
     * @param data loaded data  if any
     * @param extra
     * @return false to retry in error cases
     */
    boolean onDataLoaded(boolean success,List<T> data,Object extra);
}

package com.mgalal.payconiq.payconiqtest;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by fujitsu-lap on 25/08/2017.
 */

public class Utils {
    public static final String BASE_URL = "https://api.github.com/users/JakeWharton/";
    public static final String SHARED_PREF_FN ="payconiq_project_shared_fn";
    //https://api.github.com/users/JakeWharton/repos?page=1&per_page=15

    /**
     * Returns whether there is a valid internet connection or no connection
     *
     * @param context
     * @return true if there is internet connection false otherwise
     */
    public static boolean isInternetConnectionExist(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
    public static void showToast(Context context, int msgRes) {
        Toast.makeText(context, msgRes, Toast.LENGTH_SHORT).show();
    }
}

package com.nour.myapplication.util;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;

import com.nour.myapplication.R;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Util {
    public static void showDialog (Context context , String title , String message ){

    AlertDialog alertDialog = new AlertDialog.Builder(context).create();

                    alertDialog.setTitle(title);

                    alertDialog.setMessage(message);

                    alertDialog.setIcon(R.drawable.ic_error_outline_black_24dp);

                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
        }
        });
                    alertDialog.show();
        }

    public static boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    }




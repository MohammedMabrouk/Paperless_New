package com.rentcentric.paperlesscounter.Utility;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;

import com.rentcentric.paperlesscounter.R;

public class Extensions {

    public static void ReplaceContainers(int role, View TimeoutContainer, View NotFoundContainer, View MainContainer, View Progress) {

        switch (role) {

            case 1:
                TimeoutContainer.setVisibility(View.VISIBLE);
                if (NotFoundContainer != null) {
                    NotFoundContainer.setVisibility(View.GONE);
                }
                MainContainer.setVisibility(View.GONE);
                Progress.setVisibility(View.GONE);
                break;

            case 2:
                TimeoutContainer.setVisibility(View.GONE);
                NotFoundContainer.setVisibility(View.VISIBLE);
                MainContainer.setVisibility(View.GONE);
                Progress.setVisibility(View.GONE);
                break;

            case 3:
                TimeoutContainer.setVisibility(View.GONE);
                if (NotFoundContainer != null) {
                    NotFoundContainer.setVisibility(View.GONE);
                }
                MainContainer.setVisibility(View.VISIBLE);
                Progress.setVisibility(View.GONE);
                break;

            case 4:
                TimeoutContainer.setVisibility(View.GONE);
                MainContainer.setVisibility(View.GONE);
                Progress.setVisibility(View.VISIBLE);
                break;

        }
    }

    public static void Dialog(Context context, String Description) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setPositiveButton(context.getString(R.string.ok), null);
        alert.setMessage(Description);
        alert.setCancelable(false);
        alert.show();
    }

    public static boolean isNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }
}
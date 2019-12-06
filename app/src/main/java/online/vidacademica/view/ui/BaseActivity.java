package online.vidacademica.view.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public abstract class BaseActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;

    private static final String YES = "SIM";
    private static final String NO = "NÃO";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected final void showProgressBar(final int parentViewId) {

        if (mProgressBar == null) {
            mProgressBar = new ProgressBar(this);
            mProgressBar.setVisibility(View.INVISIBLE);
            mProgressBar.setId(View.generateViewId());

            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(300, 300);

            layoutParams.topToTop = parentViewId;
            layoutParams.bottomToBottom = parentViewId;
            layoutParams.leftToLeft = parentViewId;
            layoutParams.rightToRight = parentViewId;
            layoutParams.verticalBias = (float) 0.5;
            layoutParams.horizontalBias = (float) 0.5;

            mProgressBar.setLayoutParams(layoutParams);
        }

        mProgressBar.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        ViewGroup vg = ((ViewGroup) findViewById(parentViewId));
        vg.addView(mProgressBar);
    }

    protected final void dismissProgressBar() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.INVISIBLE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            Toast.makeText(this, "Carregamento cancelado. \n \n Favor tentar novamente.", Toast.LENGTH_LONG).show();
        } else {
            super.onBackPressed();
        }
    }

    protected final void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    protected final void showAlert(String title, String msg, int actionCustomIdentifier) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(msg)
                .setCancelable(false)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(YES, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        alertYes(actionCustomIdentifier);
                    }
                })
                .setNegativeButton(NO, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        alertNo(actionCustomIdentifier);
                    }
                }).create().show();
    }

    protected final void showAlert(String title, String msg) {
        showAlert(title, msg, 0);
    }



    protected abstract void alertYes(final int actionCustomIdentifier);

    protected abstract void alertNo(final int actionCustomIdentifier);


}
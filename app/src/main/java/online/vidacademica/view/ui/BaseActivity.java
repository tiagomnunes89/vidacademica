package online.vidacademica.view.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Constraints;

import org.xmlpull.v1.XmlPullParser;

import online.vidacademica.R;

public abstract class BaseActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected final void showProgressBar(int parentViewId) {


//                <ProgressBar
//        android:id="@+id/progressBar_id"
//        style="?android:attr/progressBarStyle"
//        android:layout_width="wrap_content"
//        android:layout_height="wrap_content"
//        app:layout_constraintBottom_toBottomOf="parent"
//        app:layout_constraintEnd_toEndOf="parent"
//        app:layout_constraintStart_toStartOf="parent"
//        app:layout_constraintTop_toTopOf="parent" />

        if (mProgressBar == null) {
            mProgressBar = new ProgressBar(this);
            mProgressBar.setVisibility(View.INVISIBLE);

            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(this.getParent());
            constraintSet.connect(parentViewId, ConstraintSet.START, parentViewId, ConstraintSet.END);
            constraintSet.connect(parentViewId, ConstraintSet.TOP, parentViewId, ConstraintSet.BOTTOM);
            constraintSet.applyTo(mProgressBar);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mProgressBar.setLayoutParams(layoutParams);
        }

        mProgressBar.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        ((ViewGroup) findViewById(parentViewId)).addView(mProgressBar);
    }

    protected void dismissProgressBar() {
//        if (mProgressBar != null) {
//            mProgressBar.setVisibility();
//        }
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    protected void showAlert(String msg) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.app_name))
                .setMessage(msg)
                .setCancelable(false)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create().show();
    }
}

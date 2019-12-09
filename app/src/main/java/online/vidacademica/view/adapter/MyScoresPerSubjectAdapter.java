package online.vidacademica.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import online.vidacademica.R;
import online.vidacademica.entities.TestResultDTO;
import online.vidacademica.view.enums.CrudEnum;
import online.vidacademica.view.ui.MyScoresActivity;
import online.vidacademica.view.ui.MyScoresPerSubjectActivity;

import static online.vidacademica.utils.JsonUtils.toJson;

public class MyScoresPerSubjectAdapter extends RecyclerView.Adapter<MyScoresPerSubjectAdapter.ScoresViewHolder> {
    private static final String TAG = MyScoresActivity.class.getSimpleName();

    private Context context;

    private List<TestResultDTO> testResultDTOS = new ArrayList<>();

    private LayoutInflater layoutInflater;

    public MyScoresPerSubjectAdapter(Context context, List<TestResultDTO> testResultDTOS) {
        this.context = context;
        this.testResultDTOS = testResultDTOS;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ScoresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_my_test, parent, false);
        return new ScoresViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoresViewHolder holder, int position) {
        TestResultDTO score = testResultDTOS.get(position);

        Locale.setDefault(Locale.US);

        holder.titleTest.setText(score.getTestName());
        holder.fullScore.setText(Double.toString(score.getScore()));

        String dayTest = score.getDate().toString().substring(8, 10);
        String monthTest = score.getDate().toString().substring(5, 7);
        String yearTest = score.getDate().toString().substring(2, 4);

        holder.dtTest.setText(dayTest + "/" + monthTest + "/" + yearTest);

    }

    @Override
    public int getItemCount() {
        return testResultDTOS.size();
    }

    public class ScoresViewHolder extends RecyclerView.ViewHolder {
        //item_titulo_materia_em_minhas_notas
        TextView titleTest;
        TextView fullScore;
        TextView dtTest;

        public ScoresViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTest = itemView.findViewById(R.id.item_titulo_test);
            fullScore = itemView.findViewById(R.id.item_fullScore_test);
            dtTest = itemView.findViewById(R.id.item_data_test);
        }


    }
}

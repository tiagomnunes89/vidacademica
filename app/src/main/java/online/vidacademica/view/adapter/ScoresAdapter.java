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
import online.vidacademica.entities.ScoreBySubject;
import online.vidacademica.entities.TestResultDTO;
import online.vidacademica.view.ui.CreateUpdateCourseActivity;

import static online.vidacademica.utils.JsonUtils.toJson;

public class ScoresAdapter extends RecyclerView.Adapter<ScoresAdapter.ScoresViewHolder> {

    private static final String TAG = CoursesAdapter.class.getSimpleName();

    List<ScoreBySubject> scoreBySubjects = new ArrayList<>();

    LayoutInflater layoutInflater;

    public ScoresAdapter(Context context, List<ScoreBySubject> scoreBySubjects){
        this.scoreBySubjects = scoreBySubjects;
        this.layoutInflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public ScoresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_recycler_scores, parent, false);
        return new ScoresViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoresViewHolder holder, int position) {
        ScoreBySubject score = scoreBySubjects.get(position);
        Locale.setDefault(Locale.US);
        holder.nota.setText(Double.toString(score.getSubjectTotalScore()));
        holder.titulo_materia.setText(score.getSubject());


    }

    @Override
    public int getItemCount() {
        return scoreBySubjects.size();
    }

    public class ScoresViewHolder extends RecyclerView.ViewHolder {
        //item_titulo_materia_em_minhas_notas
        TextView titulo_materia;
        TextView nota;

        public ScoresViewHolder(@NonNull View itemView) {
            super(itemView);
            nota = itemView.findViewById(R.id.item_score);
            titulo_materia = itemView.findViewById(R.id.item_titulo_materia);
        }
    }
}

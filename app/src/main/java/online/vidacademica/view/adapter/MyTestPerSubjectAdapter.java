package online.vidacademica.view.adapter;

import android.content.Context;
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

public class MyTestPerSubjectAdapter extends RecyclerView.Adapter<MyTestPerSubjectAdapter.ScoresViewHolder> {

    List<TestResultDTO> testResultDTOS = new ArrayList<>();

    LayoutInflater layoutInflater;

    public MyTestPerSubjectAdapter(Context context, List<TestResultDTO> testResultDTOS){
        this.testResultDTOS = testResultDTOS;
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
        TestResultDTO score = testResultDTOS.get(position);
        Locale.setDefault(Locale.US);
        holder.nota.setText(Double.toString(score.getScore()));
        holder.titulo_materia.setText(score.getsubjectName());
    }

    @Override
    public int getItemCount() {
        return testResultDTOS.size();
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

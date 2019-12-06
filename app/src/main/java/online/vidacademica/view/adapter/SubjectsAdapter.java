package online.vidacademica.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import online.vidacademica.R;
import online.vidacademica.entities.SubjectDTO;

public class SubjectsAdapter extends RecyclerView.Adapter<SubjectsAdapter.SubjectViewHolder> {
    List<SubjectDTO> subjects = new ArrayList<>();
    LayoutInflater layoutInflater;

    public SubjectsAdapter(Context context, List<SubjectDTO> subjects) {
        this.subjects = subjects;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_recycler_subject, parent, false);
        return new SubjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {
        SubjectDTO subject = subjects.get(position);
        holder.titulo.setText(subject.getName());
        String yearStart = subject.getStartDate().substring(2, 4);
        String monthStart = subject.getStartDate().substring(5, 7);
        String yearEnd = subject.getEndDate().substring(2, 4);;
        String monthEnd = subject.getEndDate().substring(5, 7);;
        holder.data.setText(monthStart + "/" + yearStart + " - " + monthEnd + "/" + yearEnd);

        if(subject.isActive()){
            holder.circle.setImageResource(R.drawable.ic_green);
        }
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public class SubjectViewHolder extends RecyclerView.ViewHolder {
        //item_titulo_materia
        TextView titulo;
        TextView data;
        ImageView circle;

        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.item_titulo_materia);
            data = itemView.findViewById(R.id.item_data_periodo_materia);
            circle = itemView.findViewById(R.id.item_circle);
        }
    }
}

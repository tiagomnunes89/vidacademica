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

import online.vidacademica.R;
import online.vidacademica.entities.CourseDTO;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.CourseViewHolder> {

    List<CourseDTO> courses = new ArrayList<>();
    LayoutInflater layoutInflater;

    public CoursesAdapter(Context context, List<CourseDTO> courses) {
        this.courses = courses;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_my_course, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        CourseDTO courseDTO = courses.get(position);
        holder.titulo.setText(courseDTO.getName());
        String status;
        if (courseDTO.isActive()){
            status = "Ativo";
        }else {
            status = "Inativo";
        }
        holder.status.setText(status);
        holder.descricao.setText(courseDTO.getDescription());
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder {
        //item_titulo_course
        TextView titulo;
        TextView status;
        TextView descricao;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.name_course);
            status = itemView.findViewById(R.id.status_course);
            descricao = itemView.findViewById(R.id.description_course);
        }
    }
}
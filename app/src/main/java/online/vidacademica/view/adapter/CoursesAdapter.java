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

import online.vidacademica.R;
import online.vidacademica.entities.CourseDTO;
import online.vidacademica.utils.JsonUtils;
import online.vidacademica.view.enums.CrudEnum;
import online.vidacademica.view.ui.CreateUpdateCourseActivity;

import static online.vidacademica.utils.JsonUtils.toJson;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.CourseViewHolder> {
    private static final String TAG = CoursesAdapter.class.getSimpleName();

    private Context context;

    private List<CourseDTO> courses;
    private LayoutInflater layoutInflater;

    public static final String CRUD_TYPE = "CRUD_TYPE";
    private static final CrudEnum UPDATE = CrudEnum.UPDATE;

    public static final String SELECTED_OBJECT = "SELECTED_OBJECT";

    public CoursesAdapter(Context context, List<CourseDTO> courses) {
        this.context = context;
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
        holder.status.setText(courseDTO.isActiveString());
        holder.descricao.setText(courseDTO.getDescription());

        holder.itemView.setOnClickListener(v -> {
            Log.i(TAG, "onBindViewHolder: " + toJson(courses.get(position)));

            Intent intent = new Intent(context, CreateUpdateCourseActivity.class)
                    .putExtra(CRUD_TYPE, UPDATE)
                    .putExtra(SELECTED_OBJECT, toJson(courses.get(position)));

            context.startActivity(intent);

        });
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

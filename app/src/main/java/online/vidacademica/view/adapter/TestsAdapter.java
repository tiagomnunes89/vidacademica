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
import online.vidacademica.entities.TestEntity;
import online.vidacademica.view.enums.CrudEnum;
import online.vidacademica.view.ui.CreateUpdateCourseActivity;

import static online.vidacademica.utils.JsonUtils.toJson;

public class TestsAdapter extends RecyclerView.Adapter<TestsAdapter.TestViewHolder> {
    private static final String TAG = CoursesAdapter.class.getSimpleName();

    private Context context;

    List<TestEntity> tests = new ArrayList<>();
    LayoutInflater layoutInflater;

    public static final String CRUD_TYPE = "CRUD_TYPE";
    private static final CrudEnum UPDATE = CrudEnum.UPDATE;

    public static final String SELECTED_OBJECT = "SELECTED_OBJECT";


    public TestsAdapter(Context context, List<TestEntity> tests) {
        this.context = context;
        this.tests = tests;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_my_test, parent, false);
        return new TestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {
        TestEntity testEntity = tests.get(position);
        holder.titulo.setText(testEntity.getName());
        holder.fullScore.setText(testEntity.getFullScore());
        String dayTest = testEntity.getDate().substring(8,10);
        String monthTest = testEntity.getDate().substring(5,7);
        String yearTest = testEntity.getDate().substring(2,4);
        holder.dtTest.setText(dayTest + "/"+monthTest+"/"+yearTest);

        holder.itemView.setOnClickListener(v -> {
            Log.i(TAG, "onBindViewHolder: " + toJson(tests.get(position)));

            Intent intent = new Intent(context, CreateUpdateCourseActivity.class)
                    .putExtra(CRUD_TYPE, UPDATE)
                    .putExtra(SELECTED_OBJECT, toJson(tests.get(position)));

            context.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return tests.size();
    }

    public class TestViewHolder extends RecyclerView.ViewHolder {
        //item_titulo_course
        TextView titulo;
        TextView fullScore;
        TextView dtTest ;

        public TestViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.item_titulo_test);
            fullScore = itemView.findViewById(R.id.item_fullScore_test);
            dtTest = itemView.findViewById(R.id.item_data_test);
        }
    }
}

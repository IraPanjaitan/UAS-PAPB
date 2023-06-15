package rmp.mobile.uaspapb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder>{
    private Context ctx;

    private ArrayList<ToDoModel> toDoModelArrayList;

    public ToDoAdapter(Context ctx, ArrayList<ToDoModel> toDoModelArrayList) {
        this.ctx = ctx;
        this.toDoModelArrayList = toDoModelArrayList;
    }


    @NonNull
    @Override
    public ToDoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.todolist_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoAdapter.ViewHolder holder, int position) {
        ToDoModel model = toDoModelArrayList.get(position);
        holder.activityTV.setText(model.getActivity());
        holder.timeTV.setText(model.getTime());
    }

    @Override
    public int getItemCount() {
        return toDoModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView activityTV, timeTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            activityTV = itemView.findViewById(R.id.idTVActivity);
            timeTV = itemView.findViewById(R.id.idTVTime);
        }
    }
}

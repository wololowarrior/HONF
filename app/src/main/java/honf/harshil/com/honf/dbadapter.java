package honf.harshil.com.honf;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by harshil on 16.11.16.
 */

public class dbadapter extends RecyclerView.Adapter<dbadapter.ViewHolder> {
    restaurantEntity[] restaurantName;
    Context context;

    public dbadapter(Context context, restaurantEntity[] restaurantName) {
        this.restaurantName = restaurantName;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_restaurant, parent, false);
        ViewHolder viewHolder = new ViewHolder(v, context, restaurantName);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(restaurantName[position].restaurantName);

    }

    @Override
    public int getItemCount() {
        return restaurantName.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView;
        restaurantEntity restaurantName[];
        Context context;

        public ViewHolder(View view, Context context, restaurantEntity[] restaurantName) {
            super(view);
            this.context = context;
            this.restaurantName = restaurantName;
            view.setOnClickListener(this);
            textView = (TextView) view.findViewById(R.id.resname);

        }


        @Override
        public void onClick(View view) {
            restaurantEntity restname = restaurantName[getAdapterPosition()];

            Intent i = new Intent(context, restaraunt_profile.class);
            i.putExtra("resid", restname.id);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.context.startActivity(i);
        }
    }
}



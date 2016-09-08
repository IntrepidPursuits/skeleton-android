package io.intrepid.skeleton.screens.demoSelection;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.intrepid.skeleton.R;
import io.intrepid.skeleton.models.Demo;

public class DemoSelectionAdapter extends RecyclerView.Adapter<DemoSelectionAdapter.ViewHolder> {
    private Context context;
    private OnSelectListener listener;

    public DemoSelectionAdapter(Context context, OnSelectListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.demo_selection_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.configureViews(Demo.values()[position]);
    }

    @Override
    public int getItemCount() {
        return Demo.values().length;
    }

    public interface OnSelectListener {
        void demoSelected(Demo demo);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private Demo demo;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(v -> DemoSelectionAdapter.this.listener.demoSelected(demo));
        }

        public void configureViews(Demo demo) {
            this.demo = demo;
            ((TextView) itemView).setText(demo.getTitle(context));
        }
    }
}

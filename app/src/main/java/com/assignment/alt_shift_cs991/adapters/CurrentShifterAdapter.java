package com.assignment.alt_shift_cs991.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.assignment.alt_shift_cs991.R;
import com.assignment.alt_shift_cs991.activities.CalendarActivity;
import com.assignment.alt_shift_cs991.activities.SwapActivity;
import com.assignment.alt_shift_cs991.model.Shift;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Adapter for the shifter that is currently logged into the app.
 */
public class CurrentShifterAdapter extends RecyclerView.Adapter<CurrentShifterAdapter.MyViewHolder> {

    private List<Shift> shifts;
    public CalendarActivity.CallBack callBack;

    /**
     * A constructor for the CurrentShifterAdapter class.
     */
    public CurrentShifterAdapter(List<Shift> shifts) {
        super();
        setHasStableIds(true);
        this.shifts = shifts;
    }

    /**
     * Returns the hash value of the item in a specific position in the list.
     *
     * @param position the index position of the item
     * @return the hashCode of the item
     */
    @Override
    public long getItemId(int position) {
        return shifts.get(position).hashCode();
    }

    /**
     * Updates items.
     *
     * @param shifts - shifts to be updated in the list
     */
    public void setItems(List<Shift> shifts) {
        this.shifts = shifts;
        notifyDataSetChanged();
    }

    public void setCallBack(CalendarActivity.CallBack callBack) {
        this.callBack = callBack;
    }

    /**
     * Creates the viewHolder and places it inside the correct viewGroup.
     *
     * @param viewGroup the viewGroup in question
     * @param viewType  the viewType which the holder should be placed in
     * @return the viewHolder
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return MyViewHolder.createHolder(viewGroup);
    }

    /**
     * Sets the item from the list into a specific holder in the recyclerView.
     *
     * @param viewHolder the viewHolder
     * @param position   the position within the list
     */
    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        Shift shift = shifts.get(position);
        viewHolder.shifterName.setText(shift.getShifter().getFirstName());
        viewHolder.date.setText(shift.getDate());
        viewHolder.itemView.setOnClickListener(v -> {
            int a = viewHolder.getAdapterPosition();
            Intent intent = new Intent(v.getContext(), SwapActivity.class);
            callBack.getModel().selectedCurrentShift = shifts.get(a);
            v.getContext().startActivity(intent);
        });
    }

    /**
     * A method to show the number of items within the list.
     *
     * @return the number of values in the list
     */
    @Override
    public int getItemCount() {
        return shifts.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView shifterName;
        private TextView date;

        /**
         * A constructor which initiates the views which will be inside the textView.
         *
         * @param itemView the current view
         */
        public MyViewHolder(View itemView) {
            super(itemView);
            shifterName = itemView.findViewById(R.id.name_field);
            date = itemView.findViewById(R.id.description_field);
        }

        /**
         * A method to create the view holder within the current view group.
         *
         * @param viewGroup the current view group
         * @return a view holder which holds list items
         */
        public static MyViewHolder createHolder(ViewGroup viewGroup) {
            LayoutInflater viewInflater = LayoutInflater.from(viewGroup.getContext());
            View listItemView = viewInflater.inflate(R.layout.adapter_item, viewGroup, false);
            return new MyViewHolder(listItemView);
        }
    }
}




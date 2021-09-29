package com.example.solarguide;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.location.LocationRequest;


public class state_custom_adapter extends FirebaseRecyclerAdapter<state_data,state_custom_adapter.myviewholder>{

    public int residential_check, commercial_check, industrial_check = 0;
    public int triangular_check, flat_check, shade_check, ground_check = 0;
    public float length , width =0 ;
    public String latitude, longitude;
    public String state_name;
    int pos;
    public LocationRequest locationRequest;

    Context context;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public state_custom_adapter(@NonNull FirebaseRecyclerOptions<state_data> options, Context context, int residential_check, int commercial_check, int industrial_check, int shade_check, int flat_check, int triangular_check, int ground_check, float length, float width, String state_name, int pos) {
        super(options);
        this.context = context;


        this.residential_check = residential_check;
        this.commercial_check = commercial_check;
        this.industrial_check = industrial_check;
        this.shade_check = shade_check;
        this.flat_check = flat_check;
        this.triangular_check = triangular_check;
        this.ground_check = ground_check;
        this.length = length;
        this.width = width;
        this.state_name = state_name;
        this.pos = pos;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull state_data model) {

        holder.name.setText(model.getName());
        //pos = position;

        if(state_name == null)
        {

        }
        else if(state_name.equals(model.getName()))
        {
            holder.name.setBackgroundColor(Color.parseColor("#FFD600"));
        }
        else
        {

        }




        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos = position;


                Bundle bundle = new Bundle();
                bundle.putInt("residential_check", residential_check);
                bundle.putInt("commercial_check", commercial_check);
                bundle.putInt("industrial_check", industrial_check);
                bundle.putInt("triangular_check", triangular_check);
                bundle.putInt("flat_check", flat_check);
                bundle.putInt("ground_check", ground_check);
                bundle.putInt("shade_check", shade_check);
                bundle.putFloat("length", length);
                bundle.putFloat("width", width);
                bundle.putInt("Position", pos);
                bundle.putString("state_name", model.getName());


                Intent mIntent = new Intent(context, states_list.class);
                mIntent.putExtras(bundle);
                context.startActivity(mIntent);

            }
        });


    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_state_layout,parent,false);




        return new myviewholder(view);
    }






    static class myviewholder extends RecyclerView.ViewHolder
    {
        TextView name;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.state_name);


        }
    }










}

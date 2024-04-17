package com.mh.parsingjson.FromAssetsToRecyclerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.mh.parsingjson.R;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private ArrayList<Student> students;
    private Context context;

    public StudentAdapter(@NonNull Context context, ArrayList<Student> students) {
        this.students = students;
        this.context = context;
    }

    @NonNull
    @Override
    public StudentAdapter.StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_info_item, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.StudentViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Student student = students.get(position);
        String fullName = student.getName();
        holder.fullName.setText(fullName);
        holder.email.setText(student.getEmail());
        holder.contactMobile.setText(student.getContactMobile());
        holder.contactHome.setText(student.getContactHome());
        holder.cardViewStudentInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.constraintLayout.getVisibility() == View.GONE) {
                    holder.constraintLayout.setVisibility(View.VISIBLE);
                    holder.imageViewShowInfo.setImageResource(R.drawable.arrow_up);
                } else {
                    holder.constraintLayout.setVisibility(View.GONE);
                    holder.imageViewShowInfo.setImageResource(R.drawable.arrow_down);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        public TextView fullName;
        public TextView email;
        public TextView contactMobile;
        public TextView contactHome;
        public ConstraintLayout constraintLayout;
        public ImageView imageViewShowInfo;
        public CardView cardViewStudentInfo;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.txt_student_name);
            email = itemView.findViewById(R.id.txt_student_email);
            contactMobile = itemView.findViewById(R.id.txt_mobile);
            contactHome = itemView.findViewById(R.id.txt_home);
            constraintLayout = itemView.findViewById(R.id.constraint_layout_show_info);
            imageViewShowInfo = itemView.findViewById(R.id.img_view_show_info);
            cardViewStudentInfo = itemView.findViewById(R.id.student_info_cardview);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }
}
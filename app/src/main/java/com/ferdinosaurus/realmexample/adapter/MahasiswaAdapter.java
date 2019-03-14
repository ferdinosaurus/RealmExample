package com.ferdinosaurus.realmexample.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ferdinosaurus.realmexample.R;
import com.ferdinosaurus.realmexample.activity.MainActivity;
import com.ferdinosaurus.realmexample.activity.UpdateActivity;
import com.ferdinosaurus.realmexample.realm.RealmController;
import com.ferdinosaurus.realmexample.realm.model.MahasiswaModel;

import io.realm.RealmResults;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MahasiswaItemViewHolder> {

    Activity activity;

    RealmResults<MahasiswaModel> mahasiswaModelRealmResults;
    RealmController realmController;

    public MahasiswaAdapter(Activity activity, RealmResults<MahasiswaModel> mahasiswaModelRealmResults) {
        this.activity = activity;
        this.mahasiswaModelRealmResults = mahasiswaModelRealmResults;
        realmController = new RealmController(activity);
    }

    @NonNull
    @Override
    public MahasiswaItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_mahasiswa, viewGroup, false);
        return new MahasiswaItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaItemViewHolder mahasiswaItemViewHolder, int i) {
        final MahasiswaModel mahasiswaModel = mahasiswaModelRealmResults.get(i);

        mahasiswaItemViewHolder.item_mahasiswa_nama.setText(mahasiswaModel.getNama());
        mahasiswaItemViewHolder.item_mahasiswa_jurusan.setText(mahasiswaModel.getJurusan());
        mahasiswaItemViewHolder.ll_item_mahasiswa.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                realmController.deleteMahasiswa(mahasiswaModel);
                notifyDataSetChanged();
                return false;
            }
        });

        mahasiswaItemViewHolder.ll_item_mahasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, UpdateActivity.class);
                intent.putExtra("id",mahasiswaModel.getId());
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mahasiswaModelRealmResults.size();
    }

    public class MahasiswaItemViewHolder extends RecyclerView.ViewHolder {

        TextView item_mahasiswa_nama,item_mahasiswa_jurusan;
        LinearLayout ll_item_mahasiswa;

        public MahasiswaItemViewHolder(@NonNull View itemView) {
            super(itemView);
            item_mahasiswa_nama = itemView.findViewById(R.id.item_mahasiswa_nama);
            item_mahasiswa_jurusan = itemView.findViewById(R.id.item_mahasiswa_jurusan);
            ll_item_mahasiswa = itemView.findViewById(R.id.ll_item_mahasiswa);
        }
    }
}

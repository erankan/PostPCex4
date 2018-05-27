package erankan.ex4postpc;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {

    private ArrayList<MyImage> imageArrayList;
    private LayoutInflater inflater;
    private ItemClickListener clickListener;

    ImageAdapter(Context context, ArrayList<MyImage> images){
        imageArrayList = images;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.my_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MyImage image = imageArrayList.get(position);
        holder.bindImg(image);
    }

    @Override
    public int getItemCount() {
        return imageArrayList.size();
    }

    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;

        MyViewHolder(View view){
            super(view);
            imageView = view.findViewById(R.id.myImage);
            view.setOnClickListener(this);
        }

        void bindImg(MyImage image){
            if (!TextUtils.isEmpty(image.getImage_url())){
                Picasso.with(imageView.getContext()).load(image.getImage_url()).into(imageView);
            }
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null)
                clickListener.onItemClick(v, getAdapterPosition());
        }
    }

    void setClickListener(ItemClickListener itemClickListener){
        clickListener = itemClickListener;
    }
}

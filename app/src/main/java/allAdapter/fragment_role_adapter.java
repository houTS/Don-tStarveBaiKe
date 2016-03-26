package allAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hou.ts.dontstarve.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import BmobTable.allRole;

/**
 * Created by Administrator on 2016/3/25.
 */
public class fragment_role_adapter extends RecyclerView.Adapter<fragment_role_adapter.MyViewHolder> {

    List<allRole> l;
    Context context;

    public fragment_role_adapter(Context context, List<allRole> l) {
        this.l = l;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder v = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.role_item, parent, false));
        return v;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.name.setText(l.get(position).getRoleName());
        holder.hp.setProgress(Integer.valueOf(l.get(position).getHP()));
        holder.san.setProgress(Integer.valueOf(l.get(position).getSAN()));
        holder.hun.setProgress(Integer.valueOf(l.get(position).getHUN()));
//        l.get(position).getRoleImg().loadImage(context, holder.img);  //这是bmob提供的图片加载，没缓存费流量
        //用Imageloader加载图片,设置配置
        Log.i("xx",l.get(position).getRoleImg().getUrl());
        DisplayImageOptions options=new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.loading)
                .showImageOnFail(R.drawable.error)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoader.getInstance().displayImage(l.get(position).getRoleImg().getFileUrl(context),holder.img,options);

        //监听
        if(click!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click.OnClick(holder.itemView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return l.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img = null;
        TextView name = null;
        ProgressBar hp, san, hun = null;

        public MyViewHolder(View v) {
            super(v);
            img = (ImageView) v.findViewById(R.id.item_img);
            name = (TextView) v.findViewById(R.id.item_name);
            hp = (ProgressBar) v.findViewById(R.id.item_hp);
            san = (ProgressBar) v.findViewById(R.id.item_san);
            hun = (ProgressBar) v.findViewById(R.id.item_hun);

        }
    }


    //设置监听（坑爹的没有自带额监听） 设置接口回调重写
    public interface myOnItemClick{
        void OnClick(View view,int position);
    }
    private myOnItemClick click;

    public void setOnItemClickListener(myOnItemClick newOneThisClick){
        this.click=newOneThisClick;
    }
    //然后再onBindViewHolder 里面判断

}


package fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hou.ts.dontstarve.R;
import com.hou.ts.dontstarve.roleShow;

import java.util.List;
import java.util.concurrent.TimeUnit;

import BmobTable.allRole;
import allAdapter.fragment_role_adapter;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2016/3/24.
 */
public class role_fragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    RecyclerView mRecycle;
    fragment_role_adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragmet_layout, null);
        mRecycle = (RecyclerView) v.findViewById(R.id.recycle);
        selectRole();

        return v;
    }

    private void selectRole() {

        final BmobQuery<allRole> query = new BmobQuery<allRole>();
        query.order("createdAt"); // 按创建时间排序 升序
        boolean isCache = query.hasCachedResult(getActivity(), allRole.class); // 必须在查询条件之后！
        if (isCache) {
            query.setCachePolicy(BmobQuery.CachePolicy.CACHE_ELSE_NETWORK); // 如果有缓存的话，则设置策略为CACHE_ELSE_NETWORK
        } else {
            query.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE); // 如果没有缓存的话，则设置策略为NETWORK_ELSE_CACHE
        }
        query.setMaxCacheAge(TimeUnit.DAYS.toMillis(30));// 此表示缓存30天
        query.findObjects(getActivity(), new FindListener<allRole>() {
            @Override
            public void onSuccess(final List<allRole> l) {
                if (l != null && l.size() > 0) {
                    LogMess("成功     数据数目" + l.size());
                    //设置布局管理器
                    mRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
                    //设置adapter
                    adapter = new fragment_role_adapter(getActivity(), l);
                    mRecycle.setAdapter(adapter);
                    //监听事件
                    adapter.setOnItemClickListener(new fragment_role_adapter.myOnItemClick() {
                        @Override
                        public void OnClick(View view, int position) {
                            ShowToast("点击了" + position);
                            Intent intent=new Intent(getActivity(),roleShow.class);
                            intent.putExtra("role",l.get(position));
                            startActivity(intent);

                        }
                    });
                    //设置Item增加、移除动画
                    mRecycle.setItemAnimator(new DefaultItemAnimator());
//                   //添加分割线
//                    mRecycle.addItemDecoration(new DividerItemDecoration(
//                            getActivity(), DividerItemDecoration.HORIZONTAL_LIST));
                } else {
                    LogMess("成功  但是没有数据");
                    ShowToast("成功  但是没有数据");

                }
            }

            @Override
            public void onError(int i, String s) {
                LogMess("获取失败"+i+""+s);
                ShowToast("获取失败");
                if(i==9009){
                    query.clearCachedResult(getActivity(),allRole.class);
                }
            }
        });


    }

    private void LogMess(String mess) {
        Log.i("notice", mess);
    }

    private void ShowToast(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

}




package cn.edu.snnu.zc.fragmentbestpractice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NewsContentFragment extends Fragment {
    private View view;

//    创建fragment的视图
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.news_content_frag,container,false);
        return view;
    }

    public void refresh(String newsTitle,String newsContent){
        View visibilityLayout = view.findViewById(R.id.visibility_layout);
//        使得布局可见
        visibilityLayout.setVisibility(view.VISIBLE);
//        实例化新闻标题的布局
        TextView newsTitleText = (TextView) view.findViewById(R.id.news_title);
//        实例化新闻内容的布局
        TextView newsContentText = (TextView) view.findViewById(R.id.news_content);
//        设置新闻标题
        newsTitleText.setText(newsTitle);
//        设置新闻内容
        newsContentText.setText(newsContent);
    }
}

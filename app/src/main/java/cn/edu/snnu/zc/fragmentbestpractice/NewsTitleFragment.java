package cn.edu.snnu.zc.fragmentbestpractice;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsTitleFragment extends Fragment {

    private static final String TAG = "ZCDEBUG";
    private boolean isTwoPane;

    private List<News> getNews(){
        ArrayList<News> newsList = new ArrayList<>();
        for (int i = 1; i <=50 ; i++) {
            News news = new News();
            news.setTitle("This is new Title"+i);
            news.setContent(getRandomLengthContent("This is news content"+i+"."));
            newsList.add(news);
        }
        return newsList;
    }

    private String getRandomLengthContent(String content){
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(content);
        }
        return builder.toString();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_title_frag, container, false);
        RecyclerView newsTitleRecyclerView = (RecyclerView) view.findViewById(R.id.newsTitleRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        newsTitleRecyclerView.setLayoutManager(linearLayoutManager);
        NewsAdapter adapter = new NewsAdapter(getNews());
        newsTitleRecyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if((getActivity()).findViewById(R.id.news_content_layout)!=null){
            isTwoPane=true;
//            能够查找到news_content_layout是双页布局
        }else{
//            不能查找到news_content——layout时是单页布局
            isTwoPane=false;
        }
    }

//    内部类NewsAdapter
    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

        private List<News> mNewsList;


        public NewsAdapter(List<News> newsList) {
        mNewsList=newsList;
        }

    //        内部类ViewHolder
      class ViewHolder extends RecyclerView.ViewHolder{
            TextView newsTitleText;
            public ViewHolder(View view){
                super(view);
                newsTitleText= (TextView) view.findViewById(R.id.news_title);
                Log.e(TAG, "view为:"+view);
                Log.e(TAG, "newsTitleText为:"+newsTitleText);
            }
        }
        //    ViewHolder内部类结束


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.news_item, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    News news = mNewsList.get(holder.getAdapterPosition());
                    if (isTwoPane){
                        NewsContentFragment newsContentFragment = (NewsContentFragment) getFragmentManager()
                                .findFragmentById(R.id.news_content_fragment);
                        newsContentFragment.refresh(news.getTitle(),news.getContent());
                    }else {
                        NewsContentActivity.actionStart(getActivity(),news.getTitle(),news.getContent());
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            News news = mNewsList.get(position);
            Log.e(TAG, "holder为:"+holder);
            Log.e(TAG, "holder.newsTitleText为:"+holder.newsTitleText);
            holder.newsTitleText.setText(news.getTitle());

        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }

    }
  //NewsAdapter内部类结束

}

package cn.edu.snnu.zc.fragmentbestpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class NewsContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_content);
//        获得传入的新闻标题和新闻内容
        String newsTitle = getIntent().getStringExtra("news_title");
        String newsContent = getIntent().getStringExtra("news_content");
        NewsContentFragment newsContentFragment = (NewsContentFragment) getSupportFragmentManager()
                .findFragmentById(R.id.news_content_fragment);
//        刷新内容界面
        Log.e("debug", "newContentFragment:"+newsContentFragment );
        Log.e("debug", "newTitle:"+newsTitle.toString() );
        Log.e("debug", "newContent:"+newsContent.toString() );
        newsContentFragment.refresh(newsTitle,newsContent);
    }

    public static void actionStart(Context context,String newsTitle,String newsContent){
//        启动NewsContentActivity活动
        Intent intent = new Intent(context, NewsContentActivity.class);
//        通过intent传递新闻标题和新闻内容
        intent.putExtra("news_title",newsTitle);
        intent.putExtra("news_content",newsContent);
//        调用活动
        context.startActivity(intent);
    }

}
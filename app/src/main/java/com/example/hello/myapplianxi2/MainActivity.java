package com.example.hello.myapplianxi2;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hello.myapplianxi2.view.fragment.Daizhifu;
import com.example.hello.myapplianxi2.view.fragment.Yiquxiao;
import com.example.hello.myapplianxi2.view.fragment.Yizhifu;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{
    private PopupMenu popupMenu;
    private TabLayout tablayout;
    private ViewPager view_pager;
    private ImageView image;
    private int itemId;
    private Yizhifu yizhifu;
    private Yiquxiao yiquxiao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        view_pager = (ViewPager) findViewById(R.id.view_pager);
        image = (ImageView) findViewById(R.id.image);

        //tablayout中的内容
        final String[] titles = new String[]{"待支付", "已支付", "已取消"};


        view_pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            private Daizhifu daizhifu1;
            private Daizhifu daizhifu;

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }

            @Override
            public Fragment getItem(int position) {
                if (titles[position] == "待支付") {
//得到子条目 因为子条目是Fragment,所以要new一个Fragment
                    daizhifu1 = new Daizhifu();
                    return daizhifu1;
                } else if (titles[position] == "已支付") {
                    yizhifu = new Yizhifu();
                    return yizhifu;
                } else if (titles[position] == "已取消") {
                    yiquxiao = new Yiquxiao();
                    return yiquxiao;
                }
                return null;
            }

            @Override
            public int getCount() {
                return titles.length;
            }
        });
        tablayout.setupWithViewPager(view_pager);

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        itemId = item.getItemId();
        if (itemId == R.id.daizhifu) {
            view_pager.setCurrentItem(0);
            Toast.makeText(MainActivity.this, "点击了待支付!", Toast.LENGTH_SHORT).show();

        } else if (itemId == R.id.yizhifu) {
            Toast.makeText(MainActivity.this, "点击了已支付!", Toast.LENGTH_SHORT).show();
            view_pager.setCurrentItem(1);
        } else if (itemId == R.id.yiquxiao) {
            view_pager.setCurrentItem(2);
            Toast.makeText(MainActivity.this, "点击了已取消!", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
    public void pop(View view) {
        popupMenu = new PopupMenu(this, image);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(this);

    }
}

package com.crown.shoppingonline.ui.viewfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.crown.shoppingonline.R;
import com.crown.shoppingonline.ui.customview.AutoScrollViewPager;
import com.crown.shoppingonline.ui.myadapter.ImageAdapter;
import com.crown.shoppingonline.ui.myadapter.ImagePagerAdapter;
import com.crown.shoppingonline.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Crown on 2016/3/22.
 */
public class HomeFragment extends Fragment {

    private AutoScrollViewPager viewPager;
    private GridView gridView;
    //private TextView indexText;

    private Button innerViewPagerDemo;

    private List<Integer> imageIdList;
    private List<Integer> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.home_fragment, null);
        //View rootView = inflater.inflate(R.layout.home_fragment, container, false);

        //TODO：二维码扫描信息
        rootView.findViewById(R.id.qrCode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //TODO:ViewPager add listener
        viewPager = (AutoScrollViewPager) rootView.findViewById(R.id.viewPager);
        initViewPager();

        //TODO:GridView add listener
        gridView = (GridView) rootView.findViewById(R.id.grid_view);
        initGridView();
        keyWordEt = (EditText) rootView.findViewById(R.id.search_key_word);
        rootView.findViewById(R.id.search_btn).setOnClickListener(searchListener);
        return rootView;
    }

    private EditText keyWordEt;
    private View.OnClickListener searchListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String keyWord = keyWordEt.getText().toString();
            //TODO: query database for searched product info

        }
    };

    public void initViewPager() {
        imageIdList = new ArrayList<Integer>();
        imageIdList.add(R.mipmap.menu_viewpager_1);
        imageIdList.add(R.mipmap.menu_viewpager_2);
        imageIdList.add(R.mipmap.menu_viewpager_3);
        imageIdList.add(R.mipmap.menu_viewpager_4);
        imageIdList.add(R.mipmap.menu_viewpager_5);
        imageIdList.add(R.mipmap.menu_viewpager_5);

        viewPager.setAdapter(new ImagePagerAdapter(getActivity(), imageIdList).setInfiniteLoop(true));
        viewPager.setInterval(2000);
        viewPager.startAutoScroll();
        viewPager.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % ListUtils.getSize(imageIdList));
    }

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
            //indexText.setText(new StringBuilder().append((position) % ListUtils.getSize(imageIdList) + 1).append("/")
            //        .append(ListUtils.getSize(imageIdList)));
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

        @Override
        public void onPageScrollStateChanged(int arg0) {}
    }

    public void initGridView() {
        list = new ArrayList<>();
        list.add(R.mipmap.menu_guide_1);
        list.add(R.mipmap.menu_guide_2);
        list.add(R.mipmap.menu_guide_3);
        list.add(R.mipmap.menu_guide_4);
        list.add(R.mipmap.menu_guide_5);
        list.add(R.mipmap.menu_guide_6);
        list.add(R.mipmap.menu_guide_7);
        list.add(R.mipmap.menu_guide_8);

        gridView.setAdapter(new ImageAdapter(getActivity(), list));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "" + position + " " + "is clicked...",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        viewPager.stopAutoScroll();
    }

    @Override
    public void onResume() {
        super.onResume();
        viewPager.startAutoScroll();
    }
}

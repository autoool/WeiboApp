package com.techidea.weiboapp.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.techidea.weiboapp.BaseFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/7/25.
 */
public class FragmentController {

    private int containerId;

    private FragmentManager fm;

    private ArrayList<Fragment> fragments;

    private static FragmentController controller;

    public static FragmentController getInstance(FragmentActivity activity,int containerId){
        if(controller == null){
            controller = new FragmentController(activity,containerId);
        }
        return controller;
    }

    private FragmentController(FragmentActivity activity,int containerId){
        this.containerId = containerId;
        fm = activity.getSupportFragmentManager();
        initFragment();
    }

    private void initFragment(){
        fragments = new ArrayList<Fragment>();
        fragments.add(new HomeFragment());
        fragments.add(new MessageFragment());
        fragments.add(new SearchFragment());
        fragments.add(new User2Fragment());

        FragmentTransaction ft = fm.beginTransaction();
        for(Fragment fragment : fragments) {
            ft.add(containerId, fragment);
        }
        ft.commit();
    }

    //
    public void showFragment(int position) {
        FragmentTransaction ft = fm.beginTransaction();

        //hide all
        for(Fragment fragment : fragments) {
            if(fragment != null) {
                ft.hide(fragment);
            }
        }

        //show one
        Fragment fragment = fragments.get(position);
        ft.show(fragment);
        ft.commit();

    }

    public void replaceFragment(int position){
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(containerId, fragments.get(position));
        ft.commit();
    }

    public Fragment getFragment(int position) {
        return fragments.get(position);
    }
}

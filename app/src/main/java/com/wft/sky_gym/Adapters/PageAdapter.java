package com.wft.sky_gym.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.wft.sky_gym.Fragment.MemberMembershipFragment;
import com.wft.sky_gym.Fragment.MemberProfileFragment;

import java.util.ArrayList;
import java.util.List;

public class PageAdapter extends FragmentPagerAdapter {

    private int tabss;

    public PageAdapter(FragmentManager fm, int tabss) {
        super(fm);
        this.tabss = tabss;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MemberProfileFragment();
            case 1:
                return new MemberMembershipFragment();


            default:
                return null;


        }
    }

    @Override
    public int getCount() {
        return tabss;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
//}
//
//
//private static int count = 2;
//    private final List<Fragment> mFragmentList = new ArrayList<>();
//
//    public void addFragment(Fragment fragment) {
//        mFragmentList.add(fragment);
//
//    }
//    public PageAdapter(FragmentManager fm, int tabCount) {
//        super(fm);
//    }
//
//    @Override
//    public Fragment getItem(int position) {
//
//        switch (position) {
//            case 0:
//                return new MemberProfileFragment();
//            case 1:
//                return new MemberMembershipFragment();
//        }
//        return null;
//    }
//
//    @Override
//    public int getCount() {
//        return count;
//    }
//
////    @Override
////    public CharSequence getPageTitle(int position) {
////        switch (position)
////        {
////            case 0 :
////                return "Tab One";
////            case 1 :
////                return "Tab Two";
////        }
////        return null;
////    }
//}

package com.example.do_an.DesignPattern;

import androidx.fragment.app.Fragment;
import com.example.do_an.fragment.HomeFragment;
import com.example.do_an.fragment.SettingFragment;
import com.example.do_an.fragment.TransHisFragment;
import com.example.do_an.fragment.UuDaiFragment;
import com.example.do_an.R;
public class FragmentFactory {
    public static Fragment getFragment(int itemId) {
        switch (itemId) {
            case R.id.mnHome:
                return new HomeFragment();
            case R.id.mnUuDai:
                return new UuDaiFragment();
            case R.id.LSGD:
                return new TransHisFragment();
            case R.id.mnViCuaToi:
                return new SettingFragment();
            default:
                throw new IllegalArgumentException("Menu Item Not Supported");
        }
    }
}

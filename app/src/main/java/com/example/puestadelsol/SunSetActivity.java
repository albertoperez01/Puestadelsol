package com.example.puestadelsol;


import androidx.fragment.app.Fragment;


public class SunSetActivity extends SingleFragmentActivity
{

    @Override
    protected Fragment createFragment()
    {
        return SunsetFragment.newInstance();
    }
}
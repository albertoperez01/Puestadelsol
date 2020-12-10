package com.example.puestadelsol;

import android.os.Build;
import android.os.Bundle;
import android.widget.Toolbar;

import androidx.annotation.LayoutRes;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public abstract class SingleFragmentActivity extends AppCompatActivity
{
    protected abstract Fragment createFragment();

    @LayoutRes
    protected int getLayoutResId()
    {
        return R.layout.activity_fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate (Bundle savedInstanceState)
    {
        Toolbar toolbar = findViewById(R.id.toolbar);
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fragment);
        setContentView(getLayoutResId());

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null)
        {
            fragment = createFragment();
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }
    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.) {
            Toast.makeText(this,"ANIMATION",Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id == R.id.){
            Intent intent = new Intent(this,.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
     */
}




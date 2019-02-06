package juniorbraga.com.br.spents.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import juniorbraga.com.br.spents.R;
import juniorbraga.com.br.spents.fragment.HomeFragment;

public class HomeActivity extends CustomToolbarActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private Fragment selectedFragment = HomeFragment.newInstance();
    private BottomNavigationView navigation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.setResLayoutId(R.layout.activity_home);
        super.onCreate(savedInstanceState);
        super.hideBackSpace();

        this.navigation = (BottomNavigationView) findViewById(R.id.navigation);
        this.showFragment();
        this.navigation.setOnNavigationItemSelectedListener(this);
    }

    private void showFragment(){
        getSupportFragmentManager().beginTransaction().replace(R.id.content, selectedFragment).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                selectedFragment = HomeFragment.newInstance();
                return true;
//            case R.id.navigation_dashboard:
////                    mTextMessage.setText(R.string.title_dashboard);
//                return true;
//            case R.id.navigation_notifications:
////                    mTextMessage.setText(R.string.title_notifications);
//                return true;
        }
        this.showFragment();
        return false;
    }
}


package jp.ddo.trismegistos.nicopic.listener;

import jp.ddo.trismegistos.nicopic.R;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;

/**
 * タブ切り替え時のリスナークラス。
 * 
 * @author y_sugasawa
 * @since 2012/12/30
 */
public class TabListener<T extends Fragment> implements ActionBar.TabListener {

    private Fragment fragment;
    private SherlockFragmentActivity activity;
    private String tag;
    private final Class<T> clz;

    /**
     * コンストラクタ。
     * 
     * @param activity
     * @param tag
     * @param clz
     */
    public TabListener(final SherlockFragmentActivity activity, final String tag, final Class<T> clz) {
        this.activity = activity;
        this.tag = tag;
        this.clz = clz;

        this.fragment = activity.getSupportFragmentManager().findFragmentByTag(tag);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onTabSelected(final Tab tab, final FragmentTransaction ft) {
        if (fragment == null) {
            fragment = Fragment.instantiate(activity, clz.getName());
            final FragmentManager fm = activity.getSupportFragmentManager();
            fm.beginTransaction().add(R.id.container, fragment, tag).commit();
        } else if (fragment.isDetached()) {
            final FragmentManager fm = activity.getSupportFragmentManager();
            fm.beginTransaction().attach(fragment).commit();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onTabUnselected(final Tab tab, final FragmentTransaction ft) {
        if (fragment != null) {
            Log.e("onTabUnselected", fragment.getClass().getSimpleName());
            final FragmentManager fm = activity.getSupportFragmentManager();
            fm.beginTransaction().detach(fragment).commit();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }
}

package tapales.manto.bhuller.loot;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter{
    CharSequence Titles[];
    int NumbOfTabs;
    public ViewPagerAdapter(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb){
        super(fm);
        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }
    public Fragment getItem(int position){
        if(position == 0){
            ExpenseList eList = new ExpenseList();
            return eList;
        }
        else if(position == 1){
            IncomeList iList = new IncomeList();
            return iList;
        }
        else{
            ExpenseList eList = new ExpenseList();
            return eList;
        }
    }
    public CharSequence getPageTitle(int position){
        return Titles[position];
    }
    public int getCount(){
        return NumbOfTabs;
    }
}
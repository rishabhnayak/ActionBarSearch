package in.rishabhnayak.actionbarsearch;

import android.app.SearchManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Menu menu;
    List<String> mainlist,list;
    ArrayAdapter<String> test;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         list = new ArrayList<String>();
        list.add("abc");
        list.add("abcd");
        list.add("abcde");
        list.add("abcdef");
        list.add("xyz");
        list.add("xzy");
        mainlist=new ArrayList<>();


        listView=findViewById(R.id.list_item);
         test = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,mainlist);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_view_menu_item, menu);

        this.menu = menu;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

            SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

            android.support.v7.widget.SearchView search = (android.support.v7.widget.SearchView) menu.findItem(R.id.action_search).getActionView();

            search.setSearchableInfo(manager.getSearchableInfo(getComponentName()));

            search.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {

                    return false;
                }

                @Override
                public boolean onQueryTextChange(String changedText) {
                    mainlist.clear();
                    for (String text:list) {
                        String newtext = text.toLowerCase();
                        if (newtext.startsWith(changedText)) {
                            mainlist.add(text);
                        }
                    }
                    listView.setAdapter(test);
                    return false;
                }

            });

        }
        return true;
    }
}



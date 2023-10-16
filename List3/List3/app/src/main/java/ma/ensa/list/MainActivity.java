package ma.ensa.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import ma.ensa.list.adapter.StarAdapter;
import ma.ensa.list.beans.Star;
import ma.ensa.list.service.StarService;
import androidx.core.app.ShareCompat;


public class MainActivity extends AppCompatActivity {
    private List<Star> stars;
    private RecyclerView recyclerView;
    private StarAdapter starAdapter = null;

    private StarService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        stars = new ArrayList<>();
        service = StarService.getInstance();
        init();

        recyclerView = findViewById(R.id.recycle_view);

        starAdapter = new StarAdapter(this, service.findAll());
        recyclerView.setAdapter(starAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (starAdapter != null) {
                    starAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.share) {
            String shareText = "Partagez votre texte ici"; // Remplacez par le texte que vous souhaitez partager
            shareText(shareText);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void shareText(String textToShare) {
        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText(textToShare)
                .getIntent();
        if (shareIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(shareIntent);
        }
    }


    public void init() {
        service.create(new Star("ROA EL DHIMNI", "https://media.licdn.com/dms/image/D4E03AQFRGhzwy2brWg/profile-displayphoto-shrink_800_800/0/1675349749975?e=2147483647&v=beta&t=1JBgiz-7nyFE_RqYWIBuFWFdWxPy1jLLxECT9_C3o8Q", 5));
        service.create(new Star("HAJAR MACHMOUM", "https://media.licdn.com/dms/image/D4E03AQExJd-d-0hzYg/profile-displayphoto-shrink_800_800/0/1686318034456?e=2147483647&v=beta&t=7TpfVPHdmLZqbPUW-OFCXZUE3ZxdkVev5q5pn3jLbAA", 5));
        service.create(new Star("SAFIYA DAOUDI", "https://media.licdn.com/dms/image/D4E03AQENEjbcx13cMQ/profile-displayphoto-shrink_800_800/0/1675886579622?e=1702512000&v=beta&t=5ITI__5EaqMBLyZsGGxmgj60JQksTHdCQJQdVLFptaU", 5));
        service.create(new Star("ZAKARIYA ELHARCHI", "https://media.licdn.com/dms/image/D4E03AQF1Yaux5K2Rmg/profile-displayphoto-shrink_800_800/0/1684540330852?e=1702512000&v=beta&t=AHCO7IVNVjazrQJQBeTTe0lh2IzgCFIPFx5nTJP7B6A", 3));
        service.create(new Star("YOUSSEF ERRASEKH", "https://media.licdn.com/dms/image/D4E35AQGKTMKebqdTkg/profile-framedphoto-shrink_800_800/0/1684715709088?e=1697842800&v=beta&t=4ZkrXEBkgmrmQ_sTXj2hgUrMo-e9Ryv7FQVYITnHxZ4",4));
        service.create(new Star("ILYASS BENNANE", "https://media.licdn.com/dms/image/D4E03AQHRCjcjqgAiig/profile-displayphoto-shrink_800_800/0/1687186706269?e=2147483647&v=beta&t=XQI-TqGbH8DX-2076FBeGV2VhEi_FPaubU-4jVSEYfE", 2));




       }
}
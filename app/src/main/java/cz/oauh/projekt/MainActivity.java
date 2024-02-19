package cz.oauh.projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar));
        db = new DBHelper(this);
        if(db.vratPocetZaznamuCviku() == 0)
        {
         db.nastavCviky();
         System.out.println("Vložil jsem 3 záznamy");
        }
        TextView tw = findViewById(R.id.pokus);
        tw.setText("");
        Cursor data = db.vratCviky();
        data.moveToFirst();
        while(!data.isAfterLast())
        {
         tw.append(data.getString(1) + " ");
         data.moveToNext();
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_write) {
           Intent addData = new Intent(this, AddDataActivity.class);
           startActivity(addData);
            return true;
        } else
        if (id == R.id.action_calendar) {
            Snackbar.make(findViewById(android.R.id.content),
                    "Klikl jsem na kalendář", Snackbar.LENGTH_LONG).show();
            return true;
        }
        else
        if (id == R.id.action_statistic) {
            Snackbar.make(findViewById(android.R.id.content),
                    "Chci se púodívat na statistiku", Snackbar.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
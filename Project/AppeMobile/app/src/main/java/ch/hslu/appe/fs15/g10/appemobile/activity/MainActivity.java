package ch.hslu.appe.fs15.g10.appemobile.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import ch.hslu.appe.fs15.g10.appemobile.Constants;
import ch.hslu.appe.fs15.g10.appemobile.R;
import ch.hslu.appe.fs15.g10.appemobile.dto.RequestSecurityContext;

/**
 * Created by Simon on 25.04.2015.
 */
public class MainActivity extends BaseActivity {

    private Button orderButton;
    private Button articlesButton;
    private Button usersButton;
    private Button replenishmentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        orderButton = (Button) findViewById(R.id.main_order);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOrderOverview();
            }
        });
        articlesButton = (Button) findViewById(R.id.main_article);
        articlesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotImplemented();
            }
        });
        usersButton = (Button) findViewById(R.id.main_users);
        usersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotImplemented();
            }
        });
        replenishmentButton = (Button) findViewById(R.id.main_replenishment);
        replenishmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotImplemented();
            }
        });
    }

    private void disableButtons() {
        orderButton.setEnabled(false);
        articlesButton.setEnabled(false);
        usersButton.setEnabled(false);
        replenishmentButton.setEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        RequestSecurityContext requestSecurityContext = getRequestSecurityContext();
        disableButtons();
        if (requestSecurityContext != null) {

            if (requestSecurityContext.isUserInRole(Constants.Roles.Admin)) {

                usersButton.setEnabled(true);
            } else if (requestSecurityContext.isUserInRole(Constants.Roles.Datatypist)) {

                articlesButton.setEnabled(true);
            } else if (requestSecurityContext.isUserInRole(Constants.Roles.Manager)) {

                replenishmentButton.setEnabled(true);
            } else if (requestSecurityContext.isUserInRole(Constants.Roles.Sales)) {

                orderButton.setEnabled(true);
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.main_menu_login) {
            showLoginActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}

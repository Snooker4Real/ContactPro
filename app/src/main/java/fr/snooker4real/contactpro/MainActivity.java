package fr.snooker4real.contactpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.snooker4real.contactpro.adapters.ContactAdapter;
import fr.snooker4real.contactpro.dao.ContactDAO;
import fr.snooker4real.contactpro.model.Contact;

public class MainActivity extends AppCompatActivity {

    private ContactDAO contactDAO;
    private List<Contact> contacts = new ArrayList<>();
    private RecyclerView recyclerView;
    private ContactAdapter contactAdapter;

    public static final String KEY_CONTACT = "myContact";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // crée l'objet DAO
        contactDAO = new ContactDAO(getApplicationContext());

        // récupère les éléments
        recyclerView = findViewById(R.id.rvContact);

        // crée et affecte le LayoutManager au recyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
    }

    // Crée le menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Gère le menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // déclare un objet Intent
        Intent intent = null;

        if (item.getItemId() == R.id.item_add) {
            // appel l'activité AddYoutubeActivity
            intent = new Intent(this, AddContactActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // appel la méthode qui lance la tache Async
        refreshContact();
    }

    // lance la tache Async
    private void refreshContact() {
        ContactAsyncTasks contactAsyncTasks = new ContactAsyncTasks();
        contactAsyncTasks.execute();
    }

    // tache Async pour récupérer la liste de Contact à partir de la base
    public class ContactAsyncTasks extends AsyncTask<String, String, List<Contact>> {

        protected List<Contact> doInBackground(String... strings) {

            List<Contact> updateContacts = new ArrayList<>();

            try {
                updateContacts = contactDAO.list();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return updateContacts;
        }

        @Override
        protected void onPostExecute(List<Contact> updateContacts) {

            // appel la méthode qui met à jour le RecyclerView
            updateContactAdapter(updateContacts);
        }
    }

    // factorisation de la mise à jour du RecyclerView
    private void updateContactAdapter(List<Contact> updateContacts) {

        // modifie l'attribut contacts avec celui récupéré en paramètre
        contacts = updateContacts;
        // crée ContactAdapter avec en deuxième paramètre la création d'un objet suivant l'interface OnNomClickListener
        // déclarée dans ContactAdapter
        contactAdapter = new ContactAdapter(contacts, new ContactAdapter.OnNomClickListener() {

            // implémente la méthode onNomClick de l'interface OnNomClickListener
            @Override
            public void onNomClick(Contact contact) {
                Toast.makeText(getApplicationContext(), "Item clicked", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                //intent.putExtra();
                //startActivity(intent);
            }
        });
        recyclerView.setAdapter(contactAdapter);


    }
}
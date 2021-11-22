package fr.snooker4real.contactpro;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.snooker4real.contactpro.dao.ContactDAO;
import fr.snooker4real.contactpro.model.Contact;

public class AddContactActivity extends AppCompatActivity {

    // variables des éléments de la vue
    private Button btnAdd;
    private Button btnCancel;
    private EditText etPrenom;
    private EditText etNom;
    private EditText etSociete;
    private EditText etAdresse;
    private EditText etTelephone;
    private EditText etEmail;
    private EditText etFavori;
    private Spinner sprSecteur;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        // ajoute le retour dans la barre d'action
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // récupère les éléments de la vue
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
        etPrenom = findViewById(R.id.etPrenom);
        etNom = findViewById(R.id.etNom);
        etSociete = findViewById(R.id.etSociete);
        etAdresse = findViewById(R.id.etAdresse);
        etTelephone = findViewById(R.id.etTelephone);
        etEmail = findViewById(R.id.etEmail);
        sprSecteur = findViewById(R.id.sprSecteur);
        etFavori = findViewById(R.id.etFavori);

        // créer la liste qui sera affichée par le spinner
        String[] lesSecteurs = new String[]{
                "Industrie",
                "Energie",
                "Matériels",
                "Santé",
                "Finance",
                "Technologies de l'information",
                "Télécommunication",
                "Services aux collectivités",
                "Consommation discrétionnaire"
        };
        final List<String> lesSecteursList = new ArrayList<>(Arrays.asList(lesSecteurs));

        // crée l'adapter pour le spinner
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(
                this, R.layout.spinner_secteur, lesSecteursList
        );

        // ajoute l'adapter au spinner
        sprSecteur.setAdapter(spinnerArrayAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prenom = etPrenom.getText().toString();
                String nom = etNom.getText().toString();
                String societe = etSociete.getText().toString();
                String adresse = etAdresse.getText().toString();
                String telephone = etTelephone.getText().toString();
                String email = etEmail.getText().toString();
                String selectedSecteur = sprSecteur.getSelectedItem().toString();
                int favori = Integer.parseInt(etFavori.getText().toString());

                if (prenom.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Entrez un prénom", Toast.LENGTH_SHORT).show();
                } else if (nom.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Entrez un nom", Toast.LENGTH_SHORT).show();
                } else if (societe.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Entrez une société", Toast.LENGTH_SHORT).show();
                } else if (adresse.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Entrez une adresse", Toast.LENGTH_SHORT).show();
                } else if (telephone.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Entrez un téléphone", Toast.LENGTH_SHORT).show();
                } else {
                    // enregistre dans la bdd avec le DAO
                    Contact contact = new Contact();
                    contact.setPrenom(prenom);
                    contact.setNom(nom);
                    contact.setSociete(societe);
                    contact.setAdresse(adresse);
                    contact.setTelephone(telephone);
                    contact.setEmail(email);
                    contact.setSecteur(selectedSecteur);
                    contact.setFavori(favori);

                    ContactDAO contactDAO = new ContactDAO(getApplicationContext());
                    contactDAO.add(contact);

                    // termine l'activity et revient à la main
                    finish();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // termine l'activity et revient à la main
                finish();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        // termine cette activity si le bouton retour est sélectionné
        finish();
        return true;
    }
}

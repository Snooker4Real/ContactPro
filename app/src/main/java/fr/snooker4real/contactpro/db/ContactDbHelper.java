package fr.snooker4real.contactpro.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class ContactDbHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "contacts.db";

    // nom de la table
    public static final String CONTACT_TABLE_NAME = "Contact";

    // déclare les index des colonnes
    public static final int CONTACT_KEY_COLUMN_INDEX = 0;
    public static final int CONTACT_NAME_COLUMN_INDEX = 1;
    public static final int CONTACT_PRENOM_COLUMN_INDEX = 2;
    public static final int CONTACT_SOCIETY_COLUMN_INDEX = 3;
    public static final int CONTACT_ADRESSE_COLUMN_INDEX = 4;
    public static final int CONTACT_TELEPHONE_COLUMN_INDEX = 5;
    public static final int CONTACT_EMAIL_COLUMN_INDEX = 6;
    public static final int CONTACT_SECTEUR_COLUMN_INDEX = 7;
    public static final int CONTACT_FAVORI_COLUMN_INDEX = 8;

    // déclare les colonnes
    public static final String CONTACT_KEY = "id";
    public static final String CONTACT_NAME = "nom";
    public static final String CONTACT_PRENOM = "prenom";
    public static final String CONTACT_SOCIETY = "societe";
    public static final String CONTACT_ADRESSE = "adresse";
    public static final String CONTACT_TELEPHONE = "telephone";
    public static final String CONTACT_EMAIL = "email";
    public static final String CONTACT_SECTEUR = "secteur";
    public static final String CONTACT_FAVORI = "favori";

    // requete de création de la table
    private static final String CONTACT_TABLE_CREATE =
            "CREATE TABLE " + CONTACT_TABLE_NAME + " (" +
                    CONTACT_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CONTACT_NAME + " TEXT, " +
                    CONTACT_PRENOM + " TEXT, " +
                    CONTACT_SOCIETY + " TEXT, " +
                    CONTACT_ADRESSE + " TEXT, " +
                    CONTACT_TELEPHONE + " TEXT, " +
                    CONTACT_EMAIL + " TEXT, " +
                    CONTACT_SECTEUR + " TEXT, " +
                    CONTACT_FAVORI + " INTEGER);";

    private static final String CONTACT_TABLE_DROP = "DROP TABLE IF EXISTS " + CONTACT_TABLE_NAME + ";";

    public ContactDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    // Crée la Base et la table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CONTACT_TABLE_CREATE);
    }

    // update la base (pas implémentée)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CONTACT_TABLE_DROP);
        onCreate(db);
    }
}

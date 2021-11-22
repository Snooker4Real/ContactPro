package fr.snooker4real.contactpro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import fr.snooker4real.contactpro.db.ContactDbHelper;
import fr.snooker4real.contactpro.model.Contact;

public class ContactDAO extends DAO {

    public ContactDAO(Context context) {
        super(new ContactDbHelper(context));
    }

    // récupère un objet Contact à partir de l'id
    public Contact find(Long id) {
        Contact contact = null;

        Cursor cursor = db.rawQuery("select * from " + ContactDbHelper.CONTACT_TABLE_NAME +
                " where " + ContactDbHelper.CONTACT_KEY + " = ?", new String[]{String.valueOf(id)});

        if (cursor != null & cursor.moveToFirst()) {
            contact = new Contact();

            contact.setId(Long.parseLong(cursor.getString(ContactDbHelper.CONTACT_KEY_COLUMN_INDEX)));
            contact.setNom(cursor.getString(ContactDbHelper.CONTACT_NAME_COLUMN_INDEX));
            contact.setPrenom(cursor.getString(ContactDbHelper.CONTACT_PRENOM_COLUMN_INDEX));
            contact.setSociete(cursor.getString(ContactDbHelper.CONTACT_SOCIETY_COLUMN_INDEX));
            contact.setAdresse(cursor.getString(ContactDbHelper.CONTACT_ADRESSE_COLUMN_INDEX));
            contact.setTelephone(cursor.getString(ContactDbHelper.CONTACT_TELEPHONE_COLUMN_INDEX));
            contact.setEmail(cursor.getString(ContactDbHelper.CONTACT_EMAIL_COLUMN_INDEX));
            contact.setSecteur(cursor.getString(ContactDbHelper.CONTACT_SECTEUR_COLUMN_INDEX));
            contact.setFavori(Integer.parseInt(cursor.getString(ContactDbHelper.CONTACT_FAVORI_COLUMN_INDEX)));

            cursor.close();
        }

        close();

        return contact;
    }

    // Ajoute un objet Contact
    public Contact add(Contact contact) {

        open();

        ContentValues value = new ContentValues();

        value.put(ContactDbHelper.CONTACT_NAME, contact.getNom());
        value.put(ContactDbHelper.CONTACT_PRENOM, contact.getPrenom());
        value.put(ContactDbHelper.CONTACT_SOCIETY, contact.getSociete());
        value.put(ContactDbHelper.CONTACT_ADRESSE, contact.getAdresse());
        value.put(ContactDbHelper.CONTACT_TELEPHONE, contact.getTelephone());
        value.put(ContactDbHelper.CONTACT_EMAIL, contact.getEmail());
        value.put(ContactDbHelper.CONTACT_SECTEUR, contact.getSecteur());
        value.put(ContactDbHelper.CONTACT_FAVORI, contact.getFavori());

        long id = db.insert(ContactDbHelper.CONTACT_TABLE_NAME, null, value);

        // récupère l'id et met à jour l'objet Contact qui a été ajouté
        contact.setId(id);

        close();

        return contact;
    }

    // récupère une liste d'objets Contact
    public List<Contact> list() {
        open();

        List<Contact> contacts = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from " +
                ContactDbHelper.CONTACT_TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Contact contact = new Contact();

                contact.setId(Long.parseLong(cursor.getString(ContactDbHelper.CONTACT_KEY_COLUMN_INDEX)));
                contact.setNom(cursor.getString(ContactDbHelper.CONTACT_NAME_COLUMN_INDEX));
                contact.setPrenom(cursor.getString(ContactDbHelper.CONTACT_PRENOM_COLUMN_INDEX));
                contact.setSociete(cursor.getString(ContactDbHelper.CONTACT_SOCIETY_COLUMN_INDEX));
                contact.setAdresse(cursor.getString(ContactDbHelper.CONTACT_ADRESSE_COLUMN_INDEX));
                contact.setTelephone(cursor.getString(ContactDbHelper.CONTACT_TELEPHONE_COLUMN_INDEX));
                contact.setEmail(cursor.getString(ContactDbHelper.CONTACT_EMAIL_COLUMN_INDEX));
                contact.setSecteur(cursor.getString(ContactDbHelper.CONTACT_SECTEUR_COLUMN_INDEX));
                contact.setFavori(Integer.parseInt(cursor.getString(ContactDbHelper.CONTACT_FAVORI_COLUMN_INDEX)));

                contacts.add(contact);

                cursor.moveToNext();
            }
        }

        cursor.close();
        close();

        return contacts;
    }

}

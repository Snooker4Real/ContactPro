package fr.snooker4real.contactpro.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.snooker4real.contactpro.R;
import fr.snooker4real.contactpro.model.Contact;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    // sock la liste contacts
    private List<Contact> contacts;
    // stock CLickListener qui sera passé en paramètre pour traiter le click sur le nom ??
    private OnNomClickListener onNomClickListener;

    // classe qui va gérer l'affichage d'un item
    public class ContactViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNom;
        private TextView tvPrenom;
        private TextView tvSociete;
//        private TextView tvAdresse;
//        private TextView tvTelephone;
//        private TextView tvEmail;
//        private TextView tvSecteur;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);

            // on récupère les éléments de la vue avec findViewByID
            tvNom = itemView.findViewById(R.id.tvNom);
            tvPrenom = itemView.findViewById(R.id.tvPrenom);
            tvSociete = itemView.findViewById(R.id.tvSociete);
//            tvAdresse = itemView.findViewById(R.id.tvAdresse);
//            tvTelephone = itemView.findViewById(R.id.tvTelephone);
//            tvEmail = itemView.findViewById(R.id.tvEmail);
//            tvSecteur = itemView.findViewById(R.id.tvSecteur);
        }
    }

    public ContactAdapter(List<Contact> contacts, OnNomClickListener onNomClickListener) {
        // on enregistre la liste dans notre attribut contacts
        this.contacts = contacts;

        // on enregistre le ClickListener dans notre attribut onNomClickListener
        this.onNomClickListener = onNomClickListener;
    }

    // crée la view pour l'item


    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);

        // retourne notre objet ContactViewHolder qui gère un item
        return new ContactViewHolder(view);
    }

    // Binding : on crée une liaison entre les données qui se trouvent dans un Contact (nom, prenom, société, adresse, téléphone, email, secteur)
    // avec l'affichage dans un item (avec les TextView tvNom, tvPrenom, tvSociete, tvAdresse, tvTelephone, tvEmail, tvSecteur)
    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        // récupérer le contact en cours par rapport à la position
        final  Contact contact = contacts.get(position);

        // on bind
        // à gauche la donnée/ à droite la vue
        holder.tvNom.setText(contact.getNom());
        holder.tvPrenom.setText(contact.getPrenom());
        holder.tvSociete.setText(contact.getSociete());
//        holder.tvAdresse.setText(contact.getAdresse());
//        holder.tvTelephone.setText(contact.getTelephone());
//        holder.tvEmail.setText(contact.getEmail());
//        holder.tvSecteur.setText(contact.getSecteur());

        if (onNomClickListener != null) {
            // ajoute le ClickListener sur Nom
            holder.tvNom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNomClickListener.onNomClick(contact);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    // Déclare l'interface qui devra être implémenté pour gérer le click sur le nom
    // Elle sera constitué d'une méthode onNomClick qui aura en paramètre un objet contact
    public interface OnNomClickListener {
        void onNomClick(Contact contact);
    }

}

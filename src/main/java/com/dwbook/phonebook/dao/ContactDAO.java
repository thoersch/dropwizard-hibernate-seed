package com.dwbook.phonebook.dao;

import com.dwbook.phonebook.representations.Contact;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.skife.jdbi.v2.sqlobject.Bind;

public class ContactDAO extends AbstractDAO<Contact> {

    public ContactDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Contact getContactById(int id) {
        return get(id);
    }

    public int createContact(Contact contact) {
        return persist(contact).getId();
    }

    public void updateContact(@Bind("id") int id, @Bind("firstName") String firstName, @Bind("lastName") String lastName, @Bind("phone") String phone) {
        Contact contact = new Contact(id, firstName, lastName, phone);
        persist(contact);
    }

    public void deleteContact(@Bind("id") int id) {
        deleteContact(id);
    }
}

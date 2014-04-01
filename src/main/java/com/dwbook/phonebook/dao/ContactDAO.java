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

    public void updateContact(Contact contact) {
        persist(contact);
    }

    public void deleteContact(@Bind("id") int id) {
        currentSession().delete(get(id));
    }
}

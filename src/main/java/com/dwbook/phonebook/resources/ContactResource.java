package com.dwbook.phonebook.resources;

import com.dwbook.phonebook.dao.ContactDAO;
import com.dwbook.phonebook.representations.Contact;
import io.dropwizard.hibernate.UnitOfWork;

import java.net.URI;
import java.net.URISyntaxException;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/contact")
@Produces(MediaType.APPLICATION_JSON)
public class ContactResource {
    private final ContactDAO dao;

    public ContactResource(ContactDAO dao) {
        this.dao = dao;
    }

    @GET
    @UnitOfWork
    @Path("/{id}")
    public Response getContact(@PathParam("id") int id) {
        return Response.ok(
                dao.getContactById(id)
        ).build();
    }

    @POST
    @UnitOfWork
    public Response createContact(@Valid Contact contact) throws URISyntaxException {
        int newContactId = dao.createContact(contact);
        return Response.created(new URI(String.valueOf(newContactId))).build();
    }

    @DELETE
    @UnitOfWork
    @Path("/{id}")
    public Response deleteContact(@PathParam("id") int id) {
        dao.deleteContact(id);
        return Response.noContent().build();
    }

    @PUT
    @UnitOfWork
    @Path("/{id}")
    public Response updateContact(@Valid Contact contact) {
        dao.updateContact(contact);
        return Response.ok(new Contact(contact.getId(), contact.getFirstName(), contact.getLastName(), contact.getPhone())).build();
    }
}
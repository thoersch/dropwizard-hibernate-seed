package com.dwbook.phonebook;

import com.dwbook.phonebook.dao.ContactDAO;
import com.dwbook.phonebook.representations.Contact;
import com.dwbook.phonebook.resources.ContactResource;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.DatabaseConfiguration;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class App extends Application<PhonebookConfiguration> {

	private static final Logger logger = LoggerFactory.getLogger(App.class);
    private final HibernateBundle<PhonebookConfiguration> hibernate = new HibernateBundle<PhonebookConfiguration>(Contact.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(PhonebookConfiguration phonebookConfiguration) {
            return phonebookConfiguration.getDatabase();
        }
    };

    public static void main( String[] args ) throws Exception {
        new App().run(args);
    }

    @Override
    public void initialize(final Bootstrap<PhonebookConfiguration> phonebookConfigurationBootstrap) {
        phonebookConfigurationBootstrap.addBundle(hibernate );
        phonebookConfigurationBootstrap.addBundle(new MigrationsBundle<PhonebookConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(PhonebookConfiguration phonebookConfiguration) {
                return phonebookConfiguration.getDatabase();
            }
        });
    }

    @Override
    public void run(PhonebookConfiguration phonebookConfiguration, Environment environment) throws Exception {
        logger.info("run() called");
        environment.jersey().register(new ContactResource(new ContactDAO(hibernate.getSessionFactory())));
    }
}

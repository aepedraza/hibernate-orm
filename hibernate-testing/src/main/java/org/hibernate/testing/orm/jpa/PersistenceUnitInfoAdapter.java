/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or http://www.gnu.org/licenses/lgpl-2.1.html
 */
package org.hibernate.testing.orm.jpa;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import jakarta.persistence.SharedCacheMode;
import jakarta.persistence.ValidationMode;
import jakarta.persistence.spi.ClassTransformer;
import jakarta.persistence.spi.PersistenceUnitInfo;
import jakarta.persistence.spi.PersistenceUnitTransactionType;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;

/**
 * Implementation of {@link PersistenceUnitInfo} for testing use.
 *
 * Expected usage is to override methods relevant to their specific tests.
 *
 * See {@link PersistenceUnitInfoImpl} for a more bean-like implementation
 *
 * @author Steve Ebersole
 */
public class PersistenceUnitInfoAdapter implements PersistenceUnitInfo {
	private final String name = "persistenceUnitInfoAdapter@" + System.identityHashCode( this );
	private Properties properties;

	public String getPersistenceUnitName() {
		return name;
	}

	public String getPersistenceProviderClassName() {
		return HibernatePersistenceProvider.class.getName();
	}

	@Override
	public String getScopeAnnotationName() {
		return null;
	}

	@Override
	public List<String> getQualifierAnnotationNames() {
		return List.of();
	}

	public PersistenceUnitTransactionType getTransactionType() {
		return null;
	}

	public DataSource getJtaDataSource() {
		return null;
	}

	public DataSource getNonJtaDataSource() {
		return null;
	}

	public List<String> getMappingFileNames() {
		return Collections.emptyList();
	}

	public List<URL> getJarFileUrls() {
		return Collections.emptyList();
	}

	public URL getPersistenceUnitRootUrl() {
		return null;
	}

	public List<String> getManagedClassNames() {
		return Collections.emptyList();
	}

	public boolean excludeUnlistedClasses() {
		return false;
	}

	public SharedCacheMode getSharedCacheMode() {
		return null;
	}

	public ValidationMode getValidationMode() {
		return null;
	}

	public Properties getProperties() {
		if ( properties == null ) {
			properties = new Properties();
		}
		return properties;
	}

	public String getPersistenceXMLSchemaVersion() {
		return null;
	}

	public ClassLoader getClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}

	public void addTransformer(ClassTransformer transformer) {
	}

	public ClassLoader getNewTempClassLoader() {
		return null;
	}
}

/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.jpa.boot.spi;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.hibernate.Internal;
import org.hibernate.boot.registry.classloading.spi.ClassLoaderService;
import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;
import org.hibernate.jpa.boot.internal.PersistenceUnitInfoDescriptor;

import jakarta.persistence.spi.PersistenceUnitInfo;
import jakarta.persistence.spi.PersistenceUnitTransactionType;

/**
 * Entry into the bootstrap process.
 *
 * @author Steve Ebersole
 * @author Brett Meyer
 */
public final class Bootstrap {
	private Bootstrap() {
	}

	public static EntityManagerFactoryBuilder getEntityManagerFactoryBuilder(
			PersistenceUnitDescriptor persistenceUnitDescriptor,
			Map integration) {
		return new EntityManagerFactoryBuilderImpl( persistenceUnitDescriptor, integration );
	}

	/**
	 * Intended for use in Hibernate tests
	 *
	 * @param persistenceXmlUrl The URL to a persistence.xml
	 * @param persistenceUnitName The name of the persistence-unit to parse
	 * @param integration setting overrides
	 *
	 * @return The EMFB
	 */
	public static EntityManagerFactoryBuilder getEntityManagerFactoryBuilder(
			URL persistenceXmlUrl,
			String persistenceUnitName,
			Map integration) {
		return getEntityManagerFactoryBuilder( persistenceXmlUrl, persistenceUnitName, PersistenceUnitTransactionType.RESOURCE_LOCAL, integration );
	}

	/**
	 * Intended for use in Hibernate tests
	 *
	 * @param persistenceXmlUrl The URL to a persistence.xml
	 * @param persistenceUnitName The name of the persistence-unit to parse
	 * @param integration setting overrides
	 *
	 * @return The EMFB
	 */
	public static EntityManagerFactoryBuilder getEntityManagerFactoryBuilder(
			URL persistenceXmlUrl,
			String persistenceUnitName,
			@SuppressWarnings("removal")
			PersistenceUnitTransactionType transactionType,
			Map integration) {
		return new EntityManagerFactoryBuilderImpl(
				PersistenceXmlParser.create( integration ).parse( List.of( persistenceXmlUrl ), transactionType ).get( persistenceUnitName ),
				integration
		);
	}

	public static EntityManagerFactoryBuilder getEntityManagerFactoryBuilder(
			PersistenceUnitDescriptor persistenceUnitDescriptor,
			Map integration,
			ClassLoader providedClassLoader) {
		return new EntityManagerFactoryBuilderImpl( persistenceUnitDescriptor, integration, providedClassLoader );
	}

	public static EntityManagerFactoryBuilder getEntityManagerFactoryBuilder(
			PersistenceUnitDescriptor persistenceUnitDescriptor,
			Map integration,
			ClassLoaderService providedClassLoaderService) {
		return new EntityManagerFactoryBuilderImpl( persistenceUnitDescriptor, integration, providedClassLoaderService );
	}

	/**
	 * For tests only
	 */
	@Internal
	public static EntityManagerFactoryBuilder getEntityManagerFactoryBuilder(
			PersistenceUnitDescriptor persistenceUnitDescriptor,
			Map integration,
			Consumer<EntityManagerFactoryBuilderImpl.MergedSettings> mergedSettingsBaseline) {
		return new EntityManagerFactoryBuilderImpl( persistenceUnitDescriptor, integration, mergedSettingsBaseline );
	}

	public static EntityManagerFactoryBuilder getEntityManagerFactoryBuilder(
			PersistenceUnitInfo persistenceUnitInfo,
			Map integration) {
		return getEntityManagerFactoryBuilder( new PersistenceUnitInfoDescriptor( persistenceUnitInfo ), integration );
	}

	public static EntityManagerFactoryBuilder getEntityManagerFactoryBuilder(
			PersistenceUnitInfo persistenceUnitInfo,
			Map integration,
			ClassLoader providedClassLoader) {
		return getEntityManagerFactoryBuilder( new PersistenceUnitInfoDescriptor( persistenceUnitInfo ), integration, providedClassLoader );
	}

	public static EntityManagerFactoryBuilder getEntityManagerFactoryBuilder(
			PersistenceUnitInfo persistenceUnitInfo,
			Map integration,
			ClassLoaderService providedClassLoaderService) {
		return getEntityManagerFactoryBuilder( new PersistenceUnitInfoDescriptor( persistenceUnitInfo ), integration, providedClassLoaderService );
	}

	/**
	 * For tests only
	 */
	@Internal
	public static EntityManagerFactoryBuilder getEntityManagerFactoryBuilder(
			PersistenceUnitInfo persistenceUnitInfo,
			Map integration,
			Consumer<EntityManagerFactoryBuilderImpl.MergedSettings> mergedSettingsBaseline) {
		return getEntityManagerFactoryBuilder( new PersistenceUnitInfoDescriptor( persistenceUnitInfo ), integration, mergedSettingsBaseline );
	}
}

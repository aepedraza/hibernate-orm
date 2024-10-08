/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or http://www.gnu.org/licenses/lgpl-2.1.html
 */

/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * Copyright (c) 2011, Red Hat Inc. or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Red Hat Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 */
package org.hibernate.orm.test.mapping.collections.custom.declaredtype;

import org.hibernate.AnnotationException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;

import org.hibernate.testing.junit4.BaseCoreFunctionalTestCase;
import org.hibernate.testing.orm.junit.DomainModel;
import org.hibernate.testing.orm.junit.DomainModelScope;
import org.hibernate.testing.orm.junit.ServiceRegistry;
import org.hibernate.testing.orm.junit.ServiceRegistryScope;
import org.junit.jupiter.api.Test;

import jakarta.persistence.OneToMany;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Test that we get an exception when an attribute whose type is not a Collection
 * is annotated with any of <ul>
 *     <li>{@linkplain jakarta.persistence.ElementCollection}</li>
 *     <li>{@linkplain jakarta.persistence.OneToMany}</li>
 *     <li>{@linkplain jakarta.persistence.ManyToMany}</li>
 *     <li>{@linkplain org.hibernate.annotations.ManyToAny}</li>
 * </ul>
 * The test specifically uses {@linkplain OneToMany}, but the handling is the same
 *
 * @author Max Rydahl Andersen
 * @author David Weinberg
 */
@ServiceRegistry
public class UserWithUnimplementedCollectionTest {
	@Test
	void testCollectionNotCollectionFailure(ServiceRegistryScope serviceRegistryScope) {
		final MetadataSources metadataSources = new MetadataSources( serviceRegistryScope.getRegistry() );
		metadataSources.addAnnotatedClasses( UserWithUnimplementedCollection.class, Email.class );
		try {
			metadataSources.buildMetadata();
			fail( "Expecting an AnnotationException" );
		}
		catch (AnnotationException e) {
			assertThat( e ).hasMessageEndingWith( "is not a collection and may not be a '@OneToMany', '@ManyToMany', or '@ElementCollection'" );
			assertThat( e ).hasMessageContaining( ".emailAddresses" );
		}
	}
}

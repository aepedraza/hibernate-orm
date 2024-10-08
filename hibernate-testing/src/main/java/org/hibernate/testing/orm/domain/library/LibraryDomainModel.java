/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 * See the lgpl.txt file in the root directory or http://www.gnu.org/licenses/lgpl-2.1.html.
 */
package org.hibernate.testing.orm.domain.library;

import org.hibernate.testing.orm.domain.AbstractDomainModelDescriptor;

/**
 * @author Steve Ebersole
 */
public class LibraryDomainModel extends AbstractDomainModelDescriptor {
	public static final LibraryDomainModel INSTANCE = new LibraryDomainModel();

	public LibraryDomainModel() {
		super( Book.class, Person.class );
	}
}

/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.envers.internal.entities.mapper.relation.lazy.initializor;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.SortedSet;

import org.hibernate.envers.boot.internal.EnversService;
import org.hibernate.envers.exception.AuditException;
import org.hibernate.envers.internal.entities.mapper.relation.MiddleComponentData;
import org.hibernate.envers.internal.entities.mapper.relation.query.RelationQueryGenerator;
import org.hibernate.envers.internal.reader.AuditReaderImplementor;

/**
 * Initializes SortedSet collection with proper Comparator
 *
 * @author Michal Skowronek (mskowr at o2 dot pl)
 */
public class SortedSetCollectionInitializor extends BasicCollectionInitializor<SortedSet> {
	private final Comparator comparator;

	public SortedSetCollectionInitializor(
			EnversService enversService,
			AuditReaderImplementor versionsReader,
			RelationQueryGenerator queryGenerator,
			Object primaryKey,
			Number revision,
			boolean removed,
			Class<? extends SortedSet> collectionClass,
			MiddleComponentData elementComponentData,
			Comparator comparator) {
		super(
				enversService,
				versionsReader,
				queryGenerator,
				primaryKey,
				revision,
				removed,
				collectionClass,
				elementComponentData
		);
		this.comparator = comparator;
	}

	@Override
	protected SortedSet initializeCollection(int size) {
		if ( comparator == null ) {
			return super.initializeCollection( size );
		}
		try {
			return collectionClass.getConstructor( Comparator.class ).newInstance(comparator);
		}
		catch (InstantiationException e) {
			throw new AuditException( e );
		}
		catch (IllegalAccessException e) {
			throw new AuditException( e );
		}
		catch (NoSuchMethodException e) {
			throw new AuditException( e );
		}
		catch (InvocationTargetException e) {
			throw new AuditException( e );
		}
	}
}

/*
 *  MIT License
 * -----------
 *
 * Copyright (c) 2016-2019 Oleksii V. KHALIKOV, PE (gfalcon.com.ua)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package ua.com.gfalcon.utils.repository.dao;

import com.google.common.base.Optional;
import ua.com.gfalcon.utils.repository.entity.AbstractEntity;
import ua.com.gfalcon.utils.repository.exception.EntityNotFoundException;

import java.util.List;



public interface EntityDao<T extends AbstractEntity> {

    /**
     * Find entity by id
     *
     * @param id
     * @return absent if none found
     */
    Optional<T> findById(Long id);

    /**
     * Find by id or throw exception if none found
     *
     * @param id
     * @return entity
     * @throws EntityNotFoundException if not found
     */
    T findByIdExpected(Long id);

    /**
     * Returns first found entities.
     *
     * @param attribute
     * @param value
     * @return Optional
     * @see EntityDao#findAllBy(String, Object)
     */
    Optional<T> findBy(String attribute, Object value);

    /**
     * Returns first found entity or throw exception is none found.
     *
     * @param attribute
     * @param value
     * @return Optional
     * @throws EntityNotFoundException
     * @see EntityDao#findAllBy(String, Object)
     */
    T findByExpected(String attribute, Object value);

    /**
     * Search for entities which have attributes with a given value
     * Note! nested properties are supported, e.g. foo.bar.pro
     *
     * @param field e.g. "nID", "foo.bar.pro"
     * @param value e.g. "nid123"
     * @return found entities or empty List if nothing found
     */
    List<T> findAllBy(String field, Object value);

    /**
     * Returns all entities from DB.
     *
     * @return empty List if table is empty.
     */
    List<T> findAll();

    /**
     * Find all entities by ids.
     *
     * @param ids
     * @return
     */
    List<T> findAll(List<Long> ids);

    /**
     * @param entity
     * @return updated or created entity
     * @see org.hibernate.Session#saveOrUpdate(Object)
     */
    T saveOrUpdate(T entity);

    /**
     * @param entities
     * @return
     * @see EntityDao#saveOrUpdate(AbstractEntity)
     */
    List<T> saveOrUpdate(List<T> entities);

    /**
     * @param id
     * @see EntityDao#delete(AbstractEntity)
     */
    void delete(Long id);

    /**
     * @param field
     * @param value
     * @return number of deleted entities
     * @see EntityDao#delete(AbstractEntity)
     * @see EntityDao#findBy(String, Object)
     */
    int deleteBy(String field, Object value);

    /**
     * @param entity
     * @throws EntityNotFoundException if entity does not exist
     */
    void delete(T entity);

    /**
     * Delete all given entities
     *
     * @param entities
     * @return not deleted entities
     */
    List<T> delete(List<T> entities);

    /**
     * Delete all entities from DB.
     */
    void deleteAll();

    /**
     * Checks if entity with given id exists
     *
     * @param id
     * @return true - if exists
     */
    boolean exists(Long id);

    /**
     * Returns class of entity
     *
     * @return
     */
    Class<T> getEntityClass();

    /**
     * Search for entities which have attribute in specified values
     *
     * @param field
     * @return
     */
    List<T> findAllByInValues(String field, List<?> value);

}
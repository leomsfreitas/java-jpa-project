/*
 * Copyright (c) 2025 Leo Freitas
 * GitHub: https://github.com/leomsfreitas
 *
 * Licensed under the MIT License.
 * You may obtain a copy of the License at
 *     https://opensource.org/licenses/MIT
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 */

package br.edu.ifsp.leo.persistence;

import br.edu.ifsp.leo.dao.StudentDao;
import br.edu.ifsp.leo.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.List;

public class StudentDaoImpl implements StudentDao {
    private final EntityManager em;

    public StudentDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Student student) {
        this.em.persist(student);
    }

    @Override
    public void deleteById(Long id) {
        Student student = this.em.find(Student.class, id);
        if (student != null) {
            this.em.remove(student);
        }
    }

    @Override
    public void update(Student student) {
        this.em.merge(student);
    }

    @Override
    public List<Student> findAllApproved() {
        String jpql = "SELECT p FROM Student p WHERE p.status = 'APROVADO'";
        return this.em.createQuery(jpql, Student.class).getResultList();
    }

    @Override
    public List<Student> findAll() {
        String jpql = "SELECT p FROM Student p";
        return this.em.createQuery(jpql, Student.class).getResultList();
    }

    @Override
    public Student findByName(String name) {
        String jpql = "SELECT p FROM Student p WHERE p.name = :name";
        try {
            return this.em.createQuery(jpql, Student.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}

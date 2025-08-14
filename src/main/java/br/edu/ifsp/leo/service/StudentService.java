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

package br.edu.ifsp.leo.service;

import br.edu.ifsp.leo.dao.StudentDao;
import br.edu.ifsp.leo.model.Student;

import java.math.BigDecimal;
import java.util.List;

public class StudentService {
    private final StudentDao studentDao;

    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public void register(String name, String ra, String email, BigDecimal grade1, BigDecimal grade2, BigDecimal grade3) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        if (ra == null || ra.isBlank()) {
            throw new IllegalArgumentException("RA não pode ser vazio.");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email não pode ser vazio.");
        }
        if (grade1 == null || grade2 == null || grade3 == null) {
            throw new IllegalArgumentException("Notas não podem ser nulas.");
        }

        studentDao.save(new Student(name, ra, email, grade1, grade2, grade3));
    }

    public List<Student> findAllApproved() {
        return studentDao.findAllApproved();
    }

    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }
        studentDao.deleteById(id);
    }

    public void update(Student student) {
        if (student == null || student.getId() == null) {
            throw new IllegalArgumentException("Aluno não pode ser nulo e deve ter um ID.");
        }
        studentDao.update(student);
    }

    public Student findByName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        return studentDao.findByName(name);
    }

    public List<Student> findAll() {
        return studentDao.findAll();
    }

}

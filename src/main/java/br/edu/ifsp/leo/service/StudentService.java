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
import br.edu.ifsp.leo.dto.StudentDto;
import br.edu.ifsp.leo.util.MapStudent;
import jakarta.persistence.EntityManager;

import java.util.List;

public class StudentService {
    private final StudentDao studentDao;
    private final EntityManager em;

    public StudentService(StudentDao studentDao, EntityManager em) {
        this.studentDao = studentDao;
        this.em = em;
    }

    public void register(StudentDto studentDto) {
        try {
            em.getTransaction().begin();
            studentDao.save(MapStudent.fromStudentDto(studentDto));
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao cadastrar: " + e.getMessage());
        }
    }

    public void deleteById(Long id) {
        if (id == null) throw new IllegalArgumentException("ID não pode ser nulo.");

        findById(id);

        try {
            em.getTransaction().begin();
            studentDao.deleteById(id);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao excluir: " + e.getMessage());
        }
    }

    public void update(StudentDto studentDto) {
        if (studentDto == null) throw new IllegalArgumentException("Aluno não pode ser nulo.");

        studentDao.findById(studentDto.id())
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado."));

        try {
            em.getTransaction().begin();
            studentDao.update(MapStudent.fromStudentDto(studentDto));
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao atualizar aluno: " + e.getMessage(), e);
        }
    }

    public StudentDto findByName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Nome não pode ser vazio.");

        return MapStudent.toStudentDto(studentDao.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado.")));
    }

    public StudentDto findById(Long id) {
        if (id == null) throw new IllegalArgumentException("ID não pode ser nulo.");

        return MapStudent.toStudentDto(studentDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado.")));
    }

    public List<StudentDto> findAllApproved() {
        return studentDao.findAllApproved()
                .stream()
                .map(MapStudent::toStudentDto)
                .toList();
    }

    public List<StudentDto> findAll() {
        return studentDao.findAll()
                .stream()
                .map(MapStudent::toStudentDto)
                .toList();
    }

}

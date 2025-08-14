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

package br.edu.ifsp.leo;

import br.edu.ifsp.leo.model.Student;
import br.edu.ifsp.leo.persistence.JpaFactory;
import br.edu.ifsp.leo.persistence.StudentDaoImpl;
import br.edu.ifsp.leo.service.StudentService;
import br.edu.ifsp.leo.view.MenuView;
import br.edu.ifsp.leo.view.TableView;

import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

public class Main {
    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        Scanner scanner = new Scanner(System.in);
        int opt;

        EntityManager em = JpaFactory.getEntityManager();
        StudentService service = new StudentService(new StudentDaoImpl(em));

        do {
            MenuView.show();
            System.out.print("Selecione uma opção: ");
            opt = scanner.nextInt();
            scanner.nextLine();

            switch (opt) {
                case 1 -> {
                    System.out.print("Nome: ");
                    String name = scanner.nextLine();
                    System.out.print("RA: ");
                    String ra = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Nota 1: ");
                    BigDecimal grade1 = scanner.nextBigDecimal();
                    System.out.print("Nota 2: ");
                    BigDecimal grade2 = scanner.nextBigDecimal();
                    System.out.print("Nota 3: ");
                    BigDecimal grade3 = scanner.nextBigDecimal();
                    scanner.nextLine();

                    em.getTransaction().begin();
                    try {
                        service.register(name, ra, email, grade1, grade2, grade3);
                        em.getTransaction().commit();
                        System.out.println("Aluno cadastrado com sucesso!");
                    } catch (Exception e) {
                        em.getTransaction().rollback();
                        System.out.println("Erro ao cadastrar: " + e.getMessage());
                    }
                }

                case 2 -> {
                    System.out.print("ID do aluno para excluir: ");
                    Long id = scanner.nextLong();
                    scanner.nextLine();

                    Student student = em.find(Student.class, id);
                    if (student == null) {
                        System.out.println("Aluno não encontrado.");
                        break;
                    }

                    em.getTransaction().begin();
                    try {
                        service.deleteById(id);
                        em.getTransaction().commit();
                        System.out.println("Aluno excluído com sucesso!");
                    } catch (Exception e) {
                        em.getTransaction().rollback();
                        System.out.println("Erro ao excluir: " + e.getMessage());
                    }
                }

                case 3 -> {
                    System.out.print("ID do aluno para alterar: ");
                    Long id = scanner.nextLong();
                    scanner.nextLine();

                    Student student = em.find(Student.class, id);
                    if (student == null) {
                        System.out.println("Aluno não encontrado.");
                        break;
                    }

                    System.out.print("Novo nome (" + student.getName() + "): ");
                    String name = scanner.nextLine();
                    System.out.print("Novo RA (" + student.getRa() + "): ");
                    String ra = scanner.nextLine();
                    System.out.print("Novo email (" + student.getEmail() + "): ");
                    String email = scanner.nextLine();
                    System.out.print("Nova nota 1 (" + student.getGrade1() + "): ");
                    BigDecimal grade1 = scanner.nextBigDecimal();
                    System.out.print("Nova nota 2 (" + student.getGrade2() + "): ");
                    BigDecimal grade2 = scanner.nextBigDecimal();
                    System.out.print("Nova nota 3 (" + student.getGrade3() + "): ");
                    BigDecimal grade3 = scanner.nextBigDecimal();
                    scanner.nextLine();

                    student.setName(name);
                    student.setRa(ra);
                    student.setEmail(email);
                    student.setGrade1(grade1);
                    student.setGrade2(grade2);
                    student.setGrade3(grade3);
                    student.calculateStatus();

                    em.getTransaction().begin();
                    try {
                        service.update(student);
                        em.getTransaction().commit();
                        System.out.println("Aluno alterado com sucesso!");
                    } catch (Exception e) {
                        em.getTransaction().rollback();
                        System.out.println("Erro ao alterar: " + e.getMessage());
                    }
                }

                case 4 -> {
                    System.out.print("Nome do aluno para buscar: ");
                    String name = scanner.nextLine();
                    try {
                        Student student = service.findByName(name);
                        if (student != null) {
                            TableView.printHeader();
                            TableView.printRow(
                                    student.getId(),
                                    student.getEmail(),
                                    student.getName(),
                                    student.getRa(),
                                    student.getGrade1().toString(),
                                    student.getGrade2().toString(),
                                    student.getGrade3().toString(),
                                    student.getStatus().name()
                            );
                            TableView.printFooter();
                        } else {
                            System.out.println("Aluno não encontrado.");
                        }
                    } catch (Exception e) {
                        System.out.println("Erro ao buscar: " + e.getMessage());
                    }
                }

                case 5 -> {
                    List<Student> approved = service.findAllApproved();
                    TableView.printHeader();
                    for (Student student : approved) {
                        TableView.printRow(
                                student.getId(),
                                student.getEmail(),
                                student.getName(),
                                student.getRa(),
                                student.getGrade1().toString(),
                                student.getGrade2().toString(),
                                student.getGrade3().toString(),
                                student.getStatus().name()
                        );
                    }
                    TableView.printFooter();
                }
                case 6 -> {
                    List<Student> all = service.findAll();
                    TableView.printHeader();
                    for (Student student : all) {
                        TableView.printRow(
                                student.getId(),
                                student.getEmail(),
                                student.getName(),
                                student.getRa(),
                                student.getGrade1().toString(),
                                student.getGrade2().toString(),
                                student.getGrade3().toString(),
                                student.getStatus().name()
                        );
                    }
                    TableView.printFooter();
                }
                case 7 -> System.out.println("Encerrando...");
                default -> {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            }
        } while (opt != 7);

        em.close();
        scanner.close();
    }
}
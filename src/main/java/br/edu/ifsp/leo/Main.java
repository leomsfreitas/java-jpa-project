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

import br.edu.ifsp.leo.dto.StudentDto;
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
        StudentService service = new StudentService(new StudentDaoImpl(em), em);

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
                    try {
                        service.register(StudentDto.of(name, ra, email, grade1, grade2, grade3));
                        System.out.println("Aluno cadastrado com sucesso!");
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }

                }

                case 2 -> {
                    System.out.print("ID do aluno para excluir: ");
                    Long id = scanner.nextLong();
                    scanner.nextLine();
                    try {
                        service.deleteById(id);
                        System.out.println("Aluno excluído com sucesso!");
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }

                }

                case 3 -> {
                    System.out.print("ID do aluno para alterar: ");
                    Long id = scanner.nextLong();
                    scanner.nextLine();

                    try {
                        StudentDto student = service.findById(id);

                        System.out.print("Novo nome (" + student.name() + "): ");
                        String name = scanner.nextLine();
                        System.out.print("Novo RA (" + student.ra() + "): ");
                        String ra = scanner.nextLine();
                        System.out.print("Novo email (" + student.email() + "): ");
                        String email = scanner.nextLine();
                        System.out.print("Nova nota 1 (" + student.grade1() + "): ");
                        BigDecimal grade1 = scanner.nextBigDecimal();
                        System.out.print("Nova nota 2 (" + student.grade2() + "): ");
                        BigDecimal grade2 = scanner.nextBigDecimal();
                        System.out.print("Nova nota 3 (" + student.grade3() + "): ");
                        BigDecimal grade3 = scanner.nextBigDecimal();
                        scanner.nextLine();

                        service.update(StudentDto.of(id, name, ra, email, grade1, grade2, grade3));
                        System.out.println("Aluno alterado com sucesso!");
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                }

                case 4 -> {
                    System.out.print("Nome do aluno para buscar: ");
                    String name = scanner.nextLine();

                    try {
                        StudentDto studentDto = service.findByName(name);
                        if (studentDto != null) {
                            TableView.printHeader();
                            TableView.printRow(
                                    studentDto.id(),
                                    studentDto.email(),
                                    studentDto.name(),
                                    studentDto.ra(),
                                    studentDto.grade1(),
                                    studentDto.grade2(),
                                    studentDto.grade3(),
                                    studentDto.status()
                            );
                            TableView.printFooter();
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }

                case 5 -> {
                    List<StudentDto> approved = service.findAllApproved();
                    TableView.printHeader();
                    for (StudentDto student : approved) {
                        TableView.printRow(
                                student.id(),
                                student.email(),
                                student.name(),
                                student.ra(),
                                student.grade1(),
                                student.grade2(),
                                student.grade3(),
                                student.status()
                        );
                    }
                    TableView.printFooter();
                }
                case 6 -> {
                    List<StudentDto> all = service.findAll();
                    TableView.printHeader();
                    for (StudentDto student : all) {
                        TableView.printRow(
                                student.id(),
                                student.email(),
                                student.name(),
                                student.ra(),
                                student.grade1(),
                                student.grade2(),
                                student.grade3(),
                                student.status()
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
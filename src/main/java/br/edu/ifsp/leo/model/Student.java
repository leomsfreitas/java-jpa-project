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

package br.edu.ifsp.leo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name="alunos")
public class Student {

    public Student() {}

    public Student(String name, String ra, String email, BigDecimal grade1, BigDecimal grade2, BigDecimal grade3) {
        this.name = name;
        this.ra = ra;
        this.email = email;
        this.grade1 = grade1;
        this.grade2 = grade2;
        this.grade3 = grade3;
        calculateStatus();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 12)
    private String ra;

    @Column(length = 30)
    private String email;

    @Column(precision = 5, scale = 2)
    private BigDecimal grade1;

    @Column(precision = 5, scale = 2)
    private BigDecimal grade2;

    @Column(precision = 5, scale = 2)
    private BigDecimal grade3;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getGrade1() {
        return grade1;
    }

    public void setGrade1(BigDecimal grade1) {
        this.grade1 = grade1;
    }

    public BigDecimal getGrade2() {
        return grade2;
    }

    public void setGrade2(BigDecimal grade2) {
        this.grade2 = grade2;
    }

    public BigDecimal getGrade3() {
        return grade3;
    }

    public void setGrade3(BigDecimal grade3) {
        this.grade3 = grade3;
    }

    public void calculateStatus() {
        BigDecimal avg = grade1.add(grade2).add(grade3)
                .divide(BigDecimal.valueOf(3), 2, RoundingMode.HALF_UP);

        if (avg.compareTo(BigDecimal.valueOf(6)) >= 0) {
            this.status = Status.APROVADO;
        } else if (avg.compareTo(BigDecimal.valueOf(4)) >= 0) {
            this.status = Status.RECUPERACAO;
        } else {
            this.status = Status.REPROVADO;
        }
    }

    public Status getStatus() {
        return status;
    }

}

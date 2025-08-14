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

package br.edu.ifsp.leo.view;

public class MenuView {
    private MenuView() {

    }

    public static void show() {
        System.out.println("""
                +---+----------------------------------------------+
                | # | Cadastro de Alunos                           |
                +---+----------------------------------------------+
                | 1 | Cadastrar Aluno                              |
                | 2 | Excluir Aluno                                |
                | 3 | Alterar Aluno                                |
                | 4 | Buscar Aluno pelo Nome                       |
                | 5 | Listar Alunos (com status aprovação)         |
                | 6 | Listar Alunos (todos)                        |
                | 7 | Encerrar                                     |
                +---+----------------------------------------------+
                """);
    }
}

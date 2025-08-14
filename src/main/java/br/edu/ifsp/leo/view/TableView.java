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

public class TableView {
    private TableView() {

    }

    public static void printHeader() {
        System.out.println("+----+--------------------------------+----------------------+--------------+--------+--------+--------+-------------+");
        System.out.printf("| %-2s | %-30s | %-20s | %-12s | %-6s | %-6s | %-6s | %-11s |%n",
                "ID", "Email", "Nome", "RA", "Nota 1", "Nota 2", "Nota 3", "Status");
        System.out.println("+----+--------------------------------+----------------------+--------------+--------+--------+--------+-------------+");
    }

    public static void printFooter() {
        System.out.println("+----+--------------------------------+----------------------+--------------+--------+--------+--------+-------------+");
    }

    public static void printRow(Long id, String email, String nome, String ra, String nota1, String nota2, String nota3, String status) {
        System.out.printf("| %-2s | %-30s | %-20s | %-12s | %-6s | %-6s | %-6s | %-11s |%n",
                id, email, nome, ra, nota1, nota2, nota3, status);
    }


}

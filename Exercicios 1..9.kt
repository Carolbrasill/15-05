import java.io.PrintStream
import java.io.FileOutputStream
import java.io.FileDescriptor

fun main() {
        System.setOut(PrintStream(FileOutputStream(FileDescriptor.out), true, "UTF-8"))

        println("Qual núumero você quer saber se é par ou ímpar?")
        val numero = readLine()!!.toInt()
        parimpar(numero)

        println("Qual foi a nota?")
        val nota = readLine()!!.toInt()
        classificarnota(nota)

        println("Qual o seu nome?")
        val nome = readLine()!!
        println("Qual a sua carga horária?")
        val cargahoraria = readLine()!!
        nomeecargahoraria(nome, cargahoraria)

        val cursos = listOf(
            Curso(1, "Kotlin", "Programação", 9.5, 40, true),
            Curso(2, "Java", "Programação", 8.0, 60, false),
            Curso(3, "Photoshop", "Design", 7.5, 30, true),
            Curso(4, "Figma", "Design", 9.0, 20, true)
        )

        println("\n EXERCÍCIOS RELACIONADOS A CURSOS ")

        println(buscarCurso(2, cursos))
        println(cursosAtivos(cursos))
        println(ordenarCursos(cursos))
        println(separarCategorias(cursos))
        println(somaCargaHoraria(cursos))
        println(categoriasSemRepeticao(cursos))
    }

fun parimpar(numero: Int) {

    if (numero % 2 == 0) {
        println("Número par")
    } else {
        println("Número Impar")
    }
}

fun classificarnota(nota: Int): String {
    if (nota >= 90 && nota <= 100) {
        println("Nota Excelente.")
    }
    if (nota >= 79 && nota <= 89) {
        println("Aprovado.")
    }
    if (nota >= 50 && nota <= 69) {
        println("Recuperação.")
    }
    if (nota <= 49) {
        println("Reprovado.")
    }else{
        println("Nota Inválida.")
    }
    return nota.toString()
}
fun nomeecargahoraria(nome: String, cargahoraria: String): String {
    if (nome.isBlank()) {
        return "Nome obrigatório"
    }
    val carga = cargahoraria.toIntOrNull()
        ?: return "Carga horária deve ser um número"

    if (carga <= 0) {
        return "Carga horária deve ser maior que zero"
    }
    if (carga > 400) {
        return "Carga horária não pode passar de 400"
    }
    return "Dados válidados"
}

    data class Curso(
        val id: Int,
        val nome: String,
        val categoria: String,
        val nota: Double,
        val cargaHoraria: Int,
        val ativo: Boolean
    )

    fun buscarCurso(id: Int, cursos: List<Curso>): Curso? {

        for (curso in cursos) {
            if (curso.id == id) {
                return curso
            }
        }

        return null
    }

    fun cursosAtivos(cursos: List<Curso>): List<Curso> {

        val ativos = mutableListOf<Curso>()

        for (curso in cursos) {
            if (curso.ativo) {
                ativos.add(curso)
            }
        }

        return ativos
    }

    fun ordenarCursos(cursos: List<Curso>): List<Curso> {
        return cursos.sortedWith(compareByDescending<Curso> { it.nota })
    }

    fun separarCategorias(cursos: List<Curso>): MutableMap<String, MutableList<Curso>> {

        val categorias = mutableMapOf<String, MutableList<Curso>>()

        for (curso in cursos) {

            if (categorias.containsKey(curso.categoria)) {
                categorias[curso.categoria]?.add(curso)
            } else {
                categorias[curso.categoria] = mutableListOf(curso)
            }
        }

        return categorias
    }

    fun somaCargaHoraria(cursos: List<Curso>): Int {

        var total = 0

        for (curso in cursos) {
            if (curso.ativo) {
                total += curso.cargaHoraria
            }
        }

        return total
    }

    fun categoriasSemRepeticao(cursos: List<Curso>): List<String> {

        val categorias = mutableListOf<String>()

        for (curso in cursos) {

            if (!categorias.contains(curso.categoria)) {
                categorias.add(curso.categoria)
            }
        }

        return categorias
    }


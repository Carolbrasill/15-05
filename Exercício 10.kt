import java.io.PrintStream
import java.io.FileOutputStream
import java.io.FileDescriptor

fun main() {
        System.setOut(PrintStream(FileOutputStream(FileDescriptor.out), true, "UTF-8"))
    val cursos = listOf(
        Curso(1, "Kotlin", "Programação", 9.5, 40, true),
        Curso(2, "Java", "Programação", 8.0, 60, true),
        Curso(3, "Python", "Programação", 9.0, 50, true),
        Curso(4, "Figma", "Design", 9.2, 30, true),
        Curso(5, "Photoshop", "Design", 7.8, 40, false),
        Curso(6, "Excel", "Produtividade", 8.5, 20, true)
    )

    println("=== TESTE BUSCA POR NOME ===")
    println(buscarPorNome(cursos, "Java"))

    println("\n=== TESTE FILTRO POR CATEGORIA ===")
    println(filtrarPorCategoria(cursos, "Programação"))

    println("\n=== RANKING ===")
    println(rankingPorNota(cursos))

    println("\n=== RESUMO POR CATEGORIA ===")
    println(resumoPorCategoria(cursos))

    println("\n=== VALIDAÇÃO ===")
    val teste = Curso(7, "", "Programação", 11.0, -10, true)
    println(validarCurso(teste))
}
    data class Curso(
        val id: Int,
        val nome: String,
        val categoria: String,
        val nota: Double,
        val cargaHoraria: Int,
        val ativo: Boolean
    )

    fun buscarPorNome(cursos: List<Curso>, nome: String): Curso? {
        for (curso in cursos) {
            if (curso.nome.equals(nome, ignoreCase = true)) {
                return curso
            }
        }
        return null
    }

    fun filtrarPorCategoria(cursos: List<Curso>, categoria: String): List<Curso> {
        val resultado = mutableListOf<Curso>()
        for (curso in cursos) {
            if (curso.categoria.equals(categoria, ignoreCase = true)) {
                resultado.add(curso)
            }
        }
        return resultado
    }

    fun rankingPorNota(cursos: List<Curso>): List<Curso> {
        return cursos.sortedByDescending { it.nota }
    }

    fun resumoPorCategoria(cursos: List<Curso>): Map<String, Int> {
        val resumo = mutableMapOf<String, Int>()

        for (curso in cursos) {
            val atual = resumo[curso.categoria] ?: 0
            resumo[curso.categoria] = atual + 1
        }

        return resumo
    }

    fun validarCurso(curso: Curso): Boolean {
        if (curso.nome.isBlank()) return false
        if (curso.categoria.isBlank()) return false
        if (curso.nota !in 0.0..10.0) return false
        if (curso.cargaHoraria <= 0) return false
        return true
    }


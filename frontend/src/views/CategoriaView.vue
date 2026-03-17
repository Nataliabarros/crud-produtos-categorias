<script setup lang="ts">
import { ref, onMounted } from "vue"
import { listarCategorias, deletarCategoria } from "../services/categoriaService"

const categorias = ref([])

// carregar categorias
const carregarCategorias = async () => {
  categorias.value = await listarCategorias()
}

// excluir categoria
const removerCategoria = async (id: number) => {

  const confirmar = confirm("Deseja excluir essa categoria?")

  if (!confirmar) return

  await deletarCategoria(id)

  await carregarCategorias()
}

// quando abrir tela
onMounted(() => {
  carregarCategorias()
})
</script>

<template>
  <div>

    <h1>Lista de Categorias</h1>

    <table border="1" cellpadding="8">
      <thead>
        <tr>
          <th>Nome</th>
          <th>Ações</th>
        </tr>
      </thead>

      <tbody>
        <tr v-for="categoria in categorias" :key="categoria.id">
          <td>{{ categoria.nome }}</td>

          <td>
            <button @click="removerCategoria(categoria.id)">
              Excluir
            </button>
          </td>
        </tr>
      </tbody>
    </table>

  </div>
</template>
<script setup lang="ts">
import { ref, onMounted } from "vue"
import { listarProdutos, deletarProduto } from "../services/produtoService"
import ProdutoForm from "../components/ProdutoForm.vue"

const produtos = ref([])

// 👉 produto selecionado para edição
const produtoSelecionado = ref(null)

// carregar produtos
const carregarProdutos = async () => {
  produtos.value = await listarProdutos()
}

// excluir produto
const removerProduto = async (id: number) => {

  const confirmar = confirm("Deseja realmente excluir este produto?")

  if (!confirmar) return

  await deletarProduto(id)

  await carregarProdutos()
}

// 👉 editar produto
const editarProduto = (produto: any) => {
  produtoSelecionado.value = produto
}

// quando a tela abre
onMounted(() => {
  carregarProdutos()
})
</script>

<template>
  <div>

    <!-- FORMULÁRIO -->
    <ProdutoForm 
      @produtoCriado="carregarProdutos"
      :produtoParaEditar="produtoSelecionado"
    />

    <h1>Lista de Produtos</h1>

    <!-- TABELA -->
    <table border="1" cellpadding="8">

      <thead>
        <tr>
          <th>Nome</th>
          <th>Preço</th>
          <th>Categoria</th>
          <th>Ações</th>
        </tr>
      </thead>

      <tbody>
        <tr v-for="produto in produtos" :key="produto.id">
          
          <td>{{ produto.nome }}</td>
          
          <td>{{ produto.preco }}</td>
          
          <td>{{ produto.categoria?.nome }}</td>

          <td>

            <!-- BOTÃO EDITAR -->
            <button @click="editarProduto(produto)">
              Editar
            </button>

            <!-- BOTÃO EXCLUIR -->
            <button @click="removerProduto(produto.id)">
              Excluir
            </button>

          </td>

        </tr>
      </tbody>

    </table>

  </div>
</template>
<script setup lang="ts">
import { ref, onMounted } from "vue"
import { listarProdutos, deletarProduto } from "../services/produtoService"
import ProdutoForm from "../components/ProdutoForm.vue"

const produtos = ref([])

const carregarProdutos = async () => {
  produtos.value = await listarProdutos()
}

const removerProduto = async (id: number) => {

  const confirmar = confirm("Deseja realmente excluir este produto?")

  if (!confirmar) return

  await deletarProduto(id)

  await carregarProdutos()
}

onMounted(() => {
  carregarProdutos()
})
</script>

<template>
  <div>

    <ProdutoForm @produtoCriado="carregarProdutos" />

    <h1>Lista de Produtos</h1>

    <ul>
      <li
        v-for="produto in produtos"
        :key="produto.id"
      >
        {{ produto.nome }} -
        {{ produto.preco }} -
        {{ produto.categoria?.nome }}

        <button @click="removerProduto(produto.id)">
          Excluir
        </button>

      </li>
    </ul>

  </div>
</template>
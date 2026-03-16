<script setup lang="ts">
import { ref, onMounted } from "vue"
import { listarProdutos } from "../services/produtoService"
import ProdutoForm from "../components/ProdutoForm.vue"
import type { Produto } from "../types/Produto"

const produtos = ref<Produto[]>([])

const carregarProdutos = async () => {
  produtos.value = await listarProdutos()
}

onMounted(carregarProdutos)
</script>

<template>
  <div>

    <ProdutoForm @produtoCriado="carregarProdutos" />

    <h1>Lista de Produtos</h1>

    <ul>
      <li v-for="produto in produtos" :key="produto.id">
        {{ produto.nome }} - {{ produto.preco }} - {{ produto.categoria.nome }}
      </li>
    </ul>

  </div>
</template>
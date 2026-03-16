<script setup lang="ts">
import { ref, onMounted } from "vue"
import { criarProduto } from "../services/produtoService"
import { listarCategorias } from "../services/categoriaService"

const emit = defineEmits(["produtoCriado"])

const nome = ref("")
const preco = ref(0)

const categorias = ref([])
const categoriaSelecionada = ref<number | null>(null)

const carregarCategorias = async () => {
  categorias.value = await listarCategorias()
}

onMounted(carregarCategorias)

const salvarProduto = async () => {
  if (!categoriaSelecionada.value) {
    alert("Selecione uma categoria")
    return
  }

  await criarProduto({
    nome: nome.value,
    preco: preco.value,
    categoria: {
      id: categoriaSelecionada.value
    }
  })

  emit("produtoCriado")
  alert("Produto criado com sucesso")

  nome.value = ""
  preco.value = 0
  categoriaSelecionada.value = null
}
</script>

<template>
  <div>
    <h2>Criar Produto</h2>

    <input v-model="nome" placeholder="Nome do produto" />

    <input v-model="preco" type="number" placeholder="Preço" />

    <select v-model="categoriaSelecionada">
      <option disabled value="">Selecione uma categoria</option>

      <option
        v-for="categoria in categorias"
        :key="categoria.id"
        :value="categoria.id"
      >
        {{ categoria.nome }}
      </option>
    </select>

    <button @click="salvarProduto">
      Salvar
    </button>

  </div>
</template>
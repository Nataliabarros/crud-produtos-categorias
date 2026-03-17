<script setup lang="ts">
import { ref, onMounted, watch } from "vue"
import { criarProduto } from "../services/produtoService"
import { listarCategorias } from "../services/categoriaService"

const props = defineProps({
  produtoParaEditar: Object
})

const emit = defineEmits(["produtoCriado"])

const nome = ref("")
const preco = ref(0)
const categorias = ref([])
const categoriaSelecionada = ref(null)
const id = ref<number | null>(null)

const carregarCategorias = async () => {
  categorias.value = await listarCategorias()
}

onMounted(carregarCategorias)

watch(() => props.produtoParaEditar, (novoProduto: any) => {
  if (novoProduto) {
    id.value = novoProduto.id
    nome.value = novoProduto.nome
    preco.value = novoProduto.preco
    categoriaSelecionada.value = novoProduto.categoria?.id
  }
})

const salvarProduto = async () => {

  const produto = {
    id: id.value,
    nome: nome.value,
    preco: preco.value,
    categoria: {
      id: categoriaSelecionada.value
    }
  }

  if (id.value) {
    await fetch(`http://localhost:8080/produtos/${id.value}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(produto)
    })
    alert("Produto atualizado!")
  } else {
    await criarProduto(produto)
    alert("Produto criado!")
  }

  emit("produtoCriado")

  nome.value = ""
  preco.value = 0
  categoriaSelecionada.value = null
  id.value = null
}
</script>

<template>
  <div>
    <h2>Criar / Editar Produto</h2>

    <input v-model="nome" placeholder="Nome do produto" />

    <input v-model="preco" type="number" placeholder="Preço" />

    <!-- SELECT DE CATEGORIA -->
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